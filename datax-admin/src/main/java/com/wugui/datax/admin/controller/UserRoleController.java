package com.wugui.datax.admin.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.wugui.datax.admin.dto.*;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author bahsk
 * @createTime 2023-09-17 17:04
 * @description 历史数据用户权限定制接口
 */
@RestController
@RequestMapping("/api/history/auth")
@Api(tags = "用户权限接口")
public class UserRoleController extends BaseController{



    @Resource
    private UserRoleService userRoleService;


    @Resource
    private HistoryElementService elementService;

    @GetMapping("/list")
    @ApiOperation("用户配置列表")
    public R<List<HistoryUserRoleDTO>> list(UserQueryDTO vo){
        List<HistoryUserRoleDTO> list = userRoleService.list(vo);
        return success(list);
    }


    @GetMapping("/pageList")
    @ApiOperation("用户配置列表")
    public R<List<HistoryUserRoleDTO>> pageList(@RequestParam(required = false, defaultValue = "0") int current,
                                                @RequestParam(required = false, defaultValue = "10") int size,
                                                @RequestParam(required = false) String userName
                                                ){
        List<HistoryUserRoleDTO> list = userRoleService.pageList(current,size,userName);
        return success(list);
    }


    @GetMapping("/{id}")
    @ApiOperation("用户配置明细")
    public R<HistoryUserRoleDTO> getById(@PathVariable Integer id){
        HistoryUserRoleDTO detail = userRoleService.getById(id);
        return success(detail);
    }

    @PostMapping("/create")
    @ApiOperation("用户配置创建")
    public R<Integer> create(@RequestBody HistoryUserRoleBO userRoleBO){
        Integer flag = userRoleService.create(userRoleBO);
        return success(flag);
    }

    @PutMapping("/update/{id}")
    @ApiOperation("用户配置更新")
    public R<Integer> update(@PathVariable String id,@RequestBody HistoryUserRoleBO userRoleBO){
        Integer flag = userRoleService.update(Integer.valueOf(id),userRoleBO);
        return success(flag);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("用户配置删除")
    public R<Integer> create(@PathVariable Integer id){
        Integer flag = userRoleService.deleteById(id);
        return success(flag);
    }


    @GetMapping("/regions")
    @ApiOperation("区划列表")
    public R<List<ElementDTO>> getRegions(@RequestParam(required = false) String areaCode,@RequestParam(required = false) String year){
        List<ElementDTO> list = elementService.getRegions(areaCode, year);
        return success(list);
    }

    @GetMapping("/agencies")
    @ApiOperation("单位列表")
    public R<List<ElementDTO>> getAgencies(@RequestParam(required = false) List<String> areaCodes){
        List<ElementDTO> list = elementService.getAgencies(areaCodes);
        return success(list);
    }


    @GetMapping("/offices")
    @ApiOperation("处室列表")
    public R<List<ElementDTO>> getOffices(@RequestParam(required = false) List<String> areaCodes){
        List<ElementDTO> list = elementService.getOffice(areaCodes);
        return success(list);
    }


    @GetMapping("/users")
    @ApiOperation("用户列表")
    public R<List<YTHUserVO>> getUsers(String areaCode,@RequestParam( required = false ) String code,@RequestParam( required = false ) String name){
        List<YTHUserVO> list = elementService.getUsers(areaCode,code,name);
        return success(list);
    }


}
