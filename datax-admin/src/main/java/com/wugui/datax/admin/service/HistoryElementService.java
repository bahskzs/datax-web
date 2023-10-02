package com.wugui.datax.admin.service;

import com.wugui.datax.admin.dto.ElementDTO;
import com.wugui.datax.admin.dto.YTHUserVO;
import com.wugui.datax.admin.entity.AreaList;
import com.wugui.datax.admin.entity.ReportModule;

import java.util.List;

/**
 * @author Cat
 * @createTime 2023-09-17 22:14
 * @description
 */
public interface HistoryElementService {
    List<ElementDTO> getRegions(String areaCode,String year);

    List<ElementDTO> getAgencies(List<String> areaCodes);

    List<ElementDTO> getOffices(String areaCode, String year);

    List<YTHUserVO> getUsers(String areaCode,String code,String name);


    public List<ElementDTO> getOffice(List<String> areaCodes);
}
