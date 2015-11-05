'use strict';

angular.module('myApp.view4', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view4', {
                    templateUrl: 'view4/view4.html',
                    controller: 'View4Ctrl'
                });
            }])

        .controller('View4Ctrl', function ($http, $scope) {
            $http.get('api/demouser')
                    .success(function (data, status, headers, config) {
                        $scope.data = data;
                    })
                    .error(function (data, status, headers, config) {

                    });

            // $scope.currencies = {};

            $http({
                method: 'GET',
                url: 'api/Currency',
                cache: true
            }).success(function (response) {
                console.log(response);
                $scope.currencies = response;
//                for (var i = 0; i < $scope.currencies.length; i++) {
//                    if ($scope.currencies[i].code === "EUR") {
//                        $scope.fromCurrency = $scope.currencies[i].code;
//                    }
//                    if ($scope.currencies[i].code === "USD") {
//                        $scope.toCurrency = $scope.currencies[i].code;
//                    }
//                }
            }).error(function () {
                console.log("Converting failure");
            });

            $scope.calcConvert = function () {
                var from = $scope.fromCurrency;
                var to = $scope.toCurrency;
                var input = $scope.fromCurrencyVal;
                $scope.toCurrencyVal = input * (from/to);
            }

        });