<template>
	<div>
		<b-card class="main">
			<h2 class="text-center">工单预约</h2>
			<b-tabs lazy>
				<b-tab title="工单查询">
					<orderquery style="margin-top: 20px"></orderquery>
				</b-tab>
				<b-tab title="工单提交">
					<orderpost style="margin-top: 20px"></orderpost>
				</b-tab>
			</b-tabs>
		</b-card>
	</div>
</template>
<script>
export default {
	components: {
		orderpost: () => import("./Orders/OrderPost"),
		orderquery: () => import("./Orders/OrderQuery")
	},
	data() {
		return {
			show: true
		};
	}
};
</script>
<style scoped>
.main {
	width: 70%;
	margin: 50px auto;
	padding: 0 20px 0 20px;
	text-align: left;
	border-radius: 10px;
	box-shadow: 0px 0px 3px rgb(214, 214, 214);
	transition: all 0.5s;
}
.main:hover {
	box-shadow: 0px 0px 5px rgb(179, 179, 179);
}
</style>
