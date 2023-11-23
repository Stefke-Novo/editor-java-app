(ns window.component-tree
  (:require [seesaw.core :refer [tree frame horizontal-panel scrollable label button-group listbox]]
            [seesaw.dev :refer [show-options]]
            [functions.entity :refer [create-entity]])
  (:import (javax.swing.tree DefaultMutableTreeNode)))
(show-options (button-group))
(def window-map {
                 :entity frame
                 :model {:title "New frame" :visible? true}
                 :contains :content
                 :has
                 {
                  :entity horizontal-panel
                  :model nil
                  :contains :items
                  :has
                  [
                   {
                    :entity label
                    :model {:text "Label 1"}
                    }
                   {
                    :entity label
                    :model {:text "Label 2"}
                    }
                   ]
                  }
                 })
(defn form-window [model-map]
  (if (nil? (:has model-map))
    (create-entity (:entity model-map) (:model model-map))
    (create-entity (:entity model-map) (assoc (:model model-map) (:contains model-map) (if (vector? (:has model-map))
                                                                                         (mapv form-window (:has model-map)) (form-window (:has model-map)))))
    ))

(defn form-tree-model [model-map]
  (if (nil? (:has model-map))
    (DefaultMutableTreeNode. model-map)
    (let [node (DefaultMutableTreeNode. (merge {:entity (:entity model-map)} {:model (:model model-map)} {:contains (:contains model-map)}))]
      (if (vector? (:has model-map))
        (doseq [list-item (:has model-map)]
          (.add node (form-tree-model list-item)
            ))
        (.add node (form-tree-model (:has model-map))))
      node)
    ))
(form-tree-model window-map)

(eval (form-window window-map))
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
(tree (listbox :model ))
(ancestors (type []))