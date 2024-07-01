<template>
  <div style="padding: 6px 6px 0 6px">
    <el-card class="card-list" shadow="never">
      <el-form
        ref="searchFormRef"
        :inline="true"
        :model="dataList.searchParams"
      >
        <el-form-item label="充电桩名称" prop="name">
          <el-input
            v-model="dataList.searchParams.name"
            placeholder="请输入充电桩名称"
            style="width: 200px"
          ></el-input>
        </el-form-item>
        <el-form-item label="所属电站" prop="operatorId">
          <el-select
            filterable
            v-model="dataList.searchParams.stationId"
            placeholder="请选择所属电站"
          >
            <el-option label="全部" :value="0" />
            <el-option
              v-for="item in dataList.stationList"
              :label="item.name"
              :value="item.id"
              :key="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="search" plain @click="getChargeList"
            >搜索</el-button
          >
          <el-button type="danger" icon="refresh" plain @click="resetSearchForm"
            >重置</el-button
          >
        </el-form-item>
      </el-form>

      <el-row :gutter="10">
        <el-col :span="1.5" v-if="dataList.loginUser.roleId === 2">
          <el-button type="primary" icon="plus" plain @click="openAddDialog"
            >新增</el-button
          >
        </el-col>
        <el-col :span="1.5" v-if="dataList.loginUser.roleId === 2">
          <el-button type="success" icon="edit" plain @click="openEditDialog"
            >修改</el-button
          >
        </el-col>
        <el-col :span="1.5" v-if="dataList.loginUser.roleId === 2">
          <el-button type="danger" icon="delete" plain @click="openRemoveDialog"
            >删除</el-button
          >
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            icon="AlarmClock"
            plain
            @click="openAppointDialog"
            >预约</el-button
          >
        </el-col>
        <el-col :span="1.5">
          <el-button type="info" icon="Tools" plain @click="openRepairDialog"
            >报修</el-button
          >
        </el-col>
      </el-row>
      <!-- 充电桩分页列表 -->
      <el-table
        border
        @selection-change="handleSelectionChange"
        v-adaptive
        :data="dataList.chargeList"
        empty-text="暂时没查到数据哟"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column label="序号" prop="id" width="140px"></el-table-column>
        <el-table-column
          label="充电桩名称"
          prop="name"
          width="200px"
        ></el-table-column>
        <el-table-column label="充电桩图片" width="145px">
          <template #default="scope">
            <div>
              <el-image
                :preview-teleported="true"
                :preview-src-list="[filterPhoto(scope.row.photo)]"
                style="width: 120px; height: 80px"
                :src="filterPhoto(scope.row.photo)"
              />
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="收费价格(元/分钟)"
          prop="price"
          width="150px"
        ></el-table-column>
        <el-table-column label="所属电站" width="150px">
          <template #default="scope">
            <div>
              {{ scope.row.stationDTO.name }}
            </div>
          </template>
        </el-table-column>
        <el-table-column label="充电桩状态" width="150px">
          <template #default="scope">
            <div>
              <span style="color: green" v-if="scope.row.state === 1"
                >正常</span
              >
              <span style="color: red" v-if="scope.row.state === 2"
                >报修中</span
              >
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="规格描述"
          :show-overflow-tooltip="true"
          prop="description"
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
    <!-- 管理员新增充电桩信息 -->
    <Dialog ref="chargeDialogRef" :title="title" @onConfirm="saveCharge">
      <template #content>
        <el-form :model="dataList.chargeForm" label-width="130px">
          <el-form-item label="充电桩名称">
            <el-input
              v-model="dataList.chargeForm.name"
              placeholder="请输入充电桩名称"
            ></el-input>
          </el-form-item>
          <el-form-item label="充电桩图片">
            <div style="display: flex; align-items: center">
              <el-image
                id="photo-view"
                :src="filterPhoto(dataList.chargeForm.photo)"
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
          <el-form-item label="收费价格(元/分钟)">
            <el-input-number
              :min="0.0"
              :max="99999999.99"
              :precision="2"
              v-model="dataList.chargeForm.price"
              placeholder="请输入收费价格(元/分钟)"
            ></el-input-number>
          </el-form-item>
          <el-form-item label="所属电站">
            <el-select
              v-model="dataList.chargeForm.stationId"
              placeholder="请选择所属电站"
              filterable
            >
              <el-option
                v-for="item in dataList.stationList"
                :label="item.name"
                :value="item.id"
                :key="item.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="充电桩状态">
            <el-select
              v-model="dataList.chargeForm.state"
              placeholder="请选择充电桩状态"
            >
              <el-option label="正常" :value="1" />
              <el-option label="报修中" :value="2" />
            </el-select>
          </el-form-item>
          <el-form-item label="规格描述">
            <el-input
              v-model="dataList.chargeForm.description"
              :autosize="{ minRows: 3 }"
              type="textarea"
              placeholder="请输入规格描述"
            />
          </el-form-item>
        </el-form>
      </template>
    </Dialog>
    <Dialog
      ref="confirmDialogRef"
      :width="width"
      :title="title"
      @onConfirm="removeCharge"
    >
      <template #content>
        <!-- 模板引用 创建ref对象 通过 `confirmDesc.value = "确定要删除此数据"` 更新响应式变量 `confirmDesc` 的值，被绑定的视图会自动显示最新的文本内容。 -->
        <div
          style="display: flex; justify-content: center; font-size: 16px"
          v-text="confirmDesc"
        ></div>
      </template>
    </Dialog>
    <!-- 普通人员新增预约信息 -->
    <Dialog ref="appointDialogRef" :title="title" @onConfirm="saveAppoint">
      <template #content>
        <el-form
          v-loading="loading"
          :model="dataList.appointForm"
          label-width="130px"
        >
          <el-form-item label="充电桩编号">
            <div v-text="dataList.appointForm.id"></div>
          </el-form-item>
          <el-form-item label="充电桩名称">
            <div v-text="dataList.appointForm.name"></div>
          </el-form-item>
          <el-form-item label="预约日期">
            <el-date-picker
              v-model="dataList.appointForm.day"
              @change="(e) => getAppointDate()"
              :disabled-date="disabledDate"
              type="date"
              placeholder="请选择日期"
            />
          </el-form-item>
          <el-form-item label="预约时间段">
            <el-select
              v-model="dataList.appointForm.scheduleId"
              placeholder="请选择时间段"
              style="width: 220px"
            >
              <el-option
                v-for="item in dataList.scheduleOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
              />
            </el-select>
          </el-form-item>
        </el-form>
      </template>
    </Dialog>

    <Dialog ref="repairDialogRef" :title="title" @onConfirm="saveRepair">
      <template #content>
        <el-form :model="dataList.repairForm" label-width="130px">
          <el-form-item label="充电桩编号">
            <div v-text="dataList.repairForm.id"></div>
          </el-form-item>
          <el-form-item label="充电桩名称">
            <div v-text="dataList.repairForm.name"></div>
          </el-form-item>
          <el-form-item label="报修内容描述">
            <el-input
              v-model="dataList.repairForm.description"
              :autosize="{ minRows: 4 }"
              type="textarea"
              placeholder="请输入报修内容描述"
            />
          </el-form-item>
        </el-form>
      </template>
    </Dialog>
  </div>
</template>
<script setup>
import { ref, reactive, onMounted, computed, toRefs } from "vue";
import { ElMessage } from "element-plus";
import Dialog from "@/components/Dialog/index.vue";
import {
  axiosPostRequest,
  getSessionStorage,
  myEventBus,
} from "@/util/index.js";
import { format } from "date-fns";
const emitter = myEventBus();
emitter.on("refresh", () => {
  getLoginUser();
});
onMounted(() => {
  getLoginUser();
});

const searchFormRef = ref();
const chargeDialogRef = ref();
const confirmDialogRef = ref();
const appointDialogRef = ref();
const repairDialogRef = ref();
const title = ref("");
const confirmDesc = ref("");
const width = ref("50%");
const loading = ref(false);

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
    name: "",
    stationId: 0,
  },
  scheduleOptions: [
    {
      label: "00:00-02:00",
      value: "00-02",
      disabled: false,
    },
    {
      label: "02:00-04:00",
      value: "02-04",
      disabled: false,
    },
    {
      label: "04:00-06:00",
      value: "04-06",
      disabled: false,
    },
    {
      label: "06:00-08:00",
      value: "06-08",
      disabled: false,
    },
    {
      label: "08:00-10:00",
      value: "08-10",
      disabled: false,
    },
    {
      label: "10:00-12:00",
      value: "10-12",
      disabled: false,
    },
    {
      label: "12:00-14:00",
      value: "12-14",
      disabled: false,
    },
    {
      label: "14:00-16:00",
      value: "14-16",
      disabled: false,
    },
    {
      label: "16:00-18:00",
      value: "16-18",
      disabled: false,
    },
    {
      label: "18:00-20:00",
      value: "18-20",
      disabled: false,
    },
    {
      label: "20:00-22:00",
      value: "20-22",
      disabled: false,
    },
    {
      label: "22:00-24:00",
      value: "22-24",
      disabled: false,
    },
  ],
  chargeForm: {
    id: "",
    name: "",
    state: 1,
    price: 0.0,
    stationId: "",
    description: "",
    photo: "common/no_image.jpg",
  },
  multipleSelection: [],
  chargeList: [],
  stationList: [],
  repairForm: {
    id: "",
    name: "",
    description: "",
  },
  loginUser: {
    id: "",
    roleId: 1,
    username: "",
  },
  appointForm: {
    time: [],
    id: "",
    name: "",
    day: "",
    price: 0.0,
    scheduleId: "",
  },
});

// 禁用日期
const disabledDate = (date) => {
  // 8.64e7 ms === 24h
  if (date.getTime() < Date.now() - 8.64e7) {
    return true;
  }
  return false;
};

// 获取当前登录用户
const getLoginUser = async () => {
  const response = await axiosPostRequest("/user/check_login", {
    token: getSessionStorage(),
  });
  if (response.code === 0) {
    dataList.loginUser = response.data;
    getChargeList();
    getAllStation();
  } else {
    ElMessage.error(response.msg);
    router.push("/login");
  }
};

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
    dataList.chargeForm.photo = response.data;
  } else {
    ElMessage.error(response.msg);
  }
};

// 打开添加充电桩的模态框
const openAddDialog = () => {
  title.value = "添加充电桩信息";
  dataList.chargeForm = {
    id: "",
    name: "",
    state: 1,
    price: 0.0,
    stationId: "",
    description: "",
    photo: "common/no_image.jpg",
  };
  chargeDialogRef.value.openDialog();
};

// 打开预约充电桩的模态框
const openAppointDialog = () => {
  if (dataList.multipleSelection.length !== 1) {
    ElMessage.warning("请选择一条数据进行预约！");
    return;
  }
  if (dataList.multipleSelection[0].state === 2) {
    ElMessage.warning("此充电桩正在维修中，请换一个！");
    return;
  }
  title.value = "预约充电桩";
  dataList.appointForm = JSON.parse(
    JSON.stringify({
      ...dataList.multipleSelection[0],
      day: new Date(),
      scheduleId: "",
    })
  );
  getAppointDate();
  appointDialogRef.value.openDialog();
};

// 打开编辑充电桩的模态框
const openEditDialog = () => {
  if (dataList.multipleSelection.length !== 1) {
    ElMessage.warning("请选择一条数据进行编辑！");
    return;
  }
  title.value = "编辑充电桩信息";
  dataList.chargeForm = JSON.parse(
    JSON.stringify(dataList.multipleSelection[0])
  );
  chargeDialogRef.value.openDialog();
};

// 打开删除充电桩的确认弹框
const openRemoveDialog = () => {
  if (dataList.multipleSelection.length !== 1) {
    ElMessage.warning("请选择一条数据进行删除！");
    return;
  }
  confirmDesc.value = "确定要删除此数据吗？";
  title.value = "确认提示";
  width.value = "30%";
  dataList.chargeForm = JSON.parse(
    JSON.stringify(dataList.multipleSelection[0])
  );
  confirmDialogRef.value.openDialog();
};

// 打开报修的模态框
const openRepairDialog = () => {
  if (dataList.multipleSelection.length !== 1) {
    ElMessage.warning("请选择一条数据进行报修！");
    return;
  }
  let selectedRow = dataList.multipleSelection[0];
  if (selectedRow.state == 2) {
    ElMessage.warning("充电桩已经在报修中，请勿重复操作！");
    return;
  }
  title.value = "充电桩报修";
  dataList.repairForm = JSON.parse(
    JSON.stringify({ ...selectedRow, description: "" })
  );
  repairDialogRef.value.openDialog();
};

// 删除充电桩信息
const removeCharge = async () => {
  const response = await axiosPostRequest(
    "/charge/delete",
    dataList.chargeForm
  );
  if (response.code === 0) {
    ElMessage.success(response.msg);
    confirmDialogRef.value.closeDialog();
    getChargeList();
  } else {
    ElMessage.error(response.msg);
  }
};


// 在子组件Dailog中通过 emit('onConfirm') 触发事件后，父组件监听 @onConfirm 事件并执行保存充电桩信息的函数
const saveCharge = async () => {
  const response = await axiosPostRequest("/charge/save", dataList.chargeForm);
  if (response.code === 0) {
    ElMessage.success(response.msg);
    chargeDialogRef.value.closeDialog();
    getChargeList();
  } else {
    ElMessage.error(response.msg);
  }
};

// 获取已预约的日期
const getAppointDate = async () => {
  loading.value = true;
  dataList.appointForm.scheduleId = "";
  let newScheduleOptions = dataList.scheduleOptions.map((item) => {
    item.disabled = false;
    return item;
  });
  const response = await axiosPostRequest("/appoint/appointed", {
    chargeId: dataList.appointForm.id,
  });
  if (response.code === 0) {
    // 通过后端查询 appoint 表，获取了预约当天该充电桩已被预约的时间段。
    let timeList = response.data
      .filter(
        (item) => item.day === format(dataList.appointForm.day, "yyyy-MM-dd")
      )
      .map((item) => item.time);
    // 将已被预约时间段设置为不可预约状态
    newScheduleOptions = newScheduleOptions.map((item) => {
      timeList.forEach((e) => {
        if (e === item.label) {
          item.disabled = true;
        }
      });
      let timeRange = item.value.split("-");
      // 将当前时间之前的时间段设定为不可预约状态。
      if (
        Number(timeRange[0]) <= Number(format(new Date(), "HH")) &&
        format(dataList.appointForm.day, "yyyy-MM-dd") ===
          format(new Date(), "yyyy-MM-dd")
      ) {
        item.disabled = true;
      }
      return item;
    });
    dataList.scheduleOptions = newScheduleOptions;
  } else {
    ElMessage.error(response.msg);
  }
  loading.value = false;
};

// 保存报修信息
const saveRepair = async () => {
  const response = await axiosPostRequest("/repair/save", {
    chargeId: dataList.repairForm.id,
    chargeName: dataList.repairForm.name,
    description: dataList.repairForm.description,
    userId: dataList.loginUser.id,
    username: dataList.loginUser.username,
  });
  if (response.code === 0) {
    ElMessage.success("报修成功，您可以在报修列表中查看自己的报修记录！");
    repairDialogRef.value.closeDialog();
  } else {
    ElMessage.error(response.msg);
  }
};

// 保存预约信息
const saveAppoint = async () => {
  let data = JSON.parse(JSON.stringify(dataList.appointForm));
  if (data.scheduleId === "") {
    ElMessage.warning("预约时间段不能为空！");
    return;
  }
  data.day = format(data.day, "yyyy-MM-dd");
  let timeRange = data.scheduleId.split("-");
  data.time = timeRange[0] + ":00" + "-" + timeRange[1] + ":00";
  const response = await axiosPostRequest("/appoint/save", {
    day: data.day,
    time: data.time,
    chargeName: data.name,
    userId: dataList.loginUser.id,
    chargePrice: data.price,
    chargeId: data.id,
  });
  if (response.code === 0) {
    ElMessage.success("预约成功，您可以在预约列表查看您的预约信息！");
    appointDialogRef.value.closeDialog();
  } else {
    ElMessage.error(response.msg);
  }
};

// 分页变化时候
const onPageChange = (current, pageSize) => {
  paginationProps.current = current;
  paginationProps.pageSize = pageSize;
  getChargeList();
};

// 记录表格选中行
const handleSelectionChange = (val) => {
  dataList.multipleSelection = val;
};

// 获取全部电站信息
const getAllStation = async () => {
  const response = await axiosPostRequest("/station/all");
  if (response.code === 0) {
    dataList.stationList = response.data;
  } else {
    ElMessage.error(response.msg);
  }
};

// 获取充电桩信息
const getChargeList = async () => {
  const response = await axiosPostRequest("/charge/list", {
    page: paginationProps.current,
    size: paginationProps.pageSize,
    param: {
      name: dataList.searchParams.name,
      stationId: dataList.searchParams.stationId,
    },
  });
  if (response.code === 0) {
    dataList.chargeList = response.data.list;
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
