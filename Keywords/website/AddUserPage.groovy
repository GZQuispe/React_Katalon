package website

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class AddUserPage extends BasePage{

	private TestObject inputConfirmPassword = findTestObject('Object Repository/Page_NewUsers/input_confirmPassword')
	private TestObject inputEmployee  = findTestObject('Object Repository/Page_NewUsers/input_empoyee')
	private TestObject inputPassword  = findTestObject('Object Repository/Page_NewUsers/input_password')
	private TestObject inputUsername  = findTestObject('Object Repository/Page_NewUsers/input_username')
	private TestObject dropdownAdmin   = findTestObject('Object Repository/Page_NewUsers/dropdown_admin')
	private TestObject dropdownRole   = findTestObject('Object Repository/Page_NewUsers/dropdown_role')
	private TestObject dropdownStatus  = findTestObject('Object Repository/Page_NewUsers/dropdown_status')
	private TestObject dropdownSupervisor = findTestObject('Object Repository/Page_NewUsers/dropdown_supervisor')
	private TestObject buttonSave = findTestObject('Object Repository/Page_NewUsers/button_new_user')
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
		WebUI.click(span)
	}
}
