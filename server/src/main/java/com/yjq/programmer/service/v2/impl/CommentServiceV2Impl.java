package com.yjq.programmer.service.v2.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lw.utils.common.SensitiveWordUtil;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.CommentMapper;
import com.yjq.programmer.dao.SensitiveMapper;
import com.yjq.programmer.dao.UserMapper;
import com.yjq.programmer.domain.*;
import com.yjq.programmer.dto.CommentDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.dto.UserDTO;
import com.yjq.programmer.service.v2.ICommentServiceV2;
import com.yjq.programmer.utils.CommonUtil;
import com.yjq.programmer.utils.CopyUtil;
import com.yjq.programmer.utils.UuidUtil;
import com.yjq.programmer.utils.ValidateEntityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional
public class CommentServiceV2Impl implements ICommentServiceV2 {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private SensitiveMapper sensitiveMapper;



    /**
     * 分页获取留言数据
     * @param pageDTO
     * @return
     */
    @Override
    public ResponseDTO<PageDTO<CommentDTO>> getCommentList(PageDTO<CommentDTO> pageDTO) {
        CommentExample commentExample = new CommentExample();
        // 不知道当前页多少，默认为第一页
        if(pageDTO.getPage() == null){
            pageDTO.setPage(1);
        }
        // 不知道每页多少条记录，默认为每页5条记录
        if(pageDTO.getSize() == null){
            pageDTO.setSize(5);
        }
        CommentExample.Criteria c1 = commentExample.createCriteria();
        if(pageDTO.getParam() != null) {
            CommentDTO commentDTO = pageDTO.getParam();
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUsernameLike("%" + commentDTO.getUsername() + "%");
            List<String> userIdList = userMapper.selectByExample(userExample).stream().map(User::getId).collect(Collectors.toList());
            if(userIdList.size() == 0) {
                pageDTO.setTotal(0L);
                pageDTO.setList(new ArrayList<>());
                return ResponseDTO.success(pageDTO);
            }
            c1.andUserIdIn(userIdList);
            c1.andContentLike("%" + commentDTO.getContent() + "%");
        }
        commentExample.setOrderByClause("create_time desc");
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getSize());
        // 分页查出留言数据
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
        // 获取数据的总数
        pageDTO.setTotal(pageInfo.getTotal());
        // 将domain类型数据  转成 DTO类型数据
        List<CommentDTO> commentDTOList = CopyUtil.copyList(commentList, CommentDTO.class);
        for(CommentDTO commentDTO : commentDTOList) {
            User user = userMapper.selectByPrimaryKey(commentDTO.getUserId());
            commentDTO.setUserDTO(CopyUtil.copy(user, UserDTO.class));
        }
        pageDTO.setList(commentDTOList);
        return ResponseDTO.success(pageDTO);
    }

    /**
     * 保存留言信息
     * @param commentDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> saveComment(CommentDTO commentDTO) {
        // 进行统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(commentDTO);
        if (!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseDTO.errorByMsg(CodeMsg.COMMENT_EDIT_ERROR);
        }
        // 评论内容审核
        if (!handleSensitiveScan(commentDTO.getContent(),commentDTO)) {
            return ResponseDTO.errorByMsg(CodeMsg.COMMENT_CONTAIN_SENSITIVE);
        }

        Comment comment = CopyUtil.copy(commentDTO, Comment.class);
        if(CommonUtil.isEmpty(comment.getId())) {
            // 添加操作
            comment.setId(UuidUtil.getShortUuid());
            comment.setCreateTime(new Date());
            if(commentMapper.insertSelective(comment) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.COMMENT_ADD_ERROR);
            }
        } else {
            // 修改操作
            comment.setUserId(null);
            if(commentMapper.updateByPrimaryKeySelective(comment) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.COMMENT_EDIT_ERROR);
            }
        }
        return ResponseDTO.successByMsg(true, "保存成功！");
    }


    private boolean handleSensitiveScan(String content, CommentDTO commentDTO) {
        // 查询所有敏感词
        List<Sensitive> sensitives = sensitiveMapper
                .selectList(Wrappers.<Sensitive>lambdaQuery().select(Sensitive::getSensitives));
        List<String> sensitivesList = sensitives.stream().map(Sensitive::getSensitives).collect(Collectors.toList());
        //  初始化敏感词
        SensitiveWordUtil.initMap(sensitivesList);
        // 匹配单词
        Map<String, Integer> map = SensitiveWordUtil.matchWords(content);
        if (map.size()>0) {
            return true;
        }
        return false;
    }

    /**
     * 删除留言信息
     * @param commentDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> deleteComment(CommentDTO commentDTO) {
        if(CommonUtil.isEmpty(commentDTO.getId())) {
            return ResponseDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        // 删除留言信息
        if(commentMapper.deleteByPrimaryKey(commentDTO.getId()) == 0) {
            return ResponseDTO.errorByMsg(CodeMsg.COMMENT_DELETE_ERROR);
        }
        return ResponseDTO.successByMsg(true, "删除成功！");
    }

    /**
     * 获取全部留言数据
     * @param commentDTO
     * @return
     */
    @Override
    public ResponseDTO<List<CommentDTO>> getAllComment(CommentDTO commentDTO) {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        if(!CommonUtil.isEmpty(commentDTO.getUserId())) {
            criteria.andUserIdEqualTo(commentDTO.getUserId());
        }
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        return ResponseDTO.success(CopyUtil.copyList(commentList, CommentDTO.class));
    }
}
