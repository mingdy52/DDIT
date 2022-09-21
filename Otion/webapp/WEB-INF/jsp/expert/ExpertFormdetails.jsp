<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 9. 8.      박채록      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set value="${pagingVO.dataList }" var="exp"/>
<table class="table table mb-0" style="text-align: center;">
    	<thead>
    		<tr>
    			<th>NO</th>
    			<th>접수번호</th>
    			<th>진행상태</th>
    		</tr>
    	</thead>
    	<tbody>
    <c:if test="${not empty exp }">
    <c:forEach items="${exp }" var="e">
    	<tr>
    		<td>${e.rnum }</td>
    		<td>${e.exFormNum}</td>
    		<td><span data-refuse="${e.refuseReason }">${e.comCodeNm}</span></td>
    	</tr>
    </c:forEach>
    	<tr>
    	
    	</tr>
    </c:if>
    <c:if test="${empty exp }">
    	<tr>
    		<td colspan="6">이용 중인 expert가 없습니다.</td>
    	</tr>
    </c:if>
    </tbody>
    </table>