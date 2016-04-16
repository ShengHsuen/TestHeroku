<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
<!-- <link rel="stylesheet" href="resources/apphome.css"> -->
<script
	src="resources/bower_components/html5-boilerplate/dist/js/vendor/modernizr-2.8.3.min.js"></script>

<link rel="stylesheet"
	href="resources/libs/assets/animate.css/animate.css" type="text/css" />
<link rel="stylesheet"
	href="resources/libs/assets/font-awesome/css/font-awesome.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="resources/libs/assets/simple-line-icons/css/simple-line-icons.css"
	type="text/css" />
<link rel="stylesheet"
	href="resources/libs/jquery/bootstrap/dist/css/bootstrap.css"
	type="text/css" />

<link rel="stylesheet" href="resources/html/css/font.css"
	type="text/css" />
<link rel="stylesheet" href="resources/html/css/app.css" type="text/css" />
<link rel="stylesheet"
	href="resources/bower_components/angular-ui-grid/ui-grid.min.css">

<!-- CSS PARA EL WYSWYG(TEXTAREA) -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="resources/writting/writting/css/froala_editor.css">
<link rel="stylesheet"
	href="resources/writting/writting/css/froala_style.css">
<link rel="stylesheet"
	href="resources/writting/writting/css/plugins/code_view.css">
<link rel="stylesheet"
	href="resources/writting/writting/css/plugins/image_manager.css">
<link rel="stylesheet"
	href="resources/writting/writting/css/plugins/image.css">
<link rel="stylesheet"
	href="resources/writting/writting/css/plugins/table.css">
<link rel="stylesheet"
	href="resources/writting/writting/css/plugins/video.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/codemirror.min.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="app app-header-fixed" ng-controller="mainAdminCtrl">
		<!-- header -->
		<header id="header" class="app-header navbar" role="menu">
			<!-- navbar header -->
			<div class="navbar-header bg-dark">
				<button class="pull-right visible-xs dk" ui-toggle-class="show"
					target=".navbar-collapse">
					<i class="glyphicon glyphicon-cog"></i>
				</button>
				<button class="pull-right visible-xs" ui-toggle-class="off-screen"
					target=".app-aside" ui-scroll="app">
					<i class="glyphicon glyphicon-align-justify"></i>
				</button>
				<!-- brand -->
				<a href="#/" class="navbar-brand text-lt">
					<span class="hidden-folded m-l-xs">WriteMe</span>
				</a>
				<!-- / brand -->
			</div>
			<!-- / navbar header -->

			<!-- navbar collapse -->
			<div
				class="collapse pos-rlt navbar-collapse box-shadow bg-white-only">
				<!-- buttons -->
				<div class="nav navbar-nav hidden-xs hide">
					<a href="" class="btn no-shadow navbar-btn"
						ui-toggle-class="app-aside-folded" target=".app"> <i
						class="fa fa-dedent fa-fw text"></i> <i
						class="fa fa-indent fa-fw text-active"></i>
					</a> <a href="" class="btn no-shadow navbar-btn" ui-toggle-class="show"
						target="#aside-user"> <i class="icon-user fa-fw"></i>
					</a>
				</div>
				<!-- / buttons -->
				
				
				<!-- SERVIDOR CAIDO -->
				<span ng-controller="ModalDemoCtrl">
          			<script type="text/ng-template" id="myModalContent.html">
            			<div ng-include="'resources/modals/modal.form.html'"></div>
          			</script>
        		</span>	
        		<!-- /SERVIDOR CAIDO -->

				<!-- link and dropdown -->
				<!-- <ul class="nav navbar-nav ">
					<li><a ng-click="navCreate_Writting()"> <i
							class="fa fa-fw fa-plus visible-xs-inline-block"></i> <span
							translate="header.navbar.new.NEW">Crear obra</span> </span>
					</a></li> 
				</ul> -->
				<!-- / link and dropdown -->

				<!-- search form -->
				<form class="navbar-form navbar-form-sm navbar-left shift"
					ui-shift="prependTo" data-target=".navbar-collapse" role="search">
<!-- 					ng-controller="HomeCtrl" -->
				</form>
				<!-- / search form -->

				<!-- nabar right -->
				<ul class="nav navbar-nav navbar-right">

					<li class="dropdown"><a href="" data-toggle="dropdown" class="dropdown-toggle clear" data-toggle="dropdown"> 
						<span class="thumb-sm avatar pull-right m-t-n-sm m-b-n-sm m-l-sm" style="padding-top: 26px;"> <i
								class="on md b-white bottom"></i></span> 
						<span class="">Bienvenido {{user.author}}</span>  
						<b class="caret"></b>
					</a> <!-- dropdown -->
						<ul class="dropdown-menu animated fadeInRight w">
						<li><a href = "/writeMe/#/signin">Cerrar sesión</a></li>
						</ul> <!-- / dropdown --></li>
				</ul>
				<!-- / navbar right -->
			</div>
			<!-- / navbar collapse -->
		</header>
		<!-- / header -->


		<!-- aside -->
		<aside id="aside" class="app-aside hidden-xs bg-dark">
			<div class="aside-wrap">
				<div class="navi-wrap">
					<!-- / user -->

					<!-- nav -->
					<nav ui-nav class="navi clearfix">
						<ul class="nav">
							<li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
								<span></span>
							</li>
							<li><a href="/writeMe/app#/homeAdmin" class="auto"> <span
									class="pull-right text-muted">
								</span> <i class="glyphicon glyphicon-home icon"></i> <span>Inicio</span></li>
							</a>
							</li>

							<li class="line dk"></li>
							<li><a href="appAdmin#/legalEstablishment" class="auto">
									 <i class="glyphicon glyphicon-briefcase"></i> <span>Términos
										y condiciones</span>
							</a></li>

						</ul>
					</nav>
					<!-- nav -->

				</div>
			</div>
		</aside>
		<!-- / aside -->

		<div ng-view></div>


		<!-- footer -->
		<footer id="footer" class="app-footer" role="footer"> </footer>
		<!-- / footer -->
	</div>


	<!-- In production use:
  <script src="//ajax.googleapis.com/ajax/libs/angularjs/x.x.x/angular.min.js"></script>
  -->

	<!-- Del bower y home -->
	<script src="resources/bower_components/jquery/dist/jquery.min.js"></script>
	<script
		src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="resources/bower_components/angular/angular.js"></script>
	<script src="resources/bower_components/angular-route/angular-route.js"></script>
	<script src="resources/appAdmin.js"></script>
	
	<script src="resources/bower_components/ngstorage/ngStorage.min.js"></script>

	<!-- Del template -->
	<script src="resources/bower_components/angular-ui-grid/ui-grid.min.js"></script>
	<script src="resources/libs/jquery/jquery/dist/jquery.js"></script>
	<script src="resources/libs/jquery/bootstrap/dist/js/bootstrap.js"></script>
	<script src="resources/libs/angular/angular-bootstrap/ui-bootstrap-tpls.js"></script>

	<script src="resources/html/js/ui-load.js"></script>
	<script src="resources/html/js/ui-jp.config.js"></script>
	<script src="resources/html/js/ui-jp.js"></script>
	<script src="resources/html/js/ui-nav.js"></script>
	<script src="resources/html/js/ui-toggle.js"></script>
	<script src="resources/html/js/ui-client.js"></script>

	<script src="resources/legalEstablishment/legalEstablishment.js"></script>
	<script src="resources/writting/createWritting/createWritting.js"></script>
	<script src="resources/writting/writting/writting.js"></script>
	<script src="resources/writting/viewWritting/viewWritting.js"></script>
	<script
		src="resources/acceptLegalEstablishment/acceptLegalEstablishment.js"></script>
	<script src="resources/legalEstablishment/legalEstablishment.js"></script>
	<script src="resources/home/home.js"></script>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<!-- DEL WYSWYG(TEXTAREA) -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/codemirror.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/mode/xml/xml.min.js"></script>
	<script type="text/javascript"
		src="resources/writting/writting/js/froala_editor.min.js"></script>
	<script type="text/javascript"
		src="resources/writting/writting/js/plugins/align.min.js"></script>
	<script type="text/javascript"
		src="resources/writting/writting/js/plugins/code_beautifier.min.js"></script>
	<script type="text/javascript"
		src="resources/writting/writting/js/plugins/code_view.min.js"></script>
	<script type="text/javascript"
		src="resources/writting/writting/js/plugins/draggable.min.js"></script>
	<script type="text/javascript"
		src="resources/writting/writting/js/plugins/image.min.js"></script>
	<script type="text/javascript"
		src="resources/writting/writting/js/plugins/image_manager.min.js"></script>
	<script type="text/javascript"
		src="resources/writting/writting/js/plugins/link.min.js"></script>
	<script type="text/javascript"
		src="resources/writting/writting/js/plugins/lists.min.js"></script>
	<script type="text/javascript"
		src="resources/writting/writting/js/plugins/paragraph_format.min.js"></script>
	<script type="text/javascript"
		src="resources/writting/writting/js/plugins/paragraph_style.min.js"></script>
	<script type="text/javascript"
		src="resources/writting/writting/js/plugins/table.min.js"></script>
	<script type="text/javascript"
		src="resources/writting/writting/js/plugins/video.min.js"></script>
	<script type="text/javascript"
		src="resources/writting/writting/js/plugins/url.min.js"></script>
	<script type="text/javascript"
		src="resources/writting/writting/js/plugins/entities.min.js"></script>
</body>
</html>
