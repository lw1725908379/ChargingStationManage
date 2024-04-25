import { createRouter, createWebHistory } from "vue-router";
import { getSessionStorage } from "@/util/index.js";

const routes = [
  {
    path: "/login",
    name: "Login",
    component: () => import("@/views/login/index.vue"),
  },
  {
    path: "/",
    name: "Layout",
    meta: {
      loginRequire: true,
    },
    component: () => import("@/views/layout/index.vue"),
    children: [
      {
        path: "index",
        name: "Index",
        component: () => import("@/views/system/index.vue"),
      },
      {
        path: "userList",
        name: "UserList",
        component: () => import("@/views/user/list.vue"),
      },
      {
        path: "operatorList",
        name: "OperatorList",
        component: () => import("@/views/operator/list.vue"),
      },
      {
        path: "stationList",
        name: "StationList",
        component: () => import("@/views/station/list.vue"),
      },
      {
        path: "chargeList",
        name: "ChargeList",
        component: () => import("@/views/charge/list.vue"),
      },
      {
        path: "appointList",
        name: "AppointList",
        component: () => import("@/views/appoint/list.vue"),
      },
      {
        path: "orderList",
        name: "OrderList",
        component: () => import("@/views/order/list.vue"),
      },
      {
        path: "repairList",
        name: "RepairList",
        component: () => import("@/views/repair/list.vue"),
      },
      {
        path: "warnList",
        name: "WarnList",
        component: () => import("@/views/warn/list.vue"),
      },
      {
        path: "commentList",
        name: "CommentList",
        component: () => import("@/views/comment/list.vue"),
      },
    ],
  },
  {
    path: "/",
    redirect: "/index",
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from) => {
  if (to?.meta?.loginRequire) {
    let token = getSessionStorage();
    if (!token) {
      return {
        path: "/login",
      };
    }
  }
});

export default router;
