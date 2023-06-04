<template>
  <div class="content">
    <el-carousel :interval="5000" arrow="always" height="430px" indicator-position="outside">
      <el-carousel-item>
        <div style="padding: 5px 75px;height: 100%;width: 100%">
          <img :src="image" alt="图片">
        </div>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
export default {
  name: "CarouselBtn",
  props:{
    image: {
      type: String,
      default: null
    }
  },
  created() {
    console.log("this.imgList:"+this.image);
  }
}
</script>

<style scoped>
.content{
  width: 100%;
}

img{
  width: 100%;
  height: inherit;
}
</style>
