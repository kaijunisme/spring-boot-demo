$(() => {
	onloadMethod();
	function onloadMethod() {
		// 插入年份，設定今年至 1951年
		onloadYear();
		
		// 將生日字串解析為年月日
		resetBirthday();

		// 載入縣市資料
		onloadAddress();
	}

	function onloadYear() {
		for(var i = new Date().getFullYear(); i >= 1951; i--) {
			$("#year").append(`<option value=${i}>${i}</option>`);
		}
	}
	
	function resetBirthday() {
		var birthday = $("#birthday").val();
		if(birthday != "") {
			var str = birthday.split("-");
			$("#year").val(str[0]);
			setMonth(str[1]);
			setDate(str[2]);
		}
	}
	
	function setMonth(text) {
		$("#month").empty();
		$("#month").append(`<option value="">月</option>`);
		$("#date").empty();
		$("#date").append(`<option value="">日</option>`);
		for(var i = 1; i <= 12; i++) {
			if(text != undefined && i == text) $("#month").append(`<option value=${i} selected>${i}</option>`);
			else $("#month").append(`<option value=${i}>${i}</option>`);
		}		
	}
	
	function setDate(text) {
		var date = new Date($("#year").val(), $("#month").val(), 0);
		$("#date").empty();
		$("#date").append(`<option value="">日</option>`);
		for(var i = 1; i <= date.getDate(); i++) {
			if(text != undefined && i == text) $("#date").append(`<option value=${i} selected>${i}</option>`);
			else $("#date").append(`<option value=${i}>${i}</option>`);
		}		
	}
	
	function onloadAddress() {
		var county = $("#c_id").val();
		var district = $("#d_id").val();
		
		setCounty($("#c_id").val());
		setDistrict(county, district);
	}

	function setCounty(county) {
		$.ajax({
			url: "/api/county",
			type: "GET",
			dataType: "JSON",
			success: function(res) {
				if(res.state == 2000) {
					$.each(res.data, function(index, d) {
						if(county != undefined && d.id == county) $("select[name='c_id']").append(`<option value=${d.id} selected>${d.name}</option>`);
						else $("select[name='c_id']").append(`<option value=${d.id}>${d.name}</option>`);
					});
				}
				else {
					alert(res.message);
				}
			},
			error: function(xhr) {
				console.log(xhr);
			}
		});
	}
	
	function setDistrict(county, district) {
		$.ajax({
			url: "/api/district",
			type: "GET",
			data: {
				"c_id": county
			},
			dataType: "JSON",
			success: function(res) {
				$("select[name='d_id']").empty();
				$("select[name='d_id']").append(`<option value="">請選擇行政區</option>`);
				if(res.state == 2000) {
					$.each(res.data, function(index, d) {
						if(district != undefined && d.id == district) $("select[name='d_id']").append(`<option value=${d.id} selected>${d.name}</option>`);
						else $("select[name='d_id']").append(`<option value=${d.id}>${d.name}</option>`);
					});
				}
				else {
					alert(res.message);
				}
			},
			error: function(xhr) {
				console.log(xhr);
			}
		});		
	}
	
	// Description: 當年份變動時，重設月份及日期起迄
	$("#year").on("change", function() {
		setMonth();
	});
	
	// Description: 當月份變動時，重設日期起迄
	$("#month").on("change", function() {
		setDate();
	});

	// Description: 當縣市變動時，重設行政區選單
	$("select[name='c_id']").on("change", function() {
		setDistrict($(this).val(), "");
	});

	// Description: 當informationForm 表單提交時，執行表單驗證流程
    $("#informationForm").on("submit", function() {
	    // 驗證表單輸入欄位填寫及格式
		return verifyFormInput();
    });

	// Description: 驗證表單輸入欄位填寫及格式
	function verifyFormInput() {
		// 若有輸入身分證字號但格式錯誤
		if($("#id_number").val() && !isIdNumberFormat($("#id_number").val())) {
			alert("身分證字號格式錯誤");
			$("#id_number").focus();
			return false;			
		}

        // 若有輸入聯絡電話但格式錯誤
		if($("#phone").val() && !isPhoneFormat($("#phone").val())) {
			alert("聯絡電話格式錯誤");
			$("#phone").focus();
			return false;			
		}
		return true;
	}
})