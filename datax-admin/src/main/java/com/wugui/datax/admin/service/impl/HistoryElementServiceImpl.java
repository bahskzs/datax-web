package com.wugui.datax.admin.service.impl;

import com.wugui.datax.admin.dto.ElementDTO;
import com.wugui.datax.admin.dto.YTHResponse;
import com.wugui.datax.admin.dto.YTHUserDTO;
import com.wugui.datax.admin.dto.YTHUserVO;
import com.wugui.datax.admin.exector.CacheWithScheduledExecutor;
import com.wugui.datax.admin.mapper.AgencyMapper;
import com.wugui.datax.admin.mapper.OfficeMapper;
import com.wugui.datax.admin.mapper.RegionMapper;
import com.wugui.datax.admin.service.HistoryElementService;
import com.wugui.datax.admin.util.CopyUtil;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Cat
 * @createTime 2023-09-19 17:43
 * @description
 */
@Service
public class HistoryElementServiceImpl implements HistoryElementService {

    @Resource
    private OfficeMapper officeMapper;

    @Resource
    private AgencyMapper agencyMapper;

    @Resource
    private RegionMapper regionMapper;


    @Resource
    private CacheWithScheduledExecutor cache;


    @Override
    public List<ElementDTO> getRegions(String areaCode,String year) {
        return regionMapper.findByYear(areaCode, year);
    }

    @Override
    public List<ElementDTO> getAgencies(List<String> areaCodes) {
        List<ElementDTO> elementDTOS = agencyMapper.findByYear(areaCodes);
        return agencyMapper.findByYear(areaCodes);
    }

    @Override
    public List<ElementDTO> getOffices(String areaCode, String year) {
        return officeMapper.findByYear(areaCode, year);
    }


    public List<ElementDTO> getOffice(List<String> areaCodes) {
        return officeMapper.findByArea(areaCodes);
    }

    @Override
    public List<YTHUserVO> getUsers(String areaCode,String code,String name) {
         // 从缓存中获取
        // 若cache中没有，则从模拟数据中取数
        List<YTHUserDTO> list =  null;
        if (cache.getCachedSize() == 0 ) {
            list = getMockUsers(areaCode,code,name);
        } else  {
            list = cache.getUsers(areaCode,code,name);
        }
        List<YTHUserVO> res = CopyUtil.copyList(list, YTHUserVO.class);
        return res;
    }

    private List<YTHUserDTO> getMockUsers(String areaCode, String code, String name) {
        List<YTHUserDTO> list = new ArrayList<>();
        // 模拟数据,并写入list

        YTHUserDTO user = new YTHUserDTO();
        user.setGuid("E7387B617B4A4296A7202C50D822AC3A");
        user.setCode("350300");
        user.setName("莆田市管理员");
        user.setCreater("7102B98BF68A37E92CFE8CEE55FF438B");
        user.setCreatetime("2021-02-22 23:28:54");
        user.setLastuser("E7387B617B4A4296A7202C50D822AC3A");
        user.setLasttime("2023-09-15 16:56:11");
        user.setProvince("350300000");
        user.setYear(2023);
        user.setAdmintype("3");
        user.setUsertype("1");
        user.setStatus("1");
        user.setLoginmode("2");
        user.setCreatedate("2021-02-22 23:28:54");
        user.setUpdatedate("2023-09-15 16:56:11");
        user.setPassword("");
        user.setAgency("BBEF265CE731B113E050640A782005BC");
        user.setIdcode("12345678901122332v");
        user.setPhonenumber("18866350001");
        user.setDivision("BBEF265CE731B113E050640A782005BC");
        user.setRemark("");
        user.setInitialpassword("9FA6BDEE48CC881D08BB34D52106FF5B9716D22A26A3CD5EBB00930E74D8C7D3");
        user.setOrgcode("01");
        user.setOrgguid("BBEF265CE731B113E050640A782005BC");
        user.setOrgname("局领导");
        user.setAdmdiv("9D75220C7E5142FFA11B52E4C391555F");
        list.add(user);
        YTHUserDTO user2 = new YTHUserDTO();
        user2.setGuid("2E2394D3B6604A9E9B5A33E38F09C8BD");
        user2.setCode("13808598008");
        user2.setName("余志生");
        user2.setCreater("E7387B617B4A4296A7202C50D822AC3A");
        user2.setCreatetime("2021-05-21 13:19:07");
        user2.setLastuser("E7387B617B4A4296A7202C50D822AC3A");
        user2.setLasttime("2021-07-11 00:38:35");
        user2.setProvince("350300000");
        user2.setYear(2021);
        user2.setAdmintype("3");
        user2.setUsertype("1");
        user2.setStatus("1");
        user2.setLoginmode("2");
        user2.setCreatedate("2021-05-21 13:19:07");
        user2.setUpdatedate("2021-07-11 00:38:35");
        user2.setPassword("");
        user2.setAgency("BBEF265CE731B113E050640A782005BC");
        user2.setIdcode("350302196507200033");
        user2.setPhonenumber("13808598008");
        user2.setEmail("");
        user2.setDivision("BBEF265CE731B113E050640A782005BC");
        user2.setRemark("");
        user2.setInitialpassword("1");
        user2.setOrgcode("01");
        user2.setOrgguid("BBEF265CE731B113E050640A782005BC");
        user2.setOrgname("局领导");
        user2.setAdmdiv("9D75220C7E5142FFA11B52E4C391555F");
        list.add(user2);
        return list;
    }
}
