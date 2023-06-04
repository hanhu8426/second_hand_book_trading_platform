import ajax from './ajax'

const BASE_URL = 'http://8.140.52.224:8080'

// 1、登录
export const reqLogin = ({account, password}) => ajax(BASE_URL+'/getBook', {account, password},"POST")
