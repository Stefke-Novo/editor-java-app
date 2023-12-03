(ns window.component-tree
  (:require [functions.entity :refer [create-entity]]
            [seesaw.core :refer [button-group frame horizontal-panel label tree]]
            [seesaw.dev :refer [show-options]]
            [clojure.string :refer [split]])
  (:import (javax.swing.tree DefaultMutableTreeNode DefaultTreeModel)))
(show-options (button-group))
(defn new-node [m]
  (proxy [DefaultMutableTreeNode] [m]
    (toString [] (get (split (str (type (:entity m))) #"\$") 1))))
(def window-map {
                 :entity frame
                 :model {:title "New frame" :visible? true}
                 :contains :content
                 :has
                 nil
                 ;{
                 ; :entity horizontal-panel
                 ; :model nil
                 ; :contains :items
                 ; :has
                 ; [
                 ;  {
                 ;   :entity label
                 ;   :model {:text "Label 1"}
                 ;   }
                 ;  {
                 ;   :entity label
                 ;   :model {:text "Label 2"}
                 ;   }
                 ;  ]
                 ; }
                 })
(defn form-window [model-map]
  (if (nil? (:has model-map))
    (create-entity (:entity model-map) (:model model-map))
    (create-entity (:entity model-map) (assoc (:model model-map) (:contains model-map) (if (vector? (:has model-map)) (let [result (mapv form-window (:has model-map))] (println "Form window result: " result) result) (form-window (:has model-map)))))
    ))
(form-window window-map)

(defn form-tree-model [model-map]
  (if (nil? (:has model-map))
    (new-node model-map)
    (let [node (new-node (merge {:entity (:entity model-map)} {:model (:model model-map)} {:contains (:contains model-map)}))]
      (if (vector? (:has model-map))
        (doseq [list-item (:has model-map)]
          (.add node (form-tree-model list-item)
            ))
        (.add node (form-tree-model (:has model-map))))
      node)
    ))
(form-tree-model window-map)
(defn form-tree [model]
  (let [tree-java (tree :scrolls-on-expand? true)]
    (.setRoot (.getModel tree-java) (form-tree-model model))
    tree-java))

;(def tree-example (tree :scrolls-on-expand? true))
;(def tree-example-model (.getModel tree-example))
;(def root-node (DefaultMutableTreeNode. {:naziv "Stavka 1"}))
;(.add root-node (DefaultMutableTreeNode. {:naziv "Podstavka 2"}))
;(.add root-node (DefaultMutableTreeNode. {:naziv "Podstavka 3"}))
;(.setRoot tree-example-model (form-tree-model window-map) )
(show-options (tree))
;(frame :title "Tree test"
;       :visible? true
;       :content (horizontal-panel
;                  :items
;                  [
;                   (scrollable tree-example)
;                   ]))
(defn get-selected-node-content [tree]
  (.getUserObject (.getLastSelectedPathComponent tree)))
(defn update-selected-node [tree content]
  (.setUserObject (.getLastSelectedPathComponent tree) content)
  (.reload (.getModel tree)))
(defn delete-selected-node [tree]
  (.removeFromParent (.getLastSelectedPathComponent tree))
  (.reload (.getModel tree)))
(defn add-to-selected-node [tree content]
  (.add (.getLastSelectedPathComponent tree) (new-node content))
  (.reload (.getModel tree)))
(defn form-model-from-tree [node]
  (if (= (.getChildCount node) 0)
    (.getUserObject node)
    ;(assoc (.getUserObject node) :has
    ;                             (if (= (:contains node) :content)
    ;                               (form-model-from-tree (nth (enumeration-seq (.children node) ) 0))
    ;                               (mapv form-model-from-tree (vec (enumeration-seq (.children node))))))
    (do
      (if (= (:contains (.getUserObject node)) :content)
        (assoc (.getUserObject node) :has (form-model-from-tree (nth (enumeration-seq (.children node) ) 0)))
        (assoc (.getUserObject node) :has (mapv form-model-from-tree (vec (enumeration-seq (.children node)))))
        ))

    )
    )

;(vec (enumeration-seq (.children )))
(def window-example (form-tree-model window-map))
;(def formed-tree (form-tree-model window-map))

(form-model-from-tree window-example)

;barem nedelju dana pre javiti se za raspored ili eventualna pitanja