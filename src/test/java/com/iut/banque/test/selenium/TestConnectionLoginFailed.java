package com.iut.banque.test.selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class TestConnectionLoginFailed {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<>();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testConnectionLoginFailedAccountBlocked() {
        driver.get("http://localhost:8082/_00_ASBank2023/");
        driver.manage().window().setSize(new Dimension(1552, 840));
        driver.findElement(By.linkText("Accéder à la banque")).click();
        driver.findElement(By.id("controller_Connect_login_action_userCde")).click();
        driver.findElement(By.id("controller_Connect_login_action_userCde")).sendKeys("utilisateur_invalide");
        driver.findElement(By.id("controller_Connect_login_action_userPwd")).click();
        driver.findElement(By.id("controller_Connect_login_action_userPwd")).sendKeys("mot_de_passe_invalide");
        driver.findElement(By.id("controller_Connect_login_action_submit")).click();

        // Vérifier que l'utilisateur est redirigé vers une page indiquant que le compte est bloqué
        assertTrue(driver.getTitle().contains("IUT Bank - Compte bloqué"));
    }

    @Test
    public void testConnectionLoginFailedConnectionError() {
        driver.get("http://localhost:8082/_00_ASBank2023/");
        driver.manage().window().setSize(new Dimension(1552, 840));
        driver.findElement(By.linkText("Accéder à la banque")).click();
        driver.findElement(By.id("controller_Connect_login_action_userCde")).click();
        driver.findElement(By.id("controller_Connect_login_action_userCde")).sendKeys("utilisateur_invalide");
        driver.findElement(By.id("controller_Connect_login_action_userPwd")).click();
        driver.findElement(By.id("controller_Connect_login_action_userPwd")).sendKeys("mot_de_passe_invalide");
        driver.findElement(By.id("controller_Connect_login_action_submit")).click();

        // Vérifier que l'utilisateur est redirigé vers une page indiquant une erreur de connexion
        assertTrue(driver.getTitle().contains("Erreur de Connexion"));
    }
}