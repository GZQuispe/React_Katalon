package website

import java.math.RoundingMode

import org.openqa.selenium.Cookie
import org.openqa.selenium.NoAlertPresentException
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import junit.framework.Assert

public class Helper {

	@Keyword
	def startApp(){
		WebUI.openBrowser(GlobalVariable.URL)
		WebUI.maximizeWindow()
		WebUI.waitForPageLoad(GlobalVariable.DEFAULT_WAIT)
	}


	@Keyword
	def closeBrowser(){
		WebUI.closeBrowser()
	}

	def clickWithVerifyAndScreenshot(TestObject object, String objectName){
		verifyElementIsVisible(object, objectName)
		WebUI.click(object)
		WebUI.takeScreenshot()
	}


	def boolean verifyElementIsVisibleWithWait(TestObject object, int wait){
		boolean result = false
		try{
			result = WebUI.waitForElementVisible(object, wait)
		}catch (StepFailedException e){}
		return result
	}

	def boolean verifyElementIsVisibleWithWait(TestObject object){
		boolean result = false
		try{
			result = WebUI.waitForElementVisible(object, GlobalVariable.DEFAULT_WAIT)
		}catch (StepFailedException e){}
		return result
	}

	public void verifyElementIsVisible(TestObject object, String message){
		verifyElementIsVisible(object, message, GlobalVariable.DEFAULT_WAIT)
	}

	public void verifyElementIsVisible(TestObject object, String name, int wait){
		try{
			Assert.assertTrue(WebUI.waitForElementVisible(object, wait))
		} catch (AssertionError e){
			WebUI.takeScreenshot()
			throw new StepFailedException("No se encontro el item: "+name)
		}
		//	WebUI.takeScreenshot()
	}

	def verifyElementIsClickableWithWait (TestObject object){
		verifyElementIsClickableWithWait(object,"")
	}


	def boolean verifyElementIsClickableWithWait(TestObject object,String name){
		boolean result = false
		try{
			result = WebUI.waitForElementClickable (object, GlobalVariable.DEFAULT_WAIT)
		}catch (StepFailedException e){
			WebUI.takeScreenshot()
			throw new StepFailedException("El elemento "+ name + " no es clickeable.")
		}
		return result
	}

	def boolean verifyElementIsNotClickableWithWait(TestObject object){
		boolean result = false
		try{
			result = WebUI.waitForElementNotClickable (object, GlobalVariable.DEFAULT_WAIT)
		}catch (StepFailedException e){}
		return result
	}

	public Float stringToFloatSinCambios(String importe){
		if (importe[0]=="-"){
			return stringToFloatSinSignoPesos(importe) * -1
		}
		else
			return Float.parseFloat(importe)
	}


	def obtenerFechaDelDia(){
		Date date = new Date()
		String datePart = date.format("dd/MM/yyyy")
		return datePart
	}

	//este formato lo voy a usar en el script s004 para poner la fecha del dia
	def obtenerFechaActual(){
		Date date = new Date()
		String datePart = date.format('yyyy-MM-dd')
		return datePart
	}

	def getPageSource(){
		WebDriver driver = DriverFactory.getWebDriver()
		def texto=driver.getPageSource()
		println texto
	}

	def alertPresent()
	{
		WebDriver driver = DriverFactory.getWebDriver()
		try
		{
			driver.switchTo().alert()
			return true
		}
		catch (NoAlertPresentException Ex)
		{
			return false
		}
	}

	public void verifyElementIsClickable(TestObject object, String msg){
		try{
			Assert.assertTrue(WebUI.verifyElementClickable(object))
		} catch (Exception e){
			WebUI.takeScreenshot()
			throw new StepFailedException(msg)
		}
		WebUI.takeScreenshot()
	}

	def alertPresentDismiss()
	{
		WebDriver driver = DriverFactory.getWebDriver()
		if(alertPresent() == true){
			driver.switchTo().alert().dismiss()
		}
	}

	def alertPresentAccept()
	{
		WebDriver driver = DriverFactory.getWebDriver()
		if(alertPresent() == true){
			driver.switchTo().alert().accept()
		}
	}

	public String eliminarSigno(String importe){
		return importe.substring(1, importe.length())
	}

	public Float stringToFloat(String importe){
		if(importe.contains('.')){
			importe = importe.replace('.', '')
		}
		String importeSinComa = importe.replace(',', '.')
		return Float.parseFloat(importeSinComa.replace(',', '.'))
	}

	public String removeDots(String importe){
		if(importe.contains('.')){
			importe = importe.replace('.', '')
		}
		String importeSinComa = importe.replace(',', '.')
	}

	public Float stringToFloatSinSignoPesos(String importeConSigno){
		String importe = eliminarSigno(importeConSigno)
		return stringToFloat(importe)
	}

	public BigDecimal stringToBigDecimalSinSignoPesos(String importeConSigno){
		String importe = eliminarSigno(importeConSigno)
		System.out.println(importe)
		return new BigDecimal(removeDots(importe))
	}


	public static BigDecimal round(BigDecimal value, int places) {
		if (places < 0) throw new IllegalArgumentException()

		BigDecimal bd = new BigDecimal(value)
		BigDecimal bd1 = new BigDecimal(value)
		BigDecimal bd2 = new BigDecimal(value)
		BigDecimal bd3 = new BigDecimal(value)
		BigDecimal bd4 = new BigDecimal(value)
		BigDecimal bd5 = new BigDecimal(value)
		BigDecimal bd6 = new BigDecimal(value)
		bd = bd.setScale(places, RoundingMode.UP)
		System.out.println("UP " + bd)
		bd1 = bd1.setScale(places, RoundingMode.DOWN)
		System.out.println("DOWN " + bd1)
		bd2 = bd2.setScale(places, RoundingMode.CEILING)
		System.out.println("CEILING " + bd2)
		bd3 = bd3.setScale(places, RoundingMode.FLOOR)
		System.out.println("FLOOR " + bd3)
		bd4 = bd4.setScale(places, RoundingMode.HALF_UP)
		System.out.println("HALF UP " + bd4)
		bd5 = bd5.setScale(places, RoundingMode.HALF_DOWN)
		System.out.println("HALF DOWN " + bd5)
		bd6 = bd6.setScale(places, RoundingMode.HALF_EVEN)
		System.out.println("HALF EVEN " + bd6)
		return 	bd4
	}

	def clickOnButton(TestObject button,String buttonName){
		verifyElementIsVisible(button, buttonName)
		verifyElementIsClickableWithWait(button)
		WebUI.click(button)
	}

	def tokenizeCookie(Cookie ck){
		return ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure()
	}

	def buildCookie(String tokenizedCookie){
		StringTokenizer token = new StringTokenizer(tokenizedCookie,";");
		System.out.println(token)
		String name = token.nextToken();
		System.out.println(name)
		String value = token.nextToken();
		System.out.println(value)
		String domain = token.nextToken();
		System.out.println(domain)
		String path = token.nextToken();
		System.out.println(path)
		System.out.println(token.nextToken())
		//	   Date expiry = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss").parse(token.nextToken())
		Boolean isSecure = new Boolean(token.nextToken())
		Cookie cookie = new Cookie(name,value);
		System.out.println(cookie)
		return cookie
	}

}
