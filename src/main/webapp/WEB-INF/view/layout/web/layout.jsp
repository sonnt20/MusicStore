<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Megapod Template">
    <meta name="keywords" content="Megapod, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <script src="<%=request.getContextPath()%>/assets/project/angularjs/angular.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/project/angularjs/angular-sanitize.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular-route.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>

    <script src="<%=request.getContextPath()%>/assets/project/angularjs/autocomplete/autocomplete.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/project/angularjs/autocomplete/autocomplete.css">

    <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet"/>
    <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/assets/project/style/web/img/download.jpg"
          type="image/x-icon">

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800;900&display=swap"
          rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/project/style/web/css/bootstrap.min.css"
          type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/project/style/web/css/font-awesome.min.css"
          type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/project/style/web/css/elegant-icons.css"
          type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/project/style/web/css/magnific-popup.css"
          type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/project/style/web/css/owl.carousel.min.css"
          type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/project/style/web/css/slicknav.min.css"
          type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/project/style/web/css/style.css" type="text/css">

    <script src="<%=request.getContextPath()%>/assets/project/angularjs/common.js"></script>
    <!-- Custom styles for this template-->
    <script>
        var preUrl = '<%=request.getContextPath()%>';
    </script>
</head>

<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>

<tiles:insertAttribute name="topbar"/>

<tiles:insertAttribute name="bodyWeb"/>

<tiles:insertAttribute name="footer"/>

<!-- Js Plugins -->
<script src="<%=request.getContextPath()%>/assets/project/style/web/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/project/style/web/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/project/style/web/js/jquery.magnific-popup.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/project/style/web/js/mixitup.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/project/style/web/js/jquery.slicknav.js"></script>
<script src="<%=request.getContextPath()%>/assets/project/style/web/js/owl.carousel.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/project/style/web/js/main.js"></script>

<!-- Music Plugin -->
<script src="<%=request.getContextPath()%>/assets/project/style/web/js/jquery.jplayer.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/project/style/web/js/jplayerInit.js"></script>
</body>
</html>