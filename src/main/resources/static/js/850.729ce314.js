"use strict";(self["webpackChunkbook_02"]=self["webpackChunkbook_02"]||[]).push([[850],{8850:function(e,o,a){a.r(o),a.d(o,{default:function(){return j}});var s=a(3396),t=a(7139);const l=e=>((0,s.dD)("data-v-dbd6bfaa"),e=e(),(0,s.Cn)(),e),i={class:"content"},d=l((()=>(0,s._)("h1",null,"我的书摊",-1))),n={class:"box_info"},r={class:"book_list"},c=l((()=>(0,s._)("i",{class:"el-icon-edit"},null,-1))),m=l((()=>(0,s._)("p",{style:{"font-size":"14px"}},"添加新的书籍",-1))),p=[c,m],u={class:"bookCover"},g={class:"bookName"},h={style:{float:"right","font-size":"14px",color:"#757575"}},k={class:"author"},b={class:"Description"},f={class:"foot"},_=["onClick"],w=["onClick"],y={class:"dialog-footer"};function v(e,o,a,l,c,m){const v=(0,s.up)("el-image"),U=(0,s.up)("UploadPage"),V=(0,s.up)("el-input"),W=(0,s.up)("el-form-item"),B=(0,s.up)("el-form"),A=(0,s.up)("el-button"),C=(0,s.up)("el-dialog");return(0,s.wg)(),(0,s.iD)("div",i,[d,(0,s._)("div",n,[(0,s._)("div",r,[(0,s._)("div",{class:"add",onClick:o[0]||(o[0]=(...e)=>m.handleAdd&&m.handleAdd(...e))},p)]),((0,s.wg)(!0),(0,s.iD)(s.HY,null,(0,s.Ko)(c.bookList,(e=>((0,s.wg)(),(0,s.iD)("div",{class:"book_list",key:e.bookId},[(0,s._)("div",u,[(0,s.Wm)(v,{src:"src/assets/image/20.jpg"},{default:(0,s.w5)((()=>[(0,s.Uk)(' alt="书籍封面">')])),_:1})]),(0,s._)("div",g,[(0,s.Uk)((0,t.zw)(e.name)+" ",1),(0,s._)("span",h,(0,t.zw)(e.type),1)]),(0,s._)("div",k,(0,t.zw)(e.author),1),(0,s._)("div",b,(0,t.zw)(e.description),1),(0,s._)("div",f,[(0,s._)("span",{style:{float:"right"},onClick:o=>m.delBook(e)},"删除",8,_),(0,s._)("span",{style:{float:"right","margin-right":"10px"},onClick:o=>m.handleMod(e)},"修改",8,w)])])))),128))]),(0,s.Wm)(C,{title:"添加新的书籍",modelValue:c.dialogVisible,"onUpdate:modelValue":o[7]||(o[7]=e=>c.dialogVisible=e),width:"50%",center:""},{footer:(0,s.w5)((()=>[(0,s._)("span",y,[(0,s.Wm)(A,{onClick:o[5]||(o[5]=e=>c.dialogVisible=!1)},{default:(0,s.w5)((()=>[(0,s.Uk)("取 消")])),_:1}),(0,s.Wm)(A,{type:"primary",onClick:o[6]||(o[6]=e=>m.onSubmit("form"))},{default:(0,s.w5)((()=>[(0,s.Uk)("确 定")])),_:1})])])),default:(0,s.w5)((()=>[(0,s.Wm)(B,{ref:"form",model:c.book},{default:(0,s.w5)((()=>[(0,s.Wm)(U,{ref:"bookPaper",onClick:m.getPaper},null,8,["onClick"]),(0,s.Wm)(W,null,{default:(0,s.w5)((()=>[(0,s.Wm)(V,{placeholder:"书名",modelValue:c.book.name,"onUpdate:modelValue":o[1]||(o[1]=e=>c.book.name=e)},null,8,["modelValue"])])),_:1}),(0,s.Wm)(W,null,{default:(0,s.w5)((()=>[(0,s.Wm)(V,{placeholder:"类别",modelValue:c.book.type,"onUpdate:modelValue":o[2]||(o[2]=e=>c.book.type=e)},null,8,["modelValue"])])),_:1}),(0,s.Wm)(W,null,{default:(0,s.w5)((()=>[(0,s.Wm)(V,{type:"textarea",placeholder:"简介",modelValue:c.book.description,"onUpdate:modelValue":o[3]||(o[3]=e=>c.book.description=e)},null,8,["modelValue"])])),_:1}),(0,s.Wm)(W,null,{default:(0,s.w5)((()=>[(0,s.Wm)(V,{placeholder:"价格",modelValue:c.book.price,"onUpdate:modelValue":o[4]||(o[4]=e=>c.book.price=e)},null,8,["modelValue"])])),_:1})])),_:1},8,["model"])])),_:1},8,["modelValue"])])}const U=e=>((0,s.dD)("data-v-f978d582"),e=e(),(0,s.Cn)(),e),V=["src"],W={key:1,class:"el-icon-plus avatar-uploader-icon"},B=U((()=>(0,s._)("span",null,"书籍封面上传",-1)));function A(e,o,a,t,l,i){const d=(0,s.up)("el-upload");return(0,s.wg)(),(0,s.iD)("div",null,[(0,s.Wm)(d,{class:"avatar-uploader",action:"http://localhost:8080/uploadBook","show-file-list":!0,"on-remove":i.handleAvatarFail,"on-success":i.handleAvatarSuccess,"before-upload":i.beforeAvatarUpload,headers:i.headersJWT},{default:(0,s.w5)((()=>[l.imageUrl?((0,s.wg)(),(0,s.iD)("img",{key:0,src:l.imageUrl,class:"avatar",alt:"图片详情"},null,8,V)):((0,s.wg)(),(0,s.iD)("i",W)),B])),_:1},8,["on-remove","on-success","before-upload","headers"])])}var C={name:"UploadPage",data(){return{imageUrl:""}},methods:{handleAvatarSuccess(e,o){this.imageUrl=URL.createObjectURL(o.raw),console.log(this.imageUrl)},beforeAvatarUpload(e){const o="image/jpeg"===e.type,a=e.size/1024/1024<2;return o||this.$message.error("上传头像图片只能是 JPG 格式!"),a||this.$message.error("上传头像图片大小不能超过 2MB!"),o&&a},handleAvatarFail(){console.log("文件已被移动")}},computed:{headersJWT(){const e=localStorage.getItem("token");return{Authorization:e}}}},P=a(89);const I=(0,P.Z)(C,[["render",A],["__scopeId","data-v-f978d582"]]);var $=I,D=a(6779);const L="http://localhost:8080",z=e=>(0,D.Z)(L+"/bookStall",{image:e.image,name:e.name,type:e.type,description:e.description,price:e.price},"POST"),S=e=>(0,D.Z)(L+"/modifyBook",{name:e.name,type:e.type,description:e.description,price:e.price},"POST"),x=e=>(0,D.Z)(L+"/delBook",{bookId:e}),T=()=>(0,D.Z)(L+"/getBookList",{});var Z={name:"AddressPage",components:{UploadPage:$},data(){return{dialogVisible:!1,isEdit:!1,bookList:[{BookId:1,name:"黄哥的恋爱节奏",author:"黄文敬",isbn:"",type:"文学",description:"讲述了黄哥的恋爱心路历程，惊心动魄，感人肺腑",status:"",image:"",campus:"",price:"25",newProduct:"",recommend:"",sellerID:""},{bookId:2,name:"算出来的秘密爱情",author:"胡丹婷",isbn:"",type:"小说",description:"",status:"",image:"",campus:"",price:"",newProduct:"",recommend:"",sellerID:""}],book:{bookId:"",name:"",author:"",isbn:"",type:"",description:"",status:"",image:"",campus:"",price:"",newProduct:"",recommend:"",sellerID:""}}},methods:{handleAdd(){this.dialogVisible=!0,this.isEdit=!1},handleMod(e){this.dialogVisible=!0,this.isEdit=!0,this.book.bookId=e.addId,this.book.image=e.image,this.book.author=e.author,this.book.type=e.type,this.book.description=e.description,this.book.price=e.price},onSubmit(){this.isEdit?this.modifyBook():this.addBook()},getBookList(){console.log("===获取的书籍列表：==="+this.$store.getters.getUser.account+"====="),T().then((e=>{if(console.log(e),1==e.data.code){let o=e.data.data;console.log(o),this.bookList=o}else this.$message({message:e.message,type:"warning"})})).catch((e=>{console.log(e)}))},addBook(e){this.$refs[e].validate((e=>{e&&z(this.book).then((e=>{console.log(e),1==e.data.code?(this.$message({message:e.message,type:"success"}),this.dialogVisible=!1,this.getAddressList()):this.$message({message:e.message,type:"warning"})})).catch((e=>{console.log(e)}))}))},modifyBook(){S(this.book).then((e=>{console.log(e),1==e.data.code?(this.$message({message:e.message,type:"success"}),this.dialogVisible=!1,this.getAddressList()):this.$message({message:e.message,type:"warning"})})).catch((e=>{console.log(e)}))},delBook(e){this.$confirm("是否要进行删除操作?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((()=>{x(e).then((e=>{console.log(e),1==e.data.code?(this.$message({message:e.message,type:"success"}),this.getAddressList()):this.$message({message:e.message,type:"warning"})})).catch((e=>{console.log(e)}))})).catch((()=>{console.log("取消删除了")}))},getPaper(){this.book.image=this.$refs.bookPaper.imageUrl}}};const E=(0,P.Z)(Z,[["render",v],["__scopeId","data-v-dbd6bfaa"]]);var j=E}}]);
//# sourceMappingURL=850.729ce314.js.map