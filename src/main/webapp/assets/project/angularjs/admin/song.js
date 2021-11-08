app.controller('frameworkCtrl', ['$scope', '$http', '$timeout', '$q', function ($scope, $http, $timeout, $q) {

    $scope.songs = [{}];
    $scope.singers = [{}];
    $scope.albums = [{}];
    $scope.imageEncode = '';
    $scope.flagModal = true;
    $scope.flagDelete = '';
    $scope.songOld = {};
    $scope.new = {};

    $scope.loadList = function () {
        $http.post(preUrl + "/api/song/list", "", {headers: {'Content-Type': 'application/json'}})
            .then(function (response) {
                $scope.songs = response.data;
                if ($scope.songs == null || $scope.songs.length <= 0) {
                    toastr.error("Không có dữ liệu!!");
                }
            }, function (response) {
                // console.log(response);
                toastr.error("Không lấy được dữ liệu!!");
            });

    }

    $scope.loadList();

    $scope.loadSinger = function (id) {
        if (id == undefined || id == null) {
            $http.post(preUrl + "/api/singer/list", "", {headers: {'Content-Type': 'application/json'}})
                .then(function (response) {
                    $scope.singers = response.data;
                    return;
                }, function (response) {
                    // console.log(response);
                    toastr.error("Không lấy được danh sách ca sĩ!!");
                    return;
                });
        }

        var object = {
            "id": id
        }
        // chuyển thằng object ở trên thành json để gửi lên server
        var objectJson = JSON.parse(JSON.stringify(object));

        // $http.post call api server
        $http.post(preUrl + "/api/singer/getOne", objectJson, {headers: {'Content-Type': 'application/json'}})
            .then(function (response) {
                if (response.data != undefined || response.data != null) {
                    $scope.singerOld = response.data;
                    $("#addSongModal").modal("show");
                }
            }, function (response) {
                toastr.error("Có lỗi khi lấy chi tiết bài hát!!");
            });
    }

    $scope.loadSinger(undefined);

    $http.post(preUrl + "/api/album/list", "", {headers: {'Content-Type': 'application/json'}})
        .then(function (response) {
            $scope.albums = response.data;

        }, function (response) {
            toastr.error("Không lấy được danh sách album!!");
        });

    $scope.preAddSong = function () {
        $scope.new = {};
        $scope.songOld = {};
        $scope.flagModal = true;
        $("#addSongModal").modal("show");
    }

    $scope.addSong = function (song) {
        if (song.singer == undefined || song.singer == '') {
            return;
        }
        if (song.name == undefined || song.name == '') {
            return;
        }
        var newSong = {
            "name": song.name,
            // moment convert date to string
            "releaseDate": moment(song.releaseDate).format("DD/MM/YYYY"),
            // Nếu singer != null thì lấy id singer gửi lên server. nếu = null thì gửi null, không lấy  id nữa không lỗi sml
            "singerId": song.singer != undefined ? song.singer.id : null,
            // Y như dòng trên
            "albumId": song.album != undefined ? song.album.id : null,
            "linkFull": song.linkFull,
            "description": song.description,
            "image": $scope.imageEncode,
        }

        // chuyển thằng newSong ở trên thành json để gửi lên server
        var newSongJson = JSON.parse(JSON.stringify(newSong));

        // $http.post call api server
        $http.post(preUrl + "/api/song/addSong", newSongJson, {headers: {'Content-Type': 'application/json'}})
            .then(function (response) {
                $scope.songResponse = response.data;
                toastr.info("Thêm mới thành công !!");
                //đóng modal, cái form hiện để thêm mới
                $("#addSongModal").modal("hide");
                $scope.loadList();
            }, function (response) {
                toastr.error("Thêm mới thất bại!!");
            });
    }

    $scope.preEditSong = function (id) {
        $scope.new = {};
        $scope.flagModal = false;
        var object = {
            "id": id
        }
        // chuyển thằng object ở trên thành json để gửi lên server
        var objectJson = JSON.parse(JSON.stringify(object));

        // $http.post call api server
        $http.post(preUrl + "/api/song/getOne", objectJson, {headers: {'Content-Type': 'application/json'}})
            .then(function (response) {
                if (response.data != undefined || response.data != null) {
                    $scope.new = response.data;

                    $scope.getSingerOfSong($scope.new.id);

                    $scope.getAlbumsOfSong($scope.new.id);

                    $("#addSongModal").modal("show");
                }
            }, function (response) {
                toastr.error("Có lỗi khi lấy chi tiết bài hát!!");
            });
    }

    $scope.getSingerOfSong = function (id) {
        var object = {
            "id": id
        }
        // chuyển thằng object ở trên thành json để gửi lên server
        var objectJson = JSON.parse(JSON.stringify(object));

        $http.post(preUrl + "/api/singer/getSingerBySong", objectJson, {headers: {'Content-Type': 'application/json'}})
            .then(function (response) {
                $scope.new.singer = response.data;

            }, function (response) {
                toastr.error("Không lấy được ca sĩ!!");
            });
    }

    $scope.getAlbumsOfSong = function (id) {
        object = {
            "id": id
        }
        // chuyển thằng object ở trên thành json để gửi lên server
        objectJson = JSON.parse(JSON.stringify(object));

        $http.post(preUrl + "/api/album/getAlbumsBySong", objectJson, {headers: {'Content-Type': 'application/json'}})
            .then(function (response) {
                $scope.new.album = response.data;

            }, function (response) {
                toastr.error("Không lấy được album!!");
            });
    }

    $scope.editSong = function (song) {
        var newSong = {
            "id": song.id,
            "name": song.name,
            // moment convert date to string
            "releaseDate": moment(song.releaseDate).format("DD/MM/YYYY"),
            // Nếu singer != null thì lấy id singer gửi lên server. nếu = null thì gửi null, không lấy  id nữa không lỗi sml
            "singerId": song.singer != undefined ? song.singer.id : null,
            // Y như dòng trên
            "albumId": song.album != undefined ? song.album.id : null,
            "linkFull": song.linkFull,
            "description": song.description,
            "image": $scope.imageEncode,
        }

        // chuyển thằng newSong ở trên thành json để gửi lên server
        var newSongJson = JSON.parse(JSON.stringify(newSong));

        // $http.post call api server
        $http.post(preUrl + "/api/song/editSong", newSongJson, {headers: {'Content-Type': 'application/json'}})
            .then(function (response) {
                $scope.songResponse = response.data;
                toastr.info("Sửa thành công !!");
                $scope.loadList();
                //đóng modal, cái form hiện để thêm mới
                $("#addSongModal").modal("hide");
            }, function (response) {
                toastr.error("Sửa thất bại!!");
            });
        $scope.songOld = {};
    }

    $scope.preDeleteSong = function (id) {
        $scope.flagDelete = id;
        $("#deleteSongModal").modal("show");
    }

    $scope.deleteSong = function () {
        if ($scope.flagDelete == undefined || $scope.flagDelete == null) {
            return;
        }
        if ($scope.flagDelete == '') {
            return;
        }
        var object = {
            "id": $scope.flagDelete
        }

        // chuyển thằng object ở trên thành json để gửi lên server
        var objectJson = JSON.parse(JSON.stringify(object));

        // $http.post call api server
        $http.post(preUrl + "/api/song/delete", objectJson, {headers: {'Content-Type': 'application/json'}})
            .then(function (response) {
                if (response.data != undefined || response.data != null) {
                    toastr.info("Xóa thành công !!");
                    $scope.loadList();
                }
            }, function (response) {
                toastr.error("Xóa thất bại!!");
            });
        //đóng modal
        $("#deleteSongModal").modal("hide");
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

        $("#deleteSong").click(function () {
            $scope.deleteSong();
        })
    });
}]);















