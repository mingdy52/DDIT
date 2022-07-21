<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="//cdn.jsdelivr.net/npm/jquery.fancytree@2.27/dist/skin-win8/ui.fancytree.min.css" rel="stylesheet">
<script src="//cdn.jsdelivr.net/npm/jquery.fancytree@2.27/dist/jquery.fancytree-all-deps.min.js"></script>
<body>
<div id="tree"></div>
<table class="table-bordered col-8" id="displayArea">
	<tr><th>EMPLOYEE_ID</th><td id="employeeId"></td></tr>
	<tr><th>FIRST_NAME</th><td id="firstName"></td></tr>
	<tr><th>LAST_NAME</th><td id="lastName"></td></tr>
	<tr><th>EMAIL</th><td id="email"></td></tr>
	<tr><th>PHONE_NUMBER</th><td id="phoneNumber"></td></tr>
	<tr><th>HIRE_DATE</th><td id="hireDate"></td></tr>
	<tr><th>JOB_ID</th><td id="jobId"></td></tr>
	<tr><th>SALARY</th><td id="salary"></td></tr>
	<tr><th>COMMISSION_PCT</th><td id="commissionPct"></td></tr>
	<tr><th>MANAGER_ID</th><td id="managerId"></td></tr>
	<tr><th>DEPARTMENT_ID</th><td id="departmentId"></td></tr>
	<tr><th>EMP_NAME</th><td id="empName"></td></tr>
	<tr><th>RETIRE_DATE</th><td id="retireDate"></td></tr>
</table>
<script type="text/javascript">
	let displayArea = $("#displayArea");
	$("#tree").fancytree({
		extensions: ["childcounter", "wide"],
		childcounter: {
	        deep: true,
	        hideZeros: true,
	        hideExpanded: true
	    },
	    wide: {
	        // iconWidth: "32px",     // Adjust this if @fancy-icon-width != "16px"
        // iconSpacing: "6px", // Adjust this if @fancy-icon-spacing != "3px"
        // labelSpacing: "6px",   // Adjust this if padding between icon and label !=  "3px"
        // levelOfs: "32px"     // Adjust this if ul padding != "16px"
        },
        activate: function(event, data){ // 클릭이벤트
            var node = data.node;
            console.log(node.data.adaptee);
            let employee = node.data.adaptee;
            for(let prop in employee){
            	displayArea.find("#"+prop).html(employee[prop]);
            }
        },
        blur:function(){
        	displayArea.find("td").html("");
        },
		source: { // 시작하면 여기로 가라.
			url:"<%=request.getContextPath() %>/employee/employeeList.do"
		},
		lazyLoad:function(event, data){
			console.log(event);
			console.log(data);
			var node = data.node;
	      // Load child nodes via Ajax GET /getTreeData?mode=children&parent=1234
	    	console.log(node);
		      data.result = {
		        url: "<%=request.getContextPath() %>/employee/employeeList.do",
		        data: {mode: "children", managerId: node.key},
		        cache: false
		      };
		},postProcess: function(event, data) {
			console.log(data)
		  data.result = data.response.dataList;
		},
		loadChildren: function(event, data) {
	       data.node.updateCounters();
        }
	});
</script>