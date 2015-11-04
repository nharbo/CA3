'use strict';

angular.module('myApp.view3', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])

        .controller('View3Ctrl', function ($http) {

            var self = this;
//            self.info = {name: "test"};
//            self.test = "test!!";
            self.buttonClicked = false;

            self.searchbutton = function () {
//                alert("in function");
                var searchfield = self.searchfield;
                var option = self.option;
                var country = self.country;
                console.log(option);
                console.log(searchfield);
                console.log(country);


                $http.get('http://localhost:8080/AngSeedServer/api/demouser/getcomp/' + option + '/' + searchfield + '/' + country)
                        .success(function (response) {
//                            alert("success!");
                            console.log(response);
                            console.log(response.name);
                            self.buttonClicked = true;
                            self.info = response;
                        })
                        .error(function (response) {
//                            alert("Error!1!");
                            self.errormessage = "Fejl i søgningen - prøve igen!";
                        });

                self.company = function (input) {
                    MyFactory.getCompany(input);
                };
            }




        });

//        .factory('MyFactory', function ($http) {
//
//            var getCompany = function (input) {
//
//                var country = input;
//                console.log(country);
//
//                $http.get('http://localhost:8080/AngSeedServer/api/demouser/getcomp/name/lego/dk')
////                $http.get('http://localhost:8080/AngSeedServer/api/demouser/getcomp/' + input.option + '/' + input.name + '/' + input.country)
//                        .success(function (response) {
//                            alert("her!");
//                            console.log(response);
//                            
//                        })
//                        .error(function (response) {
//                            alert("Error!1!");
//                        });
//
//
//            };
//
//            return{
//                getCompany: getCompany
//            };
//
//        });
