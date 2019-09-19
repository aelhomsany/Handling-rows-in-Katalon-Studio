import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
///// new imported For manupilating test objects
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import org.openqa.selenium.WebElement as WebElement
import org.apache.commons.lang.RandomStringUtils as RandomStringUtils
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
//// new imported to handel tables in html 
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl('https://www.w3schools.com/tags/tag_button.asp')

WebUI.delay(5)

/// Here we gonna sperate the xpath of the object that we want to manupilate
// the orginal xpath before seperate 
path1 = '//*[@id="table1"]/tbody/tr['

def RowNumber

Path2 = ']/td[1]/a'

//'Expected value from Table' to be clicked 
def ExpectedValue = 'formaction'

WebDriver driver = DriverFactory.getWebDriver()

//'To locate table'
WebElement Table = driver.findElement(By.xpath('//*[@id="table1"]'))

//'To locate rows of table it will Capture all the rows available in the table '
List<WebElement> Rows = Table.findElements(By.tagName('tr'))

println('No. of rows: ' + Rows.size())

//'Find a matching text in a table and performing action'
//'Loop will execute for all the rows of the table'
table: for (int i = 0; i < Rows.size(); i++) {
    //'To locate columns(cells) of that specific row'
    List<WebElement> Cols = Rows.get(i).findElements(By.tagName('td'))

    for (int j = 0; j < Cols.size(); j++) {
        //'Verifying the expected text in the each cell'
        if (Cols.get(j).getText().equalsIgnoreCase(ExpectedValue)) {
            //'To locate anchor in the expected value matched row to perform action'	
            // add +1 becuase the tables rows starts from index of zero [0]		
            RowNumber = (i + 1)

            println(RowNumber)

            break
        }
    }
}

// concatinate the new xpath that matching the value Bananes ;) 
finalpath = ((path1 + RowNumber) + Path2)

println(finalpath)

WebUI.delay(5)

// Now get a new object from testobjects to click on the desired row 
TestObject to = new TestObject('NewXpath')

// Pathing the value of the xpath to the object property 
to.addProperty('xpath', ConditionType.EQUALS, finalpath) 

WebUI.scrollToPosition(1500, 1600) 

WebUI.click(to)  

WebUI.verifyTextPresent('formaction Attribute', false)  

