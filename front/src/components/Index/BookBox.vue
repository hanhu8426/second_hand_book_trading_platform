<script setup>

</script>

<template>
    <div class="content">
        <div class="gallery-book_title">
            <router-link :to="{path: '/search',query:{id:1}}" v-for="(item,index) in bookList" :key="index">
                <span v-if="index==0" @mouseenter="enter(index)" style="margin-right: 20px;margin-left: 30px;font-size: 24px">{{item.sortName}}</span>
                <span v-else @mouseenter="enter(index)" style="margin-right: 20px;">{{item.sortName}}</span>
            </router-link>
            <!--      <a style="float: right;margin-right: 30px" href="#">>更多书单</a>-->
            <router-link :to="{path: '/search',query:{id:bookList[0].sortId,name:bookList[0].sortName}}" style="float: right;margin-right: 30px">>更多图书</router-link>

        </div>
        <div class="gallery-book_list" v-for="(book,index) in bookList" v-show="current==index" :key="index">
            <div class="gallery-book_card" v-for="(item) in book.bookList" :key="item.id">
                <router-link :to="{path: '/book',query:{id:item.id}}">
                    <el-image
                            style="width: 82%; height: 190px;margin:5px 9%"
                            :src="item.coverImg"
                            fit="fill"></el-image>
                    <div style="width: 86%;margin: 0px 7%">
                        <a href="#" class="gallery-book_text">
                            {{item.bookName}}
                        </a>
                        <div class="gallery-book_author">
                            {{item.author}}
                        </div>
                        <div class="gallery-book_price">
                            <span style="font-size:14px;color: #bf7f5f;">￥</span>
                            {{item.price}}
                        </div>
                    </div>
                </router-link>
            </div>
        </div>
    </div>
</template>

<style scoped>

</style>