<template>
  <div style="background-color: #f7f7f6;width: 100%;min-width: 1240px">
    <Nav></Nav>
    <HeadNav></HeadNav>

     <div class="content">
      <div class="title"><h3>收货地址</h3></div>
      <div class="list_box">
        <!--           v-bind:class="{selected_address:address.select}"-->
        <div class="address_list" v-for="(address,index) in addressList"

             :key="address.addId">
          <div class="name">{{address.name}}
            <span style="float: right;font-size: 14px;color: #757575;">{{address.label}}</span>
          </div>
          <div class="tel">{{address.phone}}</div>
          <div class="detail">{{address.address}}</div>
          <div>
            <el-button @click="selectOrderAddress(index)">选择</el-button>
          </div>
        </div>
        <div class="address_list">
          <div class="add">
            <i class="el-icon-edit"></i>
            <div>
              <el-button @click="handleAdd()"><p style="font-size: 20px">添加新地址</p></el-button>
            </div>
          </div>
        </div>
      </div>

      <div class="title"><h3>商品清单</h3></div>
      <div class="list_box" >
        <div class="bookInfo">
          <div class="book_item">
            <el-image class="bookImg" :src="book.image" fit="fill"></el-image>
            <div class="book_detail">
              <p>{{book.name}} {{book.author}}</p>
            </div>
          </div>
          <div class="book_num">
            <p>{{book.price}} x 1</p>
          </div>
          <div class="book_money">
            <p class="height_text">{{book.price}}元</p>
          </div>
        </div>
      </div>
      <div class="title"><h3 style="float: left;width: 100px">配送方式</h3><span class="height_text" style="margin-left: 60px">包邮</span></div>
      <div class="title"><h3 style="float: left;width: 100px">发票</h3><span class="height_text" style="margin-left: 60px">电子普通发票个人商品明细</span></div>
      <div class="list_box">
        <div class="order_detail">
          <div class="bill_item">
            <div class="bill_title">商品总价:</div>
            <div class="bill_money height_text">{{book.price}}元</div>
          </div>
          <div class="bill_item">
            <div class="bill_title">应付总额:</div>
            <div class="bill_money height_text" style="font-size: 22px">{{book.price}}元</div>
          </div>
        </div>
      </div>
      <div class="list_box">
        <div class="order_action">
          <div class="address_box">
            <p>{{address.name}} {{address.phone}}</p>
            <p>{{address.address}}<span class="height_text">修改</span></p>
          </div>
          <div class="action">
            <button class="plainBtn" style="background-color: #ff6600;color: white" @click="submitOrder">立刻下单</button>
            <!--              <button class="plainBtn">返回购物车</button>-->
          </div>
        </div>
      </div>
    </div>
    <Footer></Footer>

    <!--添加地址的弹出框-->
    <el-dialog title="添加收货地址" v-model="dialogVisible" width="30%"  center>
      <el-form ref="form" :model="address" >
        <el-form-item>
          <el-input placeholder="姓名" v-model="newAddress.name"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input placeholder="手机号" v-model="newAddress.phone"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input type="textarea" placeholder="详细地址" v-model="newAddress.address"></el-input>
        </el-form-item>
        <el-form-item label="校区">
          <el-select v-model="newAddress.area" placeholder="请选择校区">
            <el-option label="九龙湖校区" value="九龙湖校区"></el-option>
            <el-option label="四牌楼校区" value="四牌楼校区"></el-option>
            <el-option label="丁家桥校区" value="丁家桥校区"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit('form')">确 定</el-button>
      </span>
    </el-dialog>


  </div>
</template>

<script>
import Nav from "../../components/Common/NavGation.vue";
import HeadNav from "../../components/Common/NavHeader.vue";
import Footer from "../../components/Common/FooTer.vue";
import {reqAddAddress, reqGetAddressList} from "@/api/address";
import {reqGetBook} from "@/api/book";
import {reqAddOrder} from "@/api/order";


export default {
  name: "BuyPage",
  components:{ Nav,HeadNav,Footer},
  data() {
    return {
      account: "",
      book:null,
      bookId:null,
      selectId: null,
      dialogVisible: false,
      addressList: [
        {
          id: 1,
          name: "小胖",
          phone: "18988798892",
          addr: "江西抚州市临川区西大街街道东华理工大学长江学院本部(330006)",
          area: 1,
          off: false,
          select: false,
        },
        {
          id: 2,
          name: "小胖",
          phone: "18988798892",
          addr: "江西抚州市临川区西大街街道东华理工大学长江学院本部(330006)",
          area: 1,
          off: false,
          select: false
        },
      ],
      //选中的地址
      address: {
        id: null,
        name: "",
        phone: "",
        address: "",
        area: null,
      },

      //新增地址
      newAddress: {
        id: null,
        name: "",
        phone: "",
        address: "",
        area: null,
      },
    }
  },
  created(){
    this.bookId = this.$route.params.bookId;
    this.account = this.$store.getters.getUser.account;
    this.address.id = this.$store.getters.getUser.id;
    this.getBook(this.bookId);
    this.getAddressList();
    console.log("接收到参数book："+ this.book);
  },
  methods:{
    //处理书的信息
    getBook(bookId){
      console.log("我已经传入了bookId：值为："+ bookId);
      reqGetBook(bookId).then(response=>{
        if(response.data.code===1)
        {
          console.log("接受到code,开始为book赋值：")
          // console.log(response.book);
          this.book = response.data.data;
          console.log("接收到的内容为："+ this.book)
        }
      }).catch(err=>{
        console.log(err);
      })
    },

    //处理添加操作
    handleAdd(){
      this.dialogVisible = true;
      this.isEdit = false;
    },

    // 提交表单
    onSubmit() {
      if (this.isEdit) {
        this.modifyAddress();
      } else {
        this.address = this.newAddress;
        this.addAddress();
        this.dialogVisible = false;
      }
    },

    //添加地址
    addAddress(){
      reqAddAddress(this.address).then(response=>{
        console.log("传的地址中地址信息为："+this.address.address)
        console.log(response);
        if(response.data.code === 1){
          this.$message({
            message: response.data.msg,
            type: "success"
          });
          this.dialogVisible = false;
          this.getAddressList();
        }else{
          this.$message({
            message: response.data.msg,
            type: "warning"
          })
        }
      }).catch(err=>{
        console.log(err);
      })
    },

    //得到用户地址列表
    getAddressList(){
      reqGetAddressList().then(response=>{
        console.log("已经接受到response:" + response);
        if(response.data.code===1){
          console.log("response.data:" + response.data);
          this.addressList = response.data.data;
          console.log("response.data.data:" + response.data.data);
          if(this.addressList.length>0){
            console.log(this.addressList[0]);
            this.address = this.addressList[0];//设置地址为排序第一的地址
            this.selectId = this.addressList[0].addId;//被选中的id
          }
          // console.log("===response.addressList.length==="+response.addressList.length);
        }else{
          this.$message({
            message: response.data.msg,
            type: "warning"
          })
        }
      }).catch(err=>{
        console.log(err);
      })
    },

    //选择订单地址
    selectOrderAddress(index){
      this.selectId = this.addressList[index].addId;
      this.address = this.addressList[index];
      // this.selectedAddressIndex = index; // 更新选中地址的索引
    },
    // isSelected(index) {
    //   return index === this.selectedAddressIndex; // 判断当前地址是否被选中
    // },

    //提交订单
    submitOrder(){
      this.account = this.$store.getters.getUser.account;
      console.log("====this.OrderInitDto.account===="+this.account+"=====")
      console.log(this.bookId)
      console.log(parseInt(this.bookId))
      console.log("对比实验")
      reqAddOrder(parseInt(this.selectId), parseInt(this.bookId)).then(response=>{
        if(response.data.code ===1){
          this.$message({
            type: 'success',
            message: response.data.msg,
            duration: 1000
          })
          setTimeout(() => {
            this.$router.push({path:'/user/userOrder'});
          }, 1000);
        }else if(response.data.code ===2){
          this.$message({
            message: "余额不足，请充值！！！",
            type: "warning"
          });
          this.$router.push({ path: '/userInfo' }); // 跳转到充值页面
        }
      }).catch(()=>{
        this.$message({
          message: "下单失败了",
          type: "warning"
        })
      })
    }


  }
}
</script>

<style scoped>
.content{
  width: 1240px;
  margin: 20px auto;
  background-color: white;
  padding-left: 10px;
  /*border: 1px solid #B3C0D1;*/
}
.title{
  width: 1230px;
  padding: 10px;
  line-height: 30px;
}
.list_box{
  width: 1230px;
  padding: 10px;
}
.address_list{
  width: 280px;
  height: 180px;
  border: 1px solid #cacaca;
  display: inline-block;
  margin-right: 18px;
  margin-bottom: 20px;
  padding: 20px;
  vertical-align: top;
}
.selected_address{
  border: 1px solid #ff6700;
  background-color: #f5f5f5;
}
.name{
  width: 240px;
  height: 40px;
  font-size: 18px;
}
.tel{
  width: 240px;
  height: 30px;
  font-size: 14px;
  color: #757575;
}
.detail{
  width: 240px;
  height: 30px;
  font-size: 14px;
  color: #757575;
}
.foot{
  width: 240px;
  height: 60px;
  font-size: 14px;
  color: #ff6700;
  line-height: 60px;
}
.add{
  width:80px;
  height: 80px;
  margin-top: 40px;
  margin-left: 75px;
  font-size: 30px;
  color: #757575;
  text-align: center;
}

.bookInfo{
  width: 1200px;
  height: 125px;
  padding: 10px;
  border-bottom: 1px solid #B3C0D1;
}
.book_item{
  width: 660px;
  height: 105px;
  float: left;
}
.bookImg{
  float: left;
  width: 80px;
  height: 105px;
}
.book_detail{
  margin: 25px 20px;
  height: 55px;
  line-height: 55px;
  float: left;
  font-size: 14px;
}
.book_num{
  width: 360px;
  font-size: 14px;
  float: left;
  height: 105px;
  line-height: 105px;
}
.book_money{
  width: 150px;
  font-size: 14px;
  float: left;
  height: 105px;
  line-height: 105px;
}
.height_text{
  color: #ff6700;
}

.order_detail{
  margin-left: 940px;
  width: 250px;
  height: 220px;
}
.bill_item{
  line-height: 35px;
}
.bill_title{
  width: 145px;
  text-align: right;
  float: left;
}
.bill_money{
  width: 95px;
  text-align: right;
  float: left;
}
.order_action{
  width: 1210px;
  padding-left:20px;
  padding-right: 20px;
  padding-top: 5px;
  border-top: 1px solid #B3C0D1;
  height: 50px;
}
.address_box{
  width: 700px;
  height: 50px;
  line-height: 23px;
  float: left;
  font-size: 14px;
  color: #757575;
}
.action{
  width: 400px;
  height: 50px;
  float: right;
  vertical-align: center;
  padding-top: 5px;
}
.plainBtn{
  width: 120px;
  height: 35px;
  background-color: white;
  color: #757575;
  border: 1px #cacaca solid;
  float: right;
  margin-left: 10px;
}
.plainBtn:hover{
  border: 1px #a2a4ac solid;
  background-color: #e7e7e7;
}
</style>
