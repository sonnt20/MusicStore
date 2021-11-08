app.controller('frameworkCtrl', ['$scope', '$http', '$timeout', '$q', function ($scope, $http, $timeout, $q) {

    $scope.albums = [{}];

    $scope.songs = [{}];

    $scope.new = {};

    $scope.old = {};

    $scope.loadList = function () {
        $http.post(preUrl + "/api/album/list", "", {headers: {'Content-Type': 'application/json'}})
            .then(function (response) {
                $scope.albums = response.data;

            }, function (response) {
                toastr.error("Không lấy được dữ liệu!!");
            });
    }

    $scope.loadList();

    $scope.preAddAlbum = function () {
        $scope.new = {};
        $scope.songs = [{}];
        $scope.flagModal = true;
        $("#addAlbumModal").modal("show");
    }

    $scope.viewDetail = function (id) {
        var object = {
            "id": id
        }
        // chuyển thằng object ở trên thành json để gửi lên server
        var objectJson = JSON.parse(JSON.stringify(object));

        // $http.post call api server
        $http.post(preUrl + "/api/album/getOne", objectJson, {headers: {'Content-Type': 'application/json'}})
            .then(function (response) {
                if (response.data != undefined || response.data != null) {

                    $scope.old = response.data;

                    $scope.getSongsOfAlbum($scope.old.id);

                    $("#viewDetailModel").modal("show");
                }
            }, function (response) {
                toastr.error("Có lỗi khi lấy chi tiết album!!");
            });
    }

    $scope.preEditAlbum = function (id) {
        $scope.old = {};
        $scope.songs = [{}];
        $scope.flagModal = false;
        var object = {
            "id": id
        }
        // chuyển thằng object ở trên thành json để gửi lên server
        var objectJson = JSON.parse(JSON.stringify(object));

        // $http.post call api server
        $http.post(preUrl + "/api/album/getOne", objectJson, {headers: {'Content-Type': 'application/json'}})
            .then(function (response) {
                if (response.data != undefined || response.data != null) {

                    $scope.old = response.data;

                    $scope.getSongsOfAlbum($scope.old.id);

                    $("#addAlbumModal").modal("show");
                }
            }, function (response) {
                toastr.error("Có lỗi khi lấy chi tiết album!!");
            });
    }

    $scope.addAlbum = function (album) {
        if (album.name == undefined || album.name == null) {
            return;
        }
        var newAlbum = {
            "name": album.name,
            "description": album.description,
            "image": $scope.imageEncode,
        }

        // chuyển thằng newAlbum ở trên thành json để gửi lên server
        var newAlbumJson = JSON.parse(JSON.stringify(newAlbum));

        // $http.post call api server
        $http.post(preUrl + "/api/album/addAlbum", newAlbumJson, {headers: {'Content-Type': 'application/json'}})
            .then(function (response) {
                $scope.albumResponse = response.data;
                toastr.info("Thêm mới thành công !!");
                $scope.loadList();
                //đóng modal, cái form hiện để thêm mới
                $("#addAlbumModal").modal("hide");
            }, function (response) {
                // console.log(response);
                toastr.error("Thêm mới thất bại!!");
            });
    }

    $scope.getSongsOfAlbum = function (albumId) {
        var object = {
            "id": albumId
        }
        var objectJson = JSON.parse(JSON.stringify(object));

        // $http.post call api server
        $http.post(preUrl + "/api/rls/getSongsInAlbum", objectJson, {headers: {'Content-Type': 'application/json'}})
            .then(function (response) {
                if (response.data != undefined || response.data != null) {

                    $scope.songs = response.data;

                }
            }, function (response) {
                toastr.error("Có lỗi khi lấy danh sách bài hát!!");
            });
    }

    $(document).ready(function () {
        // Hàm convert image sang base 64 (copy trên mạng kk)
        $("#imgId").change(function () {
            var file = document.querySelector(
                'input[type=file]')['files'][0];

            var reader = new FileReader();

            reader.onload = function () {
                var base64String = reader.result.replace("data:", "")
                    .replace(/^.+,/, "");

                $scope.imageEncode = base64String;
            }
            reader.readAsDataURL(file);
        })
    });
}]);