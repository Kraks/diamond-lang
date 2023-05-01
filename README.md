# The Diamond Language

Implementing the Reachability Types

## Current Status

- AST embedded in Scala 3
- STLC + Boolean + Number + References
	- with `let`, `cond`, and arithmetic
- without type synthesis
- $\alpha$-equivalence by renaming
- Test cases implemented
- Can be extended with System $F_{<:}$ (WIP)

## Structure

- `coq/`: Coq proof for metatheories.
- `Reveal.md`: Source code for slides, with LaTeX typing rules.
- `src/main/scala/qualstlc`: Sources for
    * language definition `Lang.scala` and,
    * type checker implementation `TypeCheck.scala`, including,
        + top-level driver `topTypeCheck` and `typeCheck`,
        + subtype relation `isSubQType`, `isSubType`, `isSubQual`,
        + renaming and substitution facilities `*Rename`, `*Subst`,
        + and other helpers.
- `src/test/scala/qualstlc/TypeCheck.scala`: Test cases implemented.

## How-To

To get going with the project, you need `sbt`, the build tool for Scala projects.
You can install `sbt` and JVM environment using [Couriser](https://get-coursier.io/docs/cli-installation).

With `sbt` installed, go to this directory and execute,

```bash
sbt 'testOnly *.QualSTLCTests'
```

## Evaluation

### Test Cases

`class QualSTLCTests` implements the following test cases for the language and
type checker,

- `syntax`: ensures that embedded notations in Scala generates the expected AST.
- `subqual`: ensures that `isSubqual` works as expected.
- `alloc`: ensures that `alloc` in the language produces types with freshness.
- `let`: ensures that `let` binding works as expected.
- `polymorphic reachability`: ensures that dependent application works as expected.
- `escaping closures`: ensures that escaping closures are marked with correct type.
- `separation`: ensures that unexpected overlapping are checked.
- `var rename`: ensures that renaming (for $\alpha$-equivalence) works as expected.
- `subtype`: ensures that subtyping works as expected.
- `saturation`: ensures that `.saturated` (for application with freshness) works as expected.

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