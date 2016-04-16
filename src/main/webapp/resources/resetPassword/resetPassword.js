'use strict';
//@author Mildred Guerra

angular.module('myApp.resetPassword', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/resetPassword', {
		templateUrl: 'resources/resetPassword/resetPassword.html',
		controller: 'resetPasswordCtrl'
	});
}])
.controller('resetPasswordCtrl', ['$scope','$http','$rootScope',function($scope,$http,$rootScope) {
	var canCreate = false; // Verificar si se puede crear
	$scope.navSignin = function(){
		var path = "/writeMe/#/signin";
		window.location.href = path;
	}

	//Send the email with a password
	$scope.sendEmail = function(){
		$scope.prepit = false;
		$scope.prepitSucc = false;
		$scope.user = {email: $scope.email};
		$http.post('rest/email/resetPassword',$scope.user).success(function (userResponse) {
			$scope.prepitSucc = true;	
			$scope.navSignin();
			
		 }).catch(function(error){
			    console.log("El correo no existte");
			    $scope.prepit = true;
			   });
	}
} ])