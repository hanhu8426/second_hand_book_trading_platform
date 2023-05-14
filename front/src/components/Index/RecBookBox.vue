<script>
export default {
    name: "SpikeBox",
    data(){
        return {
            imgS: ["static/image/light.jpg",
                "static/image/21.jpg",
                "static/image/22.jpg",
                "static/image/23.jpg"],
            books: [
                {
                    id: 1,
                    name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                    author: "黄子平 著",
                    price: 123.23
                },
                {
                    id: 12,
                    name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                    author: "黄子平 著",
                    price: 123.23
                },
                {
                    id: 13,
                    name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                    author: "黄子平 著",
                    price: 123.23
                },
                {
                    id: 14,
                    name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                    author: "黄子平 著",
                    price: 123.23
                }
            ]
        };
    }
}

</script>

<template>
    <div class="content">
        <div class="gallery-book_title">
            <span style="margin-left: 30px">{{this.sortTitle}}</span>
            <!--      <router-link :to="{path: '/search',query:{id:1}}" style="float: right;margin-right: 30px">>更多图书</router-link>-->
        </div>
        <div class="gallery-book_list">
            <div class="gallery-book_card" v-for="(item) in bookList" :key="item.id">
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