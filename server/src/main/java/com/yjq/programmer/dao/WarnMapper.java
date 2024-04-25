package com.yjq.programmer.dao;

import com.yjq.programmer.domain.Warn;
import com.yjq.programmer.domain.WarnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WarnMapper {
    int countByExample(WarnExample example);

    int deleteByExample(WarnExample example);

    int deleteByPrimaryKey(String id);

    int insert(Warn record);

    int insertSelective(Warn record);

    List<Warn> selectByExample(WarnExample example);

    Warn selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Warn record, @Param("example") WarnExample example);

    int updateByExample(@Param("record") Warn record, @Param("example") WarnExample example);

    int updateByPrimaryKeySelective(Warn record);

    int updateByPrimaryKey(Warn record);
}
