package wdi.lab.machine;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wdi.lab.machine.dao.LabMapper;
import wdi.lab.machine.service.LabSevice;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
class MachineApplicationTests {
    @Autowired
    private LabSevice labSevice;
    @Test
    void contextLoads() {
        try {
            labSevice.getAllInformatin();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
