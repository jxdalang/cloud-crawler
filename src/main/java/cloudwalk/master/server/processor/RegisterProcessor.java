package cloudwalk.master.server.processor;

import cloudwalk.master.server.SlaveManager;
import cloudwalk.master.server.entity.SlaveEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 1333907 on 2/18/16.
 * Processor for register.
 */

public class RegisterProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterProcessor.class);

    public static void process(String data) {
        SlaveEntity entity = new SlaveEntity();

        String[] strings = data.split("&");
        for (String string : strings) {
            String[] twoStrings = string.split("=");
            if (twoStrings.length != 2) {
                continue;
            }
            String key = twoStrings[0];
            String value = twoStrings[1];
            if ("hostname".equals(key)) {
                entity.setHostname(value);
            }
            if ("ip".equals(key)) {
                entity.setIp(value);
            }
            if ("port".equals(key)) {
                int port = -1;
                try {
                    port = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                entity.setPort(port);
            }
            if ("name".equals(key)) {
                entity.setName(value);
            }
        }

        SlaveManager.registerSlave(entity);
    }
}
