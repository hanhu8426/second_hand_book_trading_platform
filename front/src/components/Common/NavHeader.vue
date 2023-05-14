<script>
export default {
    name: "HeadNav",
    data() {
        return {
            input: "",
            select: '',
            //第一种设置图片的方法
            // imgS: "static/image/logo-250.png"
            //第二种设置图片的方法
            imgS: require('../../assets/image/logo-250.png')
        }
    },
    computed: {
        //第三种方法
        getImgS(){
            let imgUrl = "logo-250.png";
            let imgSrc = require('../../assets/image/'+imgUrl);
            return imgSrc;
        }
    }
}
</script>

<template>
    <div class="headNav">
        <div class="content">
            <div style="float: left">
                <div style="width: 232px; height: 40px;vertical-align: middle;font-family: 宋体;font-size: 38px;font-style: italic">
                    东大二手书网
                </div>
            </div>
            <div style="float: right;">
                <el-select v-model="select"  placeholder="请选择" class="search-type">
                    <el-option label="书名" value="1"></el-option>
                    <el-option label="作者" value="2"></el-option>
                    <el-option label="出版社" value="3"></el-option>
                </el-select>
                <el-input v-model="input" placeholder="书名、作者、出版社、ISBN" class="input-with-select" style="width: 400px;height: 40px;">

                </el-input>
                <el-button icon="el-icon-search" class="search-button">搜索</el-button>

            </div>
        </div>
    </div>
</template>

<style scoped>
.headNav{
    width: 100%;
    background-color: #f2f1ea;
    line-height: 100px;
    clear: both;
}
.content{
    margin: auto;
    width: 1240px;
    height: 100px;
    background-color: #f2f1ea;
}
.search-button{
    width: 60px;
    height: 40px;
}
.search-type{
    width:100px;
    height:40px;
}
</style>