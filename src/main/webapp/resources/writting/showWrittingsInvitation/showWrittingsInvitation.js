'use strict';
angular.module('myApp.showWrittingsInvitation', [ 'ngRoute' , 'ngStorage'])

.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/showWrittingsInvitation', {
		templateUrl : 'resources/writting/showWrittingsInvitation/showWrittingsInvitation.html',
		controller : 'showWrittingsInvitationCtrl'
	});
} ])

.controller('showWrittingsInvitationCtrl',['$scope','$http', '$localStorage','$rootScope',function($scope, $http, $localStorage,$rootScope) {
	
	// Mostrar
	$scope.init = function(){
		$scope.loadData = function(){
			$scope.sessionUser = $localStorage.data;
			/*$scope.usersInvited = $localStorage.usersInvited; 
			console.log($scope.usersInvited);*/
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
	$http.post('users/getWrittingsInvitation',$scope.writting).success(function(response) {
		$scope.writting = response.writtings;
		$scope.getIsOwnerListInvitation();
	}).catch(function(error){
		   $scope.serverDown = function()
			{
			   $rootScope.$broadcast('serverDown');
			}
		   $scope.serverDown();
	   });
	}
	$scope.init();
	
	$scope.getIsOwnerListInvitation = function(){
		$scope.getOwnerListInvitation = {
				"pageNumber" : 0,
				"pageSize" : 0,
				"direction" : "",
				"sortBy" : [ "" ],
				"searchColumn" : "string",
				"searchTerm" : $scope.sessionUser.author,
				"writting" : {}
		};
		$http.post('rest/protected/writting/getOwnerListInvitation',$scope.getOwnerListInvitation).success(function(response) {
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
	    window.location.href = "app#/writtingInvitation"
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
	    window.location.href = "app#/invitation";
    }
	
	$scope.leave = function(writting){
		console.log("Success " + writting.name);
		  $scope.invitation = {"pageNumber": 0,
			        "pageSize": 0,
			        "direction": "",
			        "sortBy": [""],
			        "searchColumn": "string",
			        "searchTerm": $scope.sessionUser.author,
			        "user": $scope.sessionUser,
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
		                "searchTerm": $scope.sessionUser.author,
		                "writting": writting
		            };
		    	$http.post('rest/protected/writting/getOwner',$scope.getOwner).success(function(response) {
		    		$scope.isOwner = response.isOwner;
		    		console.log("isOwner>>> " + $scope.isOwner);
		    	})
		    };
		    
		    $scope.hoverIn = function(){
		        this.hoverEdit = true;
		    };

		    $scope.hoverOut = function(){
		        this.hoverEdit = false;
		    };
		    
		    /*
			 * @author Mildred Guerra
		     *callback for ng-click 'deleteWritting':
		     */
		    $scope.deletingWritting = function (writtingId) {
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
		    
			$scope.users = {
					"pageNumber": 0,
			        "pageSize": 0,
			        "direction": "",
			        "sortBy": [""],
			        "searchColumn": "string",
			        "searchTerm": "",
			        "users": {}
		   }
	       $http.post('rest/protected/invitation/getAll', $scope.users).success(function(response) {
	    	   $scope.user = response.users;
	    	   //$scope.delSessionUser();
	       }).catch(function(error){
			   $scope.serverDown = function()
				{
				   $rootScope.$broadcast('serverDown');
				}
			   $scope.serverDown();
		   });
		    
			$scope.getInviWritting = function(wr){
				$scope.loadW = wr;
				console.log("!!!!!!!" + $scope.loadW);
				$scope.getInvitatedUsers();
			}
			$scope.getInvitatedUsers = function(){
				  $scope.invitation = {"pageNumber": 0,
					        "pageSize": 0,
					        "direction": "",
					        "sortBy": [""],
					        "searchColumn": "string",
					        "searchTerm": $scope.sessionUser.author,
					        "user": {},
					        "owner": {},
					        "writting": $scope.loadW
					        };
				     $http.post('rest/protected/invitation/getUsersInvited', $scope.invitation).success(function(response) { 
						   console.log("Invitation Success" + $scope.sessionUser.author);
					  	   $scope.usersInvited = response.usersInvited;
					  	   //Me trae bien las personas que he invitado
					  	   console.log("UsersInvited>>>"+$scope.usersInvited);
					  	 console.log("CANT USERS "+$scope.usersInvited.length);
					  	   $scope.cantUsers = $scope.cantUsers - $scope.usersInvited.length;
					  	   $scope.valUsersInvited();
				     });
				     $scope.valUsersInvited = function(){
				     	// No esta haciendo el splice bien
				     	
				     	$scope.usersInvited.sort();
				     	for(var i=0;i<$scope.user.length;i++){
				     		//console.log("Entre valUsersInvited" + $scope.user[i].author);
				     		for(var j=0;j<$scope.usersInvited.length;j++){
				         		if($scope.user[i].author === $scope.usersInvited[j]){
				         			console.log($scope.user[i].author);
				         			console.log($scope.usersInvited[j]);
				         			$scope.user.splice(i,1);
				         		}
				     		}
				     	}
				     };
			}
		    

} ]);