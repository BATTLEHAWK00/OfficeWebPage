<template>
	<div>
		<b-card class="main">
			<h2 class="text-center">关于我们</h2>
			<h3>广域科技互联办公室</h3>
			<p>这里放简介</p>
			<h4>联系我们</h4>
			<p>电话: <br />邮箱: <br />QQ: <br />微信: <br />地址:黄冈师范学院逸夫楼314</p>
		</b-card>
	</div>
</template>
<script>
export default {
	data() {
		return {};
	},
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
p{
  margin: 20pt;
}
h3{
  margin: 20pt 0 20pt 0;
}
h4{
  margin: 20pt 0 20pt 0;
}
</style>