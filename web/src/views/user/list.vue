<template>
  <div style="padding: 6px 6px 0 6px">
    <el-card class="card-list" shadow="never">
      <el-form
        ref="searchFormRef"
        :inline="true"
        :model="dataList.searchParams"
      >
        <el-form-item label="用户昵称" prop="username">
          <el-input
            v-model="dataList.searchParams.username"
            placeholder="请输入用户昵称"
            style="width: 200px"
          ></el-input>
        </el-form-item>
        <el-form-item label="用户角色" prop="roleId">
          <el-select
            v-model="dataList.searchParams.roleId"
            placeholder="请选择用户角色"
          >
            <el-option label="全部" :value="0" />
            <el-option label="普通用户" :value="1" />
            <el-option label="管理员" :value="2" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="search" plain @click="getUserList"
            >搜索</el-button
          >
          <el-button type="danger" icon="refresh" plain @click="resetSearchForm"
            >重置</el-button
          >
        </el-form-item>
      </el-form>

      <el-row :gutter="10" v-if="dataList.loginUser.roleId === 2">
        <el-col :span="1.5">
          <el-button type="primary" icon="plus" plain @click="openAddDialog"
            >新增</el-button
          >
        </el-col>
        <el-col :span="1.5">
          <el-button type="success" icon="edit" plain @click="openEditDialog"
            >修改</el-button
          >
        </el-col>
        <el-col :span="1.5">
          <el-button type="danger" icon="delete" plain @click="openRemoveDialog"
            >删除</el-button
          >
        </el-col>
      </el-row>

      <el-table
        border
        @selection-change="handleSelectionChange"
        v-adaptive
        :data="dataList.userList"
        empty-text="暂时没查到数据哟🌻"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column label="序号" prop="id" width="140px"></el-table-column>
        <el-table-column
          label="用户昵称"
          prop="username"
          width="200px"
        ></el-table-column>
        <el-table-column label="用户头像" width="145px">
          <template #default="scope">
            <div>
              <el-image
                :preview-teleported="true"
                :preview-src-list="[filterPhoto(scope.row.headPic)]"
                style="width: 120px; height: 80px"
                :src="filterPhoto(scope.row.headPic)"
              />
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="用户手机号"
          prop="phone"
          width="200px"
        ></el-table-column>
        <el-table-column label="用户性别" width="100px">
          <template #default="scope">
            <div>
              <span v-if="scope.row.sex === 1">男</span>
              <span v-if="scope.row.sex === 2">女</span>
              <span v-if="scope.row.sex === 3">未知</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="用户角色" width="100px">
          <template #default="scope">
            <div>
              <span v-if="scope.row.roleId === 1">普通用户</span>
              <span v-if="scope.row.roleId === 2">管理员</span>
            </div>
          </template></el-table-column
        >
        <el-table-column
          label="信誉积分"
          prop="rate"
          :show-overflow-tooltip="true"
        ></el-table-column>
      </el-table>

      <el-pagination
        background
        v-model:current-page="paginationProps.current"
        v-model:page-size="paginationProps.pageSize"
        :page-sizes="[10, 20, 50, 100, 200]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="paginationProps.total"
        @change="onPageChange"
      />
    </el-card>

    <Dialog ref="userDialogRef" :title="title" @onConfirm="saveUser">
      <template #content>
        <el-form :model="dataList.userForm" label-width="100px">
          <el-form-item label="用户昵称">
            <el-input
              v-model="dataList.userForm.username"
              placeholder="请输入用户昵称"
            ></el-input>
          </el-form-item>
          <el-form-item label="用户头像">
            <div style="display: flex; align-items: center">
              <el-image
                id="photo-view"
                :src="filterPhoto(dataList.userForm.headPic)"
                style="width: 100px; height: 70px; margin-right: 10px"
              />
              <el-button
                type="primary"
                icon="UploadFilled"
                plain
                @click="openUpload"
                >上传图片</el-button
              >
              <input
                type="file"
                id="photo-file"
                style="display: none"
                @change="uploadPhoto"
              />
            </div>
          </el-form-item>
          <el-form-item label="用户密码">
            <el-input
              type="password"
              v-model="dataList.userForm.password"
              placeholder="请输入用户昵称"
            ></el-input>
          </el-form-item>
          <el-form-item label="手机号码">
            <el-input
              v-model="dataList.userForm.phone"
              placeholder="请输入手机号码"
            ></el-input>
          </el-form-item>
          <el-form-item label="用户角色">
            <el-select
              v-model="dataList.userForm.roleId"
              placeholder="请选择用户角色"
            >
              <el-option label="普通用户" :value="1" />
              <el-option label="管理员" :value="2" />
            </el-select>
          </el-form-item>
          <el-form-item label="用户性别">
            <el-select
              v-model="dataList.userForm.sex"
              placeholder="请选择用户性别"
            >
              <el-option label="男" :value="1" />
              <el-option label="女" :value="2" />
              <el-option label="未知" :value="3" />
            </el-select>
          </el-form-item>
          <el-form-item label="信誉积分">
            <el-input-number
              :min="0"
              :max="100"
              v-model="dataList.userForm.rate"
              placeholder="请输入信誉积分"
            ></el-input-number>
          </el-form-item>
        </el-form>
      </template>
    </Dialog>
    <Dialog
      ref="confirmDialogRef"
      :width="width"
      :title="title"
      @onConfirm="removeUser"
    >
      <template #content>
        <div
          style="display: flex; justify-content: center; font-size: 16px"
          v-text="confirmDesc"
        ></div>
      </template>
    </Dialog>
  </div>
</template>
<script setup>
import { ref, reactive, onMounted, computed } from "vue";
import { ElMessage } from "element-plus";
import Dialog from "@/components/Dialog/index.vue";
import {
  axiosPostRequest,
  getSessionStorage,
  myEventBus,
} from "@/util/index.js";
const emitter = myEventBus();
emitter.on("refresh", () => {
  getLoginUser();
});
onMounted(() => {
  getLoginUser();
});

const searchFormRef = ref();
const userDialogRef = ref();
const confirmDialogRef = ref();
const title = ref("");
const confirmDesc = ref("");
const width = ref("50%");

const paginationProps = reactive({
  current: 1,
  pageSize: 10,
  total: 0,
});

// 获取当前登录用户
const getLoginUser = async () => {
  const response = await axiosPostRequest("/user/check_login", {
    token: getSessionStorage(),
  });
  if (response.code === 0) {
    dataList.loginUser = response.data;
    getUserList();
  } else {
    ElMessage.error(response.msg);
    router.push("/login");
  }
};

// 重置搜索表单
const resetSearchForm = () => {
  searchFormRef.value.resetFields();
};

const dataList = reactive({
  searchParams: {
    username: "",
    roleId: 0,
  },
  loginUser: {
    id: "",
    roleId: 1,
  },
  userForm: {
    id: "",
    username: "",
    password: "",
    headPic: "common/no_image.jpg",
    phone: "",
    roleId: 1,
    sex: 3,
    rate: 100,
  },
  multipleSelection: [],
  userList: [],
});

const filterPhoto = computed(() => (photo) => {
  return import.meta.env.VITE_SERVER + "/photo/view?filename=" + photo;
});

// 打开上传图片窗口
const openUpload = () => {
  document.getElementById("photo-file").click();
};
// 上传图片操作
const uploadPhoto = async () => {
  let fileObj = document.getElementById("photo-file");
  if (fileObj.value === "") return;
  let config = {
    headers: { "Content-Type": "multipart/form-data" },
  };
  let formData = new FormData();
  formData.append("photo", fileObj.files[0]);
  const response = await axiosPostRequest("/photo/upload", formData, config);
  fileObj.value = "";
  if (response.code === 0) {
    ElMessage.success(response.msg);
    document.getElementById("photo-view").src =
      import.meta.env.VITE_SERVER + "/photo/view?filename=" + response.data;
    dataList.userForm.headPic = response.data;
  } else {
    ElMessage.error(response.msg);
  }
};

// 打开添加用户的模态框
const openAddDialog = () => {
  title.value = "添加用户信息";
  dataList.userForm = {
    id: "",
    username: "",
    password: "",
    headPic: "common/no_image.jpg",
    phone: "",
    roleId: 1,
    sex: 3,
    rate: 100,
  };
  userDialogRef.value.openDialog();
};

// 打开编辑用户的模态框
const openEditDialog = () => {
  if (dataList.multipleSelection.length !== 1) {
    ElMessage.warning("请选择一条数据进行编辑！");
    return;
  }
  title.value = "编辑用户信息";
  dataList.userForm = JSON.parse(JSON.stringify(dataList.multipleSelection[0]));
  userDialogRef.value.openDialog();
};

// 打开删除用户的确认弹框
const openRemoveDialog = () => {
  if (dataList.multipleSelection.length !== 1) {
    ElMessage.warning("请选择一条数据进行删除！");
    return;
  }
  confirmDesc.value = "确定要删除此数据吗？";
  title.value = "确认提示";
  width.value = "30%";
  dataList.userForm = JSON.parse(JSON.stringify(dataList.multipleSelection[0]));
  confirmDialogRef.value.openDialog();
};

// 删除用户信息
const removeUser = async () => {
  const response = await axiosPostRequest("/user/delete", dataList.userForm);
  if (response.code === 0) {
    ElMessage.success(response.msg);
    confirmDialogRef.value.closeDialog();
    getUserList();
  } else {
    ElMessage.error(response.msg);
  }
};

// 保存用户信息
const saveUser = async () => {
  const response = await axiosPostRequest("/user/save", {
    ...dataList.userForm,
    token: getSessionStorage(),
  });
  if (response.code === 0) {
    ElMessage.success(response.msg);
    userDialogRef.value.closeDialog();
    getUserList();
    emitter.emit("refresh");
  } else {
    ElMessage.error(response.msg);
  }
};

// 分页变化时候
const onPageChange = (current, pageSize) => {
  paginationProps.current = current;
  paginationProps.pageSize = pageSize;
  getUserList();
};

// 记录表格选中行
const handleSelectionChange = (val) => {
  dataList.multipleSelection = val;
};

// 获取用户信息
const getUserList = async () => {
  const response = await axiosPostRequest("/user/list", {
    page: paginationProps.current,
    size: paginationProps.pageSize,
    param: {
      id: dataList.loginUser.roleId === 1 ? dataList.loginUser.id : "",
      username: dataList.searchParams.username,
      roleId: dataList.searchParams.roleId,
    },
  });
  if (response.code === 0) {
    dataList.userList = response.data.list;
    paginationProps.total = response.data.total;
  } else {
    ElMessage.error(response.msg);
  }
};
</script>
<style lang="scss" scoped>
.card-list {
  border-radius: 0.375rem;
  .el-table {
    margin-top: 20px;
  }
  .el-pagination {
    margin-top: 20px;
  }
}
</style>
