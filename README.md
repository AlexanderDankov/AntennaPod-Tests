![image](https://user-images.githubusercontent.com/49765744/208318444-955d232f-35a5-4aea-b6c7-644ea47d5ed6.png)

# AntennaPod-Tests

## Autotests for open source podcast manager app AntennaPod:

https://github.com/AntennaPod/AntennaPod/releases

https://f-droid.org/repo/de.danoeh.antennapod_2070195.apk

### Used tech stack:
|  Java  | JUnit5 | Appium | Gradle | Intelij IDEA | Allure Report |
|--------|--------|--------|--------|--------------|---------------|
|<img src="img/JAVA.svg" width=70 height=70>|<img src="img/Junit5.svg" width=70 height=70>|<img src="img/appium-logo-png-transparent.png" width=70 height=70>|<img src="img/Gradle.svg" width=70 height=70>|<img src="img/IDEA.svg" width=70 height=70>|<img src="img/AllureReport.svg" width=70 height=70>|

### To specify device for running tests use deviceHost system property (real/emulator/browserstack/selenoid). For example:

```
gradle clean test -DdeviceHost=emulator
```

### Allure Report:
![image](https://user-images.githubusercontent.com/49765744/208319208-4d4b6067-f5eb-4e70-bad9-c7c09afb69a4.png)

### Tests with steps and screenshot:
![image](https://user-images.githubusercontent.com/49765744/208319236-f80b5b11-d3de-477e-a717-aabea90db874.png)


