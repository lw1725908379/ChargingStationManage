package com.yjq.programmer.controller;

import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.RateDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.dto.UserDTO;
import com.yjq.programmer.service.IUserService;
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
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 注册用户信息
     * @param userDTO
     * @return
     */
    @PostMapping("/register")
    public ResponseDTO<Boolean> registerUser(@RequestBody UserDTO userDTO){
        return userService.registerUser(userDTO);
    }

    /**
     * 分页获取用户数据
     * @param pageDTO
     * @return
     */
    @PostMapping("/list")
    public ResponseDTO<PageDTO<UserDTO>> getUserList(@RequestBody PageDTO<UserDTO> pageDTO){
        return userService.getUserList(pageDTO);
    }

    /**
     * 删除用户信息
     * @param userDTO
     * @return
     */
    @PostMapping("/delete")
    public ResponseDTO<Boolean> deleteUser(@RequestBody UserDTO userDTO){
        return userService.deleteUser(userDTO);
    }

    /**
     * 保存用户信息
     * @param userDTO
     * @return
     */
    @PostMapping("/save")
    public ResponseDTO<Boolean> saveUser(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }


    /**
     * 用户登录操作
     * @param userDTO
     * @return
     */
    @PostMapping("/login")
    public ResponseDTO<UserDTO> login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }

    /**
     * 检查用户是否登录
     * @param userDTO
     * @return
     */
    @PostMapping("/check_login")
    public ResponseDTO<UserDTO> checkLogin(@RequestBody UserDTO userDTO){
        return userService.checkLogin(userDTO);
    }

    /**
     * 获取某用户的积分明细
     * @param userDTO
     * @return
     */
    @PostMapping("/rate")
    public ResponseDTO<List<RateDTO>> getRateList(@RequestBody UserDTO userDTO){
        return userService.getRateList(userDTO);
    }

    /**
     * 用户退出登录
     * @return
     */
    @PostMapping("/logout")
    public ResponseDTO<Boolean> logout(@RequestBody UserDTO userDTO){
        return userService.logout(userDTO);
    }

}
