package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.entity.JobJdbcDatasourceSnapshot;
import com.wugui.datax.admin.entity.JobJdbcDatasourceSnapshotExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobJdbcDatasourceSnapshotDao {
    long countByExample(JobJdbcDatasourceSnapshotExample example);

    int deleteByExample(JobJdbcDatasourceSnapshotExample example);

    int insert(JobJdbcDatasourceSnapshot record);

    int insertSelective(JobJdbcDatasourceSnapshot record);

    List<JobJdbcDatasourceSnapshot> selectByExample(JobJdbcDatasourceSnapshotExample example);

    int updateByExampleSelective(@Param("record") JobJdbcDatasourceSnapshot record, @Param("example") JobJdbcDatasourceSnapshotExample example);

    int updateByExample(@Param("record") JobJdbcDatasourceSnapshot record, @Param("example") JobJdbcDatasourceSnapshotExample example);
}