package com.wugui.datax.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wugui.datax.admin.DataXAdminApplication;
import com.wugui.datax.admin.dto.JobDsEnvironmentDTO;
import com.wugui.datax.admin.dto.PageResp;
import com.wugui.datax.admin.entity.JobDsEnvironment;
import com.wugui.datax.admin.entity.JobDsEnvironmentExample;
import com.wugui.datax.admin.mapper.CustomJobDsEnvironmentMapper;
import com.wugui.datax.admin.mapper.JobDsEnvironmentMapper;
import com.wugui.datax.admin.service.JobDsEnvironmentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobDsEnvironmentServiceImpl implements JobDsEnvironmentService {
    @Resource
    private JobDsEnvironmentMapper jobDsEnvironmentMapper;
    private static Logger logger = LoggerFactory.getLogger(DataXAdminApplication.class);
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
    public int deleteById(Long id){
        return jobDsEnvironmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public JobDsEnvironment queryListById(Long id) {

        return customJobDsEnvironmentMapper.selectListById(id);
    }

    @Override
    public JobDsEnvironment queryByDataSourceId(Long id) {

        return customJobDsEnvironmentMapper.selectByDataSourceId(id);
    }

    @Override
    public int selectCountById(JobDsEnvironment jobDsEnvironment) {
        return customJobDsEnvironmentMapper.selectCountById(jobDsEnvironment);
    }


    @Override
    public List<JobDsEnvironment> selectAllEnv() {
        return jobDsEnvironmentMapper.selectByExample(null);
    }

    @Override
    public PageResp<JobDsEnvironment> list(JobDsEnvironmentDTO jobDsEnvironmentDTO) {
        JobDsEnvironmentExample jobDsEnvironmentExample = new JobDsEnvironmentExample();
        JobDsEnvironmentExample.Criteria criteria=jobDsEnvironmentExample.createCriteria();

        logger.info("分页数：{}", jobDsEnvironmentDTO.getPage());
        logger.info("条数：{}", jobDsEnvironmentDTO.getSize());

        PageHelper.startPage(1, 3);
        List<JobDsEnvironment> jobDsEnvironmentList = jobDsEnvironmentMapper.selectByExample(jobDsEnvironmentExample);

        PageInfo<JobDsEnvironment> pageInfo = new PageInfo<>(jobDsEnvironmentList);
        pageInfo.setPageSize(3);
        pageInfo.setPageNum(1);
        logger.info("总行数：{}", pageInfo.getTotal());
        logger.info("总页数：{}", pageInfo.getPages());

        //List<JobDsEnvironment> list = CopyUtil.copyList(jobDsEnvironmentList, JobDsEnvironment.class);

        PageResp<JobDsEnvironment> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(jobDsEnvironmentList);

        return pageResp;
    }
}
