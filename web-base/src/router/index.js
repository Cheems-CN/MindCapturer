import { createRouter, createWebHistory } from "vue-router";
import Home from "@/views/Home.vue";
import Tech from "@/views/Tech.vue";
import Login from "@/views/Login.vue";
import Register from "@/views/Register.vue";

const routes = [
    {
        path: "/",
        name: "Home",
        component: Home,
    },
    {
        path: "/tech",
        name: "Tech",
        component: Tech,
    },
    {
        path: "/login",
        name: "Login",
        component: Login,
    },
    {
        path: "/register",
        name: "Register",
        component: Register,
    },
    // 其他可能的 404 或跳转
    {
        path: "/:pathMatch(.*)*",
        redirect: "/",
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
