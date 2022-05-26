package com.wugui.datax.admin.mapper;


import com.wugui.datax.admin.entity.JobDsEnvironment;
import com.wugui.datax.admin.entity.JobDsEnvironmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobDsEnvironmentMapper {
    long countByExample(JobDsEnvironmentExample example);

    int deleteByExample(JobDsEnvironmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(JobDsEnvironment record);

    int insertSelective(JobDsEnvironment record);

    List<JobDsEnvironment> selectByExample(JobDsEnvironmentExample example);

    JobDsEnvironment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") JobDsEnvironment record, @Param("example") JobDsEnvironmentExample example);

    int updateByExample(@Param("record") JobDsEnvironment record, @Param("example") JobDsEnvironmentExample example);

    int updateByPrimaryKeySelective(JobDsEnvironment record);

    int updateByPrimaryKey(JobDsEnvironment record);
}
