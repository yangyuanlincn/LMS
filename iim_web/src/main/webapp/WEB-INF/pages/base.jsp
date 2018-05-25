<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/skin/default/css/default.css" media="all"/>
<link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/skin/default/css/table.css" media="all"/>
<script language="javascript" src="${ctx}/js/common.js"></script>
<script language="javascript" src="${ctx}/js/jquery-1.11.3.min.js"></script>
<script>
	     function isOnlyChecked(){
	    	 var checkBoxArray = document.getElementsByName('id');
				var count=0;
				for(var index=0; index<checkBoxArray.length; index++) {
					if (checkBoxArray[index].checked) {
						count++;
					}	
				}
			//jquery
			//var count = $("[input name='id']:checked").size();
			if(count==1)
				return true;
			else
				return false;
	     }
	     function isChecked(){
	    	 var count = $("input[name='id']:checked").size();
	    	 if(count>0){
	    		 return true;
	    	 }else{
	    		 return false;
	    	 }
	     }
	     function toView(url,target){
	    	 if(isOnlyChecked()){
	    		 formSubmit(url,target);
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     //实现更新
	     function toUpdate(url,target){
	    	 if(isOnlyChecked()){
	    		 formSubmit(url,target);
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     function toDelete(url,target){
	    	 if(isChecked()){
	    		 formSubmit(url,target)
	    	 }else{
	    		 alert("请先选择一项或多项");
	    	 }
	    	 	
	     }
	</script>