package com.xunjer.linsen.common.config.BaseDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author linsen
 * @date 2020/3/18 8:22
 * @tips 明日复明日 明日何其多
 */
public abstract class AbstractBaseDao<T> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractBaseDao.class);
    private String tableName = null;
    private Class<T> cls = null;
    private String updateSqlStr = null;
    private List<Field> updateFieldList = Collections.emptyList();
    private String insertSqlStr = null;
    private List<Field> insertFieldList = Collections.emptyList();
    private String primaryKeyString = null;
    private List<Field> allFieldList = Collections.emptyList();

    public AbstractBaseDao() {
        Type superClass = getClass().getGenericSuperclass();
        Type[] actualTypes = ((ParameterizedType) superClass).getActualTypeArguments();
        this.cls = (Class<T>) actualTypes[0];
        TableName tableNameClass = this.cls.getDeclaredAnnotation(TableName.class);
        this.tableName = tableNameClass != null ? tableNameClass.name() : this.cls.getName();
        this.updateSqlStr = this.updateSqlStr();
        this.insertSqlStr = this.insertSqlStr();
        this.allFieldList = this.getAllFieldList();
    }


    /**
     * 单个更新方法(根据主键进行更新)
     *
     * @param t 待更新数据
     * @return 若t为null返回0, 否则返回受影响行数
     */
    public int updateSingle(T t) {
        if (this.updateSqlStr == null) {
            throw new IllegalStateException("不支持更新操作");
        }
        if (t == null) {
            LOGGER.error("参数不能为空");
            return 0;
        }
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        try {
            for (Field field : this.updateFieldList) {
                FieldName fieldName = field.getDeclaredAnnotation(FieldName.class);
                if (fieldName == null) {
                    continue;
                }
                boolean access = field.isAccessible();
                if (!access) {
                    field.setAccessible(true);
                }
                parameterSource.addValue(fieldName.value(), field.get(t));
                if (!access) {
                    field.setAccessible(false);
                }
                if (!access) {
                    field.setAccessible(false);
                }
                if (!access) {
                    field.setAccessible(false);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return this.getJdbcTemplate().update(updateSqlStr, parameterSource);
    }

    /**
     * 单个导入
     *
     * @param t 待导入数据(为null时抛异常)
     * @return 当受影响的行数大于0是, 返回插入生成的主键值, 否则返回-1
     */
    public Integer insertSingle(T t) {
        if (this.insertSqlStr == null) {
            throw new IllegalStateException("不支持导入操作");
        }
        if (t == null) {
            LOGGER.error("参数不能为空");
            return 0;
        }
        StringBuilder builder = new StringBuilder(insertSqlStr).append(" VALUES (");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        try {
            for (int i = 0; i < this.insertFieldList.size(); i++) {
                if (i != 0) {
                    builder.append(",");
                }
                Field field = this.insertFieldList.get(i);
                builder.append(":").append(field.getName());
                //设置为true才能访问变量的值(不是通过get方式获得值的)
                field.setAccessible(true);
                parameterSource.addValue(field.getName(), field.get(t));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        builder.append(")");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int effectCount = this.getJdbcTemplate().update(builder.toString(), parameterSource, keyHolder);
        if (effectCount > 0) {
            return keyHolder.getKey().intValue();
        }
        return null;
    }

    /**
     * 批量导入
     *
     * @param tList 待导入的数据
     * @return 受影响行数 当tList为null或空集合时,返回0
     */
    public int insertMulti(List<T> tList) {
        if (this.insertSqlStr == null) {
            throw new IllegalStateException("不支持导入操作");
        }
        if (CollectionUtils.isEmpty(tList)) {
            LOGGER.error("参数不能为空");
            return 0;
        }
        StringBuilder builder = new StringBuilder(insertSqlStr).append(" VALUES ");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        try {
            for (int i = 0; i < tList.size(); i++) {
                if (i != 0) {
                    builder.append(",");
                }
                T t = tList.get(i);
                builder.append("(");
                for (int j = 0; j < this.insertFieldList.size(); j++) {
                    if (j != 0) {
                        builder.append(",");
                    }
                    Field field = this.insertFieldList.get(j);
                    builder.append(":").append(field.getName()).append("_").append(i);
                    boolean access = field.isAccessible();
                    if (!access) {
                        //设置为true才能访问私有变量
                        field.setAccessible(true);
                    }
                    parameterSource.addValue(field.getName() + "_" + i, field.get(t));
                    if (!access) {
                        field.setAccessible(false);
                    }
                }
                builder.append(")");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalStateException(e.getMessage());
        }
        return this.getJdbcTemplate().update(builder.toString(), parameterSource);
    }

    /**
     * 根据主键删除表数据
     *
     * @param primaryKeyList 需要删除的数据的主键
     * @return 受影响行数
     */
    public int deleteMulti(List<Integer> primaryKeyList) {
        if (CollectionUtils.isEmpty(primaryKeyList)) {
            LOGGER.error("参数不能为空");
            return 0;
        }
        if (this.primaryKeyString == null) {
            throw new IllegalArgumentException("无法获得主键名");
        }
        String sql = "DELETE FROM " + this.tableName + " WHERE " + this.primaryKeyString + " IN (" + StringUtils.join(primaryKeyList, ",") + ")";
        return this.getJdbcTemplate().update(sql, new EmptySqlParameterSource());
    }

    /**
     * 单个删除
     *
     * @param primaryKey 主键值
     * @return 主键值为null返回0, 否则返回受影响行数
     */
    public Boolean deleteSingle(Integer primaryKey) {
        if (primaryKey == null) {
            LOGGER.error("参数不能为空");
            return false;
        }
        return this.deleteMulti(Collections.singletonList(primaryKey)) == 1;
    }


    /**
     * 根据主键获得数据
     *
     * @param primaryKeyList 主键集合
     * @return 参数为空时返回Collections.emptyList, 否则返回查到的结果集
     */
    public List<T> selectMulti(List<Integer> primaryKeyList) {
        if (CollectionUtils.isEmpty(primaryKeyList)) {
            LOGGER.error("参数不能为空");
            return Collections.emptyList();
        }
        String sql = "SELECT * FROM " + this.tableName + " WHERE " + this.primaryKeyString + " IN (" + StringUtils.join(primaryKeyList, ",") + ")";
        List<T> resultList = new LinkedList<>();
        this.getJdbcTemplate().query(sql, new EmptySqlParameterSource(), rs -> {
            try {
                T t = AbstractBaseDao.this.cls.newInstance();
                for (Field field : AbstractBaseDao.this.allFieldList) {
                    FieldName fieldName = field.getDeclaredAnnotation(FieldName.class);
                    //设置为true才能访问私有变量
                    field.setAccessible(true);
                    if (field.getType() == String.class) {
                        field.set(t, rs.getString(fieldName.value()));
                    } else if (field.getType() == int.class || field.getType() == Integer.class) {
                        field.set(t, rs.getInt(fieldName.value()));
                    } else if (field.getType() == Date.class) {
                        field.set(t, rs.getTimestamp(fieldName.value()));
                    } else if (field.getType() == float.class || field.getType() == Float.class) {
                        field.set(t, rs.getFloat(fieldName.value()));
                    } else if (field.getType() == double.class || field.getType() == Double.class) {
                        field.set(t, rs.getDouble(fieldName.value()));
                    } else if (field.getType() == long.class || field.getType() == Long.class) {
                        field.set(t, rs.getLong(fieldName.value()));
                    } else {
                        throw new IllegalStateException("暂不支持的类型");
                    }
                    field.setAccessible(false);
                }
                resultList.add(t);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return resultList;
    }

    /**
     * 单个查询
     *
     * @param primaryKey 主键值
     * @return 主键值为null或未查到结果时返回null
     */
    public T selectSingle(Integer primaryKey) {
        if (primaryKey == null) {
            LOGGER.error("参数不能为空");
            return null;
        }
        List<T> list = this.selectMulti(Collections.singletonList(primaryKey));
        return list.size() > 0 ? list.get(0) : null;
    }

    /**
     * 供子类实现,以便实现不同数据库间代码通用
     */
    protected abstract NamedParameterJdbcTemplate getJdbcTemplate();


    private String updateSqlStr() {
        if (this.tableName == null) {
            throw new IllegalArgumentException("表名不能为空,缺少数据库实体注解！");
        }
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ").append(this.tableName).append(" SET ");
        Field[] fields = this.cls.getDeclaredFields();
        if (fields.length == 0) {
            throw new IllegalArgumentException("无法访问类里的成员变量");
        }
        String primaryKey = null;
        List<Field> updateFiledList = new ArrayList<>(fields.length);
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            FieldName fieldName = field.getDeclaredAnnotation(FieldName.class);
            if (fieldName == null) {
                continue;
            }
            updateFiledList.add(field);
            if (fieldName.primary()) {
                if (primaryKey != null) {
                    throw new IllegalArgumentException("一个表里不能有多个主键");
                }
                primaryKey = fieldName.value();
                continue;
            }
            builder.append(" `").append(fieldName.value()).append("`=:").append(fieldName.value());
            if (primaryKey != null && i != fields.length - 1) {
                builder.append(",");
            }
        }
        if (primaryKey == null) {
            throw new IllegalArgumentException("缺失主键注解");
        }
        builder.append(" WHERE ").append(primaryKey).append("=:").append(primaryKey);
        this.updateFieldList = updateFiledList;
        this.primaryKeyString = primaryKey;
        return builder.toString();
    }

    private String insertSqlStr() {
        if (this.tableName == null) {
            throw new IllegalArgumentException("表名不能为空");
        }
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(this.tableName).append("(");
        Field[] fields = this.cls.getDeclaredFields();
        if (fields.length == 0) {
            throw new IllegalArgumentException("无法访问类里的成员变量");
        }
        boolean hasPrimary = false;
        //以顺序存储类里的字段,保证insert语句执行时,列名和值一一对应
        List<Field> fieldNameList = new ArrayList<>(fields.length);
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            FieldName fieldName = field.getDeclaredAnnotation(FieldName.class);
            if (fieldName == null) {
                continue;
            }
            if (fieldName.primary()) {
                if (hasPrimary) {
                    throw new IllegalArgumentException("一个表里不能有多个主键");
                }
                hasPrimary = true;
                continue;
            }
            builder.append("`").append(fieldName.value()).append("`");
            fieldNameList.add(field);
            if (hasPrimary && i != fields.length - 1) {
                builder.append(",");
            }
        }
        if (!hasPrimary) {
            throw new IllegalArgumentException("缺失主键注解");
        }
        builder.append(") ");
        this.insertFieldList = fieldNameList;
        return builder.toString();
    }

    private List<Field> getAllFieldList() {
        Field[] fields = this.cls.getDeclaredFields();
        if (fields.length == 0) {
            throw new IllegalArgumentException("无法访问类里的成员变量");
        }
        List<Field> fieldNameList = new ArrayList<>(fields.length);
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            FieldName fieldName = field.getDeclaredAnnotation(FieldName.class);
            if (fieldName == null) {
                continue;
            }
            fieldNameList.add(field);
        }
        return fieldNameList;
    }
}
