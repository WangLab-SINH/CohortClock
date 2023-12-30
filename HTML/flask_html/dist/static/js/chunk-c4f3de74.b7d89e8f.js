/*!
 *  build: vue-admin-better 
 *  vue-admin-beautiful.com 
 *  https://gitee.com/chu1204505056/vue-admin-better 
 *  time: 2023-8-14 16:38:22
 */
(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-c4f3de74"],{"23dc":function(e,t,l){"use strict";l.r(t);var s=function(){var e=this,t=e._self._c;return t("div",{staticClass:"tree-container"},[t("el-row",{attrs:{gutter:20}},[t("el-col",{attrs:{lg:6,md:24,sm:24,xl:6,xs:24}},[t("el-divider",{attrs:{"content-position":"left"}},[e._v("常规树")]),t("el-input",{attrs:{placeholder:"输入关键字过滤"},model:{value:e.filterText,callback:function(t){e.filterText=t},expression:"filterText"}}),t("el-tree",{ref:"demoTree",staticClass:"vab-filter-tree",attrs:{data:e.data2,"default-checked-keys":e.defaultCheckedKeys,"default-expanded-keys":e.defaultExpendedKeys,"expand-on-click-node":!1,"filter-node-method":e.filterNode,"highlight-current":!0,"node-key":"id",props:e.defaultProps,"show-checkbox":""},on:{check:e.checkNode,"node-click":e.nodeClick,"node-collapse":e.nodeCollapse,"node-expand":e.nodeExpand},scopedSlots:e._u([{key:"defalut",fn:function({node:l,data:s}){return[t("span",{staticClass:"vab-tree-item"},[4==l.data.rank?t("i",{staticClass:"el-icon-s-custom"}):e._e(),e._v(" "+e._s(l.label)+" ")]),t("span",{staticClass:"vab-tree-options"},[4!==l.data.rank?t("a",{staticClass:"vab-tree-btn",attrs:{title:"添加"},on:{click:()=>e.append(l,s,0)}},[t("i",{staticClass:"el-icon-plus"})]):e._e(),t("a",{staticClass:"vab-tree-btn",attrs:{title:"编辑"},on:{click:()=>e.edit(l,s,1)}},[t("i",{staticClass:"el-icon-edit"})]),1!==l.data.rank?t("a",{staticClass:"vab-tree-btn",attrs:{title:"刪除"},on:{click:()=>e.remove(l,s)}},[t("i",{staticClass:"el-icon-delete"})]):e._e()])]}}])})],1),t("el-col",{attrs:{lg:6,md:24,sm:24,xl:6,xs:24}},[t("el-divider",{attrs:{"content-position":"left"}},[e._v("懒加载树")]),t("el-input",{staticClass:"input-with-select",attrs:{placeholder:"请输入内容",value:e.keyW},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.showTreeList.apply(null,arguments)}},model:{value:e.keyW,callback:function(t){e.keyW=t},expression:"keyW"}}),t("div",{directives:[{name:"show",rawName:"v-show",value:e.isShow,expression:"isShow"}],staticClass:"blur-tree"},[t("el-tree",{ref:"treeFilter",staticClass:"vab-filter-tree",attrs:{data:e.filterDevLlist,"default-expand-all":"","expand-on-click-node":!1,"highlight-current":"","node-key":"indexCode",props:e.defaultProps},on:{"node-click":e.nodeClick},scopedSlots:e._u([{key:"defalut",fn:function({node:l}){return[t("span",{staticClass:"vab-tree-item"},[4==l.data.rank?t("i",{staticClass:"el-icon-s-custom"}):e._e(),e._v(" "+e._s(l.label)+" ")]),t("span",{staticClass:"vab-tree-options"},[4!==l.data.rank?t("a",{staticClass:"vab-tree-btn",attrs:{title:"添加"}},[t("i",{staticClass:"el-icon-plus"})]):e._e(),t("a",{staticClass:"vab-tree-btn",attrs:{title:"编辑"}},[t("i",{staticClass:"el-icon-edit"})]),1!==l.data.rank?t("a",{staticClass:"vab-tree-btn",attrs:{title:"刪除"}},[t("i",{staticClass:"el-icon-delete"})]):e._e()])]}}])})],1),t("div",{directives:[{name:"show",rawName:"v-show",value:!e.isShow,expression:"!isShow"}],staticClass:"el-tree-wrap"},[t("el-tree",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],ref:"tree",staticClass:"vab-filter-tree",attrs:{"expand-on-click-node":!1,"highlight-current":"",lazy:"",load:e.loadNode,"node-key":"indexCode",props:e.defaultProps},on:{"node-click":e.nodeClick},scopedSlots:e._u([{key:"defalut",fn:function({node:l}){return[t("span",{staticClass:"vab-tree-item"},[4==l.data.rank?t("i",{staticClass:"el-icon-s-custom"}):e._e(),e._v(" "+e._s(l.label)+" ")]),t("span",{staticClass:"vab-tree-options"},[t("a",{staticClass:"vab-tree-btn",attrs:{title:"编辑"}},[t("i",{staticClass:"el-icon-edit"})]),1!==l.data.rank?t("a",{staticClass:"vab-tree-btn",attrs:{title:"刪除"}},[t("i",{staticClass:"el-icon-delete"})]):e._e()])]}}])})],1)],1),t("el-col",{attrs:{lg:6,md:24,sm:24,xl:6,xs:24}},[t("el-divider",{attrs:{"content-position":"left"}},[e._v("单选树")]),t("el-select",{ref:"singleTree",staticClass:"vab-tree-select",attrs:{clearable:"","popper-class":"select-tree-popper","value-key":"id"},on:{clear:function(t){return e.selectTreeClearHandle("single")}},model:{value:e.singleSelectTreeVal,callback:function(t){e.singleSelectTreeVal=t},expression:"singleSelectTreeVal"}},[t("el-option",{attrs:{value:e.singleSelectTreeKey}},[t("el-tree",{ref:"singleSelectTree",attrs:{id:"singleSelectTree","current-node-key":e.singleSelectTreeKey,data:e.selectTreeData,"default-expanded-keys":e.selectTreeDefaultSelectedKeys,"highlight-current":!0,"node-key":"id",props:e.selectTreeDefaultProps},on:{"node-click":e.selectTreeNodeClick},scopedSlots:e._u([{key:"defalut",fn:function({node:l}){return[t("span",{staticClass:"vab-tree-item"},[e._v(e._s(l.label))])]}}])})],1)],1)],1),t("el-col",{attrs:{lg:6,md:24,sm:24,xl:6,xs:24}},[t("el-divider",{attrs:{"content-position":"left"}},[e._v("多选树")]),t("el-select",{staticClass:"vab-tree-select",attrs:{clearable:"","collapse-tags":"",multiple:"","popper-class":"select-tree-popper"},on:{change:e.changeMultipleSelectTreeHandle,clear:function(t){return e.selectTreeClearHandle("multiple")},"remove-tag":e.removeSelectTreeTag},model:{value:e.multipleSelectTreeVal,callback:function(t){e.multipleSelectTreeVal=t},expression:"multipleSelectTreeVal"}},[t("el-option",{attrs:{value:e.multipleSelectTreeKey}},[t("el-tree",{ref:"multipleSelectTree",attrs:{id:"multipleSelectTree","current-node-key":e.multipleSelectTreeKey,data:e.selectTreeData,"default-checked-keys":e.selectTreeDefaultSelectedKeys,"default-expanded-keys":e.selectTreeDefaultSelectedKeys,"highlight-current":!0,"node-key":"id",props:e.selectTreeDefaultProps,"show-checkbox":""},on:{check:e.multipleSelectTreeCheckNode}})],1)],1)],1)],1),t("el-dialog",{staticClass:"tree-operate-dialog",attrs:{title:e.dialogTitle,visible:e.treeDialogVisible,width:"400px"},on:{"update:visible":function(t){e.treeDialogVisible=t},close:function(t){e.treeDialogVisible=!1}}},[t("el-form",{ref:"treeForm",attrs:{model:e.treeForm}},[t("el-form-item",{attrs:{label:"节点名称",required:""}},[t("el-input",{model:{value:e.treeForm.name,callback:function(t){e.$set(e.treeForm,"name",t)},expression:"treeForm.name"}})],1)],1),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(t){e.treeDialogVisible=!1}}},[e._v("取 消")]),t("el-button",{attrs:{type:"primary"},on:{click:e.saveTree}},[e._v("确 定")])],1)],1)],1)},i=[],a=(l("14d9"),l("8610")),r={name:"Tree",data(){return{dialogTitle:"添加节点",treeFlag:0,treeDialogVisible:!1,treeForm:{id:"",name:""},checkNodeKeys:[],filterText:"",data2:[],defaultProps:{children:"children",label:"name"},defaultExpendedKeys:[],defaultCheckedKeys:[],loading:!0,keyW:"",filterDevLlist:[],isShow:!1,updateTree:!0,selectLevel:4,singleSelectTreeVal:"",singleSelectTreeKey:"",selectTreeData:[],selectTreeDefaultSelectedKeys:[],selectTreeDefaultProps:{children:"children",label:"name"},multipleSelectTreeVal:[],multipleSelectTreeKey:""}},watch:{filterText(e){this.$refs.demoTree.filter(e)}},mounted(){this.$nextTick(()=>{this.getTreeListFuc(1),this.setCheckedKeys(),this.initSingleTree("single"),this.initSingleTree("multiple")})},methods:{openTree(e,t){const l=e=>{e.forEach(e=>{e.rank<=t&&this.defaultExpendedKeys.push(e.id),e.children.length>0&&l(e.children)})};l(e)},async getTreeListFuc(e){const{data:t}=await Object(a["getTreeList"])();this.data2=t,e&&this.openTree(this.data2,2)},filterNode(e,t){return!e||-1!==t.name.indexOf(e)},append(e,t,l){this.treeFlag=l,this.dialogTitle="添加节点",this.treeForm={id:"",name:""},this.treeDialogVisible=!0},edit(e,t,l){this.treeFlag=l,this.dialogTitle="编辑节点",this.treeForm={id:t.id,name:t.name},this.treeDialogVisible=!0},remove(e,t){this.$baseConfirm("你确定要删除该节点?",null,async()=>{const{msg:e}=Object(a["getTreeList"])();this.$baseMessage(e,"success"),this.getTreeListFuc(0)})},saveTree(){this.$refs.treeForm.validate(async e=>{if(e){const{msg:e}=await Object(a["getTreeList"])();this.$baseMessage(e,"success"),this.treeDialogVisible=!1,this.getTreeListFuc(0)}})},setCheckedKeys(){this.$refs.demoTree.setCheckedKeys([1])},nodeClick(e,t,l){},checkNode(e,t,l){this.checkNodeKeys=t.checkedKeys},nodeExpand(e,t,l){this.defaultExpendedKeys.push(e.id)},nodeCollapse(e,t,l){this.defaultExpendedKeys.splice(this.defaultExpendedKeys.findIndex(t=>t.id===e.id),1)},async loadNode(e,t){if(0===e.level){const{data:e}=await Object(a["getTreeList"])();return this.loading=!1,t(e)}{const{data:e}=await Object(a["getTreeList"])();return t(res.data)}},async showTreeList(e){if("string"===typeof e&&(this.keyW=e.trim()),0!==this.keyW.length){let e={};e={keyWord:this.keyW};const{data:t}=await Object(a["getTreeList"])();this.filterDevLlist=t,this.isShow=!0}else this.isShow=!1},async initSingleTree(e){const{data:t}=await Object(a["getTreeList"])();this.selectTreeData=t,this.$nextTick(()=>{this.selectTreeDefaultSelectedKeys=this.singleSelectTreeKey.split(","),"single"==e?this.$refs.singleSelectTree.setCurrentKey(this.singleSelectTreeKey):this.$refs.multipleSelectTree.setCheckedKeys(this.selectTreeDefaultSelectedKeys)})},selectTreeClearHandle(e){this.selectTreeDefaultSelectedKeys=[],this.clearSelected(),"single"==e?(this.singleSelectTreeVal="",this.singleSelectTreeKey="",this.$refs.singleSelectTree.setCurrentKey("")):(this.multipleSelectTreeVal=[],this.multipleSelectTreeKey="",this.$refs.multipleSelectTree.setCheckedKeys([]))},clearSelected(){const e=document.querySelectorAll("#singleSelectTree .el-tree-node");e.forEach(e=>e.classList.remove("is-current"))},removeSelectTreeTag(e){const t=JSON.parse(JSON.stringify(this.selectTreeData));while(t.length){const l=t.shift();if(l.name==e)return this.$refs.multipleSelectTree.setChecked(l.id,!1);l.children&&l.children.length&&t.unshift(...l.children)}},changeMultipleSelectTreeHandle(e){},selectTreeNodeClick(e,t,l){e.rank>=this.selectLevel&&(this.singleSelectTreeVal=e.name,this.singleSelectTreeKey=e.id,this.$refs.singleTree.blur())},multipleSelectTreeCheckNode(e,t,l){const s=this.$refs.multipleSelectTree.getCheckedNodes(),i=[],a=[];s.forEach(e=>{e.rank>=this.selectLevel&&(i.push(e.id),a.push(e.name))}),this.multipleSelectTreeVal=a,this.multipleSelectTreeKey=i.join(",")}}},n=r,c=l("2877"),o=Object(c["a"])(n,s,i,!1,null,null,null);t["default"]=o.exports},8610:function(e,t,l){"use strict";l.r(t),l.d(t,"getTreeList",(function(){return i}));var s=l("b775");function i(e){return Object(s["default"])({url:"/tree/list",method:"post",data:e})}}}]);