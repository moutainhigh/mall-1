<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script id="eachFile" type="text/x-jquery-tmpl">
        <li class="mix filter-2010 mix_all" data-id="{{= idx}}" style="display: inline-block; opacity: 1;">
            <div class="cmanager-ctrls">
                <label><input type="checkbox" name=""/><span></span></label>

                <div class="btn-group">
                    <a href="#" class="btn btn-default btn-sm"><i class="fa fa-pencil"></i></a>
                    <a href="#" class="btn btn-default btn-sm"><i class="fa fa-comments"></i></a>
                    <a href="#" class="btn btn-default btn-sm media-delete-trigger"><i class="fa fa-trash-o"></i></a>
                </div>
            </div>
            <div class="cmanager-list-thumb">
                     {{if file}}
                        <a href="..${picPath}{{= path}}" class="lightbox" title="{{= fileName}}">
                            <img src="..${picPath}{{= path}}" alt=""/>
                        </a>
                     {{else}}
                        <a href="javascript:naviToDir('{{= path}}');"  title="{{=fileName}}">
                            <img src="../images/files/folder.png" alt=""/>
                        </a>
                     {{/if}}

            </div>
            <div class="cmanager-list-info">
                <h5>{{= fileName}}</h5>
                    <ul>
                        <li><strong>日期:</strong> {{= modifyTime}}</li>
                        {{if file}}
                            <li><strong>尺寸:</strong> {{= width}}*{{= height}}</li>
                            <li><strong>大小:</strong> {{= fileSize}} kb</li>
                          {{else}}
                            <li><strong>&nbsp;</strong></li>
                            <li><strong>&nbsp;</strong></li>
                         {{/if}}
                         <li><input type="radio" name="filePath" value="{{= path}}"/>&nbsp;&nbsp;&nbsp;选择</li>
                    </ul>
            </div>
        </li>

</script>
<div class="cmanager">
    <div class="cmanager-inner clearfix">
        <div class="cmanager-header responsive-helper clearfix">
            <div class="pull-left">
                <a href="#" class="btn btn-default cmanager-addnew-trigger">
                    <i class="fa fa-plus"></i>&nbsp; 上传文件
                </a>

                <div class="btn-group">
                    <a href="#" class="btn btn-default" id="cmanager-delete-trigger"><i class="fa fa-trash-o"></i></a>
                    <%--<a href="#" class="btn btn-default" id="cmanager-check-trigger"><i class="fa fa-check"></i></a>--%>
                    <a href="#" class="btn btn-default" id="cmanager-create-trigger"><i class="fa fa-folder"></i></a>
                </div>
            </div>
            <div class="pull-right">
                <form class="input-group cmanager-search">
                    <input type="text" name="" class="form-control" placeholder="Search here..."/>

                    <div class="input-group-btn">
                        <a href="#" class="clear-input"><i class="fa fa-times-circle"></i></a>
                        <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
                    </div>
                </form>
            </div>
        </div>
        <div class="cmanager-content clearfix">
            <div class="cmanager-sidebar" style="min-height: 500px">
                <ul class="ext-tabs-cmanager">
                    <li class="active">
                        <a href="#cmanager-tab-1">目录</a>
                    </li>
                    <li>
                        <a href="#cmanager-tab-2">类别</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div id="cmanager-tab-1" class="tab-pane active">
                        <kendo:treeView name="treeview" dataTextField="folderName" select="onSelect" dataSpriteCssClassField="nodeType">
                            <kendo:dataSource>
                                <kendo:dataSource-transport>
                                    <kendo:dataSource-transport-read url="getFolderNodesByPath.do" type="POST" contentType="application/json"/>
                                    <kendo:dataSource-transport-parameterMap>
                                        <script>
                                            function parameterMap(options, type) {
                                                return JSON.stringify(options);
                                            }
                                        </script>
                                    </kendo:dataSource-transport-parameterMap>
                                </kendo:dataSource-transport>
                                <kendo:dataSource-schema>
                                    <kendo:dataSource-schema-hierarchical-model id="path" hasChildren="hasChildren"/>
                                </kendo:dataSource-schema>
                            </kendo:dataSource>
                        </kendo:treeView>
                    </div>
                    <div id="cmanager-tab-2" class="tab-pane clearfix">
                        <ul class="ext-tabs-vertical">
                            <li class="active">
                                <a href="#cmanager-subtab-1"><i class="fa fa-file"></i> 所有文件 </a>
                            </li>
                            <li>
                                <a href="#cmanager-subtab-2"><i class="fa fa-clock-o"></i> Recently added</a>
                            </li>
                            <li>
                                <a href="#cmanager-subtab-3"><i class="fa fa-file"></i> My gallery</a>
                            </li>
                            <li>
                                <a href="#cmanager-subtab-4"><i class="fa fa-trash-o"></i> Deleted</a>
                            </li>
                            <li>
                                <a href="#cmanager-subtab-5"><i class="fa fa-star"></i> Rated</a>
                            </li>
                            <li>
                                <a href="#cmanager-subtab-6"><i class="fa fa-exchange"></i> Shared by me</a>
                            </li>
                            <li class="seperator"></li>
                            <li>
                                <a href="#cmanager-subtab-7"><i class="fa fa-bookmark"></i> Bookmarks</a>
                            </li>
                            <li>
                                <a href="#cmanager-subtab-8"><i class="fa fa-bar-chart-o"></i> Statistics</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="cmanager-subheader">
                <div class="pull-left">
                    <a href="#" class="btn" id="cmanager-sidebar-trigger">
                        <i class="fa fa-caret-left"></i>
                    </a>
                    <ul class="breadcrumb" id="breadcrumb">

                    </ul>
                    <!-- End .breadcrumb -->
                </div>
                <div class="pull-right">
                    <a href="#" class="btn" id="cmanager-grid-trigger">
                        <i class="fa fa-th-large"></i>
                    </a>
                    <a href="#" class="btn active" id="cmanager-list-trigger">
                        <i class="fa fa-list-ul"></i>
                    </a>
                </div>
            </div>
            <div class="cmanager-window">
                <div class="tab-content">
                    <div class="tab-pane active" id="cmanager-subtab-1">
                        <div class="inner-padding">
                            <ul class="cmanager-list" id="mix-1">

                            </ul>
                        </div>
                    </div>
                    <div class="tab-pane" id="cmanager-subtab-2">
                        <div class="inner-padding">
                            <strong>Recently added</strong> tab does not have any demo content(check the other tabs)
                        </div>
                    </div>
                    <div class="tab-pane" id="cmanager-subtab-3">
                        <div class="inner-padding">
                            <ul class="cmanager-list">
                                <li>
                                    <div class="cmanager-ctrls">
                                        <label><input type="checkbox" name=""/><span></span></label>

                                        <div class="btn-group">
                                            <a href="#" class="btn btn-default btn-sm"><i class="fa fa-pencil"></i></a>
                                            <a href="#" class="btn btn-default btn-sm"><i class="fa fa-comments"></i></a>
                                            <a href="#" class="btn btn-default btn-sm media-delete-trigger"><i class="fa fa-trash-o"></i></a>
                                        </div>
                                    </div>
                                    <div class="cmanager-list-thumb">
                                        <a href="images/gallery/img-11.jpg" class="lightbox" title="Lightbox title">
                                            <img src="images/gallery/95x95/img-11.jpg" alt=""/>
                                        </a>
                                    </div>
                                    <div class="cmanager-list-info">
                                        <h5>Image 3</h5>
                                        <ul>
                                            <li><strong>Date:</strong> 11/11/2012</li>
                                            <li><strong>By:</strong> Steven Watson</li>
                                            <li><strong>Size:</strong> 569 kb</li>
                                        </ul>
                                    </div>
                                </li>
                                <li>
                                    <div class="cmanager-ctrls">
                                        <label><input type="checkbox" name=""/><span></span></label>

                                        <div class="btn-group">
                                            <a href="#" class="btn btn-default btn-sm"><i class="fa fa-pencil"></i></a>
                                            <a href="#" class="btn btn-default btn-sm"><i class="fa fa-comments"></i></a>
                                            <a href="#" class="btn btn-default btn-sm media-delete-trigger"><i class="fa fa-trash-o"></i></a>
                                        </div>
                                    </div>
                                    <div class="cmanager-list-thumb">
                                        <a href="images/gallery/img-4.jpg" class="lightbox" title="Lightbox title">
                                            <img src="images/gallery/95x95/img-4.jpg" alt=""/>
                                        </a>
                                    </div>
                                    <div class="cmanager-list-info">
                                        <h5>Image 29</h5>
                                        <ul>
                                            <li><strong>Date:</strong> 11/11/2012</li>
                                            <li><strong>By:</strong> Steven Watson</li>
                                            <li><strong>Size:</strong> 569 kb</li>
                                        </ul>
                                    </div>
                                </li>
                                <li>
                                    <div class="cmanager-ctrls">
                                        <label><input type="checkbox" name=""/><span></span></label>

                                        <div class="btn-group">
                                            <a href="#" class="btn btn-default btn-sm"><i class="fa fa-pencil"></i></a>
                                            <a href="#" class="btn btn-default btn-sm"><i class="fa fa-comments"></i></a>
                                            <a href="#" class="btn btn-default btn-sm media-delete-trigger"><i class="fa fa-trash-o"></i></a>
                                        </div>
                                    </div>
                                    <div class="cmanager-list-thumb">
                                        <a href="images/gallery/img-2.jpg" class="lightbox" title="Lightbox title">
                                            <img src="images/gallery/95x95/img-2.jpg" alt=""/>
                                        </a>
                                    </div>
                                    <div class="cmanager-list-info">
                                        <h5>Image 33</h5>
                                        <ul>
                                            <li><strong>Date:</strong> 11/11/2012</li>
                                            <li><strong>By:</strong> Steven Watson</li>
                                            <li><strong>Size:</strong> 569 kb</li>
                                        </ul>
                                    </div>
                                </li>
                                <li>
                                    <div class="cmanager-ctrls">
                                        <label><input type="checkbox" name=""/><span></span></label>

                                        <div class="btn-group">
                                            <a href="#" class="btn btn-default btn-sm"><i class="fa fa-pencil"></i></a>
                                            <a href="#" class="btn btn-default btn-sm"><i class="fa fa-comments"></i></a>
                                            <a href="#" class="btn btn-default btn-sm media-delete-trigger"><i class="fa fa-trash-o"></i></a>
                                        </div>
                                    </div>
                                    <div class="cmanager-list-thumb">
                                        <a href="images/gallery/img-9.jpg" class="lightbox" title="Lightbox title">
                                            <img src="images/gallery/95x95/img-9.jpg" alt=""/>
                                        </a>
                                    </div>
                                    <div class="cmanager-list-info">
                                        <h5>Image 5</h5>
                                        <ul>
                                            <li><strong>Date:</strong> 11/11/2012</li>
                                            <li><strong>By:</strong> Steven Watson</li>
                                            <li><strong>Size:</strong> 569 kb</li>
                                        </ul>
                                    </div>
                                </li>
                            </ul>
                            <!-- End .cmanager-list -->
                        </div>
                    </div>
                    <div class="tab-pane" id="cmanager-subtab-4">
                        <div class="inner-padding">
                            <strong>Deleted</strong> tab does not have any demo content(check the other tabs)
                        </div>
                    </div>
                    <div class="tab-pane" id="cmanager-subtab-5">
                        <div class="inner-padding">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">Name</th>
                                    <th scope="col">Rated</th>
                                    <th scope="col">Total</th>
                                    <th scope="col">Score</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td><a href="#">Media file 1</a></td>
                                    <td>437</td>
                                    <td>
                                        <div class="rate">
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star-o"></i>
                                            <i class="fa fa-star-o"></i>
                                        </div>
                                    </td>
                                    <td>3,2</td>
                                </tr>
                                <tr>
                                    <td><a href="#">Media file 2</a></td>
                                    <td>111</td>
                                    <td>
                                        <div class="rate">
                                            <i class="fa fa-star-o"></i>
                                            <i class="fa fa-star-o"></i>
                                            <i class="fa fa-star-o"></i>
                                            <i class="fa fa-star-o"></i>
                                            <i class="fa fa-star-o"></i>
                                        </div>
                                    </td>
                                    <td>1,4</td>
                                </tr>
                                <tr>
                                    <td><a href="#">Media file 3</a></td>
                                    <td>332</td>
                                    <td>
                                        <div class="rate">
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                        </div>
                                    </td>
                                    <td>5,0</td>
                                </tr>
                                <tr>
                                    <td><a href="#">Media file 4</a></td>
                                    <td>609</td>
                                    <td>
                                        <div class="rate">
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star-o"></i>
                                        </div>
                                    </td>
                                    <td>4,4</td>
                                </tr>
                                <tr>
                                    <td><a href="#">Media file 5</a></td>
                                    <td>43</td>
                                    <td>
                                        <div class="rate">
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star-o"></i>
                                            <i class="fa fa-star-o"></i>
                                            <i class="fa fa-star-o"></i>
                                            <i class="fa fa-star-o"></i>
                                        </div>
                                    </td>
                                    <td>1,1</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="tab-pane" id="cmanager-subtab-6">
                        <div class="inner-padding">
                            <strong>Shared by me</strong> tab does not have any demo content(check the other tabs)
                        </div>
                    </div>
                    <div class="tab-pane" id="cmanager-subtab-7">
                        <div class="inner-padding">
                            <strong>Bookmark</strong> tab does not have any demo content(check the other tabs)
                        </div>
                    </div>
                    <div class="tab-pane" id="cmanager-subtab-8">
                        <div class="inner-padding">
                            <h3>Statistics</h3>

                            <div class="progress-project">
                                <div class="progress-project-header">
                                    <h5>Upload folder 1</h5><span>90%</span>
                                </div>
                                <div class="progress bar-small">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 90%;">
                                        <span class="sr-only">90% Complete</span>
                                    </div>
                                </div>
                            </div>
                            <!-- End .progress-project -->
                            <div class="spacer-20"></div>
                            <div class="progress-project">
                                <div class="progress-project-header">
                                    <h5>Upload folder 2</h5><span>20%</span>
                                </div>
                                <div class="progress bar-small">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%;">
                                        <span class="sr-only">20% Complete</span>
                                    </div>
                                </div>
                            </div>
                            <!-- End .progress-project -->
                            <div class="spacer-20"></div>
                            <div class="progress-project">
                                <div class="progress-project-header">
                                    <h5>Upload folder 3</h5><span>78%</span>
                                </div>
                                <div class="progress bar-small">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="78" aria-valuemin="0" aria-valuemax="100" style="width: 78%;">
                                        <span class="sr-only">78% Complete</span>
                                    </div>
                                </div>
                            </div>
                            <!-- End .progress-project -->
                            <div class="spacer-20"></div>
                            <div class="progress-project">
                                <div class="progress-project-header">
                                    <h5>Upload folder 4</h5><span>28%</span>
                                </div>
                                <div class="progress bar-small">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="28" aria-valuemin="0" aria-valuemax="100" style="width: 28%;">
                                        <span class="sr-only">28% Complete</span>
                                    </div>
                                </div>
                            </div>
                            <!-- End .progress-project -->
                        </div>
                    </div>
                </div>
                <div class="cmanager-addnew">
                    <div class="inner-padding">
                        <form style="width: 100%">
                            <div class="spacer-20"></div>
                            <h3>上传文件</h3>

                            <div class="spacer-20"></div>
                            <div class="row">
                                <div class="col-md-3">
                                    <label>文件</label>
                                </div>
                                <div class="col-md-9">
                                    <input type="file" name="mediaFile" id="mediaFile" class="form-control" style="width: 350px"/>
                                </div>
                            </div>
                            <div class="spacer-20"></div>
                            <div class="row">
                                <div class="col-md-3">
                                    <label>图片缩放</label>
                                </div>
                                <div class="col-md-9">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="width-100 pull-left margin-0-10-0-0">
                                                <input type="text" class="form-control" id="scaleWidth" value="800"/>
                                            </div>
                                            <div class="width-100 pull-left">
                                                <input type="text" class="form-control" id="scaleHeight" value="600"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="spacer-10"></div>
                                    <!-- End .helper-text-box -->
                                </div>
                            </div>
                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-md-3">
                                    <label>按比例缩放</label>
                                </div>
                                <div class="col-md-9">
                                    <label class="checkbox-inline" style="float:left"><input type="radio" name="scaleRate" value="true"/><span></span> 是</label>
                                    <label class="checkbox-inline" style="float:left"><input type="radio" name="scaleRate" value="false" checked/><span></span> 否</label>
                                </div>
                            </div>
                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-md-3">
                                </div>
                                <div class="col-md-9">
                                    <a href="#" class="btn btn-default cmanager-addnew-trigger">取消</a>
                                    <button type="button" class="btn btn-primary" onclick="uploadFile()">上传</button>
                                </div>
                            </div>
                            <div class="spacer-10"></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div id="pagefooter" class="cmanager-footer clearfix">
            <div class="pull-left">

            </div>
            <div class="pull-right"></div>
        </div>
    </div>
</div>