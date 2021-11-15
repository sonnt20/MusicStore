<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <script charset="UTF-8" src="<%=request.getContextPath()%>/assets/project/angularjs/admin/song.js"></script>

</head>
<!-- Main Content -->
<body>
<%--<section id="song" ng-app="FrameworkBase" ng-controller="frameworkCtrl">--%>
<section id="song" data-ng-app="FrameworkBase" data-ng-controller="frameworkCtrl">
    <div id="content">
        <!-- Begin Page Content -->
        <div class="container-fluid">
            <!-- Page Heading -->
            <h1 class="h3 mb-2 text-gray-800">Song Manager</h1>
            <br>
            <a ng-click="preAddSong()">
                <button class="btn btn-success" style="margin-bottom: 20px;">Add Song</button>
            </a>
            <div class="table-responsive bg-white">
                <table class="table b-t b-light table-bordered table-hover" style="margin-bottom: 0px;">
                    <thead class="bg-gray">
                    <tr>
                        <th class="text-center v-inherit text-dark">STT</th>
                        <th>Image</th>
                        <th>Name</th>
                        <th>file</th>
                        <th>Release date</th>
                        <th>Description</th>
                        <th>GenDate</th>
                        <th>Last update</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-show="songs == null ||  songs.length <= 0">
                        <td colspan="13"
                            style="height: 100%;background-color: #ececec; line-height: 3.429;text-align: center; font-style: italic;">
                            No data
                        </td>
                    </tr>
                    <tr ng-repeat="item in songs track by $index" ng-show="songs != null && songs.length > 0">
                        <td class="text-center v-inherit">{{$index + 1}}
                        </td>
                        <td class="text-left v-inherit">
                            <img style='display:block; width:100px;height:120px;'
                                 src='data:image/jpeg;base64,{{item.image}}'/></td>
                        <td class="text-left v-inherit">{{item.name}}</td>
                        <td class="text-left v-inherit">{{item.linkFull}}</td>
                        <td class="text-left v-inherit">{{item.releaseDate}}</td>
                        <td class="text-left v-inherit">{{item.description}}</td>
                        <td class="text-left v-inherit">{{item.genDate}}</td>
                        <td class="text-left v-inherit">{{item.lastUpdate}}</td>
                        <td class="text-left v-inherit row">
                            <div style="border-radius: 14%; border-collapse: collapse; margin: auto">
                                <a type="button" style="font-size: 9pt; color: limegreen;text-align: center"
                                   ng-click="preEditSong(item.id)">edit
                                </a>
                            </div>
                        </td>
                        <td class="text-left v-inherit row">
                            <div style="border-radius: 10%; border-collapse: collapse;">
                                <a type="button" style="font-size: 9pt; color: red;text-align: center"
                                   ng-click="preDeleteSong(item.id)">delete
                                </a>
                            </div>
                        </td>
                        <%--                                <td class="text-left v-inherit">{{item.lastUpdated| date:'dd/MM/yyyy HH:mm:ss'}}</td>--%>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- /.container-fluid -->
        <div class="modal" id="addSongModal" role="dialog" aria-labelledby="addSongModal" aria-hidden="true"
             data-keyboard="false" data-backdrop="static">
            <div class="modal-dialog"
                 style="width: 80%;max-height:100%;overflow-y: auto;">
                <form id="addSongForm">
                    <div class="modal-content">
                        <div class="modal-header alert-success" style="">
                            <h5 id="myModalNew" style="font-size: 16pt; color: #0a0a0a;text-align: center">ADD NEW
                                SONG</h5>
                        </div>
                        <div class="modal-body" style="">
                            <section>
                                <fieldset class="">
                                    <div class="row">
                                        <label class="col-sm-5 control-label" style="line-height: 30px">Name Song
                                            <span style="color: red" ng-if="flagModal"> (*)</span></label>
                                        <div class="col-md-6">
                                            <input ng-model="new.name" maxlength="100"
                                                   class="form-control" required>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-sm-5" style="line-height: 30px">Release Date
                                            <span style="color: red" ng-if="flagModal"> (*)</span></label>
                                        <div class="col-md-6">
                                            <div class='input-group date' id='datetimepicker1'
                                                 data-date-format="dd/mm/yyyy">
                                                <input type='date' ng-model="new.releaseDate"
                                                       ng-value="new.releaseDate| date:'yyyy-MM-dd'"
                                                       class="form-control"
                                                       required/>
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-sm-5 control-label" style="line-height: 30px">link
                                            <span style="color: red" ng-if="flagModal"> (*)</span></label>
                                        <div class="col-md-6">
<%--                                            <input type="file" id="song-file" maxlength="100" ng-model="new.linkFull"--%>
<%--                                                   class="form-control"--%>
<%--                                                   accept="audio/mp3,audio/*;capture=microphone"--%>
<%--                                                   required>--%>
                                            <input type="text" ng-model="new.linkFull" class="form-control" required>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-sm-5 control-label" style="line-height: 30px">Singer<span
                                                style="color: red" ng-if="flagModal"> (*)</span></label>
                                        <div class="col-md-6" style="line-height: 30px">
                                            <select ng-options="item as item.singerName for item in singers track by item.id"
                                                    style="line-height: 30px" ng-model="new.singer"
                                                    class="form-control" required>
                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-sm-5 control-label" style="line-height: 30px">Album</label>
                                        <div class="col-md-6" style="line-height: 30px">
                                            <select ng-options="item as item.albumName for item in albums track by item.id"
                                                    style="line-height: 30px" ng-model="new.album"
                                                    class="form-control">

                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-sm-5 control-label"
                                               style="line-height: 30px">Description</label>
                                        <div class="col-md-6">
                                <textarea name="new.description" id="new.description" style="width: 100%"
                                          ng-model="new.description"
                                          ng-value="songOld.description" maxlength="500" class="form-control">
                                </textarea>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div class="col-md-5">Image</div>
                                        <div class="col-md-4">
                                            <input type="file" select-ng-files name="imageSong"
                                                   accept="image/png, image/gif, image/jpeg"
                                                   id="imgId"
                                                   ng-model="new.imageSong">
                                        </div>
                                    </div>
                                </fieldset>
                            </section>
                        </div>
                        <div class="modal-footer" style="">
                            <div class="row" style="text-align: center">
                                <button class="btn btn-primary" ng-click="addSong(new)" ng-if="flagModal">Add</button>
                                <button class="btn btn-primary" ng-click="editSong(new)" ng-if="!flagModal">Edit
                                </button>
                                <button type="button" class="btn btn-danger" ng-click="resetFormAdd"
                                        data-dismiss="modal">
                                    Cancel
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</body>
<!-- End of Main Content -->
<!-- Delete Modal-->
<div class="modal fade" id="deleteSongModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Delete?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <%--            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>--%>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" id="deleteSong" data-dismiss="modal">Delete</a>
            </div>
        </div>
    </div>
</div>
