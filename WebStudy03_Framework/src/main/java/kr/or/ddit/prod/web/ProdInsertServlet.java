package kr.or.ddit.prod.web;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.filter.multipart.MultipartFile;
import kr.or.ddit.filter.multipart.StandardMultipartHttpServletRequest;
import kr.or.ddit.mvc.DelegatingViewResolver;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
@MultipartConfig
public class ProdInsertServlet extends HttpServlet {
	ProdService service = new ProdServiceImpl();
	OthersDAO otherDAO = new OthersDAOImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("lprodList", otherDAO.selectLprodList());
		req.setAttribute("buyerList", otherDAO.selectBuyerList());
		super.service(req, resp);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "/prod/prodForm.tiles";
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		
		try {
			BeanUtils.populate(prod, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		// 넘어오는 리퀘스트 객체가 그냥 요청인지  랩퍼인지 확인
		if(req instanceof StandardMultipartHttpServletRequest) {
			// 멀티 파트 요청이 맞다.
			
			MultipartFile imageFile = ((StandardMultipartHttpServletRequest) req).getFile("prodImage");
			prod.setProdImage(imageFile);
		}
		
		// 상품 이미지 저장 처리(MultipartFile)
		String imageFolderUrl = "/resources/prodImages";
		String imageFolderPath = getServletContext().getRealPath(imageFolderUrl);
		File imageFolder = new File(imageFolderPath);
		if(!imageFolder.exists()) imageFolder.mkdirs();
		String imageSaveName = UUID.randomUUID().toString();
		File prodImageFile = new File(imageFolder, imageSaveName);
		
		MultipartFile imageFile = prod.getProdImage();
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
		new DelegatingViewResolver().viewResolve(viewName, req, resp);
	}

}
	
