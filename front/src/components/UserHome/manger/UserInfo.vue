<template>
    <div class="content">
        <div class="info">
            <div class="user_card">
                <el-image style="width: 160px; height: 160px;vertical-align: middle;border-radius: 50%;float: left;margin: 20px 10px"
                          :src="imgS"
                          fit="fill"></el-image>
                <div class="user_card_info">
                    <p style="font-size: 22px;color: #616161">一条小黄龙</p>
                    <p style="color: #757575">修改头像</p>
                </div>
            </div>
            <div class="user_action">
                <p> <span>账号安全:</span><span>普通</span></p>
                <p> <span>绑定手机:</span><span>{{ this.form_1.phone }}</span></p>
                <p> <span>余额:</span><span>{{ this.balance }}</span></p>
            </div>
        </div>

        <div class="box_info">
            <el-tabs v-model="activeName" @tab-click="handleClick" stretch:true class="tabContainer">
                <el-tab-pane label="账号安全" name="first">
                    <div class="tab_box">
                        <div class="modify_box">
                            <el-form :model="form_1" status-icon ref="form_1" label-width="80px">

                                <el-form-item prop="phone" label="电话号码">
                                    <el-input type="number" placeholder="输入你的手机号码"></el-input>
                                </el-form-item>
                                <el-form-item prop="charge" label="充值">
                                    <el-input type="number" placeholder="输入本次充值的数额"></el-input>
                                </el-form-item>
                                <el-form-item>
                                    <el-button type="primary" style="width: 120px;" native-type="submit" @click="submitForm_1">确认充值</el-button>
                                </el-form-item>
                            </el-form>
                    </div>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="个人信息" name="second">
                    <div class="tab_box">
                        <div class="modify_box">
                            <el-form :model="form_2" status-icon  ref="form_2" label-width="80px">
                                <el-form-item prop="gender" label="性别">
                                    <el-dropdown  style="width: 100%;">
                                        <el-checkbox-group  v-model="gender" @change="genderChange">
                                            <el-checkbox
                                                v-for="item in genderOption"
                                                :key="item.type"
                                                :label="item.value"
                                            ></el-checkbox>
                                        </el-checkbox-group>
                                    </el-dropdown>
                                </el-form-item>
                                <el-form-item prop="area" label="校区">
                                    <el-dropdown  style="width: 100%;">
                                        <el-checkbox-group  v-model="area" @change="areaChange">
                                        <el-checkbox
                                            v-for="item in checkBoxOption"
                                            :key="item.value"
                                            :label="item.value"
                                        ></el-checkbox>
                                        </el-checkbox-group>
                                    </el-dropdown>
                                </el-form-item>
                                <el-form-item prop="introduction" label="简介">
                                    <el-input type="textarea" placeholder="说两句介绍自己吧"></el-input>
                                </el-form-item>
                                <el-form-item>
                                    <el-button type="primary" style="width: 120px;" native-type="submit" @click="submitForm_2">确认修改</el-button>
                                </el-form-item>
                            </el-form>
                        </div>
                    </div>
                </el-tab-pane>
            </el-tabs>
        </div>
    </div>
</template>

<script>
// <!--用户中心-->
import {reqModUserInfo_1, reqModUserInfo_2} from "@/api/user";

export default {
    name: "UserInfo",
    data () {
        var checkAccount = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('账号不能为空'));
            }
            setTimeout(() => {
                if(value.length>13){
                    callback(new Error('账号不能大于13位'));
                }else {
                    callback();
                }
            }, 1000);
        };
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入对应信息'));
            } else {
                callback();
            }
        };
        return {
            msg: 'Welcome to Your Vue.js App',
            imgS: require('../../../assets/image/head.jpg'),
            imgS1: require('../../../assets/image/weibo.png'),
            imgS2: require('../../../assets/image/qq.png'),
            imgS3: require('../../../assets/image/weixin.png'),
            imgS4: require('../../../assets/image/apple.png'),
            activeName: 'first',
            currentPage: 1,
            page_size: 2,
            total:20,
            balance:[],
            ruleForm: {
                account: '',
                password: '',
            },
            rules: {
                account: [
                    { validator: checkAccount, trigger: 'blur' }
                ],
                password: [
                    { validator: validatePass, trigger: 'blur' }
                ],

            },

            form_1: {
                phone: '',
                charge:'',
            },

            form_2:{
            area:[],
            checkBoxOption:[
               {
                   value:"四牌楼校区"
               },
               {
                   value:"九龙湖校区"
               },
               {
                   value:"丁家桥校区"
               },
           ],
            gender:[],
            genderOption:[
                {
                    value:"男",
                    type:0,
                },
                {
                    value: "女",
                    type: 1,
                }
            ],
                introduction:[],
            }
        }
    },
    mounted(){

    },
    methods: {
        handleClick(tab, event) {
            console.log(tab, event);
        },

        areaChange(){
            if(this.area.length > 1){
                this.area.splice(0,1)
            }
        },
        genderChange(){
            if(this.gender.length > 1){
                this.gender.splice(0,1)
            }
        },
        submitForm_1(){
            let _this = this;
            this.$refs["form_1"].validate((valid) => {
                if (valid) {
                    console.log("=====上传表格1=======")
                    reqModUserInfo_1({
                        phone: _this.form_1.phone,
                        charge: _this.form_1.charge,
                    }).then((response) => {
                        if(response.code == 200){//根据状态码进入下一步
                            console.log("=====上传成功=======")
                            let balanceNum=response.data.balance
                            this.balance =balanceNum
                            if(response.msg=="success"){
                                this.$message({
                                    type: 'success',
                                    message: "上传成功！",
                                    duration: 1000
                                })
                            }else {
                                this.$message({
                                    type: 'success',
                                    message: "上传成功！",
                                    duration: 1000
                                })
                                setTimeout(() => {
                                    this.$router.push({path:'/user/userCenter'});
                                }, 1000);
                            }
                        }else {
                            this.$message({
                                type: 'waring',
                                message: "登录失败"
                            })
                        }
                    }).catch(() => {
                        // this.$message.error("登录失败")
                    })
                } else {
                    //数据校验失败，不可以进行提交
                    this.$message.error("账号密码不符合要求，登陆失败");
                }
            });
        },
        submitForm_2(){
            let _this = this;
            this.$refs["form_2"].validate((valid) => {
                if (valid) {
                    console.log("=====上传表格2=======")
                    reqModUserInfo_2({
                        area:_this.form_2.area,
                        gender:_this.form_2.gender,
                        introduction: _this.form_2.introduction
                    }).then((response) => {
                        if(response.code == 200){//根据状态码进入下一步
                            console.log("=====上传成功=======")

                            if(response.msg=="success"){
                                this.$message({
                                    type: 'success',
                                    message: "上传成功！",
                                    duration: 1000
                                })
                            }else {
                                this.$message({
                                    type: 'success',
                                    message: "上传成功！",
                                    duration: 1000
                                })
                                setTimeout(() => {
                                    this.$router.push({path:'/user/userCenter'});
                                }, 1000);
                            }
                        }else {
                            this.$message({
                                type: 'waring',
                                message: "登录失败"
                            })
                        }
                    }).catch(() => {
                        // this.$message.error("登录失败")
                    })
                } else {
                    //数据校验失败，不可以进行提交
                    this.$message.error("账号密码不符合要求，登陆失败");
                }
            });
        }
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
    height: 200px;
    margin: auto;
    background-color: #ffffff;
    /*border-bottom: 1px solid #d9d9d9;*/
}

.user_card{
    width: 540px;
    height: 200px;
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
    height: 200px;
    padding-top: 65px;
}
span{
    color: #757575;
    font-size: 15px;
    margin:  5px;
    line-height: 30px;
}


h1{
    color: #757575;
    font-family: 新宋体;
}
.box_info{
    width: 960px;
    margin: 15px auto;
}
.el-tabs__item {
    height: 50px;
    line-height: 50px;
    font-size: 16px;
    color: #757575;
}
.tab_box{
    width:960px;
}

.noMesInfo{
    text-align: center;
    font-size: 18px;
    color: #757575;
    line-height: 60px
}

.secure_box{
    margin: 12px auto 0px;
    width: 960px;
    height: 100px;
    border-top: 1px solid #e5e5e5;
    /*border-bottom: 1px solid #e5e5e5;*/
}

.secure_logo{
    display: inline-block;
    width: 60px;
    height: 100px;
    line-height: 100px;
    text-align: center;
    font-size: 30px;
    color: #ff7f52;
    vertical-align: top;
}

.secure_title{
    display: inline-block;
    width: 700px;
    height: 100px;
    vertical-align: top;
    padding-top: 25px;
}

.secure_action{
    display: inline-block;
    width: 190px;
    height: 100px;
    text-align: right;
    line-height: 100px;
    vertical-align: top;
}

.text_msg{
    color: #757575;
    font-size: 14px;
    line-height: 23px;
}

.bind_list{
    width: 230px;
    height: 150px;
    /*border: 1px solid #cacaca;*/
    display: inline-block;
    margin-right: 30px;
    margin-top: 20px;
    padding: 20px 10px;
    vertical-align: top;
}
.bookImg{
    margin-top: 10px;
    float: left;
    width: 90px;
    height: 90px;
    border-radius: 10px;
}
.bind_account{
    float: left;
    width: 100px;
    height: 105px;
    padding-top: 15px;
    text-align: center;
}
.bind_btn{
    width: 80px;
    height: 30px;
    background-color: white;
    border: 1px solid #e1e1e1
}
.modify_box{
    margin-top: 30px;
    width: 400px;
    height: 270px;
    /*border: 1px solid #dfdfdf;*/
    padding: 15px;
}

</style>

