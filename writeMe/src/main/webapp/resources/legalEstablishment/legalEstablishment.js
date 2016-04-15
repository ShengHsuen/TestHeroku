'use strict';
var i = 1;
//@author Mildred Guerra

angular.module('myApp.legalEstablishment', [ 'ngRoute' ])

.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/legalEstablishment', {
		templateUrl : 'resources/legalEstablishment/legalEstablishment.html',
		controller : 'legalEstablishmentCtrl'
	});
} ]).controller('legalEstablishmentCtrl',['$scope','$http','$rootScope',function($scope, $http,$rootScope) {

	$scope.legalList = {};
	$scope.requestObject = []
	$scope.requestObject = {
			"pageNumber" : 0,
			"pageSize" : 0,
			"direction" : "",
			"sortBy" : [ "" ],
			"searchColumn" : "string",
			"searchTerm" : "",
			"legalEstablishment" : {}
	};

	//Get all the rules of legal establishment
	$scope.init = function() {
		$http.post('rest/protected/legal/getAll',
				$scope.requestObject).success(function(response) {
					$scope.legalList = response.legalList;
				});
	}

	$scope.init();
	// Show and hide create rule form
	$scope.myVar = false;
	$scope.toggle = function() {
		$scope.myVar = !$scope.myVar;

		$scope.clean();
	};

	$scope.onError = false;
	// Save rule
	$scope.saveRule = function(event) {
		$scope.requestObject = {
				"legal_establishmentId" : 0,
				"description" : $scope.description,
				"part" : 0,
				"name" : $scope.name
		}

		$http.post('rest/protected/legal/create',
				$scope.requestObject).success(
						function(response) {
							$scope.toggle();
							$scope.init();
							$scope.clean();
						}).catch(function(error){
							   $scope.serverDown = function()
								{
								   $rootScope.$broadcast('serverDown');
								}
							   $scope.serverDown();
						   });
	};
	$scope.clean = function() {
		$scope.description="";
		$scope.name="";
	}
    // callback for ng-click 'deleteLegal':
    $scope.deleteLegal = function (legalId) {

		  $http({ url: 'rest/protected/legal/delete', 
              method: 'DELETE', 
              params: {legal_establishmentId: legalId}
		  }).success(function() {
			$scope.init();
	    });
    	        }

} ]);