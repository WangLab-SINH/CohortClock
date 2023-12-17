<template>

  <div class="dot-chart">

    <div id="main" ref="main">



    </div>

  </div>

</template>

<script>

/* 引入echarts组件 */

import * as echarts from 'echarts';
import ecStat from 'echarts-stat';
export default {
  name:"DotChart",

  data () {
    return {

      dataAll : [
        [
          [10.0, 8.04],
          [8.0, 6.95],
          [13.0, 7.58],
          [9.0, 8.81],
          [11.0, 8.33],
          [14.0, 9.96],
          [6.0, 7.24],
          [4.0, 4.26],
          [12.0, 10.84],
          [7.0, 4.82],
          [5.0, 5.68]
        ],
        [
          [10.0, 9.14],
          [8.0, 8.14],
          [13.0, 8.74],
          [9.0, 8.77],
          [11.0, 9.26],
          [14.0, 8.1],
          [6.0, 6.13],
          [4.0, 3.1],
          [12.0, 9.13],
          [7.0, 7.26],
          [5.0, 4.74]
        ],
        [
          [10.0, 7.46],
          [8.0, 6.77],
          [13.0, 12.74],
          [9.0, 7.11],
          [11.0, 7.81],
          [14.0, 8.84],
          [6.0, 6.08],
          [4.0, 5.39],
          [12.0, 8.15],
          [7.0, 6.42],
          [5.0, 5.73]
        ],
        [
          [8.0, 6.58],
          [8.0, 5.76],
          [8.0, 7.71],
          [8.0, 8.84],
          [8.0, 8.47],
          [8.0, 7.04],
          [8.0, 5.25],
          [19.0, 12.5],
          [8.0, 5.56],
          [8.0, 7.91],
          [8.0, 6.89]
        ]
      ],
      markLineOpt : {
        animation: false,
        label: {
          formatter: 'y = 0.5 * x + 3',
          align: 'right'
        },
        lineStyle: {
          type: 'solid'
        },
        tooltip: {
          formatter: 'y = 0.5 * x + 3'
        },
        data: [
          [
            {
              coord: [0, 3],
              symbol: 'none'
            },
            {
              coord: [20, 13],
              symbol: 'none'
            }
          ]
        ]
      }
    }

  },



  mounted(){

    // 基于准备好的dom，初始化echarts实例

    /* var myChart = echarts.init(document.getElementById('main')); */

    var myChart = echarts.init(this.$refs.main);

    // 绘制图表
    echarts.registerTransform(ecStat.transform.regression);
    myChart.setOption({
      dataset: [
        {
          source: [
            [11,133],[21,121],[4,109],[8,137],[16,130],[24,98],[10,129],[22,122],[22,109],[6,121],[20,120],[5,106],[9,119],[20,102],[3,110],[6,137],[20,127],[3,98],[6,124],[19,111],[4,104],[12,117],[17,116],[4,94],[9,119],[16,119],[1,97],[6,123],[18,128],[1,107]
          ]
        },
        {
          transform: {
            type: 'ecStat:regression',
            config: {
              method: 'polynomial', order: 3
              // 'end' by default
              // formulaOn: 'start'
            }
          }
        }
      ],
      title: {
        text: 'User1',

        left: 'center'
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'cross'
        }
      },
      xAxis: {
        splitLine: {
          lineStyle: {
            type: 'dashed'
          }
        }
      },
      yAxis: {
        splitLine: {
          lineStyle: {
            type: 'dashed'
          }
        }
      },
      series: [
        {
          name: 'scatter',
          type: 'scatter',
          datasetIndex: 0
        },
        {
          name: 'line',
          type: 'line',
          smooth: true,
          datasetIndex: 1,
          symbolSize: 0.1,
          symbol: 'circle',
          label: { show: true, fontSize: 16 },
          labelLayout: { dx: -20 },
          encode: { label: 2, tooltip: 1 }
        }
      ]

    });

    window.PieChart = myChart

  }

}

</script>

<style scoped lang="scss">

#main{

  height: 300px;

}

</style>
