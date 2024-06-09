package com.yjq.programmer.service;

import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.RateDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.dto.UserDTO;

import java.util.List;

/**
 * @author wenLiu
 * @create 2023-12-29 13:41
 */
public interface IUserService {

    // 注册用户信息
    ResponseDTO<Boolean> registerUser(UserDTO userDTO);

    // 分页获取用户数据
    ResponseDTO<PageDTO<UserDTO>> getUserList(PageDTO<UserDTO> pageDTO);

    // 保存用户信息
    ResponseDTO<Boolean> saveUser(UserDTO userDTO);

    // 删除用户信息
    ResponseDTO<Boolean> deleteUser(UserDTO userDTO);

    // 用户登录操作
    ResponseDTO<UserDTO> login(UserDTO userDTO);

    // 检查用户是否登录
    ResponseDTO<UserDTO> checkLogin(UserDTO userDTO);

    // 获取某用户的积分明细
    ResponseDTO<List<RateDTO>> getRateList(UserDTO userDTO);

    // 用户退出登录
    ResponseDTO<Boolean> logout(UserDTO userDTO);
}
