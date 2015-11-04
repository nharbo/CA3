'use strict';

angular.module('myApp.view6', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view6', {
                    templateUrl: 'view6/view6.html'
                });
            }])

        .controller('View6Ctrl', ['$http', '$scope', function ($http, $scope) {

                var createUserObj = {
                    username: $scope.usernameInput,
                    password: $scope.passwordInput
                };

                $http({
                    method: 'POST',
                    url: 'http://localhost:8081/AngSeedServer/api/demouser/create/'  + $scope.usernameInput + "/" + $scope.passwordInput
                }).then(function successCallback(response) {
                    alert (createUserObj);
                    $scope.returnMessage = response;
                }, function errorCallback(response) {
                    alert("Failure message: " + JSON.stringify({reponse: response}));
                    alert(createUserObj);
                });
            }]);
