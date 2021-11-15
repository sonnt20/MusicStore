app.controller('frameworkCtrl', ['$scope', '$http', '$timeout', '$q', function ($scope, $http, $timeout, $q) {
    $scope.albums = [{}];

    $scope.songs = [{}];

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


}]);