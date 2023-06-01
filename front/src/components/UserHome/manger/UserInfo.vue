<script>
// <!--用户中心-->
import {reqModUserInfo_1, reqModUserInfo_2} from "@/api/user";
import UploadPage from "@/components/Common/Upload.vue";

export default {
    name: "UserInfo",
    components: {UploadPage},
    data () {
        //检测充值的数额大小
        var checkCharge = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('充值数额不能为空'));
            }
        };
        var checkPhone = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('手机号不能为空'));
            }
            setTimeout(() => {
                if(value.length>11){
                    callback(new Error('手机号不能大于11位'));
                }else {
                    callback();
                }
            }, 1000);
        };
        var checkIntro = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('自我介绍不能为空'));
            }
            setTimeout(() => {
                if(value.length<5){
                    callback(new Error('自我介绍不能少于5个字'));
                }else {
                    callback();
                }
            }, 1000);
        };
        return {
            chargeNum:{
                charge:' ',
            },
            rules:{
                charge:[
                    {
                        validator: checkCharge ,trigger:'blur'
                    }
                ],
            },
            InfoForm:{
                img:'',
                phone:'',
                area:'',
                gender:'',
                introduction:'',
            },
            ruleInfo:{
              img:'',
              phone:[{
                  validator:checkPhone,trigger:'blur'
              }
              ],
              area:'',
              gender:'',
              introduction:[
                    {
                        validator:checkIntro,trigger:'blur'
                    }
                ]

            },
            activeName: 'first',
            form_2:{
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
            }
        }
    },
        // mounted(){
        //
        // },
    methods: {
        changeInput() {
            this.$forceUpdate();
        },
        handleClick(tab, event) {
            console.log(tab, event);
        },

        getGender(item){
           this.InfoForm.gender=item.type
        },

        getArea(item){
          this.InfoForm.area=item.type
        },

        submitForm_1(){
                   console.log(this.chargeNum.charge)
                    reqModUserInfo_1({
                        charge: this.chargeNum.charge,
                    }).then((response) => {
                        if (response.data.code == 1) {//根据状态码进入下一步
                            console.log("=====上传成功=======")
                            console.log("打印余额")
                            console.log(response.data.data)
                            if (response.msg == "success") {
                                this.$message({
                                    type: 'success',
                                    message: "上传成功！",
                                    duration: 1000
                                })
                            } else {
                                this.$message({
                                    type: 'success',
                                    message: "上传成功！",
                                    duration: 1000
                                })
                                setTimeout(() => {
                                    this.$router.push({path: '/user/userCenter'});
                                }, 1000);
                            }
                        } else {
                            this.$message({
                                type: 'waring',
                                message: "充值失败"
                            })
                        }
                    }).catch(() => {
                        this.$message.error("充值失败")
                    },
                 )
                },

        getImg(){
            this.InfoForm.img= this.$refs.uploadImg.$data.imageUrl
        },
        submitForm_2() {
            console.log("这是用来测试的输出")
            console.log(this.$refs.uploadImg.imageUrl)
            console.log(this.InfoForm.img)
            console.log(this.InfoForm.phone)
            console.log(this.InfoForm.area)
            console.log(this.InfoForm.gender)
            console.log(this.InfoForm.introduction)
                    console.log("=====上传表格2=======")
                    reqModUserInfo_2({
                        img: this.InfoForm.img,
                        phone: this.InfoForm.phone,
                        area: this.InfoForm.area,
                        gender: this.InfoForm.gender,
                        introduction: this.InfoForm.introduction
                    }).then((response) => {
                        if (response.data.code == 1) {//根据状态码进入下一步
                            console.log("=====上传成功=======")

                            if (response.msg == "success") {
                                this.$message({
                                    type: 'success',
                                    message: "上传成功！",
                                    duration: 1000
                                })
                            } else {
                                this.$message({
                                    type: 'success',
                                    message: "上传成功！",
                                    duration: 1000
                                })
                                setTimeout(() => {
                                    this.$router.push({path: '/user/userCenter'});
                                }, 1000);
                            }
                        } else {
                            this.$message({
                                type: 'waring',
                                message: "资料上传失败1"
                            })
                        }
                    }).catch(() => {
                        this.$message.error("资料上传失败2")
                    })
        },

         resetForm(formName) {
             this.$refs[formName].resetFields();
          },
    }


}
</script>


<template>
    <div class="content">
        <div class="box_info">
            <el-tabs v-model="activeName" @tab-click="handleClick" stretch:true class="tabContainer" ref="chargeName">
                <el-tab-pane label="账号充值" name="first">
                    <div class="tab_box">
                        <div class="modify_box">
                                    <el-input type="text" v-model="chargeNum.charge" autocomplete="off" placeholder="请输入金额"></el-input>
                                    <el-button type="primary" style="width: 120px;" native-type="submit" @click="submitForm_1('chargeName')">确认充值</el-button>
                        </div>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="个人信息" name="second" style="height: 500px">
                    <div class="tab_box">
                        <div class="modify_box">
                            <el-form :model="InfoForm" status-icon :rules="ruleInfo" ref="InfoForm" label-width="80px">
                                <UploadPage ref="uploadImg" @click="getImg"><span>上传头像</span></UploadPage>
                                <el-form-item prop="phone" label="电话号码">
                                    <el-input  placeholder="输入你的手机号码" type="text" autocomplete="off"  v-model="this.form_2.phone"></el-input>
                                </el-form-item>
                                <el-form-item prop="gender" label="性别">
                                    <el-dropdown  style="width: 100%;">
                                        <el-checkbox-group  v-model="this.InfoForm.gender">
                                            <el-checkbox
                                                v-for="item in form_2.genderOption"
                                                :key="item.type"
                                                :label="item.value"
                                                @click="getGender('item')"
                                            ></el-checkbox>
                                        </el-checkbox-group>
                                    </el-dropdown>
                                </el-form-item>
                                <el-form-item prop="area" label="校区">
                                    <el-dropdown  style="width: 100%;">
                                        <el-checkbox-group  v-model="this.InfoForm.area" >
                                        <el-checkbox
                                            v-for="item in form_2.checkBoxOption"
                                            :key="item.value"
                                            :label="item.value"
                                            @click="getArea(item)"
                                        ></el-checkbox>
                                        </el-checkbox-group>
                                    </el-dropdown>
                                </el-form-item>
                                <el-form-item prop="introduction" label="简介">
                                    <el-input type="textarea" placeholder="说两句介绍自己吧" autocomplete="off" v-model="this.InfoForm.introduction"></el-input>
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

