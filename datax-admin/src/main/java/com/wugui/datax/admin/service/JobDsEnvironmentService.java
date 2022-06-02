package com.wugui.datax.admin.service;

import com.wugui.datax.admin.dto.JobDsEnvironmentDTO;
import com.wugui.datax.admin.dto.JobResourceDTO;
import com.wugui.datax.admin.dto.PageResp;
import com.wugui.datax.admin.entity.JobDsEnvironment;
import com.wugui.datax.admin.entity.JobResource;

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
    int update(JobDsEnvironment jobDsEnvironment);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    int deleteById(Long id);

    /**
     * 通过ID查询JobDsEnvironment
     *
     * @param id 主键
     * @return 实例对象
     */
    JobDsEnvironment queryListById(Long id);

    /**
     * 通过数据源ID查询
     *
     * @param id 主键
     * @return 实例对象
     */
    JobDsEnvironment queryByDataSourceId(Long id);


    /**
     * 查看一个datasourceid下status=1 的数量
     *
     * @param jobDsEnvironment 主键
     * @return
     */
    int selectCountById(JobDsEnvironment jobDsEnvironment);

    /**
     * 查询全部环境信息
     * @return
     */
    List<JobDsEnvironment> selectAllEnv();


    /**
     * 根据条件分页查询
     * @return
     */
    PageResp<JobDsEnvironment> list(JobDsEnvironmentDTO jobDsEnvironmentDTO);
}
