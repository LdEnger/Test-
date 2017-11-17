package cn.com.hiveview.launcher.module.portal.controller;

import cn.com.hiveview.core.util.Constants;
import cn.com.hiveview.core.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Map;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {
	@RequestMapping(value = "/init")
	@ResponseBody
	public Object upload(final HttpServletRequest request,
			final HttpServletResponse respones) {
		String path ="";
		try {
			if (request instanceof MultipartHttpServletRequest) {
				MultipartRequest multipartRequest = (MultipartRequest) request;
				Map<String, MultipartFile> map = multipartRequest.getFileMap();
				for (String key : map.keySet()) {
					MultipartFile multipartFile = map.get(key);
					String fileName = multipartFile.getOriginalFilename();
					if (fileName != null) {
						fileName = fileName.toLowerCase();
						if (!fileName.endsWith(".jpg")
								&& !fileName.endsWith(".png")
								&& !fileName.endsWith(".apk")
								&& !fileName.endsWith(".mp4")
								&& !fileName.endsWith(".3gp")
								&& !fileName.endsWith(".m3u8")
								&& !fileName.endsWith(".gif")) {
							return path;
						}
					}
					path = getUploadPath(multipartFile);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;

	}

	/**
	 * 
	 * @Title: getUploadPath
	 * @Description: 组装上传路径
	 * @param multipartFile
	 * @return
	 * @return String
	 * @throws
	 */
	public String getUploadPath(MultipartFile multipartFile) {

		String path = "";
		String _webPath = "";
		String extName = getFileExtName(multipartFile.getOriginalFilename());
		String fileName = System.currentTimeMillis() + "." + extName;

		int fileType = getFileType(extName);
		switch (fileType) {
		case 1: // 图片
			 path = Constants.UPLOADPATH_IMG;
			_webPath = Constants.WEB_IMG_PATH;
			break;
		case 2: // txt cvs

			break;
		case 3: // apk
			path = Constants.UPLOADPATH_APK;
			_webPath = Constants.WEB_APK_PATH;
			break;
		case 5: // video
			path = Constants.UPLOADPATH_APK;
			_webPath = Constants.WEB_APK_PATH;
			break;
		default: // 4 字幕

			break;
		}
		String webpath = DateUtils.getFilePath();
		try {
			path = path + webpath;
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}

			webpath = webpath + fileName;
			String uploadPath = path + fileName;
			File files = new File(uploadPath);
			multipartFile.transferTo(files);
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _webPath + webpath;
	}

	public static String getFileExtName(String fileName) {
		String extName = "";
		if (fileName != null && !"".equals(fileName)) {
			int i = fileName.lastIndexOf(".");
			if (i > 0) {
				extName = fileName.substring(i + 1, fileName.length());
			}
		}
		return extName;
	}

	public boolean isEmpry(String str) {
		return StringUtils.isEmpty(str);
	}

	// 获得文件类型
	public static int getFileType(String fileName) {
		// String suffix = getFileSuffixName(fileName).toLowerCase();
		String suffix = fileName.toLowerCase();
		if (suffix.equals("jpg") || suffix.equals("png")
				|| suffix.equals("gif") || suffix.equals("ico")
				|| suffix.equals("bmp")) {
			return 1;
		} else if (suffix.equals("txt") || suffix.equals("csv")) {
			return 2;
		} else if (suffix.equals("apk")) {
			return 3;
		} else if (suffix.equals("mp4") || suffix.equals("m3u8")
				|| suffix.equals("3gp")) {
			return 5;
		} else {
			return 4;// 字幕
		}
	}

	// 获得文件后缀名
	public static String getFileSuffixName(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length());
	}

}
