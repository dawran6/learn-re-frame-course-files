(ns app.recipes.subs
  (:require [re-frame.core :as rf :refer [reg-sub]]))

(reg-sub
 :drafts
 (fn [db _]
   (let [recipes (vals (get-in db [:recipes]))
         uid (get-in db [:auth :uid])
         filters [#(= (:public? %) false) #(= (:cook %) uid)]]
     (filter (apply every-pred filters) recipes))))

(reg-sub
 :public
 (fn [db _]
   (let [recipes (vals (get-in db [:recipes]))]
     (filter #(= (:public? %) true) recipes))))

(reg-sub
 :saved
 (fn [db _]
   (let [uid (get-in db [:auth :uid])
         saved (get-in db [:users uid :saved])
         recipes (vals (get-in db [:recipes]))]
     (filter #(contains? saved (:id %)) recipes))))

(reg-sub
 :recipe
 (fn [db _]
   (let [active-recipe (get-in db [:nav :active-recipe])]
     (get-in db [:recipes active-recipe]))))

(reg-sub
 :author?
 (fn [db _]
   (let [uid (get-in db [:auth :uid])
         active-recipe (get-in db [:nav :active-recipe])
         recipe (get-in db [:recipes active-recipe])]
     (= uid (:cook recipe)))))

(reg-sub
 :ingredients
 (fn [db _]
   (let [active-recipe (get-in db [:nav :active-recipe])
         ingredients   (get-in db [:recipes active-recipe :ingredients])]
     (->> ingredients
          (vals)
          (sort-by :order)))))

(reg-sub
 :steps
 (fn [db _]
   (let [active-recipe (get-in db [:nav :active-recipe])
         steps   (get-in db [:recipes active-recipe :steps])]
     (->> steps
          (vals)
          (sort-by :order)))))
