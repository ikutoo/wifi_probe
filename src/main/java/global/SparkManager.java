package global;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SQLContext;

/**
 * Created by Administrator on 2017-05-25.
 */
public class SparkManager {
    private static SparkManager mInstance = new SparkManager();

    private SparkConf sconf;
    private SparkContext sparkContext;
    private SQLContext sqlContext;

    public static SparkManager getInstance() {
        return mInstance;
    }

    private SparkManager() {
        sconf = new SparkConf().setAppName("wifiProbe").setMaster("local[4]").set("spark.testing.memory", "2048000000");
        sparkContext = new SparkContext(sconf);
        sqlContext = new SQLContext(sparkContext);
    }

    public SparkContext getSparkContext() {
        return sparkContext;
    }

    public SQLContext getSqlContext() {
        return sqlContext;
    }
}
