<script>

export default {
    name:"UploadImg",
    data() {
        return {
            imageUrl: '',
            image:'',
        };
    },
    methods: {
        handleAvatarSuccess(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
            console.log(this.imageUrl)
            console.log(res)
            this.image = res.data
            this.$emit(' handleDataFromChild',this.image)
            console.log(res.data)
            console.log(res.data)

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


<template>
     <div>
     <el-upload
     class="avatar-uploader"
     action='http://8.140.52.224:8080/Upload'
     :show-file-list="false"
     :on-success="handleAvatarSuccess"
     :before-upload="beforeAvatarUpload"
     :headers="headersJWT">
         <span >上传头像</span>
      <img v-if="imageUrl" :src="imageUrl" class="avatar" alt="头像图片">
     <i v-else class="el-icon-plus avatar-uploader-icon"></i>
</el-upload>
    </div>
</template>


<style>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}
.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
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

