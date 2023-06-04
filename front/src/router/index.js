import  {createWebHashHistory} from 'vue-router'
import { createRouter} from 'vue-router';

const routes =[

    {
        path: '/',
        component:()=>import('../components/IndexPage.vue')
    },
    {
        path: '/Index',
        component:()=>import('../components/IndexPage.vue')
    },
    {
        path:'/Login',component:()=> import('../components/Login/LoginPage.vue')
    },

    {
        path:'/Register',component: ()=>import('../components/Register/RegisterPage.vue')

    },
    {
        path:'/user',component: ()=>import('../components/UserPage.vue'),
        children:[
            {
                path: 'userCenter',
                component: ()=>import('../components/UserHome/Home/UserCenter.vue')
            },
            {
                path: 'mesNotice',
                component: ()=>import('../components/UserHome/Home/MesNotice.vue')
            },
            {
                path: 'PWDMod',
                component: ()=>import('../components/UserHome/manger/pwdManger.vue')
            },
            {
                path: 'userInfo',
                component: ()=>import('../components/UserHome/manger/UserInfo.vue')
            },
            {
                path: 'userOrder',
                component: ()=>import('../components/UserHome/order/UseOrder.vue'),
                children:[
                    {
                        path:'orderDetail',
                        component:()=>import('../components/UserHome/order/UseOrderDetail.vue')
                    }
                ],
            },
            {
              path:'OrderDetail',
              component:()=>import('../components/UserHome/order/UseOrderDetail.vue')

            },
            {
                path: 'bookStall',
                component: ()=>import('../components/UserHome/bookStall/bookStall.vue')
            },
            {
                path: 'addressPage',
                component: ()=>import('../components/UserHome/Home/addressPage.vue')
            },
        ]
    },
    {
       path: '/BookInfo/:bookId', component: () => import('../components/Book/BookInfo.vue')
    },
    {
        path:'/Upload_3', component: ()=>import('../components/Common/upload_3.vue')
    },
    {
        path:'/BookInfo', component: ()=>import('../components/Book/BookInfo.vue')
    },
    {
        path:'/search/:selectResult?/:inputContent?',
        name:'Search',
        component: () => import('../components/Search/search.vue')
    },
    {
        path:'/buyPage/:bookId', component:()=>import('../components/BuyPage/buyPage.vue')
    }



]

const router = createRouter(
    {
        history: createWebHashHistory(),
        routes,
    })

export default router