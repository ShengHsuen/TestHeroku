'use strict';

angular.module('myApp.signup', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/signup', {
    templateUrl: 'resources/signup/signup.html',
    controller: 'SignUpCtrl'
  });
}])

.controller('SignUpCtrl', ['$scope','$http','$rootScope',function($scope,$http,$rootScope) {

	var canCreate = false; // Verificar si se puede crear

	$scope.navSignin = function(){
		var path = "/writeMe/#/signin";
		window.location.href = path;
	}
	$scope.createAccount = function(){
		$scope.requestObject={
				"pageNumber": 0,
				"pageSize": 0,
				"direction": "",
				"sortBy": [""],
				"searchColumn": "string",
				"searchTerm": "",
				"user": {
					"name" : $scope.name,
					"lastName" : $scope.lastName,
					"author": $scope.nickName,
					"mail" : $scope.mail,
					"birthDay" : $scope.birthDay,
					"password" : $scope.password
				}
		};
		$scope.pConfirm();
		$scope.prepit = false;
		if(canCreate == true){
			$http.post('users/create',$scope.requestObject).success(function(response) {
				$scope.user = {email: $scope.mail};
				
				$http.post('rest/email/confCuenta',$scope.user).success(function (userResponse) {
					if(userResponse.code == 200){
						$scope.navSignin();
					}else{
						alert("invalido");
					}
				}).catch(function(error){
					   $scope.serverDown = function()
						{
						   $rootScope.$broadcast('serverDown');
						}
					   $scope.serverDown();
				   });
			    if($scope.prepit == false){
			        $scope.navSignin();
			       }
			      }).catch(function(error){
					   $scope.serverDown = function()
						{
						   $rootScope.$broadcast('serverDown');
						}
					   $scope.serverDown();
				   });
		      }else{	
		}
		
	}
	$scope.pConfirm = function(){
		if($scope.password.length < 5){
			$scope.ppassword = true;		
		}else{
			$scope.ppassword = false;
		}
		if($scope.password == $scope.passwordConfirm){
			$scope.pconfirm = false;
		}else{
			$scope.pconfirm = true;
		}
		if($scope.password == $scope.passwordConfirm && $scope.password.length > 4){
			canCreate = true;
		}
	}
	
	$scope.navAcceptLegal= function(){
		var path = "/writeMe/#/acceptLegalEstablishment";
		window.open(path);
	}
}]);