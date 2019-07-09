package com.example.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class GDGCadastro {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String formUrl;
	
	public GDGCadastro(String formUrl){
		this.formUrl = formUrl;
	}

	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Users/gurio/workspace/cadastro_gdg/chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "https://www.sympla.com.br/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void testGDGCadastro(User user) throws Exception {
		driver.get(baseUrl + formUrl);
		//xpath=//form[@id='ticket-form']/table/tbody/tr[3]/td[2]/a[2]/span
		
		driver.findElement(By.xpath("//*[@id='ticket-form']/table/tbody/tr[3]/td[2]/a[2]/span")).click();
		driver.findElement(By.id("btnContinue")).click();
		
		

		for (int second = 0;; second++) {
			if (second >= 60)
				try {
					if (isElementPresent(By.id("customFormField_firstName_0")))
						break;
				} catch (Exception e) {
				}
			Thread.sleep(100);
		}
		
		
		
		driver.findElement(By.id("customFormField_firstName_0")).clear();
		driver.findElement(By.id("customFormField_firstName_0")).sendKeys(user.nome);
		driver.findElement(By.id("customFormField_lastName_0")).clear();
		driver.findElement(By.id("customFormField_lastName_0")).sendKeys(user.sobrenome);
		driver.findElement(By.id("customFormField_Email_0")).clear();
		driver.findElement(By.id("customFormField_Email_0")).sendKeys(user.email);
		driver.findElement(By.id("customFormField_418064_1_0")).clear();
		driver.findElement(By.id("customFormField_418064_1_0")).sendKeys(user.ddd);
		driver.findElement(By.id("customFormField_418064_2_0")).clear();
		driver.findElement(By.id("customFormField_418064_2_0")).sendKeys(user.telefone);
		driver.findElement(By.id("customFormField_418065_0")).clear();
		driver.findElement(By.id("customFormField_418065_0")).sendKeys("UFAL");
		driver.findElement(By.id("customFormField_418067_0")).clear();
		driver.findElement(By.id("customFormField_418067_0")).sendKeys("Maceio/AL");
		
		driver.findElement(By.id("customFormField_455699_0")).click();
		
		new Select(driver.findElement(By.id("ddlCopyFrom"))).selectByVisibleText("Ingresso nº 1");
		driver.findElement(By.id("FreeOrder_confirmEmail")).clear();
		driver.findElement(By.id("FreeOrder_confirmEmail")).sendKeys(user.email);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		driver.findElement(By.id("buttonFree")).click();

		for (int second = 0;; second++) {
			if (second >= 60) {
				if (isElementPresent(By.id("lnkDownload"))){
					break;
				}
			}
			Thread.sleep(100);
		}

	}

	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			// fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
