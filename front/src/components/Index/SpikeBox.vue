<script setup>

</script>

<template>
    <div class="gallery-book">
        <div class="gallery-book_title">
            <span>今日秒杀</span><a href="#/spike">/进入秒杀会场</a>
        </div>
        <div class="gallery-book_list">
            <div class="gallery-book_card" v-for="item in books" :key="item.id">
                <el-image
                        style="width: 82%; height: 190px;margin:5px 9%"
                        :src="imgS[0]"
                        fit="fill"></el-image>
                <div style="width: 86%;margin: 7%">
                    <a href="#" class="gallery-book_text">
                        {{item.bookName}}
                    </a>
                    <div class="gallery-book_author">
                        {{item.author}}
                    </div>
                    <div class="gallery-book_price">
                        <span style="font-size:14px;color: #bf7f5f;">￥</span>
                        <s>{{item.price}}</s>
                        <span style="font-size:14px;color: #bf7f5f;">￥</span>
                        {{item.price}}
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>

</style>