package cn.com.hiveview.launcher.module.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chimeilong on 2017/6/29.
 */
@Controller
public class DecoratorController {
    @RequestMapping(value = "decorator")
    public String decorator(HttpServletRequest request, Model model) {

        return "derorator/decorator";
    }
}
