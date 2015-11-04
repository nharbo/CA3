'use strict';

angular.module('myApp.view6', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view6', {
                    templateUrl: 'view6/view6.html',
                    controller: 'View6Ctrl'
                });
            }])

        .controller('View6Ctrl', ['$http', '$scope', function ($http, $scope) {


                $scope.submitUser = function () {

                    $http({
                        method: 'POST',
                        url: 'http://localhost:8080/AngSeedServer/api/unknown/create/' + $scope.usernameInput + "/" + $scope.passwordInput
                    }).then(function successCallback(data) {
                        $scope.returnMessage = data.data;
                    }, function errorCallback(data) {
//                        alert("Failure message: " + JSON.stringify({data: data}));
                    });
                    $scope.usernameInput = '';
                    $scope.passwordInput = '';
                }
            }]);
