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
        <el-form-item>
          <el-button type="primary" icon="search" plain @click="getWarnList"
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
        :data="dataList.warnList"
        empty-text="暂时没查到数据"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column label="序号" prop="id" width="140px"></el-table-column>
        <el-table-column label="用户昵称" width="200px">
          <template #default="scope">
            <div>
              {{ scope.row.userDTO.username }}
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="扣除信誉积分"
          prop="rate"
          width="150px"
        ></el-table-column>
        <el-table-column
          label="告警时间"
          prop="createTime"
          width="200px"
        ></el-table-column>
        <el-table-column
          label="告警内容描述"
          prop="content"
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

    <Dialog ref="warnDialogRef" :title="title" @onConfirm="saveWarn">
      <template #content>
        <el-form :model="dataList.warnForm" label-width="130px">
          <el-form-item label="用户编号">
            <el-input
              v-model="dataList.warnForm.userId"
              type="text"
              placeholder="请输入用户编号"
            />
          </el-form-item>
          <el-form-item label="扣除信誉积分">
            <el-input-number
              :min="0"
              style="width: 50%"
              :max="100"
              v-model="dataList.warnForm.rate"
              placeholder="请输入扣除信誉积分"
            ></el-input-number>
          </el-form-item>
          <el-form-item label="告警内容描述">
            <el-input
              v-model="dataList.warnForm.content"
              :autosize="{ minRows: 3 }"
              type="textarea"
              placeholder="请输入告警内容描述"
            />
          </el-form-item>
        </el-form>
      </template>
    </Dialog>
    <Dialog
      ref="confirmDialogRef"
      :width="width"
      :title="title"
      @onConfirm="removeWarn"
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
const warnDialogRef = ref();
const confirmDialogRef = ref();
const title = ref("");
const confirmDesc = ref("");
const width = ref("50%");

const paginationProps = reactive({
  current: 1,
  pageSize: 10,
  total: 0,
});

// 重置搜索表单
const resetSearchForm = () => {
  searchFormRef.value.resetFields();
};

const dataList = reactive({
  searchParams: {
    username: "",
  },
  loginUser: {
    id: "",
    roleId: 1,
  },
  warnForm: {
    id: "",
    rate: 0,
    userId: "",
    content: "",
  },
  multipleSelection: [],
  warnList: [],
});

// 获取当前登录用户
const getLoginUser = async () => {
  const response = await axiosPostRequest("/user/check_login", {
    token: getSessionStorage(),
  });
  if (response.code === 0) {
    dataList.loginUser = response.data;
    getWarnList();
  } else {
    ElMessage.error(response.msg);
    router.push("/login");
  }
};

// 打开添加告警的模态框
const openAddDialog = () => {
  title.value = "添加告警信息";
  dataList.warnForm = {
    id: "",
    content: "",
  };
  warnDialogRef.value.openDialog();
};

// 打开编辑告警的模态框
const openEditDialog = () => {
  if (dataList.multipleSelection.length !== 1) {
    ElMessage.warning("请选择一条数据进行编辑！");
    return;
  }
  title.value = "编辑告警信息";
  dataList.warnForm = JSON.parse(JSON.stringify(dataList.multipleSelection[0]));
  warnDialogRef.value.openDialog();
};

// 打开删除告警的确认弹框
const openRemoveDialog = () => {
  if (dataList.multipleSelection.length !== 1) {
    ElMessage.warning("请选择一条数据进行删除！");
    return;
  }
  confirmDesc.value = "确定要删除此数据吗？";
  title.value = "确认提示";
  width.value = "30%";
  dataList.warnForm = JSON.parse(JSON.stringify(dataList.multipleSelection[0]));
  confirmDialogRef.value.openDialog();
};

// 删除告警信息
const removeWarn = async () => {
  const response = await axiosPostRequest("/warn/delete", dataList.warnForm);
  if (response.code === 0) {
    ElMessage.success(response.msg);
    confirmDialogRef.value.closeDialog();
    getWarnList();
  } else {
    ElMessage.error(response.msg);
  }
};

// 保存告警信息
const saveWarn = async () => {
  const response = await axiosPostRequest("/warn/save", {
    ...dataList.warnForm,
  });
  if (response.code === 0) {
    ElMessage.success(response.msg);
    warnDialogRef.value.closeDialog();
    getWarnList();
  } else {
    ElMessage.error(response.msg);
  }
};

// 分页变化时候
const onPageChange = (current, pageSize) => {
  paginationProps.current = current;
  paginationProps.pageSize = pageSize;
  getWarnList();
};

// 记录表格选中行
const handleSelectionChange = (val) => {
  dataList.multipleSelection = val;
};

// 获取告警信息
const getWarnList = async () => {
  const response = await axiosPostRequest("/warn/list", {
    page: paginationProps.current,
    size: paginationProps.pageSize,
    param: {
      userId: dataList.loginUser.roleId === 1 ? dataList.loginUser.id : "",
      username: dataList.searchParams.username,
    },
  });
  if (response.code === 0) {
    dataList.warnList = response.data.list;
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
