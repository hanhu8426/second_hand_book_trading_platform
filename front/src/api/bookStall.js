import ajax from "./ajax";

const BASE_URL = 'http://localhost:8080'


//添加书籍
export const reqAddBook = (book) => ajax(BASE_URL+'/bookStall', {
       image:book.image,
       name:book.name,
       type:book.type,
       description:book.description,
       price:book.price,
}, 'POST')

//修改书籍
export const reqModBook = (book) => ajax(BASE_URL+"/modifyBook",{
    name:book.name,
    type:book.type,
    description:book.description,
    price:book.price,
},'POST')

//删除书籍
export const reqDelBook = (bookId) => ajax(BASE_URL+"/delBook",{bookId})

//得到某个用户的书籍列表
export const reqGetBookList = () => ajax(BASE_URL+"/getBookList",{})
