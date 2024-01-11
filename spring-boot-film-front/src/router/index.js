import { createRouter, createWebHistory } from 'vue-router'
import { unauthorized } from "@/net";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'welcome',
            component: () => import('@/views/WelcomeView.vue'),
            children: [
                {
                    path: '',
                    name: 'welcome-login',
                    component: () => import('@/views/welcome/LoginPage.vue')
                }, {
                    path: 'register',
                    name: 'welcome-register',
                    component: () => import('@/views/welcome/RegisterPage.vue')
                }, {
                    path: 'forget',
                    name: 'welcome-forget',
                    component: () => import('@/views/welcome/ForgetPage.vue')
                }
            ]
        }, {
            path: '/index',
            name: 'index',
            component: () => import("@/views/indexView.vue"),
        }, {
            path: '/admin',
            name: 'admin',
            component: () => import("@/views/admin/AdminIndex.vue"),
            children: [
                {
                    path: 'users',
                    name: 'admin-users',
                    component: () => import("@/views/admin/views/UserManagement.vue"),
                }, {
                    path: 'exports',
                    name: 'admin-exports',
                    component: () => import("@/views/admin/views/ExportManagement.vue"),
                }, {
                    path: 'filmlist',
                    name: 'admin-filmlist',
                    component: () => import("@/views/admin/views/FilmList.vue"),
                }, {
                    path: 'uploadfilm',
                    name: 'admin-uploadfilm',
                    component: () => import("@/views/admin/views/UploadFilm.vue"),
                }
            ]
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
    ],
})

router.beforeEach((to, from, next) => {
    const isUnauthorized = unauthorized()
    if(to.name.startsWith('welcome') && !isUnauthorized) {
        next('/index')
    } else if(to.fullPath.startsWith('/index') && isUnauthorized) {
        next('/')
    }  else {
        next()
    }
})

export default router