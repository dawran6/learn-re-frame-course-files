(ns app.recipes.views.recipe-list
  (:require ["@smooth-ui/core-sc" :refer [Box]]
            [app.recipes.views.recipe-card :refer [recipe-card]]))

(defn recipe-list
  [items]
  [:> Box {:class "cards"}
   (for [recipe items]
     ^{:key (:id recipe)}
     [recipe-card recipe])])
