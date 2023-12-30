/*!
 *  build: vue-admin-better 
 *  vue-admin-beautiful.com 
 *  https://gitee.com/chu1204505056/vue-admin-better 
 *  time: 2023-12-29 21:50:54
 */
(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0322c879"],{"9ed6":function(s,t,e){"use strict";e.r(t);var r=function(){var s=this,t=s._self._c;return t("div",{staticClass:"login-container"},[t("el-alert",{staticStyle:{position:"fixed"},attrs:{closable:!1,title:"beautiful boys and girls欢迎加入vue-admin-beautifulQQ群：972435319",type:"success"}}),t("el-row",[t("el-col",{attrs:{lg:16,md:12,sm:24,xl:16,xs:24}},[t("div",{staticStyle:{color:"transparent"}},[s._v("占位符")])]),t("el-col",{attrs:{lg:8,md:12,sm:24,xl:8,xs:24}},[t("el-form",{ref:"form",staticClass:"login-form",attrs:{"label-position":"left",model:s.form,rules:s.rules}},[t("div",{staticClass:"title"},[s._v("hello !")]),t("div",{staticClass:"title-tips"},[s._v("欢迎来到"+s._s(s.title)+"！")]),t("el-form-item",{staticStyle:{"margin-top":"40px"},attrs:{prop:"username"}},[t("span",{staticClass:"svg-container svg-container-admin"},[t("vab-icon",{attrs:{icon:["fas","user"]}})],1),t("el-input",{directives:[{name:"focus",rawName:"v-focus"}],attrs:{placeholder:"请输入用户名",tabindex:"1",type:"text"},model:{value:s.form.username,callback:function(t){s.$set(s.form,"username","string"===typeof t?t.trim():t)},expression:"form.username"}})],1),t("el-form-item",{attrs:{prop:"password"}},[t("span",{staticClass:"svg-container"},[t("vab-icon",{attrs:{icon:["fas","lock"]}})],1),t("el-input",{key:s.passwordType,ref:"password",attrs:{placeholder:"请输入密码",tabindex:"2",type:s.passwordType},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&s._k(t.keyCode,"enter",13,t.key,"Enter")?null:s.handleLogin.apply(null,arguments)}},model:{value:s.form.password,callback:function(t){s.$set(s.form,"password","string"===typeof t?t.trim():t)},expression:"form.password"}}),"password"===s.passwordType?t("span",{staticClass:"show-password",on:{click:s.handlePassword}},[t("vab-icon",{attrs:{icon:["fas","eye-slash"]}})],1):t("span",{staticClass:"show-password",on:{click:s.handlePassword}},[t("vab-icon",{attrs:{icon:["fas","eye"]}})],1)],1),t("el-button",{staticClass:"login-btn",attrs:{loading:s.loading,type:"primary"},on:{click:s.handleLogin}},[s._v(" 登录 ")]),t("router-link",{attrs:{to:"/register"}},[t("div",{staticStyle:{"margin-top":"20px"}},[s._v("注册")])])],1)],1)],1)],1)},a=[],o=(e("d9e2"),e("14d9"),e("61f7")),i={name:"Login",directives:{focus:{inserted(s){s.querySelector("input").focus()}}},data(){const s=(s,t,e)=>{""==t?e(new Error("用户名不能为空")):e()},t=(s,t,e)=>{Object(o["isPassword"])(t)?e():e(new Error("密码不能少于6位"))};return{nodeEnv:"production",title:this.$baseTitle,form:{username:"",password:""},rules:{username:[{required:!0,trigger:"blur",validator:s}],password:[{required:!0,trigger:"blur",validator:t}]},loading:!1,passwordType:"password",redirect:void 0}},watch:{$route:{handler(s){this.redirect=s.query&&s.query.redirect||"/"},immediate:!0}},created(){document.body.style.overflow="hidden"},beforeDestroy(){document.body.style.overflow="auto"},mounted(){this.form.username="admin",this.form.password="123456",setTimeout(()=>{this.handleLogin()},3e3)},methods:{handlePassword(){"password"===this.passwordType?this.passwordType="":this.passwordType="password",this.$nextTick(()=>{this.$refs.password.focus()})},handleLogin(){this.$refs.form.validate(s=>{if(!s)return!1;this.loading=!0,this.$store.dispatch("user/login",this.form).then(()=>{const s="/404"===this.redirect||"/401"===this.redirect?"/":this.redirect;this.$router.push(s).catch(()=>{}),this.loading=!1}).catch(()=>{this.loading=!1})})}}},n=i,l=(e("fc29"),e("2877")),d=Object(l["a"])(n,r,a,!1,null,"29105a1c",null);t["default"]=d.exports},aa0b:function(s,t,e){},fc29:function(s,t,e){"use strict";e("aa0b")}}]);