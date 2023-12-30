/*!
 *  build: vue-admin-better 
 *  vue-admin-beautiful.com 
 *  https://gitee.com/chu1204505056/vue-admin-better 
 *  time: 2023-10-25 18:51:53
 */
(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-104755ab"],{"0242":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e._self._c;return t("div",{staticClass:"dot-chart"},[t("div",{ref:"main",attrs:{id:"main"}})])},l=[],i=a("313e"),s=a("86d5"),r=a.n(s),n={name:"DotChart",data(){return{dataAll:[[[10,8.04],[8,6.95],[13,7.58],[9,8.81],[11,8.33],[14,9.96],[6,7.24],[4,4.26],[12,10.84],[7,4.82],[5,5.68]],[[10,9.14],[8,8.14],[13,8.74],[9,8.77],[11,9.26],[14,8.1],[6,6.13],[4,3.1],[12,9.13],[7,7.26],[5,4.74]],[[10,7.46],[8,6.77],[13,12.74],[9,7.11],[11,7.81],[14,8.84],[6,6.08],[4,5.39],[12,8.15],[7,6.42],[5,5.73]],[[8,6.58],[8,5.76],[8,7.71],[8,8.84],[8,8.47],[8,7.04],[8,5.25],[19,12.5],[8,5.56],[8,7.91],[8,6.89]]],markLineOpt:{animation:!1,label:{formatter:"y = 0.5 * x + 3",align:"right"},lineStyle:{type:"solid"},tooltip:{formatter:"y = 0.5 * x + 3"},data:[[{coord:[0,3],symbol:"none"},{coord:[20,13],symbol:"none"}]]}}},mounted(){var e=i["a"](this.$refs.main);i["b"](r.a.transform.regression),e.setOption({dataset:[{source:[[11,133],[21,121],[4,109],[8,137],[16,130],[24,98],[10,129],[22,122],[22,109],[6,121],[20,120],[5,106],[9,119],[20,102],[3,110],[6,137],[20,127],[3,98],[6,124],[19,111],[4,104],[12,117],[17,116],[4,94],[9,119],[16,119],[1,97],[6,123],[18,128],[1,107]]},{transform:{type:"ecStat:regression",config:{method:"polynomial",order:3}}}],title:{text:"User1",left:"center"},tooltip:{trigger:"axis",axisPointer:{type:"cross"}},xAxis:{splitLine:{lineStyle:{type:"dashed"}}},yAxis:{splitLine:{lineStyle:{type:"dashed"}}},series:[{name:"scatter",type:"scatter",datasetIndex:0},{name:"line",type:"line",smooth:!0,datasetIndex:1,symbolSize:.1,symbol:"circle",label:{show:!0,fontSize:16},labelLayout:{dx:-20},encode:{label:2,tooltip:1}}]}),window.PieChart=e}},c=n,d=(a("348e"),a("2877")),u=Object(d["a"])(c,o,l,!1,null,"d7457f62",null);t["default"]=u.exports},"079a":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e._self._c;return t("div",{staticClass:"index-container"},[t("el-row",[t("el-button",{staticClass:"export-it",attrs:{type:"primary"},on:{click:function(t){return e.exportFile()}}},[e._v("Download data")]),t("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],ref:"tableSort",attrs:{data:e.list,"element-loading-text":e.elementLoadingText,height:e.height},on:{"sort-change":e.tableSortChange}},[t("el-table-column",{attrs:{label:"ID","show-overflow-tooltip":"",width:"95"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s(t.$index+1)+" ")]}}])}),t("el-table-column",{attrs:{label:"User name",prop:"user_name","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Phone id",prop:"phone_id","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Location",prop:"location","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Group type",prop:"group_type","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Eating start time",prop:"start_time","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Eating end time",prop:"end_time","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Edit time flag",prop:"edit_flag","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Disease",prop:"disease","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Diet type",prop:"diet_type","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Uploaded food number",prop:"food_num","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Uploaded index number",prop:"body_index_num","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Weight",prop:"weight","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Height",prop:"height","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"BMI",prop:"BMI","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Operate","show-overflow-tooltip":"",width:"180px"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("el-button",{attrs:{type:"text"},on:{click:function(t){return e.handleEdit(a.row)}}},[e._v("Edit")])]}}])})],1),t("el-pagination",{attrs:{total:e.pagination.total,background:e.background,"current-page":e.queryForm.pageNo,layout:e.layout,"page-size":e.queryForm.pageSize},on:{"current-change":e.handleCurrentChange,"size-change":e.handleSizeChange}})],1),t("el-dialog",{attrs:{title:e.title,visible:e.dialogFormVisible,width:"500px"},on:{"update:visible":function(t){e.dialogFormVisible=t},close:e.close}},[t("el-form",{ref:"form",attrs:{"label-width":"150px",model:e.form,rules:e.rules}},[t("el-form-item",{attrs:{label:"Eating start time",prop:"start_time"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.start_time,callback:function(t){e.$set(e.form,"start_time","string"===typeof t?t.trim():t)},expression:"form.start_time"}})],1),t("el-form-item",{attrs:{label:"Eating end time",prop:"end_time"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.end_time,callback:function(t){e.$set(e.form,"end_time","string"===typeof t?t.trim():t)},expression:"form.end_time"}})],1),t("el-form-item",{attrs:{label:"Edit flag true or false",prop:"edit_flag"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.edit_flag,callback:function(t){e.$set(e.form,"edit_flag","string"===typeof t?t.trim():t)},expression:"form.edit_flag"}})],1)],1),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:e.close}},[e._v("Cancel")]),t("el-button",{attrs:{type:"primary"},on:{click:e.save}},[e._v("Save")])],1)],1)],1)},l=[],i=(a("14d9"),a("bec6")),s=a("9224"),r=(a("0bb4"),a("ad8f")),n=a("0242"),c=a("ff28"),d=(a("0fae"),a("8e5f")),u=a.n(d),m=a("5c96"),h=a.n(m),p=a("b3ba");const f=a("3eba");a("c037");var b={name:"Index",components:{DotChart:n["default"],DotChart2:c["default"],ElementUI:h.a,Multiselect:u.a},data(){return{exportType:"csv",form:{photo_type:"",photo_kind:""},rules:{photo_type:[{required:!0,trigger:"blur",message:"请输入标题"}],photo_kind:[{required:!0,trigger:"blur",message:"请输入作者"}]},title:"",dialogFormVisible:!1,options:[{value:"选项1",label:"黄金糕"},{value:"选项2",label:"双皮奶"},{value:"选项3",label:"蚵仔煎"},{value:"选项4",label:"龙须面"},{value:"选项5",label:"北京烤鸭"}],value:"",checkbox_majorclass:[],checkbox_subclass:[],checkbox_species:[],user_id_list:[{text:"User1",value:"User1"},{text:"User2",value:"User2"},{text:"User3",value:"User3"},{text:"User4",value:"User4"},{text:"User5",value:"User5"}],major_class_list:[{text:"GABAergic",value:"GABAergic"},{text:"Non-neuronal",value:"Non-neuronal"},{text:"Glutamatergic",value:"Glutamatergic"}],subclass_list:[{text:"GABAergic",value:"GABAergic"},{text:"Non-neuronal",value:"Non-neuronal"},{text:"Glutamatergic",value:"Glutamatergic"}],species_list:[{text:"Human",value:"Human"},{text:"Mouse",value:"Mouse"},{text:"Monkey",value:"Monkey"}],chart:null,imgShow:!0,list:[],imageList:[],listLoading:!0,layout:"total, sizes, prev, pager, next, jumper",total:0,background:!0,selectRows:"",elementLoadingText:"正在加载...",pagination:{total:0,page:1,limit:10},queryForm:{pageNo:1,pageSize:20,title:""},timer:0,updateTime:"2023-10-25 18:51:53",nodeEnv:"production",dependencies:s["a"],devDependencies:s["b"]}},computed:{height(){return this.$baseTableHeight()}},created(){this.fetchData()},beforeDestroy(){clearInterval(this.timer)},mounted(){this.init()},methods:{handleMajorClassChange(e){},hanleMajorClassRemove(e){},showEdit(e){e?(this.title="Edit",this.form=Object.assign({},e)):this.title="添加",this.dialogFormVisible=!0},close(){this.$refs["form"].resetFields(),this.form=this.$options.data().form,this.dialogFormVisible=!1,this.$emit("fetch-data")},save(){this.$refs["form"].validate(async e=>{if(!e)return!1;{const{msg:e}=await Object(i["EditEatFlag"])(this.form);this.$baseMessage(e,"success"),this.$refs["form"].resetFields(),this.dialogFormVisible=!1,this.fetchData()}})},init(){this.chart=f.init(this.$refs.chart);const e={tooltip:{trigger:"item"},legend:{top:"5%",left:"center"},series:[{name:"Access From",type:"pie",radius:["40%","70%"],avoidLabelOverlap:!1,label:{show:!1,position:"center"},emphasis:{label:{show:!0,fontSize:40,fontWeight:"bold"}},labelLine:{show:!1},data:[{value:1048,name:"Search Engine"},{value:735,name:"Direct"},{value:580,name:"Email"},{value:484,name:"Union Ads"},{value:300,name:"Video Ads"}]}]};this.chart.setOption(e)},handleClick(e){this.$baseMessage(`点击了${e.name},这里可以写跳转`)},handleZrClick(){},handleChangeTheme(){this.$baseEventBus.$emit("theme")},exportFile(){Object(p["a"])(this.$refs.tableSort,{fileName:"Export_file",type:this.exportType,useFormatter:!0}).then(()=>{console.info("ok")}).catch(e=>{console.info(e)})},tableSortChange(){const e=[];this.$refs.tableSort.tableData.forEach((t,a)=>{e.push(t.img)}),this.imageList=e},setSelectRows(e){this.selectRows=e},handleAdd(){this.$refs["edit"].showEdit()},handleEdit(e){this.showEdit(e)},handleDelete(e){if(e.id)this.$baseConfirm("你确定要删除当前项吗",null,async()=>{const{msg:t}=await Object(r["doDelete"])({ids:e.id});this.$baseMessage(t,"success"),this.fetchData()});else{if(!(this.selectRows.length>0))return this.$baseMessage("未选中任何行","error"),!1;{const e=this.selectRows.map(e=>e.id).join();this.$baseConfirm("你确定要删除选中项吗",null,async()=>{const{msg:t}=await Object(r["doDelete"])({ids:e});this.$baseMessage(t,"success"),this.fetchData()})}}},handleSizeChange(e){this.queryForm.pageSize=e,this.fetchData()},handleCurrentChange(e){this.queryForm.pageNo=e,this.fetchData()},handleQuery(){this.queryForm.pageNo=1,this.fetchData()},async fetchData(){this.listLoading=!0,Object(i["GetUserinfolist"])(this.queryForm.pageNo,this.queryForm.pageSize).then(e=>{this.list=e.data.list,this.pagination.total=e.data.total,this.listLoading=!1})},testMessage(){this.$baseMessage("test1","success")},testALert(){this.$baseAlert("11"),this.$baseAlert("11","自定义标题",()=>{}),this.$baseAlert("11",null,()=>{})},testConfirm(){this.$baseConfirm("你确定要执行该操作?",null,()=>{},()=>{})},testNotify(){this.$baseNotify("测试消息提示","test","success","bottom-right")}}},g=b,v=(a("8498"),a("1bfb"),a("2877")),y=Object(v["a"])(g,o,l,!1,null,"05744598",null);t["default"]=y.exports},"0bb4":function(e,t,a){"use strict";a.r(t),a.d(t,"getNoticeList",(function(){return l}));var o=a("b775");function l(){return Object(o["default"])({url:"https://851edf02-46eb-43e6-828d-64c7e483ea41.bspapp.com/http/getNotice",method:"post"})}},"1bfb":function(e,t,a){"use strict";a("3f6b0")},"348e":function(e,t,a){"use strict";a("d9cb")},"3f6b0":function(e,t,a){},aa91:function(e,t,a){},abad:function(e,t,a){"use strict";a("aa91")},ad8f:function(e,t,a){"use strict";a.r(t),a.d(t,"getList",(function(){return l})),a.d(t,"doEdit",(function(){return i})),a.d(t,"doDelete",(function(){return s}));var o=a("b775");function l(e){return Object(o["default"])({url:"/table/getList",method:"post",data:e})}function i(e){return Object(o["default"])({url:"/table/doEdit",method:"post",data:e})}function s(e){return Object(o["default"])({url:"/table/doDelete",method:"post",data:e})}},d9cb:function(e,t,a){},ff28:function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e._self._c;return t("div",{staticClass:"dot-chart2"},[t("div",{ref:"main",attrs:{id:"main"}})])},l=[],i=a("313e"),s=a("86d5"),r=a.n(s),n={name:"DotChart2",data(){return{dataAll:[[[10,8.04],[8,6.95],[13,7.58],[9,8.81],[11,8.33],[14,9.96],[6,7.24],[4,4.26],[12,10.84],[7,4.82],[5,5.68]],[[10,9.14],[8,8.14],[13,8.74],[9,8.77],[11,9.26],[14,8.1],[6,6.13],[4,3.1],[12,9.13],[7,7.26],[5,4.74]],[[10,7.46],[8,6.77],[13,12.74],[9,7.11],[11,7.81],[14,8.84],[6,6.08],[4,5.39],[12,8.15],[7,6.42],[5,5.73]],[[8,6.58],[8,5.76],[8,7.71],[8,8.84],[8,8.47],[8,7.04],[8,5.25],[19,12.5],[8,5.56],[8,7.91],[8,6.89]]],markLineOpt:{animation:!1,label:{formatter:"y = 0.5 * x + 3",align:"right"},lineStyle:{type:"solid"},tooltip:{formatter:"y = 0.5 * x + 3"},data:[[{coord:[0,3],symbol:"none"},{coord:[20,13],symbol:"none"}]]}}},mounted(){var e=i["a"](this.$refs.main);i["b"](r.a.transform.regression),e.setOption({dataset:[{source:[[10,129],[18,106],[4,102],[11,132],[15,126],[24,99],[9,138],[14,121],[24,95],[14,124],[14,117],[1,91],[12,119],[14,124],[1,90],[7,128],[14,108],[6,97],[10,139],[14,117],[22,103],[11,135],[14,106],[24,103],[8,117],[20,126],[1,100],[9,125],[16,114],[4,98]]},{transform:{type:"ecStat:regression",config:{method:"polynomial",order:3}}}],title:{text:"User2",left:"center"},tooltip:{trigger:"axis",axisPointer:{type:"cross"}},xAxis:{splitLine:{lineStyle:{type:"dashed"}}},yAxis:{splitLine:{lineStyle:{type:"dashed"}}},series:[{name:"scatter",type:"scatter",datasetIndex:0,color:"orange"},{name:"line",type:"line",smooth:!0,datasetIndex:1,symbolSize:.1,symbol:"circle",label:{show:!0,fontSize:16},labelLayout:{dx:-20},encode:{label:2,tooltip:1}}]}),window.PieChart=e}},c=n,d=(a("abad"),a("2877")),u=Object(d["a"])(c,o,l,!1,null,"32f058c6",null);t["default"]=u.exports}}]);