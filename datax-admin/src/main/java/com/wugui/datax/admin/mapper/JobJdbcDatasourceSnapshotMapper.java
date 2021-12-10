package com.wugui.datax.admin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.entity.JobJdbcDatasourceSnapshot;
import com.wugui.datax.admin.entity.JobJdbcDatasourceSnapshotExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface JobJdbcDatasourceSnapshotMapper extends BaseMapper<JobJdbcDatasourceSnapshot> {
    long countByExample(JobJdbcDatasourceSnapshotExample example);

    int deleteByExample(JobJdbcDatasourceSnapshotExample example);

    int insert(JobJdbcDatasourceSnapshot record);

    int insertSelective(JobJdbcDatasourceSnapshot record);

    List<JobJdbcDatasourceSnapshot> selectByExample(JobJdbcDatasourceSnapshotExample example);

    int updateByExampleSelective(@Param("record") JobJdbcDatasourceSnapshot record, @Param("example") JobJdbcDatasourceSnapshotExample example);

    int updateByExample(@Param("record") JobJdbcDatasourceSnapshot record, @Param("example") JobJdbcDatasourceSnapshotExample example);

    void genSnapshot();
}
