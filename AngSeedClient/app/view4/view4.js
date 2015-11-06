'use strict';

angular.module('myApp.view4', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view4', {
                    templateUrl: 'view4/view4.html',
                    controller: 'View4Ctrl'
                });
            }])

        .controller('View4Ctrl', function ($http, $scope) {

            $scope.loggedIn = false;

            $http.get('api/demouser')
                    .success(function (data, status, headers, config) {
                        $scope.data = data;
                        if (data.message != "401: You are are not Authenticated - did you log on to the system") {
                            $scope.loggedIn = true;
                        }
                    })
                    .error(function (data, status, headers, config) {

                    });

            // $scope.currencies = {};

            $http({
                method: 'GET',
                url: 'api/Currency',
                cache: true
            }).success(function (response) {
                $scope.currencies = response;
                $scope.currencies.push({code: 'DKK', desc: 'Danske krone', rate: '100'});
//                $scope.fromCurrency = "EUR";
//                $scope.toCurrency = "DKK";
//                $scope.fromCurrencyVal = 1;
//
//                for (var i = 0; i < $scope.currencies.length; i++) {
//                    if ($scope.currencies[i].code === "EUR") {
//                        $scope.fromCurrency = "EUR";
//                    }
//                    if ($scope.currencies[i].code === "USD") {
//                        $scope.toCurrency = "USD";
//                    }
//                }
                console.log($scope.fromCurrency);
            }).error(function () {
                console.log("Converting failure");
            });

            $scope.calcConvert = function () {
                var from = $scope.fromCurrency;
                var to = $scope.toCurrency;
                var input = $scope.fromCurrencyVal;
                $scope.toCurrencyVal = input * (from / to);
            };

        });