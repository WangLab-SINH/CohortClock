(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-index-index"],{"034b":function(t,i,e){"use strict";e.r(i);var a=e("11bf"),n=e.n(a);for(var s in a)["default"].indexOf(s)<0&&function(t){e.d(i,t,(function(){return a[t]}))}(s);i["default"]=n.a},"11bf":function(t,i,e){"use strict";e("7a82"),Object.defineProperty(i,"__esModule",{value:!0}),i.default=void 0;var a={data:function(){return{title:"Hello",list:[{backUrl:"../../static/ico_bg.png",title:"喝水记录",titleTip:"",subTip:"还要喝",subTitle:"16000",subTitleTip:"毫升",isShow:!0,isCenter:!1,isEdit:!1},{backUrl:"../../static/ico_bg.png",title:"饮食分析\n建议",titleTip:"",subTip:"",subTitle:"",subTitleTip:"",isShow:!0,isCenter:!0,isEdit:!1},{backUrl:"../../static/ico_bg.png",title:"便便记录",titleTip:"",subTip:"没有记录",subTitle:"",subTitleTip:"",isShow:!0,isCenter:!1,isEdit:!1},{backUrl:"../../static/ico_bg.png",title:"睡眠记录",titleTip:"",subTip:"平均",subTitle:"8.8",subTitleTip:"小时",isShow:!0,isCenter:!1,isEdit:!1},{backUrl:"../../static/ico_bg.png",title:"健康习惯",titleTip:"今天更新",subTip:"",subTitle:"1/6",subTitleTip:"",isShow:!0,isCenter:!1,isEdit:!1},{backUrl:"../../static/ico_bg.png",title:"运动训练",titleTip:"没有记录",subTip:"",subTitle:"",subTitleTip:"",isShow:!0,isCenter:!1,isEdit:!1},{backUrl:"../../static/ico_bg.png",title:"饮食日记",titleTip:"27天前更新",subTip:"今日未记录",subTitle:"",subTitleTip:"",isShow:!0,isCenter:!1,isEdit:!1},{backUrl:"../../static/ico_bg.png",title:"所有记录工",titleTip:"",subTip:"",subTitle:"",subTitleTip:"",isShow:!0,isCenter:!0,isEdit:!0}]}},onLoad:function(){},methods:{gotoActivity:function(t){console.log("aaaasdasasdasd"),nativeMethod.toActivity(t)}}};i.default=a},2833:function(t,i,e){var a=e("809b");a.__esModule&&(a=a.default),"string"===typeof a&&(a=[[t.i,a,""]]),a.locals&&(t.exports=a.locals);var n=e("4f06").default;n("c7475ab0",a,!0,{sourceMap:!1,shadowMode:!1})},2923:function(t,i,e){"use strict";e.d(i,"b",(function(){return a})),e.d(i,"c",(function(){return n})),e.d(i,"a",(function(){}));var a=function(){var t=this,i=t.$createElement,a=t._self._c||i;return a("v-uni-view",{staticClass:"content"},[a("v-uni-view",{staticClass:"top"},[a("v-uni-text",{staticClass:"text1"},[t._v("77.90")]),a("v-uni-view",{staticClass:"lef"},[a("v-uni-text",{staticClass:"aa"},[t._v("公斤")]),a("v-uni-image",{staticClass:"t_1",attrs:{src:e("3300")}})],1),a("v-uni-text",{staticStyle:{flex:"1"}}),a("v-uni-image",{staticClass:"t_2",attrs:{src:e("3300")}})],1),a("v-uni-view",{staticClass:"t_c"},t._l(t.list,(function(i,e){return a("v-uni-view",{key:e,class:i.isCenter?"t_r2":"t_r",style:{backgroundImage:"url("+i.backUrl+")"},on:{click:function(e){arguments[0]=e=t.$handleEvent(e),t.gotoActivity(i.title)}}},[a("v-uni-view",{staticClass:"t_cs"},[a("v-uni-text",{class:i.isEdit?"fonts2":"fontw"},[t._v(t._s(i.title))]),a("v-uni-text",{},[t._v(t._s(i.titleTip))])],1),a("v-uni-view",{staticClass:"t_jl"},[a("v-uni-text",{staticClass:"fonts"},[t._v(t._s(i.subTip))]),a("v-uni-text",{staticClass:"fontw"},[t._v(t._s(i.subTitle))]),a("v-uni-text",{staticClass:"fonts"},[t._v(t._s(i.subTitleTip))])],1)],1)})),1)],1)},n=[]},3300:function(t,i){t.exports="data:image/png;base64,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UmVhZHlxyWU8AAADKmlUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNS42LWMxMzIgNzkuMTU5Mjg0LCAyMDE2LzA0LzE5LTEzOjEzOjQwICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtbG5zOnhtcD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLyIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDpGRkE0MjcxNTdEQzYxMUU4QkZBOERDOEVCQ0U0NTBGMSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDpGRkE0MjcxNDdEQzYxMUU4QkZBOERDOEVCQ0U0NTBGMSIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ0MgMjAxNS41IChNYWNpbnRvc2gpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6QkE4RkFCN0M3REM1MTFFOEJGQThEQzhFQkNFNDUwRjEiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6QkE4RkFCN0Q3REM1MTFFOEJGQThEQzhFQkNFNDUwRjEiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz5BZZ+3AAAB1ElEQVR42mJkAALtmZb/GfAAJkIKwIoYiAA4FV1JO0Ylk0hWxILLHTgV6cyywqoIIIAYiQinb8S4iYs036E7esgEJq6ABAGAACImMBmo5m6yDcLlR5gcNnnaumhADWIhJoOTbRC+9ILPa9+o4TWAAAIlyDVAOphCc1SYqGAICNwZxumIidi8NILz2qhBdCyPaOcicgq1wRnYAAFErRKSgZo+GzSOoWpQD1sHsRCjCDnzkpp90DM+If2jUTbqoFEHjZZDpJYroyFESeNmNFHTykEqg8g9bwACCNRiVAYyLgEx1wA7Zu3V9OMhVBt1opajBlsaCh7NZaMOGnXQgFeupHZjKO1CjUbZqINGHTTqoFEHjTpo1EGjDhqMgw342kejUTaahggpoOdg1WiUjTpoODoIvL7tzSBykB5AgPbtGIdBGIYCaBR16swROEQvzT06cxjm1lRFDC0LcpXC+xJzpIdJhOW8e4z359MVWSde1C32xRYasC0mCmascDZzrQz+7NgABAgQINnRY/iUrb5D9v9l9toqCBAgQIAAAQIESAABAgQIEKCD5ZK9QPaMigoCdIJP7NdjOyoIECBAgGQBGjB8zVDjam153T0OqInJbBAWfdg8AExKZVcA71uIAAAAAElFTkSuQmCC"},"809b":function(t,i,e){var a=e("24fb");i=a(!1),i.push([t.i,'uni-page-body[data-v-0af7d2df]{background-color:#f5f6fa}body.?%PAGE?%[data-v-0af7d2df]{background-color:#f5f6fa}.content[data-v-0af7d2df]{margin-top:%?80?%}.top[data-v-0af7d2df]{background-color:#fff;margin:%?20?%;border-radius:%?20?%;padding:%?30?% %?20?%}.top[data-v-0af7d2df]{display:flex;flex-direction:row;justify-content:flex-start}.text1[data-v-0af7d2df]{font-weight:800;font-size:%?40?%;height:%?40?%}.aa[data-v-0af7d2df]{font-size:%?28?%;color:#999;margin-left:%?10?%;margin-right:%?10?%;line-height:%?40?%;height:%?40?%;text-align:center}.t_1[data-v-0af7d2df]{width:%?30?%;height:%?18?%;margin-bottom:%?10?%}.t_2[data-v-0af7d2df]{width:%?300?%;height:%?55?%}.lef[data-v-0af7d2df]{display:flex;flex-direction:row;justify-content:center;align-items:center}.t_c[data-v-0af7d2df]{display:flex;flex-direction:row;margin:%?20?%;justify-content:space-between;flex-wrap:wrap}.t_r[data-v-0af7d2df]{width:49%;background-color:#fff;height:%?260?%;padding:%?30?%;border-radius:%?20?%;display:flex;flex-direction:column;justify-content:space-between;\n\t/* background-image: url("../../static/ico_bg.png"); */background-size:100% 100%;background-repeat:no-repeat;box-sizing:border-box;margin-top:%?10?%}.t_r2[data-v-0af7d2df]{width:49%;background-color:#fff;height:%?260?%;padding:%?30?%;border-radius:%?20?%;display:flex;flex-direction:column;justify-content:flex-start;\n\t/* background-image: url("../../static/ico_bg.png"); */background-size:100% 100%;background-repeat:no-repeat;align-items:flex-start;padding-top:%?80?%;box-sizing:border-box;margin-top:%?10?%}.t_r3[data-v-0af7d2df]{width:49%;background-color:#fff;height:%?260?%;padding:%?30?%;border-radius:%?20?%;display:flex;flex-direction:row;justify-content:center;background-size:100% 100%;background-repeat:no-repeat;align-items:center;padding-top:%?80?%;box-sizing:border-box}.edit_pa[data-v-0af7d2df]{width:%?30?%;height:%?30?%;margin-right:%?10?%}.t_jl[data-v-0af7d2df]{display:flex;flex-direction:row;margin-bottom:%?20?%;justify-content:flex-start;align-items:center}.fontw[data-v-0af7d2df]{font-size:%?34?%;color:#000;font-weight:800}.fonts[data-v-0af7d2df]{font-size:%?28?%;color:#999}.fonts2[data-v-0af7d2df]{font-size:%?30?%;color:#76bf9c;font-weight:800}.t_cs[data-v-0af7d2df]{display:flex;flex-direction:column}.t_cs uni-text[data-v-0af7d2df]:nth-child(even){font-size:%?26?%}',""]),t.exports=i},d75f:function(t,i,e){"use strict";var a=e("2833"),n=e.n(a);n.a},e9b6:function(t,i,e){"use strict";e.r(i);var a=e("2923"),n=e("034b");for(var s in n)["default"].indexOf(s)<0&&function(t){e.d(i,t,(function(){return n[t]}))}(s);e("d75f");var c=e("f0c5"),d=Object(c["a"])(n["default"],a["b"],a["c"],!1,null,"0af7d2df",null,!1,a["a"],void 0);i["default"]=d.exports}}]);