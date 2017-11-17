
function getTemplete(gridsters,data) {

    // console.log(data);
    var tabs = $("#modalbody");
    tabs.empty();
    for(var i =0;i<data.length;i++){
        var uuid = (new Date().getTime()) ^ Math.random();
        var len = data[i].length-1;
        var str = $("#template").html();
        str = str.replace("#divId#","gridsterLayoutFirst"+uuid);
        str = str.replace("#span#","0px");
        str = str.replace("#title#",data[i][len]);
        str = str.replace("#ul#","35px");
        str = str.replace("#id#","ul"+i);
        str = str.replace("#canvas#","35px");
        str = str.replace("#canvasId#","canvas"+i);
        tabs.append(str);
        // tabs.css({"height":(360*i+360)+"px"});

        var canvas = document.getElementById("canvas"+i);

        var maxX = [];
        for(var k = 0;k<data[i].length;k++){
            maxX.push(data[i][k].layoutX);
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
        for(var p = 0;p<data[i].length;p++){
            if(max == data[i][p].layoutX){
                num =  p;
            }
        }

        if(data[i][0].col == 105){
            canvas.width =  ((data[i][num].layoutX-data[i][num].layoutX%22)/22+1)*22*data[i][0].row/3+Math.floor(1920/data[i][0].row)*((data[i][num].layoutX-data[i][num].layoutX%22)/22+1);

            canvas.height =  Math.floor(1080/data[i][0].col)*data[i][0].col/3+Math.floor(1080/data[i][0].col);

            drawTables(canvas, ((data[i][num].layoutX-data[i][num].layoutX%22)/22+1)*22, Math.floor(1080/data[i][0].col));


        }else if(data[i][0].col == 255){
            // alert(1);
            canvas.width =  ((data[i][num].layoutX-data[i][num].layoutX%12)/12+1)*12*data[i][0].row/3+Math.floor(1920/data[i][0].row)*((data[i][num].layoutX-data[i][num].layoutX%12)/12+1);

            canvas.height =  Math.floor(1080/data[i][0].col)*data[i][0].col/3+Math.floor(1080/data[i][0].col);

            drawTables(canvas, ((data[i][num].layoutX-data[i][num].layoutX%12)/12+1)*12, Math.floor(1080/data[i][0].col));

        }else if(data[i][0].col == 245){
            // alert(3);
            canvas.width =  ((data[i][num].layoutX-data[i][num].layoutX%20)/20+1)*20*data[i][0].row/3+Math.floor(1920/data[i][0].row)*((data[i][num].layoutX-data[i][num].layoutX%20)/20+1);

            canvas.height =  Math.floor(1080/data[i][0].col)*data[i][0].col/3+Math.floor(1080/data[i][0].col);

            drawTables(canvas, ((data[i][num].layoutX-data[i][num].layoutX%20)/20+1)*20, Math.floor(1080/data[i][0].col));

        }else if(data[i][0].col == 95){
            // alert(4);
            canvas.width =  ((data[i][num].layoutX-data[i][num].layoutX%12)/12+1)*12*data[i][0].row/3+Math.floor(1920/data[i][0].row)*((data[i][num].layoutX-data[i][num].layoutX%12)/12+1);

            canvas.height =  Math.floor(1080/data[i][0].col)*data[i][0].col/3+Math.floor(1080/data[i][0].col);

            drawTables(canvas, ((data[i][num].layoutX-data[i][num].layoutX%12)/12+1)*12, Math.floor(1080/data[i][0].col));

        }





        gridsters[i] = $("#gridsterLayoutFirst"+uuid+" ul").gridster({
            namespace:"#gridsterLayoutFirst"+uuid,

            avoid_overlapped_widgets : true,

            widget_margins : [ 0.5, 0.5 ],//模块的间距 [上下,左右]

            extra_cols:1000,

            widget_base_dimensions : [data[i][0].row/3, data[i][0].col/3]//模块的宽高 [宽,高]

        }).data('gridster');

        gridsters[i].remove_all_widgets();
        // gridster.resize_widget_dimensions({widget_base_dimensions: [data[i][0].row/3, data[i][0].col/3]});
        gridsters[i].disable();


        data[i].pop();


        $.each(data[i],function(){

            // var li = '<li style="border:1px solid #000;background:url("+this.contentImg+") center 0 repeat;background-size:cover;"></li>';

            var li = '<li style="border:1px solid rgba(0,0,0,0);background: url('
                + this.contentImg
                + ') center 0 repeat;background-size:cover;" id='+this.layoutId+'></li>';


            gridsters[i].add_widget(
                li,
                this.layoutW,
                this.layoutH,
                this.layoutX,
                this.layoutY
            );
        });

        //添加表示每屏宽度的辅助线开始
        var spans = ($("#gridsterLayoutFirst"+uuid+" ul").width()-$("#gridsterLayoutFirst"+uuid+" ul").width()%640)/640;
        // console.log(spans);
        // console.log($("#gridsterLayoutFirst"+uuid+" ul").width());
        // console.log("ul的宽度"+$('#gridsterLayoutFirst ul').width());
        if(spans>1){
            $("#gridsterLayoutFirst"+uuid).find('p').remove();
            for(var x = 1;x <= spans;x++){
                var $p = "<p style='position: absolute;top:35px;left:"+640*x+"px;width: 0px;background:red;height:348px;border-right:1px solid #000;'></p>";

                $("#gridsterLayoutFirst"+uuid).append($p);
            };

        };
        $("#gridsterLayoutFirst"+uuid).find('p').css({"height":Math.floor(1080/data[i][0].col)*data[i][0].col/3+Math.floor(1080/data[i][0].col)});
        //添加表示每屏宽度的辅助线结束


    }

};






