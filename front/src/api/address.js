import ajax from "./ajax";

const BASE_URL = 'http://8.140.52.224:8080'

//添加地址
export const reqAddAddress = (addregitss) => ajax(BASE_URL+'/addUserAddress', {
    name: address.name,
    phone: address.phone,
    address: address.address,
    area: address.area,
    id: address.id,
},"POST")

//修改地址
export const reqModAddress = (address) => ajax(BASE_URL+"/modifyUserAddress",{
    addId: address.addId,
    name: address.name,
    phone: address.phone,
    address: address.address,
    area: address.area,
},'POST')

//删除地址
export const reqDelAddress = (addId) => ajax(BASE_URL+"/delUserAddress",{addId})

//得到某个用户的地址列表
export const reqGetAddressList = () => ajax(BASE_URL+"/getUserAddress",{})
