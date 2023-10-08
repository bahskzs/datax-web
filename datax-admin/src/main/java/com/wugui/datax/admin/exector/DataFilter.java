package com.wugui.datax.admin.exector;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Cat
 * @createTime 2023-10-07 20:06
 * @description
 */
public class DataFilter {

    public static List<String> filterData(List<String> dataList, String name, String code, String area) {
        return dataList.stream()
                .filter(data -> {
                    String[] dataParts = data.split(":");
                    return dataParts[1].contains(name) &&
                            (code == null || dataParts[2].contains(code)) &&
                            (area == null || dataParts[0].contains(area));
                })
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> dataList = Arrays.asList("350000:张三:7650:18045044879",
                "450000:李四:7650:18045044880",
                "351020:张二三:7650:18045044879",
                "451000:张四:17650:18045044880"
                );
        List<String> filteredData = filterData(dataList, "四", "", "");
        filteredData.forEach(System.out::println);
    }
}
