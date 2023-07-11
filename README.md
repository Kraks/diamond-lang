# The Diamond Language

This is the accompanying artifact for submission #73 __Polymorphic Reachability
Types: Tracking Freshness, Aliasing, and Separation in Higher-Order Polymorphic
Programs__.

In this artifact, we provide an implementation of the polymorphic reachability
type system, dubbed the Diamond language (since our freshness marker is the
diamond shape).
The Diamond language has a Scala-like syntax, and enhances types with
reachability qualifiers.
We also provide checked, executable examples demonstrated in
the paper.

## Get Started

Diamond is written in Scala 3, so to get started with the project, you need
`sbt`, the build tool for Scala projects.  You can install `sbt` and JVM
environment using [Coursier](https://get-coursier.io/docs/cli-installation).

## Examples in the Paper

The examples shown in the paper can be found at `src/test/scala/popl24submission/Examples.scala`.
You can also run the following test in `sbt` to check that these test cases have expected types
or expected error:

```
sbt:Diamond> testOnly diamond.popl24.ExamplesInPaper
```

The expected output is

```
sbt:Diamond> testOnly diamond.popl24.ExamplesInPaper
[info] ExamplesInPaper:
[info] - Sec 2.2.2 - Freshness Marker
[info] - Sec 2.2.3 - Precise Reachability Polymorphism
[info] - Sec 2.2.4 - Maybe-Tracked and Subtyping
[info] - Sec 2.2.5 - On-Demand Transitivity
[info] - Sec 2.2.6 - Qualifier-Dependent Application
[info] - Sec 2.3 - Type-and-Qualifier Abstractions in F◆-Sub
[info] - Sec 2.4.1 - Transparent Pairs
[info] - Sec 2.4.2 - Opauqe Pairs
[info] - Sec 2.4.2 - Transparent to Opaque Pairs
[info] - Fig 1 and Sec 2.5 - counter example and neste mutable references
[info] Run completed in 257 milliseconds.
[info] Total number of tests run: 10
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 10, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
```

## Play with other Examples

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

Compared to examples used in the paper, the implemented front-end language adds
a `topval` form to define top-level bindings.
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
    * `Run.scala` top-level driver and pretty-printers
    * `Parser.scala` the parser that translates the Diamond front-end language to the core language.
- `src/test/scala` test cases
    * `src/test/scala/popl24submission/Examples.scala` examples corresponding to the submitted paper.
- `examples` some examples written in the Diamond front-end language.

