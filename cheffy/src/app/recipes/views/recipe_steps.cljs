(ns app.recipes.views.recipe-steps
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [app.components.modal :refer [modal]]
            [app.components.form-group :refer [form-group]]
            ["@smooth-ui/core-sc" :refer [Box Row Col Button Typography]]
            ["styled-icons/fa-solid/Plus" :refer [Plus]]
            [clojure.string :as str]))

(defn recipe-steps
  []
  (let [initial-values {:id nil :desc ""}
        values         (r/atom initial-values)
        open-modal     (fn [{:keys [modal-name step]}]
                         (rf/dispatch [:open-modal modal-name])
                         (reset! values step))
        save           (fn [{:keys [id desc]}]
                         (rf/dispatch [:upsert-step {:id (or id (keyword (str "step-" (random-uuid))))
                                                     :desc (str/trim desc)}])
                         (reset! values initial-values))]
    (fn []
      (let [{:keys [steps]} @(rf/subscribe [:recipe])
            author?         @(rf/subscribe [:author?])]
        [:> Box {:background-color "white"
                 :border-radius    10
                 :p                2
                 :pt               0}
         [:> Box {:display         "flex"
                  :justify-content "flex-start"}
          [:> Box
           [:> Typography {:variant "h5"
                           :py      15
                           :m       0}
            "Steps"]]
          [:> Box {:my 15
                   :pl 10}
           [:> Button {:variant  "light"
                       :size     "sm"
                       :on-click #(open-modal {:modal-name :step-editor
                                               :step       initial-values})}
            [:> Plus {:size 12}]]]]
         [:> Box
          (for [[_ {:keys [id order desc] :as step}] steps]
            ^{:key id}
            [:> Box {:py 10}
             [:> Row (when author?
                       {:on-click #(open-modal {:modal-name :step-editor
                                                :step       step})
                        :class    "editable"})
              [:> Col {:py 10}
               desc]]])]
         (when author?
           [modal {:modal-name :step-editor
                   :header     "Step"
                   :body       [:<>
                                [:> Row
                                 [form-group {:id     :desc
                                              :label  "Description"
                                              :type   "text"
                                              :values values}]]]
                   :footer     [:<>
                                (when-let [step-id (:id @values)]
                                  [:a {:href     "#"
                                       :on-click #(when (js/confirm "Are you sure?")
                                                    (rf/dispatch [:delete-step step-id]))}
                                   "Delete"])
                                [:> Button {:variant  "light"
                                            :mx       10
                                            :on-click #(rf/dispatch [:close-modal])}
                                 "Cancel"]
                                [:> Button {:on-click #(save @values)}
                                 "Save"]]}])]))))
