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

Fixpoint expand (ctx: context) (qual: qualset) : qualset :=
  match ctx with
  | (f, Sym fn fr q)::ctx' => expand ctx' (
      if fn && negb fr && StrSet.mem f qual then
        StrSet.union q qual
      else qual)
  | Nil => qual
  end.

Inductive list_suffix {A: Type}: list A -> list A -> Prop :=
| suff_refl: forall a, list_suffix a a
| suff_ind: forall a l1 l2, list_suffix (a::l1) l2 -> list_suffix l1 l2.

Lemma retrieve_works_on_suffix: forall G G',
  wf_context G -> list_suffix G' G -> wf_context G'
  /\ (forall x a, retrieve G' x = Some a -> retrieve G x = Some a).
Proof.
  intros.
  induction H0; auto.
  apply IHlist_suffix in H; clear IHlist_suffix.
  destruct H.
  inversion H; subst.
  split; try assumption; intros.
  apply H1; clear H1; simpl.
  destruct (x =? n)%string eqn:?.
  apply String.eqb_eq in Heqb; subst.
  - (* true case *)
  destruct a.
  apply retrieve_is_well_formed in H2; auto.
  destruct H2.
  contradiction.
  - (* false case *)
  assumption.
Qed.

Lemma expand_is_sound: forall G q,
  wf_context G -> StrSet.Subset q (ddomain G) -> subQual G (expand G q) q.
Proof.
  intros.
  (* proper induction preparation *)
  pose G as G'.
  replace G with G' at 2.
  2: reflexivity.
  pose q as q'.
  replace q with q' at 1.
  2: reflexivity.
  assert (list_suffix G' G).
  constructor.
  assert (subQual G q' q).
  apply q_sub; (assumption || SDecide.fsetdec).
  generalize dependent q'.
  induction G'; intros.
  - (* base case *)
    simpl in *.
    assumption.
  - (* inductive case *)
    destruct a; destruct s0; simpl in *.
    apply IHG'.
    apply suff_ind in H1; assumption.
    clear IHG'.
    (* obtain retrieve witness *)
    apply retrieve_works_on_suffix in H1; auto.
    destruct H1.
    assert (retrieve ((s, Sym isFun isFresh qual) :: G') s = Some (Sym isFun isFresh qual)). {
      simpl.
      assert ((s =? s)%string = true).
      apply String.eqb_eq.
      reflexivity.
      rewrite H4.
      reflexivity.
    }
    apply H3 in H4; clear H3.
    (* destruct if-then-else *)
    destruct (isFun && negb isFresh && StrSet.mem s q') eqn:?; try assumption.
    apply andb_true_iff in Heqb; destruct Heqb.
    apply andb_true_iff in H3; destruct H3.
    apply negb_true_iff in H6; subst.
    apply StrSet.mem_spec in H5.
    (* obtain well-formedness witness *)
    apply retrieve_is_well_formed in H4 as ?; try assumption.
    apply subQual_is_well_formed in H2 as ?.
    destruct H3; destruct H6; destruct H8; clear H6 H9.
    (* qualifier reasoning *)
    apply q_trans with (q := q'); try assumption.
    apply q_cong'.
    apply q_trans with (q := StrSet.singleton s).
    apply q_trans with (q := StrSet.add s qual).
    * apply q_sub.
      assumption.
      all: SDecide.fsetdec.
    * apply q_self; assumption.
    * apply q_sub.
      assumption.
      all: SDecide.fsetdec.
    * apply q_sub.
      assumption.
      all: SDecide.fsetdec.
Qed. 

Lemma expand_is_increasing: forall G q,
  StrSet.Subset q (expand G q).
Proof.
  intros.
  revert q.
  induction G; intros; simpl.
  SDecide.fsetdec.
  destruct a; destruct s0; destruct (isFun && negb isFresh && StrSet.mem s q) eqn:?.
  assert (StrSet.Subset (StrSet.union qual q) (expand G (StrSet.union qual q))).
  apply IHG.
  SDecide.fsetdec.
  apply IHG.
Qed.

Lemma expand_is_saturated: forall G q q0 f,
  StrSet.In f q0 -> retrieve G f = Some (Sym true false q) -> StrSet.Subset (StrSet.add f q) (expand G q0).
Proof.
  intros.
  generalize dependent q0.
  induction G; intros; simpl in *.
  discriminate.
  destruct a; destruct s0.
  destruct ((f =? s)%string) eqn:?.
  - (* true case *)
    apply String.eqb_eq in Heqb; subst.
    assert (StrSet.mem s q0 = true).
    { apply StrSet.mem_spec. SDecide.fsetdec. }
    inversion H0; subst.
    rewrite H1.
    simpl.
    assert (StrSet.Subset (StrSet.union q q0) (expand G (StrSet.union q q0))).
    apply expand_is_increasing.
    SDecide.fsetdec.
  - (* false case *)
    apply IHG; try assumption.
    destruct (isFun && negb isFresh && StrSet.mem s q0) eqn:?.
    all: SDecide.fsetdec.
Qed.

Lemma expand_is_monotonic: forall G q1 q2,
  StrSet.Subset q1 q2 -> StrSet.Subset (expand G q1) (expand G q2).
Proof.
  intros.
  revert H; revert q1; revert q2.
  induction G; intros; simpl in *.
  assumption.
  destruct a; destruct s0.
  apply IHG.
  destruct (isFun && negb isFresh); simpl.
  2: assumption.
  destruct (StrSet.mem s q1) eqn:?; destruct (StrSet.mem s q2) eqn:?.
  1,3,4: SDecide.fsetdec.
  apply StrSet.mem_spec in Heqb.
  apply not_true_iff_false in Heqb0.
  exfalso.
  apply Heqb0; clear Heqb0.
  apply StrSet.mem_spec.
  SDecide.fsetdec.
Qed.

Fixpoint bounded (ctx: context) (x: string) (qual: qualset) : bool :=
  if StrSet.mem x qual then true else
    match ctx with
    | (x', Sym fn fr q)::ctx' =>
        if (x =? x')%string && negb fn && negb fr then
          StrSet.for_all (fun x => bounded ctx' x qual) q
        else bounded ctx' x qual
    | Nil => false
    end.

Lemma bounded_is_sound: forall G x q,
  wf_context G -> StrSet.Subset q (ddomain G) -> bounded G x q = true -> subQual G (StrSet.singleton x) q.
Proof.
  intros.
  pose G as G'.
  replace G with G' in H1.
  2: reflexivity.
  assert (list_suffix G' G).
  constructor.
  generalize dependent x.
  induction G'; intros; simpl in *.
  - (* base case *)
    destruct (StrSet.mem x q) eqn:?.
    2: discriminate.
    apply StrSet.mem_spec in Heqb.
    apply q_sub; try assumption.
    SDecide.fsetdec.
  - (* inductive case *)
    destruct a; destruct s0.
    apply suff_ind in H2 as H3.
    intuition; clear H3.
    (* case analysis: mem x q *)
    destruct (StrSet.mem x q) eqn:Heqb.
    { apply StrSet.mem_spec in Heqb. apply q_sub. all: assumption || SDecide.fsetdec. }
    clear Heqb.
    (* destruct if-then-else *)
    destruct ((x =? s)%string && negb isFun && negb isFresh) eqn:Heqb0.
    2: intuition. (* trivial false case *)
    apply andb_true_iff in Heqb0; destruct Heqb0 as [ HB12 HB3 ].
    apply andb_true_iff in HB12; destruct HB12 as [ HB1 HB2 ].
    apply String.eqb_eq in HB1.
    apply negb_true_iff in HB2.
    apply negb_true_iff in HB3.
    subst.
    (* obtain retrieve witness *)
    apply retrieve_works_on_suffix in H2; try assumption.
    destruct H2 as [H2 IHretrieve].
    assert (retrieve ((s, Sym false false qual) :: G') s = Some (Sym false false qual)) as Hretrieve. {
      simpl.
      assert ((s =? s)%string = true) as Heqb.
      apply String.eqb_eq.
      reflexivity.
      rewrite Heqb.
      reflexivity.
    }
    apply IHretrieve in Hretrieve; clear IHretrieve.
    (* unfold for_all stuff *)
    apply StrSet.for_all_spec in H1.
    2: { unfold Proper. unfold respectful. intros. subst. reflexivity. }
    unfold StrSet.For_all in H1.
    (* qualifier reasoning *)
    apply q_trans with (q := qual).
    + (* q_var *)
      apply q_var.
      all: assumption.
    + (* induction *)
      apply q_cong_fold; try assumption; intros.
      intuition.
Qed.

Lemma bounded_respects_subset: forall G x q1 q2,
  StrSet.Subset q1 q2 -> bounded G x q1 = true -> bounded G x q2 = true.
Proof.
  intros.
  generalize dependent x.
  induction G; intros; simpl in *.
  - (* base case *)
    destruct (StrSet.mem x q1) eqn:Heqb.
    2: discriminate.
    apply StrSet.mem_spec in Heqb.
    replace (StrSet.mem x q2) with true.
    reflexivity.
    symmetry.
    apply StrSet.mem_spec.
    SDecide.fsetdec.
  - (* inductive case *)
    destruct a; destruct s0.
    destruct (StrSet.mem x q1) eqn:Heqb1; destruct (StrSet.mem x q2) eqn:Heqb2.
    1,3: reflexivity.
    (* ill case: x in q1 not in q2 *)
    apply StrSet.mem_spec in Heqb1.
    assert (~ StrSet.In x q2) as Heqb2'.
    { apply not_true_iff_false in Heqb2. intro. apply Heqb2. apply StrSet.mem_spec. assumption. }
    clear Heqb2; exfalso.
    SDecide.fsetdec.
    (* truly inductive case *)
    destruct ((x =? s)%string && negb isFun && negb isFresh); try intuition.
    apply StrSet.for_all_spec.
    { unfold Proper. unfold respectful. intros. subst. reflexivity. }
    apply StrSet.for_all_spec in H0.
    2: { unfold Proper. unfold respectful. intros. subst. reflexivity. }
    unfold StrSet.For_all in *; intros.
    intuition.
Qed.

Lemma bounded_can_be_simple: forall G x q,
  StrSet.In x q -> bounded G x q = true.
Admitted.

Definition algorithmic (ctx: context) (q1 q2: qualset) : bool :=
  if is_well_formed ctx && StrSet.subset q1 (ddomain ctx) && StrSet.subset q2 (ddomain ctx) then
    StrSet.for_all (fun x => bounded ctx x (expand ctx q2)) q1
  else false.

Theorem algorithmic_is_sound: forall ctx q1 q2,
  algorithmic ctx q1 q2 = true -> subQual ctx q1 q2.
Proof.
  unfold algorithmic.
  intros.
  destruct (is_well_formed ctx && StrSet.subset q1 (ddomain ctx) && StrSet.subset q2 (ddomain ctx)) eqn:?.
  2: discriminate.
  (* destruct boolean condition *)
  apply andb_true_iff in Heqb; destruct Heqb.
  apply andb_true_iff in H0; destruct H0.
  apply is_well_formed_iff in H0.
  apply StrSet.subset_spec in H1.
  apply StrSet.subset_spec in H2.
  (* unfold for_all stuff *)
  apply StrSet.for_all_spec in H.
  2: { unfold Proper. unfold respectful. intros. subst. reflexivity. }
  unfold StrSet.For_all in *.
  (* qualifier reasoning *)
  apply q_cong_fold; try assumption; intros.
  apply H in H3 as ?; clear H.
  apply expand_is_sound in H1; try assumption.
  apply q_trans with (q := expand ctx q2); try assumption.
  apply subQual_is_well_formed in H1 as ?.
  destruct H; destruct H5.
  apply bounded_is_sound; assumption.
Qed.

Theorem algorithmic_is_complete: forall ctx q1 q2,
  subQual ctx q1 q2 -> algorithmic ctx q1 q2 = true.
Proof.
  intros.
  apply subQual_is_well_formed in H as ?.
  destruct H0; destruct H1.
  unfold algorithmic.
  apply is_well_formed_iff in H0 as ?.
  rewrite H3; clear H3.
  apply StrSet.subset_spec in H1 as ?.
  rewrite H3; clear H3.
  apply StrSet.subset_spec in H2 as ?.
  rewrite H3; clear H3.
  simpl.
  apply StrSet.for_all_spec.
  { unfold Proper. unfold respectful. intros. subst. reflexivity. }
  unfold StrSet.For_all.
  induction H; intros.
  - (* q_sub *)
    apply bounded_respects_subset with (q1 := q).
    apply expand_is_increasing.
    apply bounded_can_be_simple.
    SDecide.fsetdec.
  - (* q_trans *)
    admit.
  - (* q_var *)
    apply bounded_respects_subset with (q1 := q).
    apply expand_is_increasing.
    assert (x0 = x).
    SDecide.fsetdec.
    subst; clear H4.
    induction G; simpl in *.
    discriminate.
    destruct a; destruct s0.
    destruct (StrSet.mem x q) eqn:?.
    reflexivity.
    assert (~ StrSet.In x q).
    { apply not_true_iff_false in Heqb. intro. apply Heqb. apply StrSet.mem_spec. assumption. }
    clear Heqb.
    destruct ((x =? s)%string && negb isFun && negb isFresh) eqn:?.
    * (* true case *)
    apply StrSet.for_all_spec.
    { unfold Proper. unfold respectful. intros. subst. reflexivity. }
    unfold StrSet.For_all; intros.
    apply andb_true_iff in Heqb; destruct Heqb.
    apply andb_true_iff in H6; destruct H6.
    rewrite H6 in H3.
    inversion H3; subst.
    destruct G; simpl in *.
    all: replace (StrSet.mem x0 q) with true; try reflexivity.
    all: symmetry; apply StrSet.mem_spec; SDecide.fsetdec.
    * (* false case *)
    destruct ((x =? s)%string) eqn:?.
    + inversion H3; subst.
      discriminate.
    + inversion H; subst; clear H.
      apply retrieve_is_well_formed in H3 as ?; try assumption.
      apply IHG; try assumption; clear IHG Heqb.
      all: SDecide.fsetdec.
  - (* q_self *)
    apply bounded_respects_subset with (q1 := (StrSet.add f q)).
    apply expand_is_saturated.
    SDecide.fsetdec.
    assumption.
    apply bounded_can_be_simple.
    assumption.
  - (* q_cong *)
    destruct (StrSet.mem x q) eqn:?.
    * (* q case *)
      apply StrSet.mem_spec in Heqb.
      apply IHsubQual in Heqb; try assumption.
      2,3: SDecide.fsetdec.
      apply bounded_respects_subset with (q1 := (expand G q')); try assumption.
      apply expand_is_monotonic.
      SDecide.fsetdec.
    * (* p case *)
      assert (~ StrSet.In x q).
      { apply not_true_iff_false in Heqb. intro. apply Heqb. apply StrSet.mem_spec. assumption. }
      assert (StrSet.In x p).
      SDecide.fsetdec.
      apply bounded_respects_subset with (q1 := StrSet.union p q').
      apply expand_is_increasing.
      apply bounded_can_be_simple.
      SDecide.fsetdec.
Admitted.

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

Definition expand_one_step' (ctx: context) (qual: qualset) : qualset :=
  StrSet.fold (fun f p =>
    match retrieve ctx f with
    | Some (Sym true false q) => StrSet.union q p
    | _ => p
    end) qual qual.

Lemma expand_one_step'_is_bounded_increasing: forall G q1 q2,
  wf_context G -> q2 = expand_one_step' G q1 ->
  StrSet.Subset q2 (StrSet.union (ddomain G) q1) /\ StrSet.Subset q1 q2.
Proof.
  intros.
  revert H0.
  remember q1.
  rewrite Heqq at 1.
  unfold expand_one_step'.
  generalize dependent q2.
  apply SProps.fold_rec; subst; intros.
  - split; SDecide.fsetdec.
  - specialize (H3 a).
    assert (a = a). reflexivity.
    apply H3 in H5; clear H3.
    destruct (retrieve G x) eqn:?.
    * destruct s.
      apply retrieve_is_well_formed in Heqo; try assumption.
      destruct isFun; destruct isFresh; split; subst.
      all: SDecide.fsetdec.
    * split; SDecide.fsetdec.
Qed.

Inductive R (ctx: context) : qualset -> qualset -> Prop :=
  | RExpandOneStep : forall qual, ~ StrSet.Subset (expand_one_step' ctx qual) qual
      -> R ctx (expand_one_step' ctx qual) qual.

Lemma not_subset_iff: forall a b, StrSet.subset a b = false -> ~ StrSet.Subset a b.
Proof.
  unfold not; intros.
  apply not_true_iff_false in H.
  apply H.
  apply SFacts.subset_iff.
  assumption.
Qed.

Definition expand' (ctx: context) (qual: qualset)
    (rec: forall q, R ctx q qual -> qualset): qualset :=
  let b0 := StrSet.subset (expand_one_step' ctx qual) qual in
    match b0 as b1 return b0 = b1 -> qualset with
    | false => fun H => rec _ (RExpandOneStep _ _ (not_subset_iff _ _ H))
    | true => fun _ => qual
    end eq_refl.

Definition measure (ctx: context) (qual: qualset) :=
  StrSet.cardinal (StrSet.diff (ddomain ctx) qual).

Lemma wfR' : forall n ctx qual, wf_context ctx -> measure ctx qual <= n -> Acc (R ctx) qual.
Proof.
  unfold measure.
  intros.
  generalize dependent qual.
  induction n; intros.
  - inversion H0; subst; clear H0.
    apply SProps.cardinal_Empty in H2.
    constructor; intros.
    inversion H0; subst; clear H0.
    exfalso; apply H1; clear H1.
    remember (expand_one_step' ctx qual).
    apply expand_one_step'_is_bounded_increasing in Heqq; try assumption.
    SDecide.fsetdec.
  - inversion H0; subst; clear H0.
    * constructor; intros.
      inversion H0; subst; clear H0.
      apply IHn; clear IHn.
      apply Lt.lt_n_Sm_le.
      rewrite <- H2; clear H2.
      remember (expand_one_step' ctx qual).
      apply expand_one_step'_is_bounded_increasing in Heqq; try assumption.
      remember (ddomain ctx).
      assert (~ StrSet.Subset (StrSet.diff q0 qual) (StrSet.diff q0 q)). {
        intro; apply H1; clear H1.
        SDecide.fsetdec.
      }
      apply not_all_ex_not in H0.
      destruct H0.
      apply SProps.subset_cardinal_lt with (x := x).
      all: SDecide.fsetdec.
    * apply IHn.
      assumption.
Qed.

Theorem wfR : forall ctx, wf_context ctx -> well_founded (R ctx).
Proof.
  unfold well_founded; intros.
  apply (wfR' (StrSet.cardinal (ddomain ctx)) _ _ H).
  unfold measure.
  apply SProps.subset_cardinal.
  SDecide.fsetdec.
Qed.

Lemma wf_convert: forall ctx,
  is_well_formed ctx = true -> wf_context ctx.
Proof.
  intros. apply is_well_formed_iff. assumption.
Qed.

Definition expand (ctx: context) :=
  let b0 := is_well_formed ctx in
    match b0 with
    | false => fun _ => fun q => q
    | true => fun Heq => Fix (wfR ctx (wf_convert _ Heq)) (fun _ => qualset) (expand' ctx)
    end eq_refl.

Lemma unfold_playground: forall ctx q1 q2,
  expand ctx q1 = q2.
Proof.
  intros.
  unfold expand.
  destruct (is_well_formed ctx) eqn:?.
  (* destruct (is_well_formed ctx) eqn:?.
  unfold Fix in H.
  rewrite Fix_eq in H. *)
Admitted.

(* Providing an unfolding requires extensionality.
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
[ simpl; repeat fold expand | apply expand_extensional ]. *)

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