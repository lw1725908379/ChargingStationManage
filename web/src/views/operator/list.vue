<template>
  <div style="padding: 6px 6px 0 6px">
    <el-card class="card-list" shadow="never">
      <el-form
        ref="searchFormRef"
        :inline="true"
        :model="dataList.searchParams"
      >
        <el-form-item label="运营商名称" prop="name">
          <el-input
            v-model="dataList.searchParams.name"
            placeholder="请输入运营商名称"
            style="width: 200px"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="search" plain @click="getOperatorList"
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
        :data="dataList.operatorList"
        empty-text="暂时没查到数据哟"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column label="序号" prop="id" width="140px"></el-table-column>
        <el-table-column
          label="运营商名称"
          prop="name"
          width="300px"
        ></el-table-column>
        <el-table-column
          label="下属电站数量"
          prop="stationNum"
          width="200px"
        ></el-table-column>
        <el-table-column
          label="运营商描述"
          prop="description"
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
    <!-- saveOperator Dialog -->
    <Dialog ref="operatorDialogRef" :title="title" @onConfirm="saveOperator">
      <template #content>
        <el-form :model="dataList.operatorForm" label-width="100px">
          <el-form-item label="运营商名称">
            <el-input
              v-model="dataList.operatorForm.name"
              placeholder="请输入运营商名称"
            ></el-input>
          </el-form-item>

          <el-form-item label="运营商描述">
            <el-input
              v-model="dataList.operatorForm.description"
              :autosize="{ minRows: 3 }"
              type="textarea"
              placeholder="请输入运营商描述"
            />
          </el-form-item>
        </el-form>
      </template>
    </Dialog>
    <Dialog
      ref="confirmDialogRef"
      :width="width"
      :title="title"
      @onConfirm="removeOperator"
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
const operatorDialogRef = ref();
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
    getOperatorList();
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
  // TODO 将分页查询参数添加到store
  searchParams: {
    name: "",
  },
  loginUser: {
    id: "",
    roleId: 1,
  },
  operatorForm: {
    id: "",
    name: "",
    description: "",
  },
  multipleSelection: [],
  operatorList: [],
});

// 打开添加运营商的模态框
const openAddDialog = () => {
  title.value = "添加运营商信息";
  dataList.operatorForm = {
    id: "",
    name: "",
    description: "",
  };
  operatorDialogRef.value.openDialog();
};

// 打开编辑运营商的模态框
const openEditDialog = () => {
  if (dataList.multipleSelection.length !== 1) {
    ElMessage.warning("请选择一条数据进行编辑！");
    return;
  }
  title.value = "编辑运营商信息";
  dataList.operatorForm = JSON.parse(
    JSON.stringify(dataList.multipleSelection[0])
  );
  operatorDialogRef.value.openDialog();
};

// 打开删除运营商的确认弹框
const openRemoveDialog = () => {
  if (dataList.multipleSelection.length !== 1) {
    ElMessage.warning("请选择一条数据进行删除！");
    return;
  }
  confirmDesc.value = "确定要删除此数据吗？";
  title.value = "确认提示";
  width.value = "30%";
  dataList.operatorForm = JSON.parse(
    JSON.stringify(dataList.multipleSelection[0])
  );
  confirmDialogRef.value.openDialog();
};

// 删除运营商信息
const removeOperator = async () => {
  const response = await axiosPostRequest(
    "/operator/delete",
    dataList.operatorForm
  );
  if (response.code === 0) {
    ElMessage.success(response.msg);
    confirmDialogRef.value.closeDialog();
    getOperatorList();
  } else {
    ElMessage.error(response.msg);
  }
};

// 保存运营商信息
const saveOperator = async () => {
  const response = await axiosPostRequest(
    "/operator/save",
    dataList.operatorForm
  );
  if (response.code === 0) {
    ElMessage.success(response.msg);
    operatorDialogRef.value.closeDialog();
    getOperatorList();
  } else {
    ElMessage.error(response.msg);
  }
};

// 分页变化时候
const onPageChange = (current, pageSize) => {
  paginationProps.current = current;
  paginationProps.pageSize = pageSize;
  getOperatorList();
};

// 记录表格选中行
const handleSelectionChange = (val) => {
  dataList.multipleSelection = val;
};

// 获取运营商信息
const getOperatorList = async () => {
  const response = await axiosPostRequest("/operator/list", {
    page: paginationProps.current,
    size: paginationProps.pageSize,
    param: {
      name: dataList.searchParams.name,
    },
  });
  if (response.code === 0) {
    dataList.operatorList = response.data.list;
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
