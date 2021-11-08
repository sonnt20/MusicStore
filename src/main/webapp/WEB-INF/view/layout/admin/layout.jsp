<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en" class="app">
<head>

    <%--    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>--%>
    <script src="<%=request.getContextPath()%>/assets/project/angularjs/angular.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/project/angularjs/angular-sanitize.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular-route.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/project/angularjs/autocomplete/autocomplete.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/project/angularjs/autocomplete/autocomplete.css">
    <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet"/>
    <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment-with-locales.js"></script>

    <script src="<%=request.getContextPath()%>/assets/project/angularjs/common.js"></script>

    <link href="<%=request.getContextPath()%>/assets/project/style/admin/vendor/fontawesome-free/css/all.min.css"
          rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<%=request.getContextPath()%>/assets/project/style/admin/css/sb-admin-2.min.css" rel="stylesheet">
    <script>
        var preUrl = '<%=request.getContextPath()%>';
    </script>
</head>

<body>
<!-- Page Wrapper -->
<section id="wrapper">
    <!-- Sidebar -->
    <tiles:insertAttribute name="menu"/>
    <!-- End of Sidebar -->
    <!-- Content Wrapper -->
    <section id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <%--        <tiles:insertAttribute name="home"/>--%>
        <section>
            <!-- Topbar -->
            <tiles:insertAttribute name="topbar"/>
            <!-- End of Topbar -->
            <!-- Begin Page Content -->
            <tiles:insertAttribute name="bodyWeb"/>
            <!-- /.container-fluid -->
        </section>
        <!-- End of Main Content -->
        <!-- Footer -->
        <tiles:insertAttribute name="footer"/>
        <!-- End of Footer -->
    </section>
    <!-- End of Content Wrapper -->
</section>
<!-- End of Page Wrapper -->


</body>
<!-- Bootstrap core JavaScript-->
<%--<script src="<%=request.getContextPath()%>/assets/project/style/admin/vendor/jquery/jquery.min.js"></script>--%>
<script src="<%=request.getContextPath()%>/assets/project/style/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="<%=request.getContextPath()%>/assets/project/style/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="<%=request.getContextPath()%>/assets/project/style/admin/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="<%=request.getContextPath()%>/assets/project/style/admin/vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="<%=request.getContextPath()%>/assets/project/style/admin/js/demo/chart-area-demo.js"></script>
<script src="<%=request.getContextPath()%>/assets/project/style/admin/js/demo/chart-pie-demo.js"></script>

<!-- Page level plugins -->
<script src="<%=request.getContextPath()%>/assets/project/style/admin/vendor/datatables/jquery.dataTables.min.js"></script>

<script src="<%=request.getContextPath()%>/assets/project/style/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="<%=request.getContextPath()%>/assets/project/style/admin/js/demo/datatables-demo.js"></script>
</html>