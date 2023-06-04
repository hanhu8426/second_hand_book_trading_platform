<template>
    <div class="content">
        <h1>我的订单</h1>
        <div class="box_info">
            <el-tabs v-model="activeName" @tab-click="handleClick">
                <el-tab-pane label="全部有效订单" name="first">
                    <div class="tab_box">
                        <div class="order_list" v-for="(orderItem,index) in orderAndBookList" :key="index">
                            <div class="order_summary">
                                <div>
                                <p class="order_status" v-if="orderItem.order.status===1">订单待收货</p>
                                <p class="order_status" v-if="orderItem.order.status===2">订单已完成</p>
                                </div>
                                <p class="caption-info">
                                    {{orderItem.order.beginTime}}
                                    <span>|</span>
                                    {{orderItem.order.name}}
                                    <span>|</span>
                                    订单号：{{orderItem.order.orderId}}
                                    <span style="float: right">实付金额： <span class="money">{{orderItem.book.price}} </span>元</span>
                                </p>
                            </div>
                            <div class="bookInfo">
                                <div class="book_item">
                                    <el-image class="bookImg"  :src="orderItem.book.image" fit="fill"></el-image>
                                </div>
                                <div class="book_action">
                                    <button class="plainBtn" v-show="orderItem.order.status===2" @click="handleChange(orderItem)">确认收货</button>
                                    <br>
                                    <button class="plainBtn"  @click="goToOrderDetail(orderItem)">订单详情</button>
                                    <br>
                                </div>
                            </div>
                        </div>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="待收货" name="third">
                    <div class="tab_box" v-show="total<1">
                        <p class="noMesInfo" v-show="true">暂无数据</p>
                    </div>
                    <div class="tab_box" v-show="total>0">
                        <div class="order_list" v-for="order in orderList" :key="order.id">
                            <div class="order_summary">
                                <p class="order_status">{{order.orderStatus}}</p>
                                <p class="caption-info">
                                    {{order.orderTime}}
                                    <span>|</span>
                                    {{order.address.name}}
                                    <span>|</span>
                                    订单号：{{order.orderId}}

                                    <span style="float: right">实付金额： <span class="money">{{order.expense.finallyPrice}} </span>元</span>
                                </p>
                            </div>
                            <div class="bookInfo">
                                <div class="book_item">
                                    <el-image class="bookImg" v-for="(img,index) in order.coverImgList" :src="img" :key="index" fit="fill"></el-image>
                                </div>
                                <div class="book_action">
                                    <button class="plainBtn" @click="goToOrderDetail(order.id)">订单详情</button>
                                    <br>
                                    <button class="plainBtn" @click="changeStatus()">确认收货</button>
                                    <br>
                                    <button class="plainBtn">联系客服</button>
                                    <br>
                                </div>
                            </div>
                        </div>
                    </div>
                </el-tab-pane>
            </el-tabs>
        </div>
        <div style="margin: 10px 0px 20px;width: 100%" v-show="total>0">
            <div class="block" style="float: right">
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-sizes="[5, 10, 20, 50]"
                    :page-size="page_size"
                    background
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
                </el-pagination>
            </div>
        </div>
    </div>
</template>
<script>
// <!--  reqUserGetOrderList-->
// import {reqUserGetOrderList,reqModOrderStatus} from "../../../api/order";
// <!--用户订单页面-->
import {reqModOrderStatus, reqUserOrders} from "@/api/user";
import {reqGetBook} from "@/api/book";

export default {
    name: "UserOrder",
    data() {
        return {
            showDetail:false,
            activeName: 'first',
            currentPage: 1,
            page_size: 5,
            total:20,
            //包含书和订单的数组

            orderAndBookList:[
                {
                    order:{
                        orderId:54564651321,
                        buyerId:5,
                        sellerId:2,
                        name:'黄文敬',
                        phone:'1235444755',
                        address:'梅园一栋测试测试测试',
                        bookId:'2',
                        beginTime:'2020.2.21',
                        endTime:'2020.3.21',
                        status:2,
                    },
                    book:{
                        bookId: '2',
                        name: "我的名字",
                        author: "李贵林",
                        isbn: "564564212",
                        type: 1,
                        description: "这是一本不知道用来干嘛的书",
                        status: 0,
                        image: "#",
                        campus: "九龙湖校区",
                        price: 15,
                        recommend: "1",
                        sellerID: "12346",
                    },
                },
            ],
            orderList:[
                {
                    id:1,
                    orderId:1,
                    account:"刘德华",
                    orderTime:10.21,
                    shipTime:11.21,
                    getTime:12.21,
                    coverImgList:[],
                    orderDetailDtoList:[
                        {
                            book:{
                                id: 5,
                                bookName: '我的名字',
                                author: '李贵林',
                                isbn: '123456798878',
                                price: '52',
                                description: '这是一本用来测试的书籍',
                                put: true,
                                coverImg: '',
                            },
                            num:1,
                            price: 52,
                        }
                    ],
                    expense:{
                        orderId:846546545132,//订单编号
                        productTotalMoney:52,//商品总价
                        freight:null,//运费 默认为0元
                        finallyPrice:null,//最终实付总额
                    },
                    address:{
                        id: 1,
                        account: "黄小龙",
                        name: "小胖",
                        phone: "18988798892",
                        addr: "江西抚州市临川区西大街街道东华理工大学长江学院本部(330006)",
                        label: "家",
                        off: false,
                    }
                },
            ],
            beUserDelete: false
        };
    },
     created(){
         console.log("开始构建订单组件")
         this.function_getList();
     },
    methods: {



        // eslint-disable-next-line no-unused-vars
        handleClick(tab, event) {
            console.log("=====this.activeName===="+this.activeName+"=======");
            switch (this.activeName) {
                case "first":
                    this.orderStatus="";
                    this.beUserDelete=false;
                    break;
                case "third":
                    this.orderStatus="已发货";
                    this.beUserDelete=false;
                    break;
                case "four":
                    this.orderStatus="";
                    this.beUserDelete=true;
                    break;
            }
            console.log("=====this.orderStatus====="+this.orderStatus+"======")
            this.getOrderList(1,5);
        },
        //确认收货
        handleChange(orderItem){
            this.$router.push({path:'/user/userOrder/userOrderDetail'});
            console.log(this.showDetail)
            console.log(orderItem.order.status)
            orderItem.orderStatus=1;
            console.log(orderItem.order.status)
            console.log(orderItem)
            console.log("收货成功")
        },
        //跳转到订单明细页面
        goToOrderDetail(orderItem) {
            console.log("=====跳转到订单详情页========")
            this.$router.push({
                path:'/user/userOrder/userOrderDetail',
                query: {
                    orderId: orderItem.order.orderId,
                    bookId:orderItem.book.bookId,
                }
            });
        },
        async function_getList() {
            try {
                const orderResponse = await reqUserOrders(); // 发起订单请求
                console.log(orderResponse);

                if (orderResponse.data.code === 1) {
                    const orderList = orderResponse.data.data;
                    const length = orderList.length;

                    for (let i = 0; i < length; i++) {
                        const bookResponse = await reqGetBook(orderList[i].bookId); // 发起获取书籍请求
                        console.log(bookResponse);

                        if (bookResponse.data.code === 1) {
                            console.log("获取书籍成功");
                            const order = orderList[i];
                            const book = bookResponse.data.data;
                            this.orderAndBookList.push({ order, book });
                            console.log(this.orderAndBookList[i].order);
                            console.log(this.orderAndBookList[i].book);
                        } else {
                            console.log("获取书籍失败");
                            this.$message({
                                message: bookResponse.message,
                                type: "warning",
                            });
                        }
                    }

                    console.log("订单获取成功");
                } else {
                    this.$message({
                        message: orderResponse.message,
                        type: "warning",
                    });
                }

                console.log("订单所有信息更新成功");
            } catch (error) {
                console.log(error);
            }
        },


        // getList(){
        //   reqUserOrders().then(response=>{
        //       console.log(response);
        //       if(response.data.code===1){
        //           var orderList = response.data.data
        //           var length = orderList.length()
        //           for(var i=0;i<length;i++){
        //               this.orderAndBookList[i].push({order:response.data.order,book:null})
        //         }
        //           for(var m=0;m<length;m++){
        //               console.log(this.orderAndBookList[m].order)
        //           }
        //           console.log("订单获取成功")
        //       }
        //       else {
        //           this.$message({
        //               message: response.message,
        //               type: "warning"
        //           })
        //       }
        //   }).catch(error=>{
        //       console.log(error)
        //   });
        //
        //   var termArray = this.orderAndBookList;
        //   var newLength = termArray.length
        //   for(var n=0;n<newLength;n++){
        //         reqGetBook(this.orderAndBookList[n].order.bookId).then(response=>{
        //            console.log(response)
        //            if(response.data.code===1){
        //                console.log("获取书籍成功")
        //                this.orderAndBookList[n].book=response.data.book
        //                console.log(this.orderAndBookList[n].book)
        //            }
        //            else {
        //                console.log("获取书籍失败")
        //                this.$message({
        //                    message: response.message,
        //                    type: "warning"
        //                })
        //            }
        //        }).catch(error=>{
        //            console.log(error)
        //        })
        //     }
        //     console.log("订单所有信息更新成功")
        // },
        // //分页函数
        // handleSizeChange(val) {
        //     console.log(`每页 ${val} 条`);
        //     this.page_size = val;
        //     this.getOrderList(1,this.page_size);
        // },
        // handleCurrentChange(val) {
        //     console.log(`当前页: ${val}`);
        //     this.currentPage = val;
        //     console.log(this.currentPage+":"+this.page_size);
        //     this.getOrderList(this.currentPage,this.page_size);
        // },
        // //得到订单列表
        // getOrderList(page,pageSize){
        //     let account= this.$store.getters.getUser.account;
        //     reqUserGetOrderList(account,page,pageSize,this.orderStatus,this.beUserDelete).then(response=>{
        //         if(response.code==200){
        //             this.total = response.total;
        //             console.log(this.total);
        //             this.orderList = response.orderDtoList;
        //         }else {
        //             this.$message({
        //                 message: response.message,
        //                 type: "warning"
        //             })
        //         }
        //         // eslint-disable-next-line no-unused-vars
        //     }).catch(err=>{
        //         this.$message({
        //             message: "获取用户订单出错了",
        //             type: "warning"
        //         })
        //     })
        // },
        //
        // //进行订单收货
        getOrder(id){
            this.$confirm('确认收货码?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                reqModOrderStatus(id).then(response=>{
                    if(response.code==200){
                        this.$message({
                            message: response.message,
                            type: "success"
                        })
                    }else {
                        this.$message({
                            message: response.message,
                            type: "warning"
                        })
                    }
                    // eslint-disable-next-line no-unused-vars
                }).catch(err=>{
                    this.$message({
                        message: "确认收货出错了",
                        type: "warning"
                    })
                })
            }).catch(()=>{
                this.$message({
                    message: "取消确认收货",
                    type: "warning"
                })
            });
        },
    },
}
</script>

<style scoped>

.content{
    margin: 10px auto;
    width:1000px;
    background-color: white;
    padding: 30px 20px;
}
h1{
    color: #757575;
    font-family: 新宋体;
}
.box_info{
    width: 960px;
    margin: 10px auto;
}
/deep/ .el-tabs__item {
    height: 50px;
    line-height: 50px;
    font-size: 16px;
    color: #757575;
}
.tab_box{
    width:960px;
}

.order_list{
    border: 1px #f3f0e9 solid;
    margin: 12px auto;
    width: 960px;
    height: 250px;
    max-height: 240px;
    line-height: 35px;
    border: 1px solid #cacaca;
}
.order_summary{
    width: 960px;
    height: 105px;
    padding: 15px 25px;
    border-bottom: 1px solid #e7e7e7;
}
.order_status{
    font-size: 22px;
    color: #757575;
}
.caption-info{
    font-size: 14px;
    color: #757575;
}
.money{
    font-size: 23px;
    color: black;
}
.bookInfo{
    width: 960px;
    height: 145px;
    padding: 15px 25px;
}
.book_item{
    width: 660px;
    height: 105px;
    float: left;
}
.bookImg{
    float: left;
    margin-top: 10px;
    width: 60px;
    height: 85px;
    margin-right: 15px;
}
.book_detail{
    margin: 25px 20px;
    height: 55px;
    line-height: 27.5px;
    float: left;
    font-size: 14px;
}
.book_action{
    width: 250px;
    font-size: 14px;
    color: #757575;
    float: left;
    height: 105px;
}
.plainBtn{
    width: 120px;
    height: 30px;
    margin: 2px 0px;
    background-color: white;
    color: #757575;
    border: 1px #cacaca solid;
    float: right;
}
.plainBtn:hover{
    border: 1px #393a42 solid;
    background-color: #e7e7e7;
}
.noMesInfo{
    text-align: center;
    font-size: 18px;
    color: #757575;
    line-height: 60px
}

</style>
