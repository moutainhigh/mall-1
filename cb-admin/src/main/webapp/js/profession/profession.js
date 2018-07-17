
$.profession = function () {
    var data = {
        jsonProfession : "../js/profession/json-profession.js",
    };
    return {
        _LoadNameByCode: function (datapath, targetcode) {
            var t = ''; // t:
            $.ajax({
                url:datapath,
                dataType:"json",
                async:false,
                success:function (r) {
                    if (targetcode == undefined || targetcode == '' || targetcode == null) {
                        t = '';
                    }else{
                        for (var i = 0; i < r.length; i++) {
                            if (r[i].key == targetcode) {
                                t = r[i].value;
                                break;
                            }
                        }
                    }
                }
            });
            return t;
        },
        getProfession: function (targetcode){
            var that = this;
            return that._LoadNameByCode(data.jsonProfession, targetcode);
        }
    };
} ();

