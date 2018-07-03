//@ sourceURL=role.js
var roleId;
$(function(){
	findRoles(1);// 第一次执行的时候要查询出第一页数据
	//给搜索按钮添加click事件
	$("#rolePanel .row button").click(function(){
		findRoles(1);
	});
	//给新增角色框里的确认按钮添加click事件
	$("#addRole_submit").click(function(){
		alert(155);
		return addRole();
	});
	$("#editRole form").submit(function(){
		alert(11.0);
		return updateRole();
	});
	$(".bs-example-modal-sm button:eq(1)").click(function(){
		deleteRole();
	})

});
function deleteRole(){
	$.ajax({
		url:basePath+"role/deleteRole/"+roleId,
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status==1){
				//删行
				$("#role_"+roleId).remove();
				//关闭modal
				$(".bs-example-modal-sm").modal("hide");
			}
		},
		error:function(){
			alert("请求失败")
		}
	});
}
function deleteRoleClick(rId){
	roleId=rId;
}
function updateRole(){
	var newRoleName=$("#role_name").val();
	$.ajax({
		url:basePath+"role/updateRole",
		type:"post",
		data:{"id":roleId,"name":newRoleName},
		dataType:"json",
		success:function(result){
			if(result.status==1){
				alert(result.message);
				$("#role_"+roleId).find("td:eq(2)").text(newRoleName);
				//关掉modal
				$("#editRole").modal("toggle");
			}
			
		},
		error:function(){
			alert("请求失败")
		}
	});
	return false;
}
function updateRoleClick(rId){
	alert("updateClike"+rId);
	roleId=rId;
	var rName=$("#role_"+roleId).find("td:eq(2)").text();
	$("#role_name").val(rName);
}
function addRole(){
	//获取新角色数据
	var roleName=$("#roleName").val();
	//发送ajax异步请求
	$.ajax({
		url:basePath+"role/addRole/"+roleName,
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status==1){
				alert(result.message); 
			}
		},
		error:function(){
			alert("请求失败！")
		}
		
	});
	return false;
}
function findRoles(currentPage){
	// 处理模糊关键字
	var roleKeyword=$("#rolePanel .row input[type=text]").val();
	alert("rolekey");
	if(roleKeyword==null||roleKeyword==""){
		roleKeyword="undefined";
	}
	$.ajax({
		url:basePath+"role/findRolesByPage",
		type:"get",
		data:{"currentPage":currentPage,"roleKeyword":roleKeyword},
	    dataType:"json",
		success:function(result){
			// alert(result.status+":"+result.message);
			if(result.status==1){
				// 清空表格，分页组件
				$("#role_table tbody").html("");
				$("#role_page").html("")
				// dom给表格添加数据
				var page=result.data;
				var roles=page.data;
				$(roles).each(function(n,value){
					// n表示循环到第几个对象，从0开始
					// value是一个对象，循环到的那个对象
					if(value.name!='超级管理员'&&value.name!='学员'&&value.name!='讲师'){
						// 需要加上修改和删除的超链接
						var tr='<tr id="role_'+value.id+'">'+
			              '<td>'+(n+1)+'</td>'+
			              '<td>'+value.id+'</td>'+
			              '<td>'+value.name+'</td>'+
			              '<td>'+
			              '<a href="" onclick=updateRoleClick(\''+value.id+'\') id="urole'+value.id+'" data-toggle="modal" data-target="#editRole" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑</a>'+
			              '<a href="" onclick=deleteRoleClick(\''+value.id+'\') id="delRole'+value.id+'" data-toggle="modal" data-target=".bs-example-modal-sm"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</a>'+
			              '</td>'+    
			              '</tr>';
						$("#role_table tbody").append(tr);
					}else{
						// 不需要加上修改和删除的超链接
						var tr='<tr id="role_'+value.id+'">'+
			             '<td>'+(n+1)+'</td>'+
			              '<td>'+value.id+'</td>'+
			              '<td>'+value.name+'</td>'+
			              '<td>'+
			              '</td>'+
			              '</tr>';
						$("#role_table tbody").append(tr);
					}
				});
				//开始构建页面中的分页组件条
				if(page.totalPage>1){
					//大于1页才显示分页条
					
					//处理前一页
					var previous='<li>'+
			          '<a href="javascript:findRoles('+page.previousPage+')" aria-label="Previous">'+
	                  '<span aria-hidden="true">&laquo;</span>'+
	                  '</a>'+
                      '</li>';
					$("#role_page").append(previous);
					//处理中间的超链接
					$(page.aNum).each(function(n,value){
						var middle='<li><a href="javascript:findRoles('+value+')">'+value+'</a></li>';
						$("#role_page").append(middle);
					});
					//处理后一页
					var next='<li>'+
			                 '<a href="javascript:findRoles('+page.nextPage+')" aria-label="Next">'+
	                         '<span aria-hidden="true">&raquo;</span>'+
	                         '</a>'+
	                         '</li>';
					$("#role_page").append(next);
				}
			}else if(result.status==0){
				alert("没有查询到数据")
			}
		},
		error:function(){
		alert("请求失败")
		}
	});
}

