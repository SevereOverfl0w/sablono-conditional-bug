(def project 'sablono-conditional-bug)
(def version "0.1.0-SNAPSHOT")

(set-env! :resource-paths #{"resources" "src"}
          :source-paths   #{"test"}
          :dependencies   '[[org.clojure/clojure "1.8.0"]
                            [org.clojure/clojurescript "1.8.51"]
                            [org.omcljs/om "1.0.0-alpha34" :exclusions [cljsjs/react cljsjs/react-dom]]
                            [cljsjs/react "15.0.2-0"]
                            [cljsjs/react-dom "15.0.2-0"]
                            [sablono "0.7.1"]
                            [adzerk/boot-test "RELEASE" :scope "test"]
                            [adzerk/boot-reload "0.4.7"]
                            [adzerk/boot-cljs "1.7.228-1"]
                            [pandeiro/boot-http "0.7.3"]])

(require '[adzerk.boot-cljs :refer [cljs]])
(require '[adzerk.boot-reload :refer [reload]])
(require '[pandeiro.boot-http :refer [serve]])


(task-options!
 aot {:namespace   #{'sablono-loop.core}}
 pom {:project     project
      :version     version
      :description "FIXME: write description"
      :url         "http://example/FIXME"
      :scm         {:url "https://github.com/yourname/sablono-loop"}
      :license     {"Eclipse Public License"
                    "http://www.eclipse.org/legal/epl-v10.html"}}
 jar {:main        'sablono-loop.core
      :file        (str "sablono-loop-" version "-standalone.jar")})

(deftask dev
  "Run the project in development"
  []
  (comp
    (serve)
    (watch)
    (cljs)))

(require '[adzerk.boot-test :refer [test]])
