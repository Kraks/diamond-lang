### Metatheories

The following definitions and lemmas are included in `subqual.v`

- `qualset`, `symbol`, `context`, `ddomain`: language constructs describing $\Gamma$.
- `wf_context` and `is_well_formed` (`is_well_formed_iff` to relate them): describing well-formedness of context.
- `retrieve` (with `retrieve_is_well_formed`): the function to query an element in the context.
- `subQual`: the declarative version of sub-qualifier rules.
    * Congruence rule is treated separately, compared with the version in the slides.
    * `subQual_is_well_formed`, `q_cong'`, `q_cong_fold` are derived lemmas.
- `expand_one_step` is the function expanding the RHS for one step.
    * Lemmas include `*_is_increasing`, `*_is_sound`, and `*_is_monotonic`.
- `expand` is the function to expand the RHS until saturated, using `expand_one_step`.
    * It is defined as a `Program Fixpoint` and is guaranteed to terminate.
    * However, it is not useful in proofs. All latter proofs do not use it.
- `Expand` is the inductive proposition version of `expand`, defined using `expand_one_step`.
    * Lemmas include `*_is_sound`, `*_is_increasing`, `*_is_well_formed`, `*_is_monotonic`, and `*_is_saturated`.
    * Axiom includes `*_is_terminating`.
- `Bounded` is the step upcasting the LHS, defined as an inductive proposition.
    * Lemmas include `*_is_sound`, `*_is_transitive`.
- `algorithmic` is the final algorithmic sub-qualifier rule defined using `Expand` and `Bounded`.
    * Theorems include `*_is_sound` and `*_is_complete`.
