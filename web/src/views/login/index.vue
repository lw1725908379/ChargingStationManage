<template>
  <div class="bigBox">
    <div class="box">
      <div class="slide-box" ref="slideRef">
        <h1 class="slide-title">充电桩管理系统</h1>
        <h1 class="slide-title" v-if="!switchLock">欢迎注册</h1>
        <p class="slide-title" v-if="switchLock">欢迎登录</p>
        <div class="img-box">
          <img src="@/assets/logo.png" alt="LOGO" />
        </div>
        <div class="slide-button" v-if="switchLock" @click="toRegister">
          还没有账户？点击注册
        </div>
        <div class="slide-button" v-if="!switchLock" @click="toLogin">
          已存在账户？点击登录
        </div>
      </div>

      <!-- 注册盒子 -->
      <div class="register-form">
        <!-- 标题盒子 -->
        <div class="register-title">
          <h1>用户注册</h1>
        </div>
        <!-- 输入框盒子 -->
        <el-form ref="registerFormRef" :model="registerForm">
          <el-form-item prop="username">
            <el-input
              type="text"
              v-model="registerForm.username"
              placeholder="请输入用户昵称"
              suffix-icon="User"
            />
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              type="password"
              v-model="registerForm.password"
              placeholder="请输入用户密码"
              show-password
              suffix-icon="Lock"
            />
          </el-form-item>
          <el-form-item prop="rePassword">
            <el-input
              type="password"
              v-model="registerForm.rePassword"
              placeholder="请输入确认密码"
              show-password
              suffix-icon="Lock"
            />
          </el-form-item>
          <el-form-item prop="phone">
            <el-input
              type="text"
              v-model="registerForm.phone"
              placeholder="请输入手机号码"
              suffix-icon="Phone"
            />
          </el-form-item>
        </el-form>
        <!-- 注册按钮盒子 -->
        <div class="register-btn-box">
          <el-button class="register-btn" @click="handleRegister"
            >注册</el-button
          >
        </div>
      </div>

      <!-- 登录盒子 -->
      <div class="login-form">
        <div class="login-title">
          <h1>用户登录</h1>
        </div>
        <el-form ref="loginFormRef" :model="loginForm">
          <el-form-item prop="username">
            <el-input
              type="text"
              v-model="loginForm.username"
              placeholder="请输入用户昵称"
              suffix-icon="User"
            />
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              type="password"
              v-model="loginForm.password"
              placeholder="请输入用户密码"
              show-password
              suffix-icon="Lock"
            />
          </el-form-item>
        </el-form>
        <!-- 登录按钮盒子 -->
        <div class="login-btn-box">
          <el-button
            class="login-btn"
            :loading="loginLoading"
            @click="handleLogin"
            >登录</el-button
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from "vue";
import { axiosPostRequest, setSessionStorage } from "@/util/index.js";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
const router = useRouter();
// 切换开关
const switchLock = ref(true);
// 根据ref设置css
const slideRef = ref();
// 切换按钮背景颜色
const switchBtnBg = ref("#FE3E7C");
// 切换按钮悬浮背景颜色
const switchBtnHoverBg = ref("#F9739E");

const registerFormRef = ref();
const loginFormRef = ref();
const loginLoading = ref(false);

const registerForm = reactive({
  username: "",
  password: "",
  phone: "",
  rePassword: "",
});

const loginForm = reactive({
  username: "",
  password: "",
});

/** 切换注册 */
const toRegister = () => {
  switchLock.value = false;
  slideRef.value.style.background = "#c9e0ed";
  slideRef.value.style.transform = "translateX(100%)";
  switchBtnBg.value = "#409EFF";
  switchBtnHoverBg.value = "#79BBFF";
};
/** 切换登录 */
const toLogin = () => {
  switchLock.value = true;
  slideRef.value.style.background = "#edd4dc";
  slideRef.value.style.transform = "translateX(0%)";
  switchBtnBg.value = "#FE3E7C";
  switchBtnHoverBg.value = "#F9739E";
};
// 注册操作
const handleRegister = async () => {
  const response = await axiosPostRequest("/user/register", registerForm);
  if (response.code === 0) {
    ElMessage.success(response.msg);
    registerFormRef.value.resetFields();
  } else {
    ElMessage.error(response.msg);
  }
};

// 登录操作
const handleLogin = async () => {
  // TODO 增加前端非法值判断，减轻后端压力...
  loginLoading.value = true;
  const response = await axiosPostRequest("/user/login", loginForm);
  if (response.code === 0) {
    ElMessage.success(response.msg);
    // 本地持久化登入用户token等信息
    setSessionStorage(response.data.token);
    loginFormRef.value.resetFields();
    router.push("/index");
  } else {
    ElMessage.error(response.msg);
  }
  loginLoading.value = false;
};
</script>

<style lang="scss" scoped>
.bigBox {
  display: flex;
  height: 100vh;
  overflow-x: hidden;
  background: linear-gradient(to right, rgb(247 209 215), rgb(191 227 241));
 
  .box {
    position: relative;
    z-index: 2;
    display: flex;
    min-width: 720px;
    min-height: 400px;
    margin: auto;
    border: 1px solid rgb(255 255 255 / 60%);
    border-radius: 8px;
    box-shadow: 2px 1px 19px rgb(0 0 0 / 10%);
    .slide-box {
      position: absolute;
      top: 0;
      left: 0;
      z-index: 10;
      width: 50%;
      height: 100%;
      font-size: 16px;
      background-color: #edd4dc;
      border-radius: 4px;
      box-shadow: 2px 1px 19px rgb(0 0 0 / 10%);
      transition: 0.5s ease-in-out;
      h1 {
        margin-top: 50px;
        font-weight: bold;
        text-align: center;
        text-shadow: 4px 4px 3px rgb(0 0 0 / 10%);
        letter-spacing: 2px;
        user-select: none;
      }
      p {
        height: 30px;
        margin: 20px 0;
        font-weight: bold;
        line-height: 30px;
        text-align: center;
        text-shadow: 4px 4px 3px rgb(0 0 0 / 10%);
        /* 禁止选中 */
        user-select: none;
      }
      .slide-title {
        font-size: 20px;
        color: v-bind(switchBtnBg);
      }
      .img-box {
        width: 80px;
        height: 80px;
        margin: auto;
        margin-top: 30px;
        overflow: hidden;
        user-select: none;
        border-radius: 50%;
        box-shadow: 4px 4px 3px rgb(0 0 0 / 10%);
        img {
          width: 100%;
          /* 动画过渡，先加速再减速 */
          transition: 0.5s ease-in-out;
        }
      }
      .slide-button {
        width: 160px;
        padding: 8px 16px;
        margin: auto;
        margin-top: 60px;
        font-size: 14px;
        line-height: 14px;
        color: #fff;
        cursor: pointer;
        user-select: none;
        background: v-bind(switchBtnBg);
        border: 1px solid rgb(255 255 255);
        border-radius: 20px;
        box-shadow: 4px 4px 3px rgb(0 0 0 / 10%);
        &:hover {
          background: v-bind(switchBtnHoverBg);
        }
      }
    }
    .login-form,
    .register-form {
      flex: 1;
      height: 100%;
    }
    .login-title {
      height: 102px;
      line-height: 102px;
      h1 {
        font-size: 24px;
        font-weight: bold;
        color: #409eff;
        text-align: center;
        text-shadow: 4px 4px 3px rgb(0 0 0 / 10%);
        letter-spacing: 5px;
        user-select: none;
      }
    }
    .el-form {
      display: flex;
      flex-direction: column;
      align-items: center;
      .el-form-item {
        width: 60%;
      }
    }
    .login-btn-box {
      display: flex;
      .login-btn {
        height: 32px;
        padding: 8px 16px;
        margin: auto;
        margin-top: 16px;
        font-size: 14px;
        line-height: 14px;
        color: #fff;
        cursor: pointer;
        user-select: none;
        background: #409eff;
        border: 1px solid rgb(255 255 255);
        border-radius: 20px;
        box-shadow: 4px 4px 3px rgb(0 0 0 / 10%);
        &:hover {
          background: #67aff7;
        }
      }
    }
    .register-title {
      height: 98px;
      line-height: 98px;
      h1 {
        font-size: 22px;
        font-weight: bold;
        color: #fe3e7c;
        text-align: center;
        text-shadow: 4px 4px 3px rgb(0 0 0 / 10%);
        letter-spacing: 5px;
        user-select: none;
      }
    }
    .register-btn-box {
      display: flex;
      .register-btn {
        height: 32px;
        padding: 8px 16px;
        margin: auto;
        font-size: 14px;
        line-height: 14px;
        color: #fff;
        cursor: pointer;
        user-select: none;
        background: #fe3e7c;
        border: 1px solid rgb(255 255 255);
        border-radius: 20px;
        box-shadow: 4px 4px 3px rgb(0 0 0 / 10%);
        &:hover {
          background: #f9739e;
        }
      }
    }
  }
}
</style>
