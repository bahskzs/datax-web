package com.wugui.datax.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wugui.datax.admin.entity.JobDsEnvironment;
import com.wugui.datax.admin.entity.JobInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    JobDsEnvironment selectListById(Long id);
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
     * @param jobDsEnvironment
     * @return
     */
    int selectCountById(JobDsEnvironment jobDsEnvironment);


    int update(JobDsEnvironment jobDsEnvironment);


}
