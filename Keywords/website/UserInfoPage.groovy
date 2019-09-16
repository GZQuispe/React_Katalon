package website

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class UserInfoPage extends BasePage{

	private TestObject inputConfirmPassword = findTestObject('NewUsersPage/input_confirmPassword')
	private TestObject inputEmployee  = findTestObject('NewUsersPage/input_empoyee')
	private TestObject inputPassword  = findTestObject('NewUsersPage/input_password')
	private TestObject inputUsername  = findTestObject('NewUsersPage/input_username')
	private TestObject dropdownAdmin   = findTestObject('NewUsersPage/dropdown_admin')
	private TestObject dropdownRole   = findTestObject('NewUsersPage/dropdown_role')
	private TestObject dropdownStatus  = findTestObject('NewUsersPage/dropdown_status')
	private TestObject dropdownSupervisor = findTestObject('NewUsersPage/dropdown_supervisor')
	private TestObject buttonSave = findTestObject('NewUsersPage/button_new_user')
	private TestObject span = new TestObject('')

	@Keyword
	def enterData(String employee, String username, String role, String supervisor, String adminRole, String status, String password, String confirmPassword){
		verifyElementIsVisible(inputUsername,"Input Username")
		WebUI.setText(inputEmployee,employee)
		WebUI.setText(inputUsername,username)
		WebUI.setText(inputPassword,password)
		WebUI.setText(inputConfirmPassword,confirmPassword)
		selectOption(dropdownRole,role)
		selectOption(dropdownSupervisor,supervisor)
		selectOption(dropdownAdmin,adminRole)
		selectOption(dropdownStatus,status)
		WebUI.takeScreenshot()
		WebUI.click(buttonSave)
	}

	@Keyword
	def selectOption(TestObject object, String option){
		WebUI.click(object)
		String xpath = "//li/span[contains(text(),'" + option +"')]"
		span.addProperty("xpath", ConditionType.EQUALS, xpath)
		verifyElementIsVisible(span,"Option")
		WebUI.click(span)
	}

	@Keyword
	def editStatus(String newStatus){
		verifyElementIsVisible(inputUsername,"Input Username")
		selectOption(dropdownStatus,newStatus)
		WebUI.takeScreenshot()
		WebUI.click(buttonSave)
	}
}
