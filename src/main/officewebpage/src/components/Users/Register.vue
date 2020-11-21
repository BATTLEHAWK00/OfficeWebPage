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
						:description="descriptions.username"
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
						:description="descriptions.passwd"
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
						:description="descriptions.passwdCheck"
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
						id="passwd-group-check"
						label="验证码:"
						label-for="vericode-check"
						:description="local.veriCode"
					>
						<b-form-input
							id="vericode-check"
							v-model="input.veriCode"
							required
							placeholder="输入验证码"
							:state="vericodeCheck"
						></b-form-input>
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
export default {
	data() {
		return {
			form: {
				username: "",
				passwd: "",
			},
			input: {
				passwdCheck: "",
				veriCode: "",
			},
			local: {
				veriCode: "",
			},
			descriptions: {
				username: "用户名请用英文",
				passwd: "位数介于6-18之间",
				passwdCheck: "",
				veriCode: "",
			},
		};
	},
	methods: {
		onSubmit() {
			this.$ajax.post("/api/login", this.form).then((res) => {
				console.log(res);
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
	},
	computed: {
		passwdCheck() {
			if (this.form.passwd == "") {
				return null;
			}
			return this.form.passwd.length > 6 && this.form.passwd.length < 18;
		},
		passwdTwiceCheck() {
			if (this.form.passwd == "" || this.input.passwdCheck == "") {
				return null;
			}
			return this.form.passwd == this.input.passwdCheck;
		},
		vericodeCheck() {
			if (this.input.veriCode == "") return null;
			return this.local.veriCode == this.input.veriCode;
		},
	},
	beforeMount() {
		this.local.veriCode = this.randomNum(100000, 999999);
	},
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
</style>