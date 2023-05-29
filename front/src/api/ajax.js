import axios from 'axios'
import store from "@/store/store";
import router from "@/router";
// 包装的axios ajax请求接口
export default function ajax (url, data={}, method='GET') {
    return new Promise(function (resolve, reject) {
        let promise

        if (method === 'GET') {
            // 准备url query参数数据
            let dataStr = ''
            Object.keys(data).forEach(key => {
                dataStr += key + '=' + data[key] + '&'
            })
            if (dataStr !== '') {
                dataStr = dataStr.substring(0, dataStr.lastIndexOf('&'))
                url = url + '?' + dataStr
            }
            promise = axios.get(url)
        } else {
            promise = axios.post(url, data)
        }

        promise.then(function (response) {
            resolve(response.data)
        }).catch(function (error) {
            reject(error)
        })
    })
}


// 前置拦截
ajax.interceptors.request.use(config => {
        // 登录流程控制中，根据本地是否存在token判断用户的登录情况
        // 但是即使token存在，也有可能token是过期的，所以在每次的请求头中携带token
        // 后台根据携带的token判断用户的登录情况，并返回给我们对应的状态码
        // 而后我们可以在响应拦截器中，根据状态码进行一些统一的操作。
        console.log("在请求头中放入令牌")
        const token = localStorage.getItem("token");
        console.log("发送前的token:"+token);
        config.headers.Authorization = token;
        return config;
    },
    error => {
        console.log("请求出错了");
        Promise.error(error)
    }
)


ajax.interceptors.response.use(
    response => {
        console.log("返回的response:"+response);
        let data = response.data;
        console.log("response.data"+response.data);
        console.log("response.data.code"+data.code);
        switch(response.data.code){
            case 0:
                console.log("=======后端返回的编码是401=======")
                this.$store.commit("REMOVE_INFO");//清空本地信息
                store.state.token = ''
                store.state.userInfo = {}
                localStorage.setItem("token", '')
                sessionStorage.setItem("userInfo", JSON.stringify(''))


                setTimeout(() => {
                    router.replace({
                        path: '/Login',
                        query: {
                            redirect: router.currentRoute.fullPath
                        }
                    });
                }, 1000);
                return Promise.reject(response);

            case 403:
                console.log("返回403");
                this.$message({
                    type: 'waring',
                    message: "未登录,请先登录！",
                    duration: 1000
                })
                setTimeout(() => {
                    router.replace({
                        path: '/Login',
                        query: {
                            redirect: router.currentRoute.fullPath
                        }
                    });
                }, 1000);
                return Promise.reject(response);
            default:
                break;
        }

        return response;
        // return Promise.resolve(response);
        // 如果返回的状态码为200，说明接口请求成功，可以正常拿到数据
        // 否则的话抛出错误
        // console.log("前置response.status:"+response.status);
        // if (response.status === 200) {
        //   console.log("路由守卫发现已经登录");
        //   return Promise.resolve(response);
        // } else {
        //   console.log("路由守卫发现未登录");
        //   console.log("response.status:"+response.status);
        //   // return Promise.reject(response);
        // }
    },
    error => {
        // console.log(403);
        console.log("error:"+error);
        const status = error.response ? error.response.status : null
        console.log("令牌是空的，没办法执行")
        console.log("error.response:"+error.response);
        console.log("status:"+status);
        // if(error.response.data) {
        //   error.message = error.response.data.msg
        // }

        // if(error.response.status === 403) {
        //   store.commit("REMOVE_INFO")
        //   router.push("/login")
        // }
        // Element.Message.error(error.message, {duration: 2 * 1000})
        return error
    }
)

