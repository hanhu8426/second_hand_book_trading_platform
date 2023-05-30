import  {createWebHashHistory} from 'vue-router'
import { createRouter} from 'vue-router';

const routes =[

    {
        path: '/',
        name: 'Index',
        component:()=>import('../components/IndexPage.vue')
    },

    {
    path:'/Login',component:()=> import('../components/Login/LoginPage.vue')
    },
    {
        path:'/Test',component: ()=>import('../components/TestVue.vue')

    },
    {
        path:'/Register',component: ()=>import('../components/Register/RegisterPage.vue')

    },
    {
        path:'/Upload',component:()=>import('../components/Common/Upload.vue')
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
                component: ()=>import('../components/UserHome/order/UseOrder.vue')
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
    // {
    //     path:'*' , redirect :'/'
    // },
]

const router = createRouter(
    {
        history: createWebHashHistory(),
        routes,
    })

export default router
