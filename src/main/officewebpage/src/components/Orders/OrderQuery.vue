<template>
	<div>
		<b-table
			hover
			:items="table.items"
			:fields="table.fields"
			:busy="isBusy"
			class="mt-3"
		>
			<template #cell(status)="data">
				<h6>
					<b-badge :variant="statusBadgeColor(data.item.state)">{{
						data.item.state
					}}</b-badge>
				</h6>
			</template>
		</b-table>
	</div>
</template>

<script>
export default {
	name: "orderquery",
	data() {
		return {
			isBusy: true,
			table: {
				fields: [
					{
						label: "工单号",
						key: "oid",
					},
					{
						label: "工单类型",
						key: "type",
					},
					{
						label: "提交时间",
						key: "submitTime",
					},
					{
						label: "更新时间",
						key: "respTime",
					},
					{
						label: "当前状态",
						key: "status",
					},
				],
				items: [],
			},
		};
	},
	methods: {
		statusBadgeColor(state) {
			switch (state) {
				case "已提交":
				case "已受理":
					return "primary";
				case "待付款":
				case "待完善":
					return "danger";
				case "已完成":
				case "已完结":
					return "success";
				default:
					return "secondary";
			}
		},
	},
	beforeMount() {
		var that = this;
		setTimeout(() => {
			this.$ajax.get("/api/order").then((res) => {
				if (res.data.message == "OK") {
					that.table.items = res.data.data;
					setTimeout(() => {
						that.isBusy = false;
					}, 100);
				}
			});
		}, 200);
	},
};
</script>