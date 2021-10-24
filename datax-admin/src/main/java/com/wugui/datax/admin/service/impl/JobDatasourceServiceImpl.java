package com.wugui.datax.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wugui.datax.admin.dto.DatasourceDTO;
import com.wugui.datax.admin.dto.DatasourceGroupDTO;
import com.wugui.datax.admin.dto.DatasourceRespDTO;
import com.wugui.datax.admin.mapper.JobDatasourceMapper;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.service.JobDatasourceService;
import com.wugui.datax.admin.tool.query.BaseQueryTool;
import com.wugui.datax.admin.tool.query.HBaseQueryTool;
import com.wugui.datax.admin.tool.query.MongoDBQueryTool;
import com.wugui.datax.admin.tool.query.QueryToolFactory;
import com.wugui.datax.admin.util.AESUtil;
import com.wugui.datax.admin.util.CopyUtil;
import com.wugui.datax.admin.util.JdbcConstants;
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
@Transactional(readOnly = true)
public class JobDatasourceServiceImpl extends ServiceImpl<JobDatasourceMapper, JobDatasource> implements JobDatasourceService {

    @Resource
    private JobDatasourceMapper datasourceMapper;

    @Override
    public Boolean  dataSourceTest(JobDatasource jobDatasource) throws IOException {
        if (JdbcConstants.HBASE.equals(jobDatasource.getDatasource())) {
            return new HBaseQueryTool(jobDatasource).dataSourceTest();
        }
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
    public DatasourceGroupDTO selectDatasourceByWords(String words, Integer year) {

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
        DatasourceGroupDTO build = DatasourceGroupDTO.builder()
                .datasourceDTOList(datasourceDTOS)
                .datasourceRespDTOList(datasourceRespDTOS)
                .build();

        return build;
    }

}
