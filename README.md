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

- Tracking aliases in types

A freshly allocated resource (e.g. memory allocation) is _tracked_ but has not
aliased to any variable:
```scala
Ref 42  // : Ref[Int]^◆
```
The diamond qualifier notation indicates that this term yields a fresh resource.

Assignment propagates aliases (or reachability):

```scala
val x = Ref 42;
val y = x;       // : Ref[Int]^y under the context of y: Ref[Int]^x, x: Ref[Int]^◆
...
```

- Polymorphic identity function

```scala
def polyId[T <: Top](x: T^<>): T^x = x
val x = id[Int](3);              // : Int^∅
// type argument to id is optional
val c = id(Ref 42);              // : Ref[Int]^c
x + (! c)                        // : Int^∅
```

The identity function is not only polymorphic with respect to argument types, but also
to its reachability.
The result of `id(3)` is untracked, since its argument is untracked (indicated
by the empty set notation).
In contrast, the result of `id(Ref 42)` remains tracked (indicated by the `c`
in the type), since `Ref 42` is a freshly allocated resource that we want to
track.
The type system guarantees tracked resources remain tracked all the time.

- Separation

``` scala
val c1 = Ref 42;
val c2 = c1;       // c2 and c1 are aliased
def f(x: Ref[Int]^<>): Int = { (!x) + (!c1) };
f(c2)              // type error
```

Function applications in Diamond check separation between the applied function and argument.
For example, `f` captures `c1` from its defining scope, and also takes an argument `x`.
The argument of `x` is annotated with the diamond, indicating that the function cannot
take argument that has overlap with what can be observed by the function from the environment.
Therefore, the above example issues a type error.

Programmers can indicate permissible overlap at the function's argument position.
For example, we can add `c1` into the `f`'s argument qualifier, then the following
program type checks (even though we provide `c2` as argument but the typechecker knows
that `c1` can be reached from `c2`).

``` scala
val c1 = Ref 42;
val c2 = c1;       // c2 and c1 are aliased
def f(x: Ref[Int]^{c1, <>}): Int = { (!x) + (!c1) };
f(c2)              // ok
```

- Escaped resources

Diamond smoothly supports tracking escaped resources via self-reference.
The following snippet shows an example that we allocate
a mutable cell with function `counter`, which then returns
two functions to increase and decrease the mutable cell.
These two functions they capture the same resource and are
returned as a pair.

```scala
def counter(n: Int) = {
  val x = Ref n;
  val inc = () => { x := (! x) + 1 };
  val dec = () => { x := (! x) - 1 };
  makePair[(() => Unit)^x][(() => Unit)^x](inc)(dec)
};
val p = counter(0);
val inc = fst[(() => Unit)^p][(() => Unit)^p](p); // (() => Unit)^p
val dec = snd[(() => Unit)^p][(() => Unit)^p](p); // (() => Unit)^p
```

In the outer scope, once we leave the scope of `x`, we can still track
the fact that the `inc` and `dec` function they access and mutate the same
hidden resource (see `examples/counter.dia` for full example).

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

- **Polymorphic Reachability Types: Tracking Freshness, Aliasing, and Separation in Higher-Order Generic Programs** </br>
by Guannan Wei, Oliver Bračevac, Songlin Jia, Yuayn Bao, and Tiark Rompf
([preprint](https://arxiv.org/abs/2307.13844)).

- **Reachability Types: Tracking Aliasing and Separation in Higher-order Functional Programs** (OOPSLA 2021)</br>
by Yuyan Bao, Guannan Wei, Oliver Bračevac, Luke Jiang, Qiyang He, and Tiark Rompf
([pdf](https://dl.acm.org/doi/10.1145/3485516)).

- Mechanizations and Coq proofs of the core calculus can be found [here](https://github.com/TiarkRompf/reachability).

