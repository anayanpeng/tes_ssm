//@ sourceURL=login.js
$(function(){
	console.log("sss");
	//每次加载login.html
	$("#inputName").val(getCookie("loginName"));
	$(".container form").submit(function(){
		return login();
	})
});
function login(){
	alert("login()")
	var loginName=$("#inputName").val();
	var password=$("#inputPassword").val();
	var remember=$(".container form input[type=checkbox]").get(0).checked;
	alert(loginName+" "+password+" "+remember);
	//
	$.ajax({
		url:basePath+"user/login/"+loginName+"/"+password,
		type:"get",
		dataType:"json",
		success:function(result){
			if(result.status==1){
				if(remember){
					//记住密码打对勾
					addCookie("loginName",loginName,5);
				}
				window.location.href="index.html";
			}else if(result.status==0)
				alert(result.message);
		},
		error:function(){
			alert("请求失败。。。");
		}
	});
	return false;
}