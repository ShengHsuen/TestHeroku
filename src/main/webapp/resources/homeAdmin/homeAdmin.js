'use strict';

angular.module('myApp.homeAdmin', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/homeAdmin', {
    templateUrl: 'resources/homeAdmin/homeAdmin.html',
    controller: 'HomeAdminCtrl'
  });
}])

.controller('HomeAdminCtrl', ['$scope','$http',function($scope,$http) {

}]);