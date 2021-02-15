(ns web3clj.utils
  (:import [java.math BigDecimal]
           [org.web3j.utils Convert Convert$Unit]))

(def wei    Convert$Unit/WEI)
(def kwei   Convert$Unit/KWEI)
(def mwei   Convert$Unit/MWEI)
(def gwei   Convert$Unit/GWEI)
(def szabo  Convert$Unit/SZABO)
(def finney Convert$Unit/FINNEY)
(def ether  Convert$Unit/ETHER)
(def kether Convert$Unit/KETHER)
(def mether Convert$Unit/METHER)
(def gether Convert$Unit/GETHER)

(defn ->big-decimal [number]
  (cond (string? number) (BigDecimal. number)
        (number? number) (BigDecimal. number)))

(defn ->wei
  [number ^Convert$Unit unit]
  (Convert/toWei (->big-decimal number) unit))

(defn <-wei
  [number ^Convert$Unit unit]
  (Convert/fromWei (->big-decimal number) unit))
