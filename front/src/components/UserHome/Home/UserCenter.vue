<template>
    <div class="content">
        <div class="info">
            <div class="user_card">
                <el-image style="width: 160px; height: 160px;vertical-align: middle;border-radius: 50%;float: left;margin: 20px 10px"
                          :src="user.img"
                          fit="fill"></el-image>
                <div class="user_card_info">
                    <p style="font-size: 22px;color: #616161">{{user.account}}</p>
                    <p><span class="link" @click="gotoModUserInfo">修改个人信息></span></p>
                </div>
            </div>
            <div class="user_action">
                <p style="font-size: 11px;color: #616161"> <span>余额:</span>{{user.balance}}</p>
                <p style="font-size: 11px;color: #616161"> <span>绑定手机:</span>{{user.phone}}</p>
                <p style="font-size: 11px;color: #616161"> <span>校区:</span>{{user.area}}</p>
                <p style="font-size: 11px;color: #616161"> <span>简介:</span>{{user.introduction}}</p>
            </div>
        </div>

            <div class="info-list">
                <el-image style="width: 120px; height: 120px;vertical-align: middle;border-radius: 50%;float: left;margin: 15px 10px"
                          :src="imgS2"
                          fit="fill"></el-image>
                <div class="list_info">
                    <router-link to="/user/userOrder">
                    <p> <span class="title">查看订单:</span></p>
                    </router-link>
                </div>
            </div>


        </div>
        <div id="myChart" v-show="false" :style="{width: '300px', height: '300px'}"></div>
</template>

<script>
// <!--用户中心-->

import {reqGetUserInfo} from "@/api/user";

export default {
    name: "UserCenter",
    data () {
        return {
            msg: 'Welcome to Your Vue.js App',
            imgS: require('../../../assets/image/head.jpg'),
            imgS1: require('../../../assets/image/icon1.png'),
            imgS2: require('../../../assets/image/icon2.png'),
            imgS3: require('../../../assets/image/icon3.png'),
            imgS4: require('../../../assets/image/icon4.png'),
            user:{
                id:'',
                account: "",
                password:"",
                manage: false,
                img:"",
                gender: "",
                balance: "",
                area:'',
                address:"",
                phone:"",
                introduction:"",
            }
        }
    },
    created(){
        this.initUserCenter();
    },
    methods: {
        initUserCenter(){
            reqGetUserInfo().then(response=>{
                console.log(response);
                if(response.data.code==1){
                    this.user = response.data.data;
                }else{
                    this.$message({
                        message: response.msg,
                        type: "warning"
                    })
                }
                // eslint-disable-next-line no-unused-vars
            }).catch(err=>{
                this.$message({
                    message: "获取数据错误了，请检查网络是否连接",
                    type: "warning"
                })
            })
        },
        //操作表格
        gotoModUserInfo(){
            this.$router.push({
                path: "/user/userInfo",
            })
        },
    }
}
</script>

<style scoped>

.content{
    width: 1000px;
    margin: 40px auto;
    background-color: white;
}

.info{
    width: 940px;
    height: 300px;
    margin:auto;
    background-color: #ffffff;
    border-bottom: 1px solid #d9d9d9;
}

.user_card{
    width: 540px;
    height: 300px;
    float: left;
}

.user_card_info{
    margin: 75px ;
    width: 200px;
    height: 100px;
    display: inline-block;
    line-height: 30px;
}
.user_action{
    float: left;
    width: 400px;
    height: 300px;
    padding-top: 65px;
}
.link{
    font-size: 14px;
    color: #ff6700;
}
span{
    color: #757575;
    font-size: 15px;
    margin:  5px;
    line-height: 30px;
}

.portal-sub{
    padding-top: 20px;
    width: 940px;
    height: 400px;
    margin:  auto;
}

.info-list{
    margin: 15px 4px;
    box-sizing: border-box;
    width: 460px;
    height: 150px;
    float: left;
}
.list_info{
    width: 300px;
    height: 150px;
    padding: 40px 10px;
    display: inline-block;
}
.num{
    color: #ff6700;
}
.title{
    font-size: 20px;
    color: #757575;
}
</style>
