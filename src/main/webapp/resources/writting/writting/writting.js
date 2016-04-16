'use strict';

angular.module('myApp.writting', ['ngRoute', 'ngStorage'])

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/writting', {
        templateUrl: 'resources/writting/writting/writting.html',
        controller: 'WrittingCtrl'
    });
 }]).controller('WrittingCtrl', ['$scope','$http', '$localStorage','$rootScope', function($scope,$http,$localStorage,$rootScope) {

		$scope.loadData = function(){
			$scope.contentWithoutTags = $localStorage.showContent;
			$scope.name = $localStorage.nameWritting;
			$scope.cantUsers = $localStorage.cantUsers;
			console.log($localStorage.cantUsers);
			console.log($localStorage.nameWritting);
		}
		$scope.loadData();
		$('.selector').froalaEditor('html.set', $scope.contentWithoutTags);
		
		$scope.date = new Date();
		var anno = $scope.date.getFullYear();
		var mes = $scope.date.getMonth() + 1;
		var dia = $scope.date.getDate();
		var fecha = anno.toString() + "-" + mes.toString() + "-" + dia.toString();

		var publish = false;
		
	    $scope.ppublish = false;
		
		$scope.send = function(){
			$scope.content = $('#edit').val();
			update();
		}

		$('#btnYes').click(function() {
		    // handle deletion here
		  	var id = $('#myModal').data('id');
		  	$('[data-id='+id+']').remove();
		  	$('#myModal').modal('hide');
		});
		
		$scope.send = function(){
			$('html, body').animate( {scrollTop : 0}, 800 );

			$scope.date = new Date();
			var year = $scope.date.getFullYear();
			var month = $scope.date.getMonth() + 1;
			var day = $scope.date.getDate();
			var hour = $scope.date.getHours();
			var minute = $scope.date.getMinutes();
			var second = $scope.date.getSeconds();
			$scope.fecha = year.toString() + "-" + month.toString() + "-" + day.toString();
			$scope.modified = $scope.fecha + " " + hour.toString() + ":" + minute.toString() + ":" + second.toString();

			$scope.modifiedDate = true;
			$scope.notModified = true;
			
			$scope.content = $('#edit').val();
			update();
		}

		$('#myModal').on('show', function() {
			var id = $(this).data('id'),
            	removeBtn = $(this).find('.danger');
		})

		$('.confirm-delete').on('click', function(e) {
			e.preventDefault();

			var id = $(this).data('id');
			$('#myModal').data('id', id).modal('show');
		});

		$('#btnYes').click(function() {
			// handle deletion here
			var id = $('#myModal').data('id');
			$('[data-id=' + id + ']').remove();
			$('#myModal').modal('hide');
		});

    $scope.navHome = function() {
    	$rootScope.$broadcast('home-started');
        var path = "app#/home";
        window.location.href = path;

    };
    
    $scope.navInvitation = function(){
    	var path = "app#/invitation";
        window.location.href = path;
    }
    
    var update = function() {
        $scope.writting = {
            "pageNumber": 0,
            "pageSize": 0,
            "direction": "",
            "sortBy": [""],
            "searchColumn": "string",
            "searchTerm": $scope.name,
            "writting": {
                "name": $scope.name,
                "description": "a",
                "cantUsers": $scope.cantUsers,
                "date": fecha,
                "likes": 0,
                "limit time": "2100-01-01",
                "numMaxCharacters": 10000,
                "numMinCharacters": 30,
                "published": publish,
                "content": $scope.content
            }
        };
        $http.post('rest/protected/writting/editContent', $scope.writting).success(function(response) {

        }).catch(function(error){
 		   $scope.serverDown = function()
			{
			   $rootScope.$broadcast('serverDown');
			}
		   $scope.serverDown();
	   });
    }

    $scope.showPublish = function(){
    	$('html, body').animate( {scrollTop : 0}, 800 );
    	if($scope.ppublish == false){
    		$scope.ppublish = true;
    	}else{
    		$scope.ppublish = false;
    	}	
    };
    
    $scope.publish = function() {
        $scope.writting = {
            "pageNumber": 0,
            "pageSize": 0,
            "direction": "",
            "sortBy": [""],
            "searchColumn": "string",
            "searchTerm": $scope.name,
            "writting": {
                "name": $scope.name,
                "description": "a",
                "cantUsers": $scope.cantUsers,
                "date": fecha,
                "likes": 0,
                "limit time": "2100-01-01",
                "numMaxCharacters": 10000,
                "numMinCharacters": 30,
                "published": publish,
                "content": $scope.content
            }
        };
        
        publish = true;
        $scope.content = $('#edit').val();
        console.log("Published: " + publish + "Fecha: " + fecha);
        $scope.navHome();
        $http.post('rest/protected/writting/publish', $scope.writting).success(function(response) {
            console.log("writting/publish");
        }).catch(function(error){
 		   $scope.serverDown = function()
			{
			   $rootScope.$broadcast('serverDown');
			}
		   $scope.serverDown();
	   });
    }
    
}]);
