'use strict';

angular.module('myApp.view2', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.when('/view2', {
              templateUrl: 'view2/view2.html',
              controller: 'View2Ctrl'
            });
          }])

        .controller('View2Ctrl', function ($http, $scope) {
          $http({
            method: 'GET',
            url: 'api/demouser'
          }).then(function successCallback(response) {
            $scope.data = response.data.message;
          }, function errorCallback(response) {
            $scope.error = data.error;
          });


        });