<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html lang="en" ng-app="myApp" class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html lang="en" ng-app="myApp" class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html lang="en" ng-app="myApp" class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en" ng-app="myApp" class="no-js">
<!--<![endif]-->
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Write Me / Mett</title>
<meta name="description"
	content="app, web app, responsive, responsive layout, admin, admin panel, admin dashboard, flat, flat ui, ui kit, AngularJS, ui route, charts, widgets, components" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet"
	href="resources/bower_components/html5-boilerplate/dist/css/normalize.css">
<link rel="stylesheet"
	href="resources/bower_components/html5-boilerplate/dist/css/main.css">
<link rel="stylesheet"
	href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="resources/appLogin.css"> -->
<script
	src="resources/bower_components/html5-boilerplate/dist/js/vendor/modernizr-2.8.3.min.js"></script>

<link rel="stylesheet"
	href="resources/libs/assets/animate.css/animate.css" type="text/css" />
<link rel="stylesheet"
	href="resources/libs/assets/font-awesome/css/font-awesome.min.css"
	type="text/css" />
<link rel="stylesheet" href="resources/libs/assets/simple-line-icons/css/simple-line-icons.css" type="text/css" />
<link rel="stylesheet" href="resources/libs/jquery/bootstrap/dist/css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="resources/html/css/font.css" type="text/css" />
<link rel="stylesheet" href="resources/html/css/app.css" type="text/css" />
<link rel="stylesheet" href="resources/bower_components/angular-ui-grid/ui-grid.min.css">

</head>
<body>

	<!-- SERVIDOR CAIDO -->
				
				<span ng-controller="ModalDemoCtrl">
          			<script type="text/ng-template" id="myModalContent.html">
            			<div ng-include="'resources/modals/modal.form.html'"></div>
          			</script>
        		</span>	
        		<!-- /SERVIDOR CAIDO -->
	
	<!--[if lt IE 7]>
      <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
  <![endif]-->

	<div ng-view></div>

	<!-- In production use:
  <script src="//ajax.googleapis.com/ajax/libs/angularjs/x.x.x/angular.min.js"></script>
  -->
	<!-- Del bower y signin -->
	<script src="resources/bower_components/jquery/dist/jquery.min.js"></script>
	<script src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="resources/bower_components/angular/angular.js"></script>
	<script src="resources/bower_components/angular-route/angular-route.js"></script>
	<script src="resources/appLogin.js"></script>
	<script src="resources/signin/signin.js"></script>
	<script src="resources/signup/signup.js"></script>
	<script src="resources/bower_components/angular-ui-grid/ui-grid.min.js"></script>
	<script src="resources/acceptLegalEstablishment/acceptLegalEstablishment.js"></script>
	<script src="resources/resetPassword/resetPassword.js"></script>
	  <script src="resources/bower_components/ngstorage/ngStorage.min.js"></script>
	<!-- Del template -->
	<script src="resources/libs/jquery/jquery/dist/jquery.js"></script>
	<script src="resources/libs/jquery/bootstrap/dist/js/bootstrap.js"></script>
	<script src="resources/html/js/ui-load.js"></script>
	<script src="resources/html/js/ui-jp.config.js"></script>
	<script src="resources/html/js/ui-jp.js"></script>
	<script src="resources/html/js/ui-nav.js"></script>
	<script src="resources/html/js/ui-toggle.js"></script>
	<script src="resources/html/js/ui-client.js"></script>
	<script src="resources/libs/angular/angular-bootstrap/ui-bootstrap-tpls.js"></script>

</body>
</html>
