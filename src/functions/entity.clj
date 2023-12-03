(ns functions.entity
  (:require [seesaw.core :refer [config! label table frame horizontal-panel]]))

;create entity
(defn create-entity [entity entity-settings]
  `(~entity ~@(mapcat identity entity-settings)))
(create-entity label {:text "Label 1"})

;update entity
(defn update-entity [entity entity-settings]
  `(config! ~entity ~@(mapcat identity entity-settings)))
(update-entity (label) {:text "label 2"})

;delete entity
(defn delete-entity [frame entity]
  (.remove (.getParent entity) entity)
  (.revalidate frame)
  (.repaint frame))

(assoc {:name "Zika"} :name "Mika")
(def ex (atom {:name "Mika"}))
(swap! ex assoc :name "Zika")