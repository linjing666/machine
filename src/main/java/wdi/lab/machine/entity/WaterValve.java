package wdi.lab.machine.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @program: machine
 * @description
 * @author: linjing
 * @create: 2020-01-10 08:50
 **/
@Data
@Document(collation = "WaterValve")
public class WaterValve {
//    private String ID;
    private String DrainOrder;
    private String InletOrder;
    //    private String Sender;
//    private String Tester;
//    private String ProNum;
    private String MachineNum;
    private String JobNum;
    private String RealWaterLevel;
    //    private String NG;
    private String Status;
    private String FullDrain;
    private String HalfDrain;
    private String Count;
    private String CreateTime;
//    private String type;
}
