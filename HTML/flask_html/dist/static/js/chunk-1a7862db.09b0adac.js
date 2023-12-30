/*!
 *  build: vue-admin-better 
 *  vue-admin-beautiful.com 
 *  https://gitee.com/chu1204505056/vue-admin-better 
 *  time: 2023-12-29 21:50:54
 */
(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1a7862db"],{"0242":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e._self._c;return t("div",{staticClass:"dot-chart"},[t("div",{ref:"main",attrs:{id:"main"}})])},i=[],s=a("313e"),l=a("86d5"),r=a.n(l),n={name:"DotChart",data(){return{dataAll:[[[10,8.04],[8,6.95],[13,7.58],[9,8.81],[11,8.33],[14,9.96],[6,7.24],[4,4.26],[12,10.84],[7,4.82],[5,5.68]],[[10,9.14],[8,8.14],[13,8.74],[9,8.77],[11,9.26],[14,8.1],[6,6.13],[4,3.1],[12,9.13],[7,7.26],[5,4.74]],[[10,7.46],[8,6.77],[13,12.74],[9,7.11],[11,7.81],[14,8.84],[6,6.08],[4,5.39],[12,8.15],[7,6.42],[5,5.73]],[[8,6.58],[8,5.76],[8,7.71],[8,8.84],[8,8.47],[8,7.04],[8,5.25],[19,12.5],[8,5.56],[8,7.91],[8,6.89]]],markLineOpt:{animation:!1,label:{formatter:"y = 0.5 * x + 3",align:"right"},lineStyle:{type:"solid"},tooltip:{formatter:"y = 0.5 * x + 3"},data:[[{coord:[0,3],symbol:"none"},{coord:[20,13],symbol:"none"}]]}}},mounted(){var e=s["a"](this.$refs.main);s["b"](r.a.transform.regression),e.setOption({dataset:[{source:[[11,133],[21,121],[4,109],[8,137],[16,130],[24,98],[10,129],[22,122],[22,109],[6,121],[20,120],[5,106],[9,119],[20,102],[3,110],[6,137],[20,127],[3,98],[6,124],[19,111],[4,104],[12,117],[17,116],[4,94],[9,119],[16,119],[1,97],[6,123],[18,128],[1,107]]},{transform:{type:"ecStat:regression",config:{method:"polynomial",order:3}}}],title:{text:"User1",left:"center"},tooltip:{trigger:"axis",axisPointer:{type:"cross"}},xAxis:{splitLine:{lineStyle:{type:"dashed"}}},yAxis:{splitLine:{lineStyle:{type:"dashed"}}},series:[{name:"scatter",type:"scatter",datasetIndex:0},{name:"line",type:"line",smooth:!0,datasetIndex:1,symbolSize:.1,symbol:"circle",label:{show:!0,fontSize:16},labelLayout:{dx:-20},encode:{label:2,tooltip:1}}]}),window.PieChart=e}},c=n,m=(a("348e"),a("2877")),u=Object(m["a"])(c,o,i,!1,null,"d7457f62",null);t["default"]=u.exports},"0bb4":function(e,t,a){"use strict";a.r(t),a.d(t,"getNoticeList",(function(){return i}));var o=a("b775");function i(){return Object(o["default"])({url:"https://851edf02-46eb-43e6-828d-64c7e483ea41.bspapp.com/http/getNotice",method:"post"})}},"348e":function(e,t,a){"use strict";a("d9cb")},"4e35":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e._self._c;return t("div",{staticClass:"index-container"},[t("el-row",[t("el-button",{staticClass:"export-it",attrs:{type:"primary"},on:{click:function(t){return e.handleAdd()}}},[e._v("Add metrics")]),t("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],ref:"tableSort",attrs:{data:e.list,"element-loading-text":e.elementLoadingText,height:e.height},on:{"sort-change":e.tableSortChange}},[t("el-table-column",{attrs:{label:"ID","show-overflow-tooltip":"",width:"95"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s(t.$index+1)+" ")]}}])}),t("el-table-column",{attrs:{label:"Body Metrics Name",prop:"index_console","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Metrics Query",prop:"index_query","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Metrics Unit",prop:"index_unit","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Min Number",prop:"min_num","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Max Number",prop:"max_num","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Scale",prop:"scale","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Average",prop:"average","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Metrics Name",prop:"index_name","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Is Circadian",prop:"is_circadian","show-overflow-tooltip":""}})],1),t("el-pagination",{attrs:{background:e.background,"current-page":e.queryForm.pageNo,layout:e.layout,"page-size":e.queryForm.pageSize,total:e.total},on:{"current-change":e.handleCurrentChange,"size-change":e.handleSizeChange}})],1),t("el-dialog",{attrs:{title:e.title,visible:e.dialogFormVisible,width:"500px"},on:{"update:visible":function(t){e.dialogFormVisible=t},close:e.close}},[t("el-form",{ref:"form",attrs:{"label-width":"150px",model:e.form,rules:e.rules}},[t("el-form-item",{attrs:{label:"Body Metrics Name",prop:"index_console"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.index_console,callback:function(t){e.$set(e.form,"index_console","string"===typeof t?t.trim():t)},expression:"form.index_console"}})],1),t("el-form-item",{attrs:{label:"Metrics Query",prop:"index_query"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.index_query,callback:function(t){e.$set(e.form,"index_query","string"===typeof t?t.trim():t)},expression:"form.index_query"}})],1),t("el-form-item",{attrs:{label:"Metrics Unit",prop:"index_unit"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.index_unit,callback:function(t){e.$set(e.form,"index_unit","string"===typeof t?t.trim():t)},expression:"form.index_unit"}})],1),t("el-form-item",{attrs:{label:"Min Number",prop:"min_num"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.min_num,callback:function(t){e.$set(e.form,"min_num","string"===typeof t?t.trim():t)},expression:"form.min_num"}})],1),t("el-form-item",{attrs:{label:"Max Number",prop:"max_num"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.max_num,callback:function(t){e.$set(e.form,"max_num","string"===typeof t?t.trim():t)},expression:"form.max_num"}})],1),t("el-form-item",{attrs:{label:"Scale",prop:"scale"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.scale,callback:function(t){e.$set(e.form,"scale","string"===typeof t?t.trim():t)},expression:"form.scale"}})],1),t("el-form-item",{attrs:{label:"Average",prop:"average"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.average,callback:function(t){e.$set(e.form,"average","string"===typeof t?t.trim():t)},expression:"form.average"}})],1),t("el-form-item",{attrs:{label:"Metrics Name",prop:"index_name"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.index_name,callback:function(t){e.$set(e.form,"index_name","string"===typeof t?t.trim():t)},expression:"form.index_name"}})],1),t("el-form-item",{attrs:{label:"Is Circadian",prop:"is_circadian"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.is_circadian,callback:function(t){e.$set(e.form,"is_circadian","string"===typeof t?t.trim():t)},expression:"form.is_circadian"}})],1)],1),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:e.close}},[e._v("Cancel")]),t("el-button",{attrs:{type:"primary"},on:{click:e.save}},[e._v("Save")])],1)],1)],1)},i=[],s=(a("14d9"),a("bec6")),l=a("9224"),r=(a("0bb4"),a("ad8f")),n=a("0242"),c=a("ff28"),m=(a("0fae"),a("8e5f")),u=a.n(m),d=a("5c96"),f=a.n(d),p=a("b3ba");const h=a("3eba");a("c037");var b={name:"Index",components:{DotChart:n["default"],DotChart2:c["default"],ElementUI:f.a,Multiselect:u.a},data(){return{exportType:"csv",options:[{value:"选项1",label:"黄金糕"},{value:"选项2",label:"双皮奶"},{value:"选项3",label:"蚵仔煎"},{value:"选项4",label:"龙须面"},{value:"选项5",label:"北京烤鸭"}],value:"",dialogFormVisible:!1,form:{index_console:"",index_query:"",index_unit:"",min_num:null,max_num:null,scale:null,average:null,index_name:"",is_circadian:""},checkbox_majorclass:[],checkbox_subclass:[],checkbox_species:[],user_id_list:[{text:"User1",value:"User1"},{text:"User2",value:"User2"},{text:"User3",value:"User3"},{text:"User4",value:"User4"},{text:"User5",value:"User5"}],major_class_list:[{text:"GABAergic",value:"GABAergic"},{text:"Non-neuronal",value:"Non-neuronal"},{text:"Glutamatergic",value:"Glutamatergic"}],subclass_list:[{text:"GABAergic",value:"GABAergic"},{text:"Non-neuronal",value:"Non-neuronal"},{text:"Glutamatergic",value:"Glutamatergic"}],species_list:[{text:"Human",value:"Human"},{text:"Mouse",value:"Mouse"},{text:"Monkey",value:"Monkey"}],chart:null,imgShow:!0,list:[],imageList:[],listLoading:!0,layout:"total, sizes, prev, pager, next, jumper",total:0,background:!0,selectRows:"",elementLoadingText:"正在加载...",queryForm:{pageNo:1,pageSize:20,title:""},timer:0,updateTime:"2023-12-29 21:50:54",nodeEnv:"production",dependencies:l["a"],devDependencies:l["b"]}},computed:{height(){return this.$baseTableHeight()}},created(){this.fetchData()},beforeDestroy(){clearInterval(this.timer)},mounted(){this.init()},methods:{handleMajorClassChange(e){},hanleMajorClassRemove(e){},exportFile(){Object(p["a"])(this.$refs.tableSort,{fileName:"Export_file",type:this.exportType,useFormatter:!0}).then(()=>{console.info("ok")}).catch(e=>{console.info(e)})},showEdit(e){e?(this.title="Edit",this.form=Object.assign({},e)):this.title="添加",this.dialogFormVisible=!0},close(){this.$refs["form"].resetFields(),this.form=this.$options.data().form,this.dialogFormVisible=!1,this.$emit("fetch-data")},save(){this.$refs["form"].validate(async e=>{if(!e)return!1;{const{msg:e}=await Object(s["EditBodyIndexType"])(this.form);this.$baseMessage(e,"success"),this.$refs["form"].resetFields(),this.dialogFormVisible=!1,this.fetchData()}})},init(){this.chart=h.init(this.$refs.chart);const e={tooltip:{trigger:"item"},legend:{top:"5%",left:"center"},series:[{name:"Access From",type:"pie",radius:["40%","70%"],avoidLabelOverlap:!1,label:{show:!1,position:"center"},emphasis:{label:{show:!0,fontSize:40,fontWeight:"bold"}},labelLine:{show:!1},data:[{value:1048,name:"Search Engine"},{value:735,name:"Direct"},{value:580,name:"Email"},{value:484,name:"Union Ads"},{value:300,name:"Video Ads"}]}]};this.chart.setOption(e)},handleClick(e){this.$baseMessage(`点击了${e.name},这里可以写跳转`)},handleZrClick(){},handleChangeTheme(){this.$baseEventBus.$emit("theme")},tableSortChange(){const e=[];this.$refs.tableSort.tableData.forEach((t,a)=>{e.push(t.img)}),this.imageList=e},setSelectRows(e){this.selectRows=e},handleAdd(){this.showEdit()},handleEdit(e){this.$refs["edit"].showEdit(e)},handleDelete(e){if(e.id)this.$baseConfirm("你确定要删除当前项吗",null,async()=>{const{msg:t}=await Object(r["doDelete"])({ids:e.id});this.$baseMessage(t,"success"),this.fetchData()});else{if(!(this.selectRows.length>0))return this.$baseMessage("未选中任何行","error"),!1;{const e=this.selectRows.map(e=>e.id).join();this.$baseConfirm("你确定要删除选中项吗",null,async()=>{const{msg:t}=await Object(r["doDelete"])({ids:e});this.$baseMessage(t,"success"),this.fetchData()})}}},handleSizeChange(e){this.queryForm.pageSize=e,this.fetchData()},handleCurrentChange(e){this.queryForm.pageNo=e,this.fetchData()},handleQuery(){this.queryForm.pageNo=1,this.fetchData()},async fetchData(){this.listLoading=!0,Object(s["Getaddbpdyindexlist"])().then(e=>{this.list=e.data.list,this.total=e.data.total,this.listLoading=!1})},testMessage(){this.$baseMessage("test1","success")},testALert(){this.$baseAlert("11"),this.$baseAlert("11","自定义标题",()=>{}),this.$baseAlert("11",null,()=>{})},testConfirm(){this.$baseConfirm("你确定要执行该操作?",null,()=>{},()=>{})},testNotify(){this.$baseNotify("测试消息提示","test","success","bottom-right")}}},g=b,x=(a("8498"),a("e9c3"),a("2877")),v=Object(x["a"])(g,o,i,!1,null,"6d56a610",null);t["default"]=v.exports},"51d6":function(e,t,a){},aa91:function(e,t,a){},abad:function(e,t,a){"use strict";a("aa91")},ad8f:function(e,t,a){"use strict";a.r(t),a.d(t,"getList",(function(){return i})),a.d(t,"doEdit",(function(){return s})),a.d(t,"doDelete",(function(){return l}));var o=a("b775");function i(e){return Object(o["default"])({url:"/table/getList",method:"post",data:e})}function s(e){return Object(o["default"])({url:"/table/doEdit",method:"post",data:e})}function l(e){return Object(o["default"])({url:"/table/doDelete",method:"post",data:e})}},d9cb:function(e,t,a){},e9c3:function(e,t,a){"use strict";a("51d6")},ff28:function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e._self._c;return t("div",{staticClass:"dot-chart2"},[t("div",{ref:"main",attrs:{id:"main"}})])},i=[],s=a("313e"),l=a("86d5"),r=a.n(l),n={name:"DotChart2",data(){return{dataAll:[[[10,8.04],[8,6.95],[13,7.58],[9,8.81],[11,8.33],[14,9.96],[6,7.24],[4,4.26],[12,10.84],[7,4.82],[5,5.68]],[[10,9.14],[8,8.14],[13,8.74],[9,8.77],[11,9.26],[14,8.1],[6,6.13],[4,3.1],[12,9.13],[7,7.26],[5,4.74]],[[10,7.46],[8,6.77],[13,12.74],[9,7.11],[11,7.81],[14,8.84],[6,6.08],[4,5.39],[12,8.15],[7,6.42],[5,5.73]],[[8,6.58],[8,5.76],[8,7.71],[8,8.84],[8,8.47],[8,7.04],[8,5.25],[19,12.5],[8,5.56],[8,7.91],[8,6.89]]],markLineOpt:{animation:!1,label:{formatter:"y = 0.5 * x + 3",align:"right"},lineStyle:{type:"solid"},tooltip:{formatter:"y = 0.5 * x + 3"},data:[[{coord:[0,3],symbol:"none"},{coord:[20,13],symbol:"none"}]]}}},mounted(){var e=s["a"](this.$refs.main);s["b"](r.a.transform.regression),e.setOption({dataset:[{source:[[10,129],[18,106],[4,102],[11,132],[15,126],[24,99],[9,138],[14,121],[24,95],[14,124],[14,117],[1,91],[12,119],[14,124],[1,90],[7,128],[14,108],[6,97],[10,139],[14,117],[22,103],[11,135],[14,106],[24,103],[8,117],[20,126],[1,100],[9,125],[16,114],[4,98]]},{transform:{type:"ecStat:regression",config:{method:"polynomial",order:3}}}],title:{text:"User2",left:"center"},tooltip:{trigger:"axis",axisPointer:{type:"cross"}},xAxis:{splitLine:{lineStyle:{type:"dashed"}}},yAxis:{splitLine:{lineStyle:{type:"dashed"}}},series:[{name:"scatter",type:"scatter",datasetIndex:0,color:"orange"},{name:"line",type:"line",smooth:!0,datasetIndex:1,symbolSize:.1,symbol:"circle",label:{show:!0,fontSize:16},labelLayout:{dx:-20},encode:{label:2,tooltip:1}}]}),window.PieChart=e}},c=n,m=(a("abad"),a("2877")),u=Object(m["a"])(c,o,i,!1,null,"32f058c6",null);t["default"]=u.exports}}]);