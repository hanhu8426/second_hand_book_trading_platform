<script>
import UploadPage from "@/components/Common/Upload_2.vue";
import {reqAddBook, reqDelBook, reqGetBookList, reqModBook} from "@/api/bookStall";
export default {
    name: "AddressPage",
    components: {UploadPage},
    data() {
        return {
            dialogVisible: false,
            isEdit: false,//用来判断是添加书还是修改书 false:添加 true:修改
            bookList: [
                {
                    BookId: 1,
                    name: "黄哥的恋爱节奏",
                    author: "黄文敬",
                    isbn: "",
                    type: "文学",
                    description: "讲述了黄哥的恋爱心路历程，惊心动魄，感人肺腑",
                    status: "",
                    image: "",
                    campus: "",
                    price: "25",
                    newProduct: "",
                    recommend: "",
                    sellerID: "",
                },
                {
                    bookId: 2,
                    name: "算出来的秘密爱情",
                    author: "胡丹婷",
                    isbn: "",
                    type: "小说",
                    description: "",
                    status: "",
                    image: "",
                    campus: "",
                    price: "",
                    newProduct: "",
                    recommend: "",
                    sellerID: "",
                },

            ],

            book: {
                bookId: '',
                name: "",
                author: "",
                isbn: "",
                type: "",
                description: "",
                status: "",
                image: "",
                campus: "",
                price: "",
                newProduct: "",
                recommend: "",
                sellerID: "",
            },
        };
    },
    //初始化构建组件
    // created(){
    //     this.getBookList();
    // },
    methods: {
        //处理添加操作
        handleAdd() {
            this.dialogVisible = true;
            this.isEdit = false;
        },
        //处理修改
        handleMod(book) {
            this.dialogVisible = true;
            this.isEdit = true;
            this.book.bookId = book.addId;
            this.book.image = book.image;
            this.book.author = book.author;
            this.book.type = book.type;
            this.book.description=book.description;
            this.book.price=book.price;
        },

        //提交处理
        onSubmit() {
            if (this.isEdit) {
                this.modifyBook();
            } else {
                this.addBook();
            }
        },

        //得到用户书籍列表
        getBookList() {
            console.log("===获取的书籍列表：===" + this.$store.getters.getUser.account + "=====");
            reqGetBookList().then(response => {
                console.log(response);
                if (response.data.code == 1) {
                    let bookList = response.data.data
                    console.log(bookList)
                    this.bookList = bookList
                } else {
                    this.$message({
                        message: response.message,
                        type: "warning"
                    })
                }
            }).catch(err => {
                console.log(err);
            })
        },

        //添加地址
        addBook(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    reqAddBook(this.book).then(response => {
                        console.log(response);
                        if (response.data.code == 1) {
                            this.$message({
                                message: response.message,
                                type: "success"
                            });
                            this.dialogVisible = false;
                            this.getAddressList();
                        } else {
                            this.$message({
                                message: response.message,
                                type: "warning"
                            })
                        }
                    }).catch(err => {
                        console.log(err);
                    })
                }
            })
        },


        //修改书籍
        modifyBook() {
            reqModBook(this.book).then(response => {
                console.log(response);
                if (response.data.code == 1) {
                    this.$message({
                        message: response.message,
                        type: "success"
                    });
                    this.dialogVisible = false;
                    this.getAddressList();
                } else {
                    this.$message({
                        message: response.message,
                        type: "warning"
                    })
                }
            }).catch(err => {
                console.log(err);
            })
        },
        //处理删除地址
        delBook(bookId) {
            this.$confirm('是否要进行删除操作?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                reqDelBook(bookId).then(response => {
                    console.log(response);
                    if (response.data.code == 1) {
                        this.$message({
                            message: response.message,
                            type: "success"
                        });
                        this.getAddressList();
                    } else {
                        this.$message({
                            message: response.message,
                            type: "warning"
                        })
                    }
                }).catch(err => {
                    console.log(err);
                })
            }).catch(() => {
                console.log("取消删除了");
            });
        },

        getPaper(){
            this.book.image=this.$refs.bookPaper.imageUrl
        }


    }
}



</script>




<template>
    <div class="content">
        <h1>我的书摊</h1>
        <div class="box_info">
            <div class="book_list">
                <div class="add" @click="handleAdd">
                    <i class="el-icon-edit"></i>
                    <p style="font-size: 14px">添加新的书籍</p>
                </div>
            </div>
            <div class="book_list" v-for="book in bookList" :key="book.bookId">
                <div class="bookCover">
                    <el-image  src="src/assets/image/20.jpg"> alt="书籍封面"></el-image>
                </div>
                <div class="bookName">{{book.name}}
                    <span style="float: right;font-size: 14px;color: #757575;">{{book.type}}</span>
                </div>
                <div class="author">{{book.author}}</div>
                <div class="Description">{{book.description}}</div>
                <div class="foot">
                    <span style="float: right" @click="delBook(book)">删除</span>
                    <span style="float: right;margin-right: 10px" @click="handleMod(book)">修改</span>
                </div>
            </div>
        </div>

        <!--添加图书的弹出框-->
        <el-dialog title="添加新的书籍" v-model="dialogVisible" width="50%"  center>
            <el-form ref="form" :model="book">
                <UploadPage ref="bookPaper" @click="getPaper"></UploadPage>
                <el-form-item>
                    <el-input placeholder="书名" v-model="book.name"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input placeholder="类别" v-model="book.type"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input type="textarea" placeholder="简介" v-model="book.description"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input placeholder="价格" v-model="book.price"></el-input>
                </el-form-item>
            </el-form>
            <template v-slot:footer>
            <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit('form')">确 定</el-button>
            </span></template>
        </el-dialog>

    </div>
</template>


<style scoped>

.content{
    margin: 10px auto;
    width:1000px;
    background-color: white;
    padding: 30px 20px;
}
h1{
    color: #757575;
    font-family: 新宋体;
}
.box_info{
    width: 960px;
    margin: 10px auto;
}
.book_list{
    width: 280px;
    height: 180px;
    border: 1px solid #cacaca;
    display: inline-block;
    margin-right: 20px;
    margin-top: 20px;
    padding: 20px;
    vertical-align: top;
}


.img-uploader .avatar {
    width: 178px;
    height: 178px;
    background: #9a9a9a;
    display: block;
}

.img-uploader .el-icon-plus {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    text-align: center;
}

.bookName{
    width: 240px;
    height: 40px;
    font-size: 18px;
}
.author{
    width: 240px;
    height: 30px;
    font-size: 14px;
    color: #757575;
}
.Description{
    width: 240px;
    height: 30px;
    font-size: 14px;
    color: #757575;
}
.foot{
    width: 240px;
    height: 60px;
    font-size: 14px;
    color: #ff6700;
    line-height: 60px;
}
.add{
    width:80px;
    height: 80px;
    margin-top: 40px;
    margin-left: 75px;
    font-size: 30px;
    color: #757575;
    text-align: center;
}


</style>
