package com.wugui.datax.admin.service;

import com.wugui.datax.admin.dto.*;

import java.util.List;

/**
 * @author Cat
 * @createTime 2023-09-17 22:14
 * @description
 */
public interface UserRoleService {
    List<HistoryUserRoleDTO> list(UserQueryDTO vo);

    HistoryUserRoleDTO getById(Integer id);

    Integer create(HistoryUserRoleBO userRoleBO);

    Integer update(Integer id, HistoryUserRoleBO userRoleBO);

    Integer deleteById(Integer id);
}
