package com.wugui.datax.admin.service;

import com.wugui.datax.admin.entity.JobDsEnvironment;

import java.util.List;


public interface JobDsEnvironmentService{
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    JobDsEnvironment queryById(Long id);

    /**
     * 新增数据
     *
     * @param jobDsEnvironment 实例对象
     * @return 实例对象
     */
    JobDsEnvironment insert(JobDsEnvironment jobDsEnvironment);
    /**
     * 更新数据
     *
     * @param jobDsEnvironment 实例对象
     * @return 实例对象
     */
    JobDsEnvironment update(JobDsEnvironment jobDsEnvironment);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过ID查询List
     *
     * @param id 主键
     * @return 实例对象
     */
    List<JobDsEnvironment> queryListById(Long id);

    /**
     * 通过数据源ID查询
     *
     * @param id 主键
     * @return 实例对象
     */
    JobDsEnvironment queryByDataSourceId(Long id);

}