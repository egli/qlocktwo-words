(defproject qlocktwo-words "0.1.0"
  :description "Calculate an ideal layout of a set of words for a qlocktwo clone"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/math.combinatorics "0.1.4"]]
  :main ^:skip-aot qlocktwo-words.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
