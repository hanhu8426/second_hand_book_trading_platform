<script>
export default {
    name: "CarouSel",
    props:{
        imgList: {
            type: Array,
            default: null
        }
    },
    data(){
        return{
            imgS: ["https://hwj-first-test.obs.cn-south-1.myhuaweicloud.com/%E8%BD%AE%E6%92%AD%E5%9B%BE/20.jpg",
                "https://hwj-first-test.obs.cn-south-1.myhuaweicloud.com/%E8%BD%AE%E6%92%AD%E5%9B%BE/21.jpg",
                "https://hwj-first-test.obs.cn-south-1.myhuaweicloud.com/%E8%BD%AE%E6%92%AD%E5%9B%BE/24.jpg",
                "https://hwj-first-test.obs.cn-south-1.myhuaweicloud.com/%E8%BD%AE%E6%92%AD%E5%9B%BE/23.jpg",
                "https://hwj-first-test.obs.cn-south-1.myhuaweicloud.com/%E8%BD%AE%E6%92%AD%E5%9B%BE/22.jpg"
            ]
        }
    },
    created() {
        console.log("轮播图中this.imgList:"+this.imgList)
    }
}
</script>

<template>
<div class="content">
<el-carousel :interval="5000" arrow="always" height="300px">
    <el-carousel-item v-for="item in imgS" :key="item.id">
          <img v-bind:src="item" alt="图片"/>
    </el-carousel-item>
</el-carousel>
</div>

</template>

<style scoped>

</style>