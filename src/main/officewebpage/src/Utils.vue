<script>
import GlobalVar from "./Global";
const methods = {
	updateUserState($ajax, redir, callback) {
		var userStr = window.sessionStorage.getItem("user");
		var userObj = null;
		var that = this;
		if (userStr != null) {
			userObj = JSON.parse(userStr);
		} else {
			return;
		}
		$ajax
			.get("/api/user/login", {
				params: {
					uid: userObj.uid,
				},
			})
			.then((res) => {
				if (res.data.message == "OK") {
					if (callback) callback(userObj);
				}else{
					window.sessionStorage.removeItem("user");
				}
			})
			.catch((err) => {
				console.log(error);
				this.logout($ajax, redir);
			});
	},
	logout($ajax, redirect) {
		$ajax.get("/api/user/logout");
		window.sessionStorage.removeItem("user");
		if (redirect) location.replace("/");
	},
	SetTitle(name) {
		if (arguments.length == 0) {
			document.title = GlobalVar.OfficeName;
			return;
		}
		document.title = name + " - " + GlobalVar.OfficeName;
	},
};
export default {
	methods,
};
</script>