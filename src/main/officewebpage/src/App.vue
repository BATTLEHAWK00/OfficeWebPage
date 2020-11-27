<template>
	<div id="app">
		<!-- 导航栏 -->
		<div>
			<b-navbar
				toggleable="lg"
				type="dark"
				variant="dark"
				class="nav-bar"
				sticky
			>
				<b-navbar-brand href="/">{{
					GlobalVar.OfficeName
				}}</b-navbar-brand>
				<b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
				<b-collapse id="nav-collapse" is-nav>
					<b-navbar-nav>
						<b-nav-item to="/">首页</b-nav-item>
						<b-nav-item to="/posts">动态</b-nav-item>
						<b-nav-item to="/orderpost" v-if="isLoggedin"
							>工单预约</b-nav-item
						>
						<b-nav-item to="/aboutus">关于我们</b-nav-item>
					</b-navbar-nav>
					<!-- Right aligned nav items -->
					<b-navbar-nav class="ml-auto">
						<b-nav-item-dropdown
							right
							v-if="isLoggedin"
							key="users"
						>
							<!-- Using  'button-content' slot -->
							<template #button-content>
								<em>你好呀,{{ userName }}</em>
							</template>
							<b-dropdown-item @click="Utils.methods.logout($ajax,true)"
								>注销</b-dropdown-item
							>
						</b-nav-item-dropdown>
						<b-navbar-nav right v-else key="users">
							<b-button
								v-if="isLoggedin == false"
								variant="primary"
								style="margin-right: 10px"
								@click="goto('/login')"
								>登录</b-button
							>
							<b-button
								v-if="isLoggedin == false"
								variant="secondary"
								@click="goto('/register')"
								>注册</b-button
							>
						</b-navbar-nav>
					</b-navbar-nav>
				</b-collapse>
			</b-navbar>
		</div>
		<router-view @onLoginSuccess="setLoginState" />
	</div>
</template>

<script>
import Utils from "./Utils";
export default {
	name: "App",
	data() {
		return {
			userName: "请登录",
			isLoggedin: false,
		};
	},
	methods: {
		goto(route) {
			this.$router.replace(route);
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
.nav-bar {
	transition: all 0.5s;
}

ul {
	padding: 0;
}
li {
	list-style: none;
}
body {
	background-color: rgb(247, 247, 247);
}
</style>
