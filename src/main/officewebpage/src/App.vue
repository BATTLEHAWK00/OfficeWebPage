<template>
	<div id="app">
		<!-- 导航栏 -->
		<div>
			<b-navbar
				toggleable="lg"
				type="dark"
				variant="info"
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
						<b-nav-item to="/orderpost">工作预约</b-nav-item>
						<b-nav-item to="/aboutus">关于我们</b-nav-item>
					</b-navbar-nav>
					<!-- Right aligned nav items -->
					<b-navbar-nav class="ml-auto">
						<b-nav-item-dropdown right>
							<!-- Using 'button-content' slot -->
							<template #button-content>
								<em>{{ userName }}</em>
							</template>
							<b-nav-item>注销</b-nav-item>
						</b-nav-item-dropdown>
					</b-navbar-nav>
				</b-collapse>
			</b-navbar>
		</div>
		<router-view />
	</div>
</template>

<script>
export default {
	name: "App",
	data() {
		return {
			userName: "请登录",
		};
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
	background-color: white;
}
ul{
  padding: 0;
}
li{
  list-style: none;
}
body{
  background-color: rgb(240, 240, 240);
}
</style>
