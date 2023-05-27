
<template>
    <div class="headNav">
        <div class="content">
            <div style="float: left">
                <div style="width: 232px; height: 40px;vertical-align: middle;font-family: 宋体;font-size: 38px;font-style: italic">
                    东大二手书网
                </div>
            </div>
            <div style="float: right;">
                <el-select v-model="select" placeholder="请选择" class="search-type" @change="handleOptionChange">
                    <el-option v-for="type in type_list" :key="type.value" :value="type.typeName">{{type.typeName}}</el-option>
                </el-select>
                <el-input v-model="input" placeholder="书名、作者" class="input-with-select" style="width: 400px;height: 40px;">

                </el-input>
                <el-button icon="el-icon-search" class="search-button">搜索</el-button>

            </div>
        </div>
    </div>
</template>



<script>
export default {
    name: "HeadNav",
    data() {
        return {
            rel:'',
            input: "",
            select: '',
            type_list:[
                {
                    typeName: '书名',
                    value: '0',
                },
                {
                    typeName: '作者',
                    value: '1',
                }
            ],
            imgS: require('../../assets/image/logo-250.png')
        }
    },
    computed: {
    },
    methods:{
        handleOptionChange(value) {
            if (value === 'option1') {
                this.handleOption1();
            } else if (value === 'option2') {
                this.handleOption2();
            }
        },
        handleOption1(){
            this.select=0
        },
        handleOption2(){
            this.select=1
        },
    }
}
</script>


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