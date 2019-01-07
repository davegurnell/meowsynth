# Meowsynth

The future of feline synthesis.

Copyright 2015-2016 [Dave Gurnell][davegurnell]. Licensed [GPLv3][license].

[![Join the chat at https://gitter.im/underscoreio/scala](https://badges.gitter.im/underscoreio/scala.svg)][gitter]

## Synopsis

Meowsynth is a fun project to demonstrate the serious side of functional programming.
It is built on top of [Compose][compose], a functional music composition library
I developed for training at [Underscore][underscore].

You can read more about Compose [in this blog post][compose-post]
and browse the source code for the demo songs [on Github][compose-examples].

Contributions to Compose and Meowsynth are welcome!
Please jump in to [the Underscore Gitter channel][gitter] 
to chat about ideas.

## Compiling and Running the Code

Meowsynth is a [ScalaJS][scalajs] project built using [SBT][sbt] and Li Haoyi's [workbench] plugin.
All you need to build the code is a working JVM. The SBT launcher takes care of the rest:

~~~ bash
# Change to the root directory of the project:
$ cd meowsynth

# Launch SBT:
$ ./sbt.sh

# Compile the code from within SBT:
> fastOptJS

# Leaving SBT running, open the debug server running on `localhost:12345`:
$ open 'http://localhost:12345/target/scala-2.11/classes/index-dev.html'
~~~

## Deploying the Code

~~~bash
# Compile/copy everything to the dist directory:
./build.sh

# Commit the dist directory:
git add .
git commit -m 'Build ready for deployment'

# Deploy to gh-pages:
./deploy.sh
~~~

[davegurnell]: http://davegurnell.com
[license]: http://www.apache.org/licenses/LICENSE-2.0.txt
[gitter]: https://gitter.im/underscoreio/scala?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge
[underscore]: http://underscore.io
[scalajs]: http://scala-js.org
[sbt]: http://scala-sbt.org
[workbench]: https://github.com/lihaoyi/workbench
[compose]: https://github.com/underscoreio/compose
[compose-examples]: https://github.com/underscoreio/compose/tree/develop/examples/src/main/scala/compose/examples
[compose-post]: http://underscore.io/blog/posts/2015/03/05/compositional-music-composition.html
