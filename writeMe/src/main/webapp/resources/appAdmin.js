'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngStorage',
  'myApp.home',
  'myApp.legalEstablishment',
  'myApp.viewWritting',
  'ui.bootstrap'
])

.config(['$routeProvider','$provide','$httpProvider', function($routeProvider,$provide,$httpProvider) {
	$routeProvider.otherwise({redirectTo: '/home'});
}])

.controller('mainAdminCtrl', ['$scope','$http', '$localStorage',function($scope,$http,$localStorage) {
	
	$scope.load = function(){	
		$scope.user = $localStorage.data;
		console.log($scope.user);
	}
	$scope.load();
	
	
	if($scope.user == null && $localStorage.data == null ){
		var path = "/writeMe/#/signin";
		  window.location.href = path;
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