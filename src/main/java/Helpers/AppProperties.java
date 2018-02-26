package Helpers;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class AppProperties {
    private static Properties props = null;

    public static Properties getProps() {
        if (Objects.isNull(props)) {
            props = new Properties();
            try {
                props.load(Properties.class.getResourceAsStream("/application.properties"));
            } catch (IOException ex) {
                System.exit(1);
            }
        }
        return props;
    }
}
