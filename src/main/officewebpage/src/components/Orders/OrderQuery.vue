<template>
	<div>
		<b-table
			hover
			:items="table.items"
			:fields="table.fields"
			:busy="isBusy"
		>
			<template #cell(status)="data">
				<h6>
					<b-badge :variant="statusBadgeColor(data.item.state)">{{
						data.item.state
					}}</b-badge>
				</h6>
			</template>
			<template #cell(submitTime)="data">
				{{ data.item.submitTime | dateFilter }}
			</template>
			<template #cell(respTime)="data">
				{{ data.item.respTime | dateFilter }}
			</template>
			<template #cell(operation)="data">
				<a href="javascript:void(0)" @click="orderPay">去支付</a>
				<a href="javascript:void(0)" @click="orderDelete(data.item.oid)"
					>删除</a
				>
				<a href="javascript:void(0)" @click="orderReminder">催单</a>
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
						key: "oid"
					},
					{
						label: "工单类型",
						key: "type"
					},
					{
						label: "提交时间",
						key: "submitTime"
					},
					{
						label: "更新时间",
						key: "respTime"
					},
					{
						label: "当前状态",
						key: "status"
					},
					{
						label: "操作",
						key: "operation"
					}
				],
				items: []
			}
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
		orderDelete(oid) {
			console.log(oid);
			this.$ajax
				.get("/api/order/delete?oid=" + oid)
				.then(res => {
					alert("删除成功！");
					location.reload();
				})
				.catch(err => {
					alert("删除失败！错误：" + err.response.data.data);
				});
		}
	},
	filters: {
		dateFilter(tstamp) {
			if (tstamp == 0) return "无";
			return new Date(parseInt(tstamp))
				.toLocaleString()
				.replace(/:\d{1,2}$/, " ");
		}
	},
	beforeMount() {
		var that = this;
		setTimeout(() => {
			this.$ajax
				.get("/api/order/getlist", {
					params: {
						uid: JSON.parse(window.sessionStorage.getItem("user"))
							.uid
					}
				})
				.then(res => {
					if (res.data.message == "OK") {
						that.table.items = res.data.data;
						if (that.table.items.length == 0)
							that.table.items.push({
								type: "你还没有提交工单哦！",
								submitTime: 0,
								respTime: 0
							});
						setTimeout(() => {
							that.isBusy = false;
						}, 100);
					}
				});
		}, 200);
	}
};
</script>
