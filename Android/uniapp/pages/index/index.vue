<template>
	<view class="content">

		<!-- 喝水记录 -->
		<view class="t_c">
			<view :class="item.isCenter?'t_r2':'t_r' " v-for="(item,index) in list" :key="index" :style="{backgroundImage:'url('+item.backUrl+')'}" @click="gotoActivity(item.title)">
				<view class="t_cs">
					<!-- <image v-if="item.isEdit" class="edit_pa" src="../../static/logo.png"></image> -->
					<text :class="item.isEdit?'fonts2':'fontw'">{{item.title}}</text>
					<text class="">{{item.titleTip}}</text>
				</view>
				<view class="t_jl">
					<text class="fonts">{{item.subTip}}</text>
					<text class="fontw">{{item.subTitle}}</text>
					<text class="fonts">{{item.subTitleTip}}</text>
				</view>
				 
			</view> 
		</view>
 
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello',
				list: [
					// {
					// 	backUrl: "../../static/ico_bg.png",
					// 	title: "Sleep time",
					// 	titleTip: "",
					// 	subTip: "",
					// 	subTitle: "",
					// 	subTitleTip: "",
					// 	isShow: true,
					// 	isCenter: true,
					// 	isEdit:false
					// },
					// {
					// 	backUrl: "../../static/ico_bg.png",
					// 	title: "Weight",
					// 	titleTip: "",
					// 	subTip: "",
					// 	subTitle: "",
					// 	subTitleTip: "",
					// 	isShow: true,
					// 	isCenter: true,
					// 	isEdit:false
					// },
					{
						backUrl: "../../static/ico_bg.png",
						title: "Blood Pressure",
						titleTip: "",
						subTip: "",
						subTitle: "",
						subTitleTip: "",
						isShow: true,
						isCenter: true,
						isEdit:false
					},
					{
						backUrl: "../../static/ico_bg.png",
						title: "Heart Rate",
						titleTip: "",
						subTip: "",
						subTitle: "",
						subTitleTip: "",
						isShow: true,
						isCenter: true,
						isEdit:false
					},
					{
						backUrl: "../../static/ico_bg.png",
						title: "Waist Circumference",
						titleTip: "",
						subTip: "",
						subTitle: "",
						subTitleTip: "",
						isShow: true,
						isCenter: true,
						isEdit:false
					},
					{
						backUrl: "../../static/ico_bg.png",
						title: "Exercise Duration",
						titleTip: "",
						subTip: "",
						subTitle: "",
						subTitleTip: "",
						isShow: true,
						isCenter: true,
						isEdit:false
					},
					{
						backUrl: "../../static/ico_bg.png",
						title: "Height",
						titleTip: "",
						subTip: "",
						subTitle: "",
						subTitleTip: "",
						isShow: true,
						isCenter: true,
						isEdit:false
					},
					{
						backUrl: "../../static/ico_bg.png",
						title: "Temperature",
						titleTip: "",
						subTip: "",
						subTitle: "",
						subTitleTip: "",
						isShow: true,
						isCenter: true,
						isEdit:false
					},
					{
						backUrl: "../../static/ico_bg.png",
						title: "Medicine",
						titleTip: "",
						subTip: "",
						subTitle: "",
						subTitleTip: "",
						isShow: true,
						isCenter: true,
						isEdit:false
					},
					{
						backUrl: "../../static/ico_bg.png",
						title: "Disease Status",
						titleTip: "",
						subTip: "",
						subTitle: "",
						subTitleTip: "",
						isShow: true,
						isCenter: true,
						isEdit:false
					}
				]
			}
		},
		onLoad(options) {
			var new_data = options.name.split(";");
			if(!new_data == ""){
				for(var i=0 ; i < new_data.length; i++){
					var temp_element = 					{
							backUrl: "../../static/ico_bg.png",
							title: new_data[i],
							titleTip: "",
							subTip: "",
							subTitle: "",
							subTitleTip: "",
							isShow: true,
							isCenter: true,
							isEdit:false
						}
					this.list.push(temp_element)
				}
			}
			
		},
		methods: { 
			gotoActivity(activity){
				console.log("aaaasdasasdasd")
				nativeMethod.toActivity(activity);
			}
		}
	}
</script>

<style>
	page {
		background-color: #f5f6fa;
	}

	.content {
		margin-top: 80rpx;
	}

	.top {
		background-color: white;
		margin: 20rpx;
		border-radius: 20rpx;
		padding: 30rpx 20rpx;
	}

	.top {
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
	}

	.text1 {
		font-weight: 800;
		font-size: 40rpx;
		height: 40rpx;
	}

	.aa {
		font-size: 28rpx;
		color: #999999;
		margin-left: 10rpx;
		margin-right: 10rpx;
		line-height: 40rpx;
		height: 40rpx;
		text-align: center;
	}

	.t_1 {
		width: 30rpx;
		height: 18rpx;
		margin-bottom: 10rpx;
	}

	.t_2 {
		width: 300rpx;
		height: 55rpx;
	}

	.lef {
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-items: center;
	}

	.t_c {
		display: flex;
		flex-direction: row;
		margin: 20rpx;
		justify-content: space-between;
		flex-wrap: wrap;
	}

	.t_r {
		width: 49%;
		background-color: #ffffff;
		height: 260rpx;
		padding: 30rpx;
		border-radius: 20rpx;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		/* background-image: url("../../static/ico_bg.png"); */
		background-size: 100% 100%;
		background-repeat: no-repeat;
		box-sizing: border-box;
		margin-top: 10rpx;
	}

	.t_r2 {
		width: 49%;
		background-color: #ffffff;
		height: 260rpx;
		padding: 30rpx;
		border-radius: 20rpx;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		/* background-image: url("../../static/ico_bg.png"); */
		background-size: 100% 100%;
		background-repeat: no-repeat;
		align-items: flex-start;
		padding-top: 80rpx;
		box-sizing: border-box;
		margin-top: 10rpx;
	}

	.t_r3 {
		width: 49%;
		background-color: #ffffff;
		height: 260rpx;
		padding: 30rpx;
		border-radius: 20rpx;
		display: flex;
		flex-direction: row;
		justify-content: center;
		background-size: 100% 100%;
		background-repeat: no-repeat;
		align-items: center;
		padding-top: 80rpx;
		box-sizing: border-box;
	}

	.edit_pa {
		width: 30rpx;
		height: 30rpx;
		margin-right: 10rpx;
	}

	.t_jl {
		display: flex;
		flex-direction: row;
		margin-bottom: 20rpx;
		justify-content: flex-start;
		align-items: center;
	}

	.fontw {
		font-size: 34rpx;
		color: black;
		font-weight: 800;
	}

	.fonts {
		font-size: 28rpx;
		color: #999999;
	}

	.fonts2 {
		font-size: 30rpx;
		color: #76bf9c;
		font-weight: 800;
	}

	.t_cs {
		display: flex;
		flex-direction: column;
	}

	.t_cs text:nth-child(even) {
		font-size: 26rpx;
	}
</style>