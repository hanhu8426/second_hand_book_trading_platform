import ajax from "./ajax";

const BASE_URL = 'http://localhost:8080'

//添加地址
export const reqAddAddress = (address) => ajax(BASE_URL+'/addUserAddress', {
    name: address.name,
    phone: address.phone,
    address: address.addr,
    area: address.area,
},"POST")

//修改地址
export const reqModAddress = (address) => ajax(BASE_URL+"/modifyUserAddress",{
    addId: address.addId,
    name: address.name,
    phone: address.phone,
    address: address.addr,
    area: address.area,
},'POST')

//删除地址
export const reqDelAddress = (addId) => ajax(BASE_URL+"/delUserAddress",{addId})

//得到某个用户的地址列表
export const reqGetAddressList = () => ajax(BASE_URL+"/getUserAddress",{})
