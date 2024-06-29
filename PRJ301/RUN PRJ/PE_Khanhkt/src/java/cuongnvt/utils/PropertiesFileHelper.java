package cuongnvt.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;

public class PropertiesFileHelper {
      public static Properties getProperties(ServletContext context, String fileRelativePath){
        InputStream input = context.getResourceAsStream(fileRelativePath);
        Properties prop = null;
        try {
            prop = new Properties();
            prop.load(input);
            
        } catch (IOException ex) {
        }
        return prop;
    }
}
