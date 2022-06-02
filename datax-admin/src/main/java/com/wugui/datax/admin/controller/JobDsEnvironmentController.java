package com.wugui.datax.admin.controller;



import com.baomidou.mybatisplus.extension.api.R;
import com.wugui.datax.admin.dto.CommonResp;
import com.wugui.datax.admin.dto.JobDsEnvironmentDTO;
import com.wugui.datax.admin.dto.JobResourceDTO;
import com.wugui.datax.admin.dto.PageResp;
import com.wugui.datax.admin.entity.JobDsEnvironment;
import com.wugui.datax.admin.entity.JobResource;
import com.wugui.datax.admin.service.JobDsEnvironmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/job/env")
@Api(tags = "数据源环境接口")
public class JobDsEnvironmentController extends BaseController{
    @Resource
    private JobDsEnvironmentService jobDsEnvironmentService;

    @GetMapping("/all")
    @ApiOperation("查询所有数据源对应的环境列表")
    public R<List<JobDsEnvironment>> selectListById(@RequestParam(required = true) Long id) {
        return success(jobDsEnvironmentService.selectAllEnv());
    }

    @GetMapping("/{id}")
    @ApiOperation("查询id对应的数据源环境信息")
    public R<JobDsEnvironment> selectListById(@PathVariable Serializable id) {
        long fid = Long.parseLong((String) id);
        return success(jobDsEnvironmentService.queryListById((Long) fid));
    }


    @GetMapping("/select")
    @ApiOperation("查询对应数据源id的环境信息")
    public R<JobDsEnvironment> selectByDataSourceId(@RequestParam(required = true) Long datasourceid) {
        return success(jobDsEnvironmentService.queryByDataSourceId(datasourceid));
    }

    /**
     * 新增数据
     *
     * @param entity 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public R<JobDsEnvironment> add(@RequestBody JobDsEnvironment entity){
        int datasourceId=entity.getDatasourceId();
        long id=entity.getId();
        Date date=new Date();
        entity.setCreateDate(date);
        entity.setUpdateDate(date);
        int countNum=jobDsEnvironmentService.selectCountById(entity);
        if(countNum>=1){
           return  R.failed("该数据源存在状态等于1的数据！！");
        }
        return R.ok(jobDsEnvironmentService.insert(entity));
    }

    /**
     * 更新数据
     *
     * @param entity 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public R<Integer> edit(@RequestBody JobDsEnvironment entity){
        Date date=new Date();
        entity.setUpdateDate(date);
        String datasourceStatus=entity.getStatus();
        if(datasourceStatus.equals("1")){
            int datasourceId=entity.getDatasourceId();
            long id=entity.getId();
            int countNum=jobDsEnvironmentService.selectCountById(entity);
            if(countNum>=1){
                return R.failed("该数据源存在状态等于1的数据！！");
            }
        }

        return R.ok(jobDsEnvironmentService.update(entity));
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    public R<Integer> deleteById(@RequestParam("id") Long id){
        return R.ok(jobDsEnvironmentService.deleteById(id));
    }


    @ApiOperation("根据条件分页查询")
    @GetMapping("/list")
    public CommonResp list(JobDsEnvironmentDTO jobDsEnvironmentDTO) {
        CommonResp<PageResp<JobDsEnvironment>> listCommonResp = new CommonResp<>();
    /*    jobDsEnvironmentDTO.setPage(1);
        jobDsEnvironmentDTO.setSize(5);*/
        PageResp<JobDsEnvironment> list = jobDsEnvironmentService.list(jobDsEnvironmentDTO);
        listCommonResp.setContent(list);
        return listCommonResp;
    }


}
