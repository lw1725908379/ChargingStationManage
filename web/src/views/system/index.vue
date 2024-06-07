<template>
  <div
    style="
      padding: 6px;
      display: flex;
      flex-direction: column;
      height: calc(100% - 12px);
    "
  >
    <el-card class="card" shadow="hover" style="flex-shrink: 0; flex-grow: 0">
      <!-- 添加水印 -->
      <div
        class="user"
        v-waterMarker="{ text: 'wenLiu', textColor: '#D9D9D9' }"
      >
        <img
          class="user-img"
          :src="filterPhoto(data.loginUser.headPic)"
          alt="avatar"
        />
        <div style="padding-left: 20px">
          <div class="user-info">
            <span v-text="data.loginUser.username"></span>
            <span
              v-if="data.loginUser.roleId === 1"
              style="color: rgb(64 158 255)"
              >[普通用户]</span
            >
            <span
              v-if="data.loginUser.roleId === 2"
              style="color: rgb(64 158 255)"
              >[管理员]</span
            >
          </div>
          <div class="user-info" style="padding-bottom: 0">
            欢迎来到充电桩管理系统
          </div>
        </div>
      </div>
    </el-card>
    <el-row :gutter="20" style="margin-top: 5px; flex-shrink: 0; flex-grow: 0">
      <el-col :span="6" :lg="6" :md="12" :sm="12" :xs="24">
        <el-card class="card" shadow="hover">
          <div class="data-status">
            <span class="title" v-if="data.loginUser.roleId === 1"
              >我的预约数</span
            >
            <span class="title" v-if="data.loginUser.roleId === 2"
              >全部预约数</span
            >
            <el-tag type="info">总数</el-tag>
          </div>
          <div class="value" v-text="data.appointList.length"></div>
        </el-card>
      </el-col>
      <el-col :span="6" :lg="6" :md="12" :sm="12" :xs="24">
        <el-card class="card" shadow="hover">
          <div class="data-status">
            <span class="title" v-if="data.loginUser.roleId === 1"
              >我的告警数</span
            >
            <span class="title" v-if="data.loginUser.roleId === 2"
              >全部告警数</span
            >
            <el-tag type="success">总数</el-tag>
          </div>
          <div class="value" v-text="data.warnList.length"></div>
        </el-card>
      </el-col>
      <el-col :span="6" :lg="6" :md="12" :sm="12" :xs="24">
        <el-card class="card" shadow="hover">
          <div class="data-status">
            <span class="title" v-if="data.loginUser.roleId === 1"
              >我的订单数</span
            >
            <span class="title" v-if="data.loginUser.roleId === 2"
              >全部订单数</span
            >
            <el-tag type="warning">总数</el-tag>
          </div>
          <div class="value" v-text="data.orderList.length"></div>
        </el-card>
      </el-col>
      <el-col :span="6" :lg="6" :md="12" :sm="12" :xs="24">
        <el-card class="card" shadow="hover">
          <div class="data-status">
            <span class="title" v-if="data.loginUser.roleId === 1"
              >我的留言数</span
            >
            <span class="title" v-if="data.loginUser.roleId === 2"
              >全部留言数</span
            >
            <el-tag type="danger">总数</el-tag>
          </div>
          <div class="value" v-text="data.commentList.length"></div>
        </el-card>
      </el-col>
    </el-row>
    <el-row
      :gutter="20"
      style="margin-top: 5px; flex-shrink: 1; flex-grow: 1; overflow-y: auto"
    >
      <el-col
        :span="12"
        :lg="12"
        :md="12"
        :sm="24"
        :xs="24"
        style="height: 100%"
      >
        <el-scrollbar>
          <el-card class="card" shadow="hover">
            <template #header>
              <div>信誉积分变化明细</div>
            </template>
            <el-timeline v-if="data.rateList.length > 0">
              <el-timeline-item
                type="warning"
                v-for="(item, index) in data.rateList"
                :key="index"
                :timestamp="item.createTime"
              >
                <div
                  v-if="item.type === 1"
                  v-text="'+' + item.score + '分，当前总分：' + item.nowScore"
                ></div>
                <div
                  v-if="item.type === 2"
                  v-text="'-' + item.score + '分，当前总分：' + item.nowScore"
                ></div>
                <div v-text="'变动描述：' + item.content"></div>
              </el-timeline-item>
            </el-timeline>
            <el-empty v-else description="暂无数据" />
          </el-card>
        </el-scrollbar>
      </el-col>
      <el-col :span="12" :lg="12" :md="12" :sm="24" :xs="24">
        <el-card class="card" shadow="hover">
          <el-calendar>
            <template #date-cell="{ data }">
              <div>
                <div>
                  {{ data.day.split("-").slice(2).join("-") }}
                  <div
                    v-if="getAppointedNum(data.day) > 0"
                    style="
                      font-size: 10px;
                      margin-top: 5px;
                      color: rgb(64 158 255);
                    "
                  >
                    {{ getAppointedNum(data.day) }}个预约
                  </div>
                </div>
              </div>
            </template>
          </el-calendar>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script setup>
import { ref, reactive, onMounted, computed } from "vue";
import { ElMessage } from "element-plus";
import {
  axiosPostRequest,
  getSessionStorage,
  myEventBus,
} from "@/util/index.js";
import { useRouter } from "vue-router";
const router = useRouter();
const emitter = myEventBus();
emitter.on("refresh", () => {
  getLoginUser();
});
onMounted(() => {
  getLoginUser();
});

const data = reactive({
  loginUser: {
    id: "",
    headPic: "common/no_image.jpg",
    username: "",
    roleId: 1,
  },
  appointedList: [],
  appointList: [],
  rateList: [],
  warnList: [],
  orderList: [],
  commentList: [],
});

const filterPhoto = computed(() => (photo) => {
  return import.meta.env.VITE_SERVER + "/photo/view?filename=" + photo;
});

// 获取当前登录用户
const getLoginUser = async () => {
  const response = await axiosPostRequest("/user/check_login", {
    token: getSessionStorage(),
  });
  if (response.code === 0) {
    data.loginUser = response.data;
    getAppointList();
    getAppointedList();
    getRateList();
    getOrderList();
    getWarnList();
    getCommentList();
  } else {
    ElMessage.error(response.msg);
    router.push("/login");
  }
};

// 获取预约数
const getAppointedNum = computed(() => (day) => {
  let count = 0;
  data.appointedList.forEach((item) => {
    if (item.day === day) {
      count++;
    }
  });
  return count;
});

// 获取当前登录用户的预约数据
const getAppointList = async () => {
  const response = await axiosPostRequest("/appoint/all", {
    userId: data.loginUser.roleId === 1 ? data.loginUser.id : "",
  });
  if (response.code === 0) {
    data.appointList = response.data;
  } else {
    ElMessage.error(response.msg);
  }
};

// 获取日历上预约数据
const getAppointedList = async () => {
  const response = await axiosPostRequest("/appoint/appointed", {
    userId: data.loginUser.id,
  });
  if (response.code === 0) {
    data.appointedList = response.data;
  } else {
    ElMessage.error(response.msg);
  }
};

// 获取当前登录用户的积分明细
const getRateList = async () => {
  const response = await axiosPostRequest("/user/rate", {
    id: data.loginUser.id,
  });
  if (response.code === 0) {
    data.rateList = response.data;
  } else {
    ElMessage.error(response.msg);
  }
};

// 获取当前登录用户的告警数据
const getWarnList = async () => {
  const response = await axiosPostRequest("/warn/all", {
    userId: data.loginUser.roleId === 1 ? data.loginUser.id : "",
  });
  if (response.code === 0) {
    data.warnList = response.data;
  } else {
    ElMessage.error(response.msg);
  }
};

// 获取当前登录用户的订单数据
const getOrderList = async () => {
  const response = await axiosPostRequest("/order/all", {
    userId: data.loginUser.roleId === 1 ? data.loginUser.id : "",
  });
  if (response.code === 0) {
    data.orderList = response.data;
  } else {
    ElMessage.error(response.msg);
  }
};

// 获取当前登录用户的留言数据
const getCommentList = async () => {
  const response = await axiosPostRequest("/comment/all", {
    userId: data.loginUser.roleId === 1 ? data.loginUser.id : "",
  });
  if (response.code === 0) {
    data.commentList = response.data;
  } else {
    ElMessage.error(response.msg);
  }
};
</script>
<style lang="scss" scoped>
.card {
  border-radius: 0.375rem;
  .user {
    display: flex;
    align-items: center;
    .user-img {
      user-select: none;
      width: 60px;
      height: 60px;
      border-radius: 50%;
    }
    .user-info {
      white-space: nowrap;
      padding-bottom: 8px;
      font-weight: bold;
    }
  }
  .data-status {
    display: flex;
    justify-content: space-between;
    .title {
      font-size: 0.875rem;
      line-height: 1.25rem;
    }
  }
  .value {
    font-size: 1.5rem;
    line-height: 2rem;
  }
  :deep(.el-calendar-day) {
    height: 50px !important;
    p {
      margin: 0 !important;
    }
  }
  :deep(.el-calendar__body) {
    padding: 0 20px;
  }
  :deep(.el-calendar__header) {
    padding: 0 20px 12px 20px;
  }
}
</style>
