import ajax from "./ajax";

<<<<<<< HEAD
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
=======
const BASE_URL = 'http://localhost:8080/book'

//添加出版社newProduct: true,
//                 recommend
export const reqAddBook = (book) => ajax(BASE_URL+'/addBook', {
    author: book.author,
    isbn: book.isbn,
    publish: book.publish,
    birthday: book.birthday,
    marketPrice: book.marketPrice,
    price: book.price,
    stock: book.stock,
    description: book.description,
    put: book.put,
    bookName: book.bookName,
    rank: book.rank,
    newProduct: book.newProduct,
    recommend: book.recommend,
    bookSort: book.bookSort
}, 'POST')

export const reqModifyBook = (book) => ajax(BASE_URL+'/modifyBook', {
    author: book.author,
    isbn: book.isbn,
    publish: book.publish,
    birthday: book.birthday,
    marketPrice: book.marketPrice,
    price: book.price,
    stock: book.stock,
    description: book.description,
    put: book.put,
    bookName: book.bookName,
    rank: book.rank,
    newProduct: book.newProduct,
    recommend: book.recommend,
    bookSort: book.bookSort,
    id: book.id
}, 'POST')


export const reqGetBookList = (page,pageSize)=>ajax(BASE_URL+'/getBookList',{page, pageSize})
export const reqGetBook = (id)=>ajax(BASE_URL+'/getBook',{id})

export const reqGetRecBookList = (sort)=>ajax(BASE_URL+'/getRecBookList',{sort})



//删除
export const reqDelBook = (bookId)=>ajax(BASE_URL+'/delBook',{bookId})
export const reqDelBookImg = (bookId,url)=>ajax(BASE_URL+'/delOneImg',{bookId,url})


export const reqGetBookImgPathList = (isbn)=>ajax(BASE_URL+'/getImgPaths',{isbn})


export const reqModifyPut = (bookId,put)=>ajax(BASE_URL+'/modifyPut',{bookId,put})
export const reqModifyRec = (bookId,recommend)=>ajax(BASE_URL+'/modifyRec',{bookId,recommend})
export const reqModifyNew = (bookId,newProduct)=>ajax(BASE_URL+'/modifyNew',{bookId,newProduct})

//getSortBookList
export const reqGetSortBookList = (sortId)=>ajax(BASE_URL+'/getSortBookList',{sortId})

//getSortBookListBySort
export const reqGetBookListBySort = (sortId,page,pageSize)=>ajax(BASE_URL+'/getBookListBySort',{sortId,page, pageSize})
>>>>>>> 826b6550bfc3c5701fcd4160c6180764e3485c89
