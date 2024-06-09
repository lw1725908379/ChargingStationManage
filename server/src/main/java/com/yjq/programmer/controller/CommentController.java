package com.yjq.programmer.controller;

import com.yjq.programmer.dto.CommentDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.service.ICommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wenLiu
 * @create 2023-12-31 11:15
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private ICommentService commentService;


    /**
     * 分页获取留言数据
     * @param pageDTO
     * @return
     */
    @PostMapping("/list")
    public ResponseDTO<PageDTO<CommentDTO>> getCommentList(@RequestBody PageDTO<CommentDTO> pageDTO){
        return commentService.getCommentList(pageDTO);
    }

    /**
     * 删除留言信息
     * @param commentDTO
     * @return
     */
    @PostMapping("/delete")
    public ResponseDTO<Boolean> deleteComment(@RequestBody CommentDTO commentDTO){
        return commentService.deleteComment(commentDTO);
    }

    /**
     * 保存留言信息
     * @param commentDTO
     * @return
     */
    @PostMapping("/save")
    public ResponseDTO<Boolean> saveComment(@RequestBody CommentDTO commentDTO){
        return commentService.saveComment(commentDTO);
    }

    /**
     * 获取全部留言数据
     * @param commentDTO
     * @return
     */
    @PostMapping("/all")
    public ResponseDTO<List<CommentDTO>> getAllComment(@RequestBody CommentDTO commentDTO) {
        return commentService.getAllComment(commentDTO);
    }
}
