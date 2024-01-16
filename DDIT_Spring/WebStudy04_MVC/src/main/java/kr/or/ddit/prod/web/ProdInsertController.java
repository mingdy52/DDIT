package kr.or.ddit.prod.web;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.filter.multipart.MultipartFile;
import kr.or.ddit.filter.multipart.StandardMultipartHttpServletRequest;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAtrribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdInsertController{
	ProdService service = new ProdServiceImpl();
	OthersDAO othersDAO = new OthersDAOImpl();
	
	private void addOthersData(HttpServletRequest req){
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList());
	}
	
	@RequestMapping("/prod/prodInsert.do")
	public String prodForm(HttpServletRequest req){
		addOthersData(req);
		return "/prod/prodForm.tiles";
	}
	
	@RequestMapping(value="/prod/prodInsert.do", method=RequestMethod.POST)
	public String insertProcess(@RequestPart("prodImage") MultipartFile imageFile
								, @ModelAtrribute("prod") ProdVO prod
								, HttpServletRequest req) throws IOException{
		addOthersData(req);
//		ProdVO prod = new ProdVO();
//		req.setAttribute("prod", prod);
//		try {
//			BeanUtils.populate(prod, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new RuntimeException(e);
//		}
		
//		if(req instanceof StandardMultipartHttpServletRequest) {
//			MultipartFile imageFile = ((StandardMultipartHttpServletRequest) req).getFile("prodImage");
			prod.setProdImage(imageFile);
//		}
		
		// 상품 이미지 저장 처리(MultipartFile)
		String imageFolderUrl = "/resources/prodImages";
		String imageFolderPath = req.getServletContext().getRealPath(imageFolderUrl);
		File imageFolder = new File(imageFolderPath);
		if(!imageFolder.exists()) imageFolder.mkdirs();
		String imageSaveName = UUID.randomUUID().toString();
		File prodImageFile = new File(imageFolder, imageSaveName);
		
//		MultipartFile imageFile = prod.getProdImage();
		if(!imageFile.isEmpty()) {
			imageFile.transferTo(prodImageFile);
			prod.setProdImg(imageSaveName);
		}
		
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(prod, errors, InsertGroup.class);
		String viewName = null;
		if(valid) {
			ServiceResult result = service.createProd(prod);
			if(ServiceResult.OK.equals(result)) {
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
			}else {
				req.setAttribute("message", "서버 오류");
				viewName = "/prod/prodForm.tiles";
			}
		}else {
			viewName = "/prod/prodForm.tiles";
		}
		return viewName;
	}
}





































