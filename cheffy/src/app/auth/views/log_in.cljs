(ns app.auth.views.log-in
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [app.router :as router]
            [app.components.page-nav :refer [page-nav]]
            [app.components.form-group :refer [form-group]]
            ["@smooth-ui/core-sc" :refer [FormGroup Label Input Row Col Button Box]]))

(defn log-in
  []
  (let [initial-values {:email "" :password ""}
        values (r/atom initial-values)]
    (fn []
      [:> Row {:justify-content "center"}
       [:> Col {:xs 12 :sm 6}
        [page-nav {:center "log-in"}]
        [form-group {:id :email
                     :label "Email"
                     :type "email"
                     :values values}]
        [form-group {:id :password
                     :label "Password"
                     :type "password"
                     :values values}]
        [:> Box {:display "flex"
                 :justify-content "space-between"}
         [:> Box {:py 1
                  :pr 2}
          [:a {:href (router/path-for :sign-up)
               :on-click #(rf/dispatch [:set-active-page :sign-up])}
           "New to Cheffy? Create an account!"]]
         [:> Box
          [:> Button {:on-click #(rf/dispatch [:log-in @values])}
           "Log in"]]]]])))
