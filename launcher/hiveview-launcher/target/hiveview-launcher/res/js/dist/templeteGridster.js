$(document).ready(function() {
    // var maxNumber = Number.POSITIVE_INFINITY;
    // var canvas = document.getElementById('a_canvas');
    // drawTables(canvas, 120, 6);
    var init_row = 0;
    var init_sizey = 0;
    gridster = $("#gridsterLayoutFirst ul").gridster({
        avoid_overlapped_widgets : true,
        widget_margins : [ 0.5, 1.5],//模块的间距 [上下,左右]
        widget_base_dimensions : [0, 0],//模块的宽高 [宽,高]
        extra_cols:1000,
        // autogrow_rows:false,
        // shift_larger_widgets_down: false,
        //
        // autogenerate_stylesheet: true,
        // autogenerate_stylesheet: true,
        // max_cols:3,
        // max_rows: 4,
        // max_cols: 24,
        // min_rows:2,
        /* max_cols: 24,  */                           //最多创建多少列，null表示没有限制
        //拖拽时触发函数
        draggable : {
            start : function(event, ui) {
            },
            drag : function(event, ui) {

            },                                                                                       //drag结束
            stop : function(event, ui) {

            }                                                              //drag stop结束
        },

        //设置resize句柄
        resize : {
            // max_size:[2, 2],
            enabled :true,//允许缩放
            axes: ['both'],
            handle:'.resize',//html标签的css类名，按住此标签可以对网格进行放缩
            //改变大小时触发函数
            // max_size: [5, 5],
            // min_size: [1, 1],
            start : function(event, ui, $widget) {
                // init_row = $widget.attr('data-row');
                // init_sizey = $widget.attr('data-sizey');
                //
                // console.log(init_row);
                // console.log(init_sizey);
                var gridster = $("#gridsterLayoutFirst ul").gridster().data('gridster');
                var json = gridster.serialize();// 得到 所有的 widget    //拿到所有的布局信息,向后台传数据方法1 拿到的是templeteGridster.js 中48行中的所有数据
                layoutJson1 = Gridster.sort_by_row_and_col_asc(json);////拿到所有的布局信息,向后台传数据方法2
                //
                // console.log(layoutJson1);
            },
            resize : function(event, ui, $widget) {
                // console.log(type);
                var rrow = $widget.attr('data-row');
                var rsize_y = $widget.attr('data-sizey');
                //
                // console.log(rrow);
                // console.log(rsize_y);
                // if(type == 1){
                //     var sum=(rrow-0)+(rsize_y-0);
                //     // console.log(sum);
                //     if(sum>3){
                //         // $widget.attr('data-row',init_row)  ;
                //         // $widget.attr('data-sizey',init_sizey) ;
                //         // $('#gridsterLayoutFirst ul').css({"height":"360px"});
                //         // $('#gridsterLayoutFirst').css({"height":"360px"});
                //         var gridster = $("#gridsterLayoutFirst ul").gridster().data('gridster');
                //         gridster.remove_all_widgets();
                //
                //         $.each(layoutJson1,
                //             function() {
                //
                //                 var title = '<input type="button" class="remove" value="关闭" />'
                //                 var position = '<input id="position' + this.id + '" value="1" type="hidden"/>';
                //                 var li = '<li class="new" id=' + this.id + '>' + title + position + '</li>';
                //
                //                 gridster.add_widget(
                //                     li,
                //                     this.size_x,
                //                     this.size_y,
                //                     this.col,
                //                     this.row
                //                 );
                //         })
                //     }
                // };
            },
            stop : function(event, ui, $widget) {
                // console.log($widget.attr("data-col"));
                // console.log($widget.attr("data-row"));
                // console.log($widget.attr("data-sizex"));
                // console.log($widget.attr("data-sizey"));
                // console.log($widget.attr("id"));
                // console.log($widget.attr("id"));
                var last_uuid1 = $("#uuid").val();
                // console.log($('#'+last_uuid1).attr('id'));
                if($widget.attr("id") == $('#'+last_uuid1).attr('id')){
                    // var size_x = $widget.attr("data-row")+$widget.attr("data-sizey")-1;
                    size_y1=($widget.attr("data-col")-0)+($widget.attr("data-sizex")-0);
                    // console.log(size_y1);
                };

                // console.log(type);
                // var rrow = $widget.attr('data-row');
                // var rsize_y = $widget.attr('data-sizey');
                // //
                // // console.log(rrow);
                // // console.log(rsize_y);
                // if(type == 1){
                //     var sum=(rrow-0)+(rsize_y-0);
                //     // console.log(sum);
                //     if(sum>3){
                //         // $widget.attr('data-row',init_row)  ;
                //         // $widget.attr('data-sizey',init_sizey) ;
                //         // $('#gridsterLayoutFirst ul').css({"height":"360px"});
                //         // $('#gridsterLayoutFirst').css({"height":"360px"});
                //         var gridster = $("#gridsterLayoutFirst ul").gridster().data('gridster');
                //         gridster.remove_all_widgets();
                //
                //         $.each(layoutJson1,
                //             function() {
                //
                //                 var title = '<input type="button" class="remove" value="关闭" />'
                //                 var position = '<input id="position' + this.id + '" value="1" type="hidden"/>';
                //                 var li = '<li class="new" id=' + this.id + '>' + title + position + '</li>';
                //
                //                 gridster.add_widget(
                //                     li,
                //                     this.size_x,
                //                     this.size_y,
                //                     this.col,
                //                     this.row
                //                 );
                //             })
                //     }
                // };
            }
        },

        //$w为要输出位置的网格对象（li）, wgd为该网格对象的坐标对象，包括col，row，size



        //包含每个模块的属性信息 点击下方按钮添加模块的时候 用到了这个方法

        //比如下面的100行的 getLayout 方法 中的positionSeq字段就用到了

        //可以理解为,除了5个基本属性,要想使用其他的属性 就要先获取到 存在这里
        serialize_params : function($w, wgd) {
            var id = $w.attr("id");
            var positionSeq=$("#positionSeq"+id).val();
            return {
                id : id,
                col : wgd.col,
                row : wgd.row,
                size_x : wgd.size_x,
                size_y : wgd.size_y,
                positionSeq : positionSeq
            }
        },


        //模块重叠时触发函数
        /* collision : {
         on_overlap_start : function(collider_data) {
         console.log("on_overlap_start");
         },
         on_overlap : function(collider_data) {
         console.log("on_overlap");
         },
         on_overlap_stop : function(collider_data) {
         console.log("on_overlap_stop");
         }
         } */
    });
    $('#gridsterLayoutFirst ul').css({'padding': '0'});
    //如果新增的模块超出了gridster的底边界,移除此模块的方法
    $(document).on('click', '.remove', function() {
        size_y1 = 0;
        var gridster = $("#gridsterLayoutFirst ul").gridster().data('gridster');
        // size_y1 = $(this).parent().attr("data-col")-0;
        // $("#uuid").val($(this).parent().attr("id"));
        gridster.remove_widget($(this).parent());
        // console.log(this.parentNode.getAttribute("id"));
        // $("#uuid").val(this.parentNode.getAttribute("id"));
    });
});

//失效
function gridsterDisable() {
    var gridster = $("#gridsterLayoutFirst ul").gridster().data('gridster');
    gridster.disable();
}

//生效的方法
function gridsterEnable() {
    var gridster = $("#gridsterLayoutFirst ul").gridster().data('gridster');
    gridster.enable( );
}

// //适应内容大小
// function fit_to_content() {
//     var gridster = $("#gridsterLayoutFirst ul").gridster().data('gridster');
//     gridster.fit_to_content( );
// }

//点击下面的按钮,添加模块,如果超出了下边界,就自动删掉 //data-row和data-sizey都是自动生成的 直接拿来用就可以
function maxRow(type,uuid){
    if(type == 1){
        var row=$('#'+uuid).attr('data-row');
        var size_y=$('#'+uuid).attr('data-sizey');
        var sum=(row-0)+(size_y-0);
        if(sum>5){
            var gridster = $("#gridsterLayoutFirst ul").gridster().data('gridster');
            gridster.remove_widget($('#'+uuid));
            // $('#gridsterLayoutFirst ul').attr({"style":"height:360px;background:grey;position:absolute;width:"+max*$('#'+uuid).width()+$('#'+uuid).attr('data-col')*2});
        }
    };

    if(type == 2){
        var row=$('#'+uuid).attr('data-row');
        var size_y=$('#'+uuid).attr('data-sizey');
        var sum=(row-0)+(size_y-0);
        if(sum>11){
            var gridster = $("#gridsterLayoutFirst ul").gridster().data('gridster');
            gridster.remove_widget($('#'+uuid));
        }
    };

    if(type == 3){
        var row=$('#'+uuid).attr('data-row');
        var size_y=$('#'+uuid).attr('data-sizey');
        var sum=(row-0)+(size_y-0);
        if(sum>5){
            var gridster = $("#gridsterLayoutFirst ul").gridster().data('gridster');
            gridster.remove_widget($('#'+uuid));
        }
    };

    if(type == 4){
        var row=$('#'+uuid).attr('data-row');
        var size_y=$('#'+uuid).attr('data-sizey');
        var sum=(row-0)+(size_y-0);
        if(sum>12){
            var gridster = $("#gridsterLayoutFirst ul").gridster().data('gridster');
            gridster.remove_widget($('#'+uuid));
        }
    }
}


//从json文件里读取json并绘制出自定义布局                   //这个方法编辑预览的时候用到了,已经存在数据库中,只是从数据库中取出来的时候才用到
function getLayout(templeteId,type) {
    var gridster = $("#gridsterLayoutFirst ul").gridster().data('gridster');
    $.post(ctx +'/customRecomTempleteList/getLayout.json', {
            'templeteId' : templeteId
        },
        function(data) { //取出的所有布局信息
            // console.log(typeof data);
            // console.log(data);
            // serialization = Gridster.sort_by_row_and_col_asc(data);
            // console.log(serialization);
            serialization =data;
            gridster.remove_all_widgets();
            gridster.resize_widget_dimensions({widget_base_dimensions: [data[0].row/3, data[0].col/3]});

            $.each(serialization,
                function() {
                    if(type == 1){//预览模式
                        remove = '<span id="b'+this.layoutId+'" style="color:black;display:inline-block;margin-left: 5px;font-size: 16px;">'+ data[0].row*this.layoutW+ 'x'+data[0].col*this.layoutH+'</span>'
                            +'<span class="remove"></span>';
                        gridster.disable_resize();

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
                        // console.log(num);

                        $("#uuid").val(data[num].layoutId);

                        // console.log($("#"+data[0].layoutId).width());
                        //
                        // var id = data[num].layoutId;
                        // // console.log((data[num].layoutX * $("#"+data[num].layoutId).width())*2+data[num].layoutX);
                        // //
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

                        $("#gridsterLayoutFirst p").css({"height":Math.floor(1080/data[0].col)*data[0].col/3+Math.floor(1080/data[0].col)*2});

                        var positionSeq = '<input id="positionSeq'+this.layoutId+'" value="'+this.positionSeq+'" type="hidden"/>';
                        var li = '<li class="new" id="'+this.layoutId+'">'+ remove+ positionSeq +'</li>';

                    }else{
                        /*remove = '<input type="button" value="编辑" onclick="editTempelte('+ this.layoutId+ ')"/>'+*/
                        gridster.enable_resize();

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

                            $('#addTemplete5').hide();

                        }else if(data[0].col == 255){
                            // alert(1);
                            canvas.width =  ((data[num].layoutX-data[num].layoutX%12)/12+1)*12*data[0].row/3+Math.floor(1920/data[0].row)*((data[num].layoutX-data[num].layoutX%12)/12+1);

                            canvas.height =  Math.floor(1080/data[0].col)*data[0].col/3+Math.floor(1080/data[0].col)*2;

                            drawTables(canvas, ((data[num].layoutX-data[num].layoutX%12)/12+1)*12, Math.floor(1080/data[0].col));

                            $('#addTemplete5').show();

                        }else if(data[0].col == 245){
                            // alert(3);
                            canvas.width =  ((data[num].layoutX-data[num].layoutX%20)/20+1)*20*data[0].row/3+Math.floor(1920/data[0].row)*((data[num].layoutX-data[num].layoutX%20)/20+1);

                            canvas.height =  Math.floor(1080/data[0].col)*data[0].col/3+Math.floor(1080/data[0].col)*2;

                            drawTables(canvas, ((data[num].layoutX-data[num].layoutX%20)/20+1)*20, Math.floor(1080/data[0].col));

                            $('#addTemplete5').hide();

                        }else if(data[0].col == 95){
                            // alert(4);
                            canvas.width =  ((data[num].layoutX-data[num].layoutX%12)/12+1)*12*data[0].row/3+Math.floor(1920/data[0].row)*((data[num].layoutX-data[num].layoutX%12)/12+1);

                            canvas.height =  Math.floor(1080/data[0].col)*data[0].col/3+Math.floor(1080/data[0].col)*2;

                            drawTables(canvas, ((data[num].layoutX-data[num].layoutX%12)/12+1)*12, Math.floor(1080/data[0].col));

                            $('#addTemplete5').hide();
                        }

                        $("#gridsterLayoutFirst p").css({"height":Math.floor(1080/data[0].col)*data[0].col/3+Math.floor(1080/data[0].col)*2});


                        $("#uuid").val(data[num].layoutId);
                        rrr = Math.floor(1920/data[0].row);
                        ccc =   Math.floor(1080/data[0].col);

                        cWidth = (rrr*data[0].row/3)*2+rrr;
                        cHeight = ccc*data[0].col/3+ccc*2;

                        var positionSeq = '<input id="positionSeq'+this.layoutId+'" value="'+this.positionSeq+'" type="hidden"/>';
                        var  remove ='<button class="remove">关闭</button>';
                        var li = '<li class="new" id="'+this.layoutId+'">'+ remove+ positionSeq +'</li>';
                    }



                    gridster.add_widget(
                        li,
                        this.layoutW,
                        this.layoutH,
                        this.layoutX,
                        this.layoutY
                    );


                });

            var removes1 = [];
            $(".remove").each(function(ele,index){
                removes1.push($(this));

            });



            // console.log(removes1);

            for(var i = 0;i<removes1.length;i++) {
                if (removes1[i].parent().attr("data-sizex") == 3) {
                        $("#addTemplete5").attr("disabled", true);
                        // alert(0);
                }
            }

            // console.log(removes1);

            for(var i = 0;i<removes1.length;i++){
                if(removes1[i].parent().attr("data-sizex") == 3){
                    removes1[i].click(function(){
                        $("#addTemplete5").removeAttr("disabled");
                    });
                }
            }


        });

    $(document).on('click', '.remove', function() {
        var gridster = $(".gridster ul").gridster().data('gridster');
        gridster.remove_widget($(this).parent());
    });


}