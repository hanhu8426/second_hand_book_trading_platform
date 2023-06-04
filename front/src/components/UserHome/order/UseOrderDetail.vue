<template>
    <div class="content">
        <div class="detail_title">
            <span style="color: red">
                <span style="float: right"></span>
            </span>
        </div>
        <div class="info">
            <div class="info_title">
                <i class="el-icon-s-flag"></i><span> 基本信息</span>
            </div>
            <div class="table-layout">
                <el-row>
                    <el-col :span="8" class="table-cell-title">订单编号</el-col>
                    <el-col :span="8" class="table-cell-title">下单时间</el-col>
                    <el-col :span="8" class="table-cell-title">收货时间</el-col>
                </el-row>
                <el-row>
                    <el-col :span="8" class="table-cell">{{this.order.orderId}}</el-col>
                    <el-col :span="8" class="table-cell">{{this.order.beginTime}}</el-col>
                    <el-col :span="8" class="table-cell">{{this.order.endTime}}</el-col>
                </el-row>
            </div>

            <div class="info_title">
                <i class="el-icon-s-flag"></i><span> 收货人信息</span>
            </div>
            <div class="table-layout">
                <el-row>
                    <el-col :span="4" class="table-cell-title">收货人姓名</el-col>
                    <el-col :span="4" class="table-cell-title">电话</el-col>
                    <el-col :span="13" class="table-cell-title">地址</el-col>
                    <el-col :span="3" class="table-cell-title">校区</el-col>
                </el-row>
                <el-row>
                    <el-col :span="4" class="table-cell">{{this.order.name}}</el-col>
                    <el-col :span="4" class="table-cell">{{this.order.phone}}</el-col>
                    <el-col :span="13" class="table-cell">{{this.order.address}}</el-col>
                    <el-col :span="3" class="table-cell">{{this.book.campus}}</el-col>
                </el-row>
            </div>

            <div class="info_title">
                <i class="el-icon-s-flag"></i><span> 书籍信息</span>
            </div>
            <div class="table-layout">
                <el-row>
                    <el-col :span="4" class="table-cell-title">书籍照片</el-col>
                    <el-col :span="4" class="table-cell-title">书名</el-col>
                    <el-col :span="4" class="table-cell-title">作者</el-col>
                    <el-col :span="4" class="table-cell-title">校区</el-col>
                    <el-col :span="4" class="table-cell-title">isbn</el-col>
                    <el-col :span="4" class="table-cell-title">价格</el-col>

                </el-row>
                <el-row>
                    <el-col :span="4" class="table-cell"><el-image :src="this.book.image" :fit="'fill'"></el-image></el-col>
                    <el-col :span="4" class="table-cell">{{this.book.name}}</el-col>
                    <el-col :span="4" class="table-cell">{{this.book.author}}</el-col>
                    <el-col :span="4" class="table-cell">
                        <p v-if="this.book.campus===1">九龙湖校区</p>
                        <p v-if="this.book.campus===2">四牌楼校区</p>
                        <p v-if="this.book.campus===3">丁家桥校区</p>
                    </el-col>
                    <el-col :span="4" class="table-cell">{{this.book.isbn}}</el-col>
                    <el-col :span="4" class="table-cell">{{this.book.price}}</el-col>

                </el-row>
            </div>

            <div class="info_title">
                <i class="el-icon-s-flag"></i><span> 费用信息</span>
            </div>
            <div class="table-layout">
                <el-row>
                    <el-col :span="12" class="table-cell-title">订单总金额</el-col>
                    <el-col :span="12" class="table-cell-title">实付金额</el-col>
                </el-row>
                <el-row>
                    <el-col :span="12" class="table-cell">{{this.book.price}}</el-col>
                    <el-col :span="12" class="table-cell">{{this.book.price}}</el-col>
                </el-row>
            </div>
        </div>
    </div>
</template>

<script>
import {reqGetOrderByOrderId} from "@/api/user";
import {reqGetBook} from "@/api/book";

export default {
    name: "UserOrderDetail",
    props:['message'],
    data(){
        return {
            active: 2,
            testNum:'测试的数字',
            showDetail: false,

            order: {
                    orderId:'',
                    buyerId:'',
                    sellerId: '',
                    name: '',
                    phone: '',
                    address: '',
                    bookId: '',
                    beginTime: '',
                    endTime: '',
                    status: '',
            },
            book: {
                    bookId: '',
                    name: "",
                    author: "",
                    isbn: "",
                    type: "",
                    description: "",
                    status: "",
                    image: "",
                    campus: "",
                    price: "",
                    recommend: "",
                    sellerID: "",
            },

            user:localStorage.getItem("user")
        }},
     created() {
       this.getInit_2(this.$route.query.orderId,this.$route.query.bookId)
       console.log("开始组件构建")
     },
     methods:{
        async getInit(orderId,bookId){
             //这里使用orderId获取订单信息并存储
             reqGetOrderByOrderId(orderId).then(response=>{
                 console.log(response)
                 if(response.data.code===1){
                     this.orderItem.order = response.data.data
                     console.log(this.orderItem.order)
                     console.log("检查本地订单的测试")
                 }
                 else {
                     console.log("状态码错误，失败")
                     this.$message({
                         type: 'waring',
                         message: "获取订单失败"
                     })
                 }
             }).catch(error=>{
                 console.log(error)
             })
             //这里使用bookId获取订单信息并存储
             reqGetBook(bookId).then(response=>{
                 console.log(response)
                 if(response.data.code===1){
                     this.orderItem.book = response.data.data
                     console.log(this.orderItem.book)
                     console.log("检查本地书籍的测试")
                 }
                 else {
                     console.log("状态码错误，失败")
                     this.$message({
                         type: 'waring',
                         message: "获取书籍失败"
                     })
                 }
             }).catch(error=>{
                 console.log(error)
             })
         },

         async getInit_2(orderId,bookId){
             try{
                    //通过订单的id获取订单
                    const orderResponse = await reqGetOrderByOrderId(orderId);
                    console.log(orderResponse);

                    if(orderResponse.data.code===1){
                        const orderDetail = orderResponse.data.data;
                        this.order = orderDetail
                        console.log(this.order)
                    }
                    else{
                        console.log("订单信息对应失败")
                    }
                 //通过订单的id获取订单
                 const bookResponse = await reqGetBook(bookId);
                 console.log(bookResponse);

                 if(bookResponse.data.code===1){
                     const bookDetail = bookResponse.data.data;
                     this.book = bookDetail
                     console.log(this.order)
                 }
                 else{
                     console.log("书籍信息对应失败")
                 }
             }
             finally {
                 console.log("更新结束")
             }
         },
}}
</script>

<style scoped>
h1{
    color: #757575;
    font-family: 新宋体;
    margin-left: 35px;
    margin-bottom: 20px;
    margin-top: 30px;
}
.content{
    margin: 30px auto 30px;
    width: 100%;
    box-sizing: border-box;
    background-color: white;
}
.detail_title{
    width: 940px;
    height: 90px;
    margin: 0px auto;
    line-height: 90px;
    background-color: #f2f6fc;
    border: 1px solid #dcdcdc;
    border-bottom: none;
    padding: 0px 15px;
}
.info{
    width: 940px;
    margin: 0px auto;
    padding: 0px 20px 20px;
    border: 1px solid #dcdcdc;
    border-top: none;
}
.info_title{
    width: 100%;
    height: 60px;
    margin: 0px auto;
    line-height: 60px;
    padding: 0px 15px;
}
.table-layout {
    border-left: 1px solid #DCDFE6;
    border-top: 1px solid #DCDFE6;
}
.table-cell {
    height: 60px;
    line-height: 40px;
    border-right: 1px solid #DCDFE6;
    border-bottom: 1px solid #DCDFE6;
    padding: 10px;
    font-size: 14px;
    color: #606266;
    text-align: center;
    overflow: hidden;
}

.table-cell-title {
    border-right: 1px solid #DCDFE6;
    border-bottom: 1px solid #DCDFE6;
    padding: 10px;
    background: #F2F6FC;
    text-align: center;
    font-size: 14px;
    color: #303133;
}
</style>
