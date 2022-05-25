package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.entity.JobResource;
import com.wugui.datax.admin.entity.JobResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobResourceMapper {
    long countByExample(JobResourceExample example);

    int deleteByExample(JobResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JobResource record);

    int insertSelective(JobResource record);

    List<JobResource> selectByExample(JobResourceExample example);

    JobResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JobResource record, @Param("example") JobResourceExample example);

    int updateByExample(@Param("record") JobResource record, @Param("example") JobResourceExample example);

    int updateByPrimaryKeySelective(JobResource record);

    int updateByPrimaryKey(JobResource record);
}