'use strict';

angular.module('myApp.invitation', ['ngRoute', 'ngStorage'])

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/invitation', {
        templateUrl: 'resources/invitation/invitation.html',
        controller: 'InvitationCtrl'
    });
 }])
 
 .controller('InvitationCtrl', ['$scope','$http', '$localStorage','$rootScope', function($scope,$http,$localStorage,$rootScope) {
	 
	$scope.init = function(){
		
		$scope.saveData = function(){
			/*params: {mainWritting : mainWr};*/
		    $localStorage.usersInvited = $scope.usersInvited;
		};
		
		$scope.loadData = function(){
			$scope.contentWithoutTags = $localStorage.showContent;
			$scope.name = $localStorage.nameWritting;
			$scope.cantUsers = $localStorage.cantUsers;
			$scope.sessionUser = $localStorage.data;
			$scope.writtingId = $localStorage.writtingId;
			$scope.writtingload = $localStorage.writting;
			$scope.fil = '';
			$scope.guests = [];
			$scope.user = [];
			while($scope.guests.length > 0) {
				$scope.guests.pop();
			}
			while($scope.user.length > 0) {
				$scope.user.pop();
			}
			$scope.required = false;
		}
		$scope.loadData();
		
		$scope.delSessionUser = function(){
			for (var i = 0; i < $scope.user.length; i++) {
				if($scope.user[i].author == $scope.sessionUser.author){
					$scope.user.splice(i,1);
				}
			}
		}
		
		$scope.addGuest = function(item){
			$scope.cantUsers --;
			$scope.user.splice($scope.functiontofindIndexByKeyValue($scope.user, 'author', item.author), 1);
		    $scope.guests.push(item);
		    console.log(item.author);
		}
		$scope.takeoutGuest = function(item){
			$scope.cantUsers ++;
			$scope.guests.splice($scope.functiontofindIndexByKeyValue($scope.guests, 'author', item.author), 1);
		      $scope.user.push(item);
		      console.log(item.author);
		}
		
	    $scope.functiontofindIndexByKeyValue = function (arraytosearch, key, valuetosearch) {
	        for (var i = 0; i < arraytosearch.length; i++) {
	          if (arraytosearch[i][key] == valuetosearch) {
	            return i;
	          }
	        }
	          return null;
	      }
	};
	$scope.init();

    $scope.sendInvitation = function(){
    	console.log($scope.guests);
		$scope.guestsRequest = {
				  "pageNumber": 0,
				  "pageSize": 0,
				  "direction": "string",
				  "sortBy": [
				    "string"
				  ],
				  "searchColumn": "string",
				  "searchTerm": "string",
				  "user": {},
				  "luser": $scope.guests,
				  "email": "string"
				}
	     $http.post('rest/protected/invitation/sendInvitation', $scope.guestsRequest).success(function(response) {
	    	 $scope.findWritting();
	     }).catch(function(error){
			   $scope.serverDown = function()
				{
				   $rootScope.$broadcast('serverDown');
				}
			   $scope.serverDown();
		   });
    }
    $scope.findWritting = function(){
		  $http({ url: 'rest/protected/invitation/findWritting', 
              method: 'POST', 
              params: {writtingId: $scope.writtingId}
		  }).success(function() {
			  $scope.createInvitation();
		  });
		  
   }
    $scope.createInvitation = function(){
		$scope.userHasWritting={
				  "pageNumber": 0,
				  "pageSize": 0,
				  "direction": "string",
				  "sortBy": [
				    "string"
				  ],
				  "searchColumn": "string",
				  "searchTerm": $scope.sessionUser.author,
				  "userHasWritting": {
				      "statusColor": false,
				      "user_has_writtingId": 0,
				      "linkInvitation": $scope.writtingId,
				      "banned": false,
				      "invitationStatus": false,
				      "owner": false
				}
		};
	    $http.post('rest/protected/invitation/createInvitation', $scope.userHasWritting).success(function(response) {
	    	console.log("Success");
    	    $rootScope.$broadcast('invitation-send');
    	    var path = "app#/showWrittingsInvitation";
    	    window.location.href = path;
	    	    
	    }).catch(function(error){
			   $scope.serverDown = function()
				{
				   $rootScope.$broadcast('serverDown');
				}
			   $scope.serverDown();
		   });
    }
    
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
    	 $scope.delSessionUser();
     }).catch(function(error){
		   $scope.serverDown = function()
			{
			   $rootScope.$broadcast('serverDown');
			}
		   $scope.serverDown();
	   });
	
	$scope.getInvitatedUsers = function(){
		  $scope.invitation = {"pageNumber": 0,
			        "pageSize": 0,
			        "direction": "",
			        "sortBy": [""],
			        "searchColumn": "string",
			        "searchTerm": $scope.sessionUser.author,
			        "user": {},
			        "owner": {},
			        "writting": $scope.writtingload
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
		     }
	}
	$scope.getInvitatedUsers();
	
 }]);