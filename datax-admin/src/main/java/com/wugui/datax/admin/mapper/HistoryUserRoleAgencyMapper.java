package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.entity.HistoryUserRoleAgency;
import com.wugui.datax.admin.entity.HistoryUserRoleOffice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Cat
 * @createTime 2023-10-09 9:54
 * @description
 */
public interface HistoryUserRoleAgencyMapper {


    int deleteByPrimaryKey(Integer id);
    int deleteByUserId(String userId);

    int insert(HistoryUserRoleAgency record);

    // 批量insert
    int insertBatch(@Param("list") List<HistoryUserRoleAgency> records);

    int insertSelective(HistoryUserRoleAgency record);

    HistoryUserRoleAgency selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HistoryUserRoleAgency record);

    int updateByPrimaryKey(HistoryUserRoleAgency record);
}
