'use strict';

angular.module('myApp.view5', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view5', {
                    templateUrl: 'view5/view5.html',
                    controller: 'View5Ctrl'
                });
            }])

        .controller('View5Ctrl', function ($http, $scope) {
            
            //$scope.loggedIn = false;
            //$scope.showMessage = true;


            $http.get('api/demoadmin')
                    .success(function (data, status, headers, config) {
                        $scope.data = data.message;
                    })
                    .error(function (data, status, headers, config) {

                    });
            $http({
                method: 'GET',
                url: 'api/demoadmin/getAllUsers/'
            }).success(function (response) {
                //loggedIn = true;
                $scope.users = response;
            }).error(function (response) {

            });
//            $scope.test = function(){
//                alert('test');
//            };

//            index kommer fra track by index
            $scope.delete = function (index, username) {
                
                $http({
                    method: 'POST',
                    url: 'api/demoadmin/user/' + username
                }).success(function () {
//                    splice opdaterer viewet "on the fly"
                    $scope.users.splice(index, 1);
                    //showMessage = true;
                    $scope.status = "User successfully deleted!";
                }).error(function (response) {
                    //showMessage = true;
                    $scope.status = "User not deleted - try again!"
                });
            };
        });