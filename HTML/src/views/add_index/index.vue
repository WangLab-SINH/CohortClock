<template>
  <div class="index-container">


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
            {{ scope.$index + 1 }}
          </template>
        </el-table-column>
<!--        <el-table-column label="User name" prop="user_name" show-overflow-tooltip />-->
<!--        <el-table-column label="Phone id" prop="phone_id" show-overflow-tooltip />-->
<!--        <el-table-column label="Location" prop="location" show-overflow-tooltip />-->
<!--        &lt;!&ndash;        <el-table-column label="Image" show-overflow-tooltip>&ndash;&gt;-->
<!--        &lt;!&ndash;          <template #default="{ row }">&ndash;&gt;-->
<!--        &lt;!&ndash;            <el-image&ndash;&gt;-->
<!--        &lt;!&ndash;              v-if="imgShow"&ndash;&gt;-->
<!--        &lt;!&ndash;              :preview-src-list="imageList"&ndash;&gt;-->
<!--        &lt;!&ndash;              :src="row.img"&ndash;&gt;-->
<!--        &lt;!&ndash;            />&ndash;&gt;-->
<!--        &lt;!&ndash;          </template>&ndash;&gt;-->
<!--        &lt;!&ndash;        </el-table-column>&ndash;&gt;-->

<!--        <el-table-column label="Group type" prop="group_type" show-overflow-tooltip />-->
<!--        <el-table-column label="Disease" prop="disease" show-overflow-tooltip />-->
<!--        <el-table-column label="Diet type" prop="diet_type" show-overflow-tooltip />-->
<!--        <el-table-column label="Uploaded food number" prop="food_num" show-overflow-tooltip />-->
<!--        <el-table-column label="Uploaded index number" prop="body_index_num" show-overflow-tooltip />-->
<!--        <el-table-column label="Weight" prop="weight" show-overflow-tooltip />-->
<!--        <el-table-column label="Height" prop="height" show-overflow-tooltip />-->
<!--        <el-table-column label="BMI" prop="BMI" show-overflow-tooltip />-->


        <el-table-column label="Body index name" prop="index_console" show-overflow-tooltip />
        <el-table-column label="Index query" prop="index_query" show-overflow-tooltip />
        <el-table-column label="Index unit" prop="index_unit" show-overflow-tooltip />
        <!--        <el-table-column label="Image" show-overflow-tooltip>-->
        <!--          <template #default="{ row }">-->
        <!--            <el-image-->
        <!--              v-if="imgShow"-->
        <!--              :preview-src-list="imageList"-->
        <!--              :src="row.img"-->
        <!--            />-->
        <!--          </template>-->
        <!--        </el-table-column>-->

        <el-table-column label="Min number" prop="min_num" show-overflow-tooltip />
        <el-table-column label="Max number" prop="max_num" show-overflow-tooltip />
        <el-table-column label="Scale" prop="scale" show-overflow-tooltip />
        <el-table-column label="Average" prop="average" show-overflow-tooltip />
        <el-table-column label="Index name" prop="index_name" show-overflow-tooltip />
        <el-table-column label="Is circadian" prop="is_circadian" show-overflow-tooltip />



      </el-table>
      <el-pagination
        :background="background"
        :current-page="queryForm.pageNo"
        :layout="layout"
        :page-size="queryForm.pageSize"
        :total="total"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"
      />
    </el-row>

  </div>
</template>

<script>
// import VabChart from '@/plugins/echarts'
import { Getaddbpdyindexlist, GetUserinfolist, PressureInfoData } from "@/api/photoPage";
import { dependencies, devDependencies } from '../../../package.json'
// import { getList } from '@/api/changeLog'
import { getNoticeList } from '@/api/notice'
import { doDelete, getList } from '@/api/table'
import DotChart from '@/views/circadian/DotChart.vue'
import DotChart2 from '@/views/circadian/DotChart2.vue'
const Echarts = require('echarts/lib/echarts')
import 'element-ui/lib/theme-chalk/index.css'
import Multiselect from 'vue-multiselect'
import ElementUI from 'element-ui'
import elTableExport from "el-table-export";
require('echarts/lib/chart/pie')

export default {
  name: 'Index',
  components: {
    DotChart,
    DotChart2,
    ElementUI,
    Multiselect
    // VabChart,
  },
  data() {
    return {

      exportType: "csv",
      options: [{
        value: '选项1',
        label: '黄金糕'
      }, {
        value: '选项2',
        label: '双皮奶'
      }, {
        value: '选项3',
        label: '蚵仔煎'
      }, {
        value: '选项4',
        label: '龙须面'
      }, {
        value: '选项5',
        label: '北京烤鸭'
      }],
      value: "",



      checkbox_majorclass: [],
      checkbox_subclass: [],
      checkbox_species: [],
      user_id_list:[{text:'User1', value:'User1'},{text:'User2',value:'User2'},{text:'User3',value:'User3'},{text:'User4',value:'User4'},{text:'User5',value:'User5'}],
      major_class_list: [{text:'GABAergic', value:'GABAergic'},{text:'Non-neuronal',value:'Non-neuronal'},{text:'Glutamatergic',value:'Glutamatergic'},],
      subclass_list: [{text:'GABAergic', value:'GABAergic'},{text:'Non-neuronal',value:'Non-neuronal'},{text:'Glutamatergic',value:'Glutamatergic'},],
      species_list: [{text:'Human', value:'Human'},{text:'Mouse',value:'Mouse'},{text:'Monkey',value:'Monkey'}],
      chart: null,
      imgShow: true,
      list: [],
      imageList: [],
      listLoading: true,
      layout: 'total, sizes, prev, pager, next, jumper',
      total: 0,
      background: true,
      selectRows: '',
      elementLoadingText: '正在加载...',
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
    this.init()
  },
  methods: {


    handleMajorClassChange (value) {






    },
    hanleMajorClassRemove (value) {


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


    showEdit(row) {
      if (!row) {
        this.title = '添加'
      } else {
        this.title = '编辑'
        this.form = Object.assign({}, row)
      }
      this.dialogFormVisible = true
    },
    init () {
      this.chart = Echarts.init(this.$refs.chart)
      const option = {
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
            data: [
              { value: 1048, name: 'Search Engine' },
              { value: 735, name: 'Direct' },
              { value: 580, name: 'Email' },
              { value: 484, name: 'Union Ads' },
              { value: 300, name: 'Video Ads' }
            ]
          }
        ]
      }
      this.chart.setOption(option)
    },


























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
      Getaddbpdyindexlist().then(res => {
        // console.log('获取列表：', JSON.stringify(rest.data.list))

        this.list = res.data.list
        this.total = res.data.total
        this.listLoading = false
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





  },
}
</script>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
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
.speciesPage {
  max-width: 1180px;
  margin: 0 auto;
}
h2 {
  text-align: left;
  padding-left: 20px;
}
h4 {
  text-align: left;
}
.checkBox {
  width: 100%;
  background-color: #E2EFF6;
  margin-bottom: 30px;
  border: 1px solid transparent;
  border-radius: 10px;
  padding: 0 20px;
  text-align: left;
  line-height: 30px;
}
.el-table__expand-column .cell {
  .el-table__expand-icon--expanded{
    transform: rotate(180deg);
  }
  .el-table__expand-icon{
    .el-icon-arrow-right:before {
      content: "\E6E1";
    }
  }
}

.div1 {
  display: inline-block;  /*转为行内块儿 */
  text-align: center;
  width: 300px;
}

.legend_class {
  font-size: 16px;
}

.introBrowser{
  /*width: 100%;*/
  background-color: #E2EFF6;
  margin-bottom: 30px;
  border: 1px solid transparent;
  border-radius: 10px;
  padding: 0 20px;
  text-align: left;
  .introContent{
    line-height: 30px;
    font-size: 16px;
  }
}
</style>
