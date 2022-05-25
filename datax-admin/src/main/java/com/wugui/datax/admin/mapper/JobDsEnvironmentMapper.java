package com.wugui.datax.admin.mapper;

import java.awt.print.Pageable;
import java.util.List;
import com.wugui.datax.admin.entity.JobDsEnvironment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface JobDsEnvironmentMapper{
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    JobDsEnvironment queryById(Long id);
    /**
     * 分页查询指定行数据
     *
     * @param jobDsEnvironment 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<JobDsEnvironment> queryAllByLimit(JobDsEnvironment jobDsEnvironment, @Param("pageable") Pageable pageable);
    /**
     * 统计总行数
     *
     * @param jobDsEnvironment 查询条件
     * @return 总行数
     */
    long count(JobDsEnvironment jobDsEnvironment);
    /**
     * 新增数据
     *
     * @param jobDsEnvironment 实例对象
     * @return 影响行数
     */
    int insert(JobDsEnvironment jobDsEnvironment);
    /**
     * 批量新增数据
     *
     * @param entities List<JobDsEnvironment> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<JobDsEnvironment> entities);
    /**
     * 批量新增或按主键更新数据
     *
     * @param entities List<JobDsEnvironment> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<JobDsEnvironment> entities);
    /**
     * 更新数据
     *
     * @param jobDsEnvironment 实例对象
     * @return 影响行数
     */
    int update(JobDsEnvironment jobDsEnvironment);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
}