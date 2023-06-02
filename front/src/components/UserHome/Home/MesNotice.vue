<template>
    <div class="content">
        <h1>消息通知</h1>
        <div class="box_info">
            <el-tabs v-model="activeName" @tab-click="handleClick">
                <el-tab-pane label="全部消息" name="first">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="alert fade alert-simple alert-success alert-dismissible text-left font__family-montserrat font__size-16 font__weight-light brk-library-rendered rendered show"
                                  v-for="item in messageList"
                                  :key="item.index"
                                   ref="MesForm">
                                <i class="start-icon far fa-check-circle faa-tada animated"></i>
                                <strong class="font__weight-semibold">{{ item.message }}</strong> You successfullyread this important.
                            </div>
                        </div>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="余额信息" name="second">
                    <div class="tab_box">
                        <p class="noMesInfo">余额信息</p>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="订单信息" name="third">
                    <div class="tab_box">
                        <p class="noMesInfo">订单信息</p>
                    </div>
                </el-tab-pane>
            </el-tabs>
        </div>
    </div>
</template>

<script>
// <!--消息通知页面-->
export default {
    name: "MesNotice",
    data() {
        return {
            activeName: 'second',
            messageList:[
                {index:1,message:"我冲了一百块"},
                {index:2,message: "我冲了一杯酒"},
            ],
        };
    },
    methods: {
        handleClick(tab, event) {
            console.log(tab, event);
        }
    }
}
</script>

<style scoped>
.content{
    margin: 10px auto;
    width:1000px;
    padding: 30px 20px;
    background-color: white;
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

.noMesInfo{
    text-align: center;
    font-size: 18px;
    color: #757575;
    line-height: 60px
}
body
{
    background: #000426;
}

.alert>.start-icon {
    margin-right: 0;
    min-width: 20px;
    text-align: center;
}

.alert>.start-icon {
    margin-right: 5px;
}

.greencross
{
    font-size:18px;
    color: #25ff0b;
    text-shadow: none;
}

.alert-simple.alert-success
{
    border: 1px solid rgba(36, 241, 6, 0.46);
    background-color: rgba(7, 149, 66, 0.12156862745098039);
    box-shadow: 0px 0px 2px #259c08;
    color: #0ad406;
    text-shadow: 2px 1px #00040a;
    transition:0.5s;
    cursor:pointer;
}
.alert-success:hover{
    background-color: rgba(7, 149, 66, 0.35);
    transition:0.5s;
}
.alert-simple.alert-info
{
    border: 1px solid rgba(6, 44, 241, 0.46);
    background-color: rgba(7, 73, 149, 0.12156862745098039);
    box-shadow: 0px 0px 2px #0396ff;
    color: #0396ff;
    text-shadow: 2px 1px #00040a;
    transition:0.5s;
    cursor:pointer;
}

.alert-info:hover
{
    background-color: rgba(7, 73, 149, 0.35);
    transition:0.5s;
}

.blue-cross
{
    font-size: 18px;
    color: #0bd2ff;
    text-shadow: none;
}

.alert-simple.alert-warning
{
    border: 1px solid rgba(241, 142, 6, 0.81);
    background-color: rgba(220, 128, 1, 0.16);
    box-shadow: 0px 0px 2px #ffb103;
    color: #ffb103;
    text-shadow: 2px 1px #00040a;
    transition:0.5s;
    cursor:pointer;
}

.alert-warning:hover{
    background-color: rgba(220, 128, 1, 0.33);
    transition:0.5s;
}

.warning
{
    font-size: 18px;
    color: #ffb40b;
    text-shadow: none;
}

.alert-simple.alert-danger
{
    border: 1px solid rgba(241, 6, 6, 0.81);
    background-color: rgba(220, 17, 1, 0.16);
    box-shadow: 0px 0px 2px #ff0303;
    color: #ff0303;
    text-shadow: 2px 1px #00040a;
    transition:0.5s;
    cursor:pointer;
}

.alert-danger:hover
{
    background-color: rgba(220, 17, 1, 0.33);
    transition:0.5s;
}

.danger
{
    font-size: 18px;
    color: #ff0303;
    text-shadow: none;
}

.alert-simple.alert-primary
{
    border: 1px solid rgba(6, 241, 226, 0.81);
    background-color: rgba(1, 204, 220, 0.16);
    box-shadow: 0px 0px 2px #03fff5;
    color: #03d0ff;
    text-shadow: 2px 1px #00040a;
    transition:0.5s;
    cursor:pointer;
}

.alert-primary:hover{
    background-color: rgba(1, 204, 220, 0.33);
    transition:0.5s;
}

.alertprimary
{
    font-size: 18px;
    color: #03d0ff;
    text-shadow: none;
}

.square_box {
    position: absolute;
    -webkit-transform: rotate(45deg);
    -ms-transform: rotate(45deg);
    transform: rotate(45deg);
    border-top-left-radius: 45px;
    opacity: 0.302;
}

.square_box.box_three {
    background-image: -moz-linear-gradient(-90deg, #290a59 0%, #3d57f4 100%);
    background-image: -webkit-linear-gradient(-90deg, #290a59 0%, #3d57f4 100%);
    background-image: -ms-linear-gradient(-90deg, #290a59 0%, #3d57f4 100%);
    opacity: 0.059;
    left: -80px;
    top: -60px;
    width: 500px;
    height: 500px;
    border-radius: 45px;
}

.square_box.box_four {
    background-image: -moz-linear-gradient(-90deg, #290a59 0%, #3d57f4 100%);
    background-image: -webkit-linear-gradient(-90deg, #290a59 0%, #3d57f4 100%);
    background-image: -ms-linear-gradient(-90deg, #290a59 0%, #3d57f4 100%);
    opacity: 0.059;
    left: 150px;
    top: -25px;
    width: 550px;
    height: 550px;
    border-radius: 45px;
}

.alert:before {
    content: '';
    position: absolute;
    width: 0;
    height: calc(100% - 44px);
    border-left: 1px solid;
    border-right: 2px solid;
    border-bottom-right-radius: 3px;
    border-top-right-radius: 3px;
    left: 0;
    top: 50%;
    transform: translate(0,-50%);
    height: 20px;
}

.fa-times
{
    -webkit-animation: blink-1 2s infinite both;
    animation: blink-1 2s infinite both;
}


/**
 * ----------------------------------------
 * animation blink-1
 * ----------------------------------------
 */
@-webkit-keyframes blink-1 {
    0%,
    50%,
    100% {
        opacity: 1;
    }
    25%,
    75% {
        opacity: 0;
    }
}
@keyframes blink-1 {
    0%,
    50%,
    100% {
        opacity: 1;
    }
    25%,
    75% {
        opacity: 0;
    }
}

</style>
