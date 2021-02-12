(ns clj-evm.core
  (:require [clj-evm.abi    :as abi]
            [clj-evm.crypto :as crypto]
            [clj-evm.evm    :as evm]))


(comment
  ;; The Demo
  (let [conf (evm/configuration (crypto/address crypto/demo-wallet) 10)
        op-tracer (evm/console-debug-tracer)])
  )
