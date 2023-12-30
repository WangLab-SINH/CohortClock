<template>

  <div class="dot-chart">

    <div id="main" ref="main">



    </div>

  </div>

</template>

<script>

/* 引入echarts组件 */

import * as echarts from 'echarts';
import { Getdaytimedatalist } from "@/api/photoPage";

export default {
  name:"DistributionPlot",

  data () {
    return {
      x_axis: [],
      y_axis: []
    }

  },



  mounted(){

    // 基于准备好的dom，初始化echarts实例

    /* var myChart = echarts.init(document.getElementById('main')); */



  },

  created() {
    this.fetchData()
  },

  methods: {
    fetchData() {


      Getdaytimedatalist().then(res => {
        // console.log('获取列表：', JSON.stringify(rest.data.list))

        this.x_axis = res.data.x_axis
        this.y_axis = res.data.y_axis

        this.initEcharts(this.x_axis, this.y_axis)
      })

    },

    initEcharts(x_axis, y_axis) {
      var myChart = echarts.init(this.$refs.main);

      // 绘制图表

      myChart.setOption({
        title: {
          text: 'Daily uploads'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            label: {
              backgroundColor: '#6a7985'
            }
          }
        },

        toolbox: {

        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            boundaryGap: false,
            data: x_axis
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            type: 'line',
            stack: 'Total',
            areaStyle: {},
            emphasis: {
              focus: 'series'
            },
            data: y_axis
          },

        ]
      });

      window.PieChart = myChart
    }



  }

}

</script>

<style scoped lang="scss">

#main{

  height: 300px;

}

</style>
