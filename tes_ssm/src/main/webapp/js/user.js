//@ sourceURL=user.js
var userId;
//设定初始默认值
var roleType="all";
$(function(){
	console.log("lpoad user");
	findUsers(1);
	$("#role_type button").click(function(){
		roleType=$(this).text();
		if(roleType=="全部"){
			roleType="all";
		}
		alert(roleType);
		findUsers(1);
	});
	$("#user_tab li:eq(2) a").click(function(e){
		e.preventDefault()
		findAllRoles();
		$(this).tab('show');
	})
	//给新增用户的表单添加submit事件
	$("#addUserPanel form").submit(function(){
		return addUser();
	})
	//给导出
	$("#export_user").click(function(){
		alert("145ds555");
		window.location.href=basePath+"user/exportExcel";
	})
//添加用户
 function addUser(){
		var loginName=$("#addUserPanel form #inputEmail").val();
		var password=$("#addUserPanel form #inputPassword").val();
		var password2=$("#addUserPanel form  #inputPassword2").val();
		var nickName=$("#addUserPanel form #nickName").val();
		var age=$("#addUserPanel form #age").val();
		var roleId=$("#addUserPanel form #roleCategory").val();
		var sex=$("#addUserPanel form input[name=user-type]:checked").val();
		alert(password+" "+" "+password2+" "+loginName+" "+nickName+" "+age+" "+roleId+" "+sex);
		if(password!=password2){
			alert("两次密码不同");
			return false;
		}
		if(age<0){
			return false;
		}
		//异步提交
		$.ajaxFileUpload({
			url:basePath+"user/newUser",
			secureuri:false,
			fileElementId:"addHeadPicture",//文件域的id
			type:"post",
			data:{"loginName":loginName,"password":password2,"nickName":nickName,"age":age,"sex":sex,"roleId":roleId},
			dataType:"text",
			success:function(data,status){
				//alert(data);
				data=data.replace(/<PRE.*?>/g,'');
				data=data.replace("<PRE>",'');
				data=data.replace("</PRE>",'');
				data=data.replace(/<pre.*?>/g,'');
				data=data.replace("<pre>",'');
				data=data.replace("</pre>",'');
				alert(data);
			},
			error:function(){
				alert("请求失败!");
			}
		});
		return false;
   }
	
});
//查询所有的角色
function findAllRoles(){
	$.ajax({
		url:basePath+"/role/findAllRoles",
		type:"get",
		dataType:"json",
		success:function(result){
			if(result.status==1){
				$("#roleCategory").html("");
				var roles=result.data;
				$(roles).each(function(n,role){
					var select_role='<option value="'+role.id+'">'+role.name+"</option>"
						$("#roleCategory").append(select_role);
				})
			}else if(result.status==0){
				alert(result.message)
			}
		},
        error:function(){
			alert("请求失败");
		}
	
	})
}
function findUsers(currentPage){
	//处理模糊关键字
	var userKeyword=$("#userPanel form input").val();
	if(userKeyword==null||userKeyword==""){
		userKeyword="undefinded";
	}
	$.ajax({
		url:basePath+"user/findUsersByPage",
		type:"post",
		data:{"currentPage":currentPage,"userKeyword":userKeyword,"roleType":roleType},
		dataType:"json",
		success:function(result){
			if(result.status==1){
				//清空所有的表格和分页条
				$("#user_table tbody").html("");
				$("#user_page").html("");
				//
				var page=result.data;
				var users=page.data;
				$(users).each(function(index,user){
					//从user对象中取出roles的集合对象
					var roles=user.roles;
					var roleString='';
					//从roles集合中遍历每一个role对象
					$(roles).each(function(n,role){
						//从role对象中取出role的name属性值
						roleString+=role.name+",";
					});
					if(roleString==''){
						roleString='无角色';
					}else{
						//有角色,但是要去掉最后一个逗号
						roleString=roleString.substring(0,roleString.length-1);
					}
					var tr='<tr>'+
	                '<td>'+(index+1)+'</td>'+
	                '<td>'+user.loginName+'</td>'+
	                '<td>'+user.nickName+'</td>'+
	                '<td>'+user.loginType+'</td>'+
	                '<td>'+user.score+'</td>'+
	                '<td>'+new Date(user.regDate).toLocaleDateString().replace("/","-").replace("/","-")+'</td>'+
	                '<td>'+user.isLock+'</td>'+
	                '<td>'+roleString+'</td>'+
	                '<td>'+
	                  '<a href="" data-toggle="modal" data-target="#editUser"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑</a>'+
	                  '<a href="" data-toggle="modal" data-target=".bs-example-modal-sm"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</a>'+
	                '</td>'+
              '</tr>';
		$("#user_table tbody").append(tr);
		});
			//处理分页条;
			if(page.totalPage>1){
				//处理前一页
				var previous='<li>'+
	            '<a href="javascript:findUsers('+page.previousPage+')" aria-label="Previous">'+
	              '<span aria-hidden="true">&laquo;</span>'+
	            '</a>'+
	          '</li>';
				$("#user_page").append(previous);
				//处理中间页数字
				$(page.aNum).each(function(n,value){
					var middle='<li><a href="javascript:findUsers('+value+')">'+value+'</a></li>';
					$("#user_page").append(middle);
				});					
				//处理下一页
				var next='<li>'+
		            '<a href="javascript:findUsers('+page.nextPage+')" aria-label="Next">'+
	              '<span aria-hidden="true">&raquo;</span>'+
	            '</a>'+
	          '</li>';
				$("#user_page").append(next);
			}
			}
		},
			error:function(){
				alert("请求失败");
			}
		
	});
}