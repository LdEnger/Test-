$(document).ready(function() {
				var gridster = null;
				gridster = $("#gridsterLayoutSecond ul").gridster({
					avoid_overlapped_widgets : true,

					widget_margins : [ 0.5, 0.5 ],//模块的间距 [上下,左右]

					widget_base_dimensions : [ 40, 40 ],//模块的宽高 [宽,高]

                    extra_cols:1000,

                    // draggable:{
                    //
                    //     start: function(event, ui){
                    //         //alert("statr");
                    //         //console.log("start");
                    //     },
                    //     drag:function(event, ui){
                    //
                    //         //console.log("drag");
                    //     },
                    //     stop: function(event, ui){
                    //         //console.log("stop");
                    //     }
                    // },

                    //设置resize句柄
                    // resize:{
                    //     enabled: 'false',//允许缩放
                    //     // axes: ['both'],
                    //     // handle:'.resize',//html标签的css类名，按住此标签可以对网格进行放缩
                    //     start: function(event, ui,$widget){
                    //         //alert("statr");
                    //         //console.log("start");
                    //     },
                    //     resize: function(event, ui,$widget){
                    //         //alert("resize");
                    //         //console.log("resize");
                    //     },
                    //     stop: function(event, ui,$widget){
                    //         //alert("stop");
                    //         //console.log("stop");
                    //     }
                    // },
                    serialize_params : function($w, wgd) {
                        var id = $w.attr("id");
                        var contentImg = $("#contentImg" + id).val();
                        var content = $("#span" + id).html();
                        var contentId=$("#contentId"+id).val();
                        var contentType=$("#contentType"+id).val();
                        var contentName=$("#contentName"+id).val();
                        var contentSubtitle=$("#contentSubtitle"+id).val();
                        var contentOutcropImg=$("#contentOutcropImg"+id).val();
                        var contentBigPic=$("#contentBigPic"+id).val();
                        var customContentImgSel=$("#customContentImgSel"+id).val();
                        var categoryId= $("#categoryId"+id).val();
                        var chnId= $("#chnId"+id).val();
                        var chnType= $("#chnType"+id).val();
                        var hotId= $("#hotId"+id).val();
                        var hotType= $("#hotType"+id).val();
                        var aqyIsVip= $("#aqyIsVip"+id).val();
                        var apkBagName= $("#apkBagName"+id).val();
                        var specSn= $("#specSn"+id).val();
                        var apkVersionCode= $("#apkVersionCode"+id).val();
                        var apkDownUrl= $("#apkDownUrl"+id).val();
                        var videoId= $("#videoId"+id).val();
                        var videoUrl= $("#videoUrl"+id).val();
                        var action= $("#action"+id).val();
                        var apk= $("#apk"+id).val();
                        var isPlay= $("#isPlay"+id).val();
                        var installStyle= $("#installStyle"+id).val();
                        var appType= $("#appType"+id).val();
                        return {
                            id : id,
                            col : wgd.col,
                            row : wgd.row,
                            size_x : wgd.size_x,
                            size_y : wgd.size_y,
                            contentImg : contentImg,
                            content : content,
                            contentId : contentId,
                            contentType : contentType,
                            contentName : contentName,
                            contentSubtitle : contentSubtitle,
                            contentOutcropImg : contentOutcropImg,
                            contentBigPic : contentBigPic,
                            customContentImgSel : customContentImgSel,
                            categoryId : categoryId,
                            chnId : chnId,
                            chnType : chnType,
                            hotId : hotId,
                            hotType : hotType,
                            aqyIsVip : aqyIsVip,
                            apkBagName : apkBagName,
                            specSn:specSn,
                            apkVersionCode:apkVersionCode,
                            apkDownUrl:apkDownUrl,
                            videoId:videoId,
                            videoUrl:videoUrl,
                            action:action,
                            apk:apk,
                            isPlay:isPlay,
                            installStyle:installStyle,
                            appType:appType
                        }
                    },
				}).data('gridster');

				//
				gridster.disable();
				$(document).on('click','.remove',function() {
							var gridster = $("#gridsterLayoutSecond ul").gridster().data('gridster');
							gridster.remove_widget($(this).parent());
						});

			});

        //相当于templeteGridster.js 中的getLayout方法
	function getContent(templeteId, fatherId, type) {//如果type==1 预览模式，如果type==其他，可编辑模式
		/*$("#reconent_detail_dialog").dialog('open');*/
		var gridster = $("#gridsterLayoutSecond ul").gridster().data('gridster');
        templetContentMap = {};
        $.post(ctx + "/customRecomTempleteList/getContent.json",{"templeteId":templeteId,"fatherId":fatherId},  //这里要注意fatherId代表布局 templeteId代表内容
						function(data) {
            //add的时候templeteId = first的FatherId。edit的时候templeteId就是ID，但是没有对应的layout
				// 			serialization = Gridster.sort_by_row_and_col_asc(data);
                            serialization =data;
                            // console.log(data);
							gridster.remove_all_widgets();
                            gridster.resize_widget_dimensions({widget_base_dimensions: [data[0].row/3, data[0].col/3]});
							$.each(serialization,function() {
                                templetContentMap[this.layoutId] = this.contentId;
                                var butten;
                                var title;
                                var contentTitle;
                                var customContentImg;
                                if (this.contentId == "" || this.contentId == null) {
                                    contentTitle = "未编辑";
                                } else {
                                    contentTitle = this.contentName;
                                }
                                if(this.contentOutcropImg == ""|| this.contentOutcropImg == null){
                                    customContentImg = this.contentImg;
                                }else{
                                    customContentImg = this.contentOutcropImg;
                                }
                                if(this.isPlay == 1){
                                    allTotal ++;
                                }
                                var layoutContentSubtitle = this.contentSubtitle == undefined?'':this.contentSubtitle;
                                var layoutContentOutcropImg = this.contentOutcropImg == undefined?'':this.contentOutcropImg;
                                var layoutContentBigPic = this.contentBigPic == undefined?'':this.contentBigPic;
                                var layoutCategoryId = this.categoryId == undefined?'':this.categoryId;
                                var layoutChnId = this.chnId == undefined?'':this.chnId;
                                var layoutChnType = this.chnType == undefined?'':this.chnType;
                                var layoutHotId = this.hotId == undefined?'':this.hotId;
                                var layoutHotType = this.hotType == undefined?'':this.hotType;
                                var layoutAqyIsVip = this.aqyIsVip == undefined?'':this.aqyIsVip;
                                var layoutApkBagName = this.apkBagName == undefined?'':this.apkBagName;
                                var layoutSpecSn = this.specSn == undefined?'':this.specSn;
                                var layoutApkVersionCode = this.apkVersionCode == undefined?'':this.apkVersionCode;
                                var layoutApkDownUrl = this.apkDownUrl == undefined?'':this.apkDownUrl;
                                var layoutVideoId = this.videoId == undefined?'':this.videoId;
                                var layoutVideoUrl = this.videoUrl == undefined?'':this.videoUrl;
                                var layoutAction = this.action == undefined?'':this.action;
                                var layoutApk = this.apk == undefined?'':this.apk;
                                var layoutIsPlay =this.isPlay == undefined?'':this.isPlay;
                                var layoutInstallStyle =this.installStyle == undefined?'':this.installStyle;
                                var layoutAppType =this.appType == undefined?'':this.appType;

                                var input = '<input id="contentImg'+this.layoutId+'" value="'+this.contentImg+'" type="hidden"/>';
                                var contentId = '<input id="contentId'+this.layoutId+'" value="'+this.contentId+'" type="hidden"/>';
                                var contentType = '<input id="contentType'+this.layoutId+'" value="'+this.contentType+'" type="hidden"/>';
                                var contentName = '<input id="contentName'+this.layoutId+'" value="'+this.contentName+'" type="hidden"/>';
                                var contentSubtitle = '<input id="contentSubtitle'+this.layoutId+'" value="'+layoutContentSubtitle+'" type="hidden"/>';
                                var customContentImgSel = '<input id="customContentImgSel'+this.layoutId+'" value="'+this.customContentImgSel+'" type="hidden"/>';
                                var contentOutcropImg = '<input id="contentOutcropImg'+this.layoutId+'" value="'+layoutContentOutcropImg+'" type="hidden"/>';
                                var contentBigPic = '<input id="contentBigPic'+this.layoutId+'" value="'+layoutContentBigPic+'" type="hidden"/>';
                                var categoryId = '<input id="categoryId'+this.layoutId+'" value="'+layoutCategoryId+'" type="hidden"/>';
                                var chnId = '<input id="chnId'+this.layoutId+'" value="'+layoutChnId+'" type="hidden"/>';
                                var chnType = '<input id="chnType'+this.layoutId+'" value="'+layoutChnType+'" type="hidden"/>';
                                var hotId = '<input id="hotId'+this.layoutId+'" value="'+layoutHotId+'" type="hidden"/>';
                                var hotType = '<input id="hotType'+this.layoutId+'" value="'+layoutHotType+'" type="hidden"/>';
                                var aqyIsVip = '<input id="aqyIsVip'+this.layoutId+'" value="'+layoutAqyIsVip+'" type="hidden"/>';
                                var apkBagName = '<input id="apkBagName'+this.layoutId+'" value="'+layoutApkBagName+'" type="hidden"/>';
                                var specSn = '<input id="specSn'+this.layoutId+'" value="'+layoutSpecSn+'" type="hidden"/>';
                                var apkVersionCode = '<input id="apkVersionCode'+this.layoutId+'" value="'+layoutApkVersionCode+'" type="hidden"/>';
                                var apkDownUrl = '<input id="apkDownUrl'+this.layoutId+'" value="'+layoutApkDownUrl+'" type="hidden"/>';
                                var videoId = '<input id="videoId'+this.layoutId+'" value="'+layoutVideoId+'" type="hidden"/>';
                                var videoUrl = '<input id="videoUrl'+this.layoutId+'" value="'+layoutVideoUrl+'" type="hidden"/>';
                                var action = '<input id="action'+this.layoutId+'" value="'+layoutAction+'" type="hidden"/>';
                                var apk = '<input id="apk'+this.layoutId+'" value="'+layoutApk+'" type="hidden"/>';
                                var isPlay = '<input id="isPlay'+this.layoutId+'" value="'+layoutIsPlay+'" type="hidden"/>';
                                var installStyle = '<input id="installStyle'+this.layoutId+'" value="'+layoutInstallStyle+'" type="hidden"/>';
                                var appType = '<input id="appType'+this.layoutId+'" value="'+layoutAppType+'" type="hidden"/>';
                                title = '<span id="span'+this.layoutId+'" style="color:black;">'+ contentTitle+ '</span>';
                                if (type == 1) {
                                    butten = "" ;

                                    $("#row").val(data[0].row);
                                    $("#col").val(data[0].col);

                                    var maxX = [];
                                    for(var i = 0;i<data.length;i++){
                                        maxX.push(data[i].layoutX);
                                    }

                                    var max =  maxX[0];
                                    if(maxX.length ==1 ){
                                        max =  maxX[0];
                                    }
                                    for(var j = 1;j<maxX.length;j++){

                                        if(max<maxX[j]){
                                            max = maxX[j];
                                        }
                                    }
                                    var num = 0;
                                    for(var k = 0;k<data.length;k++){
                                        if(max == data[k].layoutX){
                                            num =  k;
                                        }
                                    }


                                    var canvas = document.getElementById('a_canvas');

                                    if(data[0].col == 105){
                                        canvas.width =  ((data[num].layoutX-data[num].layoutX%22)/22+1)*22*data[0].row/3+Math.floor(1920/data[0].row)*((data[num].layoutX-data[num].layoutX%22)/22+1);

                                        canvas.height =  Math.floor(1080/data[0].col)*data[0].col/3+Math.floor(1080/data[0].col)*2;

                                        drawTables(canvas, ((data[num].layoutX-data[num].layoutX%22)/22+1)*22, Math.floor(1080/data[0].col));


                                    }else if(data[0].col == 255){
                                        // alert(1);
                                        canvas.width =  ((data[num].layoutX-data[num].layoutX%12)/12+1)*12*data[0].row/3+Math.floor(1920/data[0].row)*((data[num].layoutX-data[num].layoutX%12)/12+1);

                                        canvas.height =  Math.floor(1080/data[0].col)*data[0].col/3+Math.floor(1080/data[0].col)*2;

                                        drawTables(canvas, ((data[num].layoutX-data[num].layoutX%12)/12+1)*12, Math.floor(1080/data[0].col));

                                    }else if(data[0].col == 245){
                                        // alert(3);
                                        canvas.width =  ((data[num].layoutX-data[num].layoutX%20)/20+1)*20*data[0].row/3+Math.floor(1920/data[0].row)*((data[num].layoutX-data[num].layoutX%20)/20+1);

                                        canvas.height =  Math.floor(1080/data[0].col)*data[0].col/3+Math.floor(1080/data[0].col)*2;

                                        drawTables(canvas, ((data[num].layoutX-data[num].layoutX%20)/20+1)*20, Math.floor(1080/data[0].col));

                                    }else if(data[0].col == 95){
                                        // alert(4);
                                        canvas.width =  ((data[num].layoutX-data[num].layoutX%12)/12+1)*12*data[0].row/3+Math.floor(1920/data[0].row)*((data[num].layoutX-data[num].layoutX%12)/12+1);

                                        canvas.height =  Math.floor(1080/data[0].col)*data[0].col/3+Math.floor(1080/data[0].col)*2;

                                        drawTables(canvas, ((data[num].layoutX-data[num].layoutX%12)/12+1)*12, Math.floor(1080/data[0].col));

                                    }

                                    //添加表示每屏宽度的辅助线开始
                                    var spans = ($('#gridsterLayoutSecond ul').width()-$('#gridsterLayoutSecond ul').width()%640)/640;
                                    // console.log(spans);
                                    // console.log("ul的宽度"+$('#gridsterLayoutFirst ul').width());
                                    if(spans>1){
                                        $('#gridsterLayoutSecond').find('span').remove();
                                        for(var x = 1;x <= spans;x++){
                                            var $span = "<span style='position: absolute;top:0;left:"+640*x+"px;width: 0px;background:red;height:348px;border-right:1px solid #000;'></span>";

                                            $('#gridsterLayoutSecond').append($span);
                                        };

                                    };
                                    $("#gridsterLayoutSecond span").css({"height":Math.floor(1080/data[0].col)*data[0].col/3+Math.floor(1080/data[0].col)*2});
                                    //添加表示每屏宽度的辅助线结束

                                } else {
                                    butten = '<input id="b'+ this.layoutId+ '" type="button" value="编辑" onclick="editLayoutContent('
                                        + this.layoutId+ ','+this.layoutW+','+this.layoutH+','+data[0].row+','+data[0].col+')"/>';

                                    $("#row").val(data[0].row);
                                    $("#col").val(data[0].col);

                                    var maxX = [];
                                    for(var i = 0;i<data.length;i++){
                                        maxX.push(data[i].layoutX);
                                    }

                                    var max =  maxX[0];
                                    if(maxX.length ==1 ){
                                        max =  maxX[0];
                                    }
                                    for(var j = 1;j<maxX.length;j++){

                                        if(max<maxX[j]){
                                            max = maxX[j];
                                        }
                                    }
                                    var num = 0;
                                    for(var k = 0;k<data.length;k++){
                                        if(max == data[k].layoutX){
                                            num =  k;
                                        }
                                    }


                                    var canvas = document.getElementById('a_canvas');

                                    if(data[0].col == 105){
                                        canvas.width =  ((data[num].layoutX-data[num].layoutX%22)/22+1)*22*data[0].row/3+Math.floor(1920/data[0].row)*((data[num].layoutX-data[num].layoutX%22)/22+1);

                                        canvas.height =  Math.floor(1080/data[0].col)*data[0].col/3+Math.floor(1080/data[0].col)*2;

                                        drawTables(canvas, ((data[num].layoutX-data[num].layoutX%22)/22+1)*22, Math.floor(1080/data[0].col));


                                    }else if(data[0].col == 255){
                                        // alert(1);
                                        canvas.width =  ((data[num].layoutX-data[num].layoutX%12)/12+1)*12*data[0].row/3+Math.floor(1920/data[0].row)*((data[num].layoutX-data[num].layoutX%12)/12+1);

                                        canvas.height =  Math.floor(1080/data[0].col)*data[0].col/3+Math.floor(1080/data[0].col)*2;

                                        drawTables(canvas, ((data[num].layoutX-data[num].layoutX%12)/12+1)*12, Math.floor(1080/data[0].col));

                                    }else if(data[0].col == 245){
                                        // alert(3);
                                        canvas.width =  ((data[num].layoutX-data[num].layoutX%20)/20+1)*20*data[0].row/3+Math.floor(1920/data[0].row)*((data[num].layoutX-data[num].layoutX%20)/20+1);

                                        canvas.height =  Math.floor(1080/data[0].col)*data[0].col/3+Math.floor(1080/data[0].col)*2;

                                        drawTables(canvas, ((data[num].layoutX-data[num].layoutX%20)/20+1)*20, Math.floor(1080/data[0].col));

                                    }else if(data[0].col == 95){
                                        // alert(4);
                                        canvas.width =  ((data[num].layoutX-data[num].layoutX%12)/12+1)*12*data[0].row/3+Math.floor(1920/data[0].row)*((data[num].layoutX-data[num].layoutX%12)/12+1);

                                        canvas.height =  Math.floor(1080/data[0].col)*data[0].col/3+Math.floor(1080/data[0].col)*2;

                                        drawTables(canvas, ((data[num].layoutX-data[num].layoutX%12)/12+1)*12, Math.floor(1080/data[0].col));

                                    }

                                    //添加表示每屏宽度的辅助线开始
                                    var spans = ($('#gridsterLayoutSecond ul').width()-$('#gridsterLayoutSecond ul').width()%640)/640;
                                    // console.log(spans);
                                    // console.log("ul的宽度"+$('#gridsterLayoutFirst ul').width());
                                    if(spans>1){
                                        $('#gridsterLayoutSecond').find('span').remove();
                                        for(var x = 1;x <= spans;x++){
                                            var $span = "<span style='position: absolute;top:0;left:"+640*x+"px;width: 0px;background:red;height:348px;border-right:1px solid #000;'></span>";

                                            $('#gridsterLayoutSecond').append($span);
                                        };

                                    };
                                    $("#gridsterLayoutSecond span").css({"height":Math.floor(1080/data[0].col)*data[0].col/3+Math.floor(1080/data[0].col)*2});
                                    //添加表示每屏宽度的辅助线结束
                                }
                                var li = '<li class="new" id="'
                                    + this.layoutId
                                    + '" style="background: url('
                                    + customContentImg
                                    + ') center 0 repeat;background-size:cover;/*color:green;*/">'
                                    + butten + input + contentId + contentType + contentName
                                    + contentSubtitle + contentOutcropImg + customContentImgSel + contentBigPic
                                    + categoryId + chnId + chnType + hotId + hotType + aqyIsVip + apkBagName+specSn+apkVersionCode+apkDownUrl+videoId+videoUrl+action+apk+isPlay+installStyle+appType
                                    + title + '</li>';
                                gridster.add_widget(
                                    li,
                                    this.layoutW,
                                    this.layoutH,
                                    this.layoutX,
                                    this.layoutY
                                );
                            });
						}, "json");
		/*$(document).on('click', '.remove', function() {
         var gridster = $(".gridster ul").gridster().data('gridster');
         gridster.remove_widget($(this).parent());
         });*/
	}
	