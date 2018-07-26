/*

description: 省市区三级(二级)联动
*/
//$(function(){
    $.citySelector = function () {
    	var data = {
    			province : "province",
    	        city : "city",
    	        district : "district",
    	        preProvince : "0",
    	        preCity : "0",
    	        preDistrict : "0",
    	        jsonProvince : "../js/district/json-array-of-province.js",
    	        jsonCity : "../js/district/json-array-of-city.js",
    	        jsonDistrict : "../js/district/json-array-of-district.js",
                jsonAddress : "../js/district/address.js",
    	        hasDistrict : true,
    	        initProvince : "<option value='0'>请选择省份</option>",
    	        initCity : "<option value='0'>请选择城市</option>",
    	        initDistrict : "<option value='0'>请选择区县</option>"
    	};
        return {
            init: function (dataObj) {
            	if(dataObj != undefined ){
            		jQuery.extend(data, dataObj);
            	}
            	var province = $("#"+data.province);
                var city = $("#"+data.city);
                var that = this;
                that._LoadOptions(data.jsonProvince, data.preProvince, data.province, null, 0, data.initProvince);
                province.change(function () {
                    that._LoadOptions(data.jsonCity, data.preCity, data.city, data.province, 2, data.initCity);
                });
                if (data.hasDistrict) {
                	city.change(function () {
                        that._LoadOptions(data.jsonDistrict, data.preDistrict, data.district, data.city, 4, data.initDistrict);
                    });
                	province.change(function () {
                		city.change();
                    });
                }
                setTimeout(function () {
                    province.change();
                }, 500);
            },
            _LoadOptions: function (datapath, preobj, targetobj, parentobj, comparelen, initoption) {
                $.get(
                datapath,
                function (r) {
                    var t = ''; // t:    html容器 
                    var s;      // s:    选中标识 
                    var pre;    // pre:  初始值
                    if (preobj === undefined) {
                        pre = 0;
                    } else {
                        pre = preobj;
                    }
                    for (var i = 0; i < r.length; i++) {
                        s = '';
                        if (comparelen === 0) {
                            if (pre !== "" && pre !== 0 && r[i].code === pre) {
                                s = ' selected=\"selected\" ';
                                pre = '';
                            }
                            t += '<option value=' + r[i].code + s + '>' + r[i].name + '</option>';
                        }
                        else {
                            var p = $("#"+parentobj).val();
                            if (p.substring(0, comparelen) === r[i].code.substring(0, comparelen)) {
                                if (pre !== "" && pre !== 0 && r[i].code === pre) {
                                    s = ' selected=\"selected\" ';
                                    pre = '';
                                }
                                t += '<option value=' + r[i].code + s + '>' + r[i].name + '</option>';
                            }
                        }

                    }
                    if (initoption !== '') {
                    	$("#"+targetobj).html(initoption + t);
                    } else {
                    	$("#"+targetobj).html(t);
                    }
                },
                "json"
                );
            },
            _LoadNameByCode: function (datapath, targetcode) {
            	var t = ''; // t:    编码对应的省市区名 
                $.ajax({
                	url:datapath,
                	dataType:"json",
                	async:false,
                	success:function (r) {
                        if (targetcode == undefined || targetcode == '' || targetcode == null) {
                            t = '';
                        }else{
                        	for (var i = 0; i < r.length; i++) {
                                if (r[i].code == targetcode) {
                                    t = r[i].name;
                                    break;
                                }
                            }
                        }
                    }
                });
                return t;
            },
            _LoadNameByValue: function (datapath, targetcode) {
                var t = ''; // t:    编码对应的省市区名
                $.ajax({
                    url:datapath,
                    dataType:"json",
                    async:false,
                    success:function (r) {
                        if (targetcode == undefined || targetcode == '' || targetcode == null) {
                            t = '';
                        }else{
                            for (var i = 0; i < r.length; i++) {
                                if (r[i].value == targetcode) {
                                    t = r[i].name;
                                    break;
                                }
                            }
                        }
                    }
                });
                return t;
            },
            getProvince: function (targetcode){
            	var that = this;
            	return that._LoadNameByCode(data.jsonProvince, targetcode);
            },
            getCity: function (targetcode){
            	var that = this;
            	return that._LoadNameByCode(data.jsonCity, targetcode);
            },
            getDistrict: function (targetcode){
                var that = this;
                return that._LoadNameByCode(data.jsonDistrict, targetcode);
            },
            getAddress: function (targetcode){
                var that = this;
                return that._LoadNameByValue(data.jsonAddress, targetcode);
            },
            getPCDNames:function(pCode,cCode,dCode){
                var that = this;
                var p = that._LoadNameByCode(data.jsonProvince, pCode);
                var c = that._LoadNameByCode(data.jsonCity, cCode);
                var d = that._LoadNameByCode(data.jsonDistrict, dCode);
                return p+" "+c+" "+d;
            }
        };
    } ();
//    citySelector.init();
//});
