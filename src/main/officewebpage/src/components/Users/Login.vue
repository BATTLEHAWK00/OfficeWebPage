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
							placeholder="输入用户名"
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
    computed:{
		vericodeCheck() {
			if (this.input.veriCode == "")
                return null;
            return this.local.veriCode == this.input.veriCode;
        },
    },
	beforeMount() {
		this.local.veriCode = this.randomNum(100000, 999999);
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
</style>