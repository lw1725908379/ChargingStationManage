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
        <el-form-item label="充电桩名称" prop="chargeId">
          <el-select
            filterable
            v-model="dataList.searchParams.chargeId"
            placeholder="请选择充电桩名称"
          >
            <el-option label="全部" :value="0" />
            <el-option
              v-for="item in dataList.chargeList"
              :label="item.name"
              :value="item.id"
              :key="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="报修状态" prop="state">
          <el-select
            v-model="dataList.searchParams.state"
            placeholder="请选择报修状态"
          >
            <el-option label="全部" :value="0" />
            <el-option label="未受理" :value="1" />
            <el-option label="已受理" :value="2" />
            <el-option label="已撤销" :value="3" />
            <el-option label="已完成" :value="4" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="search" plain @click="getRepairList"
            >搜索</el-button
          >
          <el-button type="danger" icon="refresh" plain @click="resetSearchForm"
            >重置</el-button
          >
        </el-form-item>
      </el-form>

      <el-row :gutter="10">
        <el-col :span="1.5" v-if="dataList.loginUser.roleId === 2">
          <el-button type="success" icon="edit" plain @click="openEditDialog"
            >修改</el-button
          >
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="info"
            icon="CloseBold"
            plain
            @click="openCancelDialog"
            >撤销</el-button
          >
        </el-col>
        <el-col :span="1.5" v-if="dataList.loginUser.roleId === 2">
          <el-button type="danger" icon="delete" plain @click="openRemoveDialog"
            >删除</el-button
          >
        </el-col>
      </el-row>

      <el-table
        border
        @selection-change="handleSelectionChange"
        v-adaptive
        :data="dataList.repairList"
        empty-text="暂时没查到数据哟"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column label="序号" prop="id" width="140px"></el-table-column>
        <el-table-column
          label="充电桩名称"
          prop="chargeName"
          width="200px"
        ></el-table-column>
        <el-table-column
          label="用户昵称"
          prop="username"
          width="150px"
        ></el-table-column>
        <el-table-column label="报修状态" width="150px">
          <template #default="scope">
            <div>
              <span v-if="scope.row.state === 1">未受理</span>
              <span v-if="scope.row.state === 2">已受理</span>
              <span v-if="scope.row.state === 3">已撤销</span>
              <span v-if="scope.row.state === 4">已完成</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="报修时间"
          prop="createTime"
          width="200px"
        ></el-table-column>
        <el-table-column
          prop="description"
          label="报修内容描述"
          :show-overflow-tooltip="true"
        >
        </el-table-column>
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

    <Dialog ref="repairDialogRef" :title="title" @onConfirm="saveRepair">
      <template #content>
        <el-form :model="dataList.repairForm" label-width="100px">
          <el-form-item label="报修编号">
            <div v-text="dataList.repairForm.id"></div>
          </el-form-item>
          <el-form-item label="报修状态">
            <el-select
              v-model="dataList.repairForm.state"
              placeholder="请选择报修状态"
            >
              <el-option label="未受理" :value="1" />
              <el-option label="已受理" :value="2" />
              <el-option label="已撤销" :value="3" />
              <el-option label="已完成" :value="4" />
            </el-select>
          </el-form-item>
        </el-form>
      </template>
    </Dialog>
    <Dialog
      ref="confirmDialogRef"
      :width="width"
      :title="title"
      @onConfirm="onConfirm"
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
const repairDialogRef = ref();
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
    chargeId: "",
    state: 0,
  },
  loginUser: {
    id: "",
    roleId: 1,
  },
  repairForm: {
    id: "",
    description: "",
    chargeId: "",
    method: "",
    state: 1,
  },
  multipleSelection: [],
  chargeList: [],
  repairList: [],
});

// 打开编辑报修的模态框
const openEditDialog = () => {
  if (dataList.multipleSelection.length !== 1) {
    ElMessage.warning("请选择一条数据进行编辑！");
    return;
  }
  title.value = "编辑报修信息";
  dataList.repairForm = JSON.parse(
    JSON.stringify(dataList.multipleSelection[0])
  );
  repairDialogRef.value.openDialog();
};

// 打开撤销报修的确认弹框
const openCancelDialog = () => {
  if (dataList.multipleSelection.length !== 1) {
    ElMessage.warning("请选择一条数据进行撤销！");
    return;
  }
  let selectedRow = dataList.multipleSelection[0];
  if (selectedRow.state !== 1) {
    ElMessage.warning("请选择一条未受理的数据进行撤销！");
    return;
  }
  confirmDesc.value = "确定要撤销此数据吗？";
  title.value = "确认提示";
  width.value = "30%";
  dataList.repairForm = JSON.parse(
    JSON.stringify({ ...selectedRow, method: "cancel" })
  );
  confirmDialogRef.value.openDialog();
};

// 打开删除报修的确认弹框
const openRemoveDialog = () => {
  if (dataList.multipleSelection.length !== 1) {
    ElMessage.warning("请选择一条数据进行删除！");
    return;
  }
  let selectedRow = dataList.multipleSelection[0];
  if (selectedRow.state === 2) {
    ElMessage.warning("此报修还在受理中，禁止删除！");
    return;
  }
  confirmDesc.value = "确定要删除此数据吗？";
  title.value = "确认提示";
  width.value = "30%";
  dataList.repairForm = JSON.parse(
    JSON.stringify({ ...selectedRow, method: "delete" })
  );
  confirmDialogRef.value.openDialog();
};

// 获取当前登录用户
const getLoginUser = async () => {
  const response = await axiosPostRequest("/user/check_login", {
    token: getSessionStorage(),
  });
  if (response.code === 0) {
    dataList.loginUser = response.data;
    getRepairList();
    getAllCharge();
  } else {
    ElMessage.error(response.msg);
    router.push("/login");
  }
};

// 确认操作
const onConfirm = async () => {
  if (dataList.repairForm.method === "cancel") {
    // 撤销报修
    const response = await axiosPostRequest("/repair/save", {
      id: dataList.repairForm.id,
      description: dataList.repairForm.description,
      chargeId: dataList.repairForm.chargeId,
      state: 3,
    });
    if (response.code === 0) {
      ElMessage.success("撤销报修成功！");
      confirmDialogRef.value.closeDialog();
      getRepairList();
    } else {
      ElMessage.error(response.msg);
    }
  } else if (dataList.repairForm.method === "delete") {
    // 删除报修数据
    const response = await axiosPostRequest("/repair/delete", {
      id: dataList.repairForm.id,
    });
    if (response.code === 0) {
      ElMessage.success(response.msg);
      confirmDialogRef.value.closeDialog();
      getRepairList();
    } else {
      ElMessage.error(response.msg);
    }
  }
};

// 保存报修信息
const saveRepair = async () => {
  const response = await axiosPostRequest("/repair/save", dataList.repairForm);
  if (response.code === 0) {
    ElMessage.success(response.msg);
    repairDialogRef.value.closeDialog();
    getRepairList();
  } else {
    ElMessage.error(response.msg);
  }
};

// 分页变化时候
const onPageChange = (current, pageSize) => {
  paginationProps.current = current;
  paginationProps.pageSize = pageSize;
  getRepairList();
};

// 记录表格选中行
const handleSelectionChange = (val) => {
  dataList.multipleSelection = val;
};

// 获取全部充电桩信息
const getAllCharge = async () => {
  const response = await axiosPostRequest("/charge/all");
  if (response.code === 0) {
    dataList.chargeList = response.data;
  } else {
    ElMessage.error(response.msg);
  }
};

// 获取报修信息
const getRepairList = async () => {
  const response = await axiosPostRequest("/repair/list", {
    page: paginationProps.current,
    size: paginationProps.pageSize,
    param: {
      username: dataList.searchParams.username,
      userId: dataList.loginUser.roleId === 1 ? dataList.loginUser.id : "",
      chargeId: dataList.searchParams.chargeId,
      state: dataList.searchParams.state,
    },
  });
  if (response.code === 0) {
    dataList.repairList = response.data.list;
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
