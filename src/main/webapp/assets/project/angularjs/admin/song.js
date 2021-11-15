app.controller('frameworkCtrl', ['$scope', '$http', '$timeout', '$q', function ($scope, $http, $timeout, $q) {

    $scope.songs = [{}];
    $scope.singers = [{}];
    $scope.albums = [{}];
    $scope.imageEncode = '';
    $scope.flagModal = true;
    $scope.flagDelete = '';
    $scope.songBase64 = '';
    $scope.songOld = {};
    $scope.new = {};
    var folderpath = './music/';

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
            "linkFull":  song.linkFull,
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

        $scope.songBase64 = '';
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
        $scope.songBase64 = '';
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

    var context = new AudioContext();
    var source = null;
    var audioBuffer = null;
// Converts an ArrayBuffer to base64, by converting to string
// and then using window.btoa' to base64.
    var bufferToBase64 = function (buffer) {
        var bytes = new Uint8Array(buffer);
        var len = buffer.byteLength;
        var binary = "";
        for (var i = 0; i < len; i++) {
            binary += String.fromCharCode(bytes[i]);
        }
        return window.btoa(binary);
    };
    var base64ToBuffer = function (buffer) {
        var binary = window.atob(buffer);
        var buffer = new ArrayBuffer(binary.length);
        var bytes = new Uint8Array(buffer);
        for (var i = 0; i < buffer.byteLength; i++) {
            bytes[i] = binary.charCodeAt(i) & 0xFF;
        }
        return buffer;
    };

    function initSound(arrayBuffer) {
        var base64String = bufferToBase64(arrayBuffer);
        var audioFromString = base64ToBuffer(base64String);
        document.getElementById("encodedResult").value = base64String;
        context.decodeAudioData(audioFromString, function (buffer) {
            // audioBuffer is global to reuse the decoded audio later.
            audioBuffer = buffer;
            var buttons = document.querySelectorAll('button');
            buttons[0].disabled = false;
            buttons[1].disabled = false;
        }, function (e) {
            console.log('Error decoding file', e);
        });
    }

    function audioBufferToWav(aBuffer) {
        let numOfChan = aBuffer.numberOfChannels,
            btwLength = aBuffer.length * numOfChan * 2 + 44,
            btwArrBuff = new ArrayBuffer(btwLength),
            btwView = new DataView(btwArrBuff),
            btwChnls = [],
            btwIndex,
            btwSample,
            btwOffset = 0,
            btwPos = 0;
        setUint32(0x46464952); // "RIFF"
        setUint32(btwLength - 8); // file length - 8
        setUint32(0x45564157); // "WAVE"
        setUint32(0x20746d66); // "fmt " chunk
        setUint32(16); // length = 16
        setUint16(1); // PCM (uncompressed)
        setUint16(numOfChan);
        setUint32(aBuffer.sampleRate);
        setUint32(aBuffer.sampleRate * 2 * numOfChan); // avg. bytes/sec
        setUint16(numOfChan * 2); // block-align
        setUint16(16); // 16-bit
        setUint32(0x61746164); // "data" - chunk
        setUint32(btwLength - btwPos - 4); // chunk length

        for (btwIndex = 0; btwIndex < aBuffer.numberOfChannels; btwIndex++)
            btwChnls.push(aBuffer.getChannelData(btwIndex));

        while (btwPos < btwLength) {
            for (btwIndex = 0; btwIndex < numOfChan; btwIndex++) {
                // interleave btwChnls
                btwSample = Math.max(-1, Math.min(1, btwChnls[btwIndex][btwOffset])); // clamp
                btwSample = (0.5 + btwSample < 0 ? btwSample * 32768 : btwSample * 32767) | 0; // scale to 16-bit signed int
                btwView.setInt16(btwPos, btwSample, true); // write 16-bit sample
                btwPos += 2;
            }
            btwOffset++; // next source sample
        }

        let wavHdr = lamejs.WavHeader.readHeader(new DataView(btwArrBuff));
        let wavSamples = new Int16Array(btwArrBuff, wavHdr.dataOffset, wavHdr.dataLen / 2);

        wavToMp3(wavHdr.channels, wavHdr.sampleRate, wavSamples);

        function setUint16(data) {
            btwView.setUint16(btwPos, data, true);
            btwPos += 2;
        }

        function setUint32(data) {
            btwView.setUint32(btwPos, data, true);
            btwPos += 4;
        }
    }

    function wavToMp3(channels, sampleRate, samples) {
        var buffer = [];
        var mp3enc = new lamejs.Mp3Encoder(channels, sampleRate, 128);
        var remaining = samples.length;
        var samplesPerFrame = 1152;
        for (var i = 0; remaining >= samplesPerFrame; i += samplesPerFrame) {
            var mono = samples.subarray(i, i + samplesPerFrame);
            var mp3buf = mp3enc.encodeBuffer(mono);
            if (mp3buf.length > 0) {
                buffer.push(new Int8Array(mp3buf));
            }
            remaining -= samplesPerFrame;
        }
        var d = mp3enc.flush();
        if(d.length > 0){
            buffer.push(new Int8Array(d));
        }

        var mp3Blob = new Blob(buffer, {type: 'audio/mp3'});
        var bUrl = window.URL.createObjectURL(mp3Blob);

        // send the download link to the console
        console.log('mp3 download:', bUrl);

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
        $("#song-file").change(function () {
            var reader = new FileReader();
            reader.onload = function (e) {
                $scope.songBase64 = bufferToBase64(this.result);
            };
            reader.readAsArrayBuffer(document.getElementById("song-file").files[0]);
        })
    });


}]);















