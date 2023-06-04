import ajax from "./ajax";

const BASE_URL = 'http://localhost:8080'


//上传图片
export const uploadImage = (formData) => ajax(BASE_URL+'/bookStall/uploadImg', {
       formData:formData
}, 'POST')

//添加地址
export const reqAddBook = (book) => ajax(BASE_URL+'/bookStall', {
       img:book.img,
       name:book.name,
       type:book.type,
       introduction:book.introduction,
       price:book.price,
}, 'POST')

//修改地址
export const reqModBook = (book) => ajax(BASE_URL+"/modifyBook",{
    img:book.img,
    name:book.name,
    type:book.type,
    introduction:book.introduction,
    price:book.price,
},'POST')

//删除地址
export const reqDelBook = (id) => ajax(BASE_URL+"/delBook",{id})

//得到某个用户的地址列表
export const reqGetBookList = (account) => ajax(BASE_URL+"/getBookList",{account})

export const reqGetBook = (bookId) => ajax(`${BASE_URL}/getBook/${bookId}`);
