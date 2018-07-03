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
});
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
					var tr='<tr>'+
	                '<td>'+(index+1)+'</td>'+
	                '<td>'+user.loginName+'</td>'+
	                '<td>'+user.nickName+'</td>'+
	                '<td>'+user.loginType+'</td>'+
	                '<td>'+user.score+'</td>'+
	                '<td>'+new Date(user.regDate).toLocaleDateString().replace("/","-").replace("/","-")+'</td>'+
	                '<td>'+user.isLock+'</td>'+
	                '<td>'+"管理员"+'</td>'+
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