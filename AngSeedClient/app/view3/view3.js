'use strict';

angular.module('myApp.view3', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])

        .controller('View3Ctrl', ["MyFactory", function (MyFactory) {

            var self = this;

            self.company = function (input) {
                return MyFactory.getCompany(input);
            };


        }])

        .factory('MyFactory', function ($http) {

            var getCompany = function (input) {
                
                var country = input;
                console.log(country);

                $http.get('http://localhost:8080/AngSeedServer/api/demouser/getcomp/name/lego/dk')
//                $http.get('http://localhost:8080/AngSeedServer/api/demouser/getcomp/' + input.option + '/' + input.name + '/' + input.country)
                        .success(function (response) {
                            console.log(response);
                        })
                        .error(function (response) {

                        });
            };
            
            return{
                getCompany: getCompany
            }

        });
