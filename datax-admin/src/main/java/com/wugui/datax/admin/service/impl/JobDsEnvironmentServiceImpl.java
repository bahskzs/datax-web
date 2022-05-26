package com.wugui.datax.admin.service.impl;

import com.wugui.datax.admin.entity.JobDsEnvironment;
import com.wugui.datax.admin.mapper.CustomJobDsEnvironmentMapper;
import com.wugui.datax.admin.mapper.JobDsEnvironmentMapper;
import com.wugui.datax.admin.service.JobDsEnvironmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JobDsEnvironmentServiceImpl implements JobDsEnvironmentService {
    @Resource
    private JobDsEnvironmentMapper jobDsEnvironmentMapper;

    @Resource
    private CustomJobDsEnvironmentMapper customJobDsEnvironmentMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public JobDsEnvironment queryById(Long id){
        return jobDsEnvironmentMapper.selectByPrimaryKey(id);
    }


    /**
     * 新增数据
     *
     * @param jobDsEnvironment 实例对象
     * @return 实例对象
     */
    @Override
    public JobDsEnvironment insert(JobDsEnvironment jobDsEnvironment){
        jobDsEnvironmentMapper.insert(jobDsEnvironment);
        return jobDsEnvironment;
    }

    /**
     * 更新数据
     *
     * @param jobDsEnvironment 实例对象
     * @return 实例对象
     */
    @Override
    public int update(JobDsEnvironment jobDsEnvironment){
        return customJobDsEnvironmentMapper.update(jobDsEnvironment);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Boolean deleteById(Long id){
        int res = jobDsEnvironmentMapper.deleteByPrimaryKey(id);
        return res>0;
    }

    @Override
    public List<JobDsEnvironment> queryListById(Long id) {

        return customJobDsEnvironmentMapper.selectListById(id);
    }

    @Override
    public JobDsEnvironment queryByDataSourceId(Long id) {

        return customJobDsEnvironmentMapper.selectByDataSourceId(id);
    }

    @Override
    public int selectCountByDataSourceId(Long id) {
        return customJobDsEnvironmentMapper.selectCountByDataSourceId(id);
    }

    @Override
    public List<JobDsEnvironment> selectAllEnv() {
        return jobDsEnvironmentMapper.selectByExample(null);
    }
}
