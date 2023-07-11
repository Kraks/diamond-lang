# The Diamond Language

![Scala CI](https://github.com/Kraks/Diamond-lang/actions/workflows/scala.yml/badge.svg)

<p align="center">
<img src="rusty_diamond.png?raw=true" alt="A rusty diamond by Midjourney" width="300px" height="300px"/>
</p>

A prototype implementation of the polymorphic reachability type system, dubbed
the Diamond programming language.  The Diamond language has a Scala-like
syntax, and enhances types with reachability qualifiers that track
reachability and sharing of resources.

## Examples

- Polymorphic identity function:

```scala
def polyId[T <: Top](x: T^<>): T^x = x
val x = id[Int](3);              // : Int^∅
// type argument to id is optional
val c = id(Ref 42);              // : Ref[Int]^◆
x + (! c)                        // : Int^∅
```

## Get Started

Diamond is written in Scala 3, so to get started with the project, you need
`sbt`, the build tool for Scala projects.  You can install `sbt` and JVM
environment using [Coursier](https://get-coursier.io/docs/cli-installation).

## Play with Examples

You can play with the type checker and interpreter by writing programs under `examples` and run
the following command to see its type and evaluation result.

```
sbt:Diamond> run <filename.dia>
```

For example, `examples/polyId.dia` contains a snippet of the polymorphic identity function:

```scala
def polyId[T <: Top](x: T^<>): T^x = x
val x = id(3);              // : Int^∅
val c = id(Ref 42);         // : Ref[Int]^◆
x + (! c)                   // : Int^∅
```

Running `run polyId.dia` shows the parsed AST, the type of the final result, as
well as the evaluation result.

Additionally, you may run `test` in `sbt` to check all mechanized test cases.

```
sbt:Diamond> test
```

### Experiment with Qualifiers

The implemented front-end language adds a `topval` form to define top-level
bindings.
It is useful to assist experimenting with the language, but is not intended to
be used as a real feature of the language.
In contrast to ordinary let-binding using the `val` keyword, bindings declared
with `topval` will be persistent, i.e. we would not leave its scope at the end
of the program.  In this way, we can examine the qualifiers more clearly.
For example, if we define `x` using `topval`:

```
def polyId[T <: Top](x: T^<>): T^x = x
topval x = id(Ref 42);
x
```

We will see the final type is `Ref[Int^∅]^x` where the reachability `x` is residualized
(instead of `Ref[Int^∅]^◆`).

## Structure

- `src/main/scala/qualfsub`
    * `CoreLang.scala` the core language definitions.
    * `TypeCheck.scala` the type checker.
    * `Eval.scala` the interpreter.
    * `Run.scala` top-level driver and pretty-printers.
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
