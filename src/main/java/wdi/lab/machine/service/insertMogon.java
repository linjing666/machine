package wdi.lab.machine.service;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

/**
 * @program: machine
 * @description
 * @author: linjing
 * @create: 2020-01-10 14:54
 **/
public interface insertMogon {

    @Async
    Future<String> getMachineInfo(String tableName);
}
