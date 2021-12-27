$(() => {
    $("#modifyPasswordForm").on("submit", function() {
	    // 驗證表單輸入欄位填寫及格式
		return verifyFormInput();
    });

	// Description: 驗證表單輸入欄位填寫及格式
	function verifyFormInput() {
        // 驗證舊密碼
        var oldPassword = $("#oldPassword").val();
		var verifyOldPassword = isValidPassword(oldPassword);
		if(!verifyOldPassword.valid) {
            alert(verifyOldPassword.message);
            $("#password").focus();
            return false;
        }
		
        // 驗證新密碼
        var password = $("#password").val();
		var verifyPassword = isValidPassword(password);
		if(!verifyPassword.valid) {
            alert(verifyPassword.message);
            $("#password").focus();
            return false;
        }

        // 驗證再次輸入新密碼
		var verifyCheckPassword = isValidCheckPassword(password, $("#checkPassword").val());
		if(!verifyCheckPassword.valid) {
            alert(verifyCheckPassword.message);
            $("#checkPassword").focus();
            return false;
		}
		return true;
	}
})