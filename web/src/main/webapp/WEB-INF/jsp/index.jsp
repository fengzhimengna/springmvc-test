<!DOCTYPE html>
<html >
<head>
<meta charset="UTF-8">
<title>用户界面</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css"></link>
<link rel="stylesheet" href="resources/css/senna.css"></link>
<!-- <script type="text/javascript" src="resources/js/lib/angular.js"></script>
<script type="text/javascript" src="resources/js/lib/angular-route.js"></script>
<script type="text/javascript" src="resources/js/lib/angular-resource.js"></script> -->
<!-- <script data-baseurl="resources/js" data-main="resources/js/main.js" src="resources/js/lib/require.js" id="main"></script> -->
<script src="resources/js/lib/senna-debug.js"></script>
</head>
<body  >
  <!-- Content surface is the entire body -->
    <a href="/user/list.html">Page 1</a>
    <a href="/user/detail.html">Page 2</a>
  <!-- End of content surface -->
  <div id="content" data-senna-surface >
  fsfasaf
  </div>
  <script>
    var app = new senna.App();
    app.setBasePath('/resources/tpl/');
    app.addSurfaces('content');
    app.addRoutes(new senna.Route(/\w+\.html/, senna.HtmlScreen));
  </script>
  
</body>
</html>