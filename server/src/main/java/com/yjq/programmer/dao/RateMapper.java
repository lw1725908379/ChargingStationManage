package com.yjq.programmer.dao;

import com.yjq.programmer.domain.Rate;
import com.yjq.programmer.domain.RateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RateMapper {
    int countByExample(RateExample example);

    int deleteByExample(RateExample example);

    int deleteByPrimaryKey(String id);

    int insert(Rate record);

    int insertSelective(Rate record);

    List<Rate> selectByExample(RateExample example);

    Rate selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Rate record, @Param("example") RateExample example);

    int updateByExample(@Param("record") Rate record, @Param("example") RateExample example);

    int updateByPrimaryKeySelective(Rate record);

    int updateByPrimaryKey(Rate record);
}
