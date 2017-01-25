package com.github.iconloader.integration

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ActiveProfiles('integration')
@ContextConfiguration
@SpringBootTest(classes = TestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IconIntegrationSpec extends Specification {

    @Value('${info.build.icon}')
    private String icon

    @Value('${eureka.instance.metadataMap.icon}')
    private String eurekaIcon

    def "Load icon"() {
        when:
        ''

        then:
        icon.startsWith('data:image/png;base64')
        eurekaIcon.startsWith('data:image/png;base64')
    }
}
