me = {};
window.addEventListener('DOMContentLoaded', function() {
	me.attendanceRecordListFnc = (function() {
	})();

	me.init = (function() {
		$("#attendanceRecordList-search").on("click", (e) => {
			me.search();
		});
		
		commonFnc.disableControl();
		let now = new Date();
		$('#attendanceRecordList-fiscalYear').val(now.getFullYear());
		for (let ii = 1; ii < 13; ii++) {
			//let option = $('<option>').html(ii).val(ii);
			let option = $('<option>').html("あ").val("あ");
			$('#attendanceRecordList-monthSelection').append(option);
		}
		//$('#attendanceRecordList-monthSelection').val(now.getMonth() + 1);
		$('#attendanceRecordList-monthSelection').val(null);
	})();
	
	me.search = (function() {
		commonFnc.disableControl();
		let conditionData = me.getConditionData();
		$.ajax({
			url: "/attendanceRecordListSearch",
			type: "GET",
			dataType: "json",
			data: commonFnc.addCsrf(conditionData)
		})
			.done(function(data) {
			})
			.fail(function() {
			})
			.always(function() {
				commonFnc.enableControl();
			});
	});
	
	me.getConditionData = (function() {
		let fiscalYear = $('#attendanceRecordList-fiscalYear').val();
		let month = $('#attendanceRecordList-monthSelection').val();
		return {
			fiscalYear: fiscalYear,
			month: month,
		};
	});
});