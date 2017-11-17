package cn.com.hiveview.entity.module.website.news;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by user on 2017/9/19.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class NewsVo {
    private Long newsId; //新闻id
    private String newsName;   //新闻名称
    private String surfaceImg;   //新闻封面图
    private String newsAbstract;   //摘要
    private String newsFrom;  //新闻来源
    private String newsUrl;  //来源链接
    private String newsClassification;  //新闻分类
    private String newsContentDetial;  //新闻内容详情
    private int status;  //显示状态   0显示，-1隐藏，默认0
    private int sort;  //排序，若排序相同，后创建优先
    private String createTime;  //创建时间
    private String updateTime;  //修改时间
    private int deleteStatus;  //删除状态 0为删除，-1为不删除，默认为0
}
