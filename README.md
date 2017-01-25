# Icon-Loader

Loads icon as base64 string from icon/icon.png into 'info.build.icon' property
 
## Installation

Gradle
```
compile('com.github.iconloader:icon-loader:0.0.1')
```

```
repositories {
    maven {
        url  "http://dl.bintray.com/thomasbem/maven"
    }
}
```

## Usage

1. Add an image called icon/icon.png to the src/main/resources folder
2. Add `@EnableIcon` to the Application-class (the class containing the `@SpringBootApplication`).  


