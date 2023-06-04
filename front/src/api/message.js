import ajax from "./ajax";

const BASE_URL = 'http://localhost:8080'


//余额消息变动
export const reqModBalanceMex = () => ajax(BASE_URL+'/sendBalanceMes', {},"POST")

