package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.entity.HistoryUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@author Cat
 *@createTime 2023-09-17 22:19
 *@description
 */
public interface HistoryUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HistoryUserRole record);

    int insertSelective(HistoryUserRole record);

    HistoryUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HistoryUserRole record);

    int updateByPrimaryKey(HistoryUserRole record);

    List<HistoryUserRole> list();


    List<HistoryUserRole> listByUserId(@Param("userId") String userId);

    List<HistoryUserRole> pageList(@Param("userId") String userId,@Param("offset") int offset,
                                    @Param("pagesize") int pagesize);
}
