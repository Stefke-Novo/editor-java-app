(ns functions.component-models
  (:require [seesaw.core :refer [button text horizontal-panel vertical-panel xyz-panel text-options]]
            [seesaw.dev :refer [show-options]]
            [functions.table :refer [get-data-from-table]]))

(def label-model
  {
   :background :aliceblue
   :border [5 "Compound" 10]
   :bounds [10 10 200 40]
   :drag-enabled? true
   :enabled? true
   :focusable? true
   :font "ARIAL-BOLD-16"
   :foreground :aliceblue
   :h-text-position :left
   :halign :left
   :icon nil
   :id nil
   :listen nil
   :maximum-size nil
   :minimum-size nil
   :popup nil
   :size nil
   :text "new text"
   :tip "Tooltip text"
   :v-text-position :center
   :valign :center
   :visible? true
   })
(def button-model
  {
   :action nil
   :background :aliceblue
   :border nil
   :bounds nil
   :drag-enabled? true
   :enabled? true
   :focusable? true
   :font "ARIAL-BOLD-18"
   :foreground :aliceblue
   :group nil
   :halign :left
   :icon nil
   :id nil
   :listen nil
   :maximum-size nil
   :minimum-size nil
   :paint nil
   :popup nil
   :preferred-size nil
   :selected? false
   :size nil
   :text nil
   :tip nil
   :valign :center
   :visible? true
   })
(def text-field-model
  {
   :action nil
   :background :aliceblue
   :border nil
   :bounds nil
   :caret-color :aliceblue
   :caret-position nil
   :columns nil
   :cursor nil
   :disabled-text-color :aliceblue
   :drag-enabled? true
   :drop-mode :insert
   :editable? true
   :enabled? true
   :focusable? true
   :font "ARIAL-BOLD-18"
   :foreground :aliceblue
   :halign :left
   :icon nil
   :listen nil
   :margin nil
   :maximum-size nil
   :minimum-size nil
   :paint nil
   :popup nil
   :preferred-size nil
   :selected-text-color nil
   :selection-color nil
   :size nil
   :text nil
   :tip nil
   :visible? true
   })
(def horizontal-panel-model
  {
   :background :aliceblue
   :border nil
   :bounds nil
   :cursor nil
   :drag-enabled? false
   :enabled? true
   :focusable? true
   :font "ARIAL-BOLD-18"
   :foreground :aliceblue
   :icon nil
   :id nil
   :maximum-size nil
   :minimum-size nil
   :paint nil
   :popup nil
   :preferred-size nil
   :size nil
   :visible? true
   })

(def vertical-panel-model
  {
   :background :aliceblue
   :border nil
   :bounds nil
   :drag-enabled? true
   :enabled? true
   :focusable? true
   :font "ARIAL-BOLD-18"
   :foreground :aliceblue
   :icon nil
   :id nil
   :maximum-size nil
   :minimum-size nil
   :paint nil
   :popup nil
   :preferred-size nil
   :size nil
   :text nil
   :tip nil
   :visible? true
   })

(def simple-panel-model
  {
   :background :aliceblue
   :border nil
   :bounds nil
   :cursor nil
   :drag-enabled? true
   :enabled? true
   :focusable? true
   :font "ARIAL-BOLD-18"
   :foreground :aliceblue
   :icon nil
   :id nil
   :maximum-size nil
   :minimum-size nil
   :paint nil
   :popup nil
   :preferred-size nil
   :size nil
   :text nil
   :tip nil
   :visible? true
   })
(defn sub-model [model entity]
  (sort (filter (fn [key] (contains? entity key)) (keys model))))
(defn return-component [model]
  ;(println "Label model" (sort (sub-model label-model model)))
  ;(println "Button model" (sort (sub-model button-model model)))
  ;(println "Text field model" (sort (sub-model text-field-model model)))
  ;(println "Vertical panel model " (sort (sub-model vertical-panel-model model)))
  ;(println "Horizontal panel model " (sort (sub-model horizontal-panel-model model)))
  ;(println "Simple panel model " (sort (sub-model simple-panel-model model)))
  (condp = (sort (keys model))
    (sub-model label-model model) {:entity seesaw.core/label}
    (sub-model button-model model) {:entity seesaw.core/button}
    (sub-model text-field-model model) {:entity seesaw.core/text}
    (sub-model vertical-panel-model model) {:entity seesaw.core/vertical-panel :contains :items}
    (sub-model horizontal-panel-model model) {:entity seesaw.core/horizontal-panel :contains :items}
    (sub-model simple-panel-model model) {:entity seesaw.core/xyz-panel :contains :items}))
(defn add-keywords [entity model]
  (reduce (fn [m v] (if ((first v) entity) (assoc m (first v) (last v)))) model (into [] entity)))
(defn form-model [content]
  (condp = (:entity content)
    seesaw.core/label (add-keywords (:model content) label-model)
    seesaw.core/button (add-keywords (:model content) button-model)
    seesaw.core/text (add-keywords (:model content) text-field-model)
    seesaw.core/vertical-panel (add-keywords (:model content) vertical-panel-model)
    seesaw.core/horizontal-panel (add-keywords (:model content) horizontal-panel-model)
    seesaw.core/xyz-panel (add-keywords (:model content) simple-panel-model)))

