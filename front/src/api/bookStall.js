import ajax from "./ajax";

const BASE_URL = 'http://8.140.52.224:8080'


//添加书籍
export const reqAddBook = (book) => ajax(BASE_URL+'/addBook', {
       name:book.name,
       author:book.author,
       isbn:book.isbn,
       type:book.type,
       description:book.description,
       status:book.status,
       campus:book.campus,
       price:book.price,
       recommend:book.recommend,
}, 'POST')

//修改书籍
export const reqModBook = (book) => ajax(BASE_URL+"/modifyBook",{
    bookId:book.bookId,
    image:book.image,
    name:book.name,
    author:book.author,
    type:book.type,
    description:book.description,
    price:book.price,
},'POST')

//删除书籍
export const reqDelBook = (bookId) => ajax(BASE_URL+"/delBook",{bookId})

//得到某个用户的书籍列表
export const reqGetBookList = () => ajax(BASE_URL+"/getBookList",{})

export const reqAppointBook = (bookId) => ajax(BASE_URL+"/AppointBook",{bookId})
