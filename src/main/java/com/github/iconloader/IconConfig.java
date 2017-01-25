package com.github.iconloader;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class IconConfig {

    private static final String IMAGE_PREFIX = "data:image/png;base64,";
    private static final String ICON_PATH = "icon/icon.png";

    @Autowired
    private ConfigurableEnvironment env;

    @PostConstruct
    public void init() throws IOException {
        Optional<String> icon = getIcon(ICON_PATH);
        if(icon.isPresent()) {
            Properties properties = new Properties();
            properties.put("info.build.icon", icon.get());
            PropertiesPropertySource source = new PropertiesPropertySource("icon", properties);

            MutablePropertySources propertySources = env.getPropertySources();
            propertySources.addFirst(source);
        }
    }

    Optional<String> getIcon(String iconPath) throws IOException {
        InputStream resource = IconConfig.class.getClassLoader().getResourceAsStream(iconPath);
        if (resource == null) {
            log.warn("Could not find icon/icon.png in src/main/resources");
            return Optional.empty();
        }

        byte[] bytes = IOUtils.toByteArray(resource);
        return Optional.of(String.format("%s%s", IMAGE_PREFIX, Base64.getEncoder().encodeToString(bytes)));
    }
}
