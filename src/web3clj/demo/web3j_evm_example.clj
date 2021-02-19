(ns web3clj.demo.web3j-evm-example
  "The Demo from web3j-evnexample, but redone as Clojure.

  https://github.com/web3j/web3j-evmexample

  https://github.com/web3j/web3j-evmexample/blob/master/src/main/java/org/web3j/web3jevmexample/Demo.java"
  (:require [web3clj.abi      :as abi]
            [web3clj.crypto   :as crypto]
            [web3clj.evm      :as evm]
            [web3clj.protocol :as protocol]
            [web3clj.tx       :as tx]
            [web3clj.utils    :as utils]))

(comment
  (def conf (evm/configuration (crypto/address crypto/demo-wallet) 10))

  ;; (def op-tracer (evm/console-debug-tracer))
  ;; (def embedded-service (evm/embedded-web3j-service conf op-tracer))

  (def passthrough-tracer (evm/passthrough-tracer))
  (def embedded-service (evm/embedded-web3j-service conf passthrough-tracer))

  (def web3j (protocol/web3j-build embedded-service))
  (println "Starting a simple ETH transfer transaction ...")
  (def recipient-address "0x2dfBf35bb7c3c0A466A6C48BEBf3eF7576d3C420")
  (def tx-receipt (tx/send-funds! web3j
                                  crypto/demo-wallet
                                  recipient-address
                                  (utils/ether 1)))
  (println "Transfer transaction receipt:" tx-receipt)
  (if (tx/ok? tx-receipt)
    (println "Transaction was ok.")
    (println "Failed transaction.")))
