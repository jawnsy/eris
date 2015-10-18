Eris
====

[![Travis CI Build Status](https://travis-ci.org/jawnsy/eris.svg?branch=master)](https://travis-ci.org/jawnsy/eris)
[![AppVeyor CI Build Status](https://ci.appveyor.com/api/projects/status/github/jawnsy/eris?branch=master&svg=true)](https://ci.appveyor.com/project/jawnsy/eris)
[![Code Coverage](https://codecov.io/github/jawnsy/eris/coverage.svg?branch=master)](https://codecov.io/github/jawnsy/eris?branch=master)

Introduction
------------

**Eris** provides a number of compatible implementations of Pseudo-random
Number Generator (PRNG) algorithms.  It was named after the [Greek goddess of
chaos, strife, and discord](https://en.wikipedia.org/wiki/Eris_\(mythology\)).
It is an opinionated reimagination of the `java.util.Random` API, following
best current practices in Java programming.

Features and Restrictions
-------------------------

* **Drop-in compatible**: All algorithms are functionally equivalent to, and
  tested against, the original reference implementations unless otherwise
  stated.
* **Designed for performance**: Implementations do not internally provide any
  thread safety guarantees (e.g. synchronization or use of volatile fields),
  preferring instead to provide improved performance in single-threaded use
  cases.
* **Simplicity**: The core API is very simple and minimalistic (following the
  mantra, "when in doubt, leave it out"), ensuring ease of use.
* **Immutable**: There are no facilities for modifying seed data or other
  configuration parameters once generators are constructed.

Usage
-----


Compatibility
-------------

As this is a new project, there are currently NO guarantees of compatibility.
The API should not be considered stable.

Contributing
------------

Contributions to this project (in the form of bug reports, patches, or pull
requests) are gratefully accepted by the maintainer.  Please see the
`Contributing.md` file contained in this package for details.

Copyright and License
---------------------

Copyright 2015 by Jonathan Yu and other contributors

Licensed under the Apache License, Version 2.0 (the "License"); you may not
use this package except in compliance with the License (see the `LICENSE` file
included in this distribution). You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.
