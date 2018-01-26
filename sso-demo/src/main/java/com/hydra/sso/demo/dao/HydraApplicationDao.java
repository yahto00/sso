package com.hydra.sso.demo.dao;

import com.hydra.sso.demo.model.HydraApplication;
import com.hydra.sso.demo.model.HydraApplicationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HydraApplicationDao {
    int countByExample(HydraApplicationExample example);

    int deleteByExample(HydraApplicationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HydraApplication record);

    int insertSelective(HydraApplication record);

    List<HydraApplication> selectByExample(HydraApplicationExample example);

    HydraApplication selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HydraApplication record, @Param("example") HydraApplicationExample example);

    int updateByExample(@Param("record") HydraApplication record, @Param("example") HydraApplicationExample example);

    int updateByPrimaryKeySelective(HydraApplication record);

    int updateByPrimaryKey(HydraApplication record);
}