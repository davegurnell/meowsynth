# Meowsynth

The future of feline synthesis.

## Building

Meowsynth is a ScalaJS project, built using SBT (the Scala Built Tool). All you need to build the code is a working JVM. The SBT launcher takes care of the rest:

~~~ bash
# Change to the root directory of the project:
$ cd meowsynth

# Compile the Javascript code with SBT's `fastOptJS` command
# (takes a while the first time you run it):
$ ./sbt.sh fastOptJS

# Run a web server in the local directory:
$ python -m SimpleHTTPServer

# Finally, open `http://localhost:8000`
# in your browser to view the site.
~~~

SBT will cache a lot of library files in `~/.ivy2`.
You may want to monitor the size of this cache if you're
not a regular Scala developer.

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
