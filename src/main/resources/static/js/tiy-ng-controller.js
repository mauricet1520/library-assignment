angular.module('TIYAngularApp', [])
   .controller('LibraryController', function($scope, $http) {
        $scope.getBooks = function() {
                    console.log("About to go get me some data!");
                    $scope.name = "James";

                    $http.get("/get_books.json")
                        .then(
                            function successCallback(response) {
                                console.log(response.data);
                                console.log("Adding data to scope");
                                $scope.myBook = response.data;
                            },
                            function errorCallback(response) {
                                console.log("Unable to get data");
                            });
                };

                $scope.add_book = function() {
                    console.log("About to add the following book " + JSON.stringify($scope.bookRequest));

                         $http.post("/add_book.json", $scope.bookRequest)
                                                       .then(
                                                           function successCallback(response) {
                                                               console.log(response.data);
                                                               console.log("Adding data to scope");
                                                               $scope.myBook = response.data;
                                                           },
                                                           function errorCallback(response) {
                                                               console.log("Unable to get data");
                                                           });
                                               };

                 $scope.delete_book = function() {
                                     console.log(" to add the following book " + JSON.stringify($scope.bookRequest));

                                          $http.post("/delete_book.json", $scope.bookRequest)
                                                                        .then(
                                                                            function successCallback(response) {
                                                                                console.log(response.data);
                                                                                console.log("Deleting data");
                                                                                $scope.myBook = response.data;
                                                                            },
                                                                            function errorCallback(response) {
                                                                                console.log("Unable to get data");
                                                                            });
                                                                };

            console.log("LibraryController ...");
            $scope.name = "James";
            $scope.bookRequest = {};

            });




