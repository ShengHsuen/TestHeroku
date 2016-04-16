'use strict';
angular.module('myApp.showWrittingsPublic', [ 'ngRoute' , 'ngStorage'])

.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/showWrittingsPublic', {
		templateUrl : 'resources/writting/showWrittingsPublic/showWrittingsPublic.html',
		controller : 'showWrittingsPublicCtrl'
	});
} ])

.controller('showWrittingsPublicCtrl',['$scope','$http', '$localStorage','$rootScope',function($scope, $http, $localStorage,$rootScope) {
	
	// Mostrar
	$scope.init = function(){
		$scope.loadData = function(){
			$scope.sessionUser = $localStorage.data;
			$scope.writtingLoad = $localStorage.writting;
		}
		$scope.loadData();
	$scope.writting = [];
	$scope.writting = {
			"pageNumber" : 0,
			"pageSize" : 0,
			"direction" : "",
			"sortBy" : [ "" ],
			"searchColumn" : "string",
			"searchTerm" : " ",
			"writting" : {}
	};
	$http.post('users/getWrittingsPublic',$scope.writting).success(function(response) {
		$scope.writting = response.writtings;
		$scope.getIsOwnerListPublic();
	}).catch(function(error){
		   $scope.serverDown = function()
			{
			   $rootScope.$broadcast('serverDown');
			}
		   $scope.serverDown();
	   });
	}
	$scope.init();
	
	$scope.getIsOwnerListPublic = function(){
		$scope.getOwnerListPublic = {
				"pageNumber" : 0,
				"pageSize" : 0,
				"direction" : "",
				"sortBy" : [ "" ],
				"searchColumn" : "string",
				"searchTerm" : $scope.sessionUser.author,
				"writting" : {}
		};
		$http.post('rest/protected/writting/getOwnerListPublic',$scope.getOwnerListPublic).success(function(response) {
			$scope.isOwnerList = response.isOwnerList;
			$scope.isOwnerList.reverse();
			console.log($scope.isOwnerList);
		})
	};
	
	$scope.saveData = function(mainWr, name, cantUsers, writtingId, writting){
		params: {mainWritting : mainWr};
	    params: {name : name};
		params: {cantUsers : cantUsers};
		params: {writtingId : writtingId};
		params: {writting : writting};
	    $localStorage.mainWritting = mainWr;
	    $localStorage.nameWritting = name;
	    $localStorage.cantUsers = cantUsers;
	    $localStorage.writtingId = writtingId;
	    $localStorage.writting = writting;
	    window.location.href = "app#/writtingPublic"
	};
	
	$scope.navInvitation = function(mainWr, name, cantUsers, writtingId, writting){
		params: {mainWritting : mainWr};
	    params: {name : name};
		params: {cantUsers : cantUsers};
		params: {writtingId : writtingId};
		params: {writting : writting};
	    $localStorage.mainWritting = mainWr;
	    $localStorage.nameWritting = name;
	    $localStorage.cantUsers = cantUsers;
	    $localStorage.writtingId = writtingId;
	    $localStorage.writting = writting;
	    window.location.href = "app#/writtingPublic";
    }
	
	$scope.leave = function(writting){
		console.log("Success " + writting.name);
		  $scope.invitation = {"pageNumber": 0,
			        "pageSize": 0,
			        "direction": "",
			        "sortBy": [""],
			        "searchColumn": "string",
			        "searchTerm": $scope.user.author,
			        "user": $scope.user,
			        "owner": {},
			        "writting": writting 
			        };
		  $http.post('rest/protected/invitation/refuseInvitation', $scope.invitation).success(function(response) {
			  console.log("Success");
			  $scope.init();
		  });
	}
	
	  $scope.$on('invitation-started', function(event, args) {
		  $scope.init();
	  });

		    $scope.valOwner = function(writting){
		        $scope.getOwner = {
		                "pageNumber": 0,
		                "pageSize": 0,
		                "direction": "",
		                "sortBy": [""],
		                "searchColumn": "string",
		                "searchTerm": $scope.user.author,
		                "writting": writting
		            };
		    	$http.post('rest/protected/writting/getOwner',$scope.getOwner).success(function(response) {
		    		$scope.isOwner = response.isOwner;
		    		console.log("isOwner>>> " + $scope.isOwner);
		    	})
		    };
		    
		    
		    /*
			 * @author Mildred Guerra
		     *callback for ng-click 'deleteWritting':
		     */
		    $scope.deleteWritting = function (writtingId) {
		    	console.log("esta vara es el id del wriiting "+ writtingId)
		    			  $http({ url: 'rest/protected/writting/delete', 
		    	                method: 'DELETE', 
		    	                params: {writtingId: writtingId}
		    			  }).success(function() {
		    					$scope.init();
		    			    }).catch(function(error){
		    					   $scope.serverDown = function()
		    						{
		    						   $rootScope.$broadcast('serverDown');
		    						}
		    					   $scope.serverDown();
		    				   });
		   }
		    $scope.$on('invitation-send', function(event, args) {
		        $scope.init();
		       });

} ]);