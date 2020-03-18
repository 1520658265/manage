package com.xunjer.linsen.common.config.config.BaseDao;

import lombok.Data;
import org.apache.tomcat.jni.Error;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author linsen
 * @date 2020/3/17 20:20
 * @tips 明日复明日 明日何其多
 */
@Data
public abstract class BaseDao<T> {

    private Class<T> t ;

    private Field primaryField;

    private String primaryName;

    private String tableName;

    private List<Field> fieldList;

    private List<String> filedNameList;

//    private NamedParameterJdbcTemplate jdbcTemplate;

    public BaseDao(){
        Type superClass = getClass().getGenericSuperclass();
        Type[] actualTypes = ((ParameterizedType) superClass).getActualTypeArguments();
        this.t = (Class<T>) actualTypes[0];
        fieldList = Arrays.asList(this.t.getDeclaredFields());
        Annotation tableNameAnnotation = t.getAnnotation(TableName.class);
        Annotation[] list = t.getAnnotations();
        System.out.println(list.length);
        if(tableNameAnnotation==null){
            throw new RuntimeException("缺少TabName注解");
        }else{
            tableName = ((TableName) tableNameAnnotation).name();
        }
        Field[] fields = t.getDeclaredFields();
        List<Field> list1 = new ArrayList<>(fields.length);
        for(Field field : fields){
            Annotation fieldNameAnnotation = field.getAnnotation(FieldName.class);
            if(fieldNameAnnotation==null){
                throw new RuntimeException("缺少FieldName注解");
            }
            list1.add(field);
            FieldName fieldName = (FieldName) fieldNameAnnotation;
            filedNameList.add(fieldName.value());
            if(fieldName.primary()){
                primaryField = field;
                primaryName = fieldName.value();
            }
        }
        this.fieldList = list1;
    }

    public T find(Integer primary){
        StringBuilder select = new StringBuilder(" SELECT ");
        filedNameList.forEach(s->select.append(s+","));
        select.deleteCharAt(select.length()-1);
        select.append(" FROM ");
        select.append(tableName);
        select.append(" WHERE ");
        select.append(primaryName);
        select.append(" =:"+primary);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(primaryName,primary);
        List<T> list = getJdbcTemplate().query(select.toString(),parameterSource,new BeanPropertyRowMapper<>(t));
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }


    /**
     * 供子类实现,以便实现不同数据库间代码通用
     */
    protected abstract NamedParameterJdbcTemplate getJdbcTemplate();

}
