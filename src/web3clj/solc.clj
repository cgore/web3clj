(ns web3clj.solc
  (:require
    [clojure.java.io :as io]
    [web3clj.core :as w3cljc]
    [web3clj.crypto :as w3cljcrypto]
    [web3clj.evm :as w3cljevm]
    [web3clj.protocol :as w3cljproto]
    [web3clj.utils :as wu]
    [web3clj.utils.numeric :as wun])
  (:import
    org.web3j.codegen.SolidityFunctionWrapperGenerator
    (org.web3j.crypto
      Credentials
      WalletUtils)
    (org.web3j.protocol
      Web3j
      Web3jService)
    org.web3j.protocol.http.HttpService
    (org.web3j.sokt
      MainKt
      SolcArguments
      SolcInstance
      SolcOutput
      SolidityFile)))


(defn compile-contract
  "Compile a given contract from a file and
  return compiled information. Shape of returned
  data :
  {:abi <compiled-abi>
   :bytecode <compiled-bytecode>
   :ast <compiled-ast>}"
  [file contract & {out :out solc-args :solc-args
                    :or {out "/tmp"
                         solc-args [SolcArguments/AST
                                    SolcArguments/BIN
                                    SolcArguments/ABI
                                    SolcArguments/OVERWRITE
                                    (.param (SolcArguments/OUTPUT_DIR)
                                            (wu/func0 out))]}}]
  (let [sol-file (SolidityFile. file)
        solc (.getCompilerInstance sol-file ".web3j" false)
        solc-args (into-array SolcArguments solc-args)]
    (when (= 0 (.getExitCode (.execute solc solc-args)))
      {:abi (slurp (str out "/" contract ".abi"))
       :bytecode (slurp (str out "/" contract ".bin"))
       :ast (slurp (str out "/" contract ".sol.ast"))})))


(defn generate-solidity-wrappers
  "Generate java wrappers from compiled solidity contract files.
  Alternatively you can use the ~web3j generate solidity~ cli tool
  Note: this does not seem to work with codegen 5.0.0, works with 4.8.4!"
  [^String bytecode-file
   ^String abi-file
   ^String java-src-path
   contract-name
   package-name]
  (let [gen-wrapper (SolidityFunctionWrapperGenerator. (io/file bytecode-file)
                                                       (io/file abi-file)
                                                       (io/file java-src-path)
                                                       contract-name
                                                       package-name
                                                       true ; useJavaNativeTypes
                                                       true ; useJavaPrimitiveTypes
                                                       20)]   ; address length in bytes, default is 20

    (.generate gen-wrapper)))


(defn create-compiled-contract
  "Compile solidity contract and spit it out to
  files
  Returns: dictionary with :abi and :bytecode keys"
  [file contract-name out-path]
  (let [{:keys [abi bytecode] :as out} (compile-contract file contract-name :out out-path)
        file-name (str out-path "/" contract-name)]
    (io/make-parents file-name)
    (spit (str file-name ".abi") abi)
    (spit (str file-name ".bytecode") bytecode)
    out))


(comment
  (let [{:keys [abi bytecode]} (create-compiled-contract "resources/contracts/Inbox.sol"
                                                         "Inbox"
                                                         "resources/out")]
    (println "ABI is " abi))
  (generate-solidity-wrappers "resources/out/Inbox.bytecode"
                              "resources/out/Inbox.abi"
                              "src/java"
                              "Inbox"
                              "web3clj.solc")
  ;; At this point you will need to restart the repl for it
  ;; to load this namespace.

  (import web3clj.solc.Inbox)

  ;; Deploy a smart contract
  (def web3-ganache (Web3j/build (HttpService. "http://localhost:7545")))
  ;; These credentials determine the author of the contract deployment
  (def pr-key "8b246c57e1eb7ccec7705ce7889144292c37e02bec747da104b8981fdfb9fcfe")
  (def local-wallet (Credentials/create pr-key))

  (def inbox-contract (.send (Inbox/deploy web3-ganache
                                           local-wallet
                                           ;; this comes from the ganache settings
                                           (wun/->big-integer 20000000000)
                                           ;; eventually we required 302737 of gas
                                           (wun/->big-integer 2000000)
                                           "hello world")))

  ;; get the message from contract
  (def contract-message (.send (.getMessage inbox-contract)))

  ;; update the message
  ;; who decides the gas price and gas limit on these transactions?
  (.send (.setMessage inbox-contract "bye for now")))
