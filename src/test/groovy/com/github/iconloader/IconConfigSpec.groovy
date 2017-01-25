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
        icon.get().length() > 0
    }

    def "Empty response when no icon found"() {
        when:
        def icon = iconConfig.getIcon("icorn/icon.png")

        then:
        !icon.present
    }
}
