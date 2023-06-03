<template>
    <div class="content">
        <h1>收货地址</h1>
        <div class="box_info">
            <div class="address_list">
                <div class="add" @click="handleAdd">
                    <i class="el-icon-edit"></i>
                    <p style="font-size: 14px">添加新地址</p>
                </div>
            </div>
            <div class="address_list" v-for="address in addressList" :key="address.addId">
                <div class="name">{{address.name}}
                    <span style="float: right;font-size: 14px;color: #757575;">{{address.area}}</span>
                </div>
                <span class="tel">{{address.phone}}</span>
                <span class="detail">{{address.addr}}</span>
                <div class="foot">
                    <span style="float: right" @click="delAddress(address.addId)">删除</span>
                    <span style="float: right;margin-right: 10px" @click="handleMod(address)">修改</span>
                </div>
            </div>
        </div>

        <!--添加图书的弹出框-->
        <el-dialog title="添加收货地址" v-model="dialogVisible" width="30%"  center>
            <el-form ref="form" :model="address" >
                <el-form-item>
                    <el-input placeholder="姓名" v-model="address.name"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input placeholder="手机号" v-model="address.phone"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input type="textarea" placeholder="详细地址" v-model="address.addr"></el-input>
                </el-form-item>
                <el-form-item prop="area">
                    <el-dropdown  style="width: 100%;">
                        <el-select v-model="address.area" placeholder="请选择校区">
                            <el-option label="四牌楼校区" value="四牌楼校区" ></el-option>
                            <el-option label="九龙湖校区" value="九龙湖校区"></el-option>
                            <el-option label="丁家桥校区" value="丁家桥校区"></el-option>
                        </el-select>
                    </el-dropdown>
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
import {reqDelAddress,reqAddAddress,reqGetAddressList,reqModAddress} from "../../../api/address";
// <!--用户地址页面-->
export default {
    name: "AddressPage",
    data() {
        return {
            dialogVisible: false,
            isEdit:false,//用来判断是添加地址还是修改地址 false:添加 true:修改
            addressList:[
            ],
            address:{
                addId:"",
                name: "",
                phone: "",
                addr: "",
                area: "",
            },
        };
    },
    created(){
        console.log("开始生命构建，获取该用户的所有地址数据")
        this.getAddressList();
    },
    mounted() {
      console.log("重新渲染页面")
        this.getAddressList();
    },
    methods: {
        //处理添加操作
        handleAdd(){
            this.dialogVisible = true;
            this.isEdit = false;
        },
        //处理修改
        handleMod(addr){
            this.dialogVisible = true;
            this.isEdit = true;
            this.address.addId = addr.addId;
            this.address.account = addr.account;
            this.address.name = addr.name;
            this.address.phone = addr.phone;
            this.address.addr = addr.addr;
            this.address.label = addr.label;
        },

        //提交处理
        onSubmit() {
            if(this.isEdit){
                this.modifyAddress();
            }else {
                this.addAddress();
            }
        },

        //得到用户地址列表
        getAddressList(){
            console.log("===获取的地址列表：==="+this.$store.getters.getUser.account+"=====");
            reqGetAddressList().then(response=>{
                console.log(response);
                if(response.data.code==1){
                    let addList = response.data.data
                    console.log(addList)
                    this.addressList = addList
                }else{
                    this.$message({
                        message: response.message,
                        type: "warning"
                    })
                }
            }).catch(err=>{
                console.log(err);
            })
        },

        //添加地址
        addAddress() {
            console.log("尝试添加地址")
            console.log(this.address)
                    reqAddAddress(this.address).then(response => {
                        console.log(response);
                        if (response.data.code == 1) {
                            this.$message({
                                message: response.message,
                                type: "success"
                            });
                            this.dialogVisible = false;
                            this.getAddressList();
                        } else {
                            this.$message({
                                message: response.message,
                                type: "warning"
                            })
                        }
                    }).catch(err => {
                        console.log(err);
                    })
                },

        //修改地址
        modifyAddress(){
            console.log("这是修改地址的测试")
            console.log(this.address)
            reqModAddress(this.address).then(response=>{
                console.log(response);
                if(response.data.code==1){
                    this.$message({
                        message: response.message,
                        type: "success"
                    });
                    this.dialogVisible = false;
                    this.getAddressList();
                }else{
                    this.$message({
                        message: response.message,
                        type: "warning"
                    })
                }
            }).catch(err=>{
                console.log(err);
            })
        },
        //处理删除地址
        delAddress(id){
            this.$confirm('是否要进行删除操作?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                reqDelAddress(id).then(response=>{
                    console.log(response);
                    if(response.data.code==1){
                        this.$message({
                            message: response.message,
                            type: "success"
                        });
                        this.getAddressList();
                    }else{
                        this.$message({
                            message: response.message,
                            type: "warning"
                        })
                    }
                }).catch(err=>{
                    console.log(err);
                })
            }).catch(()=>{
                console.log("取消删除了");
            });
        },

        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    }
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
.address_list{
    width: 280px;
    height: 180px;
    border: 1px solid #cacaca;
    display: inline-block;
    margin-right: 20px;
    margin-top: 20px;
    padding: 20px;
    vertical-align: top;
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
</style>
