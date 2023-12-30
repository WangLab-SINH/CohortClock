<template>
  <div class="index-container">
    <el-row :gutter="20">
      <el-col
        v-for="(item, index) in iconList"
        :key="index"
        :lg="8"
        :md="3"
        :sm="6"
        :xl="6"
        :xs="12"
      >
          <el-card class="icon-panel" shadow="never">
            <el-image style="width:35px;height: 35px;"
                      :src="require('@/assets/image/'+item.icon)" />

            <p>{{ item.number }}</p>
            <p>{{ item.title }}</p>
          </el-card>
      </el-col>
    </el-row>
<!--    <el-row :gutter="20">-->
<!--      <el-col-->
<!--        v-for="(item, index) in iconList1"-->
<!--        :key="index"-->
<!--        :lg="8"-->
<!--        :md="3"-->
<!--        :sm="6"-->
<!--        :xl="6"-->
<!--        :xs="12"-->
<!--      >-->
<!--        <router-link target="_blank" :to="item.link">-->
<!--          <el-card class="icon-panel" :style="{ background: item.color }">-->
<!--            <vab-icon :icon="['fas', item.icon]" style="color: #ffffff" />-->
<!--&lt;!&ndash;            <p>{{ item.number }}</p>&ndash;&gt;-->
<!--&lt;!&ndash;            <p>{{ item.title }}</p>&ndash;&gt;-->
<!--          </el-card>-->
<!--&lt;!&ndash;          <el-row :gutter="20">&ndash;&gt;-->
<!--&lt;!&ndash;            <el-col>&ndash;&gt;-->
<!--&lt;!&ndash;              <p>{{ item.number }}</p>&ndash;&gt;-->
<!--&lt;!&ndash;              <p>{{ item.title }}</p>&ndash;&gt;-->
<!--&lt;!&ndash;            </el-col>&ndash;&gt;-->
<!--&lt;!&ndash;            <el-col>&ndash;&gt;-->
<!--&lt;!&ndash;              <p>{{ item.number }}</p>&ndash;&gt;-->
<!--&lt;!&ndash;              <p>{{ item.title }}</p>&ndash;&gt;-->
<!--&lt;!&ndash;            </el-col>&ndash;&gt;-->
<!--&lt;!&ndash;          </el-row>&ndash;&gt;-->
<!--        </router-link>-->
<!--      </el-col>-->
<!--    </el-row>-->
    <el-row :gutter="20">
      <el-col :lg="16" :md="12" :sm="24" :xl="12" :xs="24">
        <el-card shadow="never">
          <div slot="header">

          </div>
<!--          <vab-chart autoresize :option="fwl" />-->
          <DistributionPlot></DistributionPlot>
          <div class="bottom">

          </div>
        </el-card>
      </el-col>
      <el-col :lg="16" :md="12" :sm="24" :xl="12" :xs="24">
        <el-card shadow="never">
          <div slot="header">

          </div>
          <BarPlot></BarPlot>
          <div class="bottom">

          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-button class="export-it" type="primary" @click="exportFile()">Download data</el-button>
      <el-table
        ref="tableSort"
        v-loading="listLoading"
        :data="list"
        :element-loading-text="elementLoadingText"
        :height="height"
        @sort-change="tableSortChange"
      >
        <!--        <el-table-column show-overflow-tooltip type="selection" width="55" />-->
        <el-table-column label="ID" show-overflow-tooltip width="95">
          <template #default="scope">
            {{ scope.$index + 1 + (queryForm.pageNo - 1) * queryForm.pageSize }}
          </template>
        </el-table-column>
        <el-table-column label="User Name" prop="user_name" show-overflow-tooltip />
        <el-table-column label="Upload Timestamp" prop="upload_time" show-overflow-tooltip />
        <el-table-column label="Deit Time" prop="user_time" show-overflow-tooltip />
<!--        <el-table-column label="Image" show-overflow-tooltip>-->
<!--          <template #default="{ row }">-->
<!--            <el-image-->
<!--              v-if="imgShow"-->
<!--              :preview-src-list="imageList"-->
<!--              :src="row.img"-->
<!--            />-->
<!--          </template>-->
<!--        </el-table-column>-->
        <el-table-column label="Image">
          <template slot-scope="scope">
<!--            <el-image style="width:20px;height: 20px;" :preview-src-list="[scope.row.photo_image]"-->
<!--                      :src="scope.row.photo_image" />-->
<!--            <el-image style="width:20px;height: 20px;" :preview-src-list="[require('E:/temp_food.jpeg')]"-->
<!--                      :src="require('E:/temp_food.jpeg')" />-->
<!--            <el-image-->
<!--              class="table-td-thumb"-->
<!--              :src="require('../../assets/image/'+scope.row.photo_image+'')"-->
<!--            ></el-image>-->
<!--                <el-image style="width:20px;height: 20px;" :preview-src-list="[require('../../assets/image/'+scope.row.photo_name+'')]"-->
<!--                          :src="require('../../assets/image/'+scope.row.photo_name+'')" />-->

            <el-image style="width:20px;height: 20px;" :preview-src-list="[`http://39.100.73.118/deeplearning_photo/uploads/${scope.row.photo_name}`]"
                      :src="`http://39.100.73.118/deeplearning_photo/uploads/${scope.row.photo_name}`" />



<!--            <el-image-->
<!--              class="table-td-thumb"-->
<!--              :src="imgformat(scope.row)"-->
<!--            ></el-image>-->

          </template>
        </el-table-column>
        <el-table-column
          label="Food or Not"
          prop="photo_type"
          show-overflow-tooltip
          sortable
        />
        <el-table-column label="Type of Food" prop="photo_kind" show-overflow-tooltip />
        <el-table-column label="Food Calorie" prop="photo_cal" show-overflow-tooltip />
<!--        <el-table-column label="User anno of food type" prop="photo_type_anno" show-overflow-tooltip />-->
<!--        <el-table-column label="User anno of food calorie" prop="photo_cal_anno" show-overflow-tooltip />-->

      </el-table>
      <el-pagination
        :total="pagination.total"
        :background="background"
        :current-page="queryForm.pageNo"
        :layout="layout"
        :page-size="queryForm.pageSize"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"
      />
    </el-row>

  </div>
</template>

<script>
import VabChart from '@/plugins/echarts'
import DistributionPlot from '@/views/index/DistributionPlot.vue'
import BarPlot from '@/views/index/BarPlot.vue'
import elTableExport from "el-table-export";
import { PhotoInfoData } from '@/api/photoPage'
import { dependencies, devDependencies } from '../../../package.json'
// import { getList } from '@/api/changeLog'
import { getNoticeList } from '@/api/notice'
import { doDelete, getList } from '@/api/table'

export default {
  name: 'Index',
  components: {
    VabChart,
    DistributionPlot,
    BarPlot,
  },
  data() {
    return {
      exportType: "csv",
      imgShow: true,
      list: [],
      imageList: [],
      listLoading: true,
      layout: 'total, sizes, prev, pager, next, jumper',
      total: 0,
      background: true,
      selectRows: '',
      elementLoadingText: '正在加载...',
      pagination: {
        total: 0,
        page: 1,
        limit: 10
      },
      queryForm: {
        pageNo: 1,
        pageSize: 20,
        title: '',
      },

      timer: 0,
      updateTime: process.env.VUE_APP_UPDATE_TIME,
      nodeEnv: process.env.NODE_ENV,
      dependencies: dependencies,
      devDependencies: devDependencies,
      config1: {
        startVal: 0,
        endVal: this.$baseLodash.random(20000, 60000),
        decimals: 0,
        prefix: '',
        suffix: '',
        separator: ',',
        duration: 8000,
      },
      config2: {
        startVal: 0,
        endVal: this.$baseLodash.random(1000, 20000),
        decimals: 0,
        prefix: '',
        suffix: '',
        separator: ',',
        duration: 8000,
      },
      config3: {
        startVal: 0,
        endVal: this.$baseLodash.random(1000, 20000),
        decimals: 0,
        prefix: '',
        suffix: '',
        separator: ',',
        duration: 8000,
      },

      //访问量
      fwl: {
        color: [
          '#1890FF',
          '#36CBCB',
          '#4ECB73',
          '#FBD437',
          '#F2637B',
          '#975FE5',
        ],
        backgroundColor: 'rgba(252,252,252,0)',
        grid: {
          top: '4%',
          left: '2%',
          right: '4%',
          bottom: '0%',
          containLabel: true,
        },
        xAxis: [
          {
            type: 'category',
            boundaryGap: false,
            data: [],
            axisTick: {
              alignWithLabel: true,
            },
          },
        ],
        yAxis: [
          {
            type: 'value',
          },
        ],
        series: [
          {
            name: '访问量',
            type: 'line',
            data: [],
            smooth: true,
            areaStyle: {},
          },
        ],
      },
      //授权数
      sqs: {
        color: [
          '#1890FF',
          '#36CBCB',
          '#4ECB73',
          '#FBD437',
          '#F2637B',
          '#975FE5',
        ],
        backgroundColor: 'rgba(252,252,252,0)',
        grid: {
          top: '4%',
          left: '2%',
          right: '4%',
          bottom: '0%',
          containLabel: true,
        },
        xAxis: [
          {
            type: 'category',
            /*boundaryGap: false,*/
            data: ['0时', '4时', '8时', '12时', '16时', '20时', '24时'],
            axisTick: {
              alignWithLabel: true,
            },
          },
        ],
        yAxis: [
          {
            type: 'value',
          },
        ],
        series: [
          {
            name: '授权数',
            type: 'bar',
            barWidth: '60%',
            data: [10, 52, 20, 33, 39, 33, 22],
          },
        ],
      },
      //词云
      cy: {
        grid: {
          top: '4%',
          left: '2%',
          right: '4%',
          bottom: '0%',
        },
        series: [
          {
            type: 'wordCloud',
            gridSize: 15,
            sizeRange: [12, 40],
            rotationRange: [0, 0],
            width: '100%',
            height: '100%',
            textStyle: {
              normal: {
                color() {
                  const arr = [
                    '#5470c6',
                    '#91cc75',
                    '#fac858',
                    '#ee6666',
                    '#73c0de',
                    '#975FE5',
                  ]
                  let index = Math.floor(Math.random() * arr.length)
                  return arr[index]
                },
              },
            },
            data: [
              {
                name: 'vue-admin-better',
                value: 15000,
              },
              {
                name: 'element',
                value: 10081,
              },
              {
                name: 'beautiful',
                value: 9386,
              },

              {
                name: 'vue',
                value: 6500,
              },
              {
                name: 'chuzhixin',
                value: 6000,
              },
              {
                name: 'good',
                value: 4500,
              },
              {
                name: 'success',
                value: 3800,
              },
              {
                name: 'never',
                value: 3000,
              },
              {
                name: 'boy',
                value: 2500,
              },
              {
                name: 'girl',
                value: 2300,
              },
              {
                name: 'github',
                value: 2000,
              },
              {
                name: 'hbuilder',
                value: 1900,
              },
              {
                name: 'dcloud',
                value: 1800,
              },
              {
                name: 'china',
                value: 1700,
              },
              {
                name: '1204505056',
                value: 1600,
              },
              {
                name: '972435319',
                value: 1500,
              },
              {
                name: 'young',
                value: 1200,
              },
              {
                name: 'old',
                value: 1100,
              },
              {
                name: 'vuex',
                value: 900,
              },
              {
                name: 'router',
                value: 800,
              },
              {
                name: 'money',
                value: 700,
              },
              {
                name: 'qingdao',
                value: 800,
              },
              {
                name: 'yantai',
                value: 9000,
              },
              {
                name: 'author is very cool',
                value: 9200,
              },
            ],
          },
        ],
      },

      //更新日志
      reverse: true,
      activities: [],
      noticeList: [],
      //其他信息
      userAgent: navigator.userAgent,
      //卡片图标
      iconList: [
        {
          icon: 'people_new1.png',
          title: 'Number of Users',
          link: '',
          color: '#ffc069',
          number: '2500',
        },
        {
          icon: 'eating.png',
          title: 'Number of Meal Time',
          link: '',
          color: '#5cdbd3',
          number: '123',
        },
        {
          icon: 'body_index.png',
          title: 'Number of Body Metrics',
          link: '',
          color: '#b37feb',
          number: '1,805',
        },
        {
          icon: 'days.png',
          title: 'Number of Days',
          link: '',
          color: '#69c0ff',
          number: '123',
        },
      ],

      iconList1: [
        {
          icon: 'bullhorn',
          title: '视频播放器',
          link: '/vab/player',
          color: '#ff85c0',
          number: '2500',
        },
        {
          icon: 'gift',
          title: '表格',
          link: '/vab/table/comprehensiveTable',
          color: '#ffd666',
          number: '123',
        },
        {
          icon: 'balance-scale-left',
          title: '源码',
          link: 'https://github.com/chuzhixin/vue-admin-better',
          color: '#ff9c6e',
          number: '1,805',
        },
        {
          icon: 'coffee',
          title: '书籍',
          link: '',
          color: '#95de64',
          number: '123',
        },
      ],
    }
  },
  computed: {
    height() {
      return this.$baseTableHeight()
    },
  },

  created() {
    this.fetchData()
  },
  beforeDestroy() {
    clearInterval(this.timer)
  },
  mounted() {
    let base = +new Date(2020, 1, 1)
    let oneDay = 24 * 3600 * 1000
    let date = []

    let data = [Math.random() * 1500]
    let now = new Date(base)

    const addData = (shift) => {
      now = [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/')
      date.push(now)
      data.push(this.$baseLodash.random(20000, 60000))

      if (shift) {
        date.shift()
        data.shift()
      }

      now = new Date(+new Date(now) + oneDay)
    }

    for (let i = 1; i < 6; i++) {
      addData()
    }
    addData(true)
    this.fwl.xAxis[0].data = date
    this.fwl.series[0].data = data
    this.timer = setInterval(() => {
      addData(true)
      this.fwl.xAxis[0].data = date
      this.fwl.series[0].data = data
    }, 3000)
  },
  methods: {
    handleClick(e) {
      this.$baseMessage(`点击了${e.name},这里可以写跳转`)
    },
    handleZrClick() {},
    handleChangeTheme() {
      this.$baseEventBus.$emit('theme')
    },
    //     async fetchData() {
    //       const { data } = await getList()
    //       data.map((item, index) => {
    //         if (index === data.length - 1) {
    //           item.color = '#0bbd87'
    //         }
    //       })
    //       this.activities = data
    //       const res = await getNoticeList()
    //       this.noticeList = res.data
    //       /* getRepos({
    //   token: "1061286824f978ea3cf98b7b8ea26fe27ba7cea1",
    // }).then((res) => {
    //   const per_page = Math.ceil(res.data.stargazers_count / 100);
    //   alert(per_page);
    //   getStargazers({
    //     token: "1061286824f978ea3cf98b7b8ea26fe27ba7cea1",
    //     page: 1,
    //     per_page: res.per_page,
    //   }).then((res) => {
    //     alert(JSON.stringify(res));
    //   });
    // }); */
    //     },
    tableSortChange() {
      const imageList = []
      this.$refs.tableSort.tableData.forEach((item, index) => {
        imageList.push(item.img)
      })
      this.imageList = imageList
    },

    exportFile() {
      elTableExport(this.$refs.tableSort, {
        fileName: "Export_file",
        type: this.exportType,
        useFormatter: true,
      })
        .then(() => {
          console.info("ok");
        })
        .catch((err) => {
          console.info(err);
        });
    },

    setSelectRows(val) {
      this.selectRows = val
    },
    handleAdd() {
      this.$refs['edit'].showEdit()
    },
    handleEdit(row) {
      this.$refs['edit'].showEdit(row)
    },
    handleDelete(row) {
      if (row.id) {
        this.$baseConfirm('你确定要删除当前项吗', null, async () => {
          const { msg } = await doDelete({ ids: row.id })
          this.$baseMessage(msg, 'success')
          this.fetchData()
        })
      } else {
        if (this.selectRows.length > 0) {
          const ids = this.selectRows.map((item) => item.id).join()
          this.$baseConfirm('你确定要删除选中项吗', null, async () => {
            const { msg } = await doDelete({ ids: ids })
            this.$baseMessage(msg, 'success')
            this.fetchData()
          })
        } else {
          this.$baseMessage('未选中任何行', 'error')
          return false
        }
      }
    },
    handleSizeChange(val) {
      this.queryForm.pageSize = val
      this.fetchData()
    },
    handleCurrentChange(val) {
      this.queryForm.pageNo = val
      this.fetchData()
    },
    handleQuery() {
      this.queryForm.pageNo = 1
      this.fetchData()
    },
    async fetchData() {
      this.listLoading = true
      // const { data, totalCount } = await getList(this.queryForm)
      // this.list = data
      // const imageList = []
      // data.forEach((item, index) => {
      //   imageList.push(item.img)
      // })
      // this.imageList = imageList
      // this.total = totalCount
      // setTimeout(() => {
      //   this.listLoading = false
      // }, 500)
      // PhotoInfoData(this.queryForm.pageNo, this.queryForm.limit).then(res => {
      //   console.log(res.data)
      //   // console.log(JSON.parse(res.data.species[0][0].mvpData))
      //   //console.log(typeof res.data.species[0][0].mvpData)
      //   this.data = res.data.photo[0]
      //   this.total = res.data.photo[1]
      //   this.listLoading = false
      // })
      PhotoInfoData(this.queryForm.pageNo, this.queryForm.pageSize).then(res => {
        // console.log('获取列表：', JSON.stringify(rest.data.list))

          this.list = res.data.list
          this.pagination.total = res.data.total
          this.listLoading = false
        console.log(this.list)
      })

    },
    testMessage() {
      this.$baseMessage('test1', 'success')
    },
    testALert() {
      this.$baseAlert('11')
      this.$baseAlert('11', '自定义标题', () => {
        /* 可以写回调; */
      })
      this.$baseAlert('11', null, () => {
        /* 可以写回调; */
      })
    },
    testConfirm() {
      this.$baseConfirm(
        '你确定要执行该操作?',
        null,
        () => {
          /* 可以写回调; */
        },
        () => {
          /* 可以写回调; */
        }
      )
    },
    testNotify() {
      this.$baseNotify('测试消息提示', 'test', 'success', 'bottom-right')
    },
    imgformat(row) {
      let lj = row.photo_image + '';
      let img = require("../../assets/image/" + lj);
      // let img =require('@/assets/images/MilkTea/' + lj );
      // let img2 =require('@/assets/images/MilkTea/mxlc.jpg'  );

      // // this.$message(row.mtImg);

      return img;
    },





  },
}
</script>
<style lang="scss" scoped>
.index-container {
  padding: 0 !important;
  margin: 0 !important;
  background: #f5f7f8 !important;

  ::v-deep {
    .el-alert {
      padding: $base-padding;

      &--info.is-light {
        min-height: 82px;
        padding: $base-padding;
        margin-bottom: 15px;
        color: #909399;
        background-color: $base-color-white;
        border: 1px solid #ebeef5;
      }
    }

    .el-card__body {
      .echarts {
        width: 100%;
        height: 115px;
      }
    }
  }

  .card {
    ::v-deep {
      .el-card__body {
        .echarts {
          width: 100%;
          height: 305px;
        }
      }
    }
  }

  .bottom {
    padding-top: 20px;
    margin-top: 5px;
    color: #595959;
    text-align: left;
    border-top: 1px solid $base-border-color;
  }

  .table {
    width: 100%;
    color: #666;
    border-collapse: collapse;
    background-color: #fff;

    td {
      position: relative;
      min-height: 20px;
      padding: 9px 15px;
      font-size: 14px;
      line-height: 20px;
      border: 1px solid #e6e6e6;

      &:nth-child(odd) {
        width: 20%;
        text-align: right;
        background-color: #f7f7f7;
      }
    }
  }

  .icon-panel {
    height: 117px;
    text-align: center;
    cursor: pointer;

    svg {
      font-size: 40px;
    }

    p {
      margin-top: 10px;
    }
  }

  .bottom-btn {
    button {
      margin: 5px 10px 15px 0;
    }
  }
}
</style>
