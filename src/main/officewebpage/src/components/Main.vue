<template>
	<div class="bg-box">
		<div class="main-bg">
			<div class="main">
				<div class="bg-center">
					<h1 class="center-text">{{ GlobalVar.OfficeName }}</h1>
				</div>
				<div class="bg-bottom">
					<a href="http://beian.miit.gov.cn/">{{
						GlobalVar.BeianNumber
					}}</a>
					<p>{{ GlobalVar.OfficeName }} 版权所有</p>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {};
	},
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.bg-box {
	position: absolute;
	top: 0;
	width: 100%;
	height: 100vh;
	overflow: hidden;
	z-index: -1;
}
.main-bg::before {
	content: "";
	position: fixed;
	display: block;
	width: 100%;
	height: 100vh;
	z-index: -1;
	background-image: url("../assets/banner.png");
	background-position: center;
	background-repeat: no-repeat;
	background-size: 100%;
	filter: brightness(0.5) blur(7px);
	transition: all 0.5s;
}
.main-bg:hover::before {
	filter: brightness(0.7);
}
.main {
	width: 100%;
	height: 100vh;
}
.bg-center {
	position: relative;
	top: 40%;
	width: 80%;
	height: 50%;
	margin: 0 auto;
}
.center-text {
	cursor: default;
	letter-spacing: 1.5rem;
	font-size: 4vmax;
	color: white;
	text-shadow: 2px 2px 6px #131313;
	transition: all 0.75s;
}
.center-text:hover {
	letter-spacing: 3rem;
}
.bg-bottom {
	position: absolute;
	bottom: 0;
	width: 100%;
	text-align: center;
}
p {
	font-size: 0.5rem;
	color: rgb(202, 202, 202);
}
a {
	font-size: 0.5rem;
}
</style>
