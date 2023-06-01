<template>
    <div>
        <el-upload
                class="avatar-uploader"
                action="http://localhost:8080/uploadBook"
                :show-file-list="true"
                :on-remove="handleAvatarFail"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload"
                :headers="headersJWT">
            <img v-if="imageUrl" :src="imageUrl" class="avatar" alt="图片详情">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            <span>书籍封面上传</span>
        </el-upload>
    </div>
</template>

<script>
export default {
    name: "UploadPage",
    data() {
        return {
            imageUrl: ''
        };
    },
    methods: {
        handleAvatarSuccess(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
            console.log(this.imageUrl);
        },
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        },
        handleAvatarFail(){
            console.log("文件已被移动")
        }
    },
    computed: {
        headersJWT(){
            const token=localStorage.getItem('token')
            return{
                Authorization:token
            };
        },
    },
}
</script>

<style scoped>
.avatar-uploader{
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}
.avatar-uploader{
    border-color: #409EFF;
    background: #9a9a9a;
}
.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}
.avatar {
    width: 178px;
    height: 178px;
    display: block;

}
</style>
