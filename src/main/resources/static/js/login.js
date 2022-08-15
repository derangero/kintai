var loginFnc; // サブシステムごとに名称変更する
window.addEventListener('load', function() {

	loginInit = (function() {
		document.getElementById("login-loginButton").disabled = false;
		
		let loginForm = document.getElementById('loginForm');
		loginForm.addEventListener('submit', () => {
			document.getElementById("login-loginButton").disabled = true;
		}, false);
	})();

	loginFnc = (function() {
		return {
		};
	})();
});