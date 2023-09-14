package com.wugui.datax.admin.controller;


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
public class MenuController {

    @Resource
    private ReportService reportService;

    @Resource
    private ReportModuleService reportModuleService;

    @Resource
    private AreaListService areaListService;

    @GetMapping("/list")
    @ApiOperation("全部报表列表")
    public CommonResp getAllReports(){
        CommonResp<List<ReportQueryResp>> resp = new CommonResp<>();
        List<ReportQueryResp> list = reportService.getAllReports();
        resp.setSuccess(true);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/modules")
    @ApiOperation("模块列表")
    public CommonResp getAllModules(){
        CommonResp<List<ReportModule>> resp = new CommonResp<>();
        List<ReportModule> list = reportModuleService.getAllModules();
        resp.setSuccess(true);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/area")
    @ApiOperation("区划")
    public CommonResp getArea(){
        CommonResp<List<AreaList>> resp = new CommonResp<>();
        List<AreaList> list = areaListService.getArea();
        resp.setSuccess(true);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/create")
    @ApiOperation("添加报表")
    public CommonResp createReport(@RequestBody ReportCreateReq report) {
        CommonResp resp = new CommonResp<>();
        reportService.insertSelective(report);
        resp.setSuccess(true);
        return resp;
    }

    @PostMapping("/update/{id}")
    @ApiOperation("修改报表")
    public CommonResp updateReport(@RequestBody ReportCreateReq report, @PathVariable Integer id) {
        CommonResp resp = new CommonResp<>();
        reportService.updateByPrimaryKey(report, id);
        resp.setSuccess(true);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除报表")
    public CommonResp deleteReport(@PathVariable Integer id) {
        CommonResp resp = new CommonResp<>();
        reportService.deleteByPrimaryKey(id);
        resp.setSuccess(true);
        return resp;
    }
}