(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-index-index"],{"034b":function(t,e,i){"use strict";i.r(e);var a=i("11bf"),n=i.n(a);for(var s in a)["default"].indexOf(s)<0&&function(t){i.d(e,t,(function(){return a[t]}))}(s);e["default"]=n.a},"11bf":function(t,e,i){"use strict";i("7a82"),Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var a={data:function(){return{title:"Hello",list:[{backUrl:"../../static/ico_bg.png",title:"Blood Pressure",titleTip:"",subTip:"",subTitle:"",subTitleTip:"",isShow:!0,isCenter:!0,isEdit:!1},{backUrl:"../../static/ico_bg.png",title:"Heart Rate",titleTip:"",subTip:"",subTitle:"",subTitleTip:"",isShow:!0,isCenter:!0,isEdit:!1},{backUrl:"../../static/ico_bg.png",title:"Waist Circumference",titleTip:"",subTip:"",subTitle:"",subTitleTip:"",isShow:!0,isCenter:!0,isEdit:!1},{backUrl:"../../static/ico_bg.png",title:"Exercise Duration",titleTip:"",subTip:"",subTitle:"",subTitleTip:"",isShow:!0,isCenter:!0,isEdit:!1},{backUrl:"../../static/ico_bg.png",title:"Height",titleTip:"",subTip:"",subTitle:"",subTitleTip:"",isShow:!0,isCenter:!0,isEdit:!1},{backUrl:"../../static/ico_bg.png",title:"Temperature",titleTip:"",subTip:"",subTitle:"",subTitleTip:"",isShow:!0,isCenter:!0,isEdit:!1},{backUrl:"../../static/ico_bg.png",title:"Medicine",titleTip:"",subTip:"",subTitle:"",subTitleTip:"",isShow:!0,isCenter:!0,isEdit:!1},{backUrl:"../../static/ico_bg.png",title:"Disease",titleTip:"",subTip:"",subTitle:"",subTitleTip:"",isShow:!0,isCenter:!0,isEdit:!1}]}},onLoad:function(){},methods:{gotoActivity:function(t){console.log("aaaasdasasdasd"),nativeMethod.toActivity(t)}}};e.default=a},"545a":function(t,e,i){"use strict";var a=i("eb41"),n=i.n(a);n.a},c6f4:function(t,e,i){var a=i("24fb");e=a(!1),e.push([t.i,'uni-page-body[data-v-5eeb99b6]{background-color:#f5f6fa}body.?%PAGE?%[data-v-5eeb99b6]{background-color:#f5f6fa}.content[data-v-5eeb99b6]{margin-top:%?80?%}.top[data-v-5eeb99b6]{background-color:#fff;margin:%?20?%;border-radius:%?20?%;padding:%?30?% %?20?%}.top[data-v-5eeb99b6]{display:flex;flex-direction:row;justify-content:flex-start}.text1[data-v-5eeb99b6]{font-weight:800;font-size:%?40?%;height:%?40?%}.aa[data-v-5eeb99b6]{font-size:%?28?%;color:#999;margin-left:%?10?%;margin-right:%?10?%;line-height:%?40?%;height:%?40?%;text-align:center}.t_1[data-v-5eeb99b6]{width:%?30?%;height:%?18?%;margin-bottom:%?10?%}.t_2[data-v-5eeb99b6]{width:%?300?%;height:%?55?%}.lef[data-v-5eeb99b6]{display:flex;flex-direction:row;justify-content:center;align-items:center}.t_c[data-v-5eeb99b6]{display:flex;flex-direction:row;margin:%?20?%;justify-content:space-between;flex-wrap:wrap}.t_r[data-v-5eeb99b6]{width:49%;background-color:#fff;height:%?260?%;padding:%?30?%;border-radius:%?20?%;display:flex;flex-direction:column;justify-content:space-between;\n\t/* background-image: url("../../static/ico_bg.png"); */background-size:100% 100%;background-repeat:no-repeat;box-sizing:border-box;margin-top:%?10?%}.t_r2[data-v-5eeb99b6]{width:49%;background-color:#fff;height:%?260?%;padding:%?30?%;border-radius:%?20?%;display:flex;flex-direction:column;justify-content:flex-start;\n\t/* background-image: url("../../static/ico_bg.png"); */background-size:100% 100%;background-repeat:no-repeat;align-items:flex-start;padding-top:%?80?%;box-sizing:border-box;margin-top:%?10?%}.t_r3[data-v-5eeb99b6]{width:49%;background-color:#fff;height:%?260?%;padding:%?30?%;border-radius:%?20?%;display:flex;flex-direction:row;justify-content:center;background-size:100% 100%;background-repeat:no-repeat;align-items:center;padding-top:%?80?%;box-sizing:border-box}.edit_pa[data-v-5eeb99b6]{width:%?30?%;height:%?30?%;margin-right:%?10?%}.t_jl[data-v-5eeb99b6]{display:flex;flex-direction:row;margin-bottom:%?20?%;justify-content:flex-start;align-items:center}.fontw[data-v-5eeb99b6]{font-size:%?34?%;color:#000;font-weight:800}.fonts[data-v-5eeb99b6]{font-size:%?28?%;color:#999}.fonts2[data-v-5eeb99b6]{font-size:%?30?%;color:#76bf9c;font-weight:800}.t_cs[data-v-5eeb99b6]{display:flex;flex-direction:column}.t_cs uni-text[data-v-5eeb99b6]:nth-child(even){font-size:%?26?%}',""]),t.exports=e},e609:function(t,e,i){"use strict";i.d(e,"b",(function(){return a})),i.d(e,"c",(function(){return n})),i.d(e,"a",(function(){}));var a=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("v-uni-view",{staticClass:"content"},[i("v-uni-view",{staticClass:"t_c"},t._l(t.list,(function(e,a){return i("v-uni-view",{key:a,class:e.isCenter?"t_r2":"t_r",style:{backgroundImage:"url("+e.backUrl+")"},on:{click:function(i){arguments[0]=i=t.$handleEvent(i),t.gotoActivity(e.title)}}},[i("v-uni-view",{staticClass:"t_cs"},[i("v-uni-text",{class:e.isEdit?"fonts2":"fontw"},[t._v(t._s(e.title))]),i("v-uni-text",{},[t._v(t._s(e.titleTip))])],1),i("v-uni-view",{staticClass:"t_jl"},[i("v-uni-text",{staticClass:"fonts"},[t._v(t._s(e.subTip))]),i("v-uni-text",{staticClass:"fontw"},[t._v(t._s(e.subTitle))]),i("v-uni-text",{staticClass:"fonts"},[t._v(t._s(e.subTitleTip))])],1)],1)})),1)],1)},n=[]},e9b6:function(t,e,i){"use strict";i.r(e);var a=i("e609"),n=i("034b");for(var s in n)["default"].indexOf(s)<0&&function(t){i.d(e,t,(function(){return n[t]}))}(s);i("545a");var o=i("f0c5"),r=Object(o["a"])(n["default"],a["b"],a["c"],!1,null,"5eeb99b6",null,!1,a["a"],void 0);e["default"]=r.exports},eb41:function(t,e,i){var a=i("c6f4");a.__esModule&&(a=a.default),"string"===typeof a&&(a=[[t.i,a,""]]),a.locals&&(t.exports=a.locals);var n=i("4f06").default;n("5b40da12",a,!0,{sourceMap:!1,shadowMode:!1})}}]);