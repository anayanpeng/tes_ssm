//@ sourceURL=main.js

$(function(){
	// 注册面板关闭事件
	$(".panel .close").click(function(){
		close_panel(this);
	});
	// 注册面板重置事件
	$(".glyphicon-refresh").click(function(){
		reset_panel();
	});
	// 画用户统计饼状图
	draw_user();
	// 画视频统计环行图
	draw_video();
	//获取内存中的header.html
	$("#logout_a").click(function(){
		alert("logout")
	})
});
function logout(){
	$.ajax({
		url:bashPath+"main/logout",
		type:"post",
		success:function(result){
			if(result.status==1){
				window.location.href="login.html"
			}
		},
		error:function(){
			alert("错误");
		}
		
	})
	
}
// 关闭面板
function close_panel(btn) {
	$(btn).parent().parent().parent().fadeOut(200);
}

// 重置面板
function reset_panel() {
	$(".panel").parent().fadeIn(200);
}

function draw_user() {
	var data = [
		{
			value: 9273,
			color:"#F7464A",
			highlight: "#FF5A5E",
			label: "学员"
		},
		{
			value: 1293,
			color: "#46BFBD",
			highlight: "#5AD3D1",
			label: "讲师"
		},
		{
			value: 273,
			color: "#FDB45C",
			highlight: "#FFC870",
			label: "管理员"
		}
	];
	var ctx = document.getElementById("chart-user").getContext("2d");
	window.myDoughnut = new Chart(ctx).Pie(data, {responsive : true});
}

function draw_video() {
	var data = [
		{
			value: 600,
			color:"#F7464A",
			highlight: "#FF5A5E",
			label: "Java"
		},
		{
			value: 300,
			color: "#46BFBD",
			highlight: "#5AD3D1",
			label: "IOS"
		},
		{
			value: 250,
			color: "#FDB45C",
			highlight: "#FFC870",
			label: "Android"
		},
		{
			value: 200,
			color: "#949FB1",
			highlight: "#A8B3C5",
			label: ".NET"
		},
		{
			value: 150,
			color: "#4D5360",
			highlight: "#616774",
			label: "UID"
		},
		{
			value: 100,
			color: "#4D5360",
			highlight: "#616774",
			label: "网络营销"
		},
		{
			value: 80,
			color: "#4D5360",
			highlight: "#616774",
			label: "C++"
		},
		{
			value: 70,
			color: "#4D5360",
			highlight: "#616774",
			label: "软件测试"
		},
		{
			value: 60,
			color: "#4D5360",
			highlight: "#616774",
			label: "php"
		},
		{
			value: 50,
			color: "#4D5360",
			highlight: "#616774",
			label: "大数据"
		},
		{
			value: 40,
			color: "#4D5360",
			highlight: "#616774",
			label: "WEB前端"
		},
		{
			value: 30,
			color: "#4D5360",
			highlight: "#616774",
			label: "Unity3D"
		},
		{
			value: 20,
			color: "#4D5360",
			highlight: "#616774",
			label: "智能硬件"
		}

	];
	var ctx = document.getElementById("chart-video").getContext("2d");
	window.myDoughnut = new Chart(ctx).Doughnut(data, {responsive : true});
}