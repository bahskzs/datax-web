package com.wugui.datax.admin.config;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author bahsk
 * @createTime 2022-05-18 17:22
 * @description
 */
@Configuration
@Data
public class HadoopConfig {

    @Value("${hadoop.mode:false}")
    private String mode;

    @Value("${hadoop.nameNodes:localhost}")
    private String nameNodes;

    @Value("${hadoop.nameService:localhost}")
    private String nameService;

    @Value("${hadoop.address:localhost}")
    private String address;

    private static final String PROXY = "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider";

    protected Map<String, Object> values = new HashMap<>();

    public Map<String, Object> getParameters() {

        if (StringUtils.equals("HA", mode)) {

            values.put("dfs.nameservices", nameService);
            values.put("dfs.ha.namenodes." + nameService, nameNodes);
            values.put("dfs.client.failover.proxy.provider." + nameService, PROXY);

            String[] nodeArr = address.split(",");
            String[] nameNodeArr = nameNodes.split(",");

            for (int i = 0; i < nodeArr.length; i++) {
                String node = nodeArr[i];
                String nameNode = nameNodeArr[i];
                values.put("dfs.namenode.rpc-address." + nameService + "." + nameNode, node);
            }
        }
        return values;
    }

}
