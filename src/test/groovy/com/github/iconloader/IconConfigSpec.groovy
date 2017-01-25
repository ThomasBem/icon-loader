package com.github.iconloader

import spock.lang.Specification

class IconConfigSpec extends Specification {
    private IconConfig iconConfig

    void setup() {
        iconConfig = new IconConfig()
    }

    def "Load icon"() {
        when:
        def icon = iconConfig.getIcon("icon/icon.png")

        then:
        icon.length() > 0
    }

    def "Throw FileNotFoundExepection when no icon found"() {
        when:
        iconConfig.getIcon("icorn/icon.png")

        then:
        thrown(FileNotFoundException)
    }
}
