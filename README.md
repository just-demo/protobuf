gradlew --stop
gradlew clean jmh

======================
without delegate to gradle proto files will not be build automatically when running a class from intellij, also generated sources will not be available
1) int64 vs object
2) camel case becomes lowercase
3) enum UNDEFINED vs 0, null in mapstruct?
4) no way to deserialize enum as case insensitive
5) json property mapping is not supported
6) Int32Value.of(integer) - null?
7) when a message is removed from proto file compilation fails because old class is referencing to some kind of outer bucket that does not contain the class anymore