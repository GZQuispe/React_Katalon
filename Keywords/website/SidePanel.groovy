package website

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class SidePanel extends BasePage{

	private TestObject admin 		  = findTestObject('Object Repository/SidePanel/option_admin')
	private TestObject userManagement = findTestObject('Object Repository/SidePanel/option_user_management')
	private TestObject users 		  = findTestObject('Object Repository/SidePanel/option_users')

	@Keyword
	def selectOptionUsers(){
		selectAdmin()
		selectUserManagement()
		WebUI.takeScreenshot()
		selectUsers()
	}

	def selectAdmin(){
		verifyElementIsVisible(admin, "Option Admin")
		WebUI.click(admin)
	}

	def selectUserManagement(){
		verifyElementIsVisible(userManagement,"Option User Management")
		WebUI.click(userManagement)
	}

	def selectUsers(){
		verifyElementIsVisible(users,"Option Users")
		WebUI.click(users)
	}
}
