/**
 * Created by gonglei on 2016/1/18.
 */
//定义动态表的id
var autoTableId = "attribute-table-tb";
//定义动态表中每一行的数据
var autoTableRowData = new Array(
    '<input type="text" size="12" name="name" id="name" value="">',
    '<input type="text" size="35" name="address" id="address" value="">',
    '<button type="button" name="deleteRow" data-toggle="tooltip" title="Remove" class="btn btn-danger"><i class="fa fa-minus-circle"></i></button>'
);
//定义删除按钮的name属性
var delTableRowName = "deleteRow";

function addAttributeRow(rowIndex) {
    var tbobj = document.getElementById(autoTableId);
    var trobj, tdobj;
    if (rowIndex == -1) {
        trobj = tbobj.insertRow(-1);
    } else {
        trobj = tbobj.insertRow(rowIndex + 1);
    }
    trobj.className = "N1";
    trobj.onmouseover = new Function("this.className='N2';");
    trobj.onmouseout = new Function("this.className='N1';");
    for (var i = 0; i < autoTableRowData.length; i++) {
        tdobj = trobj.insertCell(-1);
        tdobj.className = "DN";
        tdobj.innerHTML = autoTableRowData[i];
    }
    //重新定义onclick事件
    setAddFunction();
}
//删除1行
function delTableRow(rowIndex) {
    var tbobj = document.getElementById(autoTableId);
    if (rowIndex == -1) {
        if (tbobj.rows.length > 1) {
            tbobj.deleteRow(tbobj.rows.length - 1);
        }
    } else {
        tbobj.deleteRow(rowIndex);
    }
}
//定义删除动态行的onclick方法
function setAddFunction() {
    var delNames = document.getElementsByName(delTableRowName);
    for (var i = 0; i < delNames.length; i++) {
        delNames[i].onclick = new Function("delTableRow(this.parentNode.parentNode.rowIndex-1);");
    }
}

// 提交属性组，以及多个属性
function addAttributeGroup() {

    //属性组
    var attrGroupObj =
    {
        groupName: ""
    }
    attrGroupObj = new  Object();
    attrGroupObj.groupName = $("[name='groupName']").val();
    // 多个属性
    //封装属性对象
    var attrObj =
    {
        attributeName: "",
        sortOrder: ""
    }
    var a = JSON.parse("{\"data\":[]}");
    //封装底部表格中的数据
    //获得行数(包括thead)
    var rows = document.getElementById(autoTableId).rows.length;
    //获得列数
    var colums = document.getElementById(autoTableId).rows[0].cells.length;

    if(rows > 0) {//
        //每行
        for (var i = 0; i < rows; i++) {
            var attrObj = new Object();
            //attrObj.attributeName = document.getElementById(autoTableId).rows[i].cells[0].value;
            attrObj.attributeName = $("#" + autoTableId + " tr:eq("+i+") td:eq(0) input").val();
            alert(attrObj.attributeName);
            attrObj.sortOrder = $("#" + autoTableId + " tr:eq("+i+") td:eq(1) input").val();
            alert(attrObj.sortOrder);
            //向JSON数组添加JSON对象
            a.data.push(attrObj);
        }
        //格式化数据
        var objAttributes=JSON.stringify(a);
         //异步提交数据

        $.ajax({

            url: "addAttributeGroup.do",
            type: "post",
            data: {
                    'attributes': objAttributes
                    },
            datatype: "json",

            success: function (data) {

            }

        });

    }

}