# Getting started

## JDK version

The JDK for the project is initially set to JDK 11; this can be changed in [`pom.xml`](pom.xml), by changing the values specified for the `maven.compiler.source` and `maven.compiler.target` properties. 

## Root project name

Initially, the root project is called `project`. This root project name will become part of the name of any JAR files built by the project; thus, it should be changed to something more relevant to your specific project. This is done by editing the value contained in the `artifactId` element in the [`pom.xml`](pom.xml) file. (There's a `TODO` comment for this change in that file.) The value assigned must be in lowercase; conventionally, `spinal-case` (all lowercase, with dashes separating multiple words) is used.

## Maven coordinates

Maven projects have _coordinates,_ enabling the project's build artifacts to be cached automatically (for reference by other builds, among other things) and optionally deployed to local or remote Maven repositories. There are (at least) three components to Maven coordinates:

* `groupId`
* `artifactId`
* `version`

Set the `groupId` by modifying the value contained in the XML element of the same name in [`pom.xml`](pom.xml). The value should follow the same rules as a Java package name&mdash;all lowercase, without dashes or spaces, and with periods separating the components. In fact, the `groupId` of a project is usually the first 2&ndash;3 components of the base package name used in the project.

Set the `artifactId` as described in ["Root project name", above](#root-project-name). 

The `version` for the Maven coordinates is also set in [`pom.xml`](pom.xml). Here, it is set initially to `1.0.0-SNAPSHOT`, and should be updated according to your project's versioning scheme. (This value is also included as part of the name of JAR artifacts produced by the build.)

## Source and resource folders

Mavem has a default directory structure&mdash;shown below&mdash;for the source, test source, resource, and test resource folder. IntelliJ automatically uses this structure in a Maven project; there's no need to set or change it.

* `src/`
    * `main/`
        * `java/` (source folder)
        * `resources/` (resource folder)
    * `test/`
        * `java/` (test source folder)
        * `resources/` (rest resource folder)

Since Git tracks only files and their paths relative to the repository root, but not directories themselves, each of these directories initially contains a `.keep` file, so that the directory structure is preserved in Git. If/when files are added to any given one of these directories, the `.keep` file in that directory can be deleted.

## Main class

The project is configured to use the Maven Shade plug-in to create a runnable JAR from the root project, with all runtime dependencies packaged in the same JAR. In this project, the name of the main class for the JAR is taken from the content of the `main-class.name` element in [`pom.xml`](pom.xml). (There's a `TODO` comment for this change in that file).

## Dependencies

Dependencies should be declared via `dependency` elements within the `dependencies` element of [`pom.xml`](pom.xml).

We do not recommend that dependencies be incorporated directly as JAR files in the project, unless those JARs are _not_ otherwise accessible in the Maven Central repository, or another accessible Maven repository. In any event, even local JAR files required as dependencies in the project will need to be declared using `dependency` elements in `pom.xml`.

Note that this project is initially configured with JUnit5 as a testing dependency; there are also instructions (in the comments of `pom.xml`) for using JUnit4 instead of JUnit5. (In fact, JUnit4 and JUnit5 can both be used in the same project; however, we don't recommend doing this in most cases.)

Here are just a few more frequently used libraries, with links to the [MvnRepository](https://mvnrepository.com/) page giving the `dependency` element for the latest version (as of 2023-01-17) of each:

| Library | Link to `dependency` specification in MvnRepository                                     |
|:--------|:----------------------------------------------------------------------------------------|
| Gson    | <https://mvnrepository.com/artifact/com.google.code.gson/gson/2.10.1>                   |
| Jackson | <https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.14.1> |
| Jansi   | <https://mvnrepository.com/artifact/org.fusesource.jansi/jansi/2.4.0>                   |

## Version control

The `.gitignore` file included in this project repository is specifically intended for use with either a Gradle or Maven project. 

The list of included/excluded files and directories is close to&mdash;but not exactly the same as&mdash;the corresponding list for a native IntelliJ project. If, at some moment, the project is converted to a native IntelliJ project, some files currently being ignored in the `.idea` directory should be tracked in version control. Do this by replacing the line (initially lines 4--5) reading  

```gitignore
*.iml
.idea/
```

with the following:

```gitignore
.idea/*
!.idea/artifacts/
!.idea/libraries/
!.idea/misc.xml
!.idea/modules.xml
```

These changes should be made by one team member, on one branch of the repository, then propagated (to remotes, other branches, other team members' local clones, etc.) via the usual Git mechanisms.

## Build tasks

In a project it recognizes as using Maven, IntelliJ automatically leverages Maven for the operations found under the **Build** menu. However, a more complete set of lifecycle _phases_ (roughly analogous to Gradle tasks) is available under _Lifecycle_ in IntelliJ's **Maven** tool window. Here are some phases that may be useful in this project:

* _clean_

    Deletes the `target` directory&mdash;including all previously compiled `.class` files, copied resource files, etc.

* _compile_

    Compiles code in `src/main/java` and processes resources in `src/main/resources`, with output placed in `target/classes`.

* _test_

    Compiles code in `src/main/java` and `src/test/java`, processes resources in `src/main/resources` and `src/test/resources`, with output written to `target/classes` and `target/test-classes`, and uses the Maven Surefire plugin to run all tests using the engine(s) (e.g. JUnit5) specified in `pom.xml`.

* _package_

    Compiles code in `src/main/java`, processes resources in `src/main/resources`, generates HTML from the bytecode and the Javadoc comments in the source code, and packages the results into JAR files in `build/libs`.
