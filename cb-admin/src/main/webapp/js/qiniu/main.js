var token = "";
var domain = "";
var config = "";
var putExtra = ""
$(function () {
    $.ajax({
        url: "/admin/uploads/getQiniuInfo.do",
        async:true,
        success: function (res) {
            token = res.upToken;
            domain = res.domain;
            config = {
                useCdnDomain: true,
                disableStatisticsReport: false,
                retryCount: 6,
                region: qiniu.region.z2
            };
            putExtra = {
                fname: "",
                params: {},
                mimeType: null
            };
            $(".nav-box")
                .find("a")
                .each(function (index) {
                    $(this).on("click", function (e) {
                        switch (e.target.name) {
                            case "h5":
                                uploadWithSDK(token, putExtra, config, domain);
                                break;
                            default:
                                "";
                        }
                    });
                });
            imageControl(domain);
            uploadWithSDK(token, putExtra, config, domain);
        }
    })
})



