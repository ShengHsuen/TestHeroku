'use strict';

angular.module('myApp.writtingPublic', ['ngRoute', 'ngStorage'])

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/writtingPublic', {
        templateUrl: 'resources/writting/writtingPublic/writtingPublic.html',
        controller: 'WrittingPublicCtrl'
    });
 }])
 
 .controller('WrittingPublicCtrl', ['$scope','$http', '$localStorage','$rootScope', function($scope,$http,$localStorage,$rootScope) {

	 	$scope.disableButtons = function() {
	    	$rootScope.$broadcast('disableButtons');
	    };
	 	$scope.disableButtons();
	 
		$scope.loadData = function(){
			$scope.name = $localStorage.nameWritting;
			$scope.writtingload = $localStorage.writting;
			$scope.user = $localStorage.data;
		}
		var num = 0;
		var finalContent ="";
		$scope.loadData();	
        var parti = 1;
		$scope.date = new Date();
		var anno = $scope.date.getFullYear();
		var mes = $scope.date.getMonth() + 1;
		var dia = $scope.date.getDate();
		var fecha = anno.toString() + "-" + mes.toString() + "-" + dia.toString();
		
		var publish = false;
		
	    $scope.ppublish = false;
		
		$scope.finish = function(){
			$scope.content = $('#edit').val();
			updateFinish();
			$rootScope.$broadcast('disableButtonsTrue');
			
			var path = "/writeMe/app#/showWrittingsPublic";
			  window.location.href = path;
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
    
    var createWritting = function() {
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
                "cantUsers": 0,
                "participation": parti,
                "date": fecha,
                "likes": 0,
                "limit time": "2100-01-01",
                "numMaxCharacters": 10000,
                "numMinCharacters": 30,
                "published": publish,
                "content": $scope.content
            }
        };
        $http.post('rest/protected/writting/createWrittingInvitation', $scope.writting).success(function(response) {
				createUserHasWritting();
        }).catch(function(error){
 		   $scope.serverDown = function()
			{
			   $rootScope.$broadcast('serverDown');
			}
		   $scope.serverDown();
	   });
    }
    
    var updateFinish = function() {
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
                "cantUsers": 0,
                "date": fecha,
                "likes": 0,
                "limit time": "2100-01-01",
                "numMaxCharacters": 10000,
                "numMinCharacters": 30,
                "published": publish,
                "content": $scope.content
            }
        };
        $http.post('rest/protected/writting/editContentFinish', $scope.writting).success(function(response) {
				
        }).catch(function(error){
 		   $scope.serverDown = function()
			{
			   $rootScope.$broadcast('serverDown');
			}
		   $scope.serverDown();
	   });
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
                "cantUsers": 0,
                "participation": parti,
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

    var createUserHasWritting = function() {
        $scope.userHasWritting = {
            "pageNumber": 0,
            "pageSize": 0,
            "direction": "string",
            "sortBy": [
                "string"
            ],
            "searchColumn": "string",
            "searchTerm": "string",
            "userHasWritting": {
                "dateModifie": fecha,
                "statusColor": false,
                "user_has_writtingId": 0,
                "linkInvitation": 1,
                "banned": false,
                "dateCreate": fecha,
                "invitationStatus": true
            }
        };
        
        $http.post('rest/protected/writting/createUserHasWritting', $scope.userHasWritting).success(function(response) {

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
    
    
    
$scope.getAllContent = function(){
	$scope.find = {
			"pageNumber" : 0,
			"pageSize" : 0,
			"direction" : "",
			"sortBy" : [ "" ],
			"searchColumn" : "string",
			"searchTerm" : name,
			"writting" : {}
	};
    $http.post('rest/protected/writting/getWrittingInviContent', $scope.find).success(function(response) {
    	  finalContent = response.content;
    }).catch(function(error){
		   $scope.serverDown = function()
		{
		   $rootScope.$broadcast('serverDown');
		}
	   $scope.serverDown();
   })
};
    $scope.publish = function() { 
        $scope.writting = {
        	"content": finalContent,
            "pageNumber": 0,
            "pageSize": 0,
            "direction": "",
            "sortBy": [""],
            "searchColumn": "string",
            "searchTerm": $scope.name,
            "writting": {
                "name": $scope.name,
                "description": "a",
                "cantUsers": 0, // Pasarle cantUsers por parametro tambien porque sino le cae encima
                "date": fecha,
                "likes": 0,
                "limit time": "2100-01-01",
                "numMaxCharacters": 10000,
                "numMinCharacters": 30,
                "content": finalContent
            }
            
        };
        publish = true;
        $scope.navHome();
        $http.post('rest/protected/writting/publish', $scope.writting).success(function(response) {
        }).catch(function(error){
 		   $scope.serverDown = function()
			{
			   $rootScope.$broadcast('serverDown');
			}
		   $scope.serverDown();
	   });
    }
    
    
    var content = "";
    $scope.participation;
    var name= "";
    
	$scope.loadData = function(){
		name = $localStorage.nameWritting;
	}
	$scope.loadData();
	
    
    function actualizar(){
    	$scope.contentLast = {
    			"pageNumber" : 0,
    			"pageSize" : 0,
    			"direction" : "",
    			"sortBy" : [ "" ],
    			"searchColumn" : "string",
    			"searchTerm" : name,
    			"writting" : {}
    	};
    	$http.post('rest/protected/writting/getContentLastWrittingByMain',$scope.contentLast
		  ).success(function(response) {
		     content = response.content;
		     $scope.participation = response.participation;
		     
		     $('#preview').html(content);
      })
    }

    $scope.valOwner = function(){
        $scope.getOwner = {
                "pageNumber": 0,
                "pageSize": 0,
                "direction": "",
                "sortBy": [""],
                "searchColumn": "string",
                "searchTerm": $scope.user.author,
                "writting": $scope.writtingload
            };
    	$http.post('rest/protected/writting/getOwner',$scope.getOwner).success(function(response) {
    		$scope.isOwner = response.isOwner;
    		console.log("isOwner>>> " + $scope.isOwner);
    	})
    };

    $scope.valOwner();
    $scope.getAllContent();
    
    $scope.getContributors = function(){
        $scope.getContrib = {
                "pageNumber": 0,
                "pageSize": 0,
                "direction": "",
                "sortBy": [""],
                "searchColumn": "string",
                "searchTerm": "",
                "writting": $scope.writtingload
            };
	    $http.post('rest/protected/public/getContributors',$scope.getContrib).success(function(response) {
	    	$scope.contributors = response.luser;
	    	console.log("Contributors>>> " + $scope.contributors);
	    	$scope.getCanWrite($scope.contributors);
	    })
    };
    $scope.getContributors();
        
    $scope.userCanWrite = function(){
        $scope.getUserCanWrite = {
                "pageNumber": 0,
                "pageSize": 0,
                "direction": "",
                "sortBy": [""],
                "searchColumn": "string",
                "searchTerm": "",
                "writting": $scope.writtingload
            };
    	$http.post('rest/protected/public/getUserCanWrite',$scope.getUserCanWrite).success(function(response) {
    		$scope.userCanWrite = response.user.author;
    		console.log("CAn WRITEEE " + $scope.userCanWrite );
    	})
    };
    $scope.userCanWrite();
    
    $scope.getCanWrite = function(contributors){
    	console.log(contributors);
    	$scope.canWriteArray = [];
    	for(var i=0;i < contributors.length;i++){
    		if($scope.userCanWrite == contributors[i].author){
    			$scope.canWriteArray.push(true);
    		}else{
    			$scope.canWriteArray.push(false);
    		}
    		console.log($scope.canWriteArray);
    	}
    }
    
    $scope.moveNext = function(){
        $scope.getN = {
                "pageNumber": 0,
                "pageSize": 0,
                "direction": "",
                "sortBy": [""],
                "searchColumn": "string",
                "searchTerm": $scope.userCanWrite,
                "writting": $scope.writtingload
            };
    	$http.post('rest/protected/public/setNext',$scope.getN).success(function(response) {
    		console.log("public/  Success");
    	})
    };
    
    var actu;
    var testing;
    
    function test(){
    	   $(document).ready(function () {
    	        if(window.location.href.indexOf("writtingPublic") > -1) {
    	        	actu = setInterval(actualizar, 2000);
    	        }else{
    	        	clearInterval(actu)
    	        	clearInterval(testing)
    	        
    	        }
    	    })
    };
    
    $scope.contentLastWritting = function(){
    	$scope.contentLast = {
    			"pageNumber" : 0,
    			"pageSize" : 0,
    			"direction" : "",
    			"sortBy" : [ "" ],
    			"searchColumn" : "string",
    			"searchTerm" : name,
    			"writting" : {}
    	};
    	
    	$http.post('rest/protected/writting/getContentLastWrittingByMain',$scope.contentLast
		  ).success(function(response) {
		     content = response.content;
		     $('#preview').html(content);
		     if($scope.userCanWrite == $scope.user.author){
		    	 $scope.divShow = true;
		    	
		     }else{
		    	 $scope.divShow = false;
		      	 $rootScope.$broadcast('disableButtonsTrue');  
		      	 testing = setInterval(test,4000);
		      	 
		     }
		    })
		   
    };
    $scope.contentLastWritting();
}]);
