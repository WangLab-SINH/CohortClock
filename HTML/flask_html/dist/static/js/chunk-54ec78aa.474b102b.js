/*!
 *  build: vue-admin-better 
 *  vue-admin-beautiful.com 
 *  https://gitee.com/chu1204505056/vue-admin-better 
 *  time: 2023-8-14 16:38:22
 */
(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-54ec78aa"],{"48e0":function(t,e,a){"use strict";a("a2e0")},"577a":function(t,e,a){"use strict";a.r(e);var n=function(){var t=this;t._self._c;return t._m(0)},s=[function(){var t=this,e=t._self._c;return e("div",{staticClass:"map-container"},[e("div",{staticClass:"container",attrs:{id:"map"}})])}],i=(a("14d9"),a("cee4")),r=a("fdf9"),o={name:"Map",components:{},data(){return{}},created(){},mounted(){this.$nextTick(()=>{const t=this.$baseMap();i["a"].get("https://fastly.jsdelivr.net/npm/mapv@2.0.12/examples/data/od-xierqi.txt").then(e=>{let a=[],n=[];e=e.data.split("\n");let s=0;for(let t=0;t<e.length;t++){let i=e[t].split(","),r=[];i.length>s&&(s=i.length);for(let t=0;t<i.length;t+=2){let e=Number(i[t])/20037508.34*180,a=Number(i[t+1])/20037508.34*180;a=180/Math.PI*(2*Math.atan(Math.exp(a*Math.PI/180))-Math.PI/2),0!=e&&NaN!=a&&(r.push([e,a]),n.push({geometry:{type:"Point",coordinates:[e,a]},count:1,time:t}))}a.push({geometry:{type:"LineString",coordinates:r}})}let i=new r["DataSet"](a),o={strokeStyle:"rgba(53,57,255,0.5)",shadowColor:"rgba(53,57,255,0.2)",shadowBlur:3,lineWidth:3,draw:"simple"},l=(new r["MaptalksLayer"]("mapv1",i,o).addTo(t),new r["DataSet"](n)),p={fillStyle:"rgba(255, 250, 250, 0.2)",globalCompositeOperation:"lighter",size:1.5,animation:{stepsRange:{start:0,end:100},trails:3,duration:5},draw:"simple"};new r["MaptalksLayer"]("mapv2",l,p).addTo(t)})})},methods:{}},l=o,p=(a("48e0"),a("2877")),d=Object(p["a"])(l,n,s,!1,null,"fbc757a0",null);e["default"]=d.exports},a2e0:function(t,e,a){}}]);