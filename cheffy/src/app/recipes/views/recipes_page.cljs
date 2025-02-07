(ns app.recipes.views.recipes-page
  (:require [app.components.page-nav :refer [page-nav]]
            ["@smooth-ui/core-sc" :refer [Typography]]
            [re-frame.core :as rf]
            [app.recipes.views.recipe-list :refer [recipe-list]]
            [app.recipes.views.recipe-editor :refer [recipe-editor]]))

(defn recipes-page
  []
  (let [public @(rf/subscribe [:public])
        drafts @(rf/subscribe [:drafts])
        logged-in? @(rf/subscribe [:logged-in?])]
    [:<>
     [page-nav {:center "recipes"
                :right (when logged-in? [recipe-editor])}]
     (when (seq drafts)
       [:<>
        [:> Typography {:variant "h4"
                        :py 20
                        :font-weight 700}
         "Drafts"]
        [recipe-list drafts]])
     (when logged-in?
       [:<>
        [:> Typography {:variant "h4"
                        :py 20
                        :font-weight 700}
         "Public"]
        [recipe-list public]])]))
