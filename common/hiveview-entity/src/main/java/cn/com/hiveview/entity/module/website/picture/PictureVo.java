package cn.com.hiveview.entity.module.website.picture;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by user on 2017/9/19.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PictureVo {
    private Long pictureId; //轮播图id
    private String pictureName;   //图片名称
    private String pictureSaveUrl; //图片服务器地址
    private String pictureUrl;   //图片链接地址
    private int status;  //显示状态   0显示，-1隐藏，默认0
    private int sort;  //排序，若排序相同，后创建优先
    private String createTime;  //创建时间
    private String updateTime;  //修改时间
    private int deleteStatus;  //删除状态 0为删除，-1为不删除，默认为0
}
