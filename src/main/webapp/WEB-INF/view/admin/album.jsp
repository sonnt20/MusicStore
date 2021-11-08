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

    <script charset="UTF-8" src="<%=request.getContextPath()%>/assets/project/angularjs/admin/album.js"></script>
    <style>
        fieldset.scheduler-border {
            border: 1px groove #ddd !important;
            padding: 0 1.4em 1.4em 1.4em !important;
            margin: 0 0 1.5em 0 !important;
            -webkit-box-shadow: 0px 0px 0px 0px #000;
            box-shadow: 0px 0px 0px 0px #000;
        }

        legend.scheduler-border {
            font-size: 1.2em !important;
            font-weight: bold !important;
            text-align: left !important;
            width: auto;
            padding: 0 10px;
            border-bottom: none;
        }
    </style>
</head>
<!-- Main Content -->
<body>
<section id="song" data-ng-app="FrameworkBase" data-ng-controller="frameworkCtrl">
    <div id="content">
        <!-- Begin Page Content -->
        <div class="container-fluid">
            <!-- Page Heading -->
            <h1 class="h3 mb-2 text-gray-800">Album Manager</h1>
            <br>
            <a ng-click="preAddAlbum()">
                <button class="btn btn-success" style="margin-bottom: 20px;">Add album</button>
            </a>
            <div class="table-responsive bg-white">
                <table class="table b-t b-light table-bordered table-hover" style="margin-bottom: 0px;">
                    <thead class="bg-gray">
                    <tr>
                        <th class="text-center v-inherit text-dark">STT</th>
                        <th>Image</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>GenDate</th>
                        <th>Last update</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-show="albums == null ||  albums.length <= 0">
                        <td colspan="13"
                            style="height: 100%;background-color: #ececec; line-height: 3.429;text-align: center; font-style: italic;">
                            No data
                        </td>
                    </tr>
                    <tr ng-repeat="item in albums track by $index" ng-show="albums != null && albums.length > 0">
                        <td class="text-center v-inherit">{{$index + 1}}
                        </td>
                        <td class="text-left v-inherit">
                            <img style='display:block; width:100px;height:120px;'
                                 src='data:image/jpeg;base64,{{item.image}}'/></td>
                        <td class="text-left v-inherit">{{item.albumName}}</td>
                        <td class="text-left v-inherit">{{item.description}}</td>
                        <td class="text-left v-inherit">{{item.genDate}}</td>
                        <td class="text-left v-inherit">{{item.lastUpdate}}</td>
                        <td class="text-left v-inherit row">
                            <div style="border-radius: 10%; border-collapse: collapse;">
                                <a type="button" style="font-size: 9pt; color: red;text-align: center"
                                   ng-click="viewDetail(item.id)">view
                                </a>
                            </div>
                            <div style="border-radius: 10%; border-collapse: collapse;">
                                <a type="button" style="font-size: 9pt; color: red;text-align: center"
                                   ng-click="preDeleteAlbum(item.id)">delete
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
        <div class="modal" id="addAlbumModal" role="dialog" aria-labelledby="addAlbumModal" aria-hidden="true"
             data-keyboard="false" data-backdrop="static">
            <div class="modal-dialog modal-lg">
                <form id="addSongForm">
                    <div class="modal-content">
                        <div class="modal-header alert-success" style="">
                            <h5 id="myModalNew" style="font-size: 16pt; color: #0a0a0a;text-align: center">ADD NEW
                                ALBUM</h5>
                        </div>
                        <div class="modal-body" style="">
                            <section>
                                <fieldset class="">
                                    <div class="row">
                                        <label class="col-sm-5 control-label" style="line-height: 30px">Album name
                                            <span style="color: red"> (*)</span></label>
                                        <div class="col-md-6">
                                            <input ng-model="new.name" maxlength="100" class="form-control" required>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-sm-5 control-label"
                                               style="line-height: 30px">Description</label>
                                        <div class="col-md-6">
                                <textarea name="new.description" id="new.description" style="width: 100%"
                                          ng-model="new.description" maxlength="500" class="form-control">
                                </textarea>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div class="col-md-5">Image</div>
                                        <div class="col-md-4">
                                            <input type="file" select-ng-files name="imageAlbum"
                                                   accept="image/png, image/gif, image/jpeg"
                                                   id="imgId"
                                                   ng-model="new.imageAlbum">
                                        </div>
                                    </div>
                                </fieldset>
                            </section>
                        </div>
                        <div class="modal-footer" style="">
                            <div class="row" style="text-align: center">
                                <button class="btn btn-primary" ng-click="addAlbum(new)">Add</button>
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
        <div class="modal fade bd-example-modal-xl" id="viewDetailModel" tabindex="-1" role="dialog"
             aria-labelledby="viewDetailModel" aria-hidden="true">
            <div class="modal-dialog modal-xl"
                 style="max-height: 100%;overflow-y: scroll;">
                <form id="viewDetailForm">
                    <div class="modal-content">
                        <div class="modal-header alert-success" style="">
                            <h5 id="myModalView" style="font-size: 16pt; color: #0a0a0a;text-align: center">ALBUM</h5>
                        </div>
                        <div class="modal-body" style="">
                            <div class="row">
                                <div class="col-md-4">
                                    <img style='display:block; width:200px;height:240px;'
                                         src='data:image/jpeg;base64,{{old.image}}'/>
                                </div>
                                <div class="col-md-6">
                                    <input ng-model="old.albumName" maxlength="100" class="form-control" required>
                                </div>
                            </div>
                            <br>

                            <fieldset class="scheduler-border">
                                <legend class="scheduler-border"><label class="col-sm-5 control-label"
                                                                        style="line-height: 30px">Songs: </label>
                                </legend>
                                <div class="control-group">
                                    <div class="controls bootstrap-timepicker">
                                        <table class="table b-t b-light table-bordered table-hover"
                                               style="margin-bottom: 0px;">
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
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr ng-show="songs == null ||  songs.length <= 0">
                                                <td colspan="13"
                                                    style="height: 100%;background-color: #ececec; line-height: 3.429;text-align: center; font-style: italic;">
                                                    No data
                                                </td>
                                            </tr>
                                            <tr ng-repeat="item in songs track by $index"
                                                ng-show="songs != null && songs.length > 0">
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
                                                        <a type="button"
                                                           style="font-size: 9pt; color: limegreen;text-align: center"
                                                           ng-click="preEditSong(item.id)">edit
                                                        </a>
                                                    </div>
                                                    <div style="border-radius: 10%; border-collapse: collapse;">
                                                        <a type="button"
                                                           style="font-size: 9pt; color: red;text-align: center"
                                                           ng-click="preDeleteSong(item.id)">delete
                                                        </a>
                                                    </div>
                                                </td>
                                                <%--                                <td class="text-left v-inherit">{{item.lastUpdated| date:'dd/MM/yyyy HH:mm:ss'}}</td>--%>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <i class="icon-time"></i>
                                    </div>
                                </div>
                            </fieldset>

                            <br>
                            <div class="row">
                                <label class="col-sm-3 control-label"
                                       style="line-height: 30px">Description</label>
                                <div class="col-md-5">
                                <textarea name="old.description" style="width: 100%"
                                          ng-model="old.description" maxlength="500" class="form-control">
                                </textarea>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer" style="">
                            <div class="row" style="text-align: center">
                                <button class="btn btn-primary" ng-click="addAlbum(new)">Edit</button>
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