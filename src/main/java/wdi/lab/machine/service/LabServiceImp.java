package wdi.lab.machine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import wdi.lab.machine.dao.LabMapper;
import wdi.lab.machine.entity.WaterValve;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @program: machine
 * @description
 * @author: linjing
 * @create: 2020-01-10 09:03
 **/
@Service
public class LabServiceImp implements LabSevice {
    @Autowired
    private LabMapper labMapper;
    @Autowired
    private insertMogon insertMogon;
    @Override
    public void getAllInformatin() throws ExecutionException, InterruptedException {
        System.out.println("开始运行" + new Date());
        List<HashMap<String, Object>> mapList = labMapper.selectAll();
        List<Future<String>> futureList=new ArrayList<>();
        List<String> tableNameList=new ArrayList<>();
        long start=System.currentTimeMillis();
        if (!mapList.isEmpty()) {
            for (int i = 0; i < mapList.size(); i++) {
                String tableName = String.valueOf(mapList.get(i).get("machinename")).trim();
//            String jobNum = String.valueOf(mapList.get(i).get("ordernum"));
                String type = String.valueOf(mapList.get(i).get("testtype"));
                String ordername = String.valueOf(mapList.get(i).get("ordername"));
                if (tableNameList.contains(tableName)){
                    continue;
                }
                tableNameList.add(tableName);
                if ("1".equals(type.trim())) {
                    if (ordername.contains("_")) {
                        futureList.add(insertMogon.getMachineInfo(tableName.trim()));
                    }else {

                    }
                }
            }
            while (true){
                if (futureList.isEmpty()){
                    break;
                }
                for (int i = 0; i < futureList.size(); i++) {
                    if (futureList.get(i).isDone()){
//                        System.out.println(futureList.get(i).get());
                        futureList.remove(i);
                    }else {
                        break;
                    }
                }
            }
            System.out.println("任务完成，耗时："+ (System.currentTimeMillis()-start)+"ms");
        }
    }


}
