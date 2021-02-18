(ns web3clj.core
  (:require [web3clj.abi      :as abi]
            [web3clj.crypto   :as crypto]
            [web3clj.evm      :as evm]
            [web3clj.protocol :as protocol]
            [web3clj.tx       :as tx]
            [web3clj.utils    :as utils]))

(comment
  ;; The Demo from web3j-evnexample, but redone as Clojure.
  (let [conf (evm/configuration (crypto/address crypto/demo-wallet) 10)
        op-tracer (evm/console-debug-tracer)
        embedded-service (evm/embedded-web3j-service conf op-tracer)
        web3j (protocol/web3j-build embedded-service)
        _ (println "Starting a simple ETH transfer transaction ...")
        recipient-address "0x2dfBf35bb7c3c0A466A6C48BEBf3eF7576d3C420"
        tx-receipt (tx/send-funds! web3j
                                   crypto/demo-wallet
                                   recipient-address
                                   (utils/ether 1))]
    web3j))
