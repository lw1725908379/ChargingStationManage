package com.yjq.programmer.service;

import com.yjq.programmer.dto.CommentDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;

import java.util.List;

/**
 * @author wenLiu
 * @create 2023-12-31 11:06
 */
public interface ICommentService {

    // 分页获取留言数据
    ResponseDTO<PageDTO<CommentDTO>> getCommentList(PageDTO<CommentDTO> pageDTO);

    // 保存留言信息
    ResponseDTO<Boolean> saveComment(CommentDTO commentDTO);

    // 删除留言信息
    ResponseDTO<Boolean> deleteComment(CommentDTO commentDTO);

    // 获取全部留言数据
    ResponseDTO<List<CommentDTO>> getAllComment(CommentDTO commentDTO);
}
