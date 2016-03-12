# Meowsynth

The future of feline synthesis.

Copyright 2015-2016 [Dave Gurnell][davegurnell]. Licensed [GPLv3][license].

## Compiling and Running the Code

Meowsynth is a ScalaJS project, built using SBT (the Scala Built Tool) and Li Haoyi's [workbench] plugin.
All you need to build the code is a working JVM. The SBT launcher takes care of the rest:

~~~ bash
# Change to the root directory of the project:
$ cd meowsynth

# Launch SBT:
$ ./sbt.sh fastOptJS

# Compile the code from within SBT:
> fastOptJS

# Leaving SBT running, open the debug server running on `localhost:12345`:
$ open 'http://localhost:12345/target/scala-2.11/classes/index-dev.html'
~~~

[davegurnell]: http://davegurnell.com
[license]: http://www.apache.org/licenses/LICENSE-2.0.txt
[workbench]: https://github.com/lihaoyi/workbench
