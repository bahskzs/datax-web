package com.wugui.datax.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.wugui.datax.admin.core.util.LocalCacheUtil;
import com.wugui.datax.admin.dto.DatasourceGroupRespDTO;
import com.wugui.datax.admin.dto.JobDatasourceRespDTO;
import com.wugui.datax.admin.entity.FieldMapping;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.service.FieldMappingService;
import com.wugui.datax.admin.service.JobDatasourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * jdbc数据源配置控制器层
 *
 * @author zhouhongfa@gz-yibo.com
 * @version v1.0
 * @since 2019-07-30
 */
@RestController
@RequestMapping("/api/fieldsMapping")
@Api(tags = "字段类型映射管理接口")
public class FieldMappingController extends BaseController {
    /**
     * 服务对象
     */
    @Autowired
    private FieldMappingService fieldMappingService;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @GetMapping
    @ApiOperation("分页查询所有数据")
    @ApiImplicitParams(
            {@ApiImplicitParam(paramType = "query", dataType = "String", name = "current", value = "当前页", defaultValue = "1", required = true),
                    @ApiImplicitParam(paramType = "query", dataType = "String", name = "size", value = "一页大小", defaultValue = "20", required = true),
                    @ApiImplicitParam(paramType = "query", dataType = "Boolean", name = "ifCount", value = "是否查询总数", defaultValue = "true"),
                    @ApiImplicitParam(paramType = "query", dataType = "String", name = "ascs", value = "升序字段，多个用逗号分隔"),
                    @ApiImplicitParam(paramType = "query", dataType = "String", name = "descs", value = "降序字段，多个用逗号分隔")
            })
    public R<IPage<FieldMapping>> selectAll() {
        BaseForm form = new BaseForm();
        int size = fieldMappingService.selectAllFieldMapping().size();
        QueryWrapper<FieldMapping> query = (QueryWrapper<FieldMapping>) form.pageQueryWrapperCustom(form.getParameters(), new QueryWrapper<FieldMapping>());
        return success(fieldMappingService.page(form.getPlusPagingQueryEntity(true,size), query));


    }

    /**
     * 获取所有数据源
     * @return
     */
    @ApiOperation("获取所有数据源")
    @GetMapping("/all")
    public R<List<FieldMapping>> selectAllFieldMapping() {
        return success(this.fieldMappingService.selectAllFieldMapping());
    }




    /**
     * 新增数据
     *
     * @param entity 实体对象
     * @return 新增结果
     */
    @ApiOperation("新增数据")
    @PostMapping
    public R<Boolean> insert(@RequestBody  FieldMapping entity) {
        return success(this.fieldMappingService.save(entity));
    }



    /**
     * 修改数据
     *
     * @param entity 实体对象
     * @return 修改结果
     */
    @PutMapping
    @ApiOperation("修改数据")
    public R<Boolean> update(@RequestBody FieldMapping entity) {
        FieldMapping d = fieldMappingService.getById(entity.getId());
        return success(this.fieldMappingService.updateById(entity));
    }



    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @ApiOperation("删除数据")
    public R<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.fieldMappingService.removeByIds(idList));
    }






}
