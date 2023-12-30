<template>

  <div class="dot-chart2">

    <div id="main" ref="main">



    </div>

  </div>

</template>

<script>

/* 引入echarts组件 */

import * as echarts from 'echarts';
import ecStat from 'echarts-stat';
export default {
  name:"DotChart2",

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
            [10,129],[18,106],[4,102],[11,132],[15,126],[24,99],[9,138],[14,121],[24,95],[14,124],[14,117],[1,91],[12,119],[14,124],[1,90],[7,128],[14,108],[6,97],[10,139],[14,117],[22,103],[11,135],[14,106],[24,103],[8,117],[20,126],[1,100],[9,125],[16,114],[4,98]
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
        text: 'User2',

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
          datasetIndex: 0,
    color: "orange"
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
