(ns proj.core)

(def empty-row [0 0 0])

(def empty-board [empty-row empty-row empty-row])

(defn index-to-symbol [i]
  (nth ["X" " " "O"] (+ i 1)))

(defn printed-row [row]
  (str "\n" (apply str (map index-to-symbol row))))

(defn get-board-column [board,i]
  (map  #(nth % i) board))

(defn board-columns [board]
  (map #(get-board-column board %) [0,1,2]))

(defn get-board-at-pos [board,x,y]
  (nth (nth board y) x))

(defn cross-line-point-getter [board]
  (fn [x,y] (get-board-at-pos board x y)))

(defn cross-line-getter [board]
  (fn [positions] (map #(apply (cross-line-point-getter board) %) positions)))

(defn get-cross-lines [board]
  (map (cross-line-getter board) [[[0 0] [1 1] [2 2]] [[0 2] [1 1] [2 0]]]))

(defn is-line-win [line]
  (> (* (apply + line) (apply + line)) 8))

(defn all-positions [board]
  (apply concat board))

(defn all-lines [board]
  (concat board (board-columns board) (get-cross-lines board)))

(defn has-win [board]
  (if (some #(is-line-win %) (all-lines board)) true false))

(defn winner [board]
  (first (first (filter #(is-line-win %) (all-lines board)))))

(defn value-count [board,value]
  (apply + (map #(if % 1 0) (map #(= % value) (all-positions board) ))))