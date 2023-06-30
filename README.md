# The Diamond Language

Implementing the Reachability Types.

## Structure

- `src/main/scala/qualfsub`:
    * `CoreLang.scala` the core language definitions.
    * `TypeCheck.scala` the type checker.
        + top-level driver `topTypeCheck` and `typeCheck`.
        + subtype relation `isSubQType`, `isSubType`, `isSubQual`.
    * `Eval.scala` the interpreter.
    * `Parser.scala` parser that translates the Diamond front-end language to the core language.
- `src/test/scala` test cases.
- `examples` examples written in the Diamond front-end language.
- `coq`: Coq proof for the metatheory of algorithmic subtyping used in the implementation.

## How-To

To get started with the project, you need `sbt`, the build tool for Scala projects.
You can install `sbt` and JVM environment using [Couriser](https://get-coursier.io/docs/cli-installation).

You can play with the type checker and interpreter by writing programs under `examples` and run
the following command to test it:

```
sbt:Diamond> run <filename.dia>
```

You can also run `test` to check all mechanized test cases.

```
sbt:Diamond> test
```

## References

[1] **Reachability Types: Tracking Aliasing and Separation in Higher-order Functional Programs** (OOPSLA 2021)</br>
by Yuyan Bao, Guannan Wei, Oliver Bračevac, Luke Jiang, Qiyang He, and Tiark Rompf
([pdf](https://dl.acm.org/doi/10.1145/3485516)).

[2] **Polymorphic Reachability Types: Tracking Freshness, Aliasing, and Separation in Higher-Order Polymorphic Programs** (2023)</br>
by Guannan Wei, Oliver Bračevac, Siyuan He, Yuyan Bao, and Tiark Rompf
