<template>
	<div>
		<b-img
			alt="验证码"
			:src="local.veriCodeSrc"
			@click="refreshVeriCode"
			class="vericode-img"
		>
		</b-img>
	</div>
</template>
<script>
import Utils from "../../Utils";
export default {
	data() {
		return {
			src: "",
			api: "/api/vericode",
		};
	},
	methods: {
		refreshVeriCode() {
			this.local.veriCodeSrc =
				api + "?num=" + Utils.methods.randomNum(100, 999);
		},
	},
	props: ["verify"],
	beforeMount() {
		this.refreshVeriCode();
	},
};
</script>
<style scoped>
.vericode-img {
	cursor: pointer;
	max-width: 70%;
	/* border-style: solid; */
	border-radius: 5px;
	box-shadow: 1px 1px 3px rgb(223, 223, 223);
	transition: all 0.5s;
}
.vericode-img:hover {
	box-shadow: 1px 1px 5px rgb(197, 196, 196);
	transform: scale(1.02);
}
</style>