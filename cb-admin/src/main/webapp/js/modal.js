(function($){
    $.fn.createModal = function(options,callback) {
        var opts = $.extend({}, $.fn.createModal.defaults, options);
        return this.each(function() {
            $this = $(this);

            var modalContainer = document.createElement("div"),
                modalBg = document.createElement("div"),
                modalContent = document.createElement("div");

            $(modalBg).css({
                "position": "absolute",
                "top": 0,
                "left": 0,
                "width": "100%",
                "height": "100%",
                "background-color": opts.background,
                "opacity": 0.5
            });

            $(modalContent).css({
                "position": "fixed",
                "background-color": "#fff",
                "width": opts.width,
                "height": opts.height,
                "left": ($(document).width() - parseInt(opts.width))/2,
                "top": ($(window).height() - parseInt(opts.height))/2,
                "border": "2px #fff solid",
                "border-radius": "5px"
            });

            $(modalContainer).css({
                position: "fixed",
                width: "100%",
                height: $(document).height(),
                zIndex: 999,
                left: 0,
                top: 0
            }).append(modalBg).append(modalContent);//纭畾modalContainer鐨勪綅缃�

            var writeModal = function(_$this){
                $(window).resize(function(){
                    $(modalContent).css({
                        "left": ($(window).width() - parseInt(opts.width))/2,
                        "top": ($(window).height() - parseInt(opts.height))/2
                    });
                    $(modalBg).css({
                        "height": $(document).height()
                    })
                });//绐楀彛澶у皬鍙樺寲鏃�,灏嗗脊绐楃Щ鍔ㄥ埌灞忓箷涓棿浣嶇疆

                var getTitle = _$this.attr("data-title");
                if(getTitle){
                    $(modalContent).append(
                        "<h1 class='modal-title' control-move>" + _$this.attr("data-title") + "</h1>"
                    );
                }//鑾峰彇data-title鐨勫€�

                $(modalContent).append(
                    "<div class='modal-close close-pos-r-t'>X</div>"
                );

                if(_$this.attr("data-url")){
                    htmlobj = $.ajax({
                        url: _$this.attr("data-url"),
                        async:false
                    });
                    $(modalContent).append("<p>" + htmlobj.responseText + "</p>");//鍐欏叆ajax璇锋眰寰楀埌鐨勫€�
                }else {
                    if(opts.html == ""){
                        var getContent = _$this.html();
                        $(modalContent).append("<p>" + getContent + "</p>").append("<p>杩欑鎯呭喌灏辨槸鐩存帴璇诲彇瑙﹀彂褰撳墠浜嬩欢鐨勫厓绱犵殑html浠ｇ爜</p>");
                    }else{
                        $(modalContent).append(opts.html);
                    }
                };

                $(modalContainer).prependTo("body");

                if(opts.bgClose){
                    $(modalBg).click(function(){
                        $(modalContent).empty();
                        $(modalContainer).remove();
                    });
                };
                $(".modal-close").click(function(){
                    $(modalContent).empty();
                    $(modalContainer).remove();
                });

                if(opts.move){
                    $(modalContent).find("[control-move]").css("cursor","move").on("mousedown",function(e){
                        /*$(this)[0].onselectstart = function(e) { return false; }*///闃叉鎷栧姩绐楀彛鏃讹紝浼氭湁鏂囧瓧琚€変腑鐨勭幇璞�(浜嬪疄璇佹槑涓嶅姞涓婅繖娈垫晥鏋滀細鏇村ソ)
                        $(this)[0].oncontextmenu = function(e) { return false; }//闃叉鍙冲嚮寮瑰嚭鑿滃崟
                        var getStartX = e.pageX,
                            getStartY =  e.pageY;
                        var getPositionX = $(modalContent).offset().left,
                            getPositionY = $(modalContent).offset().top;
                        $(document).on("mousemove",function(e){
                            var getEndX = e.pageX,
                                getEndY =  e.pageY;
                            $(modalContent).css({
                                left: getEndX-getStartX+getPositionX,
                                top: getEndY-getStartY+getPositionY
                            });
                        });
                        $(document).on("mouseup",function(){
                            $(document).unbind("mousemove");
                        })
                    });
                };
                if(opts.resizable){//璁惧畾寮圭獥鏄惁鍙互鎷栧姩鏀瑰彉澶у皬
                    var resizeControl = "<div class='resizable-e'></div>" +
                        "<div class='resizable-s'></div>" +
                        "<div class='resizable-w'></div>" +
                        "<div class='resizable-n'></div>" +
                        "<div class='resizable-en'></div>" +
                        "<div class='resizable-es'></div>" +
                        "<div class='resizable-wn'></div>" +
                        "<div class='resizable-ws'></div>";

                    var e = "resizable-e",
                        s = "resizable-s",
                        w = "resizable-w",
                        n = "resizable-n",
                        en = "resizable-en",
                        es = "resizable-es",
                        ws = "resizable-ws",
                        wn = "resizable-wn";

                    $(modalContent).append(resizeControl);

                    $("." + e + "," + "." + s + "," + "." + w + "," + "." + n + "," + "." + en + "," + "." + es + "," + "." + ws + "," + "." + wn).mousedown(function(ev){
                        var getStartX = ev.pageX,
                            getStartY = ev.pageY,
                            getWidth = $(modalContent).width(),
                            getHeight = $(modalContent).height(),
                            getLeft = $(modalContent).offset().left,
                            getTop = $(modalContent).offset().top;
                        $this = $(this);
                        $(document).mousemove(function(ev){
                            var getEndX = ev.pageX,
                                getEndY = ev.pageY;
                            var getOffsetX =getEndX -getStartX,
                                getOffsetY =getEndY -getStartY;
                            if($this.attr("class") == e){$(modalContent).css("width", getWidth + getOffsetX);}
                            else if($this.attr("class") == s){$(modalContent).css("height", getHeight + getOffsetY);}
                            else if($this.attr("class") == w){
                                $(modalContent).css({
                                    "width": getWidth - getOffsetX,
                                    "left": getLeft + getOffsetX
                                });
                            }else if($this.attr("class") == n){
                                $(modalContent).css({
                                    "height": getHeight - getOffsetY,
                                    "top": getTop + getOffsetY
                                });
                            }else if($this.attr("class") == en){
                                $(modalContent).css({
                                    "width": getWidth + getOffsetX,
                                    "height": getHeight - getOffsetY,
                                    "top": getTop + getOffsetY
                                });
                            }else if($this.attr("class") == es){
                                $(modalContent).css({
                                    "width": getWidth + getOffsetX,
                                    "height": getHeight + getOffsetY
                                });
                            }else if($this.attr("class") == ws){
                                $(modalContent).css({
                                    "width": getWidth - getOffsetX,
                                    "height": getHeight + getOffsetY,
                                    "left": getLeft + getOffsetX
                                });
                            }else if($this.attr("class") == wn){
                                $(modalContent).css({
                                    "width": getWidth - getOffsetX,
                                    "height": getHeight - getOffsetY,
                                    "left": getLeft + getOffsetX,
                                    "top": getTop + getOffsetY
                                });
                            }
                        });
                        $(document).mouseup(function(){
                            $(this).unbind("mousemove");
                        });
                    })
                };
                if(callback){
                    callback();
                };
                if(opts.addFunction){
                    opts.addFunction();
                };

            }


            //娉ㄦ剰杩欎釜if...else涓殑鍐呭鍩烘湰涓婃槸涓€鏍风殑锛屽彧鏄负浜嗛槻姝㈠嚭鐜板钁╁湪鍏冪礌涓婂啓鐨刼nclick鐨勬柟娉曟椂 鍑虹幇鏃犻檺寰幆
            writeModal($this);
        });
    };
    $.fn.createModal.defaults = {
        background: '#000000',
        width: '600px',
        height: '500px',
        html: "",
        move: true,
        resizable: true,
        bgClose: false,
        addFunction: function(){}
    };
})(jQuery);
