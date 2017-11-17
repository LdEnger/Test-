package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by user on 2017/7/25.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class EntranceAppContentApiVo {
    private Integer contentId;
    private String entranceContentName; // 标题
    private String entranceContentFocus; // 副标题
    private Integer seq; // 顺序
    private String entranceContentImage; // 图片
}
