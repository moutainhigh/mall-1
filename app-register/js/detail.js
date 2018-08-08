$(function () {
    $("#picker").picker({
        title: "请选择证件类型",
        cols: [{
            textAlign: 'center',
            values: ['居民身份证', '居民户口簿', '军官证', '港澳居民往来内地通行证', '出生证', '台湾居民往来内地通行证', '外国护照', '外国人永久居留身份证', '武警身份证', '其他证件']
        }]
    });

    var item = window.sessionStorage.getItem("customerVo");
    if (item) {
        vm.customerVo = JSON.parse(item);
    } else {
        window.location.href = "./register.html";
    }

    var ua = navigator.userAgent.toLowerCase(); //获取浏览器的userAgent,并转化为小写——注：userAgent是用户可以修改的
    var isIos = ua.indexOf('iphone') != -1 || ua.indexOf('ipad') != -1; //判断是否是苹果手机，是则是true
    if (isIos) {
        $("input:file").removeAttr("capture");
    }
    //通过正则表达式匹配ua中是否含有MicroMessenger字符串
    if (ua.match(/MicroMessenger/i) == 'micromessenger') {
        $("input:file").removeAttr("capture");
    }


});

var vm = new Vue({
    el: '#rrapp',
    data: {
        customerVo: {
            realName: '',
            mobile: '',
            code: '',
            pwd: '',
            cardType: '',
            customerCardNo: '',
            invitationCode: '',
            cardPositiveImg: '',
            cardNegativeImg: '',
            bankCardImg: '',
            objImg: {urls: [], current: ''}
        },
        valid: false
    },
    methods: {
        chooseType: function chooseType(e) {
            console.log(e);
        },
        //身份证正反面上传
        addPic1: function addPic1() {
            document.getElementById("image1").click();
            return false;
        },
        addPic2: function addPic2() {
            document.getElementById("image2").click();
            return false;
        },
        //银行卡正面上传
        addPic3: function addPic3() {
            document.getElementById("image3").click();
            return false;
        },
        onFileChange: function onFileChange(e) {
            var files = e.target.files || e.dataTransfer.files;
            if (!files.length) return;
            this.createImage(files, e);
        },
        createImage: function createImage(file, e) {
            var vm = this;
            $.showLoading();
            var hid = setTimeout(function () {
                $.toptip('发送超时，请稍后重试！', 'error');
            }, 15000);
            lrz(file[0], {width: 480}).then(function (rst) {
                console.log('rst');
                rst.base64 = rst.base64.split(',')[1];
                $.ajax({
                    // url: "http://39.108.65.63:8158/api/common/file/uploadBase64/PAPERWORK",
                    url: "119.23.59.102:8158/api/common/file/uploadBase64/PAPERWORK",
                    type: "post",
                    dataType: "json",
                    contentType: 'application/json',
                    data: JSON.stringify({
                        data: rst.base64
                    }),
                    success: function success(res) {
                        console.log(res);
                        switch (e.target.id) {
                            case 'image1':
                                vm.customerVo.cardPositiveImg = res.data;
                                console.log(vm.customerVo.cardPositiveImg);
                                break;
                            case 'image2':
                                vm.customerVo.cardNegativeImg = res.data;
                                break;
                            case 'image3':
                                vm.customerVo.bankCardImg = res.data;
                                break;
                        }
                        $.hideLoading();
                        clearTimeout(hid);
                    }
                });
                return rst;

            }).catch(function (err) {
                window.alert('浏览器暂不兼容');
                // 处理失败会执行
            }).always(function () {
                // 清空文件上传控件的值
                e.target.value = null;
            });
        },
        delImage: function delImage(index) {
            var vm = this;
            switch (index) {
                case 1:
                    vm.customerVo.cardPositiveImg = '';
                    break;
                case 2:
                    vm.customerVo.cardNegativeImg = '';
                    break;
                case 3:
                    vm.customerVo.bankCardImg = '';
                    break;
            }
        },
        submit: function submit() {
            var type = $("#picker").val();
            var idCardVali = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
            var householdVali = /^\d{9}$/;
            var birthVali = /[A-Z]{1}\d{9}$/;
            var hkmcPassVali = /^[HMhm]{1}([0-9]{10}|[0-9]{8})$/;
            var taiwanPassVali = /^[0-9]{8}$/;
            var passportVali = /^[a-z0-9A-Z]{6,25}$/;
            var permanentResidenceVali = /^[a-zA-Z]{3}\d{12}$/;
            var emoji = /\*[\uD83C|\uD83D|\uD83E][\uDC00-\uDFFF][\u200D|\uFE0F]|[\uD83C|\uD83D|\uD83E][\uDC00-\uDFFF]|[0-9|*|#]\uFE0F\u20E3|[0-9|#]\u20E3|[\u203C-\u3299]\uFE0F\u200D|[\u203C-\u3299]\uFE0F|[\u2122-\u2B55]|\u303D|[\A9|\AE]\u3030|\uA9|\uAE|\u3030/ig;
            var space = /\s+/g;

            var input = document.getElementsByTagName("input");
            for (var i = 0; i < input.length; i++) {
                if (emoji.test(input[i].value)) {
                    $.toptip('输入信息不得带表情', 'error');
                    return false;
                }
            }
            for (var _i = 0; _i < input.length; _i++) {
                if (space.test(input[_i].value)) {
                    $.toptip('请不要输入空格', 'error');
                    return false;
                }
            }
            if (type != '') {
                switch (type) {
                    case '居民身份证': {
                        if (!idCardVali.test(this.customerVo.customerCardNo)) {
                            $.toptip('请输入正确的证件号码', 'error');
                            return false;
                        }
                        break;
                    }
                    case '居民户口簿': {
                        if (!householdVali.test(this.customerVo.customerCardNo)) {
                            $.toptip('请输入正确的证件号码', 'error');
                            return false;
                        }
                        break;
                    }
                    case '军官证': {
                        if (!idCardVali.test(this.customerVo.customerCardNo)) {
                            $.toptip('请输入正确的证件号码', 'error');
                            return false;
                        }
                        break;
                    }
                    case '港澳居民往来内地通行证': {
                        if (!hkmcPassVali.test(this.customerVo.customerCardNo)) {
                            $.toptip('请输入正确的证件号码', 'error');
                            return false;
                        }
                        break;
                    }
                    case '出生证': {
                        if (!birthVali.test(this.customerVo.customerCardNo)) {
                            $.toptip('请输入正确的证件号码', 'error');
                            return false;
                        }
                        break;
                    }
                    case '台湾居民往来内地通行证': {
                        if (!taiwanPassVali.test(this.customerVo.customerCardNo)) {
                            $.toptip('请输入正确的证件号码', 'error');
                            return false;
                        }
                        break;
                    }
                    case '外国护照': {
                        if (!passportVali.test(this.customerVo.customerCardNo)) {
                            $.toptip('请输入正确的证件号码', 'error');
                            return false;
                        }
                        break;
                    }
                    case '外国人永久居留身份证': {
                        if (!permanentResidenceVali.test(this.customerVo.customerCardNo)) {
                            $.toptip('请输入正确的证件号码', 'error');
                            return false;
                        }
                        break;
                    }
                    case '武警身份证': {
                        if (!idCardVali.test(this.customerVo.customerCardNo)) {
                            $.toptip('请输入正确的证件号码', 'error');
                            return false;
                        }
                        break;
                    }
                    case '其他证件': {
                        break;
                    }
                }
                if (this.customerVo.customerCardNo.length > 32) {
                    $.toptip('证件号码不得超过32位', 'error');
                    return false;
                }
                vm.customerVo.cardType = type;
            } else {
                $.toptip('请选择证件类型', 'error');
                return false;
            }
            if (this.customerVo.customerCardNo.length == 0) {
                $.toptip('证件号码不能为空', 'error');
                return false;
            }
            if (this.customerVo.realName.length == 0) {
                $.toptip('姓名不能为空', 'error');
                return false;
            }
            if (this.customerVo.realName.length < 2) {
                $.toptip('姓名不能小于两个汉字', 'error');
                return false;
            }
            if (this.customerVo.realName.length > 16) {
                $.toptip('姓名不能超过16位', 'error');
                return false;
            }
            if (!this.customerVo.cardPositiveImg) {
                $.toptip('请上传身份证正面图片', 'error');
                return false;
            }
            if (!this.customerVo.cardNegativeImg) {
                $.toptip('请上传身份证背面图片', 'error');
                return false;
            }
            if (!this.customerVo.bankCardImg) {
                $.toptip('请上传银行卡正面图片', 'error');
                return false;
            }
            $.showLoading();
            var hid = setTimeout(function () {
                $.toptip('发送超时，请稍后重试！', 'error');
            }, 10000);
            $.ajax({
                // url: "http://39.108.65.63:8158/api/noAuth/register",
                url: "http://119.23.59.102:8158/api/noAuth/register",
                type: "post",
                dataType: "json",
                contentType: 'application/json',
                data: JSON.stringify(vm.customerVo),
                success: function success(res) {
                    if (res.result == 'SUCCESS') {
                        window.location.href = "http://app.999shuijingqiu.com";
                    } else {
                        $.toptip(res.message, 'error');
                    }
                    $.hideLoading();
                    clearTimeout(hid);
                },
                error: function error(res) {
                    $.hideLoading();
                    clearTimeout(hid);
                    $.toptip('发送失败，请稍后重试！', 'error');
                }
            });
        },
        showImg(img) {
            var obj = {
                urls : [img]
            };
            previewImage.start(obj);
        }
    },
    watch: {
        customerVo: {
            handler: function handler(newVal, oldVal) {
                var type = $("#picker").val();
                if (type && newVal.customerCardNo && newVal.cardPositiveImg && newVal.cardNegativeImg && newVal.bankCardImg) {
                    vm.valid = true;
                } else {
                    if (vm) {
                        vm.valid = false;
                    }
                }
            },
            immediate: true,
            deep: true
        }
    }
});