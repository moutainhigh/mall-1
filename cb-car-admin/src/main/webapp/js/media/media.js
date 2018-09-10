var currentPath = "/";
var pageNum=1;
$(document).ready(function () {
    $('#catalogTrigger').focusin(function (e) {
        $('#catalogDialog').modal();
        e.preventDefault();
    });


    /* --------------------------------------------------------------------- */

    /**
     * Name        : Cmanager
     * Description : Several code pieces used for the cmanager.
     * Url         :
     * Version     : 1.0
     * Updated     :
     * Dependency  : jQuery core
     * Developer   : Mark
     **/

        // hide/show the sidebar
    $('body').on(clickEvent, '#cmanager-sidebar-trigger', function (e) {
        var gall = $(this).parents('.cmanager');
        if (gall.hasClass('cmanager-sidebar-hide')) {
            gall.removeClass('cmanager-sidebar-hide');
        } else {
            gall.addClass('cmanager-sidebar-hide');
        }
        switchClass($(this).children(), 'fa-caret-left', 'fa-caret-right', 0);

        e.preventDefault();
    });

    // show the upload part
    $('body').on(clickEvent, '.cmanager-addnew-trigger', function (e) {
        var gall = $(this).parents('.cmanager');
        if (gall.hasClass('cmanager-addnew-show')) {
            gall.removeClass('cmanager-addnew-show');
        } else {
            gall.addClass('cmanager-addnew-show');
        }
        e.preventDefault();
    });

    // disable/enable text input by an checkbox(upload part)
    $('body').on(clickEvent, '#cmanager-enable-name-trigger', function (e) {
        if ($(this).is(':checked')) {
            $(this).parent().prev('input').attr('disabled', 'disabled');
        } else {
            $(this).parent().prev('input').removeAttr('disabled');
        }
    });

    // show/hide a checkbox per image
    $('body').on(clickEvent, '#cmanager-create-trigger', function (e) {
        var dirName=prompt("请输入文件夹名称!");
        if(dirName!=null&&dirName!=""){
            $.getJSON("createDirByPath.do", {
                filePath: currentPath,
                dirName: dirName,
                rad: Math.random()
            }, function (json) {
                if (json.result == "success") {
                    $('#eachFile').tmpl(json.fileNode).appendTo("#mix-1");
                }
            });
        }
        e.preventDefault();
    });

    // set grid view
    $('body').on(clickEvent, '#cmanager-grid-trigger', function (e) {
        $(this).parents('.cmanager').addClass('cmanager-grid-view');
        $('#cmanager-list-trigger').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

    // set list view
    $('body').on(clickEvent, '#cmanager-list-trigger', function (e) {
        $(this).parents('.cmanager').removeClass('cmanager-grid-view');
        $('#cmanager-grid-trigger').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

    // show/hide a checkbox per image
    $('body').on(clickEvent, '#cmanager-delete-trigger', function (e) {
        var filePath = $('input:radio[name="filePath"]:checked').val();
        if (filePath == null) {
            alert("请选择需要删除的文件!");
        } else {
            if (confirm("确定删除吗？")) {
                var itemObj=$(this).parents('.cmanager')
                    .find('.cmanager-window')
                    .find('.tab-pane.active')
                    .find(':checked')
                    .parents('li');
                $.getJSON("deleteFileNodeByPath.do", {
                    filePath: filePath,
                    rad: Math.random()
                }, function (json) {
                    if (json.result == "success") {
                        itemObj.fadeOut(500, function () {
                            $(this).remove();
                        });
                    }
                });
            }
        }


        e.preventDefault();
    });

    //$("#validateSubmitForm").validate({
    //    rules: {
    //        brandNo: {
    //            required: true,
    //            minlength: 2,
    //            maxlength: 32
    //        }
    //    },
    //    onkeyup: false,
    //    onfocusout: function (e) {
    //        $(e).valid();
    //    }
    //});
    loadDirFiles();
});


function onSelect(e) {
    var data = $('#treeview').data('kendoTreeView').dataItem(e.node);
    currentPath = data.path;
    loadDirFiles();
}


function loadDirFiles(pageNo) {
    if(pageNo == undefined || pageNo==""){
        pageNo=this.pageNum;
    }else{
        this.pageNum=pageNo;
    }
    $("#breadcrumb li").remove();
    var array = currentPath.split("/");
    var path = "";
    for (var i = 0; i < array.length; i++) {
        if (i == 0) {
            var an = $("<a>Home</a>");
            an.click(function () {
                naviToDir("/");
            });
            $("#breadcrumb").append($("<li/>").append(an));
        } else {
            path += "\/";
            path = path + array[i];
            var an = $("<a>" + array[i] + "</a>");
            var tpath = new String(path);
            an.click(function () {
                naviToDir(tpath);
                //todo 存在错误
            });
            $("#breadcrumb").append($("<li/>").append(an));
        }
    }
    $.getJSON("getFileNodesByPath.do", {
        path: currentPath,
        page : pageNo
    }, function (json) {
        $("#mix-1 li").remove();
        $('#eachFile').tmpl(json.fileNodes).appendTo("#mix-1");
        //$("#pagefooter").find(".pull-left").children().remove();
        //$("#pagefooter").find(".pull-right").children().remove();
        $('.lightbox').magnificPopup({
            type:'image',
            callbacks: {
                open: function(){
                    // remove the marginright from the body added by this plugin
                    $('html').css({marginRight: 0});
                },
                close: function(){ }
            }
        });

        $("#pagefooter").find(".pull-left").children().remove();
        $("#pagefooter").find(".pull-right").children().remove()

        var ul = $("<ul class='pagination'></ul>");
        var pageSize= 5, count=1;
        if(json.totalPages<=pageSize){
            for ( count=1;count<=json.totalPages;count++)
            {
                if(count==pageNo){
                    ul.append("<li class='active'><span>"+count+"</span></li>");
                }else{
                    ul.append("<li><a href='javascript:loadDirFiles("+count+")'>"+count+"</a></li>");
                }
            }
        }else{
            if(parseInt((pageNo-1)/pageSize) == 0)
            {
                for ( count=1;count<=pageSize;count++)
                {
                    if(count==pageNo){
                        ul.append("<li class='active'><span>"+count+"</span></li>");
                    }else{
                        ul.append("<li><a href='javascript:loadDirFiles("+count+")'>"+count+"</a></li>");
                    }
                }
                if(json.hasNext){
                    ul.append("<li><a href='javascript:loadDirFiles("+count+")'>下一页</a></li>");
                }
            }else if(parseInt((pageNo-1)/pageSize) == parseInt(json.totalPages/pageSize))
            {
                ul.append("<li><a href='javascript:loadDirFiles("+(parseInt((pageNo-1)/pageSize)*pageSize)+")'>上一页</a></li>");
                for ( count=parseInt(json.totalPages/pageSize)*pageSize+1;count<=json.totalPages;count++)
                {
                    if(count==pageNo){
                        ul.append("<li class='active'><span>"+count+"</span></li>");
                    }else{
                        ul.append("<li><a href='javascript:loadDirFiles("+count+")'>"+count+"</a></li>");
                    }
                }
            }else{
                ul.append("<li><a href='javascript:loadDirFiles("+(parseInt((pageNo-1)/pageSize)*pageSize)+")'>上一页</a></li>");

                for (count=parseInt((pageNo-1)/pageSize)*pageSize+1;count<=parseInt((pageNo-1)/pageSize)*pageSize+pageSize;count++)
                {
                    if(count==pageNo){
                        ul.append("<li class='active'><span>"+count+"</span></li>");
                    }else{
                        ul.append("<li><a href='javascript:loadDirFiles("+count+")'>"+count+"</a></li>");
                    }
                }
                if(count<=json.totalPages){
                    ul.append("<li><a href='javascript:loadDirFiles("+count+")'>下一页</a></li>");
                }

            }
        }


        $("#pagefooter").find(".pull-left").append(ul);

        $("#pagefooter").find(".pull-right").append("<span style='padding-right: 2rem;'>共:"+json.totalPages+"页|第:"+pageNo+"页</span>")
            .append("<span style='padding-right: 2rem;'>总记录数："+json.totalRowsAmount+"</span>");
    });
}

function naviToDir(path) {
    console.log(path);
    currentPath = path;
    loadDirFiles();
}

function uploadFile() {
    if ($("#uploadFile").val() != "") {
        $.ajaxFileUpload({
                url: 'uploadMediaFile.do',
                secureuri: false,
                fileElementId: 'mediaFile',
                dataType: 'json',
                data: {//加入的文本参数
                    scaleWidth: $("#scaleWidth").val(),
                    scaleHeight: $("#scaleHeight").val(),
                    path: currentPath
                },
                success: function (data, status) {
                    $("#mediaFile").val("");
                    $('#eachFile').tmpl(data.fileNode).appendTo("#mix-1");
                    if(confirm("文件上传成功！是否继续上传？")){

                    }else{
                        $(".cmanager").removeClass('cmanager-addnew-show');
                    }
                },
                error: function (data, status, e) {
                    alert("文件上传失败！");
                    $("#mediaFile").val("");
                }
            }
        );
    }
}

function getSelectedPath(){
    var filePath = $('input:radio[name="filePath"]:checked').val();
    return filePath;
}