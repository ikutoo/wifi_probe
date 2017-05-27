package constants;

/**
 * Created by sky on 2017/3/11.
 */
public class Constants {
    public static final String JDBC_DRIVER = "driver";

    //创建指定连接数量的数据库连接池
    public static final String DBC_DATASOURCE_SIZE = "datasource_size";
    public static final String MYSQL_LOCAL = "mysql_local";
    //local
    public static final String JDBC_URL = "jdbc_url";
    public static final String JDBC_USER = "jdbc_user";
    public static final String JDBC_PASSWORD = "jdbc_password";
    //remote
    public static final String JDBC_URL_PROD = "jdbc_url_prod";
    public static final String JDBC_USER_PROD = "jdbc_user_prod";
    public static final String JDBC_PASSWORD_PROD = "jdbc_password_prod";
    //临时表
    public static final String TABLE_USER = "table_user";
    public static final String  TABLE_EQUIPMENT =   "table_equipment";
    //运行参数
    public static final String PROCCESS_INTERVAL = "proccess_interval";
    public static final String PULL_INTERVAL = "pull_interval";
    public static final String INSTORE_DISTANCE = "instore_distance";
}
