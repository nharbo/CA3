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
                    }).then(function successCallback(response) {
                        $scope.returnMessage = response;
                    }, function errorCallback(response) {
                        alert("Failure message: " + JSON.stringify({reponse: response}));
                    });
                    $scope.usernameInput = '';
                    $scope.passwordInput = '';
                }
            }]);
