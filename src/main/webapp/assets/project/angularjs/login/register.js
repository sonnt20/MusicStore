app.controller('frameworkCtrl', ['$scope', '$http', '$timeout', '$q', function ($scope, $http, $timeout, $q) {
    $scope.userMail = '';
    $scope.password = '';

    $scope.registerUser = function () {
        if ($scope.password != $scope.passwordRepeat) {
            toastr.error("Mật khẩu không khớp !!!");
            return;
        }
        var user = {
            //Lấy firstName, passwor, email, password  từ input (webapp/WEB-INF/view/register.jsp)
            "firstName": $scope.firstName,
            "lastName": $scope.lastName,
            "email": $scope.email,
            "password": $scope.password
        }
        var user = JSON.parse(JSON.stringify(user));

        //Call api register (musicstore/controller/api/LoginAPI.java)
        $http.post(preUrl + "/api/register", user, {headers: {'Content-Type': 'application/json'}})
            .then(function (response) {
                if (response.data = null) {
                    toastr.error("Đăng kí thất bại, vui lòng thử lại!!");

                } else if (response.data.id != null && response.data.id != undefined) {
                    toastr.error("Đăng kí thành công!");
                    window.location.href = preUrl + "/login";
                } else {
                    toastr.error("Email đã tồn tại");
                    window.location.href = preUrl + "/login";
                }

            }, function (response) {
                // console.log(response);
                toastr.error("Có lỗi trong quá trình đăng kí, vui lòng thử lại!");
            });
    }

}]);