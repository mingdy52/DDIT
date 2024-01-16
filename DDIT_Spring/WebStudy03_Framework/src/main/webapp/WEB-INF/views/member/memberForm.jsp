<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Objects"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   
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
<%-- <%   
   MemberVO member =(MemberVO)request.getAttribute("member");
   if(member==null)
      member = new MemberVO(); //강제로 빈 member만들어주기
   Map<String,String> errors =(Map)request.getAttribute("errors");
      if(errors==null)
         errors = new LinkedHashMap<>();
%> --%>
<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request"/>
<jsp:useBean id="errors" class="java.util.LinkedHashMap" scope="request"/>
<form method="post"enctype="application/x-www-form-urlencoded">
   <table>
      <tr>
         <th>회원아이디</th>
         <td>
         <input type="text" name="memId"  class="form-control" value='<%=Objects.toString(member.getMemId(),"") %>'/>
         <%--없으면 null나옴 <span class="error"><%=errors.get("memId")%></span> --%>
            <span class="error">${errors['memId']}</span>
         </td>
      </tr>
      <tr>
      <th>비밀번호</th>
      <td>
         <input type="text" name="memPass"  value="${member.memPass }" />
         <span class="error">${errors["memPass"] }</span>
      </td>
   </tr>
   <tr>
      <th>회원명</th>
      <td>
         <input type="text" name="memName"  value="${member.memName }" />
         <span class="error">${errors["memName"] }</span>
      </td>
   </tr>
   <tr>
      <th>주민번호1</th>
      <td>
         <input type="text" name="memRegno1" value="${member.memRegno1 }" />
         <span class="error">${errors["memRegno1"] }</span>
      </td>
   </tr>
   <tr>
      <th>주민번호2</th>
      <td>
         <input type="text" name="memRegno2" value="${member.memRegno2 }" />
         <span class="error">${errors["memRegno2"] }</span>
      </td>
   </tr>
   <tr>
      <th>생일</th>
      <td>
         <input type="date" name="memBir"  value="${member.memBir }" />
         <span class="error">${errors["memBir"] }</span>
      </td>
   </tr>
   <tr>
      <th>우편번호</th>
      <td>
         <input type="text" name="memZip"  value="${member.memZip }" />
         <span class="error">${errors["memZip"] }</span>
      </td>
   </tr>
   <tr>
      <th>주소1</th>
      <td>
         <input type="text" name="memAdd1"  value="${member.memAdd1 }" />
         <span class="error">${errors["memAdd1"] }</span>
      </td>
   </tr>
   <tr>
      <th>주소2</th>
      <td>
         <input type="text" name="memAdd2"  value="${member.memAdd2 }" />
         <span class="error">${errors["memAdd2"] }</span>
      </td>
   </tr>
   <tr>
      <th>집전화번호</th>
      <td>
         <input type="text" name="memHometel" value="${member.memHometel }" />
         <span class="error">${errors["memHometel"] }</span>
      </td>
   </tr>
   <tr>
      <th>회사번호</th>
      <td>
         <input type="text" name="memComtel" value="${member.memComtel }" />
         <span class="error">${errors["memComtel"] }</span>
      </td>
   </tr>
   <tr>
      <th>휴대폰</th>
      <td>
         <input type="text" name="memHp"  value="${member.memHp }" />
         <span class="error">${errors["memHp"] }</span>
      </td>
   </tr>
   <tr>
      <th>이메일</th>
      <td>
         <input type="text" name="memMail"  value="${member.memMail }" />
         <span class="error">${errors["memMail"] }</span>
      </td>
   </tr>
   <tr>
      <th>직업</th>
      <td>
         <input type="text" name="memJob" value="${member.memJob }" />
         <span
         class="error">${errors["memJob"] }</span>
      </td>
   </tr>
   <tr>
      <th>취미</th>
      <td>
         <input type="text" name="memLike" value="${member.memLike }" />
         <span
         class="error">${errors["memLike"] }</span>
      </td>
   </tr>
   <tr>
      <th>기념일</th>
      <td>
         <input type="text" name="memMemorial" value="${member.memMemorial }" />
         <span class="error">${errors["memMemorial"] }</span>
      </td>
   </tr>
   <tr>
      <th>기념일자</th>
      <td>
         <input type="date" name="memMemorialday" value="${member.memMemorialday }" />
         <span class="error">${errors["memMemorialday"] }</span>
      </td>
   </tr>
   <tr>
      <th>마일리지</th>
      <td>
         <input type="number" name="memMileage" value="${member.memMileage }" />
         <span class="error">${errors["memMileage"] }</span>
      </td>
   </tr>
   <tr>
      <th>탈퇴여부</th>
      <td>
         <input type="text" name="memDelete" value="${member.memDelete }" />
         <span class="error">${errors["memDelete"] }</span>
      </td>
   </tr>
   <tr>
      <td colspan="2">   
         <input type="submit" class="btn btn-primary" value="저장" />    
         <input type="reset" class="btn btn-warning" value="취소" />
      </td>
   </tr>
</table>
</form>
