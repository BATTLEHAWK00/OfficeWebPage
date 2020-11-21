<template>
	<div>
		<b-form @submit="onSubmit" @reset="onReset">
			<b-form-group
				id="input-group-1"
				label="工单描述:"
				label-for="input-1"
			>
				<b-form-textarea
					id="textarea"
					v-model="form.orderDesc"
					placeholder="请输入..."
					rows="3"
					max-rows="6"
					required
				></b-form-textarea>
			</b-form-group>

			<b-form-group
				id="input-group-3"
				label="工单类型:"
				label-for="input-3"
			>
				<b-form-select
					id="input-3"
					v-model="form.orderType"
					:options="orderTypes"
					required
				></b-form-select>
			</b-form-group>
			<b-button type="submit" variant="primary">提交</b-button>
			<b-button type="reset" variant="danger">重置</b-button>
		</b-form>
	</div>
</template>
<script>
export default {
	name: "orderpost",
	data() {
		return {
			form: {
				orderDesc: "",
				orderType: null,
			},
			orderTypes: [
				{
					text: "请选择",
					value: null,
				},
				"硬件维修",
				"软件配置",
				"系统维护",
				"打印复印",
				"PPT定制",
				"VR定制",
				"小程序开发",
				"软件定制",
			],
		};
	},
	methods: {
		onSubmit(evt) {
			evt.preventDefault();
			this.$ajax
				.post("/api/order", this.form)
				.then((res) => {
					if (res.data.message == "OK") alert("提交成功！");
				})
				.catch((res) => {
					alert("提交失败！原因：" + res.data.message);
				});
		},
		onReset(evt) {
			evt.preventDefault();
			this.form.orderDesc = "";
			this.form.orderType = null;
		},
	},
};
</script>