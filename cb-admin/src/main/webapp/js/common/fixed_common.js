/**
 * 后台页面共用的js
 * Created by lxc on 2018-08-09.
 */

/**
 * 设置销售价
 * add by lxc   2018-08-09 14:23
 */
function salePrice() {
    var ratio = $('#ratio').val();      //商品比例设置
    if(ratio == null || ratio == 0){
        ratio = $("#catalogRatio").val();//分类比例设置
    }
    var costPrice = $('#costPrice');//成本价
    if(costPrice.val() != null && costPrice.val() != ''){
        //解决js小数运算精度丢失问题
        var salePrice = mul(costPrice.val(),ratio);
        $('#sellPrice').val(salePrice);//比例设置*成本价
    }
}