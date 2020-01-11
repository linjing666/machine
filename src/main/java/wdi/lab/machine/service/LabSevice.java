package wdi.lab.machine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wdi.lab.machine.dao.LabMapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @program: machine
 * @description
 * @author: linjing
 * @create: 2020-01-09 16:59
 **/

public interface LabSevice  {

    void  getAllInformatin() throws ExecutionException, InterruptedException;
}
