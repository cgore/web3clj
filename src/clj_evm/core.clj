(ns clj-evm.core
  (:require [clj-evm.abi    :as abi]
            [clj-evm.crypto :as crypto]
            [clj-evm.evm    :as evm]))

(comment
  ;; The Demo from web3j-evnexample, but redone as Clojure.
  (let [conf (evm/configuration (crypto/address crypto/demo-wallet) 10)
        op-tracer (evm/console-debug-tracer)
        embedded-service (evm/embedded-web3j-service conf op-tracer)]
    embedded-service))
