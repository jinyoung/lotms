
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import UserManager from "./components/listers/UserCards"
import UserDetail from "./components/listers/UserDetail"
import CompanyManager from "./components/listers/CompanyCards"
import CompanyDetail from "./components/listers/CompanyDetail"

import OrderManager from "./components/listers/OrderCards"
import OrderDetail from "./components/listers/OrderDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/users',
                name: 'UserManager',
                component: UserManager
            },
            {
                path: '/users/:id',
                name: 'UserDetail',
                component: UserDetail
            },
            {
                path: '/companies',
                name: 'CompanyManager',
                component: CompanyManager
            },
            {
                path: '/companies/:id',
                name: 'CompanyDetail',
                component: CompanyDetail
            },

            {
                path: '/orders',
                name: 'OrderManager',
                component: OrderManager
            },
            {
                path: '/orders/:id',
                name: 'OrderDetail',
                component: OrderDetail
            },



    ]
})
