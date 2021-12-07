package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.entity.JobLogSnapshot;
import com.wugui.datax.admin.entity.JobLogSnapshotExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobLogSnapshotMapper {
    long countByExample(JobLogSnapshotExample example);

    int deleteByExample(JobLogSnapshotExample example);

    int insert(JobLogSnapshot record);

    int insertSelective(JobLogSnapshot record);

    List<JobLogSnapshot> selectByExample(JobLogSnapshotExample example);

    int updateByExampleSelective(@Param("record") JobLogSnapshot record, @Param("example") JobLogSnapshotExample example);

    int updateByExample(@Param("record") JobLogSnapshot record, @Param("example") JobLogSnapshotExample example);

    void genLogsSnapshot();
}
