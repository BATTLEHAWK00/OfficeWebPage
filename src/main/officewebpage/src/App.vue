<template>
	<div id="app">
		<!-- 导航栏 -->
		<div>
			<my-navbar :userName="userName" :isLoggedin="isLoggedin"></my-navbar>
		</div>
		<div>
			<router-view @onLoginSuccess="setLoginState" />
		</div>
	</div>
</template>

<script>
import Utils from "./Utils";
export default {
	name: "App",
	components: {
		myNavbar: () => import("./components/Coms/NavBar.vue"),
	},
	data() {
		return {
			userName: "请登录",
			isLoggedin: false,
		};
	},
	methods: {
		goto(route) {
			this.$router.push(route).catch((err) => {});
		},
		setLoginState() {
			var that = this;
			Utils.methods.updateUserState(this.$ajax, false, (user) => {
				that.isLoggedin = true;
				that.userName = user.username;
			});
		},
	},
	beforeMount() {
		this.setLoginState();
	},
};
</script>

<style>
#app {
	font-family: "Avenir", Helvetica, Arial, sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	text-align: center;
	color: #2c3e50;
}
ul {
	padding: 0;
}
li {
	list-style: none;
}
body {
	background-color: rgb(239, 240, 245);
}
</style>
