<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>文件管理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link href="../js/plugins/fileinput/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>
    <script src="../js/plugins/fileinput/fileinput.min.js" type="text/javascript"></script>
    <title>云信 - <sitemesh:title/></title>
<script>
    $(function(){
        //初始化图片上传组件
        $("#picUrl").fileinput({
            uploadUrl: "/admin/uploads/upload/INSURANCEPRODUCT.do",
            showCaption: false,
            minImageWidth: 50,
            minImageHeight: 50,
            maxFileSize : 2000,
            maxFileCount: 5
        }).on("fileuploaded", function (event, data) {
            var response = data.response;
            $('#getImgurl').val(response.url);
            //上传完成回调
            var initPreviewConfig = new Array();//展示设置
            var initPreview = response.url;
            var config = new Object();
            config.caption = "";
            config.url="/admin/uploads/upload/INSURANCEPRODUCT.do";
            config.key="123";
            config.id="1";
            initPreviewConfig=config;
            $("#picUrl").fileinput('refresh', {
                initialPreview: initPreview,
                initialPreviewConfig: initPreviewConfig,
                initialPreviewAsData: true
            });
        }).on("filepredelete", function(jqXHR) {
            var abort = true;
            if (confirm("确定要删除吗？(删除后不会恢复)")) {
                abort = false;
            }
            return abort;
        });
    })
</script>
</head>
<body>
<input id="picUrl" name="file" type="file" class="file-loading" accept="image/*" multiple>
<div id="url">
    <input id="getImgurl" type="text">
</div>
</body>
</html>
