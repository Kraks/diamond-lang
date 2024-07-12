## Supplement Material

This artifact contains the prototype implementation of the type checkers for POPL 2025 submission #86:

Avoid Arguments and Escape with Your Self: Expressive Subtyping and Decidable Bidirectional Checking for Reachability Types.

### Dependencies

- sbt 1.10.0
- JDK 17
- Scala 3

### Prototype type checkers

The prototype bidirectional type checker corresponding to Section 4 of the paper can be found at:
```
src/main/scala/avoidancestlc
```

An extended type checker with additional type quantification a la F-Sub can be found at
```
src/main/scala/avoidancefsub
```

### Examples

Monomorphic pair examples corresponding to Section 5 of the paper can be found at:
```
src/test/scala/avoidancestlc
```

Polymorphic pair examples can be found at:
```
src/test/scala/avoidancefsub
```

To check all examples, start `sbt` in the terminal and run
```
sbt:Diamond> test
```
