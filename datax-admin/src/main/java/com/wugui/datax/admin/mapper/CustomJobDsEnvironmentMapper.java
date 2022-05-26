package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.entity.JobDsEnvironment;

import java.util.List;

/**
 * @author bahsk
 * @createTime 2022-05-26 17:18
 * @description
 */
public interface CustomJobDsEnvironmentMapper {
    /**
     * 通过主键查询list JobDsEnvironment
     *
     * @param id 主键
     * @return
     */
    List<JobDsEnvironment> selectListById(Long id);
    /**
     * 通过主键 查询JobDsEnvironment
     *
     * @param id 主键
     * @return
     */
    JobDsEnvironment selectByDataSourceId(Long id);

    /**
     * 查看一个datasourceid下status=1 的数量
     *
     * @param id 主键
     * @return
     */
    int selectCountByDataSourceId(Long id);


    int update(JobDsEnvironment jobDsEnvironment);
}
