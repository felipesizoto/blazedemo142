// 1 - bibliotecas / imports

// 2 - Classe 

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; // biblioteca principal do Selenium 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver; // biblioteca do ChromeDriver 

import io.github.bonigarcia.wdm.WebDriverManager;

public class PassagemTest {
    // 2.1 - Atributos
    private WebDriver driver; // objeto do Selenium

    // 2.2 Funções e Métodos

    // Antes do Teste
    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // baixa o driver do navegador, necessario para o teste, caso
                                                 // necessário
        driver = new ChromeDriver(); // instanciar o objeto do Selenium como Chrome
        driver.manage().window().maximize(); // mmaximiza a jamela do browser
    }

    // Depois do Teste
    @AfterEach
    public void tearDown() {
        driver.quit(); // destrói o objeto do Selenium
    }

    // Teste
    @Test
    public void comprarPassagem() {
        driver.get("https://www.blazedemo.com"); // abre o site Blazedemo
        // seleciona origem, destino e aperta o botão "Find Flights"

        // combo Origem
        driver.findElement(By.name("fromPort")).click(); // clica no combo
        {
            WebElement dropdown = driver.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = 'São Paolo']")).click();
        }

        // combo Destino
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.click(); // clica no combo
            dropdown.findElement(By.xpath("//option[. = 'Cairo']")).click();
        }

        // Clicar no botão
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

        // Transição de Página

        // Verifica se foi realizado o logn e a pesquisa dos võos
        assertEquals("Flights from São Paolo to Cairo:",
                driver.findElement(By.cssSelector("h3")).getText());

        // Clica no botão do vôo desejado
        driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();

        // Verifica se está na página de compra
        assertEquals("Your flight from TLV to SFO has been reserved.",
                driver.findElement(By.cssSelector("h2")).getText());

        // Preenche o campo nome
        driver.findElement(By.id("inputName")).sendKeys("Oliver Almada");

        // Preenche o campo endereço boia
        driver.findElement(By.id("address")).sendKeys("Rua Saboia, 179");

        // Preenche a cidade
        driver.findElement(By.id("city")).sendKeys("Ribeirão Pires");

        // Preenche o estado
        driver.findElement(By.id("state")).sendKeys("São Paulo");

        // Selecionar cartão
        WebElement cartaoOpcaoAmex = driver.findElement(By.id("cardType"));
        cartaoOpcaoAmex.findElement(By.xpath("//option[text() = 'American Express']")).click();

        // Escrever o nome que está no cartão
        driver.findElement(By.id("state")).sendKeys("Oliver Francisco Ortega");

        // Apertar botão
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

    }// final do compra passagem

} // final da classe PassagemTest
