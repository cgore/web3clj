# web3clj

A Clojure wrapper around the Web3j technologies.

## Usage

In your Leiningen `project.clj` dependencies:

```clojure
[com.cgore/web3clj "0.1.0"]
```

### Key Web3j Libraries

- https://github.com/web3j/web3j - the core web3j library.
- https://github.com/web3j/web3j-maven-plugin - creates java classes based on
  the solidity contract files.
- https://github.com/web3j/web3j-evm
- https://github.com/web3j/web3j-unit
- https://github.com/web3j/web3j-quorum
- https://github.com/web3j/svm
- https://github.com/web3j/web3j-evmexample - babby's first web3j evm,
  duplicated here in the `web3clj.demo.web3j-evm-example` namespace.

## License

Copyright © 2021 Christopher Mark Gore, all rights reserved.

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
