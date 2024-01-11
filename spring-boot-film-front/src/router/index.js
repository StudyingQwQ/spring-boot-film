import { createRouter, createWebHistory } from "vue-router";
import {adminAuth, unauthorized} from "@/net/index.js";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "welcome",
            component: () => import("@/views/WelcomeView.vue"),
            children: [
                {
                    path: "",
                    name: "welcome-login",
                    component: () => import("@/views/welcome/LoginPage.vue"),
                },{
                    path: 'register',
                    name: 'welcome-register',
                    component: () => import("@/views/welcome/RegisterPage.vue")
                },{
                    path: 'forget',
                    name: 'welcome-forget',
                    component: () => import("@/views/welcome/ForgetPage.vue")
                }
            ]
        }, {
            path: '/index',
            name: 'index',
            component: () => import("@/views/indexView.vue"),
        }, {
            path: '/main',
            name: 'film',
            component: () => import("@/views/MainBackground.vue"),
            children: [
                            {
                                path: "",
                                name: "list",
                                component: () => import("@/views/main/MainList.vue"),
                            },{
                                path: 'detail',
                                name: 'film-detail',
                                component: () => import("@/views/main/FilmDetail.vue")
                            }
                        ]
        }, {
            path: '/user',
            name: 'user-info',
            component: () => import("@/views/user/User.vue"),
        },
        {
            path: '/detail',
            name: 'film-detail',
            component: () => import("@/views/index/Background.vue"),
        },
        {
            path: '/admin',
            name: 'admin',
            component: () => import("@/views/admin/AdminView.vue"),
        }

    ],
})
//路由前置守卫
router.beforeEach((to, from, next) => {
    const isUnauthorized = unauthorized()
    const admin = adminAuth()
    if(to.name.startsWith('welcome') && !isUnauthorized) {
        next('/index')
    } else if(to.fullPath.startsWith('/index') && isUnauthorized) {
        next('/')
    } else if(to.fullPath.startsWith('/admin') && !admin) {
        next('/')
    } else {
        next()
    }
})
export default router