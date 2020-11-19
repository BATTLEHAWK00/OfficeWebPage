<template>
	<div style="margin: 2rem auto">
		<h1>动态</h1>
		<div class="postsList">
			<ul>
				<li v-for="i in postsList" class="postItem">
					<div>
						<a href="">{{ i.postName }}</a>
						<span>{{ i.postDate | dateFilter }}</span>
						<p>{{ i.postContent }}</p>
					</div>
				</li>
			</ul>
		</div>
	</div>
</template>
<script>
export default {
	data() {
		return {
			postsList: [],
		};
	},
	filters: {
		dateFilter(tstamp) {
			return new Date(parseInt(tstamp))
				.toLocaleString()
				.replace(/:\d{1,2}$/, " ");
		},
	},
	beforeMount() {
		var that = this;
		this.$ajax.get("/api/posts").then((res) => {
			that.postsList = res.data;
		});
	},
};
</script>
<style scoped>
.postItem {
	display: block;
	width: 60%;
	min-height: 10rem;
	max-height: 15rem;
	margin: 2rem auto;
	padding: 20px;
	background-color: white;
	text-align: left;
	border-radius: 20px;
	box-shadow: 2px 2px 5px rgb(163, 163, 163);
	transition: all 0.5s;
}
.postItem:hover {
	box-shadow: 2px 2px 8px rgb(73, 73, 73);
	width: 65%;
}
.postItem a {
	font-weight: bold;
	color: black;
	font-size: 1.5rem;
	transition: all 0.5s;
}
.postItem a:hover {
	color: cornflowerblue;
	text-decoration: none;
}
.postItem p {
	display: -webkit-box;
	max-height: 10rem;
	-webkit-box-orient: vertical;
	-webkit-line-clamp: 5;
	overflow: hidden;
}
.postItem span {
	margin: 10px;
	color: rgb(143, 143, 143);
}
</style>