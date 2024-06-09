<template>
  <div style="padding: 6px 6px 0 6px">
    <el-card class="card-list" shadow="never">
      <el-form
        ref="searchFormRef"
        :inline="true"
        :model="dataList.searchParams"
      >
        <el-form-item label="订单编号" prop="id">
          <el-input
            v-model="dataList.searchParams.id"
            placeholder="请输入订单编号"
            style="width: 200px"
          ></el-input>
        </el-form-item>
        <el-form-item label="用户昵称" prop="username">
          <el-input
            v-model="dataList.searchParams.username"
            placeholder="请输入用户昵称"
            style="width: 200px"
          ></el-input>
        </el-form-item>
        <el-form-item label="订单状态" prop="state">
          <el-select
            v-model="dataList.searchParams.state"
            placeholder="请选择订单状态"
          >
            <el-option label="全部" :value="0" />
            <el-option label="未支付" :value="1" />
            <el-option label="已支付" :value="2" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="search" plain @click="getOrderList"
            >搜索</el-button
          >
          <el-button type="danger" icon="refresh" plain @click="resetSearchForm"
            >重置</el-button
          >
        </el-form-item>
      </el-form>

      <el-row :gutter="10">
        <el-col :span="1.5">
          <el-button type="primary" icon="Money" plain @click="openPayDialog"
            >支付</el-button
          >
        </el-col>
        <el-col :span="1.5" v-if="dataList.loginUser.roleId === 2">
          <el-button type="success" icon="edit" plain @click="openEditDialog"
            >编辑</el-button
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
        :data="dataList.orderList"
        empty-text="暂时没查到数据哟"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column label="序号" prop="id" width="140px"></el-table-column>
        <el-table-column
          label="充电桩名称"
          prop="chargeName"
          width="200px"
        ></el-table-column>
        <el-table-column label="用户昵称" width="200px">
          <template #default="scope">
            <div>
              {{ scope.row.userDTO.username }}
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="订单总价格"
          prop="totalPrice"
          width="150px"
        ></el-table-column>
        <el-table-column label="订单状态" width="100px">
          <template #default="scope">
            <div>
              <span style="color: red" v-if="scope.row.state === 1"
                >未支付</span
              >
              <span style="color: green" v-if="scope.row.state === 2"
                >已支付</span
              >
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="订单创建时间"
          prop="createTime"
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

    <Dialog ref="orderDialogRef" :title="title" @onConfirm="saveOrder">
      <template #content>
        <el-form :model="dataList.orderForm" label-width="130px">
          <el-form-item label="订单编号">
            <div v-text="dataList.orderForm.id"></div>
          </el-form-item>
          <el-form-item label="订单状态">
            <el-select
              v-model="dataList.orderForm.state"
              placeholder="请选择订单状态"
            >
              <el-option label="未支付" :value="1" />
              <el-option label="已支付" :value="2" />
            </el-select>
          </el-form-item>
        </el-form>
      </template>
    </Dialog>
  </div>
</template>
<script setup>
import { ref, reactive, onMounted } from "vue";
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
const confirmDialogRef = ref();
const orderDialogRef = ref();
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
    getOrderList();
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
    id: "",
    state: 0,
  },
  loginUser: {
    id: "",
    roleId: 1,
  },
  multipleSelection: [],
  orderList: [],
  orderForm: {
    id: "",
    state: 1,
    method: "",
  },
});

// 打开编辑订单的模态框
const openEditDialog = () => {
  if (dataList.multipleSelection.length !== 1) {
    ElMessage.warning("请选择一条数据进行编辑！");
    return;
  }
  let selectedRow = dataList.multipleSelection[0];
  title.value = "编辑订单信息";
  dataList.orderForm = JSON.parse(JSON.stringify(selectedRow));
  orderDialogRef.value.openDialog();
};

// 修改订单信息
const saveOrder = async () => {
  const response = await axiosPostRequest("/order/edit_state", {
    id: dataList.orderForm.id,
    state: dataList.orderForm.state,
  });
  if (response.code === 0) {
    ElMessage.success("保存成功！");
    orderDialogRef.value.closeDialog();
    getOrderList();
  } else {
    ElMessage.error(response.msg);
  }
};

// 确认操作
const onConfirm = async () => {
  if (dataList.orderForm.method === "pay") {
    // 支付订单
    const response = await axiosPostRequest("/order/edit_state", {
      id: dataList.orderForm.id,
      state: 2,
    });
    if (response.code === 0) {
      ElMessage.success("支付成功！");
      getOrderList();
      confirmDialogRef.value.closeDialog();
    } else {
      ElMessage.error(response.msg);
    }
  } else if (dataList.orderForm.method === "delete") {
    // 结束充电
    const response = await axiosPostRequest("/order/delete", {
      id: dataList.orderForm.id,
    });
    if (response.code === 0) {
      ElMessage.success(response.msg);
      getOrderList();
      confirmDialogRef.value.closeDialog();
    } else {
      ElMessage.error(response.msg);
    }
  }
};

// 打开删除订单确认框
const openRemoveDialog = () => {
  if (dataList.multipleSelection.length !== 1) {
    ElMessage.warning("请选择一条数据进行删除！");
    return;
  }
  let selectedRow = dataList.multipleSelection[0];
  confirmDesc.value = "确定要删除此数据吗？";
  title.value = "确认提示";
  width.value = "30%";
  dataList.orderForm = JSON.parse(
    JSON.stringify({ ...selectedRow, method: "delete" })
  );
  confirmDialogRef.value.openDialog();
};

// 支付订单
const openPayDialog = () => {
  if (dataList.multipleSelection.length !== 1) {
    ElMessage.warning("请选择一条数据进行支付！");
    return;
  }
  let selectedRow = dataList.multipleSelection[0];
  if (selectedRow.state !== 1) {
    ElMessage.warning("请选择一条未支付的数据进行支付！");
    return;
  }
  confirmDesc.value =
    "确定要花费" + selectedRow.totalPrice + "元来支付订单吗？";
  title.value = "确认提示";
  width.value = "30%";
  dataList.orderForm = JSON.parse(
    JSON.stringify({ ...selectedRow, method: "pay" })
  );
  confirmDialogRef.value.openDialog();
};

// 分页变化时候
const onPageChange = (current, pageSize) => {
  paginationProps.current = current;
  paginationProps.pageSize = pageSize;
  getOrderList();
};

// 记录表格选中行
const handleSelectionChange = (val) => {
  dataList.multipleSelection = val;
};

// 获取订单信息
const getOrderList = async () => {
  const response = await axiosPostRequest("/order/list", {
    page: paginationProps.current,
    size: paginationProps.pageSize,
    param: {
      username: dataList.searchParams.username,
      id: dataList.searchParams.id,
      userId: dataList.loginUser.roleId === 1 ? dataList.loginUser.id : "",
      state: dataList.searchParams.state,
    },
  });
  if (response.code === 0) {
    dataList.orderList = response.data.list;
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
