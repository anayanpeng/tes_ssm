//@ sourceURL=user.js

$(function(){
	console.log("load user");
	
	$('[data-toggle="popover"]').popover();
	
	/**
	  $("#chk_all_t").click(function() {
	  	if ($(":checkbox").prop("checked")){
		    $("#chk_list_1").prop("checked",true);
		    $("#chk_list_2").prop("checked",true);
		    $("#chk_list_3").prop("checked",true);
		    $("#chk_list_4").prop("checked",true);
	    }
	  	else{
	        $("#chk_list_1").removeAttr("checked");
	        $("#chk_list_2").removeAttr("checked");
	        $("#chk_list_3").removeAttr("checked");
	        $("#chk_list_4").removeAttr("checked");
	    }
	  });
	*/
	
	$(".glyphicon-import").click(function(){
		import_user();
	});
	
});

function import_user() {
	$("#import_user").trigger("click");
}