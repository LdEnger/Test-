package cn.com.hiveview.launcher.module.fixedRecom.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/31.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class NewContentChanCondition extends BaseCondition implements Serializable {
    public Integer Id;
    public Integer templetId;
    public String templetName;
    public Integer apkId;
    public String apkBagName;
    public String apkName;
    public Integer chnId;
    public Integer chnType;
    public String chnName;//频道名称
    public Integer programsetId;
    public Integer programsetType;
    public Integer status;//进阶状态  3 计费  2 待上线 1上线
    public Integer top;//优先级状态   1 为当前可用   0 为不够优先
    public Integer isVip;//是否VIP
    public Integer chargingType;//是否收费
    public String upTime;//最后上线时间
    public String downTime;//最后下线时间
    public String aqyId;//爱奇艺
    public String jqId;//极清
    public String createTime;
    public String updateTime;
    public String hkdbId;//回看点播
    public String thirdId;//第三方
    public String albumName;
    public String albumEnglishName;
    public String albumAlias;
    public Integer episodeTotal;
    public Integer episodeUpdated;
    public String labels;//标签
    public String directors;
    public String mainActors;
    public String actors;
    public String producer;//制片人
    public String composers;//作曲
    public String lyricists;//作词
    public Integer year;//所属年份
    public String focus;
    public Integer is3d;//是否3D
    public Integer is4k;//是否4k
    public Integer isDb;//是否杜比
    public String duration;//时长
    public String preDuration;//预告片时长
    public String preM3u8;//预告片m3u8地址
    public String albumHbPic;//专辑海报图
    public String albumXqyPic;//详情页图片
    public String albumXPic;//横图
    public String albumSltPic;//缩略图
    public String season;//季
    public String phase;//期
    public Integer isEffective;//状态
    public String keyword;//
    public String albumType;//专辑类型 0单剧集 1多剧集
    public String albumDesc;//描述
    public Integer seq;//排序
    public String area;//国家
    public String chinaBoxOffice;//中国票房
    public String northBoxOffice;//北美票房
    public Integer language;//语言
    public Integer aqyIsEffective;
    public Integer jqIsEffective;
    public Integer thirdIsEffective;
    public Integer hkdbIsEffective;
    public Integer sourceType;
    public String issueTime;
    public String albumYltFirstPic;//预览图1
    public String albumYltSecondPic;//预览图2
    public String albumYltThirdPic;//预览图3
    public String albumYltFourthPic;//预览图4
    public String albumYltFifthPic;//预览图5
    public Integer source;
    public String playCount;//点击数
    public Integer sType;// 1 来源专辑  0 普通专辑
    public String score;
    //	/** -----新增----- */
    private Integer otype;        //详情类型：0 视频1 专辑
    private Integer epIsDown;     //此视频是否允许下载，0 为都不可下载； 1 为是所有人可以下载; 2 只有会员账号才可以下载
    private Integer epIsTvod; 	//默认的视频是否点播视频：0 非点播 1 点播
    private Integer epIsVip; 	//默认的视频是否会员视频：0 非会员 1 会员
    private Integer isTvod;  	//是否点播视频：0 非点播 1 点播
    private Integer epIsPkg; 	//默认的视频是否点播套餐视频：0 非点播套餐 1 点播套餐
    private Integer aqyIsVip;  	//是否会员视频：0 非会员 1 会员
    private Integer isCoupon; 	//是否点播券视频：0 非点播券 1 点播券
    private Integer isPkg;   	//是否点播套餐视频：0 非点播套餐 1 点播套餐
    private Integer epIsCoupon; //默认的视频是否点播券视频：0 非点播券 1 点播券
    private String drm;//drm 属性，多个用逗号分隔；1:DRM_NONE 2:DRM_INTERTRUST3:DRM_CHINA
    private Integer epOrgPrc;
    private String  epValidTime;
    private String idemand;
    private String sname;
    private String excl;
    private Integer orgPrc;
    private String  validTime;
    private String cpName;
}
