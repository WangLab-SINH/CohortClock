<template>

  <div class="dot-chart">

    <div id="main" ref="main">



    </div>

  </div>

</template>

<script>

/* 引入echarts组件 */

import * as echarts from 'echarts';
import { Getdiettypedatalist } from "@/api/photoPage";

export default {
  name:"PiePlot",

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


      Getdiettypedatalist().then(res => {
        // console.log('获取列表：', JSON.stringify(rest.data.list))

        this.x_axis = res.data.x_axis
        console.log(this.x_axis)

        this.initEcharts(this.x_axis)
      })

    },

    initEcharts(x_axis) {
      var myChart = echarts.init(this.$refs.main);

      // 绘制图表

      myChart.setOption({
        title: {
          text: 'Number of diet types'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '5%',
          left: 'center'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 40,
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data:
              x_axis,

          }
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



<!--<template>-->

<!--  <div class="pie-chart">-->

<!--    <div id="main" ref="main">-->



<!--    </div>-->

<!--  </div>-->

<!--</template>-->

<!--<script>-->

<!--/* 引入echarts组件 */-->

<!--import * as echarts from 'echarts';-->

<!--export default {-->

<!--  name:"PieChart",-->

<!--  mounted(){-->

<!--    // 基于准备好的dom，初始化echarts实例-->

<!--    /* var myChart = echarts.init(document.getElementById('main')); */-->

<!--    var myChart = echarts.init(this.$refs.main);-->

<!--    // 绘制图表-->

<!--    myChart.setOption({-->
<!--      tooltip: {-->
<!--        trigger: 'item'-->
<!--      },-->
<!--      legend: {-->
<!--        top: '5%',-->
<!--        left: 'center'-->
<!--      },-->
<!--      series: [-->
<!--        {-->
<!--          name: 'Access From',-->
<!--          type: 'pie',-->
<!--          radius: ['40%', '70%'],-->
<!--          avoidLabelOverlap: false,-->
<!--          label: {-->
<!--            show: false,-->
<!--            position: 'center'-->
<!--          },-->
<!--          emphasis: {-->
<!--            label: {-->
<!--              show: true,-->
<!--              fontSize: 40,-->
<!--              fontWeight: 'bold'-->
<!--            }-->
<!--          },-->
<!--          labelLine: {-->
<!--            show: false-->
<!--          },-->
<!--          data: [-->
<!--            { value: 1048, name: 'Search Engine' },-->
<!--            { value: 735, name: 'Direct' },-->
<!--            { value: 580, name: 'Email' },-->
<!--            { value: 484, name: 'Union Ads' },-->
<!--            { value: 300, name: 'Video Ads' }-->
<!--          ]-->
<!--        }-->
<!--      ]-->

<!--    });-->

<!--    window.PieChart = myChart-->

<!--  }-->

<!--}-->

<!--</script>-->

<!--<style scoped lang="scss">-->

<!--#main{-->

<!--  height: 300px;-->

<!--}-->

<!--</style>-->
