package cn.com.hiveview.entity.module.website.recruitment;

import cn.com.hiveview.entity.module.website.classification.ClassficationVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by user on 2017/9/19.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class RecruitmentVo {
    private Long positionId; //职位id
    private String positionName;   //职位名称
    private String workPlace;   //工作地点
    private int positionClassficationId;   //职位分类id
    private String positionTypeName;    //职位分类名称
    private String salaryLow;   //最低薪资限度
    private String salaryHight;   //最高工资限度
    private String workExperience;   //工作经验
    private String educationLevel;   //教育水平id
    private String workType;   //职位类型
    private String email;   //接受简历邮箱
    private String workDescription ;   //职位描述
    private String responseBilities;   //任职资格
    private int stauts;  //显示状态   0显示，-1隐藏，默认0
    private int sort;  //排序，若排序相同，后创建优先
    private String createTime;  //创建时间
    private String updateTime;  //修改时间
    private int deleteStatus;  //删除状态 0为删除，-1为不删除，默认为0
    private List<ClassficationVo> classficationVoList;
    private String elseEmail;//部门邮箱
}
