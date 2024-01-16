<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Objects"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
   
   <%
      String message =(String)request.getAttribute("message");
      if(StringUtils.isNotBlank(message)){
         %>
         <script>
            alert("<%=message%>");
         </script>
         <% 
      }
   %>

<h4>회원 가입 양식</h4>
<form:form modelAttribute="member" method="post" enctype="multipart/form-data">
   <table>
      <tr>
         <th><spring:message code="member.memId" /></th>
         <td>
         <form:input type="text" path="memId"  class="form-control" />
         <form:errors path="memId" element="span" />
         </td>
      </tr>
      <tr>
      <th><spring:message code="member.memPass" /></th>
      <td>
         <form:input type="text" path="memPass"  />
         <form:errors path="memPass" element="span" />
      </td>
   </tr>
   <tr>
      <th><spring:message code="member.memName" /></th>
      <td>
         <form:input type="text" path="memName"  />
         <form:errors path="memName" element="span"/>
      </td>
   </tr>
   <tr>
      <th><spring:message code="member.memImg" /></th>
      <td>
         <form:input type="file" path="memImage"  accept="image/*" />
         <form:errors path="memImage" element="span"/>
      </td>
   </tr>
   <tr>
      <th><spring:message code="member.memRegno1" /></th>
      <td>
         <form:input type="text" path="memRegno1" />
         <form:errors path="memRegno1" element="span"/>
      </td>
   </tr>
   <tr>
      <th><spring:message code="member.memRegno2" /></th>
      <td>
         <form:input type="text" path="memRegno2"  />
         <form:errors path="memRegno2" element="span"/>
      </td>
   </tr>
   <tr>
      <th><spring:message code="member.memBir" /></th>
      <td>
         <form:input type="date" path="memBir" />
         <form:errors path="memBir" element="span"/>
      </td>
   </tr>
   <tr>
      <th><spring:message code="member.memZip" /></th>
      <td>
         <form:input type="text" path="memZip"   />
         <form:errors path="memZip" element="span"/>
      </td>
   </tr>
   <tr>
      <th><spring:message code="member.memAdd1" /></th>
      <td>
         <form:input type="text" path="memAdd1"  />
         <form:errors path="memAdd1" element="span"/>
      </td>
   </tr>
   <tr>
      <th><spring:message code="member.memAdd2" /></th>
      <td>
         <form:input type="text" path="memAdd2"  />
         <form:errors path="memAdd2" element="span"/>
      </td>
   </tr>
   <tr>
      <th><spring:message code="member.memHometel" /></th>
      <td>
         <form:input type="text" path="memHometel"  />
         <form:errors path="memHometel" element="span"/>
      </td>
   </tr>
   <tr>
      <th><spring:message code="member.memComtel" /></th>
      <td>
         <form:input type="text" path="memComtel"  />
         <form:errors path="memComtel" element="span"/>
      </td>
   </tr>
   <tr>
      <th><spring:message code="member.memHp" /></th>
      <td>
         <form:input type="text" path="memHp"   />
         <form:errors path="memHp"  element="span"/>
      </td>
   </tr>
   <tr>
      <th><spring:message code="member.memMail" /></th>
      <td>
         <form:input type="text" path="memMail"  />
         <form:errors path="memMail" element="span"/>
      </td>
   </tr>
   <tr>
      <th><spring:message code="member.memJob" /></th>
      <td>
         <form:input type="text" path="memJob"  />
         <form:errors path="memJob" element="span"/>
      </td>
   </tr>
   <tr>
      <th><spring:message code="member.memLike" /></th>
      <td>
         <form:input type="text" path="memLike"  />
         <form:errors path="memLike" element="span"/>
      </td>
   </tr>
   <tr>
      <th><spring:message code="member.memMemorial" /></th>
      <td>
         <form:input type="text" path="memMemorial"  />
         <form:errors path="memMemorial" element="span"/>
      </td>
   </tr>
   <tr>
      <th><spring:message code="member.memMemorialday" /></th>
      <td>
         <form:input type="date" path="memMemorialday"  />
         <form:errors path="memMemorialday" element="span"/>
      </td>
   </tr>
   <tr>
      <th><spring:message code="member.memMileage" /></th>
      <td>
         <form:input type="number" path="memMileage"  />
         <form:errors path="memMileage" element="span"/>
      </td>
   </tr>
   <tr>
      <th><spring:message code="member.memDelete" /></th>
      <td>
         <form:input type="text" path="memDelete" />
         <form:errors path="memDelete" element="span" />
      </td>
   </tr>
   <tr>
      <td colspan="2">   
         <form:button type="submit" class="btn btn-primary" >저장</form:button>    
         <form:button type="reset" class="btn btn-warning" >취소</form:button>
      </td>
   </tr>
</table>
</form:form>
