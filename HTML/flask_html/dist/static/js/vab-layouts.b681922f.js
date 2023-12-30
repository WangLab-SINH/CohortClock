/*!
 *  build: vue-admin-better 
 *  vue-admin-beautiful.com 
 *  https://gitee.com/chu1204505056/vue-admin-better 
 *  time: 2023-10-25 19:04:59
 */
(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["vab-layouts"],{1975:function(e,t,a){"use strict";a("52a6")},"1e68":function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e._self._c;return t("div",{staticClass:"nav-bar-container"},[t("el-row",{attrs:{gutter:15}},[t("el-col",{attrs:{lg:12,md:12,sm:12,xl:12,xs:4}},[t("div",{staticClass:"left-panel"},[t("i",{staticClass:"fold-unfold",class:e.collapse?"el-icon-s-unfold":"el-icon-s-fold",attrs:{title:e.collapse?"展开":"收起"},on:{click:e.handleCollapse}}),t("vab-breadcrumb",{staticClass:"hidden-xs-only"})],1)])],1)],1)},i=[],n=a("2f62"),o={name:"VabNavBar",data(){return{pulse:!1}},computed:{...Object(n["c"])({collapse:"settings/collapse",visitedRoutes:"tabsBar/visitedRoutes",device:"settings/device",routes:"routes/routes"})},methods:{...Object(n["b"])({changeCollapse:"settings/changeCollapse"}),handleCollapse(){this.changeCollapse()},async refreshRoute(){this.$baseEventBus.$emit("reload-router-view"),this.pulse=!0,setTimeout(()=>{this.pulse=!1},1e3)}}},r=o,l=(a("1975"),a("2877")),c=Object(l["a"])(r,s,i,!1,null,"a59cb3e0",null);t["default"]=c.exports},"26c7":function(e,t,a){},"2c5b":function(e,t,a){},3673:function(e,t,a){"use strict";a("53a0")},"397a":function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e._self._c;return e.routerView?t("div",{staticClass:"app-main-container"},[t("transition",{attrs:{mode:"out-in",name:"fade-transform"}},[t("keep-alive",{attrs:{include:e.cachedRoutes,max:e.keepAliveMaxNum}},[t("router-view",{key:e.key,staticClass:"app-main-height"})],1)],1),t("footer",{directives:[{name:"show",rawName:"v-show",value:e.footerCopyright,expression:"footerCopyright"}],staticClass:"footer-copyright"},[e._v(" Copyright "),t("vab-icon",{attrs:{icon:["fas","copyright"]}}),e._v(" DietClock "+e._s(e.fullYear)+" ")],1)],1):e._e()},i=[],n=(a("14d9"),a("2f62")),o=a("f121"),r={name:"VabAppMain",data(){return{show:!1,fullYear:(new Date).getFullYear(),copyright:o["copyright"],title:o["title"],keepAliveMaxNum:o["keepAliveMaxNum"],routerView:!0,footerCopyright:o["footerCopyright"]}},computed:{...Object(n["c"])({visitedRoutes:"tabsBar/visitedRoutes",device:"settings/device"}),cachedRoutes(){const e=[];return this.visitedRoutes.forEach(t=>{t.meta.noKeepAlive||e.push(t.name)}),e},key(){return this.$route.path}},watch:{$route:{handler(e){"mobile"===this.device&&this.foldSideBar()},immediate:!0}},created(){this.$baseEventBus.$on("reload-router-view",()=>{this.routerView=!1,this.$nextTick(()=>{this.routerView=!0})})},mounted(){},methods:{...Object(n["b"])({foldSideBar:"settings/foldSideBar"})}},l=r,c=(a("5566"),a("2877")),d=Object(c["a"])(l,s,i,!1,null,"39397ffe",null);t["default"]=d.exports},"3ab0":function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e._self._c;return t("div",{staticClass:"vue-admin-beautiful-wrapper",class:e.classObj},["horizontal"===e.layout?t("div",{staticClass:"layout-container-horizontal",class:{fixed:"fixed"===e.header,"no-tabs-bar":"false"===e.tabsBar||!1===e.tabsBar}},[t("div",{class:"fixed"===e.header?"fixed-header":""},[t("vab-top-bar"),"true"===e.tabsBar||!0===e.tabsBar?t("div",{class:{"tag-view-show":e.tabsBar}},[t("div",{staticClass:"vab-main"},[t("vab-tabs-bar")],1)]):e._e()],1),t("div",{staticClass:"vab-main main-padding"},[t("vab-ad"),t("vab-app-main")],1)]):t("div",{staticClass:"layout-container-vertical",class:{fixed:"fixed"===e.header,"no-tabs-bar":"false"===e.tabsBar||!1===e.tabsBar}},["mobile"===e.device&&!1===e.collapse?t("div",{staticClass:"mask",on:{click:e.handleFoldSideBar}}):e._e(),t("vab-side-bar"),t("div",{staticClass:"vab-main",class:e.collapse?"is-collapse-main":""},[t("div",{class:"fixed"===e.header?"fixed-header":""},[t("vab-nav-bar"),"true"===e.tabsBar||!0===e.tabsBar?t("vab-tabs-bar"):e._e()],1),t("vab-ad"),t("vab-app-main")],1)],1),t("el-backtop")],1)},i=[],n=a("2f62"),o=a("f121"),r={name:"Layout",data(){return{oldLayout:""}},computed:{...Object(n["c"])({layout:"settings/layout",tabsBar:"settings/tabsBar",collapse:"settings/collapse",header:"settings/header",device:"settings/device"}),classObj(){return{mobile:"mobile"===this.device}}},beforeMount(){window.addEventListener("resize",this.handleResize)},beforeDestroy(){window.removeEventListener("resize",this.handleResize)},mounted(){this.oldLayout=this.layout;const e=navigator.userAgent;e.includes("Juejin")&&this.$baseAlert("vue-admin-beautiful不支持在掘金内置浏览器演示，请手动复制以下地址到浏览器中查看http://mpfhrd48.sanxing.uz7.cn/vue-admin-beautiful");const t=this.handleIsMobile();t?(t?this.$store.dispatch("settings/changeLayout","vertical"):this.$store.dispatch("settings/changeLayout",this.oldLayout),this.$store.dispatch("settings/toggleDevice","mobile"),setTimeout(()=>{this.$store.dispatch("settings/foldSideBar")},2e3)):this.$store.dispatch("settings/openSideBar"),this.$nextTick(()=>{window.addEventListener("storage",e=>{e.key!==o["tokenName"]&&null!==e.key||window.location.reload(),e.key===o["tokenName"]&&null===e.value&&window.location.reload()},!1)})},methods:{...Object(n["b"])({handleFoldSideBar:"settings/foldSideBar"}),handleIsMobile(){return document.body.getBoundingClientRect().width-1<992},handleResize(){if(!document.hidden){const e=this.handleIsMobile();e?this.$store.dispatch("settings/changeLayout","vertical"):this.$store.dispatch("settings/changeLayout",this.oldLayout),this.$store.dispatch("settings/toggleDevice",e?"mobile":"desktop")}}}},l=r,c=(a("3673"),a("2877")),d=Object(c["a"])(l,s,i,!1,null,"d626f336",null);t["default"]=d.exports},"444f":function(e,t,a){},"48f0":function(e,t,a){"use strict";a("444f")},"52a6":function(e,t,a){},"53a0":function(e,t,a){},5566:function(e,t,a){"use strict";a("2c5b")},"5bd3":function(e,t,a){"use strict";a("b2de")},"5f5f":function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e._self._c;return t("router-view")},i=[],n=a("2877"),o={},r=Object(n["a"])(o,s,i,!1,null,null,null);t["default"]=r.exports},6997:function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e._self._c;return t("el-breadcrumb",{staticClass:"breadcrumb-container",attrs:{separator:">"}},e._l(e.list,(function(a){return t("el-breadcrumb-item",{key:a.path},[e._v(" "+e._s(a.meta.title)+" ")])})),1)},i=[],n={name:"VabBreadcrumb",data(){return{list:this.getBreadcrumb()}},watch:{$route(){this.list=this.getBreadcrumb()}},methods:{getBreadcrumb(){return this.$route.matched.filter(e=>e.name&&e.meta.title)}}},o=n,r=(a("a056"),a("2877")),l=Object(r["a"])(o,s,i,!1,null,"2b4cacba",null);t["default"]=l.exports},"803c":function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e._self._c;return t("div")},i=[],n=a("2f62"),o=a("f121"),r={name:"VabThemeBar",data(){return{drawerVisible:!1,theme:{name:"default",layout:"",header:"fixed",tabsBar:""}}},computed:{...Object(n["c"])({layout:"settings/layout",header:"settings/header",tabsBar:"settings/tabsBar",themeBar:"settings/themeBar"})},created(){this.$baseEventBus.$on("theme",()=>{this.handleOpenThemeBar()});const e=localStorage.getItem("vue-admin-beautiful-theme");null!==e?(this.theme=JSON.parse(e),this.handleSetTheme()):(this.theme.layout=this.layout,this.theme.header=this.header,this.theme.tabsBar=this.tabsBar)},methods:{...Object(n["b"])({changeLayout:"settings/changeLayout",changeHeader:"settings/changeHeader",changeTabsBar:"settings/changeTabsBar"}),handleIsMobile(){return document.body.getBoundingClientRect().width-1<992},handleOpenThemeBar(){this.drawerVisible=!0},handleSetTheme(){let{name:e,layout:t,header:a,tabsBar:s}=this.theme;localStorage.setItem("vue-admin-beautiful-theme",`{\n            "name":"${e}",\n            "layout":"${t}",\n            "header":"${a}",\n            "tabsBar":"${s}"\n          }`),this.handleIsMobile()||this.changeLayout(t),this.changeHeader(a),this.changeTabsBar(s),document.getElementsByTagName("body")[0].className="vue-admin-beautiful-theme-"+e,this.drawerVisible=!1},handleSaveTheme(){this.handleSetTheme()},handleSetDfaultTheme(){let{name:e}=this.theme;document.getElementsByTagName("body")[0].classList.remove("vue-admin-beautiful-theme-"+e),localStorage.removeItem("vue-admin-beautiful-theme"),this.$refs["form"].resetFields(),Object.assign(this.$data,this.$options.data()),this.changeHeader(o["layout"]),this.theme.name="default",this.theme.layout=this.layout,this.theme.header=this.header,this.theme.tabsBar=this.tabsBar,this.drawerVisible=!1,location.reload()},handleGetCode(){const e="https://github.com/chuzhixin/vue-admin-beautiful/tree/master/src/views";let t=this.$route.path+"/index.vue";"/vab/menu1/menu1-1/menu1-1-1/index.vue"===t&&(t="/vab/nested/menu1/menu1-1/menu1-1-1/index.vue"),"/vab/icon/awesomeIcon/index.vue"===t&&(t="/vab/icon/index.vue"),"/vab/icon/remixIcon/index.vue"===t&&(t="/vab/icon/remixIcon.vue"),"/vab/icon/colorfulIcon/index.vue"===t&&(t="/vab/icon/colorfulIcon.vue"),"/vab/table/comprehensiveTable/index.vue"===t&&(t="/vab/table/index.vue"),"/vab/table/inlineEditTable/index.vue"===t&&(t="/vab/table/inlineEditTable.vue"),window.open(e+t)}}},l=r,c=(a("c0bf"),a("a6bd"),a("2877")),d=Object(c["a"])(l,s,i,!1,null,"3dc262bc",null);t["default"]=d.exports},8054:function(e,t,a){},"84c8":function(e,t,a){"use strict";a.r(t);var s=a("a026");const i=a("f192");i.keys().forEach(e=>{const t=i(e),a=t.default.name;s["default"].component(a,t.default||t)});const n=a("fae7");n.keys().forEach(e=>{const t=n(e),a=t.default.name;s["default"].component(a,t.default||t)});const o=a("1654");o.keys().forEach(e=>{a("e8cc")("./"+e.slice(2))})},a056:function(e,t,a){"use strict";a("8054")},a67d:function(e,t,a){"use strict";a("26c7")},a6bd:function(e,t,a){"use strict";a("ae4f")},ab5f:function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e._self._c;return t("div",{class:"logo-container-"+e.layout},[t("router-link",{attrs:{to:"/"}},[e.logo?t("vab-remix-icon",{staticClass:"logo",attrs:{"icon-class":e.logo}}):e._e(),t("span",{staticClass:"title",class:{"hidden-xs-only":"horizontal"===e.layout},attrs:{title:e.title}},[e._v(" "+e._s(e.title)+" ")])],1)],1)},i=[],n=a("2f62"),o={name:"VabLogo",data(){return{title:this.$baseTitle}},computed:{...Object(n["c"])({logo:"settings/logo",layout:"settings/layout"})}},r=o,l=(a("48f0"),a("2877")),c=Object(l["a"])(r,s,i,!1,null,"87ad42d0",null);t["default"]=c.exports},abf6:function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e._self._c;return t("div")},i=[],n=a("ff02"),o={name:"VabAd",data(){return{nodeEnv:"production",adList:[]}},created(){this.fetchData()},methods:{async fetchData(){const{data:e}=await Object(n["getList"])();this.adList=e}}},r=o,l=(a("a67d"),a("2877")),c=Object(l["a"])(r,s,i,!1,null,"5a24c007",null);t["default"]=c.exports},ae4f:function(e,t,a){e.exports={"menu-color":"rgba(255,255,255,.95)","menu-color-active":"rgba(255,255,255,.95)","menu-background":"#21252b"}},b2de:function(e,t,a){},c0bf:function(e,t,a){"use strict";a("fd37")},cbe4:function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e._self._c;return t("el-dropdown",{on:{command:e.handleCommand}},[t("span",{staticClass:"avatar-dropdown"},[t("img",{staticClass:"user-avatar",attrs:{src:e.avatar,alt:""}}),t("div",{staticClass:"user-name"},[e._v(" "+e._s(e.username)+" "),t("i",{staticClass:"el-icon-arrow-down el-icon--right"})])]),t("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[t("el-dropdown-item",{attrs:{command:"github"}},[e._v("github地址")]),t("el-dropdown-item",{attrs:{command:"gitee",divided:""}},[e._v("码云地址")]),t("el-dropdown-item",{attrs:{command:"pro",divided:""}},[e._v("pro付费版地址")]),t("el-dropdown-item",{attrs:{command:"plus",divided:""}},[e._v("plus付费版地址")]),t("el-dropdown-item",{attrs:{command:"shop",divided:""}},[e._v(" shop-vite付费版地址 ")]),t("el-dropdown-item",{attrs:{command:"logout",divided:""}},[e._v("退出登录")])],1)],1)},i=[],n=(a("14d9"),a("2f62")),o=a("f121"),r={name:"VabAvatar",computed:{...Object(n["c"])({avatar:"user/avatar",username:"user/username"})},methods:{handleCommand(e){switch(e){case"logout":this.logout();break;case"personalCenter":this.personalCenter();break;case"github":window.open("https://github.com/chuzhixin/vue-admin-better");break;case"gitee":window.open("https://gitee.com/chu1204505056/vue-admin-better");break;case"pro":window.open("https://vue-admin-beautiful.com/admin-pro/?hmsr=homeAd&hmpl=&hmcu=&hmkw=&hmci=");break;case"plus":window.open("https://vue-admin-beautiful.com/admin-plus/?hmsr=homeAd&hmpl=&hmcu=&hmkw=&hmci=");case"shop":window.open("https://vue-admin-beautiful.com/shop-vite/?hmsr=homeAd&hmpl=&hmcu=&hmkw=&hmci=")}},personalCenter(){this.$router.push("/personalCenter/personalCenter")},logout(){this.$baseConfirm("您确定要退出"+this.$baseTitle+"吗?",null,async()=>{if(await this.$store.dispatch("user/logout"),o["recordRoute"]){const e=this.$route.fullPath;this.$router.push("/login?redirect="+e)}else this.$router.push("/login")})}}},l=r,c=(a("5bd3"),a("2877")),d=Object(c["a"])(l,s,i,!1,null,"2bbde598",null);t["default"]=d.exports},fd37:function(e,t,a){}}]);