package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.entity.HistoryUserRoleOffice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@author Cat
 *@createTime 2023-10-09 9:54
 *@description
 */
public interface HistoryUserRoleOfficeMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByUserId(String userId);

    int insert(HistoryUserRoleOffice record);

    // 批量insert
    int insertBatch(@Param("list") List<HistoryUserRoleOffice> records);

    int insertSelective(HistoryUserRoleOffice record);

    HistoryUserRoleOffice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HistoryUserRoleOffice record);

    int updateByPrimaryKey(HistoryUserRoleOffice record);
}
