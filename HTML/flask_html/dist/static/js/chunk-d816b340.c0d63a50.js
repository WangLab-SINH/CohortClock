/*!
 *  build: vue-admin-better 
 *  vue-admin-beautiful.com 
 *  https://gitee.com/chu1204505056/vue-admin-better 
 *  time: 2023-10-26 10:38:57
 */
(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-d816b340"],{"0bb4":function(e,t,a){"use strict";a.r(t),a.d(t,"getNoticeList",(function(){return s}));var i=a("b775");function s(){return Object(i["default"])({url:"https://851edf02-46eb-43e6-828d-64c7e483ea41.bspapp.com/http/getNotice",method:"post"})}},"10ce":function(e,t,a){},"1bdc":function(e,t,a){"use strict";a("10ce")},"234d":function(e,t,a){"use strict";a.r(t);var i=function(){var e=this,t=e._self._c;return t("div",{staticClass:"index-container"},[t("div",{ref:"chart"}),t("el-row",{attrs:{gutter:20}}),t("div",{staticClass:"introBrowser"},[t("h4",[e._v(" Filter ")]),t("table",[t("tr",[t("fieldset",{attrs:{id:"downbox"}},[t("legend",{staticClass:"legend_class"},[e._v(" Select body metrics : ")]),t("div",{staticClass:"div1"},[t("multiselect",{attrs:{"close-on-select":!1,"clear-on-select":!1,"preserve-search":!0,options:e.major_class_list,multiple:!1,placeholder:"Type to search","track-by":"index_name",label:"index_name"},on:{close:e.handleMajorClassChange,remove:e.hanleMajorClassRemove},model:{value:e.checkbox_majorclass,callback:function(t){e.checkbox_majorclass=t},expression:"checkbox_majorclass"}},[t("span",{attrs:{slot:"noResult"},slot:"noResult"},[e._v("Oops! No elements found. Consider changing the search query.")])])],1)])])]),t("br")]),t("el-row",[t("el-button",{staticClass:"export-it",attrs:{type:"primary"},on:{click:function(t){return e.exportFile()}}},[e._v("Download data")]),t("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],ref:"tableSort",attrs:{data:e.list,"element-loading-text":e.elementLoadingText,height:e.height},on:{"sort-change":e.tableSortChange}},[t("el-table-column",{attrs:{label:"ID","show-overflow-tooltip":"",width:"95"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s(t.$index+1+(e.queryForm.pageNo-1)*e.queryForm.pageSize)+" ")]}}])}),t("el-table-column",{attrs:{label:"User name",prop:"user_name","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Upload time",prop:"upload_time","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Upload time",prop:"data_time","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Kind of body metrics",prop:"index_name","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"Body metrics value",prop:"index_value","show-overflow-tooltip":""}})],1),t("el-pagination",{attrs:{total:e.pagination.total,background:e.background,"current-page":e.queryForm.pageNo,layout:e.layout,"page-size":e.queryForm.pageSize},on:{"current-change":e.handleCurrentChange,"size-change":e.handleSizeChange}})],1)],1)},s=[],o=(a("14d9"),a("bec6")),n=a("9224"),l=(a("0bb4"),a("ad8f")),r=a("437a"),c=(a("0fae"),a("8e5f")),d=a.n(c),h=a("5c96"),u=a.n(h),m=a("b3ba");const p=a("3eba");a("c037");var b={name:"Index",components:{DotChart:r["default"],ElementUI:u.a,Multiselect:d.a},data(){return{exportType:"csv",options:[{value:"选项1",label:"黄金糕"},{value:"选项2",label:"双皮奶"},{value:"选项3",label:"蚵仔煎"},{value:"选项4",label:"龙须面"},{value:"选项5",label:"北京烤鸭"}],value:"",select_body_index:"",checkbox_majorclass:[],checkbox_subclass:[],checkbox_species:[],major_class_list:[],subclass_list:[{text:"GABAergic",value:"GABAergic"},{text:"Non-neuronal",value:"Non-neuronal"},{text:"Glutamatergic",value:"Glutamatergic"}],species_list:[{text:"Human",value:"Human"},{text:"Mouse",value:"Mouse"},{text:"Monkey",value:"Monkey"}],chart:null,imgShow:!0,list:[],imageList:[],listLoading:!0,layout:"total, sizes, prev, pager, next, jumper",pagination:{total:0,page:1,limit:10},background:!0,selectRows:"",elementLoadingText:"正在加载...",queryForm:{pageNo:1,pageSize:20,title:""},timer:0,updateTime:"2023-10-26 10:38:57",nodeEnv:"production",dependencies:n["a"],devDependencies:n["b"]}},computed:{height(){return this.$baseTableHeight()}},created(){this.fetchData()},beforeDestroy(){clearInterval(this.timer)},mounted(){this.init()},methods:{handleMajorClassChange(e){null!=e&&(this.select_body_index=e.index_name,this.fetchData())},hanleMajorClassRemove(e){this.select_body_index="",this.fetchData()},exportFile(){Object(m["a"])(this.$refs.tableSort,{fileName:"Export_file",type:this.exportType,useFormatter:!0}).then(()=>{console.info("ok")}).catch(e=>{console.info(e)})},showEdit(e){e?(this.title="编辑",this.form=Object.assign({},e)):this.title="添加",this.dialogFormVisible=!0},init(){this.chart=p.init(this.$refs.chart);const e={tooltip:{trigger:"item"},legend:{top:"5%",left:"center"},series:[{name:"Access From",type:"pie",radius:["40%","70%"],avoidLabelOverlap:!1,label:{show:!1,position:"center"},emphasis:{label:{show:!0,fontSize:40,fontWeight:"bold"}},labelLine:{show:!1},data:[{value:1048,name:"Search Engine"},{value:735,name:"Direct"},{value:580,name:"Email"},{value:484,name:"Union Ads"},{value:300,name:"Video Ads"}]}]};this.chart.setOption(e)},handleClick(e){this.$baseMessage(`点击了${e.name},这里可以写跳转`)},handleZrClick(){},handleChangeTheme(){this.$baseEventBus.$emit("theme")},tableSortChange(){const e=[];this.$refs.tableSort.tableData.forEach((t,a)=>{e.push(t.img)}),this.imageList=e},setSelectRows(e){this.selectRows=e},handleAdd(){this.$refs["edit"].showEdit()},handleEdit(e){this.$refs["edit"].showEdit(e)},handleDelete(e){if(e.id)this.$baseConfirm("你确定要删除当前项吗",null,async()=>{const{msg:t}=await Object(l["doDelete"])({ids:e.id});this.$baseMessage(t,"success"),this.fetchData()});else{if(!(this.selectRows.length>0))return this.$baseMessage("未选中任何行","error"),!1;{const e=this.selectRows.map(e=>e.id).join();this.$baseConfirm("你确定要删除选中项吗",null,async()=>{const{msg:t}=await Object(l["doDelete"])({ids:e});this.$baseMessage(t,"success"),this.fetchData()})}}},handleSizeChange(e){this.queryForm.pageSize=e,this.fetchData()},handleCurrentChange(e){this.queryForm.pageNo=e,this.fetchData()},handleQuery(){this.queryForm.pageNo=1,this.fetchData()},async fetchData(){this.listLoading=!0,Object(o["BodyIndexData"])(this.select_body_index,this.queryForm.pageNo,this.queryForm.pageSize).then(e=>{this.list=e.data.list,console.log(e.data),this.pagination.total=e.data.total,this.listLoading=!1,this.major_class_list=e.data.index_name})},testMessage(){this.$baseMessage("test1","success")},testALert(){this.$baseAlert("11"),this.$baseAlert("11","自定义标题",()=>{}),this.$baseAlert("11",null,()=>{})},testConfirm(){this.$baseConfirm("你确定要执行该操作?",null,()=>{},()=>{})},testNotify(){this.$baseNotify("测试消息提示","test","success","bottom-right")}}},g=b,f=(a("8498"),a("31e6"),a("2877")),x=Object(f["a"])(g,i,s,!1,null,"0c7cd14a",null);t["default"]=x.exports},"31e6":function(e,t,a){"use strict";a("33ff")},"33ff":function(e,t,a){},"437a":function(e,t,a){"use strict";a.r(t);var i=function(){var e=this,t=e._self._c;return t("div",{staticClass:"dot-chart"},[t("div",{ref:"main",attrs:{id:"main"}})])},s=[],o=a("313e"),n={name:"DotChart",data(){return{dataAll:[[[10,8.04],[8,6.95],[13,7.58],[9,8.81],[11,8.33],[14,9.96],[6,7.24],[4,4.26],[12,10.84],[7,4.82],[5,5.68]],[[10,9.14],[8,8.14],[13,8.74],[9,8.77],[11,9.26],[14,8.1],[6,6.13],[4,3.1],[12,9.13],[7,7.26],[5,4.74]],[[10,7.46],[8,6.77],[13,12.74],[9,7.11],[11,7.81],[14,8.84],[6,6.08],[4,5.39],[12,8.15],[7,6.42],[5,5.73]],[[8,6.58],[8,5.76],[8,7.71],[8,8.84],[8,8.47],[8,7.04],[8,5.25],[19,12.5],[8,5.56],[8,7.91],[8,6.89]]],markLineOpt:{animation:!1,label:{formatter:"y = 0.5 * x + 3",align:"right"},lineStyle:{type:"solid"},tooltip:{formatter:"y = 0.5 * x + 3"},data:[[{coord:[0,3],symbol:"none"},{coord:[20,13],symbol:"none"}]]}}},mounted(){var e=o["a"](this.$refs.main);e.setOption({title:{text:"Body Metrics",left:"center",top:0},grid:[{left:"7%",top:"7%",width:"38%",height:"38%"},{right:"7%",top:"7%",width:"38%",height:"38%"},{left:"7%",bottom:"7%",width:"38%",height:"38%"},{right:"7%",bottom:"7%",width:"38%",height:"38%"}],tooltip:{formatter:"Group {a}: ({c})"},xAxis:[{gridIndex:0,min:0,max:20},{gridIndex:1,min:0,max:20},{gridIndex:2,min:0,max:20},{gridIndex:3,min:0,max:20}],yAxis:[{gridIndex:0,min:0,max:15},{gridIndex:1,min:0,max:15},{gridIndex:2,min:0,max:15},{gridIndex:3,min:0,max:15}],series:[{name:"I",type:"scatter",xAxisIndex:0,yAxisIndex:0,data:this.dataAll[0],markLine:this.markLineOpt},{name:"II",type:"scatter",xAxisIndex:1,yAxisIndex:1,data:this.dataAll[1],markLine:this.markLineOpt},{name:"III",type:"scatter",xAxisIndex:2,yAxisIndex:2,data:this.dataAll[2],markLine:this.markLineOpt},{name:"IV",type:"scatter",xAxisIndex:3,yAxisIndex:3,data:this.dataAll[3],markLine:this.markLineOpt}]}),window.PieChart=e}},l=n,r=(a("1bdc"),a("2877")),c=Object(r["a"])(l,i,s,!1,null,"25b1127e",null);t["default"]=c.exports},ad8f:function(e,t,a){"use strict";a.r(t),a.d(t,"getList",(function(){return s})),a.d(t,"doEdit",(function(){return o})),a.d(t,"doDelete",(function(){return n}));var i=a("b775");function s(e){return Object(i["default"])({url:"/table/getList",method:"post",data:e})}function o(e){return Object(i["default"])({url:"/table/doEdit",method:"post",data:e})}function n(e){return Object(i["default"])({url:"/table/doDelete",method:"post",data:e})}}}]);