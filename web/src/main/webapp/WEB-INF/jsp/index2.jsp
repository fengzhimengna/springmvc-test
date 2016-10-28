<!DOCTYPE html>
<html >
<head>
<meta charset="UTF-8">
<title>用户界面</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css"></link>
<link rel="stylesheet" href="resources/css/app.css"></link>
<!-- <script type="text/javascript" src="resources/js/lib/angular.js"></script>
<script type="text/javascript" src="resources/js/lib/angular-route.js"></script>
<script type="text/javascript" src="resources/js/lib/angular-resource.js"></script> -->
<script data-baseurl="resources/js" data-main="resources/js/main.js" src="resources/js/lib/require.js" id="main"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<ul class="nav nav-pills nav-stacked">
				   <li><a href="#user/list">用户管理</a></li>
				   <li><a href="#resource/list">资源管理</a></li>
				</ul>
			</div>
			<div class="col-md-10">
				<div ng-view></div>
			</div>
		</div>
	</div>
</body>
</html>