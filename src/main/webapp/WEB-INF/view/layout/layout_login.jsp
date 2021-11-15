<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <script src="<%=request.getContextPath()%>/assets/project/angularjs/angular.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/project/angularjs/angular-sanitize.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/project/angularjs/autocomplete/autocomplete.js"></script>
    <script src="<%=request.getContextPath()%>/assets/project/style/login/js/jquery-3.6.0.min.js"></script>
    <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet"/>
    <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/project/angularjs/autocomplete/autocomplete.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/project/style/login/vendor/fontawesome-free/css/all.min.css">
    <link href="" rel="stylesheet" type="text/css">
    <script src="<%=request.getContextPath()%>/assets/project/angularjs/common.js"></script>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/project/style/login/css/sb-admin-2.min.css">
    <link href="" rel="stylesheet" type="text/css">

    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/project/style/vendor/jquery/jquery.js">
    <link href="" rel="stylesheet" type="text/css">

    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/project/style/vendor/bootstrap/js/bootstrap.bundle.js">
    <link href="" rel="stylesheet" type="text/css">

    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/project/style/vendor/jquery-easing/jquery.easing.js">
    <link href="" rel="stylesheet" type="text/css">

    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/assets/project/style/js/sb-admin-2.js">
    <link href="" rel="stylesheet" type="text/css">

    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
</head>
<script>
    var preUrl = '<%=request.getContextPath()%>';
</script>
<body>
<section class="vbox">
    <%--    <tiles:insertAttribute name="header"/>--%>
    <section>
        <section class="">
            <tiles:insertAttribute name="body"/>
        </section>
    </section>

</section>
</body>

</html>