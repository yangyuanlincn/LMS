<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<s:debug></s:debug>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="view"><a href="#" onclick="javascript:toView('deptAction_toview','_self')">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('deptAction_tocreate','_self');this.blur();">新增</a></li>
<li id="update"><a href="#" onclick="javascript:toUpdate('deptAction_toupdate','_self')">修改</a></li>
<li id="delete"><a href="#" onclick="javascript:toDelete('deptAction_delete','_self');this.blur();">删除</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
    部门列表
  </div> 
  </div>
  </div>
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">编号</td>
		<td class="tableHeader">上级</td>
		<td class="tableHeader">名称</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
    ${links }
	
	<c:forEach items="${results }" var="dept"  varStatus="st">
		<tr class="odd" onmouseover="this.className='highlight'" align="left" onmouseout="this.className='odd'" >
			<td><input type="checkbox" name="id" value="${dept.id }"/></td>
			<td>${st.count }</td>
			<td>${dept.id }</td>
			<td>${dept.parent.deptName }</td>
			<td><a href="deptAction_toview?id=${dept.id }">${dept.deptName }</a></td>
		</tr>
   </c:forEach>
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

