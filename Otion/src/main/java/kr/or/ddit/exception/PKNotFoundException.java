package kr.or.ddit.exception;
/**
 * 
 * @author 이아인
 * @since 2022. 8. 8.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 8.      이아인       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
public class PKNotFoundException extends RuntimeException{

   public PKNotFoundException() {
      super();
      // TODO Auto-generated constructor stub
   }

   public PKNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
      // TODO Auto-generated constructor stub
   }

   public PKNotFoundException(String message, Throwable cause) {
      super(message, cause);
      // TODO Auto-generated constructor stub
   }

   public PKNotFoundException(String message) {
      super(message);
      // TODO Auto-generated constructor stub
   }

   public PKNotFoundException(Throwable cause) {
      super(cause);
      // TODO Auto-generated constructor stub
   }

}