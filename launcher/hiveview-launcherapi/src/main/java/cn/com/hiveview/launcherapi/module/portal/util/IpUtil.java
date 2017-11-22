package cn.com.hiveview.launcherapi.module.portal.util;

import cn.com.hiveview.core.util.Constants;
import cn.com.hiveview.launcherapi.module.portal.service.NavigationService;
import cn.com.hiveview.launcherapi.module.portal.service.PortalLauncherTempletInfoService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2017/7/19.
 */
public class IpUtil {
    private static final Logger logger = LoggerFactory.getLogger(IpUtil.class.getName());
    public static Map<String, String> cityCodeByIp(String ip) {
        Map<String, String> resultMap=new HashMap<String, String>();
        String url = MessageFormat.format(Constants.IP_IsGroupUser,
                Constants.IP_IsGroupServer);
//		String url = "http://211.103.138.119:8097/api/ip/isGroupUser.json";
        StringBuffer buffer = new StringBuffer();
        buffer.append(url).append("?userIP=").append(ip).append("&version=")
                .append(Constants.VERSION);
        try {
            String jsonRes = HttpUtil.reqPost(buffer.toString());
            if (!StringUtils.isEmpty(jsonRes)) {
                JSONObject jsonString = JSONObject.parseObject(jsonRes).getJSONObject("result");
                String province = jsonString.getString("province");
                String ccode = jsonString.getString("ccode");
                resultMap.put("province",province);
                resultMap.put("ccode",ccode);
            }else{
                resultMap.put("province","缺省");
                resultMap.put("ccode","00");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("province","缺省");
            resultMap.put("ccode","00");
        }
        return resultMap;
    }

    public static String getRemoteAddrIp(HttpServletRequest request) {
        String ipFromNginx = request.getHeader("X-Forwarded-For");
        if (StringUtils.isBlank(ipFromNginx)) {
            ipFromNginx = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipFromNginx)) {
            ipFromNginx = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipFromNginx)) {
            ipFromNginx = request.getRemoteAddr();
        }
        int idx = ipFromNginx.indexOf(",");
        return idx > 0 ? ipFromNginx.substring(0, idx) : ipFromNginx;
    }
}
