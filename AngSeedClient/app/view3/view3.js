'use strict';

angular.module('myApp.view3', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])

        .controller('View3Ctrl', ["MyFactory", function ($http, MyFactory) {

            var self = this;

            self.company = MyFactory.getCompany();


        }])

        .factory('MyFactory', function () {

            var getCompany = function () {
                
//                var name = input.name;

                $http.get('api/demouser/name/lego/dk')
                        .success(function (response) {
                            return response.data;
//                            this.data = data;
                        })
                        .error(function (response) {

                        });
            };

            return {
                getCompany: getCompany

            };

        });