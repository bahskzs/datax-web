package com.wugui.datax.admin.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.wugui.datax.admin.dto.CommonResp;
import com.wugui.datax.admin.dto.ReportCreateReq;
import com.wugui.datax.admin.dto.ReportQueryResp;
import com.wugui.datax.admin.entity.AreaList;
import com.wugui.datax.admin.entity.ReportModule;
import com.wugui.datax.admin.service.AreaListService;
import com.wugui.datax.admin.service.ReportModuleService;
import com.wugui.datax.admin.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author bahsk
 * @createTime 2023-09-07 17:04
 * @description 菜单配置接口
 */
@RestController
@RequestMapping("/api/menu")
@Api(tags = "菜单配置接口")
public class MenuController extends BaseController{

    @Resource
    private ReportService reportService;

    @Resource
    private ReportModuleService reportModuleService;

    @Resource
    private AreaListService areaListService;

    @GetMapping("/list")
    @ApiOperation("全部报表列表")
    public R<List<ReportQueryResp>> getAllReports(){
        List<ReportQueryResp> list = reportService.getAllReports();
        return success(list);
    }

    @GetMapping("/modules")
    @ApiOperation("模块列表")
    public R<List<ReportModule>> getAllModules(){
        List<ReportModule> list = reportModuleService.getAllModules();
        return success(list);
    }

    @GetMapping("/area")
    @ApiOperation("区划")
    public R<List<AreaList>> getArea(){
        List<AreaList> list = areaListService.getArea();
        return success(list);
    }

    @PostMapping("/create")
    @ApiOperation("添加报表")
    public R<Integer> createReport(@RequestBody ReportCreateReq report) {
        return success(this.reportService.insertSelective(report));
    }

    @PutMapping("/update/{id}")
    @ApiOperation("修改报表")
    public R<Integer> updateReport(@RequestBody ReportCreateReq report, @PathVariable Integer id) {
        return success(this.reportService.updateByPrimaryKey(report, id));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除报表")
    public R<Integer> deleteReport(@PathVariable Integer id) {
        return success(this.reportService.deleteByPrimaryKey(id));
    }
}