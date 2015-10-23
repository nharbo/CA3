'use strict';

angular.module('myApp.view2', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view2', {
    templateUrl: 'view2/view2.html',
    controller: 'View2Ctrl'
  });
}])

.controller('View2Ctrl', function($http,$scope) {
  $http.get('api/demouser')
            .success(function (data, status, headers, config) {
              $scope.data = data.message;
            })
            .error(function (data, status, headers, config) {
             });
 
});