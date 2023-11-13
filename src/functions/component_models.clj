(ns functions.component-models
  (:require [seesaw.core :refer [button text horizontal-panel vertical-panel xyz-panel]]
            [seesaw.dev :refer [show-options]]))

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
   :drag-enabled? nil
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