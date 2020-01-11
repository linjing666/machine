package wdi.lab.machine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wdi.lab.machine.dao.LabMapper;
import wdi.lab.machine.entity.WaterValve;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @program: machine
 * @description
 * @author: linjing
 * @create: 2020-01-10 14:55
 **/
@Service
public class insertMogonImpl implements insertMogon {
    @Autowired
    private LabMapper labMapper;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    @Async
    public Future<String> getMachineInfo(String tableName){
        List<WaterValve> machineList = labMapper.selectMachine(tableName);
        for (int i = 0; i < machineList.size(); i++) {
//            System.out.println(machineList.get(i));
            insertAndDel(machineList.get(i));
        }
        return new AsyncResult<>(tableName+"任务完成");
    }

    @Transactional
    public synchronized void insertAndDel(WaterValve waterValve) {
        mongoTemplate.insert(waterValve, "WaterValve");
        labMapper.deleteInfo(waterValve.getMachineNum(), waterValve.getDrainOrder(), waterValve.getInletOrder(), waterValve.getCount());
    }
}
