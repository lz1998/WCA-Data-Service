package xin.lz1998.wcads.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

@Service
public class DataImportUtil {
    private static EntityManager entityManager;
    private static Logger logger= LoggerFactory.getLogger(DataImportUtil.class);

    @Autowired
    public DataImportUtil(EntityManager entityManager) {
        DataImportUtil.entityManager=entityManager;
    }

    public static void importData(String filepath, JpaRepository repository, Class entityClass)  {

        try {
            Reader in = new FileReader(filepath);
            CSVParser parse = CSVFormat.newFormat('\t').withFirstRecordAsHeader().parse(in);
            List<String> headerNames=parse.getHeaderNames();
            Iterator<CSVRecord> iterator = parse.iterator();
            Method[] methods=entityClass.getMethods();
            int count=0;
            while (iterator.hasNext()){
                if(count++%10==0){
                    // 解决OOM问题
                    entityManager.flush();
                    entityManager.clear();
                    logger.debug("clear");
                }
                CSVRecord record = iterator.next();
                Object entity=entityClass.newInstance();
                for(String headerName:headerNames){
                    for(Method setMethod :methods){
                        if(setMethod.getName().toLowerCase().equals(("set"+headerName).toLowerCase())){
                            Object value=record.get(headerName);
                            Class<?> paramClass = setMethod.getParameterTypes()[0];
                            if(!paramClass.equals(String.class)){
                                Method strToObj= paramClass.getMethod("valueOf",String.class);
                                value =strToObj.invoke(paramClass, value);
                            }
                            setMethod.invoke(entity,value);
                        }
                    }
                }
                logger.info(entity.toString());
                repository.save(entity);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
//    public static void importData(String filepath, JpaRepository repository, Class entityClass) throws FileNotFoundException {
//        repository.deleteAllInBatch();
//        CsvToBean csvToBean = new CsvToBeanBuilder(new FileReader(filepath))
//                .withSeparator('\t').withType(entityClass).build();
////        Iterator iterator = csvToBean.iterator();
//        System.out.println(entityClass.getName());
//        repository.saveAll(csvToBean);
//
//
//    }

//    public static void importData(String filepath, JpaRepository repository, Class entityClass) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//
//        Csv csv=new Csv();
//
//        csv.setFieldSeparatorRead('\t');
//        ResultSet rs = csv.read(filepath,null,null);
//        System.out.println("删除");
//        System.out.println(repository.count());
//        repository.deleteAllInBatch();
//        while (rs.next()) {
//            //读取结果转换成entity
//            Object entity =  rs2Entity(rs,entityClass);
//            System.out.println(entity);
//            //存入数据库
//            repository.save(entity);
//        }
//        rs.close();
//
//    }
//    public static Object rs2Entity(ResultSet rs,Class entityClass) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
//        ResultSetMetaData meta = rs.getMetaData();
//        Method[] methods= entityClass.getMethods();
//        Object entity=entityClass.newInstance();
//        for (int i = 0; i < meta.getColumnCount(); i++) {
//            String colName=meta.getColumnLabel(i + 1);
//            for(Method setMethod :methods){
//                if(("set"+colName).toLowerCase().equals(setMethod.getName().toLowerCase())){
//                    Object value =rs.getString(i + 1);
//                    Class<?> paramClass = setMethod.getParameterTypes()[0];
//                    if(!paramClass.equals(String.class)){
//                        // 如果所需类型不是字符串，转换成所需类型
//                        Method strToObj= paramClass.getMethod("valueOf",String.class);
//                        value =strToObj.invoke(paramClass, value);
//                    }
//                    setMethod.invoke(entity, value);
//                    break;
//                }
//            }
//        }
//        return entity;
//    }
}
