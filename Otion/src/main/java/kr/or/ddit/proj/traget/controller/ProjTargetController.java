package kr.or.ddit.proj.traget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project/target")
public class ProjTargetController {

	@GetMapping
	public String get(){
		return "/proj/target/targetHome";
	}
	
	@PostMapping
	public String post() {
		return "/proj/form/targetForm";
	}
	
	@PutMapping
	public String modify() {
		return "/proj/target/targetHome";
	}
	
	@DeleteMapping
	public String delete() {
		return "/proj/target/targetHome";
	}
}
