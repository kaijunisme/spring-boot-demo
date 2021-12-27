$(() => {
    $("#registerForm").on("submit", function() {
	    // 驗證表單輸入欄位填寫及格式
		if(!verifyFormInput()) return false;
		
		// 驗證帳號是否已被使用
        if(!verifyUsernameDuplicate()) return false;
    });

	// Description: 驗證表單輸入欄位填寫及格式
	function verifyFormInput() {
        // 驗證姓名
        if($("#name").val() == "") {
            alert("請輸入姓名");
            $("#name").focus();
            return false;
        }

        // 驗證帳號
		var verifyUsername = isValidUsername($("#username").val());
		if(!verifyUsername.valid) {
            alert(verifyUsername.message);
            $("#username").focus();
            return false;
		}

        // 驗證密碼
        var password = $("#password").val();
		var verifyPassword = isValidPassword(password);
		if(!verifyPassword.valid) {
            alert(verifyPassword.message);
            $("#password").focus();
            return false;
        }

        // 驗證再次輸入密碼
		var verifyCheckPassword = isValidCheckPassword(password, $("#checkPassword").val());
		if(!verifyCheckPassword.valid) {
            alert(verifyCheckPassword.message);
            $("#checkPassword").focus();
            return false;
		}
		return true;		
	}
	
	// Description: 驗證帳號是否已被使用
	function verifyUsernameDuplicate() {
		var result = false
		$.ajax({
            url: "/api/memberAccount",
            type: "GET",
            cache:false,
			async:false,
            data: {
                "username": $("#username").val()
            },
            dataType: "JSON",
            success: function(res) {
                // 若回傳狀態為2000，則判斷回傳資料是否為true，true 為未被使用，false 為已被使用
                if(res.state == 2000 ? !res.data : false) result =  true;
                else alert("該帳號已被使用");
            },
            error: function(xhr) {
				console.log(xhr);
            }
        });
        return result;
	}
})