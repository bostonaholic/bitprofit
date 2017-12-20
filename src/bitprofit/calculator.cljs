(ns bitprofit.calculator)

(def DAYS-IN-A-YEAR 365.25)
(def HOURS-IN-A-DAY 24.00)
(def SECONDS-IN-AN-HOUR (* 60.00 60.00))

(def HOURS-IN-A-YEAR (* DAYS-IN-A-YEAR HOURS-IN-A-DAY))
(def SECONDS-IN-A-YEAR (* HOURS-IN-A-YEAR SECONDS-IN-AN-HOUR))

(defn hash-time
  "the average amount of time to find a single share

  hash-rate: TH/s"
  [difficulty hash-rate]
  (/ (* difficulty (Math/pow 2 32))
     (* hash-rate 1000 1000 1000 1000)))

(defn power-cost-per-year
  "power-consumption: Watts
   electricity-rate: $/kWh"
  [power-consumption electricity-rate] ;; Watts, $/kWh
  (* HOURS-IN-A-YEAR
     (/ power-consumption 1000)
     electricity-rate))

(defn blocks-per-year
  "the average number of blocks to be found in a year"
  [hash-time]
  (/ SECONDS-IN-A-YEAR hash-time))

(defn coins-per-year [block-coins blocks-per-year]
  (* block-coins blocks-per-year))

(defn revenue-per-year [coins-per-year conversion-rate]
  (* coins-per-year conversion-rate))

(defn pool-fees [revenue-per-year pool-rate]
  (* revenue-per-year pool-rate))

(defn profit-per-year [revenue-per-year power-cost-per-year pool-rate]
  (- revenue-per-year power-cost-per-year (pool-fees revenue-per-year pool-rate)))

(defn net-profit [profit-per-year hardware-cost]
  (- profit-per-year hardware-cost))

(defn break-even-days [profit-per-year cost-hardware]
  (cond
    (< profit-per-year 0) -1.00
    (zero? cost-hardware) 0.00
    :else (* DAYS-IN-A-YEAR (/ cost-hardware profit-per-year))))
