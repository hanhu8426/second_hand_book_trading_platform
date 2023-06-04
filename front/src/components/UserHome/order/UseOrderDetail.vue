<template>
    <div class="content">
        <div class="detail_title">
            <span style="color: red">
                <p v-if="orderItem.order.status===1" @click="testFunc">待收货</p>
                <p v-if="orderItem.order.status===2">已完成</p>
                <span style="float: right"></span>
      </span>
        </div>
        <div class="info">
            <div class="info_title">
                <i class="el-icon-s-flag"></i><span> 基本信息</span>
            </div>
            <div class="table-layout">
                <el-row>
                    <el-col :span="7" class="table-cell-title">订单编号</el-col>
                    <el-col :span="6" class="table-cell-title">用户账号</el-col>
                    <el-col :span="5" class="table-cell-title">下单时间</el-col>
                    <el-col :span="6" class="table-cell-title">收货时间</el-col>
                </el-row>
                <el-row>
                    <el-col :span="8" class="table-cell">{{orderItem.order.orderId}}</el-col>
                    <el-col :span="8" class="table-cell">{{orderItem.order.beginTime}}</el-col>
                    <el-col :span="8" class="table-cell">{{orderItem.order.endTime}}</el-col>
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
                    <el-col :span="4" class="table-cell">{{orderItem.order.name}}</el-col>
                    <el-col :span="4" class="table-cell">{{orderItem.order.phone}}</el-col>
                    <el-col :span="13" class="table-cell">{{orderItem.order.address}}</el-col>
                    <el-col :span="3" class="table-cell">{{orderItem.book.campus}}</el-col>
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
                    <el-col :span="4" class="table-cell"><el-image :src="orderItem.book.image"></el-image></el-col>
                    <el-col :span="4" class="table-cell">{{orderItem.order.phone}}</el-col>
                    <el-col :span="4" class="table-cell">{{orderItem.order.phone}}</el-col>
                    <el-col :span="4" class="table-cell">{{orderItem.order.phone}}</el-col>
                    <el-col :span="4" class="table-cell">{{orderItem.order.phone}}</el-col>
                    <el-col :span="4" class="table-cell">{{orderItem.order.phone}}</el-col>

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
                    <el-col :span="12" class="table-cell">{{orderItem.book.price}}</el-col>
                    <el-col :span="12" class="table-cell">{{orderItem.book.price}}</el-col>
                </el-row>
            </div>

        </div>
    </div>
</template>

<script>
// import {reqAdminGetOrderDetail} from "../../../api/order";

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
            orderItem: {
                order: {
                    orderId:'',
                    buyerId:'',
                    sellerId: 2,
                    name: '黄文敬',
                    phone: '1235444755',
                    address: '梅园一栋201',
                    bookId: '2',
                    beginTime: '2020.2.21',
                    endTime: '2020.3.21',
                    status: 1,
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
            },
            user:localStorage.getItem("user")
        }},
     created() {
       this.getInit(this.$route.query.orderId,this.$route.query.bookId)
       console.log("开始组件构建")
     },
     methods:{

         getInit(orderId,bookId){
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
                     this.orderItem.order = response.data.data
                     console.log(this.orderItem.order)
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

         getOrderList(){
             let message = this.$route.query.message;
             console.log(message)
    //         this.loading=false;
    //         reqAdminGetOrderDetail(id).then(response=>{
    //             if(response.code==200){
    //                 console.log(response.orderDto);
    //                 this.order = response.orderDto;
    //             }else {
    //                 this.$message({
    //                     message: "获取订单明细出错了！",
    //                     type: "warning"
    //                 })
    //             }
    //         }).catch(err=>{
    //             console.log(err);
    //         })
         },
    // }
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
