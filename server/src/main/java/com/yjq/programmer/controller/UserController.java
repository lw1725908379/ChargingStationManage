package com.yjq.programmer.controller;

import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.RateDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.dto.UserDTO;
import com.yjq.programmer.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wenLiu
 * @create 2023-12-29 13:39
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 注册用户信息
     * @param userDTO
     * @return
     */
    @ApiOperation("注册用户")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = Boolean.class),
            @ApiResponse(code = -1010, message = "注册用户失败，请联系管理员！"),
            @ApiResponse(code = -1004, message = "用户手机号码已经注册，请换一个！"),
            @ApiResponse(code = -1005, message = "用户昵称不能为空！"),
            @ApiResponse(code = -1006, message = "用户密码不能为空！"),
            @ApiResponse(code = -1008, message = "确认密码不能为空！"),
            @ApiResponse(code = -1009, message = "确认密码不一致！")
    })
    @PostMapping("/register")
    public ResponseDTO<Boolean> registerUser(@RequestBody UserDTO userDTO){
        return userService.registerUser(userDTO);
    }

    /**
     * 分页获取用户数据
     * @param pageDTO
     * @return
     */
    @ApiOperation("获取用户列表")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = PageDTO.class)
    })
    @PostMapping("/list")
    public ResponseDTO<PageDTO<UserDTO>> getUserList(@RequestBody PageDTO<UserDTO> pageDTO){
        return userService.getUserList(pageDTO);
    }

    /**
     * 删除用户信息
     * @param userDTO
     * @return
     */
    @ApiOperation("删除用户信息")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = Boolean.class),
            @ApiResponse(code = -1003, message = "用户信息删除失败，请联系管理员！"),
            @ApiResponse(code = -1001, message = "该用户不存在！")
    })
    @PostMapping("/delete")
    public ResponseDTO<Boolean> deleteUser(@RequestBody UserDTO userDTO){
        return userService.deleteUser(userDTO);
    }

    /**
     * 保存用户信息
     * @param userDTO
     * @return
     */
    @ApiOperation("保存用户信息")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = Boolean.class),
            @ApiResponse(code = -1000, message = "用户信息添加失败，请联系管理员！"),
            @ApiResponse(code = -1012, message = "用户昵称重复，请换一个！")
    })
    @PostMapping("/save")
    public ResponseDTO<Boolean> saveUser(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }


    /**
     * 用户登录操作
     * @param userDTO
     * @return
     */
    @ApiOperation("用户登录")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = UserDTO.class),
            @ApiResponse(code = -1007, message = "用户昵称或密码错误！")
    })
    @PostMapping("/login")
    public ResponseDTO<UserDTO> login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }

    /**
     * 检查用户是否登录
     * @param userDTO
     * @return
     */
    @ApiOperation("检查用户登录状态")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = UserDTO.class)
    })
    @PostMapping("/check_login")
    public ResponseDTO<UserDTO> checkLogin(@RequestBody UserDTO userDTO){
        return userService.checkLogin(userDTO);
    }

    /**
     * 获取某用户的积分明细
     * @param userDTO
     * @return
     */
    @ApiOperation("获取用户积分明细")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = List.class)
    })
    @PostMapping("/rate")
    public ResponseDTO<List<RateDTO>> getRateList(@RequestBody UserDTO userDTO){
        return userService.getRateList(userDTO);
    }

    /**
     * 用户退出登录
     * @return
     */
    @ApiOperation("用户退出登录")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = Boolean.class)
    })
    @PostMapping("/logout")
    public ResponseDTO<Boolean> logout(@RequestBody UserDTO userDTO){
        return userService.logout(userDTO);
    }

}
