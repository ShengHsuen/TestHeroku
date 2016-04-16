'use strict';

angular.module('myApp.home', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/home', {
    templateUrl: 'resources/home/home.html',
    controller: 'HomeCtrl'
  });
}])

.controller('HomeCtrl', ['$scope','$http','$rootScope', '$localStorage', function($scope,$http,$rootScope, $localStorage) {
	var num = 0;
	$scope.loadData = function(){
		$scope.sessionUser = $localStorage.data;
	}
    

	$scope.getIsOwnerList = function(){
		$scope.getOwnerList = {
				"pageNumber" : 0,
				"pageSize" : 0,
				"direction" : "",
				"sortBy" : [ "" ],
				"searchColumn" : "string",
				"searchTerm" : $scope.sessionUser.author,
				"writting" : {}
		};
		$http.post('rest/protected/public/getOwnerList',$scope.getOwnerList).success(function(response) {
			$scope.isOwnerList = response.isOwnerList;
			$scope.isOwnerList.reverse();
			console.log("Home: " + $scope.isOwnerList);
		})
	};
	
   $scope.init = function(){
	   $scope.loadData();
	   
	  $scope.writting = [];
	  $scope.user = [];
	  $scope.writting = {"pageNumber": 0,
	        "pageSize": 0,
	        "direction": "",
	        "sortBy": [""],
	        "searchColumn": "string",
	        "searchTerm": "",
	        "user": {},
	        "writting": {} 
	        };
	  $http.post('rest/protected/writting/getPublished',$scope.writting).success(function(response){
	   console.log("home.js");
	   $scope.writting = response.writting;
	   $scope.owners = response.user;
	   console.log($scope.owners);
	   $scope.getIsOwnerList();
	   
	  //$scope.filterForPublic();
	  // $scope.user = response.user;
	  }).catch(function(error){
		   $scope.serverDown = function()
			{
			   $rootScope.$broadcast('serverDown');
			}
		   $scope.serverDown();
	   });
  }
  
  $scope.$on('home-started', function(event, args) {
	  $scope.init();
  });
  
  $scope.init();
	
	$scope.viewWritting = function(id){
		params: {idWritting : id}
	    $localStorage.idWritting=id;
	    window.location.href = "app#/viewWritting"
	};
	
    $scope.publicColaborate = function(wr, $index){
    	console.log("public colaborate "+wr.name);
		$scope.userHasWritting={
				  "pageNumber": 0,
				  "pageSize": 0,
				  "direction": "string",
				  "sortBy": [
				    "string"
				  ],
				  "searchColumn": "string",
				  "searchTerm": $scope.owners[$index].author,
				  "userHasWritting": {
				      "statusColor": false,
				      "user_has_writtingId": 0,
				      "linkInvitation": $scope.writtingId,
				      "banned": false,
				      "invitationStatus": false,
				      "owner": false,
				      "writting": wr,
				      "user": $scope.sessionUser
				}
		};
	   $http.post('rest/protected/public/createPublic', $scope.userHasWritting).success(function(response) {
	    	console.log("Success");
	    	$scope.init();
    	   /* $rootScope.$broadcast('invitation-send');
    	    var path = "app#/showWrittingsInvitation";
    	    window.location.href = path;*/
	    }).catch(function(error){
			   $scope.serverDown = function()
				{
				   $rootScope.$broadcast('serverDown');
				}
			   $scope.serverDown();
		   });
    }

    $scope.show;
    $scope.getContributors = function(wr,$index){
        $scope.getContrib = {
                "pageNumber": 0,
                "pageSize": 0,
                "direction": "",
                "sortBy": [""],
                "searchColumn": "string",
                "searchTerm": "",
                "writting": wr
            };
        $http.post('rest/protected/public/getContributors',$scope.getContrib).success(function(response) {
	    	$scope.contributors = response.luser;
		    	num = wr.cantUsers - $scope.contributors.length;
		    	wr.cantUsers = num;
		    
	            if(wr.cantUsers == 0){
                  $scope.isOwnerList[$index] = false;
	             }
	            
	         
	    })
    };

	$scope.reportWritting = function(writting){
		params: {Writting : writting}
	    $localStorage.Writting=writting;
	    window.location.href = "app#/reportWritting"
	};

  
   
  
    

}]);