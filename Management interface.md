# ChargingStationManage Interface documentation


**简介**:ChargingStationManage Interface documentation


**HOST**:localhost:8085


**联系人**:


**Version**:1.0


**接口路径**:/v2/api-docs?group=Management interface


[TOC]






# 充电桩管理


## 获取全部充电桩信息


**接口地址**:`/charge/all`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|保存成功！|ResponseDTO«List«ChargeDTO»»|
|201|Created||
|400|参数错误||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|服务器内部错误||
|-3000|电站信息添加失败，请联系管理员！||
|-3001|该电站不存在！||
|-3002|电站信息编辑失败，请联系管理员！||
|-3003|电站信息删除失败，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|array|ChargeDTO|
|&emsp;&emsp;description|充电桩规格描述|string||
|&emsp;&emsp;id|充电桩ID|string||
|&emsp;&emsp;name|充电桩名称|string||
|&emsp;&emsp;photo|充电桩照片|string||
|&emsp;&emsp;price|充电价格|number||
|&emsp;&emsp;state|充电桩状态|integer(int32)||
|&emsp;&emsp;stationDTO|电站信息|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量|integer||
|&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量|integer||
|&emsp;&emsp;&emsp;&emsp;id|电站唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;location|电站位置|string||
|&emsp;&emsp;&emsp;&emsp;name|电站名称|string||
|&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量|integer||
|&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;photo|电站照片|string||
|&emsp;&emsp;stationId|所属电站ID|string||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"description": "",
			"id": "",
			"name": "",
			"photo": "",
			"price": 0,
			"state": 0,
			"stationDTO": {
				"availableChargeNum": 0,
				"chargeNum": 0,
				"id": "",
				"location": "",
				"name": "",
				"operatorDTO": {
					"description": "",
					"id": "",
					"name": "",
					"stationNum": 0
				},
				"operatorId": "",
				"photo": ""
			},
			"stationId": ""
		}
	],
	"msg": ""
}
```


## 删除充电桩信息


**接口地址**:`/charge/delete`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "description": "",
  "id": "",
  "name": "",
  "photo": "",
  "price": 0,
  "state": 0,
  "stationDTO": {
    "availableChargeNum": 0,
    "chargeNum": 0,
    "id": "",
    "location": "",
    "name": "",
    "operatorDTO": {
      "description": "",
      "id": "",
      "name": "",
      "stationNum": 0
    },
    "operatorId": "",
    "photo": ""
  },
  "stationId": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|chargeDTO|充电桩传输对象|body|true|ChargeDTO|ChargeDTO|
|&emsp;&emsp;description|充电桩规格描述||true|string||
|&emsp;&emsp;id|充电桩ID||false|string||
|&emsp;&emsp;name|充电桩名称||true|string||
|&emsp;&emsp;photo|充电桩照片||false|string||
|&emsp;&emsp;price|充电价格||true|number||
|&emsp;&emsp;state|充电桩状态||false|integer(int32)||
|&emsp;&emsp;stationDTO|电站信息||false|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;id|电站唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;location|电站位置||false|string||
|&emsp;&emsp;&emsp;&emsp;name|电站名称||false|string||
|&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息||false|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;photo|电站照片||false|string||
|&emsp;&emsp;stationId|所属电站ID||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|保存成功！|ResponseDTO«boolean»|
|201|Created||
|400|参数错误||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|服务器内部错误||
|-3000|电站信息添加失败，请联系管理员！||
|-3001|该电站不存在！||
|-3002|电站信息编辑失败，请联系管理员！||
|-3003|电站信息删除失败，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


## 分页获取充电桩数据


**接口地址**:`/charge/list`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "list": [
    {
      "description": "",
      "id": "",
      "name": "",
      "photo": "",
      "price": 0,
      "state": 0,
      "stationDTO": {
        "availableChargeNum": 0,
        "chargeNum": 0,
        "id": "",
        "location": "",
        "name": "",
        "operatorDTO": {
          "description": "",
          "id": "",
          "name": "",
          "stationNum": 0
        },
        "operatorId": "",
        "photo": ""
      },
      "stationId": ""
    }
  ],
  "page": 0,
  "param": {
    "description": "",
    "id": "",
    "name": "",
    "photo": "",
    "price": 0,
    "state": 0,
    "stationDTO": {
      "availableChargeNum": 0,
      "chargeNum": 0,
      "id": "",
      "location": "",
      "name": "",
      "operatorDTO": {
        "description": "",
        "id": "",
        "name": "",
        "stationNum": 0
      },
      "operatorId": "",
      "photo": ""
    },
    "stationId": ""
  },
  "size": 0,
  "total": 0,
  "totalPage": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pageDTO|分页传输对象|body|true|PageDTO«ChargeDTO»|PageDTO«ChargeDTO»|
|&emsp;&emsp;list|返回数据||false|array|ChargeDTO|
|&emsp;&emsp;&emsp;&emsp;description|充电桩规格描述||true|string||
|&emsp;&emsp;&emsp;&emsp;id|充电桩ID||false|string||
|&emsp;&emsp;&emsp;&emsp;name|充电桩名称||true|string||
|&emsp;&emsp;&emsp;&emsp;photo|充电桩照片||false|string||
|&emsp;&emsp;&emsp;&emsp;price|充电价格||true|number||
|&emsp;&emsp;&emsp;&emsp;state|充电桩状态||false|integer||
|&emsp;&emsp;&emsp;&emsp;stationDTO|电站信息||false|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|电站唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;location|电站位置||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|电站名称||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息||false|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|电站照片||false|string||
|&emsp;&emsp;&emsp;&emsp;stationId|所属电站ID||true|string||
|&emsp;&emsp;page|当前页码||false|integer(int32)||
|&emsp;&emsp;param|请求参数||false|ChargeDTO|ChargeDTO|
|&emsp;&emsp;&emsp;&emsp;description|充电桩规格描述||true|string||
|&emsp;&emsp;&emsp;&emsp;id|充电桩ID||false|string||
|&emsp;&emsp;&emsp;&emsp;name|充电桩名称||true|string||
|&emsp;&emsp;&emsp;&emsp;photo|充电桩照片||false|string||
|&emsp;&emsp;&emsp;&emsp;price|充电价格||true|number||
|&emsp;&emsp;&emsp;&emsp;state|充电桩状态||false|integer||
|&emsp;&emsp;&emsp;&emsp;stationDTO|电站信息||false|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|电站唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;location|电站位置||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|电站名称||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息||false|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|电站照片||false|string||
|&emsp;&emsp;&emsp;&emsp;stationId|所属电站ID||true|string||
|&emsp;&emsp;size|每页条数||false|integer(int32)||
|&emsp;&emsp;total|总条数||false|integer(int64)||
|&emsp;&emsp;totalPage|总页数||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|保存成功！|ResponseDTO«PageDTO«ChargeDTO»»|
|201|Created||
|400|参数错误||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|服务器内部错误||
|-3000|电站信息添加失败，请联系管理员！||
|-3001|该电站不存在！||
|-3002|电站信息编辑失败，请联系管理员！||
|-3003|电站信息删除失败，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|PageDTO«ChargeDTO»|PageDTO«ChargeDTO»|
|&emsp;&emsp;list|返回数据|array|ChargeDTO|
|&emsp;&emsp;&emsp;&emsp;description|充电桩规格描述|string||
|&emsp;&emsp;&emsp;&emsp;id|充电桩ID|string||
|&emsp;&emsp;&emsp;&emsp;name|充电桩名称|string||
|&emsp;&emsp;&emsp;&emsp;photo|充电桩照片|string||
|&emsp;&emsp;&emsp;&emsp;price|充电价格|number||
|&emsp;&emsp;&emsp;&emsp;state|充电桩状态|integer||
|&emsp;&emsp;&emsp;&emsp;stationDTO|电站信息|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|电站唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;location|电站位置|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|电站名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|电站照片|string||
|&emsp;&emsp;&emsp;&emsp;stationId|所属电站ID|string||
|&emsp;&emsp;page|当前页码|integer(int32)||
|&emsp;&emsp;param|请求参数|ChargeDTO|ChargeDTO|
|&emsp;&emsp;&emsp;&emsp;description|充电桩规格描述|string||
|&emsp;&emsp;&emsp;&emsp;id|充电桩ID|string||
|&emsp;&emsp;&emsp;&emsp;name|充电桩名称|string||
|&emsp;&emsp;&emsp;&emsp;photo|充电桩照片|string||
|&emsp;&emsp;&emsp;&emsp;price|充电价格|number||
|&emsp;&emsp;&emsp;&emsp;state|充电桩状态|integer||
|&emsp;&emsp;&emsp;&emsp;stationDTO|电站信息|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|电站唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;location|电站位置|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|电站名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|电站照片|string||
|&emsp;&emsp;&emsp;&emsp;stationId|所属电站ID|string||
|&emsp;&emsp;size|每页条数|integer(int32)||
|&emsp;&emsp;total|总条数|integer(int64)||
|&emsp;&emsp;totalPage|总页数|integer(int32)||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"list": [
			{
				"description": "",
				"id": "",
				"name": "",
				"photo": "",
				"price": 0,
				"state": 0,
				"stationDTO": {
					"availableChargeNum": 0,
					"chargeNum": 0,
					"id": "",
					"location": "",
					"name": "",
					"operatorDTO": {
						"description": "",
						"id": "",
						"name": "",
						"stationNum": 0
					},
					"operatorId": "",
					"photo": ""
				},
				"stationId": ""
			}
		],
		"page": 0,
		"param": {
			"description": "",
			"id": "",
			"name": "",
			"photo": "",
			"price": 0,
			"state": 0,
			"stationDTO": {
				"availableChargeNum": 0,
				"chargeNum": 0,
				"id": "",
				"location": "",
				"name": "",
				"operatorDTO": {
					"description": "",
					"id": "",
					"name": "",
					"stationNum": 0
				},
				"operatorId": "",
				"photo": ""
			},
			"stationId": ""
		},
		"size": 0,
		"total": 0,
		"totalPage": 0
	},
	"msg": ""
}
```


## 保存充电桩信息


**接口地址**:`/charge/save`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "description": "",
  "id": "",
  "name": "",
  "photo": "",
  "price": 0,
  "state": 0,
  "stationDTO": {
    "availableChargeNum": 0,
    "chargeNum": 0,
    "id": "",
    "location": "",
    "name": "",
    "operatorDTO": {
      "description": "",
      "id": "",
      "name": "",
      "stationNum": 0
    },
    "operatorId": "",
    "photo": ""
  },
  "stationId": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|chargeDTO|充电桩传输对象|body|true|ChargeDTO|ChargeDTO|
|&emsp;&emsp;description|充电桩规格描述||true|string||
|&emsp;&emsp;id|充电桩ID||false|string||
|&emsp;&emsp;name|充电桩名称||true|string||
|&emsp;&emsp;photo|充电桩照片||false|string||
|&emsp;&emsp;price|充电价格||true|number||
|&emsp;&emsp;state|充电桩状态||false|integer(int32)||
|&emsp;&emsp;stationDTO|电站信息||false|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;id|电站唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;location|电站位置||false|string||
|&emsp;&emsp;&emsp;&emsp;name|电站名称||false|string||
|&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息||false|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;photo|电站照片||false|string||
|&emsp;&emsp;stationId|所属电站ID||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|保存成功！|ResponseDTO«boolean»|
|201|Created||
|400|参数错误||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|服务器内部错误||
|-3000|电站信息添加失败，请联系管理员！||
|-3001|该电站不存在！||
|-3002|电站信息编辑失败，请联系管理员！||
|-3003|电站信息删除失败，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


# 告警管理


## 获取全部告警数据


**接口地址**:`/warn/all`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "content": "",
  "createTime": "",
  "id": "",
  "rate": 0,
  "userDTO": {
    "headPic": "",
    "id": "",
    "password": "",
    "phone": "",
    "rate": 0,
    "roleId": 0,
    "sex": 0,
    "token": "",
    "username": ""
  },
  "userId": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|warnDTO|告警传输对象|body|true|WarnDTO|WarnDTO|
|&emsp;&emsp;content|告警内容描述||false|string||
|&emsp;&emsp;createTime|创建时间||false|string(date-time)||
|&emsp;&emsp;id|告警唯一标识||false|string||
|&emsp;&emsp;rate|用户评分||false|integer(int32)||
|&emsp;&emsp;userDTO|用户传输对象||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;userId|用户唯一标识||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«List«WarnDTO»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|array|WarnDTO|
|&emsp;&emsp;content|告警内容描述|string||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;id|告警唯一标识|string||
|&emsp;&emsp;rate|用户评分|integer(int32)||
|&emsp;&emsp;userDTO|用户传输对象|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称|string||
|&emsp;&emsp;userId|用户唯一标识|string||
|&emsp;&emsp;username|用户名|string||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"content": "",
			"createTime": "",
			"id": "",
			"rate": 0,
			"userDTO": {
				"headPic": "",
				"id": "",
				"password": "",
				"phone": "",
				"rate": 0,
				"roleId": 0,
				"sex": 0,
				"token": "",
				"username": ""
			},
			"userId": "",
			"username": ""
		}
	],
	"msg": ""
}
```


## 删除告警信息


**接口地址**:`/warn/delete`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "content": "",
  "createTime": "",
  "id": "",
  "rate": 0,
  "userDTO": {
    "headPic": "",
    "id": "",
    "password": "",
    "phone": "",
    "rate": 0,
    "roleId": 0,
    "sex": 0,
    "token": "",
    "username": ""
  },
  "userId": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|warnDTO|告警传输对象|body|true|WarnDTO|WarnDTO|
|&emsp;&emsp;content|告警内容描述||false|string||
|&emsp;&emsp;createTime|创建时间||false|string(date-time)||
|&emsp;&emsp;id|告警唯一标识||false|string||
|&emsp;&emsp;rate|用户评分||false|integer(int32)||
|&emsp;&emsp;userDTO|用户传输对象||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;userId|用户唯一标识||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|-9002|告警信息删除失败，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


## 获取告警列表


**接口地址**:`/warn/list`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "list": [
    {
      "content": "",
      "createTime": "",
      "id": "",
      "rate": 0,
      "userDTO": {
        "headPic": "",
        "id": "",
        "password": "",
        "phone": "",
        "rate": 0,
        "roleId": 0,
        "sex": 0,
        "token": "",
        "username": ""
      },
      "userId": "",
      "username": ""
    }
  ],
  "page": 0,
  "param": {
    "content": "",
    "createTime": "",
    "id": "",
    "rate": 0,
    "userDTO": {
      "headPic": "",
      "id": "",
      "password": "",
      "phone": "",
      "rate": 0,
      "roleId": 0,
      "sex": 0,
      "token": "",
      "username": ""
    },
    "userId": "",
    "username": ""
  },
  "size": 0,
  "total": 0,
  "totalPage": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pageDTO|分页传输对象|body|true|PageDTO«WarnDTO»|PageDTO«WarnDTO»|
|&emsp;&emsp;list|返回数据||false|array|WarnDTO|
|&emsp;&emsp;&emsp;&emsp;content|告警内容描述||false|string||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间||false|string||
|&emsp;&emsp;&emsp;&emsp;id|告警唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;userDTO|用户传输对象||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;&emsp;&emsp;userId|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户名||false|string||
|&emsp;&emsp;page|当前页码||false|integer(int32)||
|&emsp;&emsp;param|请求参数||false|WarnDTO|WarnDTO|
|&emsp;&emsp;&emsp;&emsp;content|告警内容描述||false|string||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间||false|string||
|&emsp;&emsp;&emsp;&emsp;id|告警唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;userDTO|用户传输对象||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;&emsp;&emsp;userId|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户名||false|string||
|&emsp;&emsp;size|每页条数||false|integer(int32)||
|&emsp;&emsp;total|总条数||false|integer(int64)||
|&emsp;&emsp;totalPage|总页数||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«PageDTO«WarnDTO»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|PageDTO«WarnDTO»|PageDTO«WarnDTO»|
|&emsp;&emsp;list|返回数据|array|WarnDTO|
|&emsp;&emsp;&emsp;&emsp;content|告警内容描述|string||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;id|告警唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分|integer||
|&emsp;&emsp;&emsp;&emsp;userDTO|用户传输对象|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;headPic|用户头像|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone|手机号码|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;rate|用户评分|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sex|用户性别|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;token|用户令牌|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;username|用户昵称|string||
|&emsp;&emsp;&emsp;&emsp;userId|用户唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;username|用户名|string||
|&emsp;&emsp;page|当前页码|integer(int32)||
|&emsp;&emsp;param|请求参数|WarnDTO|WarnDTO|
|&emsp;&emsp;&emsp;&emsp;content|告警内容描述|string||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;id|告警唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分|integer||
|&emsp;&emsp;&emsp;&emsp;userDTO|用户传输对象|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;headPic|用户头像|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone|手机号码|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;rate|用户评分|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sex|用户性别|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;token|用户令牌|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;username|用户昵称|string||
|&emsp;&emsp;&emsp;&emsp;userId|用户唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;username|用户名|string||
|&emsp;&emsp;size|每页条数|integer(int32)||
|&emsp;&emsp;total|总条数|integer(int64)||
|&emsp;&emsp;totalPage|总页数|integer(int32)||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"list": [
			{
				"content": "",
				"createTime": "",
				"id": "",
				"rate": 0,
				"userDTO": {
					"headPic": "",
					"id": "",
					"password": "",
					"phone": "",
					"rate": 0,
					"roleId": 0,
					"sex": 0,
					"token": "",
					"username": ""
				},
				"userId": "",
				"username": ""
			}
		],
		"page": 0,
		"param": {
			"content": "",
			"createTime": "",
			"id": "",
			"rate": 0,
			"userDTO": {
				"headPic": "",
				"id": "",
				"password": "",
				"phone": "",
				"rate": 0,
				"roleId": 0,
				"sex": 0,
				"token": "",
				"username": ""
			},
			"userId": "",
			"username": ""
		},
		"size": 0,
		"total": 0,
		"totalPage": 0
	},
	"msg": ""
}
```


## 保存告警信息


**接口地址**:`/warn/save`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "content": "",
  "createTime": "",
  "id": "",
  "rate": 0,
  "userDTO": {
    "headPic": "",
    "id": "",
    "password": "",
    "phone": "",
    "rate": 0,
    "roleId": 0,
    "sex": 0,
    "token": "",
    "username": ""
  },
  "userId": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|warnDTO|告警传输对象|body|true|WarnDTO|WarnDTO|
|&emsp;&emsp;content|告警内容描述||false|string||
|&emsp;&emsp;createTime|创建时间||false|string(date-time)||
|&emsp;&emsp;id|告警唯一标识||false|string||
|&emsp;&emsp;rate|用户评分||false|integer(int32)||
|&emsp;&emsp;userDTO|用户传输对象||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;userId|用户唯一标识||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|-9000|告警信息创建失败，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


# 图片管理


## 上传图片


**接口地址**:`/photo/upload`


**请求方式**:`POST`


**请求数据类型**:`multipart/form-data`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|photo|photo|body|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|图片上传成功！|ResponseDTO«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|文件保存异常||
|-16|上传的图片不能为空！||
|-7|图片格式不正确！||
|-8|上传的图片不能超过2MB！||
|-9|上传的图片格式不正确！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|string||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


## 查看图片


**接口地址**:`/photo/view`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|filename|图片文件名|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|图片上传成功！||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|文件保存异常||
|-16|上传的图片不能为空！||
|-7|图片格式不正确！||
|-8|上传的图片不能超过2MB！||
|-9|上传的图片格式不正确！||


**响应参数**:


暂无


**响应示例**:
```javascript

```


## 查看图片


**接口地址**:`/photo/view`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|filename|图片文件名|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|图片上传成功！||
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|文件保存异常||
|-16|上传的图片不能为空！||
|-7|图片格式不正确！||
|-8|上传的图片不能超过2MB！||
|-9|上传的图片格式不正确！||


**响应参数**:


暂无


**响应示例**:
```javascript

```


## 查看图片


**接口地址**:`/photo/view`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|filename|图片文件名|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|图片上传成功！||
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|文件保存异常||
|-16|上传的图片不能为空！||
|-7|图片格式不正确！||
|-8|上传的图片不能超过2MB！||
|-9|上传的图片格式不正确！||


**响应参数**:


暂无


**响应示例**:
```javascript

```


## 查看图片


**接口地址**:`/photo/view`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|filename|图片文件名|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|图片上传成功！||
|204|No Content||
|401|Unauthorized||
|403|Forbidden||
|500|文件保存异常||
|-16|上传的图片不能为空！||
|-7|图片格式不正确！||
|-8|上传的图片不能超过2MB！||
|-9|上传的图片格式不正确！||


**响应参数**:


暂无


**响应示例**:
```javascript

```


## 查看图片


**接口地址**:`/photo/view`


**请求方式**:`PATCH`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|filename|图片文件名|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|图片上传成功！||
|204|No Content||
|401|Unauthorized||
|403|Forbidden||
|500|文件保存异常||
|-16|上传的图片不能为空！||
|-7|图片格式不正确！||
|-8|上传的图片不能超过2MB！||
|-9|上传的图片格式不正确！||


**响应参数**:


暂无


**响应示例**:
```javascript

```


## 查看图片


**接口地址**:`/photo/view`


**请求方式**:`OPTIONS`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|filename|图片文件名|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|图片上传成功！||
|204|No Content||
|401|Unauthorized||
|403|Forbidden||
|500|文件保存异常||
|-16|上传的图片不能为空！||
|-7|图片格式不正确！||
|-8|上传的图片不能超过2MB！||
|-9|上传的图片格式不正确！||


**响应参数**:


暂无


**响应示例**:
```javascript

```


## 查看图片


**接口地址**:`/photo/view`


**请求方式**:`HEAD`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|filename|图片文件名|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|图片上传成功！||
|204|No Content||
|401|Unauthorized||
|403|Forbidden||
|500|文件保存异常||
|-16|上传的图片不能为空！||
|-7|图片格式不正确！||
|-8|上传的图片不能超过2MB！||
|-9|上传的图片格式不正确！||


**响应参数**:


暂无


**响应示例**:
```javascript

```


# 报修管理


## 删除报修信息


**接口地址**:`/repair/delete`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "chargeId": "",
  "chargeName": "",
  "createTime": "",
  "description": "",
  "id": "",
  "state": 0,
  "userId": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|repairDTO|报修传输对象|body|true|RepairDTO|RepairDTO|
|&emsp;&emsp;chargeId|充电桩ID||false|string||
|&emsp;&emsp;chargeName|充电桩名称||false|string||
|&emsp;&emsp;createTime|报修时间||false|string(date-time)||
|&emsp;&emsp;description|报修内容描述||false|string||
|&emsp;&emsp;id|报修ID||false|string||
|&emsp;&emsp;state|报修状态：1-未受理，2-已受理，3-已撤销，4-已完成||false|integer(int32)||
|&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;username|用户昵称||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|-7000|报修信息创建失败，请联系管理员！||
|-7001|报修信息编辑失败，请联系管理员！||
|-7002|报修信息删除失败，请联系管理员！||
|-7003|此充电桩已经在报修中，请勿重复操作！||
|-7004|此充电桩已有人报修，请勿重复操作！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


## 获取报修列表


**接口地址**:`/repair/list`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "list": [
    {
      "chargeId": "",
      "chargeName": "",
      "createTime": "",
      "description": "",
      "id": "",
      "state": 0,
      "userId": "",
      "username": ""
    }
  ],
  "page": 0,
  "param": {
    "chargeId": "",
    "chargeName": "",
    "createTime": "",
    "description": "",
    "id": "",
    "state": 0,
    "userId": "",
    "username": ""
  },
  "size": 0,
  "total": 0,
  "totalPage": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pageDTO|分页传输对象|body|true|PageDTO«RepairDTO»|PageDTO«RepairDTO»|
|&emsp;&emsp;list|返回数据||false|array|RepairDTO|
|&emsp;&emsp;&emsp;&emsp;chargeId|充电桩ID||false|string||
|&emsp;&emsp;&emsp;&emsp;chargeName|充电桩名称||false|string||
|&emsp;&emsp;&emsp;&emsp;createTime|报修时间||false|string||
|&emsp;&emsp;&emsp;&emsp;description|报修内容描述||false|string||
|&emsp;&emsp;&emsp;&emsp;id|报修ID||false|string||
|&emsp;&emsp;&emsp;&emsp;state|报修状态：1-未受理，2-已受理，3-已撤销，4-已完成||false|integer||
|&emsp;&emsp;&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;page|当前页码||false|integer(int32)||
|&emsp;&emsp;param|请求参数||false|RepairDTO|RepairDTO|
|&emsp;&emsp;&emsp;&emsp;chargeId|充电桩ID||false|string||
|&emsp;&emsp;&emsp;&emsp;chargeName|充电桩名称||false|string||
|&emsp;&emsp;&emsp;&emsp;createTime|报修时间||false|string||
|&emsp;&emsp;&emsp;&emsp;description|报修内容描述||false|string||
|&emsp;&emsp;&emsp;&emsp;id|报修ID||false|string||
|&emsp;&emsp;&emsp;&emsp;state|报修状态：1-未受理，2-已受理，3-已撤销，4-已完成||false|integer||
|&emsp;&emsp;&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;size|每页条数||false|integer(int32)||
|&emsp;&emsp;total|总条数||false|integer(int64)||
|&emsp;&emsp;totalPage|总页数||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«PageDTO«RepairDTO»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|-7000|报修信息创建失败，请联系管理员！||
|-7001|报修信息编辑失败，请联系管理员！||
|-7002|报修信息删除失败，请联系管理员！||
|-7003|此充电桩已经在报修中，请勿重复操作！||
|-7004|此充电桩已有人报修，请勿重复操作！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|PageDTO«RepairDTO»|PageDTO«RepairDTO»|
|&emsp;&emsp;list|返回数据|array|RepairDTO|
|&emsp;&emsp;&emsp;&emsp;chargeId|充电桩ID|string||
|&emsp;&emsp;&emsp;&emsp;chargeName|充电桩名称|string||
|&emsp;&emsp;&emsp;&emsp;createTime|报修时间|string||
|&emsp;&emsp;&emsp;&emsp;description|报修内容描述|string||
|&emsp;&emsp;&emsp;&emsp;id|报修ID|string||
|&emsp;&emsp;&emsp;&emsp;state|报修状态：1-未受理，2-已受理，3-已撤销，4-已完成|integer||
|&emsp;&emsp;&emsp;&emsp;userId|用户ID|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称|string||
|&emsp;&emsp;page|当前页码|integer(int32)||
|&emsp;&emsp;param|请求参数|RepairDTO|RepairDTO|
|&emsp;&emsp;&emsp;&emsp;chargeId|充电桩ID|string||
|&emsp;&emsp;&emsp;&emsp;chargeName|充电桩名称|string||
|&emsp;&emsp;&emsp;&emsp;createTime|报修时间|string||
|&emsp;&emsp;&emsp;&emsp;description|报修内容描述|string||
|&emsp;&emsp;&emsp;&emsp;id|报修ID|string||
|&emsp;&emsp;&emsp;&emsp;state|报修状态：1-未受理，2-已受理，3-已撤销，4-已完成|integer||
|&emsp;&emsp;&emsp;&emsp;userId|用户ID|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称|string||
|&emsp;&emsp;size|每页条数|integer(int32)||
|&emsp;&emsp;total|总条数|integer(int64)||
|&emsp;&emsp;totalPage|总页数|integer(int32)||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"list": [
			{
				"chargeId": "",
				"chargeName": "",
				"createTime": "",
				"description": "",
				"id": "",
				"state": 0,
				"userId": "",
				"username": ""
			}
		],
		"page": 0,
		"param": {
			"chargeId": "",
			"chargeName": "",
			"createTime": "",
			"description": "",
			"id": "",
			"state": 0,
			"userId": "",
			"username": ""
		},
		"size": 0,
		"total": 0,
		"totalPage": 0
	},
	"msg": ""
}
```


## 保存报修信息


**接口地址**:`/repair/save`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "chargeId": "",
  "chargeName": "",
  "createTime": "",
  "description": "",
  "id": "",
  "state": 0,
  "userId": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|repairDTO|报修传输对象|body|true|RepairDTO|RepairDTO|
|&emsp;&emsp;chargeId|充电桩ID||false|string||
|&emsp;&emsp;chargeName|充电桩名称||false|string||
|&emsp;&emsp;createTime|报修时间||false|string(date-time)||
|&emsp;&emsp;description|报修内容描述||false|string||
|&emsp;&emsp;id|报修ID||false|string||
|&emsp;&emsp;state|报修状态：1-未受理，2-已受理，3-已撤销，4-已完成||false|integer(int32)||
|&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;username|用户昵称||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|-7000|报修信息创建失败，请联系管理员！||
|-7001|报修信息编辑失败，请联系管理员！||
|-7002|报修信息删除失败，请联系管理员！||
|-7003|此充电桩已经在报修中，请勿重复操作！||
|-7004|此充电桩已有人报修，请勿重复操作！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


# 用户管理


## 检查用户登录状态


**接口地址**:`/user/check_login`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "headPic": "",
  "id": "",
  "password": "",
  "phone": "",
  "rate": 0,
  "roleId": 0,
  "sex": 0,
  "token": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userDTO|用户传输对象|body|true|UserDTO|UserDTO|
|&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;rate|用户评分||false|integer(int32)||
|&emsp;&emsp;roleId|用户角色ID||false|integer(int32)||
|&emsp;&emsp;sex|用户性别||false|integer(int32)||
|&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;username|用户昵称||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«UserDTO»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|UserDTO|UserDTO|
|&emsp;&emsp;headPic|用户头像|string||
|&emsp;&emsp;id|用户唯一标识|string||
|&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;phone|手机号码|string||
|&emsp;&emsp;rate|用户评分|integer(int32)||
|&emsp;&emsp;roleId|用户角色ID|integer(int32)||
|&emsp;&emsp;sex|用户性别|integer(int32)||
|&emsp;&emsp;token|用户令牌|string||
|&emsp;&emsp;username|用户昵称|string||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"headPic": "",
		"id": "",
		"password": "",
		"phone": "",
		"rate": 0,
		"roleId": 0,
		"sex": 0,
		"token": "",
		"username": ""
	},
	"msg": ""
}
```


## 删除用户信息


**接口地址**:`/user/delete`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "headPic": "",
  "id": "",
  "password": "",
  "phone": "",
  "rate": 0,
  "roleId": 0,
  "sex": 0,
  "token": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userDTO|用户传输对象|body|true|UserDTO|UserDTO|
|&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;rate|用户评分||false|integer(int32)||
|&emsp;&emsp;roleId|用户角色ID||false|integer(int32)||
|&emsp;&emsp;sex|用户性别||false|integer(int32)||
|&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;username|用户昵称||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|-1001|该用户不存在！||
|-1003|用户信息删除失败，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


## 获取用户列表


**接口地址**:`/user/list`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "list": [
    {
      "headPic": "",
      "id": "",
      "password": "",
      "phone": "",
      "rate": 0,
      "roleId": 0,
      "sex": 0,
      "token": "",
      "username": ""
    }
  ],
  "page": 0,
  "param": {
    "headPic": "",
    "id": "",
    "password": "",
    "phone": "",
    "rate": 0,
    "roleId": 0,
    "sex": 0,
    "token": "",
    "username": ""
  },
  "size": 0,
  "total": 0,
  "totalPage": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pageDTO|分页传输对象|body|true|PageDTO«UserDTO»|PageDTO«UserDTO»|
|&emsp;&emsp;list|返回数据||false|array|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;page|当前页码||false|integer(int32)||
|&emsp;&emsp;param|请求参数||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;size|每页条数||false|integer(int32)||
|&emsp;&emsp;total|总条数||false|integer(int64)||
|&emsp;&emsp;totalPage|总页数||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«PageDTO«UserDTO»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|PageDTO«UserDTO»|PageDTO«UserDTO»|
|&emsp;&emsp;list|返回数据|array|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称|string||
|&emsp;&emsp;page|当前页码|integer(int32)||
|&emsp;&emsp;param|请求参数|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称|string||
|&emsp;&emsp;size|每页条数|integer(int32)||
|&emsp;&emsp;total|总条数|integer(int64)||
|&emsp;&emsp;totalPage|总页数|integer(int32)||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"list": [
			{
				"headPic": "",
				"id": "",
				"password": "",
				"phone": "",
				"rate": 0,
				"roleId": 0,
				"sex": 0,
				"token": "",
				"username": ""
			}
		],
		"page": 0,
		"param": {
			"headPic": "",
			"id": "",
			"password": "",
			"phone": "",
			"rate": 0,
			"roleId": 0,
			"sex": 0,
			"token": "",
			"username": ""
		},
		"size": 0,
		"total": 0,
		"totalPage": 0
	},
	"msg": ""
}
```


## 用户登录


**接口地址**:`/user/login`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "headPic": "",
  "id": "",
  "password": "",
  "phone": "",
  "rate": 0,
  "roleId": 0,
  "sex": 0,
  "token": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userDTO|用户传输对象|body|true|UserDTO|UserDTO|
|&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;rate|用户评分||false|integer(int32)||
|&emsp;&emsp;roleId|用户角色ID||false|integer(int32)||
|&emsp;&emsp;sex|用户性别||false|integer(int32)||
|&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;username|用户昵称||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«UserDTO»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|-1007|用户昵称或密码错误！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|UserDTO|UserDTO|
|&emsp;&emsp;headPic|用户头像|string||
|&emsp;&emsp;id|用户唯一标识|string||
|&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;phone|手机号码|string||
|&emsp;&emsp;rate|用户评分|integer(int32)||
|&emsp;&emsp;roleId|用户角色ID|integer(int32)||
|&emsp;&emsp;sex|用户性别|integer(int32)||
|&emsp;&emsp;token|用户令牌|string||
|&emsp;&emsp;username|用户昵称|string||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"headPic": "",
		"id": "",
		"password": "",
		"phone": "",
		"rate": 0,
		"roleId": 0,
		"sex": 0,
		"token": "",
		"username": ""
	},
	"msg": ""
}
```


## 用户退出登录


**接口地址**:`/user/logout`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "headPic": "",
  "id": "",
  "password": "",
  "phone": "",
  "rate": 0,
  "roleId": 0,
  "sex": 0,
  "token": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userDTO|用户传输对象|body|true|UserDTO|UserDTO|
|&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;rate|用户评分||false|integer(int32)||
|&emsp;&emsp;roleId|用户角色ID||false|integer(int32)||
|&emsp;&emsp;sex|用户性别||false|integer(int32)||
|&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;username|用户昵称||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


## 获取用户积分明细


**接口地址**:`/user/rate`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "headPic": "",
  "id": "",
  "password": "",
  "phone": "",
  "rate": 0,
  "roleId": 0,
  "sex": 0,
  "token": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userDTO|用户传输对象|body|true|UserDTO|UserDTO|
|&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;rate|用户评分||false|integer(int32)||
|&emsp;&emsp;roleId|用户角色ID||false|integer(int32)||
|&emsp;&emsp;sex|用户性别||false|integer(int32)||
|&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;username|用户昵称||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«List«RateDTO»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|array|RateDTO|
|&emsp;&emsp;content|信誉积分变动描述|string||
|&emsp;&emsp;createTime|信誉积分变动时间|string(date-time)||
|&emsp;&emsp;id|信誉积分ID|string||
|&emsp;&emsp;nowScore|变动后的信誉积分|integer(int32)||
|&emsp;&emsp;score|信誉积分变化分数|integer(int32)||
|&emsp;&emsp;type|信誉积分变化类型：1-增加，2-减少|integer(int32)||
|&emsp;&emsp;userId|用户ID|string||
|&emsp;&emsp;warnId|告警ID|string||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"content": "",
			"createTime": "",
			"id": "",
			"nowScore": 0,
			"score": 0,
			"type": 0,
			"userId": "",
			"warnId": ""
		}
	],
	"msg": ""
}
```


## 注册用户


**接口地址**:`/user/register`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "headPic": "",
  "id": "",
  "password": "",
  "phone": "",
  "rate": 0,
  "roleId": 0,
  "sex": 0,
  "token": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userDTO|用户传输对象|body|true|UserDTO|UserDTO|
|&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;rate|用户评分||false|integer(int32)||
|&emsp;&emsp;roleId|用户角色ID||false|integer(int32)||
|&emsp;&emsp;sex|用户性别||false|integer(int32)||
|&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;username|用户昵称||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|-1004|用户手机号码已经注册，请换一个！||
|-1005|用户昵称不能为空！||
|-1006|用户密码不能为空！||
|-1008|确认密码不能为空！||
|-1009|确认密码不一致！||
|-1010|注册用户失败，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


## 保存用户信息


**接口地址**:`/user/save`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "headPic": "",
  "id": "",
  "password": "",
  "phone": "",
  "rate": 0,
  "roleId": 0,
  "sex": 0,
  "token": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userDTO|用户传输对象|body|true|UserDTO|UserDTO|
|&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;rate|用户评分||false|integer(int32)||
|&emsp;&emsp;roleId|用户角色ID||false|integer(int32)||
|&emsp;&emsp;sex|用户性别||false|integer(int32)||
|&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;username|用户昵称||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|-1000|用户信息添加失败，请联系管理员！||
|-1012|用户昵称重复，请换一个！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


# 电站管理


## 获取全部电站信息


**接口地址**:`/station/all`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«List«StationDTO»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|-3000|电站信息添加失败，请联系管理员！||
|-3001|该电站不存在！||
|-3002|电站信息编辑失败，请联系管理员！||
|-3003|电站信息删除失败，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|array|StationDTO|
|&emsp;&emsp;availableChargeNum|可用充电桩数量|integer(int32)||
|&emsp;&emsp;chargeNum|充电桩数量|integer(int32)||
|&emsp;&emsp;id|电站唯一标识|string||
|&emsp;&emsp;location|电站位置|string||
|&emsp;&emsp;name|电站名称|string||
|&emsp;&emsp;operatorDTO|运营商信息|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;description|运营商描述|string||
|&emsp;&emsp;&emsp;&emsp;id|运营商ID|string||
|&emsp;&emsp;&emsp;&emsp;name|运营商名称|string||
|&emsp;&emsp;&emsp;&emsp;stationNum|电站数量|integer||
|&emsp;&emsp;operatorId|运营商唯一标识|string||
|&emsp;&emsp;photo|电站照片|string||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"availableChargeNum": 0,
			"chargeNum": 0,
			"id": "",
			"location": "",
			"name": "",
			"operatorDTO": {
				"description": "",
				"id": "",
				"name": "",
				"stationNum": 0
			},
			"operatorId": "",
			"photo": ""
		}
	],
	"msg": ""
}
```


## 删除电站信息


**接口地址**:`/station/delete`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "availableChargeNum": 0,
  "chargeNum": 0,
  "id": "",
  "location": "",
  "name": "",
  "operatorDTO": {
    "description": "",
    "id": "",
    "name": "",
    "stationNum": 0
  },
  "operatorId": "",
  "photo": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|stationDTO|电站传输对象|body|true|StationDTO|StationDTO|
|&emsp;&emsp;availableChargeNum|可用充电桩数量||false|integer(int32)||
|&emsp;&emsp;chargeNum|充电桩数量||false|integer(int32)||
|&emsp;&emsp;id|电站唯一标识||false|string||
|&emsp;&emsp;location|电站位置||false|string||
|&emsp;&emsp;name|电站名称||false|string||
|&emsp;&emsp;operatorDTO|运营商信息||false|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;&emsp;&emsp;stationNum|电站数量||false|integer||
|&emsp;&emsp;operatorId|运营商唯一标识||false|string||
|&emsp;&emsp;photo|电站照片||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|-3000|电站信息添加失败，请联系管理员！||
|-3001|该电站不存在！||
|-3002|电站信息编辑失败，请联系管理员！||
|-3003|电站信息删除失败，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


## 获取电站列表


**接口地址**:`/station/list`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "list": [
    {
      "availableChargeNum": 0,
      "chargeNum": 0,
      "id": "",
      "location": "",
      "name": "",
      "operatorDTO": {
        "description": "",
        "id": "",
        "name": "",
        "stationNum": 0
      },
      "operatorId": "",
      "photo": ""
    }
  ],
  "page": 0,
  "param": {
    "availableChargeNum": 0,
    "chargeNum": 0,
    "id": "",
    "location": "",
    "name": "",
    "operatorDTO": {
      "description": "",
      "id": "",
      "name": "",
      "stationNum": 0
    },
    "operatorId": "",
    "photo": ""
  },
  "size": 0,
  "total": 0,
  "totalPage": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pageDTO|分页传输对象|body|true|PageDTO«StationDTO»|PageDTO«StationDTO»|
|&emsp;&emsp;list|返回数据||false|array|StationDTO|
|&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;id|电站唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;location|电站位置||false|string||
|&emsp;&emsp;&emsp;&emsp;name|电站名称||false|string||
|&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息||false|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;photo|电站照片||false|string||
|&emsp;&emsp;page|当前页码||false|integer(int32)||
|&emsp;&emsp;param|请求参数||false|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;id|电站唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;location|电站位置||false|string||
|&emsp;&emsp;&emsp;&emsp;name|电站名称||false|string||
|&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息||false|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;photo|电站照片||false|string||
|&emsp;&emsp;size|每页条数||false|integer(int32)||
|&emsp;&emsp;total|总条数||false|integer(int64)||
|&emsp;&emsp;totalPage|总页数||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«PageDTO«StationDTO»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|-3000|电站信息添加失败，请联系管理员！||
|-3001|该电站不存在！||
|-3002|电站信息编辑失败，请联系管理员！||
|-3003|电站信息删除失败，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|PageDTO«StationDTO»|PageDTO«StationDTO»|
|&emsp;&emsp;list|返回数据|array|StationDTO|
|&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量|integer||
|&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量|integer||
|&emsp;&emsp;&emsp;&emsp;id|电站唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;location|电站位置|string||
|&emsp;&emsp;&emsp;&emsp;name|电站名称|string||
|&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量|integer||
|&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;photo|电站照片|string||
|&emsp;&emsp;page|当前页码|integer(int32)||
|&emsp;&emsp;param|请求参数|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量|integer||
|&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量|integer||
|&emsp;&emsp;&emsp;&emsp;id|电站唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;location|电站位置|string||
|&emsp;&emsp;&emsp;&emsp;name|电站名称|string||
|&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量|integer||
|&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;photo|电站照片|string||
|&emsp;&emsp;size|每页条数|integer(int32)||
|&emsp;&emsp;total|总条数|integer(int64)||
|&emsp;&emsp;totalPage|总页数|integer(int32)||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"list": [
			{
				"availableChargeNum": 0,
				"chargeNum": 0,
				"id": "",
				"location": "",
				"name": "",
				"operatorDTO": {
					"description": "",
					"id": "",
					"name": "",
					"stationNum": 0
				},
				"operatorId": "",
				"photo": ""
			}
		],
		"page": 0,
		"param": {
			"availableChargeNum": 0,
			"chargeNum": 0,
			"id": "",
			"location": "",
			"name": "",
			"operatorDTO": {
				"description": "",
				"id": "",
				"name": "",
				"stationNum": 0
			},
			"operatorId": "",
			"photo": ""
		},
		"size": 0,
		"total": 0,
		"totalPage": 0
	},
	"msg": ""
}
```


## 保存电站信息


**接口地址**:`/station/save`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "availableChargeNum": 0,
  "chargeNum": 0,
  "id": "",
  "location": "",
  "name": "",
  "operatorDTO": {
    "description": "",
    "id": "",
    "name": "",
    "stationNum": 0
  },
  "operatorId": "",
  "photo": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|stationDTO|电站传输对象|body|true|StationDTO|StationDTO|
|&emsp;&emsp;availableChargeNum|可用充电桩数量||false|integer(int32)||
|&emsp;&emsp;chargeNum|充电桩数量||false|integer(int32)||
|&emsp;&emsp;id|电站唯一标识||false|string||
|&emsp;&emsp;location|电站位置||false|string||
|&emsp;&emsp;name|电站名称||false|string||
|&emsp;&emsp;operatorDTO|运营商信息||false|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;&emsp;&emsp;stationNum|电站数量||false|integer||
|&emsp;&emsp;operatorId|运营商唯一标识||false|string||
|&emsp;&emsp;photo|电站照片||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|0|成功||
|200|OK|ResponseDTO«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|-3000|电站信息添加失败，请联系管理员！||
|-3001|该电站不存在！||
|-3002|电站信息编辑失败，请联系管理员！||
|-3003|电站信息删除失败，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


# 留言管理


## 获取全部留言数据


**接口地址**:`/comment/all`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "content": "",
  "createTime": "",
  "id": "",
  "replyContent": "",
  "userDTO": {
    "headPic": "",
    "id": "",
    "password": "",
    "phone": "",
    "rate": 0,
    "roleId": 0,
    "sex": 0,
    "token": "",
    "username": ""
  },
  "userId": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|commentDTO|留言传输对象|body|true|CommentDTO|CommentDTO|
|&emsp;&emsp;content|留言内容||true|string||
|&emsp;&emsp;createTime|留言创建时间||false|string(date-time)||
|&emsp;&emsp;id|留言ID||false|string||
|&emsp;&emsp;replyContent|管理员回复内容||false|string||
|&emsp;&emsp;userDTO|用户信息||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResponseDTO«List«CommentDTO»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|array|CommentDTO|
|&emsp;&emsp;content|留言内容|string||
|&emsp;&emsp;createTime|留言创建时间|string(date-time)||
|&emsp;&emsp;id|留言ID|string||
|&emsp;&emsp;replyContent|管理员回复内容|string||
|&emsp;&emsp;userDTO|用户信息|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称|string||
|&emsp;&emsp;userId|用户ID|string||
|&emsp;&emsp;username|用户名|string||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"content": "",
			"createTime": "",
			"id": "",
			"replyContent": "",
			"userDTO": {
				"headPic": "",
				"id": "",
				"password": "",
				"phone": "",
				"rate": 0,
				"roleId": 0,
				"sex": 0,
				"token": "",
				"username": ""
			},
			"userId": "",
			"username": ""
		}
	],
	"msg": ""
}
```


## 删除留言信息


**接口地址**:`/comment/delete`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "content": "",
  "createTime": "",
  "id": "",
  "replyContent": "",
  "userDTO": {
    "headPic": "",
    "id": "",
    "password": "",
    "phone": "",
    "rate": 0,
    "roleId": 0,
    "sex": 0,
    "token": "",
    "username": ""
  },
  "userId": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|commentDTO|留言传输对象|body|true|CommentDTO|CommentDTO|
|&emsp;&emsp;content|留言内容||true|string||
|&emsp;&emsp;createTime|留言创建时间||false|string(date-time)||
|&emsp;&emsp;id|留言ID||false|string||
|&emsp;&emsp;replyContent|管理员回复内容||false|string||
|&emsp;&emsp;userDTO|用户信息||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResponseDTO«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


## 分页获取留言数据


**接口地址**:`/comment/list`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "list": [
    {
      "content": "",
      "createTime": "",
      "id": "",
      "replyContent": "",
      "userDTO": {
        "headPic": "",
        "id": "",
        "password": "",
        "phone": "",
        "rate": 0,
        "roleId": 0,
        "sex": 0,
        "token": "",
        "username": ""
      },
      "userId": "",
      "username": ""
    }
  ],
  "page": 0,
  "param": {
    "content": "",
    "createTime": "",
    "id": "",
    "replyContent": "",
    "userDTO": {
      "headPic": "",
      "id": "",
      "password": "",
      "phone": "",
      "rate": 0,
      "roleId": 0,
      "sex": 0,
      "token": "",
      "username": ""
    },
    "userId": "",
    "username": ""
  },
  "size": 0,
  "total": 0,
  "totalPage": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pageDTO|分页传输对象|body|true|PageDTO«CommentDTO»|PageDTO«CommentDTO»|
|&emsp;&emsp;list|返回数据||false|array|CommentDTO|
|&emsp;&emsp;&emsp;&emsp;content|留言内容||true|string||
|&emsp;&emsp;&emsp;&emsp;createTime|留言创建时间||false|string||
|&emsp;&emsp;&emsp;&emsp;id|留言ID||false|string||
|&emsp;&emsp;&emsp;&emsp;replyContent|管理员回复内容||false|string||
|&emsp;&emsp;&emsp;&emsp;userDTO|用户信息||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户名||false|string||
|&emsp;&emsp;page|当前页码||false|integer(int32)||
|&emsp;&emsp;param|请求参数||false|CommentDTO|CommentDTO|
|&emsp;&emsp;&emsp;&emsp;content|留言内容||true|string||
|&emsp;&emsp;&emsp;&emsp;createTime|留言创建时间||false|string||
|&emsp;&emsp;&emsp;&emsp;id|留言ID||false|string||
|&emsp;&emsp;&emsp;&emsp;replyContent|管理员回复内容||false|string||
|&emsp;&emsp;&emsp;&emsp;userDTO|用户信息||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户名||false|string||
|&emsp;&emsp;size|每页条数||false|integer(int32)||
|&emsp;&emsp;total|总条数||false|integer(int64)||
|&emsp;&emsp;totalPage|总页数||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResponseDTO«PageDTO«CommentDTO»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|PageDTO«CommentDTO»|PageDTO«CommentDTO»|
|&emsp;&emsp;list|返回数据|array|CommentDTO|
|&emsp;&emsp;&emsp;&emsp;content|留言内容|string||
|&emsp;&emsp;&emsp;&emsp;createTime|留言创建时间|string||
|&emsp;&emsp;&emsp;&emsp;id|留言ID|string||
|&emsp;&emsp;&emsp;&emsp;replyContent|管理员回复内容|string||
|&emsp;&emsp;&emsp;&emsp;userDTO|用户信息|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;headPic|用户头像|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone|手机号码|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;rate|用户评分|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sex|用户性别|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;token|用户令牌|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;username|用户昵称|string||
|&emsp;&emsp;&emsp;&emsp;userId|用户ID|string||
|&emsp;&emsp;&emsp;&emsp;username|用户名|string||
|&emsp;&emsp;page|当前页码|integer(int32)||
|&emsp;&emsp;param|请求参数|CommentDTO|CommentDTO|
|&emsp;&emsp;&emsp;&emsp;content|留言内容|string||
|&emsp;&emsp;&emsp;&emsp;createTime|留言创建时间|string||
|&emsp;&emsp;&emsp;&emsp;id|留言ID|string||
|&emsp;&emsp;&emsp;&emsp;replyContent|管理员回复内容|string||
|&emsp;&emsp;&emsp;&emsp;userDTO|用户信息|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;headPic|用户头像|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone|手机号码|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;rate|用户评分|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sex|用户性别|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;token|用户令牌|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;username|用户昵称|string||
|&emsp;&emsp;&emsp;&emsp;userId|用户ID|string||
|&emsp;&emsp;&emsp;&emsp;username|用户名|string||
|&emsp;&emsp;size|每页条数|integer(int32)||
|&emsp;&emsp;total|总条数|integer(int64)||
|&emsp;&emsp;totalPage|总页数|integer(int32)||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"list": [
			{
				"content": "",
				"createTime": "",
				"id": "",
				"replyContent": "",
				"userDTO": {
					"headPic": "",
					"id": "",
					"password": "",
					"phone": "",
					"rate": 0,
					"roleId": 0,
					"sex": 0,
					"token": "",
					"username": ""
				},
				"userId": "",
				"username": ""
			}
		],
		"page": 0,
		"param": {
			"content": "",
			"createTime": "",
			"id": "",
			"replyContent": "",
			"userDTO": {
				"headPic": "",
				"id": "",
				"password": "",
				"phone": "",
				"rate": 0,
				"roleId": 0,
				"sex": 0,
				"token": "",
				"username": ""
			},
			"userId": "",
			"username": ""
		},
		"size": 0,
		"total": 0,
		"totalPage": 0
	},
	"msg": ""
}
```


## 保存留言信息


**接口地址**:`/comment/save`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "content": "",
  "createTime": "",
  "id": "",
  "replyContent": "",
  "userDTO": {
    "headPic": "",
    "id": "",
    "password": "",
    "phone": "",
    "rate": 0,
    "roleId": 0,
    "sex": 0,
    "token": "",
    "username": ""
  },
  "userId": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|commentDTO|留言传输对象|body|true|CommentDTO|CommentDTO|
|&emsp;&emsp;content|留言内容||true|string||
|&emsp;&emsp;createTime|留言创建时间||false|string(date-time)||
|&emsp;&emsp;id|留言ID||false|string||
|&emsp;&emsp;replyContent|管理员回复内容||false|string||
|&emsp;&emsp;userDTO|用户信息||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResponseDTO«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


# 订单管理


## 获取全部订单数据


**接口地址**:`/order/all`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "chargeId": "",
  "chargeName": "",
  "createTime": "",
  "id": "",
  "state": 0,
  "totalPrice": 0,
  "userDTO": {
    "headPic": "",
    "id": "",
    "password": "",
    "phone": "",
    "rate": 0,
    "roleId": 0,
    "sex": 0,
    "token": "",
    "username": ""
  },
  "userId": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|orderDTO|订单传输对象|body|true|OrderDTO|OrderDTO|
|&emsp;&emsp;chargeId|充电桩ID||false|string||
|&emsp;&emsp;chargeName|充电桩名称||false|string||
|&emsp;&emsp;createTime|订单创建时间||false|string(date-time)||
|&emsp;&emsp;id|订单ID||false|string||
|&emsp;&emsp;state|订单状态||false|integer(int32)||
|&emsp;&emsp;totalPrice|订单总价||false|number||
|&emsp;&emsp;userDTO|用户信息||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|保存成功！|ResponseDTO«List«OrderDTO»»|
|201|Created||
|400|参数错误||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|服务器内部错误||
|-6000|订单创建失败，请联系管理员！||
|-6001|订单信息编辑失败，请联系管理员！||
|-6002|订单信息删除失败，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|array|OrderDTO|
|&emsp;&emsp;chargeId|充电桩ID|string||
|&emsp;&emsp;chargeName|充电桩名称|string||
|&emsp;&emsp;createTime|订单创建时间|string(date-time)||
|&emsp;&emsp;id|订单ID|string||
|&emsp;&emsp;state|订单状态|integer(int32)||
|&emsp;&emsp;totalPrice|订单总价|number||
|&emsp;&emsp;userDTO|用户信息|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称|string||
|&emsp;&emsp;userId|用户ID|string||
|&emsp;&emsp;username|用户名|string||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"chargeId": "",
			"chargeName": "",
			"createTime": "",
			"id": "",
			"state": 0,
			"totalPrice": 0,
			"userDTO": {
				"headPic": "",
				"id": "",
				"password": "",
				"phone": "",
				"rate": 0,
				"roleId": 0,
				"sex": 0,
				"token": "",
				"username": ""
			},
			"userId": "",
			"username": ""
		}
	],
	"msg": ""
}
```


## 删除订单信息


**接口地址**:`/order/delete`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "chargeId": "",
  "chargeName": "",
  "createTime": "",
  "id": "",
  "state": 0,
  "totalPrice": 0,
  "userDTO": {
    "headPic": "",
    "id": "",
    "password": "",
    "phone": "",
    "rate": 0,
    "roleId": 0,
    "sex": 0,
    "token": "",
    "username": ""
  },
  "userId": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|orderDTO|订单传输对象|body|true|OrderDTO|OrderDTO|
|&emsp;&emsp;chargeId|充电桩ID||false|string||
|&emsp;&emsp;chargeName|充电桩名称||false|string||
|&emsp;&emsp;createTime|订单创建时间||false|string(date-time)||
|&emsp;&emsp;id|订单ID||false|string||
|&emsp;&emsp;state|订单状态||false|integer(int32)||
|&emsp;&emsp;totalPrice|订单总价||false|number||
|&emsp;&emsp;userDTO|用户信息||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|保存成功！|ResponseDTO«boolean»|
|201|Created||
|400|参数错误||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|服务器内部错误||
|-6000|订单创建失败，请联系管理员！||
|-6001|订单信息编辑失败，请联系管理员！||
|-6002|订单信息删除失败，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


## 更改订单状态信息


**接口地址**:`/order/edit_state`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "chargeId": "",
  "chargeName": "",
  "createTime": "",
  "id": "",
  "state": 0,
  "totalPrice": 0,
  "userDTO": {
    "headPic": "",
    "id": "",
    "password": "",
    "phone": "",
    "rate": 0,
    "roleId": 0,
    "sex": 0,
    "token": "",
    "username": ""
  },
  "userId": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|orderDTO|订单传输对象|body|true|OrderDTO|OrderDTO|
|&emsp;&emsp;chargeId|充电桩ID||false|string||
|&emsp;&emsp;chargeName|充电桩名称||false|string||
|&emsp;&emsp;createTime|订单创建时间||false|string(date-time)||
|&emsp;&emsp;id|订单ID||false|string||
|&emsp;&emsp;state|订单状态||false|integer(int32)||
|&emsp;&emsp;totalPrice|订单总价||false|number||
|&emsp;&emsp;userDTO|用户信息||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|保存成功！|ResponseDTO«boolean»|
|201|Created||
|400|参数错误||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|服务器内部错误||
|-6000|订单创建失败，请联系管理员！||
|-6001|订单信息编辑失败，请联系管理员！||
|-6002|订单信息删除失败，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


## 分页获取订单数据


**接口地址**:`/order/list`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "list": [
    {
      "chargeId": "",
      "chargeName": "",
      "createTime": "",
      "id": "",
      "state": 0,
      "totalPrice": 0,
      "userDTO": {
        "headPic": "",
        "id": "",
        "password": "",
        "phone": "",
        "rate": 0,
        "roleId": 0,
        "sex": 0,
        "token": "",
        "username": ""
      },
      "userId": "",
      "username": ""
    }
  ],
  "page": 0,
  "param": {
    "chargeId": "",
    "chargeName": "",
    "createTime": "",
    "id": "",
    "state": 0,
    "totalPrice": 0,
    "userDTO": {
      "headPic": "",
      "id": "",
      "password": "",
      "phone": "",
      "rate": 0,
      "roleId": 0,
      "sex": 0,
      "token": "",
      "username": ""
    },
    "userId": "",
    "username": ""
  },
  "size": 0,
  "total": 0,
  "totalPage": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pageDTO|分页传输对象|body|true|PageDTO«OrderDTO»|PageDTO«OrderDTO»|
|&emsp;&emsp;list|返回数据||false|array|OrderDTO|
|&emsp;&emsp;&emsp;&emsp;chargeId|充电桩ID||false|string||
|&emsp;&emsp;&emsp;&emsp;chargeName|充电桩名称||false|string||
|&emsp;&emsp;&emsp;&emsp;createTime|订单创建时间||false|string||
|&emsp;&emsp;&emsp;&emsp;id|订单ID||false|string||
|&emsp;&emsp;&emsp;&emsp;state|订单状态||false|integer||
|&emsp;&emsp;&emsp;&emsp;totalPrice|订单总价||false|number||
|&emsp;&emsp;&emsp;&emsp;userDTO|用户信息||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户名||false|string||
|&emsp;&emsp;page|当前页码||false|integer(int32)||
|&emsp;&emsp;param|请求参数||false|OrderDTO|OrderDTO|
|&emsp;&emsp;&emsp;&emsp;chargeId|充电桩ID||false|string||
|&emsp;&emsp;&emsp;&emsp;chargeName|充电桩名称||false|string||
|&emsp;&emsp;&emsp;&emsp;createTime|订单创建时间||false|string||
|&emsp;&emsp;&emsp;&emsp;id|订单ID||false|string||
|&emsp;&emsp;&emsp;&emsp;state|订单状态||false|integer||
|&emsp;&emsp;&emsp;&emsp;totalPrice|订单总价||false|number||
|&emsp;&emsp;&emsp;&emsp;userDTO|用户信息||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户名||false|string||
|&emsp;&emsp;size|每页条数||false|integer(int32)||
|&emsp;&emsp;total|总条数||false|integer(int64)||
|&emsp;&emsp;totalPage|总页数||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|保存成功！|ResponseDTO«PageDTO«OrderDTO»»|
|201|Created||
|400|参数错误||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|服务器内部错误||
|-6000|订单创建失败，请联系管理员！||
|-6001|订单信息编辑失败，请联系管理员！||
|-6002|订单信息删除失败，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|PageDTO«OrderDTO»|PageDTO«OrderDTO»|
|&emsp;&emsp;list|返回数据|array|OrderDTO|
|&emsp;&emsp;&emsp;&emsp;chargeId|充电桩ID|string||
|&emsp;&emsp;&emsp;&emsp;chargeName|充电桩名称|string||
|&emsp;&emsp;&emsp;&emsp;createTime|订单创建时间|string||
|&emsp;&emsp;&emsp;&emsp;id|订单ID|string||
|&emsp;&emsp;&emsp;&emsp;state|订单状态|integer||
|&emsp;&emsp;&emsp;&emsp;totalPrice|订单总价|number||
|&emsp;&emsp;&emsp;&emsp;userDTO|用户信息|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;headPic|用户头像|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone|手机号码|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;rate|用户评分|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sex|用户性别|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;token|用户令牌|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;username|用户昵称|string||
|&emsp;&emsp;&emsp;&emsp;userId|用户ID|string||
|&emsp;&emsp;&emsp;&emsp;username|用户名|string||
|&emsp;&emsp;page|当前页码|integer(int32)||
|&emsp;&emsp;param|请求参数|OrderDTO|OrderDTO|
|&emsp;&emsp;&emsp;&emsp;chargeId|充电桩ID|string||
|&emsp;&emsp;&emsp;&emsp;chargeName|充电桩名称|string||
|&emsp;&emsp;&emsp;&emsp;createTime|订单创建时间|string||
|&emsp;&emsp;&emsp;&emsp;id|订单ID|string||
|&emsp;&emsp;&emsp;&emsp;state|订单状态|integer||
|&emsp;&emsp;&emsp;&emsp;totalPrice|订单总价|number||
|&emsp;&emsp;&emsp;&emsp;userDTO|用户信息|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;headPic|用户头像|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone|手机号码|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;rate|用户评分|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sex|用户性别|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;token|用户令牌|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;username|用户昵称|string||
|&emsp;&emsp;&emsp;&emsp;userId|用户ID|string||
|&emsp;&emsp;&emsp;&emsp;username|用户名|string||
|&emsp;&emsp;size|每页条数|integer(int32)||
|&emsp;&emsp;total|总条数|integer(int64)||
|&emsp;&emsp;totalPage|总页数|integer(int32)||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"list": [
			{
				"chargeId": "",
				"chargeName": "",
				"createTime": "",
				"id": "",
				"state": 0,
				"totalPrice": 0,
				"userDTO": {
					"headPic": "",
					"id": "",
					"password": "",
					"phone": "",
					"rate": 0,
					"roleId": 0,
					"sex": 0,
					"token": "",
					"username": ""
				},
				"userId": "",
				"username": ""
			}
		],
		"page": 0,
		"param": {
			"chargeId": "",
			"chargeName": "",
			"createTime": "",
			"id": "",
			"state": 0,
			"totalPrice": 0,
			"userDTO": {
				"headPic": "",
				"id": "",
				"password": "",
				"phone": "",
				"rate": 0,
				"roleId": 0,
				"sex": 0,
				"token": "",
				"username": ""
			},
			"userId": "",
			"username": ""
		},
		"size": 0,
		"total": 0,
		"totalPage": 0
	},
	"msg": ""
}
```


# 运营商管理


## 获取全部运营商信息


**接口地址**:`/operator/all`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|保存成功！|ResponseDTO«List«OperatorDTO»»|
|201|Created||
|400|参数错误||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|服务器内部错误||
|-2000|运营商信息添加失败，请联系管理员！||
|-2001|该运营商不存在！||
|-2002|运营商信息编辑失败，请联系管理员！||
|-2003|运营商信息删除失败，请联系管理员！||
|-2004|运营商名称重复，请换一个！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|array|OperatorDTO|
|&emsp;&emsp;description|运营商描述|string||
|&emsp;&emsp;id|运营商ID|string||
|&emsp;&emsp;name|运营商名称|string||
|&emsp;&emsp;stationNum|电站数量|integer(int32)||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"description": "",
			"id": "",
			"name": "",
			"stationNum": 0
		}
	],
	"msg": ""
}
```


## 删除运营商信息


**接口地址**:`/operator/delete`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "description": "",
  "id": "",
  "name": "",
  "stationNum": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|operatorDTO|运营商传输对象|body|true|OperatorDTO|OperatorDTO|
|&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;stationNum|电站数量||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|保存成功！|ResponseDTO«boolean»|
|201|Created||
|400|参数错误||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|服务器内部错误||
|-2000|运营商信息添加失败，请联系管理员！||
|-2001|该运营商不存在！||
|-2002|运营商信息编辑失败，请联系管理员！||
|-2003|运营商信息删除失败，请联系管理员！||
|-2004|运营商名称重复，请换一个！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


## 分页获取运营商数据


**接口地址**:`/operator/list`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "list": [
    {
      "description": "",
      "id": "",
      "name": "",
      "stationNum": 0
    }
  ],
  "page": 0,
  "param": {
    "description": "",
    "id": "",
    "name": "",
    "stationNum": 0
  },
  "size": 0,
  "total": 0,
  "totalPage": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pageDTO|分页传输对象|body|true|PageDTO«OperatorDTO»|PageDTO«OperatorDTO»|
|&emsp;&emsp;list|返回数据||false|array|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;&emsp;&emsp;stationNum|电站数量||false|integer||
|&emsp;&emsp;page|当前页码||false|integer(int32)||
|&emsp;&emsp;param|请求参数||false|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;&emsp;&emsp;stationNum|电站数量||false|integer||
|&emsp;&emsp;size|每页条数||false|integer(int32)||
|&emsp;&emsp;total|总条数||false|integer(int64)||
|&emsp;&emsp;totalPage|总页数||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|保存成功！|ResponseDTO«PageDTO«OperatorDTO»»|
|201|Created||
|400|参数错误||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|服务器内部错误||
|-2000|运营商信息添加失败，请联系管理员！||
|-2001|该运营商不存在！||
|-2002|运营商信息编辑失败，请联系管理员！||
|-2003|运营商信息删除失败，请联系管理员！||
|-2004|运营商名称重复，请换一个！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|PageDTO«OperatorDTO»|PageDTO«OperatorDTO»|
|&emsp;&emsp;list|返回数据|array|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;description|运营商描述|string||
|&emsp;&emsp;&emsp;&emsp;id|运营商ID|string||
|&emsp;&emsp;&emsp;&emsp;name|运营商名称|string||
|&emsp;&emsp;&emsp;&emsp;stationNum|电站数量|integer||
|&emsp;&emsp;page|当前页码|integer(int32)||
|&emsp;&emsp;param|请求参数|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;description|运营商描述|string||
|&emsp;&emsp;&emsp;&emsp;id|运营商ID|string||
|&emsp;&emsp;&emsp;&emsp;name|运营商名称|string||
|&emsp;&emsp;&emsp;&emsp;stationNum|电站数量|integer||
|&emsp;&emsp;size|每页条数|integer(int32)||
|&emsp;&emsp;total|总条数|integer(int64)||
|&emsp;&emsp;totalPage|总页数|integer(int32)||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"list": [
			{
				"description": "",
				"id": "",
				"name": "",
				"stationNum": 0
			}
		],
		"page": 0,
		"param": {
			"description": "",
			"id": "",
			"name": "",
			"stationNum": 0
		},
		"size": 0,
		"total": 0,
		"totalPage": 0
	},
	"msg": ""
}
```


## 保存运营商信息


**接口地址**:`/operator/save`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "description": "",
  "id": "",
  "name": "",
  "stationNum": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|operatorDTO|运营商传输对象|body|true|OperatorDTO|OperatorDTO|
|&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;stationNum|电站数量||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|保存成功！|ResponseDTO«boolean»|
|201|Created||
|400|参数错误||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|服务器内部错误||
|-2000|运营商信息添加失败，请联系管理员！||
|-2001|该运营商不存在！||
|-2002|运营商信息编辑失败，请联系管理员！||
|-2003|运营商信息删除失败，请联系管理员！||
|-2004|运营商名称重复，请换一个！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


# 预约管理


## 获取全部预约数据


**接口地址**:`/appoint/all`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "chargeDTO": {
    "description": "",
    "id": "",
    "name": "",
    "photo": "",
    "price": 0,
    "state": 0,
    "stationDTO": {
      "availableChargeNum": 0,
      "chargeNum": 0,
      "id": "",
      "location": "",
      "name": "",
      "operatorDTO": {
        "description": "",
        "id": "",
        "name": "",
        "stationNum": 0
      },
      "operatorId": "",
      "photo": ""
    },
    "stationId": ""
  },
  "chargeId": "",
  "chargeName": "",
  "chargePrice": 0,
  "createTime": "",
  "day": "",
  "endTime": "",
  "id": "",
  "startTime": "",
  "state": 0,
  "time": "",
  "userDTO": {
    "headPic": "",
    "id": "",
    "password": "",
    "phone": "",
    "rate": 0,
    "roleId": 0,
    "sex": 0,
    "token": "",
    "username": ""
  },
  "userId": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|appointDTO|预约充电桩时传递的数据模型|body|true|AppointDTO|AppointDTO|
|&emsp;&emsp;chargeDTO|充电桩信息||false|ChargeDTO|ChargeDTO|
|&emsp;&emsp;&emsp;&emsp;description|充电桩规格描述||true|string||
|&emsp;&emsp;&emsp;&emsp;id|充电桩ID||false|string||
|&emsp;&emsp;&emsp;&emsp;name|充电桩名称||true|string||
|&emsp;&emsp;&emsp;&emsp;photo|充电桩照片||false|string||
|&emsp;&emsp;&emsp;&emsp;price|充电价格||true|number||
|&emsp;&emsp;&emsp;&emsp;state|充电桩状态||false|integer||
|&emsp;&emsp;&emsp;&emsp;stationDTO|电站信息||false|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|电站唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;location|电站位置||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|电站名称||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息||false|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|电站照片||false|string||
|&emsp;&emsp;&emsp;&emsp;stationId|所属电站ID||true|string||
|&emsp;&emsp;chargeId|充电桩ID||false|string||
|&emsp;&emsp;chargeName|充电桩名称||false|string||
|&emsp;&emsp;chargePrice|充电价格||false|number||
|&emsp;&emsp;createTime|创建时间||false|string(date-time)||
|&emsp;&emsp;day|预约日期||false|string(date-time)||
|&emsp;&emsp;endTime|结束充电时间||false|string(date-time)||
|&emsp;&emsp;id|预约表唯一标识||false|string||
|&emsp;&emsp;startTime|开始充电时间||false|string(date-time)||
|&emsp;&emsp;state|预约状态||false|integer(int32)||
|&emsp;&emsp;time|预约时间段||false|string||
|&emsp;&emsp;userDTO|用户信息||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|保存成功！|ResponseDTO«List«AppointDTO»»|
|201|Created||
|400|参数错误||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|服务器内部错误||
|-5000|预约失败，请联系管理员！||
|-5001|此预约日期时间段已被人预约，请换一个！||
|-5002|预约信息编辑失败，请联系管理员！||
|-5003|此充电桩正在维修，不能预约！||
|-5004|预约信息删除失败，请联系管理员！||
|-5005|信誉积分小于80分无法预约，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|array|AppointDTO|
|&emsp;&emsp;chargeDTO|充电桩信息|ChargeDTO|ChargeDTO|
|&emsp;&emsp;&emsp;&emsp;description|充电桩规格描述|string||
|&emsp;&emsp;&emsp;&emsp;id|充电桩ID|string||
|&emsp;&emsp;&emsp;&emsp;name|充电桩名称|string||
|&emsp;&emsp;&emsp;&emsp;photo|充电桩照片|string||
|&emsp;&emsp;&emsp;&emsp;price|充电价格|number||
|&emsp;&emsp;&emsp;&emsp;state|充电桩状态|integer||
|&emsp;&emsp;&emsp;&emsp;stationDTO|电站信息|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|电站唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;location|电站位置|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|电站名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|电站照片|string||
|&emsp;&emsp;&emsp;&emsp;stationId|所属电站ID|string||
|&emsp;&emsp;chargeId|充电桩ID|string||
|&emsp;&emsp;chargeName|充电桩名称|string||
|&emsp;&emsp;chargePrice|充电价格|number||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;day|预约日期|string(date-time)||
|&emsp;&emsp;endTime|结束充电时间|string(date-time)||
|&emsp;&emsp;id|预约表唯一标识|string||
|&emsp;&emsp;startTime|开始充电时间|string(date-time)||
|&emsp;&emsp;state|预约状态|integer(int32)||
|&emsp;&emsp;time|预约时间段|string||
|&emsp;&emsp;userDTO|用户信息|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称|string||
|&emsp;&emsp;userId|用户ID|string||
|&emsp;&emsp;username|用户名|string||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"chargeDTO": {
				"description": "",
				"id": "",
				"name": "",
				"photo": "",
				"price": 0,
				"state": 0,
				"stationDTO": {
					"availableChargeNum": 0,
					"chargeNum": 0,
					"id": "",
					"location": "",
					"name": "",
					"operatorDTO": {
						"description": "",
						"id": "",
						"name": "",
						"stationNum": 0
					},
					"operatorId": "",
					"photo": ""
				},
				"stationId": ""
			},
			"chargeId": "",
			"chargeName": "",
			"chargePrice": 0,
			"createTime": "",
			"day": "",
			"endTime": "",
			"id": "",
			"startTime": "",
			"state": 0,
			"time": "",
			"userDTO": {
				"headPic": "",
				"id": "",
				"password": "",
				"phone": "",
				"rate": 0,
				"roleId": 0,
				"sex": 0,
				"token": "",
				"username": ""
			},
			"userId": "",
			"username": ""
		}
	],
	"msg": ""
}
```


## 获取已预约的日期


**接口地址**:`/appoint/appointed`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "chargeDTO": {
    "description": "",
    "id": "",
    "name": "",
    "photo": "",
    "price": 0,
    "state": 0,
    "stationDTO": {
      "availableChargeNum": 0,
      "chargeNum": 0,
      "id": "",
      "location": "",
      "name": "",
      "operatorDTO": {
        "description": "",
        "id": "",
        "name": "",
        "stationNum": 0
      },
      "operatorId": "",
      "photo": ""
    },
    "stationId": ""
  },
  "chargeId": "",
  "chargeName": "",
  "chargePrice": 0,
  "createTime": "",
  "day": "",
  "endTime": "",
  "id": "",
  "startTime": "",
  "state": 0,
  "time": "",
  "userDTO": {
    "headPic": "",
    "id": "",
    "password": "",
    "phone": "",
    "rate": 0,
    "roleId": 0,
    "sex": 0,
    "token": "",
    "username": ""
  },
  "userId": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|appointDTO|预约充电桩时传递的数据模型|body|true|AppointDTO|AppointDTO|
|&emsp;&emsp;chargeDTO|充电桩信息||false|ChargeDTO|ChargeDTO|
|&emsp;&emsp;&emsp;&emsp;description|充电桩规格描述||true|string||
|&emsp;&emsp;&emsp;&emsp;id|充电桩ID||false|string||
|&emsp;&emsp;&emsp;&emsp;name|充电桩名称||true|string||
|&emsp;&emsp;&emsp;&emsp;photo|充电桩照片||false|string||
|&emsp;&emsp;&emsp;&emsp;price|充电价格||true|number||
|&emsp;&emsp;&emsp;&emsp;state|充电桩状态||false|integer||
|&emsp;&emsp;&emsp;&emsp;stationDTO|电站信息||false|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|电站唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;location|电站位置||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|电站名称||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息||false|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|电站照片||false|string||
|&emsp;&emsp;&emsp;&emsp;stationId|所属电站ID||true|string||
|&emsp;&emsp;chargeId|充电桩ID||false|string||
|&emsp;&emsp;chargeName|充电桩名称||false|string||
|&emsp;&emsp;chargePrice|充电价格||false|number||
|&emsp;&emsp;createTime|创建时间||false|string(date-time)||
|&emsp;&emsp;day|预约日期||false|string(date-time)||
|&emsp;&emsp;endTime|结束充电时间||false|string(date-time)||
|&emsp;&emsp;id|预约表唯一标识||false|string||
|&emsp;&emsp;startTime|开始充电时间||false|string(date-time)||
|&emsp;&emsp;state|预约状态||false|integer(int32)||
|&emsp;&emsp;time|预约时间段||false|string||
|&emsp;&emsp;userDTO|用户信息||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|保存成功！|ResponseDTO«List«AppointDTO»»|
|201|Created||
|400|参数错误||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|服务器内部错误||
|-5000|预约失败，请联系管理员！||
|-5001|此预约日期时间段已被人预约，请换一个！||
|-5002|预约信息编辑失败，请联系管理员！||
|-5003|此充电桩正在维修，不能预约！||
|-5004|预约信息删除失败，请联系管理员！||
|-5005|信誉积分小于80分无法预约，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|array|AppointDTO|
|&emsp;&emsp;chargeDTO|充电桩信息|ChargeDTO|ChargeDTO|
|&emsp;&emsp;&emsp;&emsp;description|充电桩规格描述|string||
|&emsp;&emsp;&emsp;&emsp;id|充电桩ID|string||
|&emsp;&emsp;&emsp;&emsp;name|充电桩名称|string||
|&emsp;&emsp;&emsp;&emsp;photo|充电桩照片|string||
|&emsp;&emsp;&emsp;&emsp;price|充电价格|number||
|&emsp;&emsp;&emsp;&emsp;state|充电桩状态|integer||
|&emsp;&emsp;&emsp;&emsp;stationDTO|电站信息|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|电站唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;location|电站位置|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|电站名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|电站照片|string||
|&emsp;&emsp;&emsp;&emsp;stationId|所属电站ID|string||
|&emsp;&emsp;chargeId|充电桩ID|string||
|&emsp;&emsp;chargeName|充电桩名称|string||
|&emsp;&emsp;chargePrice|充电价格|number||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;day|预约日期|string(date-time)||
|&emsp;&emsp;endTime|结束充电时间|string(date-time)||
|&emsp;&emsp;id|预约表唯一标识|string||
|&emsp;&emsp;startTime|开始充电时间|string(date-time)||
|&emsp;&emsp;state|预约状态|integer(int32)||
|&emsp;&emsp;time|预约时间段|string||
|&emsp;&emsp;userDTO|用户信息|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称|string||
|&emsp;&emsp;userId|用户ID|string||
|&emsp;&emsp;username|用户名|string||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"chargeDTO": {
				"description": "",
				"id": "",
				"name": "",
				"photo": "",
				"price": 0,
				"state": 0,
				"stationDTO": {
					"availableChargeNum": 0,
					"chargeNum": 0,
					"id": "",
					"location": "",
					"name": "",
					"operatorDTO": {
						"description": "",
						"id": "",
						"name": "",
						"stationNum": 0
					},
					"operatorId": "",
					"photo": ""
				},
				"stationId": ""
			},
			"chargeId": "",
			"chargeName": "",
			"chargePrice": 0,
			"createTime": "",
			"day": "",
			"endTime": "",
			"id": "",
			"startTime": "",
			"state": 0,
			"time": "",
			"userDTO": {
				"headPic": "",
				"id": "",
				"password": "",
				"phone": "",
				"rate": 0,
				"roleId": 0,
				"sex": 0,
				"token": "",
				"username": ""
			},
			"userId": "",
			"username": ""
		}
	],
	"msg": ""
}
```


## 删除预约信息


**接口地址**:`/appoint/delete`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "chargeDTO": {
    "description": "",
    "id": "",
    "name": "",
    "photo": "",
    "price": 0,
    "state": 0,
    "stationDTO": {
      "availableChargeNum": 0,
      "chargeNum": 0,
      "id": "",
      "location": "",
      "name": "",
      "operatorDTO": {
        "description": "",
        "id": "",
        "name": "",
        "stationNum": 0
      },
      "operatorId": "",
      "photo": ""
    },
    "stationId": ""
  },
  "chargeId": "",
  "chargeName": "",
  "chargePrice": 0,
  "createTime": "",
  "day": "",
  "endTime": "",
  "id": "",
  "startTime": "",
  "state": 0,
  "time": "",
  "userDTO": {
    "headPic": "",
    "id": "",
    "password": "",
    "phone": "",
    "rate": 0,
    "roleId": 0,
    "sex": 0,
    "token": "",
    "username": ""
  },
  "userId": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|appointDTO|预约充电桩时传递的数据模型|body|true|AppointDTO|AppointDTO|
|&emsp;&emsp;chargeDTO|充电桩信息||false|ChargeDTO|ChargeDTO|
|&emsp;&emsp;&emsp;&emsp;description|充电桩规格描述||true|string||
|&emsp;&emsp;&emsp;&emsp;id|充电桩ID||false|string||
|&emsp;&emsp;&emsp;&emsp;name|充电桩名称||true|string||
|&emsp;&emsp;&emsp;&emsp;photo|充电桩照片||false|string||
|&emsp;&emsp;&emsp;&emsp;price|充电价格||true|number||
|&emsp;&emsp;&emsp;&emsp;state|充电桩状态||false|integer||
|&emsp;&emsp;&emsp;&emsp;stationDTO|电站信息||false|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|电站唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;location|电站位置||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|电站名称||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息||false|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|电站照片||false|string||
|&emsp;&emsp;&emsp;&emsp;stationId|所属电站ID||true|string||
|&emsp;&emsp;chargeId|充电桩ID||false|string||
|&emsp;&emsp;chargeName|充电桩名称||false|string||
|&emsp;&emsp;chargePrice|充电价格||false|number||
|&emsp;&emsp;createTime|创建时间||false|string(date-time)||
|&emsp;&emsp;day|预约日期||false|string(date-time)||
|&emsp;&emsp;endTime|结束充电时间||false|string(date-time)||
|&emsp;&emsp;id|预约表唯一标识||false|string||
|&emsp;&emsp;startTime|开始充电时间||false|string(date-time)||
|&emsp;&emsp;state|预约状态||false|integer(int32)||
|&emsp;&emsp;time|预约时间段||false|string||
|&emsp;&emsp;userDTO|用户信息||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|保存成功！|ResponseDTO«boolean»|
|201|Created||
|400|参数错误||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|服务器内部错误||
|-5000|预约失败，请联系管理员！||
|-5001|此预约日期时间段已被人预约，请换一个！||
|-5002|预约信息编辑失败，请联系管理员！||
|-5003|此充电桩正在维修，不能预约！||
|-5004|预约信息删除失败，请联系管理员！||
|-5005|信誉积分小于80分无法预约，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```


## 更改预约状态信息


**接口地址**:`/appoint/edit_state`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "chargeDTO": {
    "description": "",
    "id": "",
    "name": "",
    "photo": "",
    "price": 0,
    "state": 0,
    "stationDTO": {
      "availableChargeNum": 0,
      "chargeNum": 0,
      "id": "",
      "location": "",
      "name": "",
      "operatorDTO": {
        "description": "",
        "id": "",
        "name": "",
        "stationNum": 0
      },
      "operatorId": "",
      "photo": ""
    },
    "stationId": ""
  },
  "chargeId": "",
  "chargeName": "",
  "chargePrice": 0,
  "createTime": "",
  "day": "",
  "endTime": "",
  "id": "",
  "startTime": "",
  "state": 0,
  "time": "",
  "userDTO": {
    "headPic": "",
    "id": "",
    "password": "",
    "phone": "",
    "rate": 0,
    "roleId": 0,
    "sex": 0,
    "token": "",
    "username": ""
  },
  "userId": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|appointDTO|预约充电桩时传递的数据模型|body|true|AppointDTO|AppointDTO|
|&emsp;&emsp;chargeDTO|充电桩信息||false|ChargeDTO|ChargeDTO|
|&emsp;&emsp;&emsp;&emsp;description|充电桩规格描述||true|string||
|&emsp;&emsp;&emsp;&emsp;id|充电桩ID||false|string||
|&emsp;&emsp;&emsp;&emsp;name|充电桩名称||true|string||
|&emsp;&emsp;&emsp;&emsp;photo|充电桩照片||false|string||
|&emsp;&emsp;&emsp;&emsp;price|充电价格||true|number||
|&emsp;&emsp;&emsp;&emsp;state|充电桩状态||false|integer||
|&emsp;&emsp;&emsp;&emsp;stationDTO|电站信息||false|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|电站唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;location|电站位置||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|电站名称||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息||false|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|电站照片||false|string||
|&emsp;&emsp;&emsp;&emsp;stationId|所属电站ID||true|string||
|&emsp;&emsp;chargeId|充电桩ID||false|string||
|&emsp;&emsp;chargeName|充电桩名称||false|string||
|&emsp;&emsp;chargePrice|充电价格||false|number||
|&emsp;&emsp;createTime|创建时间||false|string(date-time)||
|&emsp;&emsp;day|预约日期||false|string(date-time)||
|&emsp;&emsp;endTime|结束充电时间||false|string(date-time)||
|&emsp;&emsp;id|预约表唯一标识||false|string||
|&emsp;&emsp;startTime|开始充电时间||false|string(date-time)||
|&emsp;&emsp;state|预约状态||false|integer(int32)||
|&emsp;&emsp;time|预约时间段||false|string||
|&emsp;&emsp;userDTO|用户信息||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|保存成功！|ResponseDTO«bigdecimal»|
|201|Created||
|400|参数错误||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|服务器内部错误||
|-5000|预约失败，请联系管理员！||
|-5001|此预约日期时间段已被人预约，请换一个！||
|-5002|预约信息编辑失败，请联系管理员！||
|-5003|此充电桩正在维修，不能预约！||
|-5004|预约信息删除失败，请联系管理员！||
|-5005|信誉积分小于80分无法预约，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|number||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": 0,
	"msg": ""
}
```


## 分页获取预约数据


**接口地址**:`/appoint/list`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "list": [
    {
      "chargeDTO": {
        "description": "",
        "id": "",
        "name": "",
        "photo": "",
        "price": 0,
        "state": 0,
        "stationDTO": {
          "availableChargeNum": 0,
          "chargeNum": 0,
          "id": "",
          "location": "",
          "name": "",
          "operatorDTO": {
            "description": "",
            "id": "",
            "name": "",
            "stationNum": 0
          },
          "operatorId": "",
          "photo": ""
        },
        "stationId": ""
      },
      "chargeId": "",
      "chargeName": "",
      "chargePrice": 0,
      "createTime": "",
      "day": "",
      "endTime": "",
      "id": "",
      "startTime": "",
      "state": 0,
      "time": "",
      "userDTO": {
        "headPic": "",
        "id": "",
        "password": "",
        "phone": "",
        "rate": 0,
        "roleId": 0,
        "sex": 0,
        "token": "",
        "username": ""
      },
      "userId": "",
      "username": ""
    }
  ],
  "page": 0,
  "param": {
    "chargeDTO": {
      "description": "",
      "id": "",
      "name": "",
      "photo": "",
      "price": 0,
      "state": 0,
      "stationDTO": {
        "availableChargeNum": 0,
        "chargeNum": 0,
        "id": "",
        "location": "",
        "name": "",
        "operatorDTO": {
          "description": "",
          "id": "",
          "name": "",
          "stationNum": 0
        },
        "operatorId": "",
        "photo": ""
      },
      "stationId": ""
    },
    "chargeId": "",
    "chargeName": "",
    "chargePrice": 0,
    "createTime": "",
    "day": "",
    "endTime": "",
    "id": "",
    "startTime": "",
    "state": 0,
    "time": "",
    "userDTO": {
      "headPic": "",
      "id": "",
      "password": "",
      "phone": "",
      "rate": 0,
      "roleId": 0,
      "sex": 0,
      "token": "",
      "username": ""
    },
    "userId": "",
    "username": ""
  },
  "size": 0,
  "total": 0,
  "totalPage": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pageDTO|分页传输对象|body|true|PageDTO«AppointDTO»|PageDTO«AppointDTO»|
|&emsp;&emsp;list|返回数据||false|array|AppointDTO|
|&emsp;&emsp;&emsp;&emsp;chargeDTO|充电桩信息||false|ChargeDTO|ChargeDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|充电桩规格描述||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|充电桩ID||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|充电桩名称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|充电桩照片||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;price|充电价格||true|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;state|充电桩状态||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationDTO|电站信息||false|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|电站唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;location|电站位置||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|电站名称||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息||false|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|电站照片||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationId|所属电站ID||true|string||
|&emsp;&emsp;&emsp;&emsp;chargeId|充电桩ID||false|string||
|&emsp;&emsp;&emsp;&emsp;chargeName|充电桩名称||false|string||
|&emsp;&emsp;&emsp;&emsp;chargePrice|充电价格||false|number||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间||false|string||
|&emsp;&emsp;&emsp;&emsp;day|预约日期||false|string||
|&emsp;&emsp;&emsp;&emsp;endTime|结束充电时间||false|string||
|&emsp;&emsp;&emsp;&emsp;id|预约表唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;startTime|开始充电时间||false|string||
|&emsp;&emsp;&emsp;&emsp;state|预约状态||false|integer||
|&emsp;&emsp;&emsp;&emsp;time|预约时间段||false|string||
|&emsp;&emsp;&emsp;&emsp;userDTO|用户信息||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户名||false|string||
|&emsp;&emsp;page|当前页码||false|integer(int32)||
|&emsp;&emsp;param|请求参数||false|AppointDTO|AppointDTO|
|&emsp;&emsp;&emsp;&emsp;chargeDTO|充电桩信息||false|ChargeDTO|ChargeDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|充电桩规格描述||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|充电桩ID||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|充电桩名称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|充电桩照片||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;price|充电价格||true|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;state|充电桩状态||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationDTO|电站信息||false|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|电站唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;location|电站位置||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|电站名称||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息||false|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|电站照片||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationId|所属电站ID||true|string||
|&emsp;&emsp;&emsp;&emsp;chargeId|充电桩ID||false|string||
|&emsp;&emsp;&emsp;&emsp;chargeName|充电桩名称||false|string||
|&emsp;&emsp;&emsp;&emsp;chargePrice|充电价格||false|number||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间||false|string||
|&emsp;&emsp;&emsp;&emsp;day|预约日期||false|string||
|&emsp;&emsp;&emsp;&emsp;endTime|结束充电时间||false|string||
|&emsp;&emsp;&emsp;&emsp;id|预约表唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;startTime|开始充电时间||false|string||
|&emsp;&emsp;&emsp;&emsp;state|预约状态||false|integer||
|&emsp;&emsp;&emsp;&emsp;time|预约时间段||false|string||
|&emsp;&emsp;&emsp;&emsp;userDTO|用户信息||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户名||false|string||
|&emsp;&emsp;size|每页条数||false|integer(int32)||
|&emsp;&emsp;total|总条数||false|integer(int64)||
|&emsp;&emsp;totalPage|总页数||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|保存成功！|ResponseDTO«PageDTO«AppointDTO»»|
|201|Created||
|400|参数错误||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|服务器内部错误||
|-5000|预约失败，请联系管理员！||
|-5001|此预约日期时间段已被人预约，请换一个！||
|-5002|预约信息编辑失败，请联系管理员！||
|-5003|此充电桩正在维修，不能预约！||
|-5004|预约信息删除失败，请联系管理员！||
|-5005|信誉积分小于80分无法预约，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|PageDTO«AppointDTO»|PageDTO«AppointDTO»|
|&emsp;&emsp;list|返回数据|array|AppointDTO|
|&emsp;&emsp;&emsp;&emsp;chargeDTO|充电桩信息|ChargeDTO|ChargeDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|充电桩规格描述|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|充电桩ID|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|充电桩名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|充电桩照片|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;price|充电价格|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;state|充电桩状态|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationDTO|电站信息|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|电站唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;location|电站位置|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|电站名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|电站照片|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationId|所属电站ID|string||
|&emsp;&emsp;&emsp;&emsp;chargeId|充电桩ID|string||
|&emsp;&emsp;&emsp;&emsp;chargeName|充电桩名称|string||
|&emsp;&emsp;&emsp;&emsp;chargePrice|充电价格|number||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;day|预约日期|string||
|&emsp;&emsp;&emsp;&emsp;endTime|结束充电时间|string||
|&emsp;&emsp;&emsp;&emsp;id|预约表唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;startTime|开始充电时间|string||
|&emsp;&emsp;&emsp;&emsp;state|预约状态|integer||
|&emsp;&emsp;&emsp;&emsp;time|预约时间段|string||
|&emsp;&emsp;&emsp;&emsp;userDTO|用户信息|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;headPic|用户头像|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone|手机号码|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;rate|用户评分|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sex|用户性别|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;token|用户令牌|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;username|用户昵称|string||
|&emsp;&emsp;&emsp;&emsp;userId|用户ID|string||
|&emsp;&emsp;&emsp;&emsp;username|用户名|string||
|&emsp;&emsp;page|当前页码|integer(int32)||
|&emsp;&emsp;param|请求参数|AppointDTO|AppointDTO|
|&emsp;&emsp;&emsp;&emsp;chargeDTO|充电桩信息|ChargeDTO|ChargeDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|充电桩规格描述|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|充电桩ID|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|充电桩名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|充电桩照片|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;price|充电价格|number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;state|充电桩状态|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationDTO|电站信息|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|电站唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;location|电站位置|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|电站名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|电站照片|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationId|所属电站ID|string||
|&emsp;&emsp;&emsp;&emsp;chargeId|充电桩ID|string||
|&emsp;&emsp;&emsp;&emsp;chargeName|充电桩名称|string||
|&emsp;&emsp;&emsp;&emsp;chargePrice|充电价格|number||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;day|预约日期|string||
|&emsp;&emsp;&emsp;&emsp;endTime|结束充电时间|string||
|&emsp;&emsp;&emsp;&emsp;id|预约表唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;startTime|开始充电时间|string||
|&emsp;&emsp;&emsp;&emsp;state|预约状态|integer||
|&emsp;&emsp;&emsp;&emsp;time|预约时间段|string||
|&emsp;&emsp;&emsp;&emsp;userDTO|用户信息|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;headPic|用户头像|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户唯一标识|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone|手机号码|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;rate|用户评分|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;sex|用户性别|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;token|用户令牌|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;username|用户昵称|string||
|&emsp;&emsp;&emsp;&emsp;userId|用户ID|string||
|&emsp;&emsp;&emsp;&emsp;username|用户名|string||
|&emsp;&emsp;size|每页条数|integer(int32)||
|&emsp;&emsp;total|总条数|integer(int64)||
|&emsp;&emsp;totalPage|总页数|integer(int32)||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"list": [
			{
				"chargeDTO": {
					"description": "",
					"id": "",
					"name": "",
					"photo": "",
					"price": 0,
					"state": 0,
					"stationDTO": {
						"availableChargeNum": 0,
						"chargeNum": 0,
						"id": "",
						"location": "",
						"name": "",
						"operatorDTO": {
							"description": "",
							"id": "",
							"name": "",
							"stationNum": 0
						},
						"operatorId": "",
						"photo": ""
					},
					"stationId": ""
				},
				"chargeId": "",
				"chargeName": "",
				"chargePrice": 0,
				"createTime": "",
				"day": "",
				"endTime": "",
				"id": "",
				"startTime": "",
				"state": 0,
				"time": "",
				"userDTO": {
					"headPic": "",
					"id": "",
					"password": "",
					"phone": "",
					"rate": 0,
					"roleId": 0,
					"sex": 0,
					"token": "",
					"username": ""
				},
				"userId": "",
				"username": ""
			}
		],
		"page": 0,
		"param": {
			"chargeDTO": {
				"description": "",
				"id": "",
				"name": "",
				"photo": "",
				"price": 0,
				"state": 0,
				"stationDTO": {
					"availableChargeNum": 0,
					"chargeNum": 0,
					"id": "",
					"location": "",
					"name": "",
					"operatorDTO": {
						"description": "",
						"id": "",
						"name": "",
						"stationNum": 0
					},
					"operatorId": "",
					"photo": ""
				},
				"stationId": ""
			},
			"chargeId": "",
			"chargeName": "",
			"chargePrice": 0,
			"createTime": "",
			"day": "",
			"endTime": "",
			"id": "",
			"startTime": "",
			"state": 0,
			"time": "",
			"userDTO": {
				"headPic": "",
				"id": "",
				"password": "",
				"phone": "",
				"rate": 0,
				"roleId": 0,
				"sex": 0,
				"token": "",
				"username": ""
			},
			"userId": "",
			"username": ""
		},
		"size": 0,
		"total": 0,
		"totalPage": 0
	},
	"msg": ""
}
```


## 保存预约信息


**接口地址**:`/appoint/save`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "chargeDTO": {
    "description": "",
    "id": "",
    "name": "",
    "photo": "",
    "price": 0,
    "state": 0,
    "stationDTO": {
      "availableChargeNum": 0,
      "chargeNum": 0,
      "id": "",
      "location": "",
      "name": "",
      "operatorDTO": {
        "description": "",
        "id": "",
        "name": "",
        "stationNum": 0
      },
      "operatorId": "",
      "photo": ""
    },
    "stationId": ""
  },
  "chargeId": "",
  "chargeName": "",
  "chargePrice": 0,
  "createTime": "",
  "day": "",
  "endTime": "",
  "id": "",
  "startTime": "",
  "state": 0,
  "time": "",
  "userDTO": {
    "headPic": "",
    "id": "",
    "password": "",
    "phone": "",
    "rate": 0,
    "roleId": 0,
    "sex": 0,
    "token": "",
    "username": ""
  },
  "userId": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|appointDTO|预约充电桩时传递的数据模型|body|true|AppointDTO|AppointDTO|
|&emsp;&emsp;chargeDTO|充电桩信息||false|ChargeDTO|ChargeDTO|
|&emsp;&emsp;&emsp;&emsp;description|充电桩规格描述||true|string||
|&emsp;&emsp;&emsp;&emsp;id|充电桩ID||false|string||
|&emsp;&emsp;&emsp;&emsp;name|充电桩名称||true|string||
|&emsp;&emsp;&emsp;&emsp;photo|充电桩照片||false|string||
|&emsp;&emsp;&emsp;&emsp;price|充电价格||true|number||
|&emsp;&emsp;&emsp;&emsp;state|充电桩状态||false|integer||
|&emsp;&emsp;&emsp;&emsp;stationDTO|电站信息||false|StationDTO|StationDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;availableChargeNum|可用充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;chargeNum|充电桩数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|电站唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;location|电站位置||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|电站名称||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorDTO|运营商信息||false|OperatorDTO|OperatorDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;description|运营商描述||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|运营商ID||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|运营商名称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;stationNum|电站数量||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;operatorId|运营商唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;photo|电站照片||false|string||
|&emsp;&emsp;&emsp;&emsp;stationId|所属电站ID||true|string||
|&emsp;&emsp;chargeId|充电桩ID||false|string||
|&emsp;&emsp;chargeName|充电桩名称||false|string||
|&emsp;&emsp;chargePrice|充电价格||false|number||
|&emsp;&emsp;createTime|创建时间||false|string(date-time)||
|&emsp;&emsp;day|预约日期||false|string(date-time)||
|&emsp;&emsp;endTime|结束充电时间||false|string(date-time)||
|&emsp;&emsp;id|预约表唯一标识||false|string||
|&emsp;&emsp;startTime|开始充电时间||false|string(date-time)||
|&emsp;&emsp;state|预约状态||false|integer(int32)||
|&emsp;&emsp;time|预约时间段||false|string||
|&emsp;&emsp;userDTO|用户信息||false|UserDTO|UserDTO|
|&emsp;&emsp;&emsp;&emsp;headPic|用户头像||false|string||
|&emsp;&emsp;&emsp;&emsp;id|用户唯一标识||false|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;phone|手机号码||false|string||
|&emsp;&emsp;&emsp;&emsp;rate|用户评分||false|integer||
|&emsp;&emsp;&emsp;&emsp;roleId|用户角色ID||false|integer||
|&emsp;&emsp;&emsp;&emsp;sex|用户性别||false|integer||
|&emsp;&emsp;&emsp;&emsp;token|用户令牌||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户昵称||false|string||
|&emsp;&emsp;userId|用户ID||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|保存成功！|ResponseDTO«boolean»|
|201|Created||
|400|参数错误||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||
|500|服务器内部错误||
|-5000|预约失败，请联系管理员！||
|-5001|此预约日期时间段已被人预约，请换一个！||
|-5002|预约信息编辑失败，请联系管理员！||
|-5003|此充电桩正在维修，不能预约！||
|-5004|预约信息删除失败，请联系管理员！||
|-5005|信誉积分小于80分无法预约，请联系管理员！||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|响应码|integer(int32)|integer(int32)|
|data|响应数据|boolean||
|msg|响应消息|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"msg": ""
}
```