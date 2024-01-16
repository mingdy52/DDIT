package kr.or.ddit.blog.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.admin.vo.RevenueVO;
import kr.or.ddit.blog.service.BlogWriteService;
import kr.or.ddit.blog.service.MyBlogService;
import kr.or.ddit.blog.vo.BlogCateVO;
import kr.or.ddit.blog.vo.MyBlogPostVO;
import kr.or.ddit.common.CheckMember;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.enumpkg.RoleGroup;
import kr.or.ddit.exception.NotAuthorityException;
import kr.or.ddit.proj.post.vo.MyPostVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 박채록
 * @since 2022. 8. 23.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 23.      박채록       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/blog")
@MultipartConfig
public class BlogWriteController {
   
   @Inject
   BlogWriteService blogservice;
   
   @Inject
   MyBlogService myblogService;
   
   @Inject
   CheckMember check;

   public boolean checkMember(String memId, Model model) {
      boolean result = false;
      int cnt =myblogService.checkMemId(memId);
      if(cnt==0) {
         model.addAttribute("check", "check");
         result = true;
      }
      return result;
   }
   
   @GetMapping("{memId}/post")
   public String WriteForm(
         @PathVariable("memId") String memId
         , Model model) {
      
      RoleGroup result = check.checkBlog(memId);
      if(result.equals(RoleGroup.OWNER)) {
         MyBlogPostVO mypost = new MyBlogPostVO();
         mypost.setBlogerId(memId);
         List<BlogCateVO> category = myblogService.retrieveCate(memId);
         model.addAttribute("mypost", mypost);
         model.addAttribute("category", category);
         return "noTiles/wirteForm";
      } else {
         throw new NotAuthorityException();
      }
      
   }
   
   @PostMapping("{memId}/post")
   public String WirteProcess(
         @PathVariable("memId") String memId
         , @ModelAttribute("mypost") MyBlogPostVO mypost
         , Errors error) {
      String viewName = "";
      String thumnail = mypost.getThumnail();
      
      if(thumnail.isEmpty()) {
         mypost.setThumnail("/Otion/resources/images/noImg.png");
      }
      
      if(!error.hasErrors()) {
         blogservice.createMypost(mypost);
         viewName="redirect:/blog/{memId}";
         log.info(mypost.getPostContent());
      }else {
    	  
         viewName="redirect:/blog/{memId}/post";
      }
      
      return viewName;
   }   
   
   //게시글 수정
   @GetMapping("{memId}/post/{postNum}/form") 
   public String modifyPost(
         @PathVariable("memId") String memId
         , @PathVariable("postNum") String postNum
         , Model model 
         ) {
      
      
      RoleGroup result = check.checkBlog(memId);
      if(result.equals(RoleGroup.OWNER)) {
         MyBlogPostVO mypost =  myblogService.retrievePost(memId, postNum);
         log.info("mypost:{}", mypost.getAttatchList());
         List<BlogCateVO> category = myblogService.retrieveCate(memId);
         model.addAttribute("mypost", mypost);

         model.addAttribute("category", category);

         return "noTiles/updateForm";
      } else {
         throw new NotAuthorityException();

      }
      
   }
   
   @PostMapping("{memId}/post/{postNum}/form") 
   public String modifyProcess(
         @PathVariable("memId") String memId
         , @PathVariable("postNum") String postNum
         , @ModelAttribute("mypost") MyBlogPostVO mypost
         , @RequestParam(value="delAttatchNum", required=false) String[] delAttatchNum
         , @RequestParam(value="delAttatchOrder", required=false) int[] delAttatchOrder
         , Errors error) {

		Map<String, Object> map = new HashMap<>();
		map.put("mypost", mypost);
		map.put("attatchNum", delAttatchNum);
		map.put("attatchOrder", delAttatchOrder);
		String thumnail = mypost.getThumnail();
		String viewName = "";

		if (thumnail.isEmpty()) {
			mypost.setThumnail("/Otion/resources/images/noImg.png");
		}
		if (!error.hasErrors()) {
			blogservice.modifyPost(map);
			viewName = "redirect:/blog/{memId}/post/{postNum}";
		} else {
			viewName = "redirect:/blog/{memId}/post/{postNum}/form";
		}

      return viewName;
   }
   
   // 파일 다운로드
   @ResponseBody
   @GetMapping("{memId}/post/{postNum}/down")
   public void downloadExcel(
         @RequestParam(name="attnum") String attnum
         , @RequestParam(name="attorder") String attorder
         ) {
      
      
   }


}