<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="ie ie6 lte9 lte8 lte7 no-js"> <![endif]-->
<!--[if IE 7]> <html class="ie ie7 lte9 lte8 lte7 no-js"> <![endif]-->
<!--[if IE 8]> <html class="ie ie8 lte9 lte8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie ie9 lte9 no-js"> <![endif]-->
<!--[if gt IE 9]> <html class="no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>保险产品详情</title>
    <style>
        table {
            border: solid 1px;
            margin-top: 20px;
        }

        table tr {
            border: solid 1px;
        }

        table td {
            text-align: center;
            border: solid 1px;
            padding: 5px;
        }
    </style>
    <script type="text/javascript">
        function addMatter(prodId,matterId) {
            bootbox.confirm("确认添加吗？", function (result) {
                if (result) {
                    var formData = new FormData();
                    formData.append("prodId", prodId);
                    formData.append("matterId", matterId);
                    $.ajax({
                        url: "addMetterById.do",
                        type: 'GET',
                        data: {
                            "prodId": prodId,
                            "matterId": matterId
                        },
                        success: function (data) {
                            if (data) {
                                bootbox.alert("成功");
                                history.go(0)
                            } else {
                                bootbox.alert("失败");
                            }
                        },
                        error: function (err) {
                        }
                    });
                }
            });
        }

           $(function () {
               $.ajax({
                   url: "getmatterList.do",
                   type: 'GET',
                   data: {
                       "prodId": ${prodId}
                   },
                   success: function (data) {
                       var html='';
                       for (var i=0;i<data.length;i++)
                       {
                           var type='';
                           if(data[i].matterType==0){
                               type="是否题";
                           }else if(data[i].matterType==0){
                               type="填空题";
                           }
                           html+="<tr><td>"+data[i].matterId+"</td>"
                               +"<td>"+data[i].matterDescription+"</td>"
                               +"<td>"+type+"</td>"
                               +"<td><a href='javascript:void(0);' onclick='addMatter(${prodId},"+data[i].matterId+")' style='color:blue'>添加</a></td></tr>";
                       }
                       $("#thb").html(html);
                   },
                   error: function (err) {
                   }
               });
           })
    </script>
</head>
<body>
<div style="width: 80%;margin-left: 100px">
    <div class="row" style="margin-top: 20px">
        <div class="col-sm-2">
            <label>事项描述：</label>
        </div>
        <div class="col-sm-3">
            <textarea  id="matterDescription" cols="60" rows="3"></textarea>
        </div>
    </div>
    <table>
        <tr>
            <td style="width: 100px">事项Id</td>
            <td>事项描述</td>
            <td style="width: 100px">事项类型</td>
            <td style="width: 100px">操作</td>
        </tr>
        <tbody id="thb">
        </tbody>
    </table>
</div>
</body>
</html>
