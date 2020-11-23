<template>
	<div>
		<div class="login-box">
			<b-card class="login-card">
				<h2>请登录</h2>
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
						<b-img
							alt="验证码"
							:src="local.veriCodeSrc"
							@click="refreshVeriCode"
							class="vericode-img"
						>
						</b-img>
					</b-form-group>
					<div style="text-align: center">
						<b-button
							type="submit"
							variant="primary"
							style="width: 5rem"
						>
							登录
						</b-button>
					</div>
				</b-form>
			</b-card>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			form: {
				username: "",
				passwd: "",
			},
			input: {
				veriCode: "",
			},
			local: {
				veriCodeSrc: "/api/vericode",
			},
		};
	},
	methods: {
		onSubmit(evt) {
			evt.preventDefault();
			var flag = true;
			var that = this;
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
					that.onLogin();
				})
				.catch((err) => {
					flag = false;
					alert("登录失败！");
				});
		},
		onLogin() {
			this.$ajax
				.post("/api/user/login", this.form, { credentials: true })
				.then((res) => {
					if (res.data.message == "OK") {
						window.sessionStorage.setItem(
							"user",
							JSON.stringify(res.data.data)
						);
						this.$emit("onLoginSuccess");
						alert("登陆成功！");
						location.replace("/");
					} else alert(res.data.message);
				})
				.catch((err) => {
					if (err.response) {
						alert(
							"登陆失败！错误信息：" + err.response.data.message
						);
					} else {
						alert("登陆失败！");
					}
				});
		},
		randomNum(minNum, maxNum) {
			switch (arguments.length) {
				case 1:
					return parseInt(Math.random() * minNum + 1, 10);
					break;
				case 2:
					return parseInt(
						Math.random() * (maxNum - minNum + 1) + minNum,
						10
					);
					break;
				default:
					return 0;
					break;
			}
		},
		refreshVeriCode() {
			this.local.veriCodeSrc =
				"/api/vericode?num=" + this.randomNum(100, 999);
		},
	},
	computed: {
		vericodeCheck() {
			if (this.input.veriCode == "") return null;
			return this.local.veriCode == this.input.veriCode;
		},
	},
	beforeMount() {
		this.local.veriCode = this.randomNum(100000, 999999).toString();
	},
};
</script>
<style scoped>
.login-box {
	width: 50vw;
	margin: 50px auto;
}
.login-card {
	padding: 30px;
	text-align: left;
	border-radius: 10px;
	box-shadow: 0px 0px 3px rgb(214, 214, 214);
	transition: all 0.5s;
}
.login-card h2 {
	text-align: center;
}
.login-card:hover {
	box-shadow: 0px 0px 5px rgb(179, 179, 179);
}
.vericode-img {
	cursor: pointer;
	max-width: 70%;
}
</style>