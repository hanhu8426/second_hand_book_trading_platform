import axios from 'axios'
import ajax from "./ajax";
const BASE_URL = 'http://8.140.52.224:8080'

// 包装的axios ajax请求接口
function ajax1 (url, data={}, method='GET') {
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
            resolve(response)
        }).catch(function (error) {
            reject(error)
        })
    })
}

//登录
export const reqLogin = ({account, password}) => ajax1('http://8.140.52.224:8080/Login', {account, password}, 'POST')

// 注册账号
export const reqRegister = (account,password)=>ajax(BASE_URL+'/Register',{account,password})

//得到用户的基本信息getUserInfo
export const reqGetUserInfo = ()=>ajax(BASE_URL+'/getUserInfo',{})

//修改密码
export const reqModUserPwd = (oldPassword,newPassword)=>ajax(BASE_URL+'/user/PwdManage',{oldPassword,newPassword},'POST')

//修改用户的基本信息
export const reqModUserInfo_1 = (charge)=>ajax(BASE_URL+'/user/userInfoOne',{charge},"POST")
export const reqModUserInfo_2 = (img,phone,area,gender,introduction)=>ajax(BASE_URL+'/user/userInfo',{img,phone,area,gender,introduction},"POST")

export const reqUserOrders=()=>ajax(BASE_URL+'/listUserOrders',{},"POST")

export const reqModOrderStatus=(orderId)=>ajax(BASE_URL+'/end',{orderId},"POST")
//这里是根据订单的id获取概订单
export const reqGetOrderByOrderId=(orderId)=>ajax(BASE_URL+'/GetOrderByOrderId',{orderId},"POST")
