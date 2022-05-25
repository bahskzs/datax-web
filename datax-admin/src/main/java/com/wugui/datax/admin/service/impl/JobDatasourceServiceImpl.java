package com.wugui.datax.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.core.util.LocalCacheUtil;
import com.wugui.datax.admin.dto.DatasourceDTO;
import com.wugui.datax.admin.dto.DatasourceGroupRespDTO;
import com.wugui.datax.admin.dto.DatasourceRespDTO;
import com.wugui.datax.admin.dto.JobDatasourceRespDTO;
import com.wugui.datax.admin.mapper.JobDatasourceMapper;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.service.JobDatasourceService;
import com.wugui.datax.admin.tool.query.BaseQueryTool;
//import com.wugui.datax.admin.tool.query.HBaseQueryTool;
import com.wugui.datax.admin.tool.query.MongoDBQueryTool;
import com.wugui.datax.admin.tool.query.QueryToolFactory;
import com.wugui.datax.admin.util.AESUtil;
import com.wugui.datax.admin.util.CopyUtil;
import com.wugui.datax.admin.util.JdbcConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jingwk on 2020/01/30
 */
@Service
//@Transactional(readOnly = true)
public class JobDatasourceServiceImpl extends ServiceImpl<JobDatasourceMapper, JobDatasource> implements JobDatasourceService {

    @Resource
    private JobDatasourceMapper datasourceMapper;

    @Override
    public Boolean  dataSourceTest(JobDatasource jobDatasource) throws IOException {
//        if (JdbcConstants.HBASE.equals(jobDatasource.getDatasource())) {
//            return new HBaseQueryTool(jobDatasource).dataSourceTest();
//        }
        String userName = AESUtil.decrypt(jobDatasource.getJdbcUsername());
        //  判断账密是否为密文
        if (userName == null) {
            jobDatasource.setJdbcUsername(AESUtil.encrypt(jobDatasource.getJdbcUsername()));
        }
        String pwd = AESUtil.decrypt(jobDatasource.getJdbcPassword());
        if (pwd == null) {
            jobDatasource.setJdbcPassword(AESUtil.encrypt(jobDatasource.getJdbcPassword()));
        }
        if (JdbcConstants.MONGODB.equals(jobDatasource.getDatasource())) {
            return new MongoDBQueryTool(jobDatasource).dataSourceTest(jobDatasource.getDatabaseName());
        }
        BaseQueryTool queryTool = QueryToolFactory.getByDbType(jobDatasource);
        return queryTool.dataSourceTest();
    }

    @Override
    public int update(JobDatasource datasource) {
        return datasourceMapper.update(datasource);
    }

    @Override
    public List<JobDatasource> selectAllDatasource() {
        return datasourceMapper.selectList(null);
    }

    /**
     * @param words
     * @param year
     * @author: bahsk
     * @date: 2021/10/23 16:53
     * @description: 项目定制接口, 传入参数  构造source,target
     * @params:
     * @return:
     */
    @Override
    public DatasourceGroupRespDTO selectDatasourceByWords(String words, Integer year) {

        QueryWrapper<JobDatasource> sourceQueryWrapper = new QueryWrapper<>();
        QueryWrapper<JobDatasource> targetQueryWrapper = new QueryWrapper<>();

        sourceQueryWrapper.select("id","datasource_name")
                .like("datasource_name", words)
                .like("datasource_name", year)
                .like("datasource_name","生产")
                .orderByAsc("datasource_name");

        targetQueryWrapper.select("id","datasource_name")
                .like("datasource_name", words)
                .like("datasource_name", year)
                .like("datasource_name","贴源")
                .orderByAsc("datasource_name");

        List<JobDatasource> sourceList = this.datasourceMapper.selectList(sourceQueryWrapper);
        List<JobDatasource> targetList = this.datasourceMapper.selectList(targetQueryWrapper);

        List<DatasourceRespDTO> datasourceRespDTOS = new ArrayList<>();

        for (int i = 0; i < sourceList.size(); i++) {
            JobDatasource source = sourceList.get(i);
            JobDatasource target = targetList.get(i);

            DatasourceRespDTO datasourceRespDTO = DatasourceRespDTO.builder()
                    .source(source.getId().toString())
                    .sourceName(source.getDatasourceName())
                    .targetSource(target.getId().toString())
                    .targetSourceName(target.getDatasourceName())
                    .build();
            datasourceRespDTOS.add(datasourceRespDTO);
        }
        List<DatasourceDTO> datasourceDTOS = CopyUtil.copyList(datasourceRespDTOS, DatasourceDTO.class);
        DatasourceGroupRespDTO build = DatasourceGroupRespDTO.builder()
                .datasourceDTOList(datasourceDTOS)
                .datasourceRespDTOList(datasourceRespDTOS)
                .build();

        return build;
    }

    /**
     * @param jobDatasourceRespDTOList
     * @author: bahsk
     * @date: 2021-11-01 10:24
     * @description: [项目定制]批量修改数据源
     * @params:
     * @return:
     */
    @Override
    public Boolean updateBatch(List<JobDatasourceRespDTO> jobDatasourceRespDTOList) {

        for(JobDatasourceRespDTO entity : jobDatasourceRespDTOList) {
            JobDatasource d = this.getById(entity.getId());
            if(StringUtils.isNotBlank(d.getDatasourceName())) {
                // 1.清除缓存
                LocalCacheUtil.remove(d.getDatasourceName());
            }

            // 2.批量修改
            if (null != d.getJdbcUsername() && entity.getJdbcUsername().equals(d.getJdbcUsername())) {
                entity.setJdbcUsername(null);
            }
            if (null != entity.getJdbcPassword() && entity.getJdbcPassword().equals(d.getJdbcPassword())) {
                entity.setJdbcPassword(null);
            }
            JobDatasource copy = CopyUtil.copy(entity, JobDatasource.class);
            this.updateById(copy);
        }
        return true;
    }



}
