<template>
	<div style="margin: 2rem auto">
		<h1>动态</h1>
		<div class="postsList">
			<ul>
				<li v-for="i in postsList" class="postItem">
					<div>
						<div>
							<a href="">{{ i.postTitle }}</a>
							<span>{{ i.category }}</span>
							<span>{{ i.postTime | dateFilter }}</span>
						</div>
						<div>
							<b-avatar size="2em"></b-avatar>
							<span class="post-author"
								><strong>{{ i.author }}</strong></span
							>
						</div>
						<div>
							<p class="post-content">{{ i.postContent }}</p>
						</div>
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
			postsList: []
		};
	},
	filters: {
		dateFilter(tstamp) {
			return new Date(parseInt(tstamp))
				.toLocaleString()
				.replace(/:\d{1,2}$/, " ")
				.replace(/(^s*)|(s*$)/g, "");
		}
	},
	beforeMount() {
		var that = this;
		this.$ajax.get("/api/post/getlist").then(res => {
			that.postsList = res.data;
			if (res.data.length == 0) {
				that.postsList.push({
					postTitle: "暂时没有动态哦！",
					postContent:
						"文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容",
					author: "文章作者"
				});
			}
		});
	}
};
</script>
<style scoped>
.postItem {
	display: block;
	width: 60%;
	min-height: 12.5rem;
	max-height: 20rem;
	margin: 2rem auto;
	padding: 20px;
	background-color: white;
	text-align: left;
	border-radius: 20px;
	box-shadow: 2px 2px 5px rgb(209, 209, 209);
	transition: all 0.5s;
}
.postItem:hover {
	box-shadow: 2px 2px 8px rgb(187, 187, 187);
	width: 65%;
}
.postItem div {
	padding-top: 0.5rem;
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
.postItem .post-content {
	display: -webkit-box;
	max-height: 10rem;
	-webkit-box-orient: vertical;
	-webkit-line-clamp: 5;
	overflow: hidden;
}
.postItem .post-author {
	margin: 0;
	color: black;
}
.postItem span {
	margin-left: 5pt;
	color: rgb(143, 143, 143);
}
</style>
