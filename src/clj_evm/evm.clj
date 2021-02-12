(ns clj-evm.evm
  (:import [java.net URL]
           [org.web3j.abi.datatypes Address]
           [org.web3j.evm Configuration ConsoleDebugTracer PassthroughTracer EmbeddedWeb3jService]))

(defn configuration
  "Make a configuration, given an address and some eth funds, defaulting to mainnet."
  ([^Address self-address ^Long eth-fund]
   (Configuration. self-address eth-fund))
  ([^Address self-address ^Long eth-fund ^URL genesis-file-url]
   (Configuration. self-address eth-fund genesis-file-url)))
