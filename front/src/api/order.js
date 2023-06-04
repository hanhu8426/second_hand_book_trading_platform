import ajax from "./ajax";

const BASE_URL = 'http://8.140.52.224:8080'

//提交订单 addOrder
export const reqAddOrder = (addId,bookId) => ajax(BASE_URL+"/addOrder", {
    addId,bookId
},"POST")
// export const reqAddOrder = (addId, bookId) => {
//     const url = BASE_URL + "/addOrder";
//     const data = {
//         addId,
//         bookId
//     };
//
//     return axios.post(url, data)
//         .then(response => response.data)
//         .catch(error => {
//             // 处理错误
//             console.error('Error:', error);
//             throw error;
//         });
// };

//管理员得到订单列表
export const reqAdminGetOrderList = (page,pageSize) => ajax(BASE_URL+"/getAdminOrderList",{page,pageSize})

//管理员得到订单的明细getOrderDto
export const reqAdminGetOrderDetail = (id) => ajax(BASE_URL+"/getOrderDto",{id})

//删除订单
export const reqDelOrder = (id) => ajax(BASE_URL+"/delOrder",{id})

//发货deliverOrder
export const reqDeliverOrder = (id,logisticsCompany,logisticsNum) => ajax(BASE_URL+"/deliverOrder",{id,logisticsCompany,logisticsNum})

//普通用户得到自己的订单列表getUserOrderList
export const reqUserGetOrderList = (account,page,pageSize,orderStatus,beUserDelete) => ajax(BASE_URL+"/getUserOrderList",{account,page,pageSize,orderStatus,beUserDelete})

//修改订单状态modifyOrderStatus
export const reqModOrderStatus = (id,orderStatus) => ajax(BASE_URL+"/modifyOrderStatus",{id,orderStatus})

//得到时间筛选后的订单统计信息
export const reqGetOrderStatistic = (beginDate,endDate) => ajax(BASE_URL+"/order/date",{beginDate,endDate})
