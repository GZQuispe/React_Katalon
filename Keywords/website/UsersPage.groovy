package website

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class UsersPage extends BasePage{

	private TestObject addNew  = findTestObject('Object Repository/Page_Users/button_add_new')
	private TestObject table  = findTestObject('Object Repository/Page_Users/table_list_container')
	private TestObject circle  = findTestObject('Object Repository/Page_Users/circle')
	private TestObject newUser = new TestObject('New User')

	@Keyword
	def clickAddButton(){
		verifyElementIsVisible(circle, "Circle")
		verifyElementIsVisible(table, "Users Table")
		verifyElementIsVisible(addNew, "Button Add new User")
		verifyElementIsClickableWithWait(addNew)
		WebUI.takeScreenshot()
		WebUI.click(addNew)
	}


	@Keyword
	def verifyNewUserAdded(String username){
		String xpath = "//span[contains(text(),'" + username +"')]"
		newUser.addProperty("xpath", ConditionType.EQUALS, xpath)
		verifyElementIsVisible(newUser, username)
		WebUI.takeScreenshot()
		
	}
}
