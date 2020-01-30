# Kotlin compiler bug with internal modifier

Kotlin allows you to call `internal` code if the build target is considered a friend. This feature
is not exposed besides in tests. Tests can call `internal` code from their own module.

This projects triggers a bug in the Kotlin compiler, where this behavior fails and tests cannot
call `internal` code from their module. This project contains two modules `:lib1` and `:lib2`.
`lib1` contains one `internal` class and one unit test calling a function of this class. `lib2`
depends on `lib1` through an `api` dependency, meaning it exposes the code from `lib1`. Tests in
`lib1` depend on `lib2` through the `testImplementation` configuration.

```
:lib1 -> :lib2 -> tests in :lib1
```

This setup triggers a bug in the compiler or while configuring the friends path. Unit tests in
`lib1` cannot call the `internal` code anymore. Notice that the IDE doesn't show a warning nor an
error.

That is probably the same issue as in: https://youtrack.jetbrains.com/issue/KT-35942