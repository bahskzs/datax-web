package com.wugui.datax.admin.service.impl;

import com.wugui.datax.admin.entity.JobDsEnvironment;
import com.wugui.datax.admin.mapper.JobDsEnvironmentMapper;
import com.wugui.datax.admin.service.JobDsEnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobDsEnvironmentServiceImpl implements JobDsEnvironmentService {
    @Autowired
    private JobDsEnvironmentMapper jobDsEnvironmentMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public JobDsEnvironment queryById(Long id){
        return jobDsEnvironmentMapper.queryById(id);
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
    public JobDsEnvironment update(JobDsEnvironment jobDsEnvironment){
        jobDsEnvironmentMapper.update(jobDsEnvironment);
        return queryById(jobDsEnvironment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id){
        int total = jobDsEnvironmentMapper.deleteById(id);
        return total > 0;
    }

    @Override
    public List<JobDsEnvironment> queryListById(Long id) {

        return jobDsEnvironmentMapper.selectListById(id);
    }

    @Override
    public JobDsEnvironment queryByDataSourceId(Long id) {
        return jobDsEnvironmentMapper.selectByDataSourceId(id);
    }

    @Override
    public int selectCountByDataSourceId(Long id) {
        return jobDsEnvironmentMapper.selectCountByDataSourceId(id);
    }
}
