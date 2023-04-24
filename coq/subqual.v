Require Import Coq.Strings.String.
Require Import Coq.Init.Nat.
Require Import Coq.Lists.List.
Require Import Coq.MSets.MSetList.
Require Import Coq.MSets.MSetFacts.
Require Import Coq.MSets.MSetProperties.
Require Import Coq.MSets.MSetDecide.
Require Import Coq.Structures.OrdersEx.
Require Import Coq.Program.Wf.
Require Import Coq.Logic.Classical.
Local Open Scope string_scope.
Local Open Scope nat_scope.

Module StrSet := Make String_as_OT.
Module SFacts := Facts StrSet.
Module SProps := Properties StrSet.
Module SDecide := Decide StrSet.

Definition qualset := StrSet.t.

Inductive symbol :=
| Sym (isFun isFresh: bool) (qual: qualset).

Definition context := list (string * symbol).

Fixpoint ddomain (ctx: context) : qualset :=
  match ctx with
  | nil => StrSet.singleton ""
  | (x, _)::tl => StrSet.add x (ddomain tl)
  end.

Inductive wf_context : context -> Prop :=
| wf_base: wf_context nil
| wf_step: forall n (fn fr : bool) q tl, wf_context tl
    -> ~StrSet.In n (ddomain tl)
    -> StrSet.Subset q (if fn then ddomain ((n, Sym fn fr q)::tl) else ddomain tl)
    -> wf_context ((n, Sym fn fr q)::tl) .

Fixpoint is_well_formed (ctx: context) : bool :=
  match ctx with
  | nil => true
  | (n, Sym fn fr q)::tl => is_well_formed tl
      && negb (StrSet.mem n (ddomain tl))
      && StrSet.subset q (if fn then ddomain ctx else ddomain tl)
  end.

Lemma is_well_formed_iff: forall ctx,
  wf_context ctx <-> is_well_formed ctx = true.
Proof.
  intros. split; intros.
  - induction H; auto; clear H.
    simpl.
    apply andb_true_iff; split.
    apply andb_true_iff; split.
    * assumption.
    * apply SFacts.not_mem_iff in H0.
      rewrite H0; auto.
    * apply SFacts.subset_iff in H1.
      auto.
  - induction ctx.
    apply wf_base.
    destruct a.
    destruct s0.
    simpl in *.
    apply andb_true_iff in H; destruct H.
    apply andb_true_iff in H; destruct H.
    apply IHctx in H; clear IHctx.
    apply negb_true_iff in H1.
    apply SFacts.not_mem_iff in H1.
    apply SFacts.subset_iff in H0.
    apply wf_step; assumption.
Qed.

Fixpoint retrieve (ctx: context) (x: string) : option symbol :=
  match ctx with
  | nil => None
  | (x', Sym _ _ _ as s)::tl =>
      if (x =? x')%string then Some s else retrieve tl x
  end.

Lemma retrieve_is_well_formed: forall G x fn fr q,
  retrieve G x = Some (Sym fn fr q) -> wf_context G -> StrSet.In x (ddomain G) /\ StrSet.Subset q (ddomain G).
Proof.
  intros.
  induction G; simpl in *; try discriminate.
  inversion H0; subst.
  simpl in *.
  destruct (x =? n)%string eqn:?.
  - inversion H; subst.
    apply String.eqb_eq in Heqb; subst.
    destruct fn; split; SDecide.fsetdec.
  - apply IHG in H; auto.
    split; SDecide.fsetdec.
Qed.

Inductive subQual: context -> qualset -> qualset -> Prop :=
| q_sub: forall G p q,
    wf_context G -> StrSet.Subset p q -> StrSet.Subset q (ddomain G)
    -> subQual G p q
| q_trans: forall G p q r,
    subQual G p q -> subQual G q r
    -> subQual G p r
| q_var: forall G x q,
    wf_context G -> retrieve G x = Some (Sym false false q)
    -> subQual G (StrSet.singleton x) q
| q_self: forall G f q,
    wf_context G -> retrieve G f = Some (Sym true false q)
    -> subQual G (StrSet.add f q) (StrSet.singleton f)
| q_cong: forall G p q q',
    subQual G q q' -> StrSet.Subset p (ddomain G)
    -> subQual G (StrSet.union p q) (StrSet.union p q') .

Lemma subQual_is_well_formed: forall G p q,
  subQual G p q -> wf_context G /\ StrSet.Subset p (ddomain G) /\ StrSet.Subset q (ddomain G).
Proof.
  intros.
  induction H.
  - split; try split; auto; SDecide.fsetdec.
  - destruct IHsubQual1; destruct H2.
    destruct IHsubQual2; destruct H5.
    split; try split; auto; SDecide.fsetdec.
  - apply retrieve_is_well_formed in H0; auto. destruct H0.
    split; try split; auto; SDecide.fsetdec.
  - apply retrieve_is_well_formed in H0; auto. destruct H0.
    split; try split; auto; SDecide.fsetdec.
  - destruct IHsubQual; destruct H2.
    split; try split; auto; SDecide.fsetdec.
Qed.

Lemma q_cong': forall G p1 p2 q,
  subQual G p1 q -> subQual G p2 q -> subQual G (StrSet.union p1 p2) q.
Proof.
  intros.
  apply subQual_is_well_formed in H as ?.
  destruct H1. destruct H2.
  apply subQual_is_well_formed in H0 as ?.
  destruct H4. destruct H5.
  apply q_trans with (q := StrSet.union q q).
  apply q_trans with (q := StrSet.union q p2).
  apply q_trans with (q := StrSet.union p2 q).
  apply q_trans with (q := StrSet.union p2 p1).
  - apply q_sub; auto; SDecide.fsetdec.
  - apply q_cong; assumption.
  - apply q_sub; auto; SDecide.fsetdec.
  - apply q_cong; assumption.
  - apply q_sub; auto; SDecide.fsetdec.
Qed.

Lemma q_cong_fold: forall G p q,
  wf_context G -> StrSet.Subset q (ddomain G)
  -> (forall x, StrSet.In x p -> subQual G (StrSet.singleton x) q)
  -> subQual G p q.
Proof.
  intros.
  remember (StrSet.fold StrSet.add p StrSet.empty).
  apply q_trans with (q := t).
  * assert (StrSet.Equal t p).
    { subst. apply SProps.fold_identity. }
    assert (StrSet.Subset p (ddomain G)).
    {
      unfold StrSet.Subset.
      intros.
      apply H1 in H3.
      apply subQual_is_well_formed in H3.
      SDecide.fsetdec.
    }
    apply q_sub; auto.
    all: SDecide.fsetdec.
  * subst.
    apply SProps.fold_rec.
    + intros.
      apply q_sub; auto.
      SDecide.fsetdec.
    + intros.
      apply H1 in H2.
      apply q_trans with (q := StrSet.union (StrSet.singleton x) a).
      --  apply subQual_is_well_formed in H2.
          apply subQual_is_well_formed in H5.
          apply q_sub; auto.
          all: SDecide.fsetdec.
      --  apply q_cong'; auto.
Qed.

Definition expand_one_step (ctx: context) (qual: qualset) : option qualset :=
  match is_well_formed ctx && StrSet.subset qual (ddomain ctx) with
  | true => Some (StrSet.union qual (StrSet.fold (fun x p =>
      match retrieve ctx x with
      | Some (Sym true false q) => StrSet.union (StrSet.add x q) p
      | _ => p
      end) qual StrSet.empty))
  | false => None
  end.

Lemma expand_one_step_is_increasing : forall G q1 q2,
  expand_one_step G q1 = Some q2 -> StrSet.Subset q1 q2.
Proof.
  intros.
  unfold expand_one_step in H.
  destruct (is_well_formed G && StrSet.subset q1 (ddomain G)) eqn:?;
    try discriminate.
  injection H; clear H; intros.
  rewrite <- H; clear H.
  SDecide.fsetdec.
Qed.

Lemma expand_one_step_is_sound : forall G q1 q2,
  expand_one_step G q1 = Some q2 -> subQual G q2 q1.
Proof.
  intros.
  unfold expand_one_step in H.
  destruct (is_well_formed G && StrSet.subset q1 (ddomain G)) eqn:?;
    try discriminate.
  injection H; clear H; intro.
  rewrite <- H; clear H.
  apply andb_true_iff in Heqb.
  destruct Heqb.
  apply is_well_formed_iff in H.
  apply SFacts.subset_iff in H0.
  apply q_cong'.
  - apply q_sub; auto.
    SDecide.fsetdec.
  - apply SProps.fold_rec; intros.
    * apply q_sub; auto; SDecide.fsetdec.
    * apply SProps.Add_Equal in H3.
      apply subQual_is_well_formed in H4 as ?.
      destruct H5. destruct H6.
      assert (subQual G a s'').
      + apply q_trans with (q := s'); auto.
        apply q_sub; auto; SDecide.fsetdec.
      + destruct (retrieve G x) eqn:?; auto.
        destruct s.
        destruct isFun; auto.
        destruct isFresh; auto.
        apply q_cong'; auto.
        apply q_trans with (q := StrSet.singleton x).
        apply q_self; auto.
        apply q_sub; auto; SDecide.fsetdec.
Qed.

Lemma expand_one_step_is_monotonic : forall G q1 q1' q2 q2',
  StrSet.Subset q1 q2 -> expand_one_step G q1 = Some q1' -> expand_one_step G q2 = Some q2'
  -> StrSet.Subset q1' q2'.
Proof.
  intros.
  unfold expand_one_step in *.
  destruct (is_well_formed G) eqn:?; simpl in *; try discriminate.
  destruct (StrSet.subset q1 (ddomain G)) eqn:?; simpl in *; try discriminate.
  destruct (StrSet.subset q2 (ddomain G)) eqn:?; simpl in *; try discriminate.
  inversion H0; clear H0.
  inversion H1; clear H1.
  assert (StrSet.Subset q1' q2'); try SDecide.fsetdec.
  rewrite <- H3.
  apply SProps.fold_rec. {
    intros.
    SDecide.fsetdec.
  }
  intros.
  apply SProps.Add_Equal in H4.
  destruct (retrieve G x) eqn:?; try SDecide.fsetdec.
  destruct s.
  destruct isFun eqn:?; try SDecide.fsetdec.
  destruct isFresh eqn:?; try SDecide.fsetdec.
  assert (StrSet.Subset qual q2'); try SDecide.fsetdec.
  rewrite <- H2; clear H2 H3.
  assert (StrSet.In x q2); try SDecide.fsetdec.
  revert H2.
  apply SProps.fold_rec. {
    intros.
    exfalso.
    SDecide.fsetdec.
  }
  intros.
  apply SProps.Add_Equal in H6.
  destruct (x =? x0)%string eqn:?. {
    apply String.eqb_eq in Heqb4; subst.
    rewrite Heqo.
    SDecide.fsetdec.
  }
  apply String.eqb_neq in Heqb4.
  assert (StrSet.In x s'0).
  SDecide.fsetdec.
  apply H7 in H9.
  destruct (retrieve G x0) eqn:?; try SDecide.fsetdec.
  destruct s.
  destruct isFun0 eqn:?; try SDecide.fsetdec.
  destruct isFresh0 eqn:?; try SDecide.fsetdec.
Qed.

Program Fixpoint expand (ctx: context) (qual: qualset)
                        {measure (StrSet.cardinal (StrSet.diff (ddomain ctx) qual))} : option qualset :=
  match expand_one_step ctx qual with
  | Some qual' =>
    match StrSet.subset qual' qual with
    | true => Some qual'
    | false => expand ctx qual'
    end
  | None => None
  end.
Next Obligation.
  symmetry in Heq_anonymous0.
  apply expand_one_step_is_sound in Heq_anonymous0 as ?.
  apply expand_one_step_is_increasing in Heq_anonymous0.
  apply subQual_is_well_formed in H.
  destruct H; destruct H0.
  assert (~StrSet.Subset qual' qual).
  {
    intro.
    symmetry in Heq_anonymous.
    apply not_true_iff_false in Heq_anonymous.
    apply Heq_anonymous.
    apply SFacts.subset_iff.
    assumption.
  }
  unfold StrSet.Subset in H2.
  apply not_all_ex_not in H2.
  destruct H2.
  apply SProps.subset_cardinal_lt with (x := x); SDecide.fsetdec.
Qed.

(*
Axiom subset' : option qualset -> option qualset -> bool.
Axiom expand_one_step' : (context * option qualset) -> option qualset.

Inductive R : (context * option qualset) -> (context * option qualset) -> Prop :=
  | CallExpand : forall ctx_q, R (fst ctx_q, expand_one_step' ctx_q)  ctx_q
.

Definition expand' (ctx_qual : context * option qualset) (expand: forall c_q, R c_q ctx_qual -> option qualset): option qualset :=
  match subset' (expand_one_step' ctx_qual) (snd ctx_qual) with
  | true => (expand_one_step' ctx_qual)
  | false => expand _ (CallExpand ctx_qual)
  end.

Axiom measure : (context * option qualset) -> nat.

Lemma wfR' : forall n c_q, measure c_q <= n -> Acc R c_q.
Admitted.

Theorem wfR : well_founded R.
Proof.
  red. intros c_q. eapply wfR'. auto.
Defined.

Definition expand : (context * option qualset) -> option qualset :=
  Fix wfR (fun _ => option qualset) expand'.

(* Providing an unfolding requires extensionality. *)
Axiom extensionality : forall (A : Type) (B : A -> Type)
  (f g : forall a : A, B a),
(forall a : A, f a = g a) -> f = g.

Theorem expand_extensional :
forall (T1 : (context * option qualset)) (f g : forall T2, R T2 T1 -> option qualset),
(forall T2 (r : R T2 T1), f T2 r = g T2 r)
-> expand' T1 f = expand' T1 g.
Proof.
intros;
assert (f = g) by (eauto using extensionality); subst; eauto.
Qed.
#[global] Hint Resolve expand_extensional : core.

(* unfolding tactics for hypotheses and goal *)
Tactic Notation "unfold_expand" "in" hyp(H) :=
unfold expand in H; rewrite Fix_eq in H;
[ simpl in H; repeat fold expand in H | apply expand_extensional ].

Ltac unfold_expand :=
unfold expand; rewrite Fix_eq;
[ simpl; repeat fold expand | apply expand_extensional ].
*)

Inductive Expand: context -> qualset -> qualset -> Prop :=
| ex_top: forall ctx q1 q2, expand_one_step ctx q1 = Some q2
  -> StrSet.Subset q2 q1 -> Expand ctx q1 q2
| ex_ind: forall ctx q1 q q2, expand_one_step ctx q1 = Some q
  -> ~StrSet.Subset q q1 -> Expand ctx q q2 -> Expand ctx q1 q2.

Lemma expand_is_sound: forall G q1 q2, Expand G q1 q2 -> subQual G q2 q1.
Proof.
  intros.
  induction H.
  - apply expand_one_step_is_sound in H.
    assumption.
  - apply expand_one_step_is_sound in H.
    apply q_trans with (q := q); assumption.
Qed.

Lemma expand_is_increasing: forall G q1 q2,
  Expand G q1 q2 -> StrSet.Subset q1 q2.
Proof.
  intros.
  induction H.
  - apply expand_one_step_is_increasing in H.
    assumption.
  - apply expand_one_step_is_increasing in H.
    SDecide.fsetdec.
Qed.

Lemma expand_is_terminating: forall G q, wf_context G -> StrSet.Subset q (ddomain G)
  -> exists q', Expand G q q'.
Admitted.

Lemma expand_is_well_formed: forall G q1 q2,
  Expand G q1 q2 -> StrSet.Subset q2 (ddomain G).
Proof.
  intros.
  apply expand_is_sound in H.
  apply subQual_is_well_formed in H.
  SDecide.fsetdec.
Qed.

Lemma expand_is_monotonic: forall G q1 q1' q2 q2',
  StrSet.Subset q1 q2 -> Expand G q1 q1' -> Expand G q2 q2' -> StrSet.Subset q1' q2'.
Proof.
  intros.
  generalize dependent q2.
  generalize dependent q2'.
  induction H0; intros.
  - generalize dependent q1.
    generalize dependent q2.
    induction H2; intros.
    * apply (expand_one_step_is_monotonic ctx q3 _ q1 _); auto.
    * apply (IHExpand q0 q3); auto.
      apply expand_one_step_is_increasing in H.
      SDecide.fsetdec.
  - generalize dependent q1.
    generalize dependent q.
    generalize dependent q2.
    induction H3; intros.
    * apply (IHExpand q2 q1).
      assert (StrSet.Subset q q2).
      apply (expand_one_step_is_monotonic ctx q3 _ q1 _); auto.
      SDecide.fsetdec.
      apply ex_top; auto.
    * apply (IHExpand q0 q3) with (q1 := q4); auto.
      apply expand_one_step_is_increasing in H.
      SDecide.fsetdec.
Qed.

Lemma expand_is_saturated: forall G q q' x qual,
  Expand G q q' -> StrSet.In x q' -> retrieve G x = Some (Sym true false qual)
  -> StrSet.Subset qual q'.
Proof.
  intros.
  generalize dependent x.
  generalize dependent qual.
  induction H; intros.
  2: apply IHExpand with (x := x); auto.
  unfold expand_one_step in H.
  destruct (is_well_formed ctx && StrSet.subset q1 (ddomain ctx)) eqn:?; try discriminate.
  inversion H; clear H.
  assert (StrSet.In x q1).
  SDecide.fsetdec.
  revert H; clear H4.
  apply SProps.fold_rec; intros.
  exfalso; SDecide.fsetdec.
  apply SProps.Add_Equal in H4.
  destruct (x =? x0)%string eqn:?.
  - apply String.eqb_eq in Heqb0; subst.
    rewrite H2.
    SDecide.fsetdec.
  - apply String.eqb_neq in Heqb0.
    assert (StrSet.In x s'). SDecide.fsetdec.
    apply H5 in H7.
    destruct (retrieve ctx x0) eqn:?; try SDecide.fsetdec.
    destruct s eqn:?.
    destruct isFun eqn:?; try SDecide.fsetdec.
    destruct isFresh eqn:?; try SDecide.fsetdec.
Qed.

Inductive Bounded: context -> string -> qualset -> Prop :=
| bd_base: forall ctx x q, wf_context ctx -> StrSet.Subset q (ddomain ctx)
  -> StrSet.In x q -> Bounded ctx x q
| bd_ind: forall ctx x q' q, wf_context ctx -> StrSet.Subset q (ddomain ctx)
  -> retrieve ctx x = Some (Sym false false q')
  -> StrSet.For_all (fun x' => Bounded ctx x' q) q'
  -> Bounded ctx x q .

Lemma bounded_is_sound: forall G x q, Bounded G x q -> subQual G (StrSet.singleton x) q.
Proof.
  intros.
  induction H.
  - apply q_sub; auto.
    SDecide.fsetdec.
  - apply q_trans with (q := q').
    apply q_var; auto.
    apply q_cong_fold; auto.
Qed.

Lemma bounded_is_transitive: forall G x q1 q2, StrSet.Subset q2 (ddomain G) ->
  Bounded G x q1 -> StrSet.For_all (fun x => Bounded G x q2) q1 -> Bounded G x q2.
Proof.
  intros.
  induction H0; auto.
  apply bd_ind with (q' := q'); auto.
  unfold StrSet.For_all in *; intros.
  apply H5; auto.
Qed.

Inductive algorithmic: context -> qualset -> qualset -> Prop :=
| algo: forall G p q q', Expand G q q' -> StrSet.For_all (fun x => Bounded G x q') p
  -> algorithmic G p q.

Theorem algorithmic_is_sound: forall G p q,
  algorithmic G p q -> subQual G p q.
Proof.
  intros.
  destruct H.
  apply expand_is_sound in H as ?.
  unfold StrSet.For_all in H0.
  apply subQual_is_well_formed in H1 as ?.
  destruct H2.
  destruct H3.
  apply q_trans with (q := q'); auto.
  apply q_cong_fold; auto.
  intros.
  apply H0 in H5.
  apply bounded_is_sound.
  assumption.
Qed.
Print Assumptions algorithmic_is_sound.

Lemma algorithmic_is_complete: forall G p q,
  subQual G p q -> algorithmic G p q.
Proof.
  intros.
  induction H.
  3,4: apply retrieve_is_well_formed in H0 as H1; auto; destruct H1.
  1,3: assert (exists q', Expand G q q') as Hexterm.
  6: assert (exists q', Expand G (StrSet.singleton f) q') as Hexterm.
  1,3,6: apply expand_is_terminating; auto; SDecide.fsetdec.
  1,2,4: destruct Hexterm as [q' Hex];
         apply expand_is_well_formed in Hex as ?;
         apply expand_is_increasing in Hex as ?;
         apply algo with (q' := q'); auto;
         unfold StrSet.For_all; intros.
  - apply bd_base; auto.
  - apply SFacts.singleton_iff in H5; subst.
    apply bd_ind with (q' := q); auto.
    unfold StrSet.For_all; intros.
    apply bd_base; auto.
  - apply bd_base; auto.
    apply is_well_formed_iff in H as ?.
    assert (StrSet.subset (StrSet.singleton f) (ddomain G) = true).
    { apply SFacts.subset_iff. SDecide.fsetdec. }
    inversion Hex; subst; unfold expand_one_step in *.
    all: rewrite H6 in H8; rewrite H7 in H8; simpl in *.
    all: rewrite StrSet.fold_spec in H8; simpl in *; rewrite H0 in H8.
    all: inversion H8.
    2: apply expand_is_increasing in H10.
    all: SDecide.fsetdec.
  - destruct IHsubQual1; destruct IHsubQual2; subst.
    apply algo with (q' := q'0); auto.
    apply expand_is_well_formed in H3 as ?.
    unfold StrSet.For_all in *; intros.
    apply bounded_is_transitive with (q1 := q'); auto.
    unfold StrSet.For_all in *; intros.
    clear H H0 H2 H6 p x.
    generalize dependent x0.
    induction H1; intros.
    + apply expand_one_step_is_increasing in H.
      apply H4.
      SDecide.fsetdec.
    + apply IHExpand; auto.
      clear q2 H1 IHExpand H7.
      unfold expand_one_step in *.
      destruct (is_well_formed ctx) eqn:?;
      destruct (StrSet.subset q1 (ddomain ctx)) eqn:?;
      simpl in *; try discriminate.
      inversion H; clear H H2.
      apply SProps.fold_rec; intros.
      { exfalso. SDecide.fsetdec. }
      apply SProps.Add_Equal in H2.
      destruct (x =? x1)%string eqn:?.
      { apply String.eqb_eq in Heqb1; subst. apply H4. exact H. }
      apply String.eqb_neq in Heqb1.
      destruct (retrieve ctx x) eqn:?.
      2: apply H6; SDecide.fsetdec.
      destruct s eqn:?.
      destruct isFun eqn:?.
      2: apply H6; SDecide.fsetdec.
      destruct isFresh eqn:?.
      apply H6; SDecide.fsetdec.
      destruct (StrSet.mem x1 qual) eqn:?.
      2: apply SFacts.not_mem_iff in Heqb4; apply H6; SDecide.fsetdec.
      apply SFacts.mem_iff in Heqb4.
      clear - H3 H4 H Heqo Heqb4.
      apply H4 in H.
      inversion H; subst.
      2: rewrite H2 in Heqo; discriminate.
      apply bd_base; auto.
      clear - H3 Heqo H2 Heqb4.
      apply expand_is_saturated with (x := x) (qual := qual) in H3; auto.
  - apply subQual_is_well_formed in H as ?.
    destruct H1.
    assert (exists x, Expand G (StrSet.union p q') x).
    apply expand_is_terminating; auto; SDecide.fsetdec.
    destruct H3.
    apply algo with (q' := x); auto.
    inversion IHsubQual; subst.
    unfold StrSet.For_all in *; intros.
    apply expand_is_increasing in H3 as ?.
    assert (StrSet.Subset q'0 x).
    apply (expand_is_monotonic G q' _ (StrSet.union p q') _); auto; SDecide.fsetdec.
    apply expand_is_well_formed in H3 as ?.
    destruct (StrSet.mem x0 q) eqn:?.
    * apply SFacts.mem_iff in Heqb.
      apply H5 in Heqb.
      clear - Heqb H8 H9.
      induction Heqb.
      + apply bd_base; auto.
      + apply bd_ind with (q' := q'); auto.
        unfold StrSet.For_all in *; intros.
        apply H3; auto.
    * apply SFacts.not_mem_iff in Heqb.
      apply bd_base; auto.
      SDecide.fsetdec.
Qed.
Print Assumptions algorithmic_is_complete.