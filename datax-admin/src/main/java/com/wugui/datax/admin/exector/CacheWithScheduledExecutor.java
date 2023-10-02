package com.wugui.datax.admin.exector;

import com.wugui.datax.admin.dto.YTHResponse;
import com.wugui.datax.admin.dto.YTHUserDTO;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Cat
 * @createTime 2023-09-21 15:40
 * @description
 */
@Component
@Slf4j
public class CacheWithScheduledExecutor {

    @Resource
    private RestTemplate restTemplate;

    private final String OUTER_SYS_URL = "http://10.100.59.193:48000/mp-b-user-service/";


    private final ConcurrentHashMap<String, YTHUserDTO> cache = new ConcurrentHashMap<>();
    // 使用ScheduledExecutorService来定时更新缓存
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public CacheWithScheduledExecutor() {
        // 定时任务在初始化后立即启动，然后每小时执行一次
        executorService.scheduleAtFixedRate(this::refreshCache, 0, 1, TimeUnit.DAYS);
    }

    private void refreshCache() {
        // 这是更新缓存的逻辑，你应该替换为你自己的数据采集逻辑
        List<String> areaCodes = getAllAreaCodes();
        ExecutorService executor = Executors.newFixedThreadPool(10); // 创建一个线程池
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        areaCodes.forEach(areaCode -> {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                YTHResponse response = restTemplate.getForObject(OUTER_SYS_URL + "/v2/users?province=" + areaCode, YTHResponse.class);
                store(response);
            }, executor);
            futures.add(future);
        });
        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get(1, TimeUnit.HOURS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.error("Error occurred while refreshing cache", e);
        }
        log.info("缓存刷新完成,key个数 : {}", cache.keySet().size());
        executor.shutdown(); // 关闭线程池
    }



    private List<String> getAllAreaCodes() {
        //TODO 后续编码刷新得改
        String[] ALL_AREA_CODES = {"359900000","350000000","350199000","350100000","350102000","350103000","350104000","350105000","350111000","350112000","350121000","350122000","350123000","350124000","350125000","350181000","350128000","350195000","350196000","350197000","350399000","350300000","350302000","350303000","350304000","350305000","350322000","350396000","350397000","350499000","350400000","350404000","350405000","350421000","350423000","350424000","350425000","350426000","350428000","350429000","350430000","350481000","350599000","350500000","350502000","350503000","350504000","350505000","350521000","350524000","350525000","350526000","350581000","350582000","350583000","350597000","350699000","350600000","350602000","350603000","350604000","350605000","350622000","350623000","350624000","350626000","350627000","350628000","350629000","350692000","350693000","350694000","350695000","350696000","350697000","350799000","350700000","350702000","350703000","350721000","350722000","350723000","350724000","350725000","350781000","350782000","350783000","350899000","350800000","350802000","350803000","350821000","350823000","350824000","350825000","350881000","350897000","350999000","350900000","350902000","350921000","350922000","350923000","350924000","350925000","350926000","350981000","350982000","350997000","350999099","350997002","350997003","350997004","350997005","350997006","350997007"};
        return Arrays.asList(ALL_AREA_CODES);
    }

    public YTHUserDTO getUser(String areaCode) {
        // 通过键来获取缓存数据
        return cache.get(areaCode);
    }

    public int getCachedSize() {
        return cache.size();
    }

    //TODO 模糊查询获取User
    public List<YTHUserDTO> getUsers(String areaCode,String code,String name) {
        // key = areacode:code:name
        if(cache.isEmpty()) {
            refreshCache();
        }
        // name为""或者null,都置为-1
        Optional<String> optionalName = Optional.ofNullable(name);
        name = optionalName.orElse("-1");
        code = StringUtils.isBlank(code) ? "-1" : code;
        areaCode = StringUtils.isBlank(areaCode) ? "-1" : areaCode;

        List<YTHUserDTO> results = new ArrayList<>();
        for (Map.Entry<String, YTHUserDTO> entry : cache.entrySet()) {

            String key = entry.getKey();

            String finalName = name;

            // 构造条件
            Predicate<String> filter = (k -> k.contains(finalName)) ;

            if(! StringUtils.equals("-1",areaCode) ) {
                String finalAreaCode = areaCode;
                filter = filter.and(k -> k.contains(finalAreaCode));
            }
            if(! StringUtils.equals("-1",code) ) {
                String finalCode = code;
                filter =  filter.and(k -> k.contains(finalCode));

            }


            List<String> res = Arrays.asList(key).stream().filter(
                    filter
            ).collect(Collectors.toList());

            // res非空,那么当前key可用
            if(res != null && res.size() == 1 ) {
                results.add(entry.getValue());
            }

        }
        return results;
    }


    // 手动更新缓存
    public void manualRefresh(String areaCode) {
        // 这是更新缓存的逻辑，你应该替换为你自己的数据采集逻辑
        YTHResponse response = restTemplate.getForObject(OUTER_SYS_URL + "/v2/users?province="+ areaCode , YTHResponse.class);
        store(response);
    }

    /**
     * 遍历请求数据,并存入缓存
     * @param response
     */
    private void store(YTHResponse response) {
        if(response.getData() == null){
            return;
        }
        for (YTHUserDTO dto : response.getData()) {
            String CACHE_KEY = String.format("%s:%s:%s", dto.getProvince(), dto.getName(), dto.getPhonenumber());
            cache.put(CACHE_KEY, dto);
        }
    }
}
