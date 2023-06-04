<script>
import {reqAddBook, reqDelBook, reqGetBookList, reqModBook} from "@/api/bookStall";
import UploadBook from "@/components/Common/uploadBook.vue";
export default {
    name: "BookStallPage",
    components: {UploadBook},
    data() {
        return {
            dialogVisible: false,
            isEdit: false,//用来判断是添加书还是修改书 false:添加 true:修改
            TextForType:[
                "历史和政治类",
                "文学和艺术类",
                "科学和技术类",
                "商业和经济类",
                "心理学和自助类",
                "旅游和地理类",
                "宗教和哲学类",
            ],
            bookList: [
                {
                    BookId: 1,
                    name: "黄哥的恋爱节奏",
                    author: "黄文敬",
                    isbn: "",
                    type: 1,
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
                    type: 2,
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
                recommend: "",
                sellerID: "",
            },
        };
    },
    //初始化构建组件
    created() {
        console.log("开始生命构建")
        this.getBookList();
    },
    mounted() {
        console.log("开始生命构建")
        this.getBookList();
    },
    methods: {
    //修改当前的标签值
    changeLable(){
            switch (this.book.type) {
                case '1':
                    this.TextForType="历史和政治类"
                    break
                case '2':
                    this.TextForType="文学和艺术类"
                    break
                case '3':
                    this.TextForType="科学和技术类"
                    break
                case '4':
                    this.TextForType="商业和经济类"
                    break
                case '5':
                    this.TextForType="心理学和自助类"
                    break
                case '6':
                    this.TextForType="旅游和地理类"
                    break
                case '7':
                    this.TextForType="工具书类"
                    break
                default:
                    console.log('default')
            }
           },
    //处理添加操作
    handleAdd() {
            this.dialogVisible = true;
            this.isEdit = false;
        },
    //处理修改
    handleMod(book) {
            this.dialogVisible = true;
            this.isEdit = true;
            this.book.bookId = book.bookId;
            this.book.image = this.$refs.bookPaper.image;
            this.book.name=book.name;
            this.book.author = book.author;
            this.book.type = book.type;
            this.book.description = book.description;
            this.book.price = book.price;
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
                if (response.data.code === 1) {
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

    //添加书籍
    addBook() {
            console.log(this.book)
            console.log(this.TextForType)
            console.log("在向后端传送书籍的资料前进行赋值")
            this.book.status = 1
            this.book.recommend= 1
            this.book.image= this.$refs.bookPaper.image
            console.log(this.book.image)
            console.log(this.book)
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
        },
     // 加入bookId
        getBookId(book){
           this.book.bookId=book.BookId;
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
    //处理删除书籍
    delBook(book) {
        console.log("删除测试")
        console.log(book.bookId)
        this.$confirm('是否要进行删除操作?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                reqDelBook(book.bookId).then(response => {
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

},}



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
            <div class="book_list" v-for="(book,Index) in bookList" :key="Index">
                <div class="bookCover">
                    <el-image  src="book.image" alt="书籍封面"></el-image>
                </div>
                <div class="bookName">{{book.name}}
                    <span style="float: right;font-size: 14px;color: #757575;">{{this.TextForType[2]}}</span>
                </div>
                <div class="author">{{book.author}}</div>
                <div class="Description">{{book.description}}</div>
                <div class="foot">
                    <span style="float: right" @click="delBook(book)">删除</span>
                    <span style="float: right;margin-right: 10px" @click="handleMod">修改</span>
                </div>
            </div>
        </div>

        <!--添加图书的弹出框-->
        <el-dialog title="书籍变更" v-model="dialogVisible" width="50%"  center>
            <el-form ref="form" :model="book">
                <upload-book ref="bookPaper"></upload-book>
                <el-form-item>
                    <el-input placeholder="书名" v-model="book.name"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input placeholder="作者" v-model="book.author"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input placeholder="ISBN" v-model="book.isbn"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-dropdown  style="width: 100%;">
                        <el-select v-model="book.type" placeholder="请选择类型" @change="changeLable">
                            <el-option label="历史和政治类" value="1" ></el-option>
                            <el-option label="文学和艺术类" value="2"></el-option>
                            <el-option label="科学和技术类" value="3"></el-option>
                            <el-option label="商业和经济类" value="4"></el-option>
                            <el-option label="心理学和自助类" value="5"></el-option>
                            <el-option label="旅游和地理类" value="6"></el-option>
                            <el-option label="宗教和哲学类" value="7"></el-option>
                        </el-select>
                    </el-dropdown>  </el-form-item>
                <el-form-item>
                    <el-input type="textarea" placeholder="简介" v-model="book.description"></el-input>
                </el-form-item>
                <el-form-item prop="area">
                    <el-dropdown  style="width: 100%;">
                        <el-select v-model="book.campus" placeholder="请选择校区">
                            <el-option label="九龙湖校区" value=1 ></el-option>
                            <el-option label="四牌楼校区" value=2></el-option>
                            <el-option label="丁家桥校区" value=3></el-option>
                        </el-select>
                    </el-dropdown>
                </el-form-item>
                <el-form-item>
                    <el-input placeholder="价格" v-model="book.price"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="getBookId">初次确认</el-button>
                </el-form-item>
            </el-form>
            <template v-slot:footer>
            <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit('form')">再 次 确 定</el-button>
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
