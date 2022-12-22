# Appium_Mobile_Testing

### Appium (Android & IOS) Hybrid Framework Development For E-Commerce Mobile Application Testing

* Build Page object Factory pattern to drive the Element locators to the Tests
* Implemented Android & IOS Page object repositories for the respective Platform tests
* Build Actions utility classes for both Android & IOS Apps to store reusable Mobile Gestures
* Build Generic Utility called AppiumUtils Class which have commonly used methods for Android & IOS.
* Implement Base Test with Capabilities where all Tests inherit it into their respective classes
* Methods to dynamically Start & Stop Appium Server before every Test
* Build Test Strategy on how tests should be written in isolation with setup & Teardown methods
* Create Properties file to implement Global configuration values into the Framework
* Build TestNG.xml files to run all tests together with proper class, Suite, Test conventions inside the xml file
* Introduced Extent reports to get excellent HTML reports of Framework results
* Implemented TestNG listeners to capture screenshots on test failures & report it back to Extent reports
* Data driven testing from json payloads into Test using Jackson Mapper & TestNG Data provider
* Implemented TestNG groups to run selected tests based on tags set on test level
* Implemented set of Maven Profiles using TestNG.xml runner files to run test with Maven commands
* Implemented Run time variables inputs through Maven and adjust the code accordingly to receive inputs
* Integrated the Full Framework Project with Jenkins CI/CD tool and parameterized the job with run time variables
* Enhanced and integrate the framework with Cloud infrastructure to schedule tests on cloud device with Parallel execution
