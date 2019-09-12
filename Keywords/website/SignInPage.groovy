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

public class SignInPage extends BasePage{

	private TestObject inputEmail 	 = findTestObject('Object Repository/Page_SingIn/input_email')
	private TestObject inputPassword = findTestObject('Object Repository/Page_SingIn/input_password')
	private TestObject buttonSingIn  = findTestObject('Object Repository/Page_SingIn/button_sing_in')
	private TestObject linkCreateAccount = findTestObject('Object Repository/Page_SingIn/button_sing_in')

	@Keyword
	def login(String email, String password){
		enterEmail(email)
		enterPassword(password)
		clickSingIn()
	}

	def enterEmail(String email){
		verifyElementIsVisible(inputEmail, email)
		WebUI.setText(inputEmail, email)
	}

	def enterPassword(String password){
		verifyElementIsVisible(inputPassword, password)
		WebUI.setText(inputPassword, password)
	}

	def clickSingIn(){
		verifyElementIsClickable(buttonSingIn, "Button Sign In")
		WebUI.click(buttonSingIn)
	}

	@Keyword
	def clickCreateAccount(){
		verifyElementIsVisible(linkCreateAccount, "Link Create Account")
		WebUI.click(linkCreateAccount)
	}
}
