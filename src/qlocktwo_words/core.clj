(ns qlocktwo-words.core
  (:gen-class)
  (:require [clojure.math.combinatorics :as combo]
            [clojure.string :as str]))

(def matrix {:cols 11 :rows 11})

(def minutes
  ["föif"
   "zää"
   "viertel"
   "zwänzg"])

(def prepositions
  ["ab" "vor"])

(def hours
  ["eis"
   "zwöi"
   "drü"
   "vieri"
   "föifi"
   "sächsi"
   "sibni"
   "achti"
   "nüni"
   "zääni"
   "elfi"
   "zwölfi"])

(def words
  (->>
   (combo/cartesian-product
    (combo/permutations minutes)
    (combo/permutations prepositions)
    (combo/permutations hours))
   ;; add "it is" and "half", i.e. words that do not need to be
   ;; permuted
   (map (fn [[minutes prepositions hours]]
          (concat ["jetz" "isch"] minutes prepositions ["halbi"] hours)))))

(defn split-lines
  "Split the words so that the sum of the lengths of each line is <= cutoff"
  [cutoff words]
  (let [[lines last-line]
        (reduce (fn [[lines line current-length] word]
                  (let [length (+ current-length (count word))]
                    (if (<= length cutoff)
                      [lines (conj line word) (inc length)]
                      [(conj lines line) [word] (inc (count word))])))
                [[] [] 0]
                words)]
    (conj lines last-line)))

(defn fitness [matrix]
  (let [rows (count matrix)
        cols-in-last (count (str/join " " (last matrix)))]
    [rows cols-in-last]))

(defn layouts [words]
  (->>
   words
   (map #(split-lines (:cols matrix) %))
   (filter #(<= (count %) (:rows matrix)))))

(defn -main
  [& args]
  (first (sort-by count (layouts words))))

