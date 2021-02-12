(ns clj-evm.evm
  (:import [java.net URL]
           [org.web3j.abi.datatypes Address]
           [org.web3j.evm Configuration ConsoleDebugTracer PassthroughTracer EmbeddedWeb3jService]))

(defn configuration
  "Make a configuration for a specific address and some prefunded eth.
  The genesis file URL will default to the mainnet."
  ([^Address self-address ^Long eth-fund]
   (Configuration. self-address eth-fund))
  ([^Address self-address ^Long eth-fund ^URL genesis-file-url]
   (Configuration. self-address eth-fund genesis-file-url)))

(defn console-debug-tracer
  "Make a new console debug tracer.
  The contract meta file will default to \"build/resources/main/solidity\".
  The stdin will default to the real stdin."
  ([]
   (ConsoleDebugTracer.))
  ([meta-file]
   (ConsoleDebugTracer. meta-file))
  ([meta-file stdin]
   (ConsoleDebugTracer. meta-file stdin)))
