package conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigurationManager {

    private static Properties prop;
    static {
        InputStream in = ConfigurationManager.class.getClassLoader().getResourceAsStream("db.properties");
        prop= new Properties();
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return prop.getProperty(key);
    }

    public static int getInteger(String key){
        return Integer.valueOf(prop.getProperty(key));
    }

    public static boolean getBoolean(String key){
        return Boolean.valueOf(prop.getProperty(key));
    }

}
