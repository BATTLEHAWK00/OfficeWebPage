<template>
	<div>
		<b-form @submit="onSubmit" @reset="onReset">
			<b-form-group
				id="type-select-group"
				label="工单类型:"
				label-for="type-select"
			>
				<b-form-select
					id="type-select"
					v-model="form.orderType"
					:options="orderTypes"
					required
				></b-form-select>
			</b-form-group>
			<b-form-group
				id="desc-input-group"
				label="工单描述:"
				label-for="desc-input"
			>
				<b-form-textarea
					id="desc-input"
					v-model="form.orderDesc"
					placeholder="请输入..."
					rows="3"
					max-rows="6"
					required
				></b-form-textarea>
			</b-form-group>
			<b-form-group
				id="imgs-input-group"
				label="图片描述:"
				label-for="imgs-input"
			>
				<b-form-file
					id="imgs-input"
					v-model="file1"
					:state="Boolean(file1)"
					placeholder="将图片拖拽到这里或手动选择..."
					drop-placeholder="将图片拖拽到这里哟！"
				></b-form-file>
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
			var that = this;
			this.$ajax
				.post("/api/order", this.form)
				.then((res) => {
					if (res.data.message == "OK") {
						alert("提交成功！");
						that.onReset();
						location.reload();
					}
				})
				.catch((res) => {
					alert("提交失败！原因：" + res.data.message);
				});
		},
		onReset() {
			this.form.orderDesc = "";
			this.form.orderType = null;
		},
	},
};
</script>