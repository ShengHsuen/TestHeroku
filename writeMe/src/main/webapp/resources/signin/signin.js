'use strict';

angular.module('myApp.signin', ['ngRoute', 'ngStorage'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/signin', {
    templateUrl: 'resources/signin/signin.html',
    controller: 'SignInCtrl'
  });
}])

.controller('SignInCtrl', ['$scope','$http', '$localStorage','$rootScope',function($scope,$http,$localStorage,$rootScope) {
	$scope.accountInvalid = false;
	
	var userNull = function(){
		$scope.user = null;
		$localStorage.data = null;
	}
	userNull();
	
	$scope.navResetPass= function(){
		  var path = "/writeMe/#/resetPassword";
		  window.location.href = path;
		 }
	
	$scope.navSignup = function(){
		  var path = "/writeMe/#/signup";
		  window.location.href = path;
		 }
	
	
	$scope.login = function(){
		$http.post('rest/signin/checkuser',$scope.user).success(function (loginResponse) {
    		if(loginResponse.code == 200){
    			var user = {"userId":loginResponse.idUser,"name":loginResponse.name,"lastName":loginResponse.lastName, "author":loginResponse.author, "admin":loginResponse.admin};
    			console.log(user);
    			
    			$scope.saveData = function(){
    				$scope.user = {"userId":loginResponse.idUser,"name":loginResponse.name,"lastName":loginResponse.lastName, "author":loginResponse.author, "admin":loginResponse.admin};
    			    $localStorage.data = $scope.user;
    			}
    			$scope.saveData();
    			
    			if(loginResponse.admin == false){
    				var path = "/writeMe/app#/home";
        			window.location.href = path;
    			}else{
    				var path = "/writeMe/appAdmin#/homeAdmin";
        			window.location.href = path;
    			}
    			
    		}else{
    			$scope.accountInvalid = true;
    		}
    	}).catch(function(error){
 		   $scope.serverDown = function()
			{
			   $rootScope.$broadcast('serverDown');
			}
		   $scope.serverDown();
	   });
	}
	
}]);