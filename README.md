# Meowsynth

The future of feline synthesis.

## Compiling and Running the Code

Meowsynth is a ScalaJS project, built using SBT (the Scala Built Tool) and Li Haoyi's [workbench] plugin. All you need to build the code is a working JVM. The SBT launcher takes care of the rest:

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

## TODO List

- Synth engine
  - Representation of a song
    - DONE - Note (C4 1 quarter note)
    - DONE - Rest (nothing quarter note)
    - DONE - Sequence
    - DONE - Parallel
    - TODO - Select intruments (meow, mew, purr)
    - TODO - Tempo changes
  - DONE - Player - play back a song with the web audio API
- TODO - Music notation - allow people to program songs using a nice DSL
- TODO - Web site - create an awesome cat-themed web site
- TODO - Visuals and animations - make the web site more awesome and cat themed

[workbench]: https://github.com/lihaoyi/workbench