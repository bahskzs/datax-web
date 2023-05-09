package com.wugui.datax.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.wugui.datax.admin.config.HadoopConfig;
import com.wugui.datax.admin.dto.DataXBatchJsonBuildDto;
import com.wugui.datax.admin.dto.DataXJsonBuildDto;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.entity.JobDsEnvironment;
import com.wugui.datax.admin.entity.JobInfo;
import com.wugui.datax.admin.entity.JobTemplate;
import com.wugui.datax.admin.mapper.JobInfoMapper;
import com.wugui.datax.admin.mapper.JobTemplateMapper;
import com.wugui.datax.admin.service.DatasourceQueryService;
import com.wugui.datax.admin.service.DataxJsonService;
import com.wugui.datax.admin.service.JobDatasourceService;
import com.wugui.datax.admin.service.JobDsEnvironmentService;
import com.wugui.datax.admin.tool.datax.DataxJsonHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * com.wugui.datax json构建实现类
 *
 * @author jingwk
 * @ClassName DataxJsonServiceImpl
 * @Version 2.0
 * @since 2020/01/11 17:15
 */
@Service
@Slf4j
public class DataxJsonServiceImpl implements DataxJsonService {

    @Autowired
    private JobDatasourceService jobJdbcDatasourceService;

    @Resource
    private HadoopConfig hadoopConfig;

    @Resource
    private JobDsEnvironmentService jobDsEnvironmentService;

    @Override
    public String buildJobJson(DataXJsonBuildDto dataXJsonBuildDto) {
        DataxJsonHelper dataxJsonHelper = new DataxJsonHelper();
        // reader
        JobDatasource readerDatasource = jobJdbcDatasourceService.getById(dataXJsonBuildDto.getReaderDatasourceId());
        // reader plugin init



        if(jobDsEnvironmentService.queryByDataSourceId(readerDatasource.getId()) != null && "none".equals(hadoopConfig.getMode())) {
            dataxJsonHelper.initReader(dataXJsonBuildDto, readerDatasource, hadoopConfig, jobDsEnvironmentService.queryByDataSourceId(readerDatasource.getId()));
        } else if("HA".equals(hadoopConfig.getMode()) && "hive".equals(readerDatasource.getDatasource())) {
            log.info("# buildJobJson -----  hadoopConfig.getMode():{}",hadoopConfig.getMode());
            dataxJsonHelper.initReader(dataXJsonBuildDto, readerDatasource, hadoopConfig, jobDsEnvironmentService.queryByDataSourceId(readerDatasource.getId()));
        } else {
            dataxJsonHelper.initReader(dataXJsonBuildDto, readerDatasource);
        }

        dataxJsonHelper.initReader(dataXJsonBuildDto, readerDatasource);
        JobDatasource writerDatasource = jobJdbcDatasourceService.getById(dataXJsonBuildDto.getWriterDatasourceId());

        if(jobDsEnvironmentService.queryByDataSourceId(writerDatasource.getId()) != null && "none".equals(hadoopConfig.getMode())) {
            dataxJsonHelper.initWriter(dataXJsonBuildDto, writerDatasource, hadoopConfig, jobDsEnvironmentService.queryByDataSourceId(writerDatasource.getId()));
        } else if("HA".equals(hadoopConfig.getMode()) && "hive".equals(writerDatasource.getDatasource())) {
            dataxJsonHelper.initWriter(dataXJsonBuildDto, writerDatasource, hadoopConfig, jobDsEnvironmentService.queryByDataSourceId(writerDatasource.getId()));
        }
        else {
            dataxJsonHelper.initWriter(dataXJsonBuildDto, writerDatasource);
        }

        return JSON.toJSONString(dataxJsonHelper.buildJob());
    }
}
