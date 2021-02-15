(ns web3clj.core
  (:require [web3clj.abi      :as abi]
            [web3clj.crypto   :as crypto]
            [web3clj.evm      :as evm]
            [web3clj.protocol :as protocol]))

(comment
  ;; The Demo from web3j-evnexample, but redone as Clojure.
  (let [conf (evm/configuration (crypto/address crypto/demo-wallet) 10)
        op-tracer (evm/console-debug-tracer)
        embedded-service (evm/embedded-web3j-service conf op-tracer)]
    (protocol/web3j-build embedded-service)))
