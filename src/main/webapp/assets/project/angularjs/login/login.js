app.controller('frameworkCtrl', ['$scope', '$http', '$timeout', '$q', function ($scope, $http, $timeout, $q) {
    $scope.userMail = '';
    $scope.password = '';

    $scope.login = function () {
        // console.log($scope.userMail);
        var user = {
            //Lấy username vs password từ input (webapp/WEB-INF/view/login.jsp)
            "username": $("#exampleInputEmail").val(),
            "password": $scope.password
        }
        var user = JSON.parse(JSON.stringify(user));

        //Call api check login (musicstore/controller/api/LoginAPI.java)
        $http.post(preUrl + "/api/login", user, {headers: {'Content-Type': 'application/json'}})
            .then(function (response) {

                toastr.info("Đăng nhập thành công!");

                if (response.data.grRoleId == 1) {//1 là gr ADMIN
                    window.location.href = preUrl + "/admin/home"; //Chuyển hướng trang theo controller (musicstore/controller/admin/HomeController.java)
                } else if (response.data.grRoleId == 2) {//2 là gr USER
                    window.location.href = preUrl + "/login";//Chuyển hướng trang theo controller (musicstore/controller/web/HomeController.java)
                }

            }, function (response) {
                // console.log(response);
                toastr.error("Tài khoản hoặc mật khẩu không chính xác!!");
            });
    }

    // $scope.districtChange = function (cityId) {
    //     $scope.listDistrict = [];
    //     $http.get(preUrl + "/common/getListDistrict", {params: {province: cityId}})
    //         .then(function (response) {
    //             if (response.status === 200) {
    //                 $scope.listDistrict = response.data;
    //             }
    //         }, function (response) {
    //             toastr.error("Có lỗi xảy ra, vui lòng thử lại sau!");
    //         });
    // };
}]);