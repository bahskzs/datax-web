(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1b8e0e35"],{"333d":function(e,t,a){"use strict";var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"pagination-container",class:{hidden:e.hidden}},[a("el-pagination",e._b({attrs:{background:e.background,"current-page":e.currentPage,"page-size":e.pageSize,layout:e.layout,"page-sizes":e.pageSizes,total:e.total},on:{"update:currentPage":function(t){e.currentPage=t},"update:current-page":function(t){e.currentPage=t},"update:pageSize":function(t){e.pageSize=t},"update:page-size":function(t){e.pageSize=t},"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}},"el-pagination",e.$attrs,!1))],1)},l=[];a("c5f6");Math.easeInOutQuad=function(e,t,a,i){return e/=i/2,e<1?a/2*e*e+t:(e--,-a/2*(e*(e-2)-1)+t)};var n=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(e){window.setTimeout(e,1e3/60)}}();function r(e){document.documentElement.scrollTop=e,document.body.parentNode.scrollTop=e,document.body.scrollTop=e}function s(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function o(e,t,a){var i=s(),l=e-i,o=20,u=0;t="undefined"===typeof t?500:t;var c=function e(){u+=o;var s=Math.easeInOutQuad(u,i,l,t);r(s),u<t?n(e):a&&"function"===typeof a&&a()};c()}var u={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(e){this.$emit("update:page",e)}},pageSize:{get:function(){return this.limit},set:function(e){this.$emit("update:limit",e)}}},methods:{handleSizeChange:function(e){this.$emit("pagination",{page:this.currentPage,limit:e}),this.autoScroll&&o(0,800)},handleCurrentChange:function(e){this.$emit("pagination",{page:e,limit:this.pageSize}),this.autoScroll&&o(0,800)}}},c=u,d=(a("5660"),a("2877")),p=Object(d["a"])(c,i,l,!1,null,"6af373ef",null);t["a"]=p.exports},5660:function(e,t,a){"use strict";a("9cb6")},6724:function(e,t,a){"use strict";a("8d41");var i="@@wavesContext";function l(e,t){function a(a){var i=Object.assign({},t.value),l=Object.assign({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},i),n=l.ele;if(n){n.style.position="relative",n.style.overflow="hidden";var r=n.getBoundingClientRect(),s=n.querySelector(".waves-ripple");switch(s?s.className="waves-ripple":(s=document.createElement("span"),s.className="waves-ripple",s.style.height=s.style.width=Math.max(r.width,r.height)+"px",n.appendChild(s)),l.type){case"center":s.style.top=r.height/2-s.offsetHeight/2+"px",s.style.left=r.width/2-s.offsetWidth/2+"px";break;default:s.style.top=(a.pageY-r.top-s.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",s.style.left=(a.pageX-r.left-s.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return s.style.backgroundColor=l.color,s.className="waves-ripple z-active",!1}}return e[i]?e[i].removeHandle=a:e[i]={removeHandle:a},a}var n={bind:function(e,t){e.addEventListener("click",l(e,t),!1)},update:function(e,t){e.removeEventListener("click",e[i].removeHandle,!1),e.addEventListener("click",l(e,t),!1)},unbind:function(e){e.removeEventListener("click",e[i].removeHandle,!1),e[i]=null,delete e[i]}},r=function(e){e.directive("waves",n)};window.Vue&&(window.waves=n,Vue.use(r)),n.install=r;t["a"]=n},"8d41":function(e,t,a){},"9cb6":function(e,t,a){},cad6:function(e,t,a){"use strict";a.r(t);var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container"},[a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",icon:"el-icon-edit"},on:{click:e.handleCreate}},[e._v("\n        添加\n      ")])],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],attrs:{data:e.list,"element-loading-text":"Loading",border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{staticStyle:{width:"5%"},attrs:{align:"center",label:"序号"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.row.id))]}}])}),e._v(" "),a("el-table-column",{staticStyle:{width:"10%"},attrs:{align:"center",label:"字段类型"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.row.fieldsType))]}}])}),e._v(" "),a("el-table-column",{staticStyle:{width:"10%"},attrs:{label:"源类型",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.row.datasource))]}}])}),e._v(" "),a("el-table-column",{staticStyle:{width:"10%"},attrs:{label:"源字段类型",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.row.sourceFieldType))]}}])}),e._v(" "),a("el-table-column",{staticStyle:{width:"10%"},attrs:{label:"目标类型",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.row.targetDatasource)+"\n        ")]}}])}),e._v(" "),a("el-table-column",{staticStyle:{width:"10%"},attrs:{label:"目标字段类型",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.row.targetFieldType))]}}])}),e._v(" "),a("el-table-column",{staticStyle:{width:"20%"},attrs:{label:"备注",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.row.comments))]}}])}),e._v(" "),a("el-table-column",{staticStyle:{width:"25%"},attrs:{label:"操作",align:"center","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;return[a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){return e.handleUpdate(i)}}},[e._v("\n            编辑\n          ")]),e._v(" "),"deleted"!=i.status?a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(t){return e.handleDelete(i)}}},[e._v("\n            删除\n          ")]):e._e()]}}])})],1),e._v(" "),a("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.listQuery.current,limit:e.listQuery.size},on:{"update:page":function(t){return e.$set(e.listQuery,"current",t)},"update:limit":function(t){return e.$set(e.listQuery,"size",t)},pagination:e.fetchData}}),e._v(" "),a("el-dialog",{attrs:{title:e.textMap[e.dialogStatus],visible:e.dialogFormVisible,width:"800px"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"dataForm",attrs:{rules:e.rules,model:e.temp,"label-position":"left","label-width":"100px"}},[a("el-form-item",{attrs:{label:"字段类型",prop:"fieldsType"}},[a("el-select",{staticStyle:{width:"200px"},attrs:{placeholder:"字段类型"},model:{value:e.temp.fieldsType,callback:function(t){e.$set(e.temp,"fieldsType",t)},expression:"temp.fieldsType"}},e._l(e.fieldsType,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1),e._v(" "),a("el-form-item",{attrs:{label:"源类型",prop:"datasource"}},[a("el-select",{staticStyle:{width:"200px"},attrs:{placeholder:"源类型"},model:{value:e.temp.datasource,callback:function(t){e.$set(e.temp,"datasource",t)},expression:"temp.datasource"}},e._l(e.dataSources,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1),e._v(" "),a("el-form-item",{attrs:{label:"源字段",prop:"fieldTypeName"}},[a("el-input",{staticStyle:{width:"40%"},attrs:{placeholder:"源字段类型"},model:{value:e.temp.sourceFieldType,callback:function(t){e.$set(e.temp,"sourceFieldType",t)},expression:"temp.sourceFieldType"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"目标类型",prop:"targetDatasource"}},[a("el-select",{staticStyle:{width:"200px"},attrs:{placeholder:"目标类型"},model:{value:e.temp.targetDatasource,callback:function(t){e.$set(e.temp,"targetDatasource",t)},expression:"temp.targetDatasource"}},e._l(e.dataSources,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1),e._v(" "),a("el-form-item",{attrs:{label:"目标字段",prop:"targetFieldTypeName"}},[a("el-input",{staticStyle:{width:"40%"},attrs:{placeholder:"目标字段类型"},model:{value:e.temp.targetFieldType,callback:function(t){e.$set(e.temp,"targetFieldType",t)},expression:"temp.targetFieldType"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"备注"}},[a("el-input",{staticStyle:{width:"60%"},attrs:{autosize:{minRows:2,maxRows:4},type:"textarea",placeholder:"请输入备注"},model:{value:e.temp.comments,callback:function(t){e.$set(e.temp,"comments",t)},expression:"temp.comments"}})],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("\n          取消\n        ")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){"create"===e.dialogStatus?e.createData():e.updateData()}}},[e._v("\n          确认\n        ")])],1)],1),e._v(" "),a("el-dialog",{attrs:{visible:e.dialogPluginVisible,title:"Reading statistics"},on:{"update:visible":function(t){e.dialogPluginVisible=t}}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.pluginData,border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{prop:"key",label:"Channel"}}),e._v(" "),a("el-table-column",{attrs:{prop:"pv",label:"Pv"}})],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dialogPvVisible=!1}}},[e._v("Confirm")])],1)],1)],1)},l=[],n=a("b775");function r(e){return Object(n["a"])({url:"/api/fieldsMapping",method:"get",params:e})}function s(e){return Object(n["a"])({url:"/api/fieldsMapping/"+e,method:"get"})}function o(e){return Object(n["a"])({url:"/api/fieldsMapping",method:"put",data:e})}function u(e){return Object(n["a"])({url:"/api/fieldsMapping",method:"post",data:e})}function c(e){return Object(n["a"])({url:"/api/fieldsMapping",method:"delete",params:e})}var d=a("6724"),p=a("ed08"),f=a("333d"),m={name:"FieldMapping",components:{Pagination:f["a"]},directives:{waves:d["a"]},filters:{statusFilter:function(e){var t={published:"success",draft:"gray",deleted:"danger"};return t[e]}},data:function(){return{list:null,listLoading:!0,total:0,listQuery:{current:1,size:10},pluginTypeOptions:["reader","writer"],dialogPluginVisible:!1,pluginData:[],dialogFormVisible:!1,dialogStatus:"",textMap:{update:"Edit",create:"Create"},rules:{fieldsType:[{required:!0,message:"this is required",trigger:"blur"}],targetDatasource:[{required:!0,message:"this is required",trigger:"blur"}],datasource:[{required:!0,message:"this is required",trigger:"change"}],sourceFieldType:[{required:!0,message:"this is required",trigger:"blur"}],targetFieldType:[{required:!0,message:"this is required",trigger:"blur"}]},temp:{id:void 0,fieldsType:"",datasource:"",sourceFieldType:"",targetDatasource:"",targetFieldType:""},visible:!0,dataSources:[{value:"mysql",label:"mysql"},{value:"oracle",label:"oracle"},{value:"postgresql",label:"postgresql"},{value:"sqlserver",label:"sqlserver"},{value:"hive",label:"hive"},{value:"hbase",label:"hbase"},{value:"mongodb",label:"mongodb"},{value:"clickhouse",label:"clickhouse"}],fieldsType:[{value:"字符串",label:"字符串"},{value:"整型",label:"整型"},{value:"小数",label:"小数"},{value:"日期时间",label:"日期时间"},{value:"布尔型",label:"布尔型"}],jdbc:!0,hbase:!1,mongodb:!1}},created:function(){this.fetchData()},methods:{fetchData:function(){var e=this;this.listLoading=!0,r(this.listQuery).then((function(t){var a=t.records,i=t.total;e.total=i,e.list=a,e.listLoading=!1}))},resetTemp:function(){this.temp={id:void 0,fieldsType:"",datasource:"",fieldTypeName:"",targetDatasource:"",targetFieldType:""}},handleCreate:function(){var e=this;this.resetTemp(),this.dialogStatus="create",this.dialogFormVisible=!0,this.$nextTick((function(){e.$refs["dataForm"].clearValidate()}))},createData:function(){var e=this;this.$refs["dataForm"].validate((function(t){t&&u(e.temp).then((function(){e.fetchData(),e.dialogFormVisible=!1,e.$notify({title:"Success",message:"Created Successfully",type:"success",duration:2e3})}))}))},testDataSource:function(){var e=this;this.$refs["dataForm"].validate((function(t){t&&(void 0)(e.temp).then((function(t){!1===t.data?e.$notify({title:"Fail",message:t.data.msg,type:"fail",duration:2e3}):e.$notify({title:"Success",message:"Tested Successfully",type:"success",duration:2e3})}))}))},handleUpdate:function(e){var t=this;this.getShowStrategy(e.datasource),this.temp=Object.assign({},e),this.dialogStatus="update",this.dialogFormVisible=!0,this.$nextTick((function(){t.$refs["dataForm"].clearValidate()}))},updateData:function(){var e=this;this.$refs["dataForm"].validate((function(t){if(t){var a=Object.assign({},e.temp);o(a).then((function(){e.fetchData(),e.dialogFormVisible=!1,e.$notify({title:"Success",message:"Update Successfully",type:"success",duration:2e3})}))}}))},handleDelete:function(e){var t=this;console.log("删除");var a=[];a.push(e.id),c({idList:e.id}).then((function(e){t.fetchData(),t.$notify({title:"Success",message:"Delete Successfully",type:"success",duration:2e3})}))},handleFetchPv:function(e){var t=this;s(e).then((function(e){t.pluginData=e,t.dialogPvVisible=!0}))},formatJson:function(e,t){return t.map((function(t){return e.map((function(e){return"timestamp"===e?Object(p["f"])(t[e]):t[e]}))}))},changePass:function(e){this.visible=!("show"===e)}}},g=m,h=a("2877"),v=Object(h["a"])(g,i,l,!1,null,null,null);t["default"]=v.exports}}]);