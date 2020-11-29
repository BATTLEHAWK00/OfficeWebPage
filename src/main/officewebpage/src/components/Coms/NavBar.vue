<template>
	<div>
		<b-navbar
			toggleable="lg"
			type="dark"
			variant="dark"
			class="nav-bar"
			sticky
		>
			<b-navbar-brand href="/">
				{{ GlobalVar.OfficeName }}
			</b-navbar-brand>
			<b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
			<b-collapse id="nav-collapse" is-nav>
				<b-navbar-nav>
					<b-nav-item to="/">首页</b-nav-item>
					<b-nav-item to="/posts">动态</b-nav-item>
					<b-nav-item to="/orderpost" v-if="isLoggedin">
						工单预约
					</b-nav-item>
					<b-nav-item to="/aboutus">关于我们</b-nav-item>
					<b-nav-item>
						<div v-if="loading">
							<b-spinner
								label="Spinning"
								small
								variant="primary"
							></b-spinner>
							<span>加载中...</span>
						</div>
					</b-nav-item>
				</b-navbar-nav>
				<!-- Right aligned nav items -->
				<b-navbar-nav class="ml-auto">
					<b-nav-item-dropdown right v-if="isLoggedin" key="users">
						<!-- Using  'button-content' slot -->
						<template #button-content>
							<em>你好呀,{{ userName }}</em>
						</template>
						<b-dropdown-item @click="goto('/manage')">
							管理后台
						</b-dropdown-item>
						<b-dropdown-item
							@click="Utils.methods.logout($ajax, true)"
						>
							注销
						</b-dropdown-item>
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
</template>
<script>
export default {
	data() {
		return {
			loading: false,
		};
	},
	props: ["userName", "isLoggedin"],
	methods: {
		loadingAnim() {
			var that = this;
			that.loading = true;
			setTimeout(() => {
				that.loading = false;
			}, 300);
		},
		goto(route) {
			this.$router.push(route).catch((err) => {});
		},
	},
	watch: {
		$route(to, from) {
			this.loadingAnim();
		},
	},
};
</script>
<style scoped>
</style>