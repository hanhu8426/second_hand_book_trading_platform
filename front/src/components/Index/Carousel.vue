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
            imgS: ["src/assets/image/20.jpg",
                "src/assets/image/21.jpg",
                "src/assets/image/22.jpg",
                "src/assets/image/23.jpg"]
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