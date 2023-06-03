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

//得到某个用户的地址列表


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
export const reqGetBook = (bookId) => ajax(BASE_URL + '/getBook/' + bookId);

export const reqGetRecBookList = (sort)=>ajax(BASE_URL+'/getRecBookList',{sort})



//删除
export const reqDelBook = (bookId)=>ajax(BASE_URL+'/delBook',{bookId})
export const reqDelBookImg = (bookId,url)=>ajax(BASE_URL+'/delOneImg',{bookId,url})


export const reqGetBookImgPathList = (isbn)=>ajax(BASE_URL+'/getImgPaths',{isbn})


export const reqModifyPut = (bookId,put)=>ajax(BASE_URL+'/modifyPut',{bookId,put})
export const reqModifyRec = (bookId,recommend)=>ajax(BASE_URL+'/modifyRec',{bookId,recommend})
export const reqModifyNew = (bookId,newProduct)=>ajax(BASE_URL+'/modifyNew',{bookId,newProduct})

//getSortBookList
// export const reqGetSortBookList = (type)=>ajax(BASE_URL+'/getSortBookList',{type})

//getSortBookListBySort
export const reqGetBookListBySort = (type,page,pageSize)=>ajax(BASE_URL+'/getBookListBySort',{type,page, pageSize})
export const reqGetBookListByName = (input,page,pageSize)=>ajax(BASE_URL+'/getBookListByName',{input,page, pageSize})

