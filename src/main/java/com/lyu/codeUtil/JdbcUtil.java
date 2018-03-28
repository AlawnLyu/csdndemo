package com.lyu.codeUtil;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class JdbcUtil {
    private static String properties_file_uri = null;
    public static Properties configs;

    public static void setPropertiesURL(String uri) {
        properties_file_uri = uri;
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            configs = new Properties();
            if (StringUtils.isEmpty(properties_file_uri)) {
                properties_file_uri = "/genericCoder.properties";
            }
            String path = GenCodeUtil.class.getResource("/").getPath();
            InputStream in = new FileInputStream(new File(path + properties_file_uri));
            configs.load(in);
            Class.forName(configs.getProperty("spring.datasource.driverClassName"));
            Properties properties = new Properties();
            properties.put("user", configs.getProperty("spring.datasource.username"));
            properties.put("password", configs.getProperty("spring.datasource.password"));
            properties.put("remarksReporting", "true");//想要获取数据库结构中的注释，这个值是重点
            return DriverManager.getConnection(configs.getProperty("spring.datasource.url"), properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取表结构
     *
     * @param tableName
     * @return
     */
    public static TableModel getTableStructure(String catalog, String tableName) {
        List<ColumnModel> columnModelList = new ArrayList<>();
        List<ColumnModel> primaryKeyColumns = new ArrayList<>();
        Set<String> imports = new HashSet<>();
        try {
            //TODO:表相关
            //ResultSet tableSet = metaData.getTables(null,"%",tableName,new String[]{"TABLE"});
            //TODO:字段相关
            DatabaseMetaData dbMeta = getConnection().getMetaData();
            List<String> primaryKeys = getPrimaryKeys(dbMeta, catalog, tableName);
            ResultSet columnSet = dbMeta.getColumns(catalog, "%", tableName, "%");
            ColumnModel columnModel = null;
            while (columnSet.next()) {
                columnModel = new ColumnModel();
                columnModel.setColumnName(columnSet.getString("COLUMN_NAME"));
                columnModel.setColumnSize(columnSet.getInt("COLUMN_SIZE"));
                columnModel.setDataType(columnSet.getString("DATA_TYPE"));
                columnModel.setRemarks(columnSet.getString("REMARKS"));
                columnModel.setTypeName(columnSet.getString("TYPE_NAME"));
                columnModel.setAutoIncrement(columnSet.getString("IS_AUTOINCREMENT").equalsIgnoreCase("yes"));
                columnModel.setPrimaryKey(justicPrimaryKey(columnModel.getColumnName(), primaryKeys));
                //String columnClassName = ColumnTypeEnum.getColumnTypeEnumByDBType(columnModel.getTypeName());
                String columnClassName = sqlType2JavaType(columnModel.getTypeName());
                String imp = getImportByJavaType(columnClassName);
                if (StringUtils.isNotEmpty(imp)) {
                    imports.add(imp);
                }
                String fieldName = getFieldName(columnModel.getColumnName());
                String filedType = null;
                try {
                    if (StringUtils.isNotEmpty(columnClassName)) {
                        filedType = Class.forName(columnClassName).getSimpleName();
                    } else {
                        throw new RuntimeException();
                    }
                } catch (ClassNotFoundException e) {
                    filedType = columnClassName;
                }
                columnModel.setFieldName(fieldName);
                columnModel.setColumnClassName(columnClassName);
                columnModel.setFieldType(filedType);
                columnModelList.add(columnModel);
                if (columnModel.isPrimaryKey()) {
                    primaryKeyColumns.add(columnModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TableModel table = new TableModel();
        table.setColumns(columnModelList);
        table.setPrimaryKeyColumns(primaryKeyColumns);
        table.setImports(imports);
        table.setTableName(tableName);
        return table;
    }

    /**
     * 将数据库字段转换成bean属性
     *
     * @param columnName
     * @return
     */
    private static String getFieldName(String columnName) {
        char[] columnCharArr = columnName.toCharArray();
        StringBuffer sb = new StringBuffer();
        int ad = -1;
        for (int i = 0; i < columnCharArr.length; i++) {
            char cur = columnCharArr[i];
            if (cur == '_') {
                ad = i;
            } else {
                if ((ad + 1) == i && ad != -1) {
                    sb.append(Character.toUpperCase(cur));
                } else {
                    sb.append(cur);
                }
                ad = -1;
            }
        }
        return sb.toString();
    }

    /**
     * 获取表主键
     *
     * @param dbMeta
     * @param tableName
     * @return
     * @throws SQLException
     */
    private static List<String> getPrimaryKeys(DatabaseMetaData dbMeta, String catalog, String tableName) throws
            SQLException {
        ResultSet pkRSet = dbMeta.getPrimaryKeys(catalog, null, tableName);
        List<String> primaryKeys = new ArrayList<>();
        while (pkRSet.next()) {
            primaryKeys.add(pkRSet.getObject("COLUMN_NAME").toString());
        }
        return primaryKeys;
    }

    /**
     * 判断列是否为主键列
     *
     * @param columnName
     * @param primaryKeys
     * @return
     */
    private static boolean justicPrimaryKey(String columnName, List<String> primaryKeys) {
        for (String key : primaryKeys) {
            if (key.equals(columnName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取列的数据类型
     *
     * @param sqlType
     * @return
     */
    private static String sqlType2JavaType(String sqlType) {
        if (sqlType.equalsIgnoreCase("bit")) {
            return "boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "short";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "int";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        } else if (sqlType.equalsIgnoreCase("timestamp")) {
            return "Timestamp";
        }
        return "String";
    }

    /**
     * 根据数据类型获取需要引入的类
     *
     * @param javaType
     * @return
     */
    private static String getImportByJavaType(String javaType) {
        switch (javaType) {
            case "Date":
                return "java.util.Date";
            case "Timestamp":
                return "java.sql.Timestamp";
            case "Blod":
                return "java.sql.Blod";
        }
        return null;
    }
}
