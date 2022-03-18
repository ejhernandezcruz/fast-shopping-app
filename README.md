# Fast-Shopping-App (Appium Test Automation)

**Test automation framework with tests scripts using Appium**

[Fast Shopping APK](https://f-droid.org/repo/me.wolszon.fastshopping_23.apk)

## Requirements

1. *Install a JDK 8+ distribution such as  [Azul Zulu JDK](https://www.azul.com/downloads/?package=jdk)*
2. *Add Maven into your classpath* [Maven Download](https://maven.apache.org/download.cgi)


## Test Execution

To execute all the automation tests use the following command:

`mvn clean test`

Optionally you can use any IDE of  your preference
In my case I preder Intellij Idea by Jetbrains


## Configurations Files

All the properties can be done in the following  properties file **config.properties** located in src/test/resources folder

	appiumServer=http://localhost:4723/wd/hub [Appium Server URL]
    timeout=30  [timeout in seconds]
	platformName=Android [Platform name capability] 
	platformVersion=8.0  [Android version]
	automationName=UiAutomator2 [Autmation strategy]  
	deviceName=7e32c193 [Device name capability] 
	app=me.wolszon.fastshopping_23.apk  [APK]
	remote=false ['false' to run locally]

## Dependencies

| Library| Version |  
|--|--|  
|Appium|  7.6.0|  
|Selenium  |  3.141.59|  
|WebDriver Manager| 5.0.3 |  
|TestNG       | 7.4.0  |  
|Lombok| 1.18.22|