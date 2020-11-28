<template>
	<div>
		<div class="register-box">
			<b-card class="register-card">
				<h2>请注册</h2>
				<b-form @submit="onSubmit" style="width: 70%; margin: 0 auto">
					<b-form-group
						id="username-group"
						label="用户名:"
						label-for="username-input"
					>
						<b-form-input
							id="username-input"
							v-model="form.username"
							required
							placeholder="输入用户名"
						></b-form-input>
					</b-form-group>
					<b-form-group
						id="passwd-group"
						label="密码:"
						label-for="passwd-input"
					>
						<b-form-input
							id="passwd-input"
							v-model="form.passwd"
							type="password"
							required
							placeholder="输入密码"
							:state="passwdCheck"
						></b-form-input>
					</b-form-group>
					<b-form-group
						id="passwd-group-check"
						label="确认密码:"
						label-for="passwd-input-check"
					>
						<b-form-input
							id="passwd-input-check"
							v-model="input.passwdCheck"
							type="password"
							required
							placeholder="确认密码"
							:state="passwdTwiceCheck"
						></b-form-input>
					</b-form-group>
					<b-form-group
						id="tel-group-input"
						label="手机号码:"
						label-for="tel-input"
					>
						<b-form-input
							id="tel-input"
							v-model="form.tel"
							required
							placeholder="输入手机号码"
							:state="telNumCheck"
						></b-form-input>
					</b-form-group>
					<b-form-group
						id="majorclass-group-input"
						label="专业班级:"
						label-for="majorclass-input"
					>
						<b-form-input
							id="majorclass-input"
							v-model="form.majorclass"
							required
							placeholder="输入专业班级"
						></b-form-input>
					</b-form-group>
					<b-form-group
						id="passwd-group-check"
						label="验证码:"
						label-for="vericode-check"
					>
						<b-form-input
							id="vericode-check"
							v-model="input.veriCode"
							required
							placeholder="输入验证码"
							:state="vericodeCheck"
						></b-form-input>
					</b-form-group>
					<b-form-group>
						<vericode ref="vericode"></vericode>
					</b-form-group>
					<div style="text-align: center">
						<b-button
							type="submit"
							variant="primary"
							style="width: 100pt"
						>
							注册
						</b-button>
					</div>
				</b-form>
			</b-card>
		</div>
	</div>
</template>
<script>
import vericode from "../Coms/Vericode";
export default {
	components: {
		vericode,
	},
	data() {
		return {
			form: {
				username: "",
				passwd: "",
				tel: "",
				majorclass: "",
			},
			input: {
				passwdCheck: "",
				veriCode: "",
			},
		};
	},
	methods: {
		onSubmit(evt) {
			var that = this;
			evt.preventDefault();
			if (!this.checkForm()) return;
			this.$ajax
				.get("/api/vericode", {
					params: {
						code: this.input.veriCode,
					},
				})
				.then((res) => {
					if (res.data.message == "Fail") {
						alert("验证码填写错误！");
						flag = false;
						that.input.veriCode = "";
						that.refreshVeriCode();
						return;
					}
					that.onReg();
				})
				.catch((err) => {
					flag = false;
					alert("登录失败！");
				});
		},
		onReg() {
			this.$ajax
				.post("/api/user/register", this.form)
				.then((res) => {
					if (res.data.message == "OK") {
						alert("注册成功！");
						location.replace("/");
					}
				})
				.catch((err) => {
					if (err.response) {
						alert(
							"注册失败！错误信息：" + err.response.data.message
						);
					} else alert("注册失败！");
				});
		},
		checkForm() {
			if (!this.passwdCheck) {
				alert("密码不符合要求！");
				return false;
			}
			if (!this.passwdTwiceCheck) {
				alert("确认密码不符！");
				return false;
			}
			if (!this.telNumCheck) {
				alert("电话号码填写错误！");
				return false;
			}
			return true;
		},
		refreshVeriCode() {
			this.$refs.vericode.refreshVeriCode();
		},
	},
	computed: {
		passwdCheck() {
			if (this.form.passwd == "") {
				return null;
			}
			return (
				this.form.passwd.length >= 6 && this.form.passwd.length <= 18
			);
		},
		passwdTwiceCheck() {
			if (this.form.passwd == "" || this.input.passwdCheck == "") {
				return null;
			}
			return this.form.passwd == this.input.passwdCheck;
		},
		telNumCheck() {
			if (this.form.tel == "") return null;
			if (this.form.tel[0] != "1") return false;
			return this.form.tel.length == 11;
		},
		formCheck() {
			let checkList = [
				this.passwdCheck,
				this.passwdTwiceCheck,
				this.telNumCheck,
			];
			let flag = true;
			for (var i of checkList)
				if (i == false || i == null) {
					flag = false;
					break;
				}
			return flag;
		},
		nullCheck(tmp) {
			return tmp == "";
		},
	},
	beforeMount() {},
};
</script>
<style scoped>
.register-box {
	width: 50vw;
	margin: 50px auto;
}
.register-card {
	padding: 30px;
	border-radius: 10px;
	text-align: left;
	box-shadow: 0px 0px 3px rgb(214, 214, 214);
	transition: all 0.5s;
}
.register-card h2 {
	text-align: center;
}
.register-card:hover {
	box-shadow: 0px 0px 5px rgb(179, 179, 179);
}
.vericode-img {
	cursor: pointer;
	max-width: 70%;
}
</style>