package website

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class UsersPage extends BasePage{

	private TestObject addNew   = findTestObject('UsersPage/button_add_new')
	private TestObject table    = findTestObject('UsersPage/table_list_container')
	private TestObject circle   = findTestObject('UsersPage/circle')
	private TestObject newUser  = new TestObject('New User')
	private TestObject iconEdit = new TestObject('Icon Edit')
	private TestObject user 	= new TestObject('User')
	
	def UsersPage(){
		verifyElementIsVisible(circle, "Circle")
		verifyElementIsVisible(table, "Users Table")
		verifyElementIsVisible(addNew, "Button Add new User")
	}

	@Keyword
	def clickAddButton(){
		verifyElementIsClickableWithWait(addNew)
		WebUI.takeScreenshot()
		WebUI.click(addNew)
	}
	
	@Keyword
	def clickEditUser(String user){
		String editIconXpath = "//*[contains(text(),'" + user + "')]//following::*[contains(text(),'ohrm_edit')][1]"
		iconEdit.addProperty("xpath", ConditionType.EQUALS, editIconXpath)
		WebUI.click(iconEdit)
	}


	@Keyword
	def verifyNewUserAdded(String username){
		newUser = addTextXpathToObject(newUser, username)
		verifyElementIsVisible(newUser, username)
		WebUI.takeScreenshot()
	}
	
	@Keyword
	def verifyNewStatus(String username, String newStatus){
		String userXpath = "//*[contains(text(),'" + username + "')]//following::*[contains(text(),'" + newStatus + "')][1]"
		user.addProperty("xpath", ConditionType.EQUALS, userXpath)
		WebUI.delay(1)
		verifyElementIsVisible(user, "New Status")
		WebUI.takeScreenshot()
	}
}
