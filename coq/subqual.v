Require Import Coq.Strings.String.
Require Import Coq.Init.Nat.
Require Import Coq.Lists.List.
Require Import Coq.MSets.MSetList.
Require Import Coq.MSets.MSetFacts.
Require Import Coq.MSets.MSetProperties.
Require Import Coq.MSets.MSetDecide.
Require Import Coq.Structures.OrdersEx.
Require Import Coq.Program.Wf.
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
  - admit.
  - admit.
  - destruct IHsubQual; destruct H2.
    split; try split; auto; SDecide.fsetdec.
Admitted.

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

Definition expand_one_step (ctx: context) (qual: qualset) : option qualset :=
  match is_well_formed ctx && StrSet.subset qual (ddomain ctx) with
  | true => Some (StrSet.union qual (StrSet.fold (fun x p =>
      match retrieve ctx x with
      | Some (Sym true false q) => StrSet.union (StrSet.add x q) p
      | _ => p
      end) qual StrSet.empty))
  | false => None
  end.

Lemma expand_one_step_is_monotonic : forall G q1 q2,
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
Admitted.

Lemma expand_is_sound: forall G q1 q2, expand G q1 = Some q2 -> subQual G q2 q1.
Admitted.

Lemma expand_is_saturated: forall G q1 q2, expand G q1 = Some q2 -> expand G q2 = Some q2.
Admitted.