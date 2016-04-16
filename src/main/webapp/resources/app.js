'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngStorage',
  'myApp.home',
  'myApp.legalEstablishment',
  'myApp.acceptLegalEstablishment',
  'myApp.createWritting',
  'myApp.writting',
  'myApp.writtingInvitation',
  'myApp.writtingPublic',
  'myApp.showWrittings',
  'myApp.showWrittingsInvitation',
  'myApp.showWrittingsPublic',
  'myApp.invitation',
  'myApp.viewWritting',
  'myApp.reportWritting',
  'ui.bootstrap',
  'ui.grid'
])

.config(['$routeProvider','$provide','$httpProvider', function($routeProvider,$provide,$httpProvider) {
	$routeProvider.otherwise({redirectTo: '/home'});
}])

.controller('mainCtrl', ['$scope','$http', '$localStorage','$rootScope',function($scope,$http,$localStorage,$rootScope) {
	
	$scope.load = function(){
		$scope.user = $localStorage.data;
		$scope.hoster = $localStorage.hoster;
		$scope.writting = $localStorage.writting;
		console.log($scope.writting);
	}
	$scope.load();
	
	if($scope.user == null && $localStorage.data == null ){
		var path = "/writeMe/#/signin";
		window.location.href = path;
	}	
	
	$scope.init = function(){
		$scope.findInvitations = function(){
			  $scope.invitation = {"pageNumber": 0,
				        "pageSize": 0,
				        "direction": "",
				        "sortBy": [""],
				        "searchColumn": "string",
				        "searchTerm": $scope.user.author,
				        "user": {},
				        "owner": {},
				        "writting": {} 
				        };
			     $http.post('rest/protected/invitation/getInvitationByUser', $scope.invitation).success(function(response) {
			    	   console.log("Invitation Success");
				  	   $scope.writting = response.writting;
				 	   $scope.hoster = response.owner;
				 	   $scope.cantInvitations = $scope.writting.length;
				  	   console.log($scope.writting);
				  	   console.log($scope.hoster);
			     });
		}
		$scope.findInvitations();
		
		$scope.findConfirmations = function(){
			  $scope.confirmation = {"pageNumber": 0,
				        "pageSize": 0,
				        "direction": "",
				        "sortBy": [""],
				        "searchColumn": "string",
				        "searchTerm": $scope.user.author,
				        "user": {},
				        "owner": {},
				        "userAccepted":{},
				        "writting": {} 
				        };
			     $http.post('rest/protected/invitation/getConfirmationByUser', $scope.confirmation).success(function(response) {
			    	   console.log("Invitation Success");
				  	   $scope.writtingConfirmation = response.writting;
				  	   $scope.userConfirmation = response.userAccepted;
				  	   $scope.cantInvitations = $scope.writtingConfirmation.length;
				 	   //$scope.hoster = response.owner;
				  	   console.log("a "+$scope.userConfirmation);
				  	   console.log("b "+$scope.writtingConfirmation);
				  	  // $scope.mergeList();
			     });
		}
		$scope.findConfirmations();
		
		console.log("$scope.cantInvitations "+$scope.cantInvitations);
		
		/*$scope.mergeList = function(){
			for(var i=0;i<$scope.userConfirmation.length;i++){
				$scope.user.push($scope.userConfirmation[i]);
				$scope.hoster.push($scope.writtingConfirmation[i]);
			}
		}*/
	}
	$scope.init();
	
	$scope.accept = function(writting){
		  console.log(writting.writtingId);
			$scope.userHasWritting={
					  "pageNumber": 0,
					  "pageSize": 0,
					  "direction": "string",
					  "sortBy": [
					    "string"
					  ],
					  "searchColumn": "string",
					  "searchTerm": "string",
					  "userHasWritting": {
					      "statusColor": false,
					      "user_has_writtingId": 0,
					      "linkInvitation": writting.writtingId,
					      "banned": false,
					      "invitationStatus": true,
					      "owner": false
					},
	        		"user": $scope.user,
	        		"writting": writting
			};
		  $http.post('rest/protected/invitation/acceptInvitation', $scope.userHasWritting).success(function(response) {
			  console.log("Success");
			  $scope.init();
			  $scope.navShowWrittingInvitation();
		  });
		    $scope.navShowWrittingInvitation = function() {
		    	$rootScope.$broadcast('invitation-started');
		        var path = "app#/showWrittingsInvitation";
		        window.location.href = path;
		    };
	}
	
	$scope.refuse = function(writting){
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
	
	$scope.acceptConfirmation = function(writting, userWhoAccept){
	  console.log(writting.name+" --- "+userWhoAccept);
	  $scope.conf = {"pageNumber": 0,
		        "pageSize": 0,
		        "direction": "",
		        "sortBy": [""],
		        "searchColumn": "string",
		        "searchTerm": userWhoAccept,
		        "writting": writting 
		        };
	  $http.post('rest/protected/public/acceptConfirmation', $scope.conf).success(function(response) {
		  console.log("Success");
		  $scope.init();
	  });
	}
	
}])

.controller('ModalInstanceCtrl', ['$scope', '$modalInstance', 'items', function($scope, $modalInstance, items) {
    $scope.items = items;
    $scope.selected = {
      item: $scope.items[0]
    };

    $scope.ok = function () {
      $modalInstance.close($scope.selected.item);
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }])

.controller('ModalDemoCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log) {
    $scope.items = ['item1', 'item2', 'item3'];
    
    $scope.$on('serverDown', function(event){
    	console.log("SI ENTRAA AL ON");
    	
    	var modalInstance = $modal.open({
            templateUrl: 'myModalContent.html',
            controller: 'ModalInstanceCtrl',
            size: 0,
            resolve: {
              items: function () {
                return $scope.items;
              }
            }
          });

          modalInstance.result.then(function (selectedItem) {
            $scope.selected = selectedItem;
          }, function () {
            $log.info('Modal dismissed at: ' + new Date());
          });
    });
}]); 
