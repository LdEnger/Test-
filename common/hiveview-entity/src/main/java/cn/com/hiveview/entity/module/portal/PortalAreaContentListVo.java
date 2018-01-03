package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user on 2017/10/6.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PortalAreaContentListVo implements Serializable {
    private Integer id;
    private String contentName;//标题
    private Integer seq;//顺序
    private Integer seqIsTop;//置顶字段
    private Integer areaId;//专区管理ID
    private String areaContent;//内容介绍
    private String recommendImg;//推荐位图片
    private Integer contentId;//内容id
    private Integer areaType;//跳转状态
    private Integer recommendType;//推荐位类型
    private String packageName;//包名
    private Integer areaTemplate;//模版
    private Integer channelType;//频道类型
    private Integer channel;//频道
    private Integer payState;//付费(0全部,1免费,2大麦付费,3爱奇异付费)
    private Integer vipState;//vip(0全部,1非VIP,2大麦VIP,3爱奇异VIP)
    private Integer videoId;//关联剧集id
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private String relateContent;//关联内容（冗余字段）
    private String version;
}
