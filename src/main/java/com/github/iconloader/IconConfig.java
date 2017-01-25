package com.github.iconloader;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Properties;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class IconConfig {

    private static final String IMAGE_PREFIX = "data:image/png;base64,";
    private static final String ICON_PATH = "icon/icon.png";

    @Autowired
    private ConfigurableEnvironment env;

    @PostConstruct
    public void init() throws IOException {
        String icon = getIcon(ICON_PATH);

        Properties properties = new Properties();
        properties.put("info.build.icon", icon);
        PropertiesPropertySource source = new PropertiesPropertySource("icon", properties);

        MutablePropertySources propertySources = env.getPropertySources();
        propertySources.addFirst(source);
    }

    String getIcon(String iconPath) throws IOException {
        InputStream resource = IconConfig.class.getClassLoader().getResourceAsStream(iconPath);
        if(resource == null) {
            throw new FileNotFoundException("Could not find icon/icon.png in src/main/resources");
        }

        byte[] bytes = IOUtils.toByteArray(resource);
        return String.format("%s%s", IMAGE_PREFIX, Base64.getEncoder().encodeToString(bytes));
    }
}
