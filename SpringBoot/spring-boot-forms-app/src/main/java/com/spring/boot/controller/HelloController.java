package com.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@RequestMapping(value = "/hellopage", method = RequestMethod.GET)
	public String hello() {
		return "hellopage";
	}

	@RequestMapping(value = "/wish", method = RequestMethod.POST)
	public String wish(@RequestParam("uname") String uname, ModelMap map) {
		map.addAttribute("uname", uname);
		return "wish";
	}
}
