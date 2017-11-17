package cn.com.hiveview.core.captcha;

import lombok.Data;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.output.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by lgy on 2016/8/1.
 */
public final class PictureGeneratorUtils {

    public CaptchaBean getImageBase64() {
        CaptchaBean captchaBean = new CaptchaBean();

        try {
            char[] charArray = CaptchaUtil.generateCaptcha();
            String key = new String(charArray);
            key = key.toLowerCase();
            captchaBean.setKey(key);

            BufferedImage bufferedImage = CaptchaUtil.generateImage(charArray);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            //读取图片字节数组
            ImageIO.write(bufferedImage, "png", outputStream);
            byte[] data = outputStream.toByteArray();
            String value = new String(Base64.encodeBase64(data));
            captchaBean.setValue(value);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return captchaBean;
    }

    @Data
    public class CaptchaBean {
        private String key;
        private String value;
    }

    public static void main(String[] args) {
        PictureGeneratorUtils.CaptchaBean captchaBean = new PictureGeneratorUtils().getImageBase64();
        System.out.println(captchaBean.toString());
    }
}
