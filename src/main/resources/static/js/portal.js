var me = {};

window.addEventListener('load', function() {
	me.portalFnc = (function() {
		return {
		//public
			time: function(){
				let now = new Date();
				let n = document.getElementById("portal-time");
				if (n) {
					n.innerHTML = now.toLocaleTimeString();
				}
			}
		};
	})();

	me.init = (function() {
		me.portalFnc.time();
		me.intervalId = setInterval('me.portalFnc.time()', 1000);
		$("#portal-goToWorkText").hide();
		$("#portal-leaveWorkText").hide();

		$("#portal-goToWorkForm").on("submit", function(e) {
			me.saveByGoToWork(e);
		});
		$("#portal-leaveWorkForm").on("submit", function(e) {
			me.saveByLeaveWork(e);
		});
	})();

	me.saveByGoToWork = (function(e) {
		e.preventDefault();  // デフォルトのイベント(ページの遷移やデータ送信など)を無効にする
		commonFnc.disableControl();
		
		$.ajax({
			url: "/goToWork",
			type: "POST",  // HTTPメソッドを指定（デフォルトはGET）
			dataType: "json",
			data: {
				_csrf: $("*[name=_csrf]").val() // CSRFトークンを送信(SpringSecurity使用時必須/GETはダメ)
			}
		})
			.done(function(data) {
				$("#portal-errorMsg").text(data.errorMsg);
				if (!!data.stampRecordedByStart) {
					clearInterval(me.intervalId);
					$("#portal-time").text(data.recordedTime);
					$("#portal-goToWorkButton").hide();
					$("#portal-goToWorkText").show();
				} else {
					$("#portal-goToWorkButton").show();
					$("#portal-goToWorkText").hide();
				}
			})
			.fail(function() {
			})
			.always(function() {
				commonFnc.enableControl();
			});
	});

	me.saveByLeaveWork = (function(e) {
		e.preventDefault();
		commonFnc.disableControl();
		
		$.ajax({
			url: "/leaveWork",
			type: "POST",
			dataType: "json",
			data: {
				_csrf: $("*[name=_csrf]").val()
			}
		})
			.done(function(data) {
				$("#portal-errorMsg").text(data.errorMsg);
				if (!!data.stampRecordedByEnd) {
					clearInterval(me.intervalId);
					$("#portal-time").text(data.recordedTime);
					$("#portal-leaveWorkButton").hide();
					$("#portal-leaveWorkText").show();
				} else {
					$("#portal-leaveWorkButton").show();
					$("#portal-leaveWorkText").hide();
				}
			})
			.fail(function() {
			})
			.always(function() {
				commonFnc.enableControl();
			});
	});
});