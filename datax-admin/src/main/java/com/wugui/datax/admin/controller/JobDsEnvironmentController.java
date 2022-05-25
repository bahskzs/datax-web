package com.wugui.datax.admin.controller;



import com.baomidou.mybatisplus.extension.api.R;
import com.wugui.datax.admin.entity.JobDsEnvironment;
import com.wugui.datax.admin.service.JobDsEnvironmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/job/env")
@Api(tags = "数据源环境接口")
public class JobDsEnvironmentController {
    @Resource
    private JobDsEnvironmentService jobDsEnvironmentService;

    @GetMapping("/selectListById")
    @ApiOperation("通过主键查询数据")
    public List<JobDsEnvironment> selectListById(Long id) {
        return jobDsEnvironmentService.queryListById(id);
    }


    @GetMapping("/selectByDataSourceId")
    @ApiOperation("通过数据源主键查询单条数据")
    public JobDsEnvironment selectByDataSourceId(Long id) {
        return jobDsEnvironmentService.queryByDataSourceId(id);
    }


    /**
     * 新增数据
     *
     * @param jobDsEnvironment 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public R<JobDsEnvironment> add(JobDsEnvironment jobDsEnvironment){
        int datasourceId=jobDsEnvironment.getDatasourceId();
        Date date=new Date();
        //SimpleDateFormat dataFormat = new SimpleDateFormat("yyyymmdd");
        jobDsEnvironment.setCreateDate(date);
        jobDsEnvironment.setUpdateDate(date);
        int countNum=jobDsEnvironmentService.selectCountByDataSourceId((long) datasourceId);
        if(countNum>=1){
           return  R.failed("该数据源存在状态等于1的数据！！");
        }
        return R.ok(jobDsEnvironmentService.insert(jobDsEnvironment));
    }

    /**
     * 更新数据
     *
     * @param jobDsEnvironment 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public R<JobDsEnvironment> edit(JobDsEnvironment jobDsEnvironment){
        Date date=new Date();
        jobDsEnvironment.setUpdateDate(date);

        String datasourceStatus=jobDsEnvironment.getStatus();
        if(datasourceStatus.equals("1")){
            int datasourceId=jobDsEnvironment.getDatasourceId();
            int countNum=jobDsEnvironmentService.selectCountByDataSourceId((long) datasourceId);
            if(countNum>=1){
                return R.failed("该数据源存在状态等于1的数据！！");
            }
        }

        return R.ok(jobDsEnvironmentService.update(jobDsEnvironment));
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id){
        return ResponseEntity.ok(jobDsEnvironmentService.deleteById(id));

    }



}
