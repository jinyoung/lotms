
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import BasicUserManager from "./components/listers/BasicUserCards"
import BasicUserDetail from "./components/listers/BasicUserDetail"
import BasicCompanyManager from "./components/listers/BasicCompanyCards"
import BasicCompanyDetail from "./components/listers/BasicCompanyDetail"

import SalesOrderManager from "./components/listers/SalesOrderCards"
import SalesOrderDetail from "./components/listers/SalesOrderDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/basics/users',
                name: 'BasicUserManager',
                component: BasicUserManager
            },
            {
                path: '/basics/users/:id',
                name: 'BasicUserDetail',
                component: BasicUserDetail
            },
            {
                path: '/basics/companies',
                name: 'BasicCompanyManager',
                component: BasicCompanyManager
            },
            {
                path: '/basics/companies/:id',
                name: 'BasicCompanyDetail',
                component: BasicCompanyDetail
            },

            {
                path: '/sales/orders',
                name: 'SalesOrderManager',
                component: SalesOrderManager
            },
            {
                path: '/sales/orders/:id',
                name: 'SalesOrderDetail',
                component: SalesOrderDetail
            },



    ]
})
