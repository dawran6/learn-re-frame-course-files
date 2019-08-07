(ns app.inbox.views.inboxes
  (:require [re-frame.core :as rf]
            [app.components.page-nav :refer [page-nav]]
            ["@smooth-ui/core-sc" :refer [Box]]))

(defn inboxes
  []
  (let [user-inboxes @(rf/subscribe [:user-inboxes])]
    [:> Box
     [page-nav {:center "inbox"}]
     [:> Box {:class "cards"}
      (for [[k {:keys [id notifications updated-at]}] user-inboxes]
        ^{:key k}
        [:div {:id id}
         k])]]))
