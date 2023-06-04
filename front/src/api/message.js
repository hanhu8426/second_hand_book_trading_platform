import ajax from "./ajax";

const BASE_URL = 'http://8.140.52.224:8080'


//余额消息变动
export const reqModBalanceMex = () => ajax(BASE_URL+'/sendBalanceMes', {},"POST")

