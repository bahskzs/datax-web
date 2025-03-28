package com.wugui.datax.admin.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 构建json dto
 *
 * @author jingwk
 * @ClassName DataXJsonDto
 * @Version 2.1.2
 * @since 2020/05/05 17:15
 */
@Data
@Builder
public class CusDataXBatchJsonBuildDTO implements Serializable {

    private Long readerDatasourceId;

    private List<String> readerTables;

    private Long writerDatasourceId;

    private List<String> writerTables;

    private int templateId;

    private RdbmsReaderDto rdbmsReader;

    private RdbmsWriterDto rdbmsWriter;
    /*
     * 2021-10-14
     * bahskzs
     * 是否需要数据源:需要则往job_desc中添加数据源的名称
     * */
    private boolean requireDSName;
}
