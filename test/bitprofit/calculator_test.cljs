(ns bitprofit.calculator-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [bitprofit.calculator :as calc]))

(deftest break-even-days-test
  (testing "negative break even when the profit for the year is negative"
    (is (= -1.00 (calc/break-even-days -1 0))))

  (testing "break even on day 0 when cost of hardware is zero"
    (is (= -0.00 (calc/break-even-days 1 0))))

  (testing "break even days"
    (is (= 36.525 (calc/break-even-days 10000 1000)))))
