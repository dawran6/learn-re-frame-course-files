(ns app.recipes.views.recipe-page
  (:require [app.components.page-nav :refer [page-nav]]
            ["@smooth-ui/core-sc" :refer [Box Row Col]]
            [re-frame.core :as rf]
            [app.recipes.views.recipe-info :refer [recipe-info]]
            [app.recipes.views.recipe-image :refer [recipe-image]]
            [app.recipes.views.recipe-ingredients :refer [recipe-ingredients]]))

(defn recipe-page
  []
  (let [{:keys [name img ingredients steps]} @(rf/subscribe [:recipe])]
    [:> Box
     [page-nav {:center name}]
     [:> Box
      [:> Row
       [:> Col {:xs 12 :sm 6}
        [:> Box {:pb 20}
         [recipe-info]]
        [:> Box {:pb 20}
         [recipe-image]]
        [:> Box {:pb 20}
         [recipe-ingredients]]]
       [:> Col {:xs 12 :sm 6}
        [:> Box {:pb 20}
         steps] ]]]]))
