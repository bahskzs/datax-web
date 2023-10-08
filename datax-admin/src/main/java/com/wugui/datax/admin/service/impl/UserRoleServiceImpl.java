package com.wugui.datax.admin.service.impl;

import com.wugui.datax.admin.dto.HistoryUserRoleBO;
import com.wugui.datax.admin.dto.HistoryUserRoleDTO;
import com.wugui.datax.admin.dto.UserQueryDTO;
import com.wugui.datax.admin.entity.HistoryUserRole;
import com.wugui.datax.admin.mapper.HistoryUserRoleMapper;
import com.wugui.datax.admin.service.UserRoleService;
import com.wugui.datax.admin.util.CopyUtil;
import com.wugui.datax.admin.util.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Cat
 * @createTime 2023-09-17 22:27
 * @description
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private HistoryUserRoleMapper historyUserRoleMapper;

    @Override
    public List<HistoryUserRoleDTO> list(UserQueryDTO vo) {
        List<HistoryUserRole> list = historyUserRoleMapper.list();
        //TODO 利用vo过滤下list
        List<HistoryUserRoleDTO> res = CopyUtil.copyList(list, HistoryUserRoleDTO.class);
        if (StringUtils.isBlank(vo.getUserName())) {
            return res;
        }
        return res.stream().filter(
                userRoleDTO -> userRoleDTO.getUserInfo().contains(vo.getUserName()
        )).collect(Collectors.toList());

    }

    @Override
    public List<HistoryUserRoleDTO> pageList(int current, int size, String userName) {
        List<HistoryUserRole>  list = historyUserRoleMapper.pageList(userName,(current-1)*size, size);
        List<HistoryUserRoleDTO> res = CopyUtil.copyList(list, HistoryUserRoleDTO.class);
        return res;
    }

    @Override
    public HistoryUserRoleDTO getById(Integer id) {
        HistoryUserRole historyUserRole = historyUserRoleMapper.selectByPrimaryKey(id);
        HistoryUserRoleDTO dto = CopyUtil.copy(historyUserRole, HistoryUserRoleDTO.class);
        return dto;
    }

    @Override
    public Integer create(HistoryUserRoleBO userRoleBO) {


        HistoryUserRole userRole = CopyUtil.copy(userRoleBO, HistoryUserRole.class);
        dealData(userRoleBO, userRole);

        // TODO 单个用户启用状态的记录只能有一条,所以需要判断是否有存在启用状态的记录
        if(StringUtils.equals(userRole.getStatus(),"0")) {
            List<HistoryUserRole> list = historyUserRoleMapper.listByUserId(userRole.getUserid());
            List<HistoryUserRole> collect = list.stream().filter(
                    historyUserRole -> StringUtils.equals(historyUserRole.getStatus(), "0")
            ).collect(Collectors.toList());
            if (collect.size() > 0) {
                throw new IllegalArgumentException("当前已存在启用状态的记录!!");
            }
        }

        return historyUserRoleMapper.insert(userRole);
    }

    private static void dealData(HistoryUserRoleBO userRoleBO, HistoryUserRole userRole) {
        String[] userInfo = userRoleBO.getUserInfo().split("-");
        userRole.setUserid(userInfo[0]);
        userRole.setRegions(String.join(",", userRoleBO.getRegions()));
        userRole.setAgencies(String.join(",", userRoleBO.getAgencies()));
        userRole.setOffices(String.join(",", userRoleBO.getOffices()));
        userRole.setModules(String.join(",", userRoleBO.getModules()));
    }

    @Override
    public Integer update(Integer id, HistoryUserRoleBO userRoleBO) {

        // 确认id存在
        HistoryUserRole historyUserRole = historyUserRoleMapper.selectByPrimaryKey(id);
        if (historyUserRole == null) {
            throw  new IllegalArgumentException("当前记录不存在!!");
        }

        //构造HistoryUserRole对象
        HistoryUserRole userRole = CopyUtil.copy(userRoleBO, HistoryUserRole.class);
        userRole.setId(id);
        // List转Json记录
        dealData(userRoleBO, userRole);

        return historyUserRoleMapper.updateByPrimaryKey(userRole);
    }

    @Override
    public Integer deleteById(Integer id) {
        return historyUserRoleMapper.deleteByPrimaryKey(id);
    }
}
