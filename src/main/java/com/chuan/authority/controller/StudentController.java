package com.chuan.authority.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author JingChuan
 * @since 2018/8/31 17:57
 */
@RequestMapping("/study")
@Controller
public class StudentController {

    @RequestMapping("/test")
    public String study(Model model){

        model.addAttribute("home",new Date());
        return "study";
    }
}
