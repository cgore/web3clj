(defproject clj-evm "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.web3j/core      "4.8.4"]
                 [org.web3j/web3j-evm "4.8.4"]]
  :repositories [["jcenter" {:url "https://jcenter.bintray.com/"}]]
  :repl-options {:init-ns clj-evm.core})
