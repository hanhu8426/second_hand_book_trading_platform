# 前端接口文档（bzd）

- ## SearchPage（搜索页面）

  该页面有两个接口，分别是按种类搜索和按书名作者搜索，用于向后端请求相关书籍信息并展示

  **reqGetBookListBySort**

```
export const reqGetBookListBySort = (type,page,pageSize)=>ajax(BASE_URL+'/getBookListBySort',{type,page, pageSize})
```

​		*提交格式*

```
type：1
page：1
pageSize：10
```

​		*response*

```
response{
	"code":1,
	"msg":"success",
	"data":{
		"total":1,
		"rows":[
			{
				"bookId":1
				"image":"/img01.jpg"
				"name":"新华字典",
				"author"："作者",
				"campus":"校区",
				"price":13
			}
		]
	}
}
```

​		

​		**reqGetBookListByName**

```
export const reqGetBookListByName = (input,page,pageSize)=>ajax(BASE_URL+'/getBookListByName',{input,page, pageSize})
```

​		*提交格式*

```
input：“张三”
page：1
pageSize：10
```

​		*response*

```
response{
	"code":1,
	"msg":"success",
	"data":{
		"total":1,
		"rows":[
			{
				"bookId":1
				"image":"/img01.jpg"
				"name":"新华字典",
				"author"："作者",
				"campus":"校区",
				"price":13
			}
		]
	}
}
```



- ## GallaryPart（最新出版栏）

  首页右面展示最近上架的书籍，向后端请求相应书籍列表并展示

​		**reqGetRecBookList**

```
export const reqGetRecBookList =>ajax(BASE_URL+'/getRecBookList',{sort})
```

​		*response*

```
response{
	"code":1,
	"msg":"success",
	"data":{
		"total":1,
		"rows":[
			{
				"bookId":1
				"image":"/img01.jpg"
				"name":"新华字典",
				"author"："作者",
				"price":13
			}
			{
				"bookId":2
				"image":"/img02.jpg"
				"name":"新华字典",
				"author"："作者",
				"price":13
			}
			{
				"bookId":3
				"image":"/img03.jpg"
				"name":"新华字典",
				"author"："作者",
				"price":13
			}
		]
	}
}
```



- ## BookPage（书籍详情页）

  展示每个书籍详细信息，向后端传递书的ID进行查询并展示

  **reqGetBook**

```
export const reqGetBook = (bookId) => ajax(`${BASE_URL}/getBook/${bookId}`);
```

​		*提交格式*

```
"id" : 1
```

​		*response*

```
response{
	"code":1,
	"msg":"success",
	"data":{
        "bookId":1
        "name":"新华字典",
        "isbn":"0123456789"
        "type":1
        "author"："作者",
        "campus":"校区",
        "price":"售价"
        "description":"书籍描述"
        "image":"/img01.jpg"
        "sellerID":3
	}
}
```



- ## BuyPage（购买页面）

  在点击“立即购买”按钮后进入此页面，展示书籍购买订单信息，点击“立即下单”后提交订单。

  **reqAddAddress**

```
export const reqAddAddress = (address) => ajax(BASE_URL+'/addUserAddress', {
    "name": address.name,
    "phone": address.phone,
    "address": address.address,
    "area": address.area,
    "id":address.id,
},"POST")
```

​		*提交格式*

```
{
	"name":"张三",
	"phone":12345678901,
	"address":"翻斗花园"
	"area":"1"
	"id":3
}
```

​		*response*

```
{
	"code":1,
	"msg":"success",
	"data":{
		"name":"张三",
		"phone":12345678901,
        "address":"翻斗花园"
        "area":"1"
        "id":3,
        "addId":8
	}
}
```

​		

​		**reqGetAddressList**

```
export const reqGetAddressList = () => ajax(BASE_URL+"/getUserAddress",{})
```

​		*response*

```
{
	"code":1,
	"msg":"success",
	"data":[
		{
            "name":"张三",
            "phone":12345678901,
            "address":"翻斗花园"
            "area":"1"
            "id":3,
            "addId":8
		},
		{
            "name":"张三",
            "phone":12345678901,
            "address":"翻斗花园"
            "area":"1"
            "id":3,
            "addId":8
		},
		{
            "name":"张三",
            "phone":12345678901,
            "address":"翻斗花园"
            "area":"1"
            "id":3,
            "addId":8
		}
	]
}
```

​		

​		**reqAddOrder**

```
export const reqAddOrder = (addId,bookId) => ajax(BASE_URL+"/addOrder", {
    addId,bookId
},"POST")
```

​		*提交格式*

```
{
	“bookId":1,
	"addId":3,
}
```

​		*response*

```
{
	"code":1,
	"msg":"success",
	"data":null
}
```

​		**reqGetBook**

```
export const reqGetBook = (bookId) => ajax(`${BASE_URL}/getBook/${bookId}`);
```

​		*提交格式*

```
"id" : 1
```

​		*response*

```
response{
	"code":1,
	"msg":"success",
	"data":{
        "bookId":1
        "name":"新华字典",
        "isbn":"0123456789"
        "type":1
        "author"："作者",
        "campus":"校区",
        "price":"售价"
        "description":"书籍描述"
        "image":"/img01.jpg"
        "sellerID":3
	}
}
```

