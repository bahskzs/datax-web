(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-69c65a2f"],{"2f21":function(e,t,a){"use strict";var s=a("79e5");e.exports=function(e,t){return!!e&&s((function(){t?e.call(null,(function(){}),1):e.call(null)}))}},"55dd":function(e,t,a){"use strict";var s=a("5ca1"),i=a("d8e8"),n=a("4bf8"),r=a("79e5"),l=[].sort,o=[1,2,3];s(s.P+s.F*(r((function(){o.sort(void 0)}))||!r((function(){o.sort(null)}))||!a("2f21")(l)),"Array",{sort:function(e){return void 0===e?l.call(n(this)):l.call(n(this),i(e))}})},"5a91":function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container"},[a("el-input",{staticClass:"filter-item",staticStyle:{width:"200px"},attrs:{clearable:"",placeholder:"用户名称"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.fetchData(t)}},model:{value:e.listQuery.userName,callback:function(t){e.$set(e.listQuery,"userName",t)},expression:"listQuery.userName"}}),e._v(" "),a("el-button",{directives:[{name:"waves",rawName:"v-waves"}],staticClass:"filter-item",attrs:{type:"primary",icon:"el-icon-search"},on:{click:e.fetchData}},[e._v("\n        搜索\n      ")]),e._v(" "),a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",icon:"el-icon-edit"},on:{click:e.handleCreate}},[e._v("\n        添加\n      ")])],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],attrs:{data:e.list,"element-loading-text":"Loading",border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{align:"center",label:"序号",width:"80",fixed:""},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.row.id))]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"用户",width:"120",align:"center",fixed:""},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.row.userid))]}}])}),e._v(" "),a("el-table-column",{staticStyle:{display:"none"},attrs:{label:"用户账号",width:"200",align:"center",fixed:""},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.row.userInfo))]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"菜单权限",width:"200",align:"center",fixed:""},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.row.modules)+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"区划",align:"center","show-overflow-tooltip":!0,width:"200"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.row.regions))]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"单位",width:"300",align:"center","show-overflow-tooltip":!0},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.row.agencies))]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"处室",width:"300",align:"center","show-overflow-tooltip":!0},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.row.offices))]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"状态",width:"100",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.row.status))]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"操作",align:"center","class-name":"small-padding fixed-width",width:"250"},scopedSlots:e._u([{key:"default",fn:function(t){var s=t.row;return[a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){return e.handleUpdate(s)}}},[e._v("\n          编辑\n        ")]),e._v(" "),"deleted"!=s.status?a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(t){return e.handleDelete(s)}}},[e._v("\n          删除\n        ")]):e._e()]}}])})],1),e._v(" "),a("el-dialog",{attrs:{title:e.textMap[e.dialogStatus],visible:e.dialogFormVisible,width:"800px"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"dataForm",attrs:{rules:e.rules,model:e.temp,"label-position":"left","label-width":"100px"}},[a("el-form-item",{attrs:{label:"用户"}},[a("el-select",{staticStyle:{width:"500px"},attrs:{filterable:"",remote:"",clearable:"","reserve-keyword":"",placeholder:"请输入用户名","remote-method":e.fetchUser,loading:e.userLoading,disabled:"update"===e.dialogStatus},on:{change:e.handleUserChange},model:{value:e.temp.userInfo,callback:function(t){e.$set(e.temp,"userInfo",t)},expression:"temp.userInfo"}},e._l(e.userOptions,(function(e){return a("el-option",{key:e.guid,attrs:{label:e.name+"/"+e.code+"/"+e.province,value:e.guid+"-"+e.name+"-"+e.code+"-"+e.province+"-"+e.phonenumber}})})),1)],1),e._v(" "),a("el-form-item",{attrs:{label:"模块名称",prop:"modules"}},[a("el-checkbox-group",{attrs:{label:"模块名称"},model:{value:e.temp.modules,callback:function(t){e.$set(e.temp,"modules",t)},expression:"temp.modules"}},e._l(e.moduleName,(function(t){return a("el-checkbox",{key:t.value,attrs:{label:t.label}},[e._v(e._s(t.value))])})),1)],1),e._v(" "),a("el-form-item",{attrs:{label:"历史区划",prop:"regions"}},[a("el-cascader",{staticStyle:{width:"500px"},attrs:{options:e.oldRegions,props:Object.assign({},e.props,{checkStrictly:!0,emitPath:!1}),clearable:"",filterable:""},on:{change:e.handleRegionsChange},model:{value:e.temp.regions,callback:function(t){e.$set(e.temp,"regions",t)},expression:"temp.regions"}}),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.clearAreas}},[e._v("\n        清空默认区划\n        ")])],1),e._v(" "),a("el-form-item",{attrs:{label:"历史单位",prop:"agencies"}},[a("el-cascader",{staticStyle:{width:"500px"},attrs:{options:e.oldAgencies,props:Object.assign({},e.props,{checkStrictly:!0,emitPath:!1}),clearable:"",filterable:""},on:{"visible-change":e.handleOptionChange},model:{value:e.temp.agencies,callback:function(t){e.$set(e.temp,"agencies",t)},expression:"temp.agencies"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"历史处室"}},[a("el-cascader",{staticStyle:{width:"500px"},attrs:{options:e.oldOffices,props:Object.assign({},e.props,{checkStrictly:!0,emitPath:!1}),clearable:"",filterable:""},on:{"visible-change":e.handleOptionChange2},model:{value:e.temp.offices,callback:function(t){e.$set(e.temp,"offices",t)},expression:"temp.offices"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"状态",prop:"status"}},[a("el-switch",{attrs:{"active-text":"启用","inactive-text":"禁用"},model:{value:e.value1,callback:function(t){e.value1=t},expression:"value1"}})],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("\n        取消\n      ")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){"create"===e.dialogStatus?e.createData():e.updateData()}}},[e._v("\n        确认\n      ")])],1)],1)],1)},i=[],n=(a("f559"),a("3835")),r=a("b85c"),l=(a("4f7f"),a("2909")),o=(a("1c4c"),a("55dd"),a("7f7f"),a("ac6a"),a("5df3"),a("f400"),a("28a5"),a("6724")),c=a("b775");function u(e){return Object(c["a"])({url:"/api/history/auth/create",method:"post",data:e})}function d(e,t){return Object(c["a"])({url:"/api/history/auth/update/"+t,method:"put",data:e})}function f(e){return Object(c["a"])({url:"/api/history/auth/delete/"+e,method:"delete"})}function h(e){return Object(c["a"])({url:"/api/history/auth/list",method:"get",params:e})}function p(e){return Object(c["a"])({url:"/api/history/auth/regions",method:"get",params:e})}function m(e){return Object(c["a"])({url:"/api/history/auth/agencies",method:"get",params:e})}function g(e){return Object(c["a"])({url:"/api/history/auth/offices",method:"get",params:e})}function v(e){return Object(c["a"])({url:"/api/history/auth/users",method:"get",params:e})}var b={name:"UserRole",directives:{waves:o["a"]},filters:{statusFilter:function(e){var t={published:"success",draft:"gray",deleted:"danger"};return t[e]}},data:function(){return{selectedRegions:[],selectedYear:null,years:["2017","2018","2019","2020","2021"],props:{multiple:!0},userLoading:!1,num:1,value1:!0,list:null,listLoading:!0,total:0,listQuery:{userName:""},regionsQuery:{areaCode:null},menuObj:{moduleName:"",reportName:"",reportUrl:"",reportAddress:"",areaArr:"",sort:"",status:""},userRoleObj:{id:"",userid:"",userInfo:"",modules:[],regions:"",agencies:"",offices:"",status:""},temp:{id:"",userInfo:"",modules:[],regions:[],agencies:null,offices:null,status:!0},rules:{userInfo:[{required:!0,message:"this is required",trigger:"blur"}],modules:[{required:!0,message:"this is required",trigger:"blur"}],regions:[{required:!0,message:"this is required",trigger:"blur"}],agencies:[{required:!1,message:"",trigger:"blur"}],offices:[{required:!1,message:"",trigger:"blur"}],status:[{required:!0,message:"this is required",trigger:"blur"}]},dialogPluginVisible:!1,pluginData:[],dialogFormVisible:!1,dialogStatus:"",textMap:{update:"Edit",create:"Create"},visible:!0,moduleName:[{label:"基础数据",value:"基础数据"},{label:"预算编制",value:"预算编制"},{label:"指标管理",value:"指标管理"},{label:"国库集中支付",value:"国库集中支付"},{label:"总预算会计",value:"总预算会计"},{label:"国库工资统发",value:"国库工资统发"}],region:[{value:"3500",label:"福建省本级"},{value:"350100",label:"福州市"},{value:"350300",label:"莆田市"},{value:"350400",label:"三明市"},{value:"350500",label:"泉州市"},{value:"350600",label:"漳州市"},{value:"350700",label:"南平市"},{value:"350800",label:"龙岩市"},{value:"350900",label:"宁德市"}],oldRegions:null,oldAgencies:null,oldOffices:null,userOptions:null}},created:function(){this.fetchData(),this.fetchOldRegions(),this.fetchUser("")},methods:{clearAreas:function(){this.regionsQuery.areaCode=null,this.fetchOldRegions()},handleOptionChange:function(){this.fetchOldAgencies()},handleOptionChange2:function(){this.fetchOldOffices()},handleRegionsChange:function(e){this.selectedRegions=e},handleUserChange:function(e){var t=e.split("-");this.regionsQuery.areaCode=t[3].substring(0,6),this.fetchOldRegions()},delayedFetchOldOffices:function(){var e=this;setTimeout((function(){e.fetchOldOffices()}),1e3)},transformToNested:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:[],t=new Map;e.forEach((function(e){t.has(e.year)||t.set(e.year,{value:e.year,label:e.year,children:[]}),t.get(e.year).children.push({value:e.year+"-"+e.code,label:e.code+"-"+e.name+"-"+e.areaCode})}));var a=Array.from(t.values()).sort((function(e,t){return t.label.localeCompare(e.label)}));return a},transformToNested2:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:[],t=new Map;return e.forEach((function(e){t.has(e.year)||t.set(e.year,{value:e.year,label:e.year,children:[]}),t.get(e.year).children.push({value:e.year+"-"+e.code+"-"+e.name+"-"+e.areaCode,label:e.code+"-"+e.name+"-"+e.areaCode})})),Array.from(t.values())},transformToNested3:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:[],t=new Map,a=new Map;return e.forEach((function(e){a.has(e.id)||a.set(e.id,{value:e.id,label:e.code+"-"+e.name+"-"+e.areaCode,children:[]})})),e.forEach((function(e){var s=a.get(e.id);if(e.pid){var i=a.get(e.pid);i&&i.children.push(s)}t.has(e.year)||t.set(e.year,{value:e.year,label:e.year,children:[]}),e.pid||t.get(e.year).children.push(s)})),Array.from(t.values())},transformToNested4:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:[],t=[],a=Object(l["a"])(new Set(e.map((function(e){return e.year}))));return a.forEach((function(a){var s={value:a,label:a,children:[]},i=new Map;e.forEach((function(e){if(e.year===a){var t={value:e.year+"-"+e.code+"-"+e.name+"-"+e.areaCode,label:e.code+"-"+e.name+"-"+e.areaCode};i.set(e.code,t)}})),e.forEach((function(e){if(e.year===a){var t,l=null,o=Object(r["a"])(i.entries());try{for(o.s();!(t=o.n()).done;){var c=Object(n["a"])(t.value,2),u=c[0],d=c[1];e.code!==u&&e.code.startsWith(u)&&(!l||u.length<l.label.length)&&(l=d)}}catch(f){o.e(f)}finally{o.f()}l?(l.children||(l.children=[]),l.children.push(i.get(e.code)),l.children.sort((function(e,t){var a=e.label.split("-")[0],s=t.label.split("-")[0];return a.localeCompare(s)}))):s.children.push(i.get(e.code))}})),s.children.sort((function(e,t){var a=e.label.split("-")[0],s=t.label.split("-")[0];return a.localeCompare(s)})),t.push(s)})),t},fetchData:function(){var e=this;this.listLoading=!0,h(this.listQuery).then((function(t){var a=t;a.forEach((function(e){e.status="0"===e.status?"启用":"禁用"})),e.list=a,e.list.map((function(e){e.userid=e.userInfo.split("-")[1]+"-"+e.userInfo.split("-")[3]+"-"+e.userInfo.split("-")[4]})),e.listLoading=!1}))},fetchUser:function(e){var t=this;""!==e?(this.userLoading=!0,setTimeout((function(){var a={name:e,code:"",areaCode:""};v(a).then((function(e){var a=e;Array.isArray(a)?t.userOptions=a:console.error("records is not an array:",a)})).finally((function(){t.userLoading=!1}))}),200)):(this.userOptions=[],this.userLoading=!1)},fetchOldRegions:function(){var e=this;p(this.regionsQuery).then((function(t){var a=t;Array.isArray(a)?e.oldRegions=e.transformToNested(a):console.error("records is not an array:",a)}))},fetchOldAgencies:function(){var e=this,t={areaCodes:[]};if(this.selectedRegions.length>0){this.selectedRegions.forEach((function(e){t.areaCodes.push(e)}));var a=new URLSearchParams;t.areaCodes.forEach((function(e){a.append("areaCodes",e)})),m(a).then((function(t){var a=t;Array.isArray(a)?e.oldAgencies=e.transformToNested4(a):console.error("records is not an array:",a)}))}},fetchOldOffices:function(){var e=this,t={areaCodes:[]};if(this.selectedRegions.length>0){console.log("选中的区划:"+this.selectedRegions[this.selectedRegions.length-1]),this.selectedRegions.forEach((function(e){t.areaCodes.push(e)}));var a=new URLSearchParams;t.areaCodes.forEach((function(e){a.append("areaCodes",e)})),g(a).then((function(t){var a=t;Array.isArray(a)?e.oldOffices=e.transformToNested2(a):console.error("records is not an array:",a)}))}else if(this.oldOffices)return},resetTemp:function(){this.temp={id:"",userid:"",userInfo:"",modules:[],regions:"",agencies:"",offices:"",status:0}},handleCreate:function(){var e=this;this.resetTemp(),this.dialogStatus="create",this.dialogFormVisible=!0,this.$nextTick((function(){e.$refs["dataForm"].clearValidate()}))},createData:function(){var e=this;console.log("新增数据",this.temp),""===this.temp.agencies&&(this.temp.agencies=[]),""===this.temp.offices&&(this.temp.offices=[]),!0===this.value1?this.temp.status=0:this.temp.status=1,this.$refs["dataForm"].validate((function(t){t&&u(e.temp).then((function(){e.fetchData(),e.dialogFormVisible=!1,e.$notify({title:"Success",message:"Created Successfully",type:"success",duration:2e3})}))}))},handleUpdate:function(e){var t=this,a=Object.assign({},e);this.temp.id=a.id,this.temp.userInfo=a.userInfo,this.temp.modules=a.modules.split(","),this.regionsQuery.areaCode=null,this.temp.regions=a.regions.split(","),this.selectedRegions=this.temp.regions,this.temp.agencies=a.agencies.split(","),this.temp.offices=a.offices.split(","),this.fetchOldOffices(),this.fetchOldAgencies(),this.value1="禁用"!==a.status,console.log("编辑数据",this.temp),this.temp.modules=Array.from(this.temp.modules),this.temp.offices=Array.from(this.temp.offices),this.dialogStatus="update",this.dialogFormVisible=!0,this.$nextTick((function(){t.$refs["dataForm"].clearValidate()}))},updateData:function(){var e=this;!0===this.value1?this.temp.status=0:this.temp.status=1,this.$refs["dataForm"].validate((function(t){if(t){var a=Object.assign({},e.temp);d(a,e.temp.id).then((function(){e.fetchData(),e.dialogFormVisible=!1,e.$notify({title:"Success",message:"Update Successfully",type:"success",duration:2e3})}))}}))},handleDelete:function(e){var t=this;console.log("删除"),f(e.id).then((function(e){t.fetchData(),t.$notify({title:"Success",message:"Delete Successfully",type:"success",duration:2e3})}))}}},y=b,w=a("2877"),_=Object(w["a"])(y,s,i,!1,null,null,null);t["default"]=_.exports},6724:function(e,t,a){"use strict";a("8d41");var s="@@wavesContext";function i(e,t){function a(a){var s=Object.assign({},t.value),i=Object.assign({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},s),n=i.ele;if(n){n.style.position="relative",n.style.overflow="hidden";var r=n.getBoundingClientRect(),l=n.querySelector(".waves-ripple");switch(l?l.className="waves-ripple":(l=document.createElement("span"),l.className="waves-ripple",l.style.height=l.style.width=Math.max(r.width,r.height)+"px",n.appendChild(l)),i.type){case"center":l.style.top=r.height/2-l.offsetHeight/2+"px",l.style.left=r.width/2-l.offsetWidth/2+"px";break;default:l.style.top=(a.pageY-r.top-l.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",l.style.left=(a.pageX-r.left-l.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return l.style.backgroundColor=i.color,l.className="waves-ripple z-active",!1}}return e[s]?e[s].removeHandle=a:e[s]={removeHandle:a},a}var n={bind:function(e,t){e.addEventListener("click",i(e,t),!1)},update:function(e,t){e.removeEventListener("click",e[s].removeHandle,!1),e.addEventListener("click",i(e,t),!1)},unbind:function(e){e.removeEventListener("click",e[s].removeHandle,!1),e[s]=null,delete e[s]}},r=function(e){e.directive("waves",n)};window.Vue&&(window.waves=n,Vue.use(r)),n.install=r;t["a"]=n},"8d41":function(e,t,a){},f400:function(e,t,a){"use strict";var s=a("c26b"),i=a("b39a"),n="Map";e.exports=a("e0b8")(n,(function(e){return function(){return e(this,arguments.length>0?arguments[0]:void 0)}}),{get:function(e){var t=s.getEntry(i(this,n),e);return t&&t.v},set:function(e,t){return s.def(i(this,n),0===e?0:e,t)}},s,!0)}}]);