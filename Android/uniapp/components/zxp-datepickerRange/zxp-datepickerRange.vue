<template>
	<view class="view-modal" v-if="showModalView" :class="[modalAnimation?'ani_start':'ani_end']">
		<view class="date-view" :class="[modalAnimation?'ani-top':'ani-btm']">
			<view class="head-view">
				<view class="cancel-view" @click="dateCancelAction">
					<text>Cancel</text>
				</view>
				<view class="title-view">
					<text></text>
				</view>
				<view class="ok-view" @click="dateOkAction">
					<text>Save</text>
				</view>
			</view>
			<view class="mid-view">
				<view class="btn-view" :class="[dateIndex==1?'non-active':'active']" @click="dateAction(0)">
					<view class="tt-view">
						<text :class="[dateIndex==1?'normal-text non-active':'hili-text active']">Start time</text>
						<text :class="[dateIndex==1?'normal-text non-active':'hili-text active']" style="font-size: 24upx;margin-top: 6upx;">{{passageStart |　dateFillter(self)}}</text>
					</view>

				</view>
				<view class="btn-view" :class="[dateIndex==0?'non-active':'active']" @click="dateAction(1)">
					<view class="tt-view">
						<text :class="[dateIndex==0?'normal-text non-active':'hili-text active']">End time</text>
						<text :class="[dateIndex==0?'normal-text non-active':'hili-text active']" style="font-size: 24upx;margin-top: 6upx;">{{passageEnd | dateFillter(self)}}</text>
					</view>
				</view>
			</view>
			<picker-view v-if="viewMode==1" :indicator-style="indicatorStyle" :value="datevalue" @change="bindMutibleDateChange">
				<picker-view-column>
					<view class="item" v-for="(item,index) in years" :key="index">{{item}}y</view>
				</picker-view-column>
				<picker-view-column>
					<view class="item" v-for="(item,index) in months" :key="index">{{item}}m</view>
				</picker-view-column>
				<picker-view-column>
					<view class="item" v-for="(item,index) in days" :key="index">{{item}}d</view>
				</picker-view-column>
			</picker-view>
			<picker-view v-else :indicator-style="indicatorStyle" :value="datevalue" @change="bindMutibleDateChange">
				<picker-view-column>
					<view class="item" v-for="(item,index) in hours" :key="index">{{item}}h</view>
				</picker-view-column>
				<picker-view-column>
					<view class="item" v-for="(item,index) in times" :key="index">{{item}}min</view>
				</picker-view-column>
			</picker-view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			const date = new Date()
			const years = []
			const year = date.getFullYear()
			const months = []
			const month = date.getMonth() + 1
			const days = []
			const day = date.getDate()

			const hours = []
			const times = []

			const maxDate = new Date("2100-12-31")
			for (let i = 1970; i <= maxDate.getFullYear(); i++) {
				years.push(i)
			}
			for (let i = 1; i <= 12; i++) {
				months.push(i)
			}
			for (let i = 1; i <= 31; i++) {
				days.push(i)
			}

			for (let i = 0; i < 24; i++) {
				let vl = i < 10 ? ('0' + i) : i
				hours.push(vl)
			}

			for (let i = 0; i < 60; i++) {
				let vl = i < 10 ? ('0' + i) : i
				times.push(vl)
			}
			let dateProp = []
			if (this.viewMode == 1) {
				dateProp = [year - 1970, month - 1, day - 1]
			} else {
				dateProp = [parseInt(this.dateFormat(this.startDate, 'hh')), parseInt(this.dateFormat(this.startDate, 'mm'))]
			}

			return {
				years,
				months,
				days,
				hours,
				times,
				modalAnimation:false,
				dateIndex: 0,
				datevalue: dateProp,
				viewMode: 1,
				passageStart: this.startDate,
				passageEnd: this.endDate,
				showModalView: this.showMutibleDate,
				indicatorStyle: `height: ${Math.round(uni.getSystemInfoSync().screenWidth/(750/100))}px;`,
				self: this
			};
		},
		filters: {
			dateFillter: function(val, that) {
				if (val != null && val != undefined && val.length > 0 ) {
					if (that.viewMode == 1) {
						return that.dateFormat(val, 'yyyy-MM-dd')
					} else {
						return that.dateFormat(val, 'hh:mm')
					}
				} else {
					return '请选择'
				}
			}
		},
		props: {
			showMutibleDate: {
				type: Boolean,
				default: false
			},
			startDate: {
				type: String,
				default: ""
			},
			endDate: {
				type: String,
				default: ""
			},
			mode: {
				type: String,
				default: "date"
			}
		},
		created() {
			this.passageStart = this.startDate
			this.passageEnd = this.endDate
		},
		computed:{
			dualData:function()
			{
				const { startDate, endDate, mode,showMutibleDate } = this
				return  {
					startDate,
					endDate,
					mode,
					showMutibleDate
				}
			}
		},
		watch: {
			dualData:{
				handler:function(hd){
				    this.showModalView = hd.showMutibleDate
				    this.modalAnimation = hd.showMutibleDate
					
					let dateProp = []
					if (hd.mode == 'date')
					{
						this.viewMode = 1
						let date = new Date(this.dateIndex==0?hd.startDate:hd.endDate)
						if (date)
						{
							const year = date.getFullYear()
							const month = date.getMonth() + 1
							const day = date.getDate()
							this.datevalue = [year - 1970, month - 1, day - 1]
						}
						else{
							this.datevalue = [0, 0, 0]
						}
						
						this.passageStart = hd.startDate
						this.passageEnd = hd.endDate
					}
					else{
						this.viewMode = 2
						const salue = this.hhmmFormat(this.dateIndex==0?hd.startDate:hd.endDate)
						if (salue)
						{
							this.datevalue = [parseInt(this.dateFormat(salue, 'hh')), parseInt(this.dateFormat(salue, 'mm'))]
						}
						
						this.passageStart = this.hhmmFormat(hd.startDate)
						this.passageEnd = this.hhmmFormat(hd.endDate)
					}
				},
				immediate: true
			}
		},
		methods: {
			hhmmFormat:function(date)
			{
				const vl = '2020/12/12 ' + date + ":00"
				return vl
			},
			dateFormat: function(date, fmt) {
				if (date != null && date != undefined) {
					if (typeof date === 'string') {
						//ios设备不支持new Date(yyyy-mm-dd)格式，必须是new Date(yyyy/mm/dd)"才能格式化，否则就返回空对象了
						date = date.replace(/-/g, "/");
						date = new Date(date)
					}
					let ret;
					const opt = {
						"y+": date.getFullYear().toString(), // 年
						"M+": (date.getMonth() + 1).toString(), // 月
						"d+": date.getDate().toString(), // 日
						"h+": date.getHours().toString(), // 时
						"m+": date.getMinutes().toString(), // 分
						"s+": date.getSeconds().toString() // 秒
						// 有其他格式化字符需求可以继续添加，必须转化成字符串
					};
					for (let k in opt) {
						ret = new RegExp("(" + k + ")").exec(fmt);
						if (ret) {
							fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
						};
					};
					return fmt;
				} else {
					return ""
				}
			},
			bindMutibleDateChange: function(e) {
				const val = e.detail.value
				if (this.mode == 'date') {
					let year = this.years[val[0]]
					let month = this.months[val[1]]
					let day = this.days[val[2]]
					if (this.dateIndex == 0) {
						this.passageStart = '' + year + '-' + (month < 10 ? ('0' + month) : month) + '-' + (day < 10 ? ('0' + day) : day)
					} else {
						this.passageEnd = '' + year + '-' + (month < 10 ? ('0' + month) : month) + '-' + (day < 10 ? ('0' + day) : day)
					}
				} else {
					let hour = this.hours[val[0]]
					let time = this.times[val[1]]
					if (this.dateIndex == 0) {
						let date = new Date()
						let p1 = this.dateFormat(date, "yyyy-MM-dd")
						p1 = p1 + ' '

						this.passageStart = p1 + (hour < 10 ? ('0' + hour) : hour) + ':' + (time < 10 ? ('0' + time) : time) + ":00"
					} else {
						let date = new Date()
						let p1 = this.dateFormat(date, "yyyy-MM-dd")
						p1 = p1 + ' '

						this.passageEnd = p1 + (hour < 10 ? ('0' + hour) : hour) + ':' + (time < 10 ? ('0' + time) : time) + ":00"
					}
				}
			},
			dateCancelAction: function() {
				this.modalAnimation = false
				let self = this
				setTimeout(() => {
					self.showModalView = false
					self.$emit('onSelected', null)
				}, 350);
			},
			dateOkAction: function() {
				if (this.passageEnd == null || this.passageEnd.length == 0) {
					uni.showToast({
						title: "未选择结束时间",
						icon: 'none'
					})
				} else {
					this.passageDate = this.passageStart + '~' + this.passageEnd
					this.modalAnimation = false
					
					let self = this
					let data = {
						start: this.passageStart,
						end: this.passageEnd
					}
					if (this.viewMode == 2) {
						data.start = this.dateFormat(data.start, "hh:mm")
						data.end = this.dateFormat(data.end, "hh:mm")
					}
					setTimeout(() => {
						self.showModalView = false
						self.$emit('onSelected', data)
					}, 350);
					
				}
			},
			dateAction: function(e) {
				this.dateIndex = e
				if (e == 0) {
					const date = new Date(Date.parse(this.passageStart.replace(/-/g, "/")))
					if (this.viewMode == 1) {
						const year = date.getFullYear()
						const month = date.getMonth() + 1
						const day = date.getDate()

						this.datevalue = [year - 1970, month - 1, day - 1]
					} else {
						let hour = this.dateFormat(date, "hh")
						let minutes = this.dateFormat(date, 'mm')

						this.datevalue = [parseInt(hour), parseInt(minutes)]
					}
				} else {
					if (this.passageEnd && this.passageEnd.length > 0) {
						let datevl = new Date(this.passageEnd)
						if (this.viewMode == 1) {
							const year = datevl.getFullYear()
							const month = datevl.getMonth() + 1
							const day = datevl.getDate()

							this.datevalue = [year - 1970, month - 1, day - 1]
						} else {
							let h = this.dateFormat(datevl, "hh")
							let m = this.dateFormat(datevl, 'mm')

							this.datevalue = [parseInt(h), parseInt(m)]
						}
					} else {
						let datevl = new Date(this.passageStart)
						if (datevl) {
							if (this.viewMode == 1) {
								const year = datevl.getFullYear() + 1
								const month = datevl.getMonth() + 1
								const day = datevl.getDate()

								this.passageEnd = '' + year + '-' + (month < 10 ? ('0' + month) : month) + '-' + (day < 10 ? ('0' + day) : day)
								this.datevalue = [year - 1970, month - 1, day - 1]
							} else {
								let hour = this.dateFormat(datevl, "hh")
								let minutes = this.dateFormat(datevl, 'mm')

								this.datevalue = [parseInt(hour), parseInt(minutes)]
							}
						}
					}
				}
			},
		}
	}
</script>

<style lang="less">
	.ani_start {
		animation: animationShow 0.4s;
	}

	.ani_end {
		animation: animationHide 0.4s;
	}

	@keyframes animationShow {
		from {
			background-color: rgba(0, 0, 0, 0);
			opacity: 0;
		}

		to {
			background-color: rgba(0, 0, 0, 0.55);
			opacity: 1;
		}
	}

	@keyframes animationHide {
		from {
			background-color: rgba(0, 0, 0, 0.55);
			opacity: 1;
		}

		to {
			background-color: rgba(0, 0, 0, 0);
			opacity: 0;
		}
	}

	.view-modal {
		position: fixed;
		top: calc(var(--status-bar-height) + 44px);
		// bottom: 0;
		height: calc(100% - var(--status-bar-height) - 44px);
		width: 100%;
		z-index: 1;

		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		align-items: center;
		background-color: rgba(0, 0, 0, 0.55);
		
		.ani-btm{
			transform: translateY(640upx);
		}
		
		.ani-top{
			bottom: 0;
			transform: translateY(0upx);
		}
		.date-view {
			position: absolute;
			bottom: 0upx;
			height: 640upx;
			width: 100%;
			transition: all 0.25s;
			background-color: white;

			display: flex;
			flex-direction: column;
			justify-content: flex-start;
			align-items: center;

			.head-view {
				position: relative;
				width: calc(100% - 70upx);
				height: 104upx;

				display: flex;
				flex-direction: row;
				justify-content: space-between;
				align-items: center;


				.cancel-view {
					position: relative;
					width: 124upx;
					height: 100%;

					display: flex;
					flex-direction: column;
					justify-content: center;
					align-items: center;

					text {
						position: relative;
						width: 100%;
						font-size: 32upx;
						color: #333333;

						text-align: left;
					}
				}

				.title-view {
					position: relative;
					height: 100%;

					display: flex;
					flex-direction: column;
					justify-content: center;
					align-items: center;

					text {
						position: relative;
						width: 100%;
						font-size: 30upx;
						color: #999999;

						text-align: left;
					}
				}

				.ok-view {
					position: relative;
					width: 124upx;
					height: 100%;

					display: flex;
					flex-direction: column;
					justify-content: center;
					align-items: center;

					text {
						position: relative;
						width: 100%;
						font-size: 32upx;
						color: #2197FF;

						text-align: right;
					}
				}
			}

			.mid-view {
				position: relative;
				width: 100%;
				height: 106upx;

				border-bottom: 1upx solid rgb(235, 235, 235);
				border-top: 1upx solid rgb(235, 235, 235);
				display: flex;
				flex-direction: row;
				justify-content: center;
				align-items: center;

				.btn-view {
					position: relative;
					width: 50%;
					height: 100%;

					display: flex;
					flex-direction: row;
					justify-content: center;
					align-items: center;

					.tt-view {
						position: relative;
						width: 100%;
						height: 100%;

						display: flex;
						flex-direction: column;
						justify-content: center;
						align-items: center;

						.normal-text {
							position: relative;
							width: 100%;
							font-size: 32upx;
							color: #555555;
							text-align: center;
						}

						.hili-text {
							position: relative;
							width: 100%;
							font-size: 32upx;
							color: #2197FF;
							text-align: center;
						}
					}
				}

				.active {
					background-color: white;
				}

				.non-active {
					background-color: rgb(245, 245, 245);
				}
			}

			picker-view {
				position: relative;
				width: calc(100% - 70upx);
				height: 430upx;
				margin-top: 20rpx;

				background-color: white;
				// display: flex;
				// flex-direction: row;
				// justify-content: space-between;
				// align-items: center;

				.item {
					line-height: 100rpx;
					text-align: center;
				}
			}
		}
	}
</style>
