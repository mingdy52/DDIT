package kr.or.ddit.common.controller;

import java.util.Calendar;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {
	@RequestMapping("/index.do")
	public String index(Locale clientLocale, Model model) {
		log.info("locale : {}", clientLocale);
		String now = String.format(clientLocale, "%tc", Calendar.getInstance(clientLocale));
		model.addAttribute("now", now);
		return "index";
	}
}
