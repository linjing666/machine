package wdi.lab.machine.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wdi.lab.machine.entity.WaterValve;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * @program: machine
 * @description
 * @author: linjing
 * @create: 2020-01-09 16:54
 **/
@Mapper
public interface LabMapper {
    List<HashMap<String,Object>> selectAll();
    List<WaterValve> selectMachine(@Param("tableName")String tableName);
    void deleteInfo( @Param("tableName")String tableName, @Param("DrainOrder")String DrainOrder,
                     @Param("InletOrder")String InletOrder,@Param("count")String count);
}
