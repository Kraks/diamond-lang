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

Lemma expand_is_inbound: forall G q0,
  wf_context G -> StrSet.Subset (expand G q0) (StrSet.union (ddomain G) q0).
Proof.
  intros.
  generalize dependent q0.
  induction G; intros.
  simpl in *.
  SDecide.fsetdec.
  destruct a; destruct s0.
  inversion H; subst.
  intuition.
  simpl in *.
  remember (if isFun && negb isFresh && StrSet.mem s q0 then StrSet.union qual q0 else q0) as q0'.
  assert (StrSet.Subset (expand G q0') (StrSet.union (ddomain G) q0')).
  apply H0.
  assert (StrSet.Subset q0' (StrSet.union qual q0)).
  subst. destruct (isFun && negb isFresh && StrSet.mem s q0); SDecide.fsetdec.
  assert (StrSet.Subset qual (StrSet.add s (ddomain G))).
  destruct isFun; SDecide.fsetdec.
  SDecide.fsetdec.
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

Lemma expand_is_saturated2: forall G x,
  wf_context G -> StrSet.Equal (expand G (expand G x)) (expand G x).
Proof.
  intros.
  unfold StrSet.Equal.
  split.
  2: apply expand_is_increasing.
  generalize dependent x.
  induction G; intros.
  simpl.
  SDecide.fsetdec.
  destruct a0; destruct s0.
  inversion H; subst.
  intuition.
  simpl in *.
  destruct (isFun && negb isFresh) eqn:?; simpl in *.
  2: intuition.
  destruct (StrSet.mem s x) eqn:?; simpl in *.
  replace (StrSet.mem s (expand G (StrSet.union qual x))) with true in H0.
  assert (StrSet.Subset (StrSet.union qual x) (expand G (StrSet.union qual x))).
  apply expand_is_increasing.
  assert (StrSet.Subset (StrSet.union qual (expand G (StrSet.union qual x))) (expand G (StrSet.union qual x))).
  SDecide.fsetdec.
  apply expand_is_monotonic with (G := G) in H3.
  apply H1.
  SDecide.fsetdec.
  symmetry.
  apply StrSet.mem_spec.
  apply StrSet.mem_spec in Heqb0.
  apply expand_is_increasing.
  SDecide.fsetdec.
  replace (StrSet.mem s (expand G x)) with false in H0.
  intuition.
  symmetry.
  apply SFacts.not_mem_iff in Heqb0.
  apply SFacts.not_mem_iff.
  intro.
  apply expand_is_inbound in H2; try assumption.
  SDecide.fsetdec.
Qed.

Lemma expand_works_on_suffix: forall G G' q,
  list_suffix G' G -> StrSet.Subset (expand G' q) (expand G q).
Proof.
  intros.
  induction H.
  SDecide.fsetdec.
  destruct a; destruct s0; simpl in *.
  destruct (isFun && negb isFresh && StrSet.mem s q).
  2: SDecide.fsetdec.
  assert (StrSet.Subset (expand l1 q) (expand l1 (StrSet.union qual q))).
  apply expand_is_monotonic.
  all: SDecide.fsetdec.
Qed.

Lemma expand_respects_union: forall G q1 q2,
  StrSet.Equal (expand G (StrSet.union q1 q2)) (StrSet.union (expand G q1) (expand G q2)).
Proof.
  intros.
  generalize dependent q1.
  generalize dependent q2.
  induction G; intros.
  + simpl.
    SDecide.fsetdec.
  + destruct a; destruct s0; simpl.
    destruct (isFun && negb isFresh); simpl.
    2: apply IHG.
    destruct (StrSet.mem s q1) eqn:?; destruct (StrSet.mem s q2) eqn:?; simpl.
    * assert (StrSet.mem s (StrSet.union q1 q2) = true).
      apply StrSet.mem_spec in Heqb.
      apply StrSet.mem_spec.
      SDecide.fsetdec.
      rewrite H.
      assert (StrSet.Equal (expand G (StrSet.union qual (StrSet.union q1 q2))) (expand G (StrSet.union (StrSet.union qual q1) (StrSet.union qual q2)))).
      unfold StrSet.Equal; split.
      1,2: apply expand_is_monotonic; SDecide.fsetdec.
      assert (StrSet.Equal (expand G (StrSet.union (StrSet.union qual q1) (StrSet.union qual q2))) (StrSet.union (expand G (StrSet.union qual q1)) (expand G (StrSet.union qual q2)))).
      apply IHG.
      SDecide.fsetdec.
    * assert (StrSet.mem s (StrSet.union q1 q2) = true).
      apply StrSet.mem_spec in Heqb.
      apply StrSet.mem_spec.
      SDecide.fsetdec.
      rewrite H.
      assert (StrSet.Equal (expand G (StrSet.union qual (StrSet.union q1 q2))) (expand G (StrSet.union (StrSet.union qual q1) q2))).
      unfold StrSet.Equal; split.
      1,2: apply expand_is_monotonic; SDecide.fsetdec.
      assert (StrSet.Equal (expand G (StrSet.union (StrSet.union qual q1) q2)) (StrSet.union (expand G (StrSet.union qual q1)) (expand G q2))).
      apply IHG.
      SDecide.fsetdec.
    * assert (StrSet.mem s (StrSet.union q1 q2) = true).
      apply StrSet.mem_spec in Heqb0.
      apply StrSet.mem_spec.
      SDecide.fsetdec.
      rewrite H.
      assert (StrSet.Equal (expand G (StrSet.union qual (StrSet.union q1 q2))) (expand G (StrSet.union q1 (StrSet.union qual q2)))).
      unfold StrSet.Equal; split.
      1,2: apply expand_is_monotonic; SDecide.fsetdec.
      assert (StrSet.Equal (expand G (StrSet.union q1 (StrSet.union qual q2))) (StrSet.union (expand G q1) (expand G (StrSet.union qual q2)))).
      apply IHG.
      SDecide.fsetdec.
    * assert (StrSet.mem s (StrSet.union q1 q2) = false).
      apply SFacts.not_mem_iff in Heqb.
      apply SFacts.not_mem_iff in Heqb0.
      apply SFacts.not_mem_iff.
      SDecide.fsetdec.
      rewrite H.
      apply IHG.
Qed.

Lemma expand_on_function: forall G f q,
  wf_context G -> retrieve G f = Some (Sym true false q)
  -> StrSet.Subset (expand G q) (expand G (StrSet.singleton f)).
Proof.
  intros.
  induction G.
  simpl in *.
  discriminate.
  destruct a; destruct s0.
  inversion H; subst.
  simpl in *.
  destruct (f =? s)%string eqn:?.
  2: {
    intuition.
    apply retrieve_is_well_formed in H0 as ?; try assumption.
    assert (f <> s).
    intro.
    subst.
    rewrite String.eqb_refl in Heqb.
    discriminate.
    replace (StrSet.mem s (StrSet.singleton f)) with false.
    2: {
      symmetry.
      apply SFacts.not_mem_iff.
      SDecide.fsetdec.
    }
    replace (StrSet.mem s q) with false.
    2: {
      symmetry.
      apply SFacts.not_mem_iff.
      SDecide.fsetdec.
    }
    replace (isFun && negb isFresh && false) with false.
    assumption.
    rewrite andb_false_r.
    reflexivity.
  }
  apply String.eqb_eq in Heqb; subst.
  inversion H0; subst; simpl in *.
  replace (StrSet.mem s (StrSet.singleton s)) with true.
  2: {
    symmetry.
    apply StrSet.mem_spec.
    SDecide.fsetdec.
  }
  apply expand_is_monotonic.
  destruct (StrSet.mem s q); SDecide.fsetdec.
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
Proof.
  intros.
  apply StrSet.mem_spec in H.
  destruct G; simpl.
  all: rewrite H; reflexivity.
Qed.

Lemma bounded_is_transitive: forall G x q r,
  bounded G x q = true -> StrSet.For_all (fun x => bounded G x r = true) q -> bounded G x r = true.
Proof.
  intros.
  unfold StrSet.For_all in H0.
Admitted.

Lemma bounded_on_function: forall G f p q,
  wf_context G -> bounded G f q = true -> retrieve G f = Some (Sym true false p)
  -> StrSet.In f q.
Proof.
  intros.
  induction G.
  simpl in *.
  discriminate.
  destruct a; destruct s0.
  inversion H; subst.
  simpl in *.
  destruct (StrSet.mem f q) eqn:?.
  apply StrSet.mem_spec in Heqb.
  assumption.
  destruct (f =? s)%string eqn:?.
  2: {
    replace (false && negb isFun) with false in H0.
    replace (false && negb isFresh) with false in H0.
    intuition.
    all: rewrite andb_false_l; reflexivity.
  }
  apply String.eqb_eq in Heqb0; subst.
  inversion H1; subst.
  simpl in *.
  clear IHG H1 H H9 p.
  exfalso.
  apply not_true_iff_false in Heqb.
  apply Heqb; clear Heqb.
  apply StrSet.mem_spec.
  induction G.
  simpl in *.
  destruct (StrSet.mem s q) eqn:?.
  apply StrSet.mem_spec in Heqb.
  assumption.
  discriminate.
  destruct a; destruct s1.
  inversion H5; subst.
  simpl in *.
  destruct (StrSet.mem s q) eqn:?.
  apply StrSet.mem_spec in Heqb.
  assumption.
  destruct (s =? s0)%string eqn:?.
  apply String.eqb_eq in Heqb0; subst.
  exfalso.
  SDecide.fsetdec.
  replace (false && negb isFun) with false in H0.
  replace (false && negb isFresh) with false in H0.
  intuition.
  all: rewrite andb_false_l; reflexivity.
Qed.

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
    apply subQual_is_well_formed in H as ?; clear H0 H1.
    intuition.
    apply bounded_is_transitive with (q := expand G q).
    { apply H1. assumption. }
    unfold StrSet.For_all; intros; clear H H4 H5 H1 x p.
    pose G as G'.
    assert (list_suffix G' G).
    constructor.
    replace G with G' in H8.
    2: reflexivity.
    generalize dependent x0.
    induction G'; intros.
    * simpl in *. intuition.
    * destruct a; destruct s0.
      simpl in *.
      apply suff_ind in H as ?.
      destruct (isFun && negb isFresh && StrSet.mem s q) eqn:?.
      + (* true inductive case *)
      apply expand_respects_union in H8.
      apply StrSet.union_spec in H8.
      destruct H8.
      2: apply IHG'; assumption.
      apply andb_true_iff in Heqb; destruct Heqb as [ Heqb01 Heqb2 ].
      apply andb_true_iff in Heqb01; destruct Heqb01 as [ Heqb0 Heqb1 ].
      apply negb_true_iff in Heqb1; subst.
      apply StrSet.mem_spec in Heqb2.
      apply H7 in Heqb2.
      apply bounded_can_be_simple.
      apply expand_works_on_suffix with (G := G) in H4; try assumption.
      apply retrieve_works_on_suffix in H; try assumption; destruct H.
      specialize H5 with (x := s) (a := Sym true false qual).
      assert (retrieve ((s, Sym true false qual) :: G') s = Some (Sym true false qual)).
      { simpl. replace (s =? s)%string with true. reflexivity. symmetry. apply String.eqb_eq. reflexivity. }
      apply H5 in H8.
      generalize dependent x0.
      apply expand_on_function in H8 as ?; try assumption.
      apply bounded_on_function with (p := qual) in Heqb2; try assumption.
      assert (StrSet.Subset (expand G (StrSet.singleton s)) (expand G (expand G r))).
      apply expand_is_monotonic.
      SDecide.fsetdec.
      assert (StrSet.Equal (expand G (expand G r)) (expand G r)).
      apply expand_is_saturated2; try assumption.
      SDecide.fsetdec.
      + (* trivial case *)
      intuition.
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
Qed.
