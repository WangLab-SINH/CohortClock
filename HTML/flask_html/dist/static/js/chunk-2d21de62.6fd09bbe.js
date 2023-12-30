/*!
 *  build: vue-admin-better 
 *  vue-admin-beautiful.com 
 *  https://gitee.com/chu1204505056/vue-admin-better 
 *  time: 2023-8-14 16:38:22
 */
(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d21de62"],{d2ec:function(e,t,o){"use strict";o.r(t);var a=function(){var e=this,t=e._self._c;return t("div",{staticClass:"permissions-container"},[t("el-divider",{attrs:{"content-position":"left"}},[e._v(" intelligence模式,前端根据permissions拦截路由(演示环境,默认使用此方案) ")]),t("el-form",{ref:"form",attrs:{inline:!0,model:e.form}},[t("el-form-item",{attrs:{label:"切换账号"}},[t("el-radio-group",{model:{value:e.form.account,callback:function(t){e.$set(e.form,"account",t)},expression:"form.account"}},[t("el-radio",{attrs:{label:"admin"}},[e._v("admin")]),t("el-radio",{attrs:{label:"editor"}},[e._v("editor")]),t("el-radio",{attrs:{label:"test"}},[e._v("test")])],1)],1),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.handleChangePermission}},[e._v(" 切换权限 ")])],1),t("el-form-item",{attrs:{label:"当前账号拥有的权限"}},[e._v(" "+e._s(JSON.stringify(e.permissions))+" ")])],1),t("el-divider",{attrs:{"content-position":"left"}},[e._v("按钮级权限演示")]),t("el-button",{directives:[{name:"permissions",rawName:"v-permissions",value:["admin"],expression:"['admin']"}],attrs:{type:"primary"}},[e._v(' 我是拥有["admin"]权限的按钮 ')]),t("el-button",{directives:[{name:"permissions",rawName:"v-permissions",value:["editor"],expression:"['editor']"}],attrs:{type:"primary"}},[e._v(' 我是拥有["editor"]权限的按钮 ')]),t("el-button",{directives:[{name:"permissions",rawName:"v-permissions",value:["test"],expression:"['test']"}],attrs:{type:"primary"}},[e._v(' 我是拥有["test"]权限的按钮 ')]),t("br"),t("br"),t("el-divider",{attrs:{"content-position":"left"}},[e._v(" all模式,路由以及view文件引入全部交给后端(权限复杂,且随时变更,建议使用此方案) ")]),t("p",[e._v(" settings.js配置authentication为all即可完全交由后端控制,mock中有后端接口示例,权限繁琐,有几十种权限的项目直接用这种, 由于演示环境是mock数据模拟,可能无法展现此功能的配置,只做如下展示,便于您的理解 ")]),t("br"),t("el-row",{attrs:{gutter:20}},[t("el-col",{attrs:{lg:12,md:12,sm:24,xl:12,xs:24}},[t("el-table",{attrs:{border:"",data:e.tableData,"default-expand-all":"","row-key":"path","tree-props":{children:"children",hasChildren:"hasChildren"}}},[t("el-table-column",{attrs:{label:"name",prop:"name","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"path",prop:"path","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"component",prop:"component","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"redirect",prop:"redirect","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"标题",prop:"meta.title","show-overflow-tooltip":""}}),t("el-table-column",{attrs:{label:"图标","show-overflow-tooltip":""},scopedSlots:e._u([{key:"default",fn:function({row:o}){return[o.meta?t("span",[o.meta.icon?t("vab-icon",{attrs:{icon:["fas",o.meta.icon]}}):e._e()],1):e._e()]}}])}),t("el-table-column",{attrs:{label:"是否固定","show-overflow-tooltip":""},scopedSlots:e._u([{key:"default",fn:function({row:o}){return[o.meta?t("span",[e._v(" "+e._s(o.meta.affix)+" ")]):e._e()]}}])}),t("el-table-column",{attrs:{label:"是否无缓存","show-overflow-tooltip":""},scopedSlots:e._u([{key:"default",fn:function({row:o}){return[o.meta?t("span",[e._v(" "+e._s(o.meta.noKeepAlive)+" ")]):e._e()]}}])}),t("el-table-column",{attrs:{label:"badge","show-overflow-tooltip":""},scopedSlots:e._u([{key:"default",fn:function({row:o}){return[o.meta?t("span",[e._v(" "+e._s(o.meta.badge)+" ")]):e._e()]}}])})],1)],1)],1)],1)},s=[],l=o("2f62"),r=o("f121"),n=o("2033"),i={name:"Permissions",data(){return{form:{account:""},tableData:[],res:[]}},computed:{...Object(l["c"])({username:"user/username",permissions:"user/permissions"})},created(){this.fetchData()},mounted(){this.form.account=this.username},methods:{handleChangePermission(){localStorage.setItem(r["tokenTableName"],this.form.account+"-accessToken"),location.reload()},async fetchData(){const e=await Object(n["getRouterList"])();this.tableData=e.data,this.res=e}}},m=i,c=o("2877"),p=Object(c["a"])(m,a,s,!1,null,null,null);t["default"]=p.exports}}]);