<template>
  <div class="index-container">

    <div  ref="chart"></div>
    <el-row :gutter="20">
      <el-col :lg="16" :md="12" :sm="24" :xl="12" :xs="24">
        <pie-chart></pie-chart>



      </el-col>
      <el-col :lg="16" :md="12" :sm="24" :xl="12" :xs="24">
        <DistributionPlot></DistributionPlot>

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
<!--        <el-table-column label="User name" prop="user_name" show-overflow-tooltip />-->
<!--        <el-table-column label="Upload time" prop="upload_time" show-overflow-tooltip />-->
<!--        <el-table-column label="Deit time" prop="data_time" show-overflow-tooltip />-->
<!--        &lt;!&ndash;        <el-table-column label="Image" show-overflow-tooltip>&ndash;&gt;-->
<!--        &lt;!&ndash;          <template #default="{ row }">&ndash;&gt;-->
<!--        &lt;!&ndash;            <el-image&ndash;&gt;-->
<!--        &lt;!&ndash;              v-if="imgShow"&ndash;&gt;-->
<!--        &lt;!&ndash;              :preview-src-list="imageList"&ndash;&gt;-->
<!--        &lt;!&ndash;              :src="row.img"&ndash;&gt;-->
<!--        &lt;!&ndash;            />&ndash;&gt;-->
<!--        &lt;!&ndash;          </template>&ndash;&gt;-->
<!--        &lt;!&ndash;        </el-table-column>&ndash;&gt;-->
<!--        <el-table-column label="Image">-->
<!--          <template slot-scope="scope">-->
<!--&lt;!&ndash;            <el-image style="width:20px;height: 20px;" :preview-src-list="[scope.row.photo_image]"&ndash;&gt;-->
<!--&lt;!&ndash;                      src="requrie('E:/temp_food.jpeg')" />&ndash;&gt;-->
<!--            <el-image style="width:20px;height: 20px;" :preview-src-list="[require('../../assets/image/'+scope.row.photo_image+'')]"-->
<!--                      :src="require('../../assets/image/'+scope.row.photo_image+'')" />-->
<!--          </template>-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--          label="Food or not"-->
<!--          prop="photo_type"-->
<!--          show-overflow-tooltip-->
<!--          sortable-->
<!--        />-->
<!--        <el-table-column label="Type of food" prop="photo_kind" show-overflow-tooltip />-->
<!--        <el-table-column label="Food calorie" prop="photo_cal" show-overflow-tooltip />-->
<!--        <el-table-column label="User anno of food type" prop="photo_type_anno" show-overflow-tooltip />-->
<!--        <el-table-column label="User anno of food calorie" prop="photo_cal_anno" show-overflow-tooltip />-->


        <el-table-column label="User name" prop="user_name" show-overflow-tooltip />
        <el-table-column label="Upload time" prop="upload_time" show-overflow-tooltip />
        <el-table-column label="Deit time" prop="user_time" show-overflow-tooltip />
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
          label="Food or not"
          prop="photo_type"
          show-overflow-tooltip
          sortable
        />
        <el-table-column label="Type of food" prop="photo_kind" show-overflow-tooltip />
        <el-table-column label="Food calorie" prop="photo_cal" show-overflow-tooltip />

        <el-table-column label="Operate" show-overflow-tooltip width="180px">
          <template slot-scope="scope">
            <el-button type="text" @click="handleEdit(scope.row)">Edit</el-button>
<!--            <el-button type="text" @click="handleDelete(scope.row)">Delete</el-button>-->
          </template>
        </el-table-column>
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
    <el-dialog
      :title="title"
      :visible.sync="dialogFormVisible"
      width="500px"
      @close="close"
    >
      <el-form ref="form" label-width="100px" :model="form" :rules="rules">
        <el-form-item label="Food or not" prop="photo_type">
          <el-input v-model.trim="form.photo_type" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Type of food" prop="photo_kind">
          <el-input v-model.trim="form.photo_kind" autocomplete="off" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">Cancel</el-button>
        <el-button type="primary" @click="save">Save</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import VabChart from '@/plugins/echarts'
import { EditPhoto, PhotoInfoData } from "@/api/photoPage";
import { dependencies, devDependencies } from '../../../package.json'
// import { getList } from '@/api/changeLog'
import { getNoticeList } from '@/api/notice'
import { doDelete, getList } from '@/api/table'
// import TableEdit from '@/views/vab/table/components/TableEdit'
import DistributionPlot from '@/views/index/DistributionPlot.vue'
import PieChart from '@/views/board/PieChart.vue'
import elTableExport from "el-table-export";
const Echarts = require('echarts/lib/echarts')
require('echarts/lib/chart/pie')
export default {
  name: 'Index',
  components: {
    DistributionPlot,
    PieChart,
    // TableEdit,
    // VabChart,
  },
  data() {
    return {
      exportType: "csv",
      form: {
        photo_type: '',
        photo_kind: '',
      },
      rules: {
        photo_type: [{ required: true, trigger: 'blur', message: '请输入标题' }],
        photo_kind: [{ required: true, trigger: 'blur', message: '请输入作者' }],
      },
      title: '',
      dialogFormVisible: false,

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
    // showEdit(row) {
    //   console.log("showEdit")
    //   if (!row) {
    //     this.title = '添加'
    //   } else {
    //     this.title = '编辑'
    //     this.form = Object.assign({}, row)
    //   }
    //   this.dialogFormVisible = true
    // },
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



    showEdit(row) {
      if (!row) {
        this.title = '添加'
      } else {
        this.title = 'Edit'
        this.form = Object.assign({}, row)
      }
      this.dialogFormVisible = true


    },
    close() {
      this.$refs['form'].resetFields()
      this.form = this.$options.data().form
      this.dialogFormVisible = false
      this.$emit('fetch-data')
    },
    save() {
      this.$refs['form'].validate(async (valid) => {
        if (valid) {
          const { msg } = await EditPhoto(this.form)
          this.$baseMessage(msg, 'success')
          this.$refs['form'].resetFields()
          this.dialogFormVisible = false
          // this.$emit('fetch-data')
          // this.form = this.$options.data().form
          this.fetchData()
        } else {
          return false
        }
      })
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
      this.showEdit(row)
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
    fetchData() {
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
      })

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
