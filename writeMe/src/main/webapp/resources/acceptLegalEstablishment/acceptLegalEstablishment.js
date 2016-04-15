'use strict';
var i = 1;
//@author Mildred Guerra
angular.module('myApp.acceptLegalEstablishment', ['ngRoute'])

.config(['$routeProvider',function($routeProvider) {
	$routeProvider.when('/acceptLegalEstablishment',{
		templateUrl : 'resources/acceptLegalEstablishment/acceptLegalEstablishment.html',
		controller : 'acceptLegalCtrl'
	});
}])
.controller('acceptLegalCtrl',['$scope','$http','$rootScope',function($scope, $http,$rootScope) {

	$scope.navSignup = function(){
		var path = "/writeMe/#/signup";
		window.location.href = path;
	}
	//Get all the rules of legal establishment
	$scope.legalList = {};
	$scope.requestObject = []
	$http.post('acceptLegalEstablishment/getAll',
			$scope.requestObject).success(
					function(response) {
						$scope.legalList = response.legalList;
					}).catch(function(error){
						   $scope.serverDown = function()
							{
							   $rootScope.$broadcast('serverDown');
							}
						   $scope.serverDown();
					   });
	$scope.currentPage = 0;
	$scope.pageSize = 5; // Esta la cantidad de registros que deseamos mostrar por página
	$scope.pages = [];
	$scope.configPages = function() {
		   $scope.pages.length = 0;
		   var ini = $scope.currentPage - 4;
		   var fin = $scope.currentPage + 5;
		   if (ini < 1) {
		      ini = 1;
		      if (Math.ceil($scope.legalList.length / $scope.pageSize) > 10) fin = 10;
		      else fin = Math.ceil($scope.legalList.length / $scope.pageSize);
		   } else {
		      if (ini >= Math.ceil($scope.legalList.length / $scope.pageSize) - 10) {
		         ini = Math.ceil($scope.legalList.length / $scope.pageSize) - 10;
		         fin = Math.ceil($scope.legalList.length / $scope.pageSize);
		      }
		   }
		   if (ini < 1) ini = 1;
		   for (var i = ini; i <= fin; i++) {
		      $scope.pages.push({ no: i });
		   }
		   if ($scope.currentPage >= $scope.pages.length)
		      $scope.currentPage = $scope.pages.length - 1;
		};
		$scope.setPage = function(index) {
		   $scope.currentPage = index - 1;
		};
		$scope.configPages();
		
} ])

		.filter('startFromGrid', function() {
			  return function(input, start) {
			        if (!input || !input.length) { return; }
			        start = +start; //parse to int
			        return input.slice(start);
			    }
			});