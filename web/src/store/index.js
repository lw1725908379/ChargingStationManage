import { defineStore } from "pinia";

const usePiniaStore = defineStore("piniaStore", {
  state: () => {
    return {
      // 是否折叠菜单
      isCollapse: false,
      // 菜单数据
      menuList: [
        {
          id: 1,
          icon: "HomeFilled",
          name: "首页",
          path: "/index",
        },
        {
          id: 2,
          icon: "UserFilled",
          name: "用户管理",
          path: "/user",
          children: [
            {
              id: 3,
              icon: "List",
              name: "用户列表",
              path: "/userList",
            },
          ],
        },
        {
          id: 4,
          icon: "OfficeBuilding",
          name: "运营商管理",
          path: "/operator",
          children: [
            {
              id: 5,
              icon: "List",
              name: "运营商列表",
              path: "/operatorList",
            },
          ],
        },
        {
          id: 6,
          icon: "Guide",
          name: "电站管理",
          path: "/station",
          children: [
            {
              id: 7,
              icon: "List",
              name: "电站列表",
              path: "/stationList",
            },
          ],
        },
        {
          id: 8,
          icon: "SetUp",
          name: "充电桩管理",
          path: "/charge",
          children: [
            {
              id: 9,
              icon: "List",
              name: "充电桩列表",
              path: "/chargeList",
            },
          ],
        },
        {
          id: 10,
          icon: "AlarmClock",
          name: "预约管理",
          path: "/appoint",
          children: [
            {
              id: 11,
              icon: "List",
              name: "预约列表",
              path: "/appointList",
            },
          ],
        },
        {
          id: 12,
          icon: "Money",
          name: "订单管理",
          path: "/order",
          children: [
            {
              id: 13,
              icon: "List",
              name: "订单列表",
              path: "/orderList",
            },
          ],
        },
        {
          id: 13,
          icon: "Tools",
          name: "报修管理",
          path: "/repair",
          children: [
            {
              id: 14,
              icon: "List",
              name: "报修列表",
              path: "/repairList",
            },
          ],
        },
        {
          id: 15,
          icon: "Warning",
          name: "告警管理",
          path: "/warn",
          children: [
            {
              id: 16,
              icon: "List",
              name: "告警列表",
              path: "/warnList",
            },
          ],
        },
        {
          id: 17,
          icon: "ChatDotRound",
          name: "留言管理",
          path: "/comment",
          children: [
            {
              id: 18,
              icon: "List",
              name: "留言列表",
              path: "/commentList",
            },
          ],
        },
      ],
    };
  },
  actions: {
    setCollapse(value) {
      this.isCollapse = !value;
    },
  },
});

export default usePiniaStore;
