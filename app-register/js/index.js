$(function () {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == "invitationCode") {
            vm.customerVo.invitationCode = pair[1];
        }
    }

    if (vm.customerVo.invitationCode && vm.customerVo.invitationCode != 'null') {
        console.log(vm.customerVo.invitationCode, "test");
        $.ajax({
            url: "http://119.23.59.102:8158/api/noAuth/recommendCustomer/" + vm.customerVo.invitationCode,
            type: "get",
            dataType: "json",
            contentType: 'application/json',
            success: function success(res) {
                if (res.result == 'SUCCESS') {
                    vm.invMobile = res.data.mobile;
                }
            }
        });
    } else {
        $.alert("未能获取邀请码！", "异常");
    }

    // var u = navigator.userAgent;
    // var isIOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
    // if (isIOS) {
    //     if (screen.height == 812 && screen.width == 375) {
    //         //是iphoneX
    //         $("#marX").css("margin-top");
    //     } else {
    //         //不是iphoneX
    //     }
    // }
});

var vm = new Vue({
    el: '#base',
    data: {
        customerVo: {
            mobile: '',
            code: '',
            pwd: '',
            random: '',
            invitationCode: '',
        },
        invMobile: '',
        numCode: "获取验证码",
        valid: false,
        isShow:true
    },
    methods: {
        getValidCode: function getValidCode() {
            console.log(this.customerVo.mobile)
            if (!this.customerVo.mobile) {
                $.toptip('手机号码不能为空', 'error');
                return false;
            }
            var mobile = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
            if (!mobile.test(this.customerVo.mobile)) {
                $.toptip('手机号码格式错误', 'error');
                return false;
            }
            if (vm.numCode == "获取验证码") {
                var hid = setTimeout(function () {
                    $.toptip('发送超时，请稍后重试！', 'error');
                }, 10000);
                $.ajax({
                    // url: "http://39.108.65.63:8158/api/noAuth/sendMobileValidCode/REGISTER/" + vm.customerVo.mobile,
                    url: "http://119.23.59.102:8158/api/noAuth/sendMobileValidCode/REGISTER/" + vm.customerVo.mobile,
                    type: "post",
                    dataType: "json",
                    contentType: 'application/json',
                    success: function success(res) {
                        if (res.result == 'SUCCESS') {
                            clearTimeout(hid);
                            $.toptip('发送成功', 1000, 'success');

                            var num = 60;
                            var start = setInterval(function () {
                                num--;
                                if (num == 0) {
                                    vm.numCode = "获取验证码";
                                    clearInterval(start);
                                } else {
                                    vm.numCode = num + "s后重试";
                                }
                            }, 1000);
                        } else {
                            clearTimeout(hid);
                            $.toptip(res.message, 1000, 'error');
                        }
                    },
                    error: function error(res) {
                        clearTimeout(hid);
                        $.toptip('发送失败，请稍后重试', 1000, 'success');
                    }
                });
            }
        },
        next: function next() {
            var space = /\s+/g;
            let input = document.getElementsByTagName("input");
            for (let i = 0; i < input.length; i++) {
                if (space.test(input[i].value)) {
                    $.toptip('请不要输入空格', 'error');
                    return false;
                }
            }
            var mobile = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
            if (!mobile.test(this.customerVo.mobile)) {
                $.toptip('手机号码格式错误', 'error');
                return false;
            }
            if (this.customerVo.pwd.length < 6) {
                $.toptip('密码不能小于6位', 'error');
                return false;
            }
            if (this.customerVo.pwd.length > 18) {
                $.toptip('密码不能超过18位', 'error');
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
                        vm.customerVo.random = res.data;
                        window.sessionStorage.setItem("customerVo", JSON.stringify(vm.customerVo));
                        window.location.href = "./index.html";
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
        goLoad: function goLoad() {
            window.location.href = "/index.html";
        },
        close:function close() {
            vm.isShow = false;
        },
        protocol: function protocol() {
            window.location.href = "/protocol.html";
        }
    },
    watch: {
        customerVo: {
            handler: function handler(newVal) {
                if (newVal.mobile && newVal.code && newVal.pwd) {
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