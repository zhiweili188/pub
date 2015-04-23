$(document).ready(function(){
	  $("#loginForm").validation();
	   //$("form").attr("action", "${ctx}/login/ajaxLogin.do");
	  $("form").attr("action", $("#ctx").val()+"/login/ajaxLogin.do");
	   $("#loginForm button[type='button']").on('click',function(event){
	     // 2.最后要调用 valid()方法。
	     if ($("#loginForm").valid(this,"error!")==false){
	       //$("#error-text").text("error!"); 1.0.4版本已将提示直接内置掉，简化前端。
	       return false;
	     }
	    // $("form").submit();
	    submitForm();
	   }); 
	 
  });
  
	function submitForm(){
		$.ajax({
		    type: 'post',
		    url: $("#ctx").val()+'/login/ajaxLogin.do',
		    data: $("#loginForm").serializeJson(),
		    dataType: "json", 
		    success: function(data) {
		    	
		    	  if(data.code == 0) {
		    		  if(data.returnToUrl == null || data.returnToUrl=="") {
		    			  window.location.reload(true);
		    		  } else {
		    			  
		    			  window.location=$("#ctx").val()+data.returnToUrl;
		    		  }
			       } else {
			    	   $("#messageSpan").html(data.code+":"+data.message);
			    	   $("#alertDiv").removeClass("hide");
			       }
		    }
		});
	}
	function logout(){
		$.ajax({
			type: 'post',
			url: $("#ctx").val()+'/login/ajaxLogout.do',
			data: null,
			dataType: "json", 
			success: function(data) {
				
				if(data.code == 0) {
					if(data.returnToUrl == null || data.returnToUrl=="") {
						window.location.reload(true);
					} else {
						
						window.location=$("#ctx").val()+data.returnToUrl;
					}
				} else {
					
				}
			}
		});
	}
