window.addEventListener('DOMContentLoaded', function() {
	commonInit = (function() {
	})();
	commonFnc = (function() {
		return {
			disableControl: function(){
			},
			enableControl: function(){
			},
			addCsrf: function(data){
				let csrfVal = $("*[name=_csrf]").val();
				if (data != null) {
					data["_csrf"] = csrfVal;
					return data;
				}

				return  {
					"_csrf": csrfVal
				};
			}
		};
	})();
});

function test(){
	debugger;
}

$(document).ajaxStop( function(e){
})

$(document).ajaxComplete( function(e, xmlHttpRequest, settings){
	//let error = /*[[${error}]]*/"error";
	//console.log(error);
	debugger;
} )