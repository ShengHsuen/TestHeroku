'use strict';

angular.module('myApp.createWritting', ['ngRoute', 'angularFileUpload', 'ngStorage'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/createWritting', {
    templateUrl: 'resources/writting/createWritting/createWritting.html',
    controller: 'Create_WrittingCtrl'
  });
}])
.controller('Create_WrittingCtrl', ['$scope','$http','$location','$upload', '$localStorage','$rootScope', function($scope,$http,$location,$upload, $localStorage,$rootScope ) {
	
	
	$scope.date = new Date();
	var anno = $scope.date.getFullYear();
	var mes = $scope.date.getMonth() + 1;
	var dia = $scope.date.getDate();
	var fecha = anno.toString() + "-" + mes.toString() + "-" + dia.toString();
	$scope.pimg = false;
	$scope.files = {
			
	};
	/*"src":"http://localhost:8080/writeMe/resources/writtingImages/imageDefault.jpg"
	$('#blah').attr('src', $scope.files.src);
*/
	$scope.typeSelected = "Personal";
	$scope.cateSelected = "Antiguedades y Coleccionables";
		//Variables
		$scope.showCantUsers = false;
		$scope.category =[ "Antiguedades y Coleccionables", "Arquitectura", "Arte","Artes Escénicas", "Autoayuda","Biografía y Autobiografía",
		                   "Calendarios","Casa y Hogar", "Ciencia","Ciencias Políticas","Ciencias Sociales","Cocina, Comida y Vinos",
		                   "Colecciones Literarias","Comics y Novelas Gráficas","Computación e Internet","Crímenes Verdaderos",
		                   "Crítica Literaria","Cuerpo, Mente y Espíritu","Deportes y Recreación","Drama","Educación",
		                   "Estudio de Lenguas Extranjeras","Familia y Relaciones","Ficción","Ficción para Niños","Filosofía","Fotografía",
		                   "Guías de Ayuda","History & Geography","Humor","Jardinería","Juegos","Lengua y Literatura","Leyes",
		                   "Manualidades y Hobbies","Mascotas y Animales","Matemáticas","Medicina","Música","Naturaleza y Aire libre",
		                   "Negocios y Economia","Niños y Jóvenes","Novelties","Papeleria","Poesía","Psicología","Referencia",
		                   "Religión y Espiritualidad","Salud y Bienestar","Tecnología","Transporte","Tweens Fiction","Tweens Nonfiction",
		                   "Viajes","Video y DVD","Young Adult Fiction","Young Adult Nonfiction","Otros"
		                  ];
		$scope.types =["Personal","Por invitación","Pública"];
		
		//Funciones
		$scope.chkIfPersonal = function(){
			if($scope.typeSelected != "Personal"){
				$scope.showCantUsers = true;
				console.log("true");
			}else{
				$scope.showCantUsers = false;
				$scope.cantUsers = 0;
			}
		}
		$scope.chkIfPersonal();
		$scope.navWritting = function(){
			console.log($("#imgInp")[0].value);
			if( $("#imgInp")[0].value != ""){
				createWritting();
				$scope.valInvitados();
			}else{
				console.log("esta aqui");
				 $scope.pimg = true;
				 $('#blah').attr('alt', "");
			}
		}
		
		
		$scope.valInvitados = function(){
			if($scope.cantUsers > 10){
				$scope.cantUsers = 10;
			}
		}
		
		var createWritting = function(){
			$scope.prepit = false;
			$scope.writting={
					"pageNumber": 0,
					"pageSize": 0,
					"direction": "",
					"sortBy": [""],
					"searchColumn": "string",
					"searchTerm": $scope.name,
					"writting": {
					      "date": fecha,
					      "participation": false,
					      "image": "",
					      "cantUsers": $scope.cantUsers,
					      "description": $scope.description,
					      "published": false,
					      "numMinCharacters": 10,
					      "content": "",
					      "category": $scope.cateSelected,
					      "typeWritting": $scope.typeSelected,
					      "limitTime": "2016-05-05",
					      "numMaxCharacters": 10000,
					      "writtingId": 0,
					      "name": $scope.name,
					      "likes": 0
					}
			};
			
			$http.post('rest/protected/writting/create',$scope.writting).success(function(response) {
			    createUserHasWritting();
			    if($scope.prepit == false){
			     $rootScope.$broadcast('show-writtings');
			     var path = "";
			     if($scope.typeSelected == "Personal"){
			    	 path = "app#/showWrittings";
			     }else if($scope.typeSelected == "Por invitación"){
			    	 path = "app#/showWrittingsInvitation";
			     }else{
			    	 path = "app#/showWrittingsPublic";
			     }
			     window.location.href = path;

			    }
			   }).catch(function(error){
				   $scope.serverDown = function()
					{
					   $rootScope.$broadcast('serverDown');
					}
				   $scope.serverDown();
			   });

		}
		var createUserHasWritting = function(){
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
						  "dateModifie": fecha,
					      "statusColor": false,
					      "user_has_writtingId": 0,
					      "linkInvitation": 0,
					      "banned": false,
					      "dateCreate": fecha,
					      "invitationStatus": true,
					      "owner": true
					}	
			};

			$http.post('rest/protected/writting/createUserHasWritting',$scope.userHasWritting).success(function(response) {
				console.log("2");
			}).catch(function(error){
				   $scope.serverDown = function()
					{
					   $rootScope.$broadcast('serverDown');
					}
				   $scope.serverDown();
			   });
		};
		 $scope.onFileSelect = function($files) {
		    	$scope.files = $files;
				for ( var i = 0; i < $scope.files.length; i++) {
	    			var file = $scope.files[i];
	    			$scope.upload = $upload.upload({
	    				url : 'rest/protected/writting/addFiles',
	    				file : file,
	    			}).progress(
	    					function(evt) {
	    						console.log('percent: '+ parseInt(100.0 * evt.loaded / evt.total));
	    					}).success(function(data, status, headers, config) {
	    						// Rent is uploaded successfully
	    						console.log(data);
	    					});
	    	    			//.error(...)
	    	    			//.then(success, error, progress); 
	        		}
		    };
		    function readURL(input) {
		    		
				 $scope.pimg = false;
		        if (input.files && input.files[0]) {
		            var reader = new FileReader();
		            
		            reader.onload = function (e) {
		                $('#blah').attr('src', e.target.result);
		                $('#blah').attr('alt', "Imagen de portada");
		            }
		            
		            reader.readAsDataURL(input.files[0]);
		        }
		    }
		    
		    $("#imgInp").change(function(){
		    	$('#blah').removeClass( "hide");
		        readURL(this);
		    });
		    
}]);
