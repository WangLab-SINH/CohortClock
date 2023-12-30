/*!
 *  build: vue-admin-better 
 *  vue-admin-beautiful.com 
 *  https://gitee.com/chu1204505056/vue-admin-better 
 *  time: 2023-8-14 16:38:22
 */
(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0956b2b4"],{"6a7e":function(t,e,a){},"6d2a":function(t,e,a){"use strict";a.r(e),a.d(e,"getList",(function(){return i}));var s=a("b775");function i(t){return Object(s["default"])({url:"/goodsList/getList",method:"post",data:t})}},"74db":function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t._self._c;return e("div",{staticClass:"goods-list-container"},[e("vab-query-form",[e("vab-query-form-right-panel",{attrs:{span:24}},[e("el-form",{ref:"form",attrs:{inline:!0,model:t.queryForm},nativeOn:{submit:function(t){t.preventDefault()}}},[e("el-form-item",[e("el-input",{attrs:{placeholder:"商品名称"},model:{value:t.queryForm.title,callback:function(e){t.$set(t.queryForm,"title",e)},expression:"queryForm.title"}})],1),e("el-form-item",[e("el-button",{attrs:{icon:"el-icon-search","native-type":"submit",type:"primary"},on:{click:t.handleQuery}},[t._v(" 查询 ")])],1)],1)],1)],1),e("el-row",{attrs:{gutter:20}},t._l(t.list,(function(a,s){return e("el-col",{key:s,attrs:{lg:8,md:8,sm:8,xl:6,xs:24}},[e("el-card",{attrs:{"body-style":{padding:"0px"},shadow:"hover"}},[e("div",{staticClass:"goods-list-card-body"},[e("div",{staticClass:"goods-list-tag-group"},[a.isRecommend?e("el-tag",{attrs:{hit:"",type:"success"}},[t._v("推荐")]):t._e(),0===a.status?e("el-tag",{attrs:{hit:"",type:"danger"}},[t._v("缺货")]):t._e()],1),e("div",{staticClass:"goods-list-image-group"},[e("img",{staticClass:"goods-list-image",attrs:{src:a.image}})]),e("div",{staticClass:"goods-list-title"},[t._v(" "+t._s(a.title)+" ")]),e("div",{staticClass:"goods-list-description"},[t._v(" "+t._s(a.description)+" ")]),e("div",{staticClass:"goods-list-price"},[e("span",[t._v("¥ "+t._s(a.price)+" 元")])])])])],1)})),1),e("el-pagination",{attrs:{background:"","current-page":t.queryForm.pageNo,layout:t.layout,"page-size":t.queryForm.pageSize,total:t.total},on:{"current-change":t.handleCurrentChange,"size-change":t.handleSizeChange}})],1)},i=[],o=a("6d2a"),r={name:"Goods",components:{},data(){return{queryForm:{pageNo:1,pageSize:20,title:""},list:null,listLoading:!0,layout:"total, sizes, prev, pager, next, jumper",total:0,elementLoadingText:"正在加载..."}},created(){this.fetchData()},methods:{handleSizeChange(t){this.queryForm.pageSize=t,this.fetchData()},handleCurrentChange(t){this.queryForm.pageNo=t,this.fetchData()},handleQuery(){this.queryForm.pageNo=1,this.fetchData()},async fetchData(){this.listLoading=!0;const{data:t,totalCount:e}=await Object(o["getList"])(this.queryForm);this.list=t,this.total=e}}},n=r,l=(a("a7b3"),a("2877")),c=Object(l["a"])(n,s,i,!1,null,"5b1a6839",null);e["default"]=c.exports},a7b3:function(t,e,a){"use strict";a("6a7e")}}]);