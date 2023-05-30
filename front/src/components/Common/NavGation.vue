<template>
    <div class="navClass">
        <div class="content">
            <div class="nav_content">
                <el-menu
                    :default-active="$route.path"
                    class="el-menu-demo"
                    mode="horizontal"
                    @select="handleSelect"
                    background-color="#304156"
                    text-color="#fff"
                    active-text-color="#ffd04b"
                    router>
                    <el-menu-item index="/">首页</el-menu-item>
                    <el-submenu index="/">
                        <template v-slot:title>登陆|注册</template>
                        <el-menu-item index="/Login">登录</el-menu-item>
                        <el-menu-item index="/Register">注册</el-menu-item>
                        <el-menu-item index="/user/bookStall">发布</el-menu-item>

                        <el-menu-item index="/Upload">测试</el-menu-item>
                    </el-submenu>
                    <el-menu-item index="/user/mesNotice">消息</el-menu-item>
                    <el-menu-item index="/user/userOrder">我的订单</el-menu-item>
                    <el-menu-item index="/user">个人中心</el-menu-item>
                </el-menu>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    name: "NavGation",
    data() {
        return {
            activeIndex: "/",
        };
    },
    methods: {
        handleSelect(key, keyPath) {
             console.log(key, keyPath);
        },
    }
}
</script>

<style scoped>
.navClass{
    width: 100%;
    /*background-color: #545c64;*/
    background-color: #304156;
    height: 40px;
}
.content{
    margin: 0px auto;
    width: 1240px;
    height: 40px;
    /*background-color: #545c64;*/
    background-color: #304156;
}
.nav_content{
    float: right;
}
.el-menu-item,.el-submenu{
    height: 40px;
    font-size: 12px;
    line-height: 40px;
}

.el-submenu>>>.el-submenu__title {
    height: 40px;
    font-size: 12px;
    line-height: 40px;
}
</style>
