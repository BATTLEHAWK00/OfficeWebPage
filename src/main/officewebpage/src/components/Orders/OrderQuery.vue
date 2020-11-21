<template>
	<div>
		<b-table
			hover
			:items="table.items"
			:fields="table.fields"
		></b-table>
	</div>
</template>

<script>
export default {
	name: "orderquery",
	data() {
		return {
			table: {
				fields: [
					{
						label: "工单号",
						key: "orderNumber",
					},
					{
						label: "工单类型",
						key: "orderType",
                    },
                    {
						label: "当前状态",
						key: "orderStatus",
                    },
                    {
						label: "提交时间",
						key: "orderDate",
                    }
				],
				items: [
					{
						orderNumber: 40,
						orderType: "Dickerson",
                        orderStatus: "Macdonald",
                        orderDate:"sadasdas"
					},
					{
						orderNumber: 40,
						orderType: "Dickerson",
                        orderStatus: "Macdonald",
                        orderDate:"sadasdas"
                    },
					{
						orderNumber: 40,
						orderType: "Dickerson",
                        orderStatus: "Macdonald",
                        orderDate:"sadasdas"
					},					{
						orderNumber: 40,
						orderType: "Dickerson",
                        orderStatus: "Macdonald",
                        orderDate:"sadasdas"
					},
				],
			},
		};
	},
};
</script>