# The Diamond Language

![Scala CI](https://github.com/Kraks/Diamond-lang/actions/workflows/scala.yml/badge.svg)

<img src="rusty_diamond.png?raw=true" alt="A rusty diamond by Midjourney" width="512px" height="512px"/>

A prototype programming language with polymorphic reachability types.

## Examples

- Polymorphic identity function (`examples/polyId.dia`):

```scala
def polyId[T <: Top](x: T^<>): T^x = x
val x = id[Int](3);              // : Int^∅
val c = id[Ref[Int]^<>](Ref 42); // : Ref[Int]^◆
x + (! c)                        // : Int^∅
```

## How-To

To get started with the project, you need `sbt`, the build tool for Scala projects.
You can install `sbt` and JVM environment using [Coursier](https://get-coursier.io/docs/cli-installation).

You can play with the type checker and interpreter by writing programs under `examples` and run
the following command to see its type and evaluation result (e.g. `run polyId.dia`).

```
sbt:Diamond> run <filename.dia>
```

You can also run `test` to check all mechanized test cases.

```
sbt:Diamond> test
```

## Structure

- `src/main/scala/qualfsub`
    * `CoreLang.scala` the core language definitions.
    * `TypeCheck.scala` the type checker.
    * `Eval.scala` the interpreter.
    * `Parser.scala` the parser that translates the Diamond front-end language to the core language.
- `src/test/scala` test cases.
- `examples` examples written in the Diamond front-end language.
- `coq`: Coq proof for the metatheory of algorithmic subtyping used in the implementation.

## References

[1] **Reachability Types: Tracking Aliasing and Separation in Higher-order Functional Programs** (OOPSLA 2021)</br>
by Yuyan Bao, Guannan Wei, Oliver Bračevac, Luke Jiang, Qiyang He, and Tiark Rompf
([pdf](https://dl.acm.org/doi/10.1145/3485516)).

[2] **Polymorphic Reachability Types: Tracking Freshness, Aliasing, and Separation in Higher-Order Polymorphic Programs** (2023)</br>
by Guannan Wei, Oliver Bračevac, Siyuan He, Yuyan Bao, and Tiark Rompf
