<!-- <template>
<view><l-echart ref="chart" @finished="init"></l-echart></view>
</template>

<script>

// 方式三：按需引入
// 按需引入 开始
import * as echarts from 'echarts';


//-------------按需引入结束------------------------

export default {
    data() {
        return {
            option: {

  tooltip : {
            borderWidth: 0,
                trigger: 'axis',
                axisPointer: {
            type: "shadow"
        },

                formatter: "{a}</br>{b}",
                "textStyle": {
            "fontSize": 14
        },
        "borderWidth": 0
            },
            legend: {
                data: ['Work day', 'Weekend'],
                selected:{'Work day':true,'Weekend':false},
                selectedMode:'single',
            },
            xAxis:
            {
                type : 'category',
               data : ['0:00', '0:30', '1:00', '1:30','2:00', '2:30','3:00', '3:30','4:00', '4:30','5:00', '5:30','6:00', '6:30','7:00', '7:30','8:00', '8:30','9:00', '9:30','10:00', '10:30','11:00', '11:30','12:00', '12:30','13:00', '13:30','14:00', '14:30','15:00', '15:30','16:00', '16:30','17:00', '17:30','18:00', '18:30','19:00', '19:30','20:00', '20:30','21:00', '21:30','22:00', '22:30','23:00', '23:30','24:00'],

                axisTick: {
                    alignWithLabel: true,

                    }
            }
             ,
            yAxis:
                {
                    type:'value',
                    show: false
                }
            ,
            grid:{

            },
            series:[
            {
                    name:'Work day',
                    type:'line',
                    data:[0.00557815897854788,0.004932567548848999,0.0030784302988751916,0.0014852730651300904,0.0005859643251687418,0.0003990156197576467,0.0006965685425032484,0.0011178404943356858,0.0017939224420939225,0.00333752294594081,0.005484489036697004,0.007885812209732996,0.012189973964292212,0.01873503812145681,0.022648141779701807,0.020471345762443936,0.01622570189809681,0.014125623645583259,0.013852469129519842,0.013654987514369145,0.012539092723606239,0.011626254686489751,0.012527782310231701,0.014705968209942336,0.01573306241088555,0.014786887407535677,0.014247666318650815,0.014757204033717287,0.014852112030539134,0.015646340044841965,0.022646259338348927,0.0479402737639148,0.09950901598082341,0.14875299437354159,0.14875434256623685,0.10235709015786605,0.05289196267985761,0.02457844452314121,0.013010279223445604,0.007986959058861199,0.005756699907962943,0.004868710478115595,0.0033078782587059436,0.001398358331660338,0.0003434148648135818,4.8324727176621144e-05,3.886341742574114e-06,1.7853493488245285e-07],
                    "showSymbol": false,
                    lineStyle:{
                        color:'#655432',

                    },
                    hoverAnimation:true,


                    itmeStyle:{
                        color:"#fe3332"
                    },
                    smooth:true,
                    areaStyle: {
                opacity: 1,

 "color": new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#f9bc08'}, {offset: 1, color: '#fbdd7e'}], false)
            },
                },

                {
                    name:'Weekend',
                    type:'line',
					data: [8.101146575460795e-208,2.2262963598451309e-190,1.026954433440056e-173,7.951536993299192e-158,1.0334339033986846e-142,2.2544781898849805e-128,8.255456304611224e-115,5.074203021800133e-102,5.235115983540621e-90,9.066016139905511e-79,2.635350471924406e-68,1.2858549571684608e-58,1.053118642629797e-49,1.4477525577858754e-41,3.3407436978938097e-34,1.2939696040587033e-27,8.412770006527318e-22,9.181199989094544e-17,1.6821846395778396e-12,5.179264498460368e-09,2.694564639660213e-06,0.000244535211271986,0.004517779366983922,0.02501467504879325,0.04438966957280604,0.017040297010403698,0.0011586355396248182,1.7752096435632197e-05,0.0003814973013535482,0.005549785451190046,0.01382821612700825,0.013943489607935363,0.0839179737393733,0.2183783581773079,0.16884959785573145,0.05784579121041084,0.04235075739179921,0.09144916800276008,0.06254381662026211,0.03146159373193639,0.01237104517803037,0.008792966898410208,0.003613041000761382,0.0047489839577992765,0.0250173437633581,0.0443896747436619,0.0170402970093106,0.0011586270077126328],
                    "showSymbol": false,
                    lineStyle:{
                        color:'#655432',

                    },
                    hoverAnimation:true,


                    itmeStyle:{
                        color:"#fe3332"
                    },
                    smooth:true,
                    areaStyle: {
                opacity: 1,

"color": new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#2242c7'}, {offset: 1, color: '#87CEFA'}], false)
            },
                }
            ]

















            },
        };
    },
    // 组件能被调用必须是组件的节点已经被渲染到页面上
    // 1、在页面mounted里调用，有时候mounted 组件也未必渲染完成
    mounted() {
        // init(echarts, theme?:string, opts?:{}, chart => {})
        // echarts 必填， 非nvue必填，nvue不用填
        // theme 可选，应用的主题，目前只支持名称，如：'dark'
        // opts = { // 可选
        //  locale?: string  // 从 `5.0.0` 开始支持
        // }
        // chart => {} ， callback 返回图表实例
        this.$refs.chart.init(echarts, chart => {
            chart.setOption(this.option);
        });
    },
    // 2、或者使用组件的finished事件里调用
    methods: {
        async init() {
            // chart 图表实例不能存在data里
            const chart = await this.$refs.chart.init(echarts);
            chart.setOption(this.option)
        }
    }
}
</script>

<style lang="less" scoped>
	.uni-ec-canvas,
	.container{
		width: 750rpx;
		height: 750rpx;
	}
		.c1_left {
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
		}
</style -->>




<template>
	<view>

		<!-- 时间 -->

		<!-- 应时间 -->
		<view class="test_top">
		<view class="asaa">
			<view class="charts-box3">
				
				<qiun-data-charts type="arcbar" :opts="arcOpts" :chartData="arcChartData" class="zzA" />
				<view>{{time}}</view>
			</view>

			<view class="arc_right">
				<text class="aa1">Eating window</text>
				<text class="aa2">Now</text>
				<text class="aa3">{{diet_flag}}</text>

				
			</view>
			
		</view>
		<view class="aa5">
							<text class="aa4">Click to edit eating time:</text>
		<!-- 					<picker mode="time" @change="getDate1">
								<text class="aa4">{{time1}}</text>
							</picker>
							<text class="aa4">~</text>
							<picker mode="time"  @change="getDate2">
								<text class="aa4">{{time2}}</text>
							</picker> -->
		
							<view @click="dateModeAction">
		<!-- 						<text class="form-text">检查时间</text> -->
								<view>{{timeValue.length>0?timeValue:'Please select time'}}</view>
							</view>
		
							<date-range 
							mode="time"
								:showMutibleDate="showMutibleDate" 
								:startDate="startDate" 
								:endDate="endDate" @onSelected ="dateSelected"></date-range>
							
							
							
							
							
							
							
							
							<!-- <image src="../../static/logo.png"></image> -->
						</view>

		</view>

		<view class="charts-box2"><l-echart ref="chart" @finished="init"></l-echart></view>
		
		  <view class="top">
<l-echart ref="sleep_chart"></l-echart>
		  </view>
		  
		  <view class="top">
		  <l-echart ref="sleep_dis_chart"></l-echart>
		  		  </view>
		  
		  
		  <view class="top">
		  	<view class="c1_left">
		  		<text>Minimum</text>
		  		<text>{{min}}</text>
		  	</view>
		  	<view>
		  		<view class="charts-box">
		  			<qiun-data-charts type="gauge" :opts="opts" :chartData="chartsDataGauge1" />
		  		</view>
		  	</view>
		  	<view class="c1_left">
		  		<text>Maximum</text>
		  		<text>{{max}}</text>
		  	</view>
		  </view>
		
		
		
		
	</view>
</template>

<script>
	import * as echarts from 'echarts';
	import dateRange from '@/components/zxp-datepickerRange/zxp-datepickerRange.vue';
	export default {
				components: {
					dateRange
				},
		data() {
			return {
				
				// startDate: '8:00',
				// endDate: '16:00',
				// showMutibleDate: false,
				// viewMode: 'time',
				// dateValue: '',
				// timeValue: '',
				canedittime: "false",
				startDate: '08:00',
								endDate: '16:00',
								showMutibleDate: false,
								viewMode: 'time',
								dateValue: '',
								timeValue: '8:00 to 16:00',
				
				
				sleepData: {},
				sleepDisData: {},
				 sleep_opts: {
				        color: ["#1890FF","#91CB74","#FAC858","#EE6666","#73C0DE","#3CA272","#FC8452","#9A60B4","#ea7ccc"],
				        padding: [15,10,0,15],
				        enableScroll: true,
				        legend: {},
				        xAxis: {
				          disableGrid: true,
						  scrollShow: true,
						  scrollAlign: 'right',
						  itemCount:3,
				        },
				        yAxis: {
				          gridType: "dash",
				          dashLength: 2
				        },
				        extra: {
				          line: {
				            type: "straight",
				            activeType: "hollow"
				          }
				        }
				      },
				
				
				
				plot_color: "#2fc25b",
				diet_flag: "Eating time",
				arr: [],
				min_arr: [],
				weight_new : "",
				max_weight: 0,
				min_weight: 0,
				weight: "0kg",
				option: {},

				

				chartsDataGauge1: {},
				sleepchartopt: {},

				barChartData: {},
				barOpts: {
					color: ["#9A60B4"],
					padding: [15, 15, 0, 5],
					enableScroll: false,
					legend: {
						show: false
					},
					height: 200,
					xAxis: {
						disableGrid: true,
						axisLine: true
					},
					yAxis: {
						disableGrid: true,
						data: [{
							min: 0
						}]
					},
					extra: {
						column: {
							type: "group",
							width: 30,
							activeBgColor: "#000000",
							activeBgOpacity: 0.08
						}
					}
				},
				arcChartData: {},
				arcOpts: {
					color: ["#1890FF"],
					padding: undefined,
					with: 100,
					height: 100,
					title: {
						name: "Time remaining",
						fontSize: 13,
						color: "#999999"
					},
					subtitle: {
						name: "",
						fontSize: 20,
						color: "#000000"
					},
					extra: {
						arcbar: {
							type: "circle",
							width: 10,
							backgroundColor: "#E9E9E9",
							startAngle: 1.5,
							endAngle: 0.25,
							gap: 1
						}
					}
				},
				timer: null, //重复执行
				time: "0:0:0",
				secondTime: 0,
				minuteTime: 0,
				hourTime: 0,
				needTime: 1890,
				time1:"08:00",
				time2:"16:00"
			}
		},
		// mounted() {
		//     // init(echarts, theme?:string, opts?:{}, chart => {})
		//     // echarts 必填， 非nvue必填，nvue不用填
		//     // theme 可选，应用的主题，目前只支持名称，如：'dark'
		//     // opts = { // 可选
		//     //  locale?: string  // 从 `5.0.0` 开始支持
		//     // }
		//     // chart => {} ， callback 返回图表实例
		//     // this.$refs.chart.init(echarts, chart => {
		//     //     chart.setOption(this.option);
		//     // });
		// },
		onLoad(options) {

			
			
			// uni.showToast({
			// 	title: options.abc
			// });
			var new_data = options.abc.split(",");
			var new_data1 = options.categories.split(",");
			var weight = options.weight + "kg"
			this.weight_new = options.weight
			var height = Number(options.height)
			this.arr = [24*(height/100)*(height/100), Number(options.weight)]
			this.min_arr = [18.5*(height/100)*(height/100), Number(options.weight)]
			var max_num = 24*(height/100)*(height/100)> Number(options.weight)?24*(height/100)*(height/100):Number(options.weight)
			var min_num = 18.5*(height/100)*(height/100)< Number(options.weight)?18.5*(height/100)*(height/100):Number(options.weight)
			var percentage = (Number(options.weight) - min_num)/(max_num-min_num)
			this.needTime = Number(options.timetotal)
			var color = options.eatflag
			var sleep_x = options.sleep_x.split(",")
			var sleep_start_time = options.sleep_start_time.split(",")
			var sleep_end_time = options.sleep_end_time.split(",")
			
			var sleep_dis_x = options.sleep_dis_x.split(",")
			var sleep_dis_y1 = options.sleep_dis_y1.split(",")
			var sleep_dis_y2 = options.sleep_dis_y2.split(",")
			
			
			var workday_list = options.workday_list.split(",")
			var weekend_list = options.weekend_list.split(",")
			var time_percentage = parseFloat(options.percentage)
			
			this.startDate = options.start_time
			this.endDate = options.end_time
			this.timeValue = this.startDate + " to " + this.endDate
			this.canedittime = options.canedittime
			
			// this.min_weight = min(18.5*(height/100)*(height/100), double(options.weight))
			this.time = this.formatSeconds(this.needTime);
			this.getServerData(new_data,new_data1,weight,percentage,color,sleep_x,sleep_start_time,sleep_end_time,sleep_dis_x,sleep_dis_y1,sleep_dis_y2,workday_list,weekend_list,time_percentage)
		},
		computed: {
			max() {
				return Math.max.apply(null, this.arr).toFixed(1)
			},
			min() {
				return Math.min.apply(null, this.min_arr).toFixed(1)
			},
		},
		
		methods: {
dateSelected: function(e) {
				// if (e) {
				// 	let start = e.start
				// 	let end = e.end
				// 	this.timeValue = start + '至' + end
				// 	// if (this.viewMode == 'date') {
				// 	// 	this.dateValue = start + '至' + end
				// 	// } else {
				// 	// 	this.timeValue = start + '至' + end
				// 	// }
				// 	this.startDate = start,
				// 	this.endDate = end
				// 	this.StartTime = start
				// 	this.EndTime = end
				// }
				// this.showMutibleDate = false
				
								if (e) {
									let start = e.start
									let end = e.end

									if(start < end){
										
										if(this.canedittime=="true"){
											if (this.viewMode == 'date') {
												this.dateValue = start + ' to ' + end
											} else {
												this.timeValue = start + ' to ' + end
											}
											this.startDate = start,
											this.endDate = end
											this.StartTime = this.startDate
											this.EndTime = this.end
											this.getTimefromHtml(start, end,"true")
										}else{
											this.getTimefromHtml(start, end,"true")
										}
										
									}else{
										this.getTimefromHtml(start, end,"false")
									}
									
								}
								this.showMutibleDate = false
								

			},

			dateModeAction: function() {
				this.viewMode = 'time'
				this.showMutibleDate = true
			},

			
			async init() {
			    // chart 图表实例不能存在data里
			    const chart = await this.$refs.chart.init(echarts);
			    chart.setOption(this.option)
				
				const sleep_chart = await this.$refs.sleep_chart.init(echarts);
				sleep_chart.setOption(this.sleepchartopt)
				
				const sleep_dis_chart = await this.$refs.sleep_dis_chart.init(echarts);
				sleep_dis_chart.setOption(this.sleepDisData)
				
				
			},
			htmlRefresh(){
				console.log("aaaasdasasdasd")
				android.htmlRefresh();
			},
			
			
			getTimefromHtml(start_time, end_time,flag){
				android.getTimefromHtml(start_time, end_time,flag);
			},
			
			
			formatSeconds(value) {
				this.secondTime = parseInt(value); // 秒
				this.minuteTime = 0; // 分
				this.hourTime = 0; // 小时
				if (this.secondTime > 60) { //如果秒数大于60，将秒数转换成整数
					//获取分钟，除以60取整数，得到整数分钟
					this.minuteTime = parseInt(this.secondTime / 60);
					//获取秒数，秒数取余，得到整数秒数
					this.secondTime = parseInt(this.secondTime % 60);
					//如果分钟大于60，将分钟转换成小时
					if (this.minuteTime > 60) {
						//获取小时，获取分钟除以60，得到整数小时
						this.hourTime = parseInt(this.minuteTime / 60);
						//获取小时后取余的分，获取分钟除以60取余的分
						this.minuteTime = parseInt(this.minuteTime % 60);
					}
				}
				var result = "" + parseInt(this.secondTime);
			
				if (this.minuteTime > 0) {
					result = "" + parseInt(this.minuteTime) + ":" + result;
				}
				if (this.hourTime > 0) {
					result = "" + parseInt(this.hourTime) + ":" + result;
				}
				console.log('result', result);
				this.timer = setInterval(() => {
					this.timeCount()
				}, 1000)
				return result;
			},
			timeCount() {
				if (this.hourTime <= 0) {
					//this.secondTime = 59;
					//this.minuteTime = 59;
				}
			
				--this.secondTime;
				if (this.secondTime <= 0) {
					--this.minuteTime;
					this.secondTime = 59;
				}
			
				if (this.minuteTime <= 0) {
					--this.hourTime;
					this.minuteTime = 59
				}
			
			
				this.time = ((this.hourTime) + ":" + (this.minuteTime >9 ? this.minuteTime:"0"+this.minuteTime) + ":" + (this.secondTime>9 ? this.secondTime:"0"+this.secondTime))
				if(this.time=="0:01:01"){
					this.htmlRefresh();
				}
					--this.needTime 
			//	this.arcOpts.subtitle.name=this.time
				if (this.needTime <= 60) { //两个时间相等 去结算
					
					// if (this.timer) {
					// 	gotoActivity()
					// 	//clearInterval(this.timer)
					// }
				}
				//console.log(this.prc)
			},
			getServerData(new_data, new_data1,weight,percentage,color,sleep_x,sleep_start_time,sleep_end_time,sleep_dis_x,sleep_dis_y1,sleep_dis_y2,workday_list,weekend_list,time_percentage) {
				
				this.option= {
				
				  tooltip : {
				            borderWidth: 0,
				                trigger: 'axis',
				                axisPointer: {
				            type: "shadow"
				        },
				
				                formatter: "{a} {b}",
				                "textStyle": {
				            "fontSize": 14
				        },
				        "borderWidth": 0
				            },
				            legend: {
				                data: ['Work day', 'Weekend'],
				                selected:{'Work day':true,'Weekend':false},
				                selectedMode:'single',
				            },
				            xAxis:
				            {
				                type : 'category',
				               data : ['0:00', '0:30', '1:00', '1:30','2:00', '2:30','3:00', '3:30','4:00', '4:30','5:00', '5:30','6:00', '6:30','7:00', '7:30','8:00', '8:30','9:00', '9:30','10:00', '10:30','11:00', '11:30','12:00', '12:30','13:00', '13:30','14:00', '14:30','15:00', '15:30','16:00', '16:30','17:00', '17:30','18:00', '18:30','19:00', '19:30','20:00', '20:30','21:00', '21:30','22:00', '22:30','23:00', '23:30','24:00'],
				
				                axisTick: {
				                    alignWithLabel: true,
				
				                    },
									chartData: {},
				            }
				             ,
				            yAxis:
				                {
				                    type:'value',
				                    show: false
				                }
				            ,
				            grid:{
				
				            },
				            series:[
				            {
				                    name:'Work day',
				                    type:'line',
				                    // data:[0.00557815897854788,0.004932567548848999,0.0030784302988751916,0.0014852730651300904,0.0005859643251687418,0.0003990156197576467,0.0006965685425032484,0.0011178404943356858,0.0017939224420939225,0.00333752294594081,0.005484489036697004,0.007885812209732996,0.012189973964292212,0.01873503812145681,0.022648141779701807,0.020471345762443936,0.01622570189809681,0.014125623645583259,0.013852469129519842,0.013654987514369145,0.012539092723606239,0.011626254686489751,0.012527782310231701,0.014705968209942336,0.01573306241088555,0.014786887407535677,0.014247666318650815,0.014757204033717287,0.014852112030539134,0.015646340044841965,0.022646259338348927,0.0479402737639148,0.09950901598082341,0.14875299437354159,0.14875434256623685,0.10235709015786605,0.05289196267985761,0.02457844452314121,0.013010279223445604,0.007986959058861199,0.005756699907962943,0.004868710478115595,0.0033078782587059436,0.001398358331660338,0.0003434148648135818,4.8324727176621144e-05,3.886341742574114e-06,1.7853493488245285e-07],
				                    data: workday_list,
									"showSymbol": false,
				                    lineStyle:{
				                        color:'#655432',
				
				                    },
				                    hoverAnimation:true,
				
				
				                    itmeStyle:{
				                        color:"#fe3332"
				                    },
				                    smooth:true,
				                    areaStyle: {
				                opacity: 1,
				
				 "color": new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#f9bc08'}, {offset: 1, color: '#fbdd7e'}], false)
				            },
				                },
				
				                {
				                    name:'Weekend',
				                    type:'line',
									// data: [8.101146575460795e-208,2.2262963598451309e-190,1.026954433440056e-173,7.951536993299192e-158,1.0334339033986846e-142,2.2544781898849805e-128,8.255456304611224e-115,5.074203021800133e-102,5.235115983540621e-90,9.066016139905511e-79,2.635350471924406e-68,1.2858549571684608e-58,1.053118642629797e-49,1.4477525577858754e-41,3.3407436978938097e-34,1.2939696040587033e-27,8.412770006527318e-22,9.181199989094544e-17,1.6821846395778396e-12,5.179264498460368e-09,2.694564639660213e-06,0.000244535211271986,0.004517779366983922,0.02501467504879325,0.04438966957280604,0.017040297010403698,0.0011586355396248182,1.7752096435632197e-05,0.0003814973013535482,0.005549785451190046,0.01382821612700825,0.013943489607935363,0.0839179737393733,0.2183783581773079,0.16884959785573145,0.05784579121041084,0.04235075739179921,0.09144916800276008,0.06254381662026211,0.03146159373193639,0.01237104517803037,0.008792966898410208,0.003613041000761382,0.0047489839577992765,0.0250173437633581,0.0443896747436619,0.0170402970093106,0.0011586270077126328],
				                    data: weekend_list,
									"showSymbol": false,
				                    lineStyle:{
				                        color:'#655432',
				
				                    },
				                    hoverAnimation:true,
				
				
				                    itmeStyle:{
				                        color:"#fe3332"
				                    },
				                    smooth:true,
				                    areaStyle: {
				                opacity: 1,
				
				"color": new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#2242c7'}, {offset: 1, color: '#87CEFA'}], false)
				            },
				                }
				            ]
				

				
				            },
				
				
				
				        this.sleepchartopt={
				            tooltip : {
								 valueFormatter: value => value=="null" ? "": Math.floor(value) + ':' + (((value-Math.floor(value))*60).toFixed(0)>10 ? ((value-Math.floor(value))*60).toFixed(0): "0"+((value-Math.floor(value))*60).toFixed(0)),
				            borderWidth: 0,
				                trigger: 'axis',
				
				            },
				            legend: {
				                data: ['Bed time', "Wake up time"],
				                selected:{'Bed time':true}
				            },
				            xAxis:
				            {
				                type : 'category',
				               data : sleep_x,
				
				                axisTick: {
				                    alignWithLabel: true,
				
				                    }
				            }
				             ,
				            yAxis:
				                {
				                    type:'value',
				                    max:24,
				                    min:0,
				                    splitLine: {
				                    show: true,
				
				                    lineStyle:{
				                        show: true,
				                        width: 1,
				                        opacity:1,
				                        curveness: 0,
				                        type: "solid"
				                        }
				                     }
				
				                }
				            ,
				
				            series:[
				            {
				                    name:'Bed time',
				                    connectNulls: true,
				                    type:'line',
				                    data:sleep_start_time,
				                    lineStyle:{
				                        color:'#655432',
				
				                    },
				
				
				                    itmeStyle:{
				                        color:"#fe3332"
				                    },
				                    hoverAnimation: true,
				                    zlevel: 0,
				                    z: 0,
				
				                    areaStyle: {
				                        opacity: 0
				
				                    },
				            },
							{
							        name:'Wake up time',
							        connectNulls: true,
							        type:'line',
							        data:sleep_end_time,
							        lineStyle:{
							            color:'#1890FF',
											
							        },
											
											
							        itmeStyle:{
							            color:"#fe3332"
							        },
							        hoverAnimation: true,
							        zlevel: 0,
							        z: 0,
											
							        areaStyle: {
							            opacity: 0
											
							        },
							}
				
				
				
				            ]
				
				        };
						
						
						this.sleepDisData={
				            tooltip : {
											            borderWidth: 0,
				                trigger: 'axis',
				
				            },
				            legend: {
				                data: ['Bed time', "Wake up time"],
				                selected:{'Bed time':true}
				            },
				            xAxis:
				            {
				                type : 'category',
				               data : sleep_dis_x,
				
				                axisTick: {
				                    alignWithLabel: true,
				
				                    }
				            }
				             ,
				            yAxis:
				                {
				                    type:'value',

				                    splitLine: {
				                    show: true,
				
				                    lineStyle:{
				                        show: true,
				                        width: 1,
				                        opacity:1,
				                        curveness: 0,
				                        type: "solid"
				                        }
				                     }
				
				                }
				            ,
				
				            series:[
				            {
				                    name:'Bed time',
				                    connectNulls: true,
				                    type:'bar',
				                    data:sleep_dis_y1,
				                    lineStyle:{
				                        color:'#655432',
				
				                    },
				
				
				                    itmeStyle:{
				                        color:"#fe3332"
				                    },
				                    hoverAnimation: true,
				                    zlevel: 0,
				                    z: 0,
				
				                    areaStyle: {
				                        opacity: 0
				
				                    },
				            },
							{
							        name:'Wake up time',
							        connectNulls: true,
							        type:'bar',
							        data:sleep_dis_y2,
							        lineStyle:{
							            color:'#1890FF',
											
							        },
											
											
							        itmeStyle:{
							            color:"#fe3332"
							        },
							        hoverAnimation: true,
							        zlevel: 0,
							        z: 0,
											
							        areaStyle: {
							            opacity: 0
											
							        },
							}
				
				
				
				            ]
				
				        };
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				//         let sleep_res = {
				//             categories: test_year,
				//             series: [
				//               {
				//                 name: "成交量A",
				//                 data: [35,8,25,37,4,20]
				//               },
				//               {
				//                 name: "成交量B",
				//                 data: [70,40,65,100,44,68]
				//               },
				//               {
				//                 name: "成交量C",
				//                 data: [100,80,95,150,112,132]
				//               }
				//             ]
				//           };
				//         this.sleepData = JSON.parse(JSON.stringify(sleep_res));
				
				
				
				
				
				
				
				
				
				
				
				if (color==1){
					this.diet_flag = "Not eating time"
					this.plot_color =  "#f04864"
					
				}else{
					this.diet_flag = "Eating time"
					this.plot_color =  "#4cc256"
				}
				var that = this;
				setTimeout(() => {
					that.chartsDataGauge1 = {
						"categories": [{
							"value": 0.2,
							"color": "#1890ff"
						}, {
							"value": 0.8,
							"color": "#2fc25b"
						}, {
							"value": 1,
							"color": "#f04864"
						}],
						"series": [{
							"name": "完成率",
							"data": percentage
						}]

					};
					
					
					that.opts= {

					
					color: ["#1890FF"],
					padding: undefined,
					title: {
						name: weight,
						fontSize: 20,
						color: "#000000",
						offsetY: 0
					},
					subtitle: {
						name: "\n\nCurrent weight",
						fontSize: 12,
						color: "#1890ff",
						offsetY: 0
					},
					extra: {
						gauge: {
							type: "progress",
							width: 10,
							labelColor: "#666666",
							startAngle: 0.75,
							endAngle: 0.25,
							startNumber: 76,
							endNumber: 90,
							labelFormat: "",
							splitLine: {
								fixRadius: -10,
								splitNumber: 4,
								width: 15,
								color: "#FFFFFF",
								childNumber: 5,
								childWidth: 12
							},
							pointer: {
								width: 24,
								color: "auto"
							}
						}
					}
				};
					
					let res = {
						categories: new_data1,
						series: [{
							name: "series",
							data: new_data
						}]
					};
					this.barChartData = JSON.parse(JSON.stringify(res));

					//模拟服务器返回数据，如果数据格式和标准格式不同，需自行按下面的格式拼接
					let arcres = {
						series: [{
							name: "Percentage",
							color: this.plot_color,
							data: this.needTime/time_percentage
						}]
					};
					this.arcChartData = JSON.parse(JSON.stringify(arcres));
				})
			},
			getDate1(e){
				this.time1=e.detail.value
			},
			getDate2(e){
				this.time2=e.detail.value
			}
		},
	}
</script>

<style>
	.aa5{
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-items: center;
		
	}
	.top {
		display: flex;
		flex-direction: row;
		justify-content: space-around;
		border: 6rpx solid #46a0e5;
		margin: 10rpx;
		border-radius: 10rpx;
	}

	.c1_left {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	.c1_left text:nth-child(odd) {
		font-size: 28rpx;
		color: #999999;
	}

	.c1_left text:nth-child(even) {
		font-size: 34rpx;
		color: #000000;
		font-weight: 800;
	}

	.charts-box {
		width: 400rpx;
		height: 400rpx;
	}

	.charts-box2 {
		height: 500rpx;
		border: 6rpx solid #b8aee8;
		margin: 10rpx;
		border-radius: 10rpx;
	}

	.asaa {
		display: flex;
		flex-direction: row;
		justify-content: space-around;
		align-items: center;

		margin: 10rpx;
		border-radius: 10rpx;
		height: 280rpx;
	}
	.test_top {
		display: flex;
		flex-direction: column;
		justify-content: space-around;
		align-items: center;
		border: 6rpx solid #4cc256;
		margin: 10rpx;
		border-radius: 10rpx;
		height: 280rpx;
	}

	.charts-box3 {
		width: 250rpx;
		height: 250rpx;
		position: absolute;
		left: 0;
		margin-left: 20rpx;  
	}
	.zzA{
		position: relative;
	}
	.charts-box3 view{
		position: absolute;
		width: 250rpx;
		height: 250rpx; 
		text-align: center;  
		line-height: 340rpx;
		font-size: 32rpx;
		font-weight: 800;
	}
	.arc_right {
		margin-left: 140rpx;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.arc_right image {
		width: 20rpx;
		height: 20rpx;
	}

	.aa1 {
		font-size: 38rpx;
		font-weight: 800;
		color: #000000;
	}

	.aa2 {
		font-size: 28rpx;
		color: #999999;
		margin-top: 10rpx;
	}

	.aa3 {
		font-size: 48rpx;
		color: #4cc256;
		margin-bottom: 10rpx;
	}

	.aa4 {
		font-size: 28rpx;
		margin-right: 10rpx;
		color: #aaaaaa;
	}
	
	
/* 	.picker-view {
		width: 750rpx;
		height: 600rpx;
		margin-top: 20rpx;
	} */
 
/* 	.item {
		line-height: 100rpx;
		text-align: center;
		} */
		
		
/* 	.time_title {
		display: flex;
		justify-content: space-between;
		padding: 30rpx;
		font-size: 32rpx;
	} */
 
/* 	.box {
		position: absolute;
		left: 360rpx;
		top: 280rpx;
		} */


</style>