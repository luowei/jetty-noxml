package com.rootls.web;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class Home {

	@RequestMapping("/")
	public ModelAndView home()
	{
		return new ModelAndView("index","me","luowei");
	}

    @RequestMapping("/index")
    public String home(Model model)
    {
        model.addAttribute("me","luowei");
        return "index";
    }

    @RequestMapping("/test")
    public ModelAndView test()
    {
        return new ModelAndView("test","me","luowei");
    }
	
}
