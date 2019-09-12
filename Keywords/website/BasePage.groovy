package website

import org.openqa.selenium.By
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import junit.framework.Assert
import junit.framework.AssertionFailedError

public class BasePage extends Helper{

	final String mainFrameXpath = "//frame[@name = 'main']"
	final String tabPageFrameXpath = "//*[@id='tabPage']"
	final String mainFrameCss = ".oraDesktop frame"
	final String tabPageFrameCss = ".pageContainer.oraDesktop div#tabDiv.tabDiv iframe#tabPage"
	final String ENRL_FLDFrameCss = ".oraDesktop table#tabPageTable.layoutMainTable.widthHundred tbody tr#secRow_6 td#sec_fieldsList table.layoutSectionTable tbody tr#row2_fieldsList td iframe#ENRL_FLD.gridFrame"
	final String PKG_TREEframeCss = ".oraDesktop table#tabPageTable.layoutMainTable.widthHundred tbody tr#secRow_8 td#sec_pkgTree table.layoutSectionTable tbody tr#row2_pkgTree td iframe#PKG_TREE.gridFrame"

	protected WebDriver driver = DriverFactory.getWebDriver()

	def selectOptionByLabelStaleElement(testObject, label){
		WebUI.delay(1)
		boolean result = false;
		int i = 0;
		while(i < 3) {
			try {
				WebUI.selectOptionByLabel(testObject, label, false)
				result = true;
				break;
			} catch(StaleElementReferenceException e) {
			}
			i++;
		}
	}

	protected List<WebElement> findElementsInAFrame(String xpathFrame, String xpathElements){
		driver.switchTo().frame(driver.findElement(By.xpath(xpathFrame)));
		List<WebElement> elementList = driver.findElements(By.xpath(xpathElements));
		return elementList;
	}

	protected List<WebElement> findElements(String xpathElements){
		List<WebElement> elementList = driver.findElements(By.xpath(xpathElements));
		return elementList;
	}

	protected void checkThatIsVisible(TestObject object, String message){
		try{
			Assert.assertTrue(WebUI.verifyElementVisible(object))
		} catch (AssertionFailedError e){
			WebUI.takeScreenshot()
			throw new StepFailedException(message)
		}
		WebUI.takeScreenshot()
	}

	protected void checkThatTextIsPresent(String text, String message){
		try{
			Assert.assertTrue(WebUI.verifyTextPresent(text, false))
		} catch (AssertionFailedError e){
			WebUI.takeScreenshot()
			throw new StepFailedException(message)
		}
		WebUI.takeScreenshot()
	}

	protected void checkThatObjectContainsText(TestObject object, String text, String message){
		try{
			Assert.assertTrue(WebUI.getText(object).contains(text))
		} catch (AssertionFailedError e){
			WebUI.takeScreenshot()
			throw new StepFailedException(message)
		}
		WebUI.takeScreenshot()
	}

	protected void checkThatListIsPopulated(List<WebElement> list, String message){
		try{
			Assert.assertTrue(list.size() > 0)
		} catch (AssertionFailedError e){
			WebUI.takeScreenshot()
			throw new StepFailedException(message)
		}
		WebUI.takeScreenshot()
	}

	protected void checkThatIsNotVisible(TestObject object, String message){
		try{
			Assert.assertFalse(WebUI.verifyElementVisible(object))
		} catch (AssertionFailedError e){
			WebUI.takeScreenshot()
			throw new StepFailedException(message)
		}
		WebUI.takeScreenshot()
	}

	def popUpPresent() {
		try {
			WebUI.switchToWindowIndex(1)
			return true
		}
		catch (StepFailedException Ex) {
			return false
		}
	}

	protected void switchToFrameByXpath(String xpath){
		driver.switchTo().frame(driver.findElement(By.xpath(xpath)))
	}

	protected void switchToFrameByCss(String css){
		driver.switchTo().frame(driver.findElement(By.cssSelector(css)))
	}

	protected WebElement getElementFromCssSelector(String cssSelector){
		WebElement element = driver.findElement(By.cssSelector(cssSelector))
		return element
	}

	protected WebElement getElementFromXpath(String xpath){
		WebElement element = driver.findElement(By.xpath(xpath))
		return element
	}

	protected void switchToENRL_FLDFrameCss(){
		driver.switchTo().defaultContent()
		driver.switchTo().frame(driver.findElement(By.cssSelector(mainFrameCss)))
		driver.switchTo().frame(driver.findElement(By.cssSelector(tabPageFrameCss)))
		driver.switchTo().frame(driver.findElement(By.cssSelector(ENRL_FLDFrameCss)))
	}

	protected void switchToTabFrameCss(){
		driver.switchTo().defaultContent()
		driver.switchTo().frame(driver.findElement(By.cssSelector(mainFrameCss)))
		driver.switchTo().frame(driver.findElement(By.cssSelector(tabPageFrameCss)))
	}

	protected void switchToPKG_TREEFrameCss(){
		driver.switchTo().defaultContent()
		driver.switchTo().frame(driver.findElement(By.cssSelector(mainFrameCss)))
		driver.switchTo().frame(driver.findElement(By.cssSelector(tabPageFrameCss)))
		driver.switchTo().frame(driver.findElement(By.cssSelector(PKG_TREEframeCss)))
	}

	protected void switchToTabFrameXpath(){
		driver.switchTo().defaultContent()
		driver.switchTo().frame(driver.findElement(By.cssSelector(mainFrameXpath)))
		driver.switchTo().frame(driver.findElement(By.cssSelector(tabPageFrameXpath)))
	}

	protected void switchToMainFrameXpath(){
		driver.switchTo().defaultContent()
		driver.switchTo().frame(driver.findElement(By.cssSelector(mainFrameXpath)))
	}

	protected void setTextCSS(String cssPath, String text){
		getElementFromCssSelector(cssPath).clear();
		getElementFromCssSelector(cssPath).sendKeys(text);
	}
}
