package cn.qfys521;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Web2Img {
    public Web2Img(){
        new NotThrowAsException().sneakyThrow(new InstantiationException("Wen2Img.class 不应该被实例化."));
    }
    /**
     * 网页截图
     * @param url 网页链接
     * @param fileName 文件名称
     */
    public static void Screenshot(String url , String fileName){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        driver = new ChromeDriver(chromeOptions);
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
            driver.manage().window().maximize();
            driver.get(url);
            //等待页面加载完成
            new WebDriverWait(driver, Duration.ofSeconds(300)).until(d -> ((JavascriptExecutor) d)
                    .executeScript("return document.readyState").equals("complete"));
            JavascriptExecutor jexec = (JavascriptExecutor) driver;
            int width = Integer.parseInt(jexec.executeScript("return document.body.scrollWidth").toString());
            int height = Integer.parseInt(jexec.executeScript("return document.body.scrollHeight").toString()) ;
            //设置浏览窗口大小
            driver.manage().window().setSize(new Dimension(width, height));
            Screenshot screenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver);
            BufferedImage image = screenshot.getImage();
            ImageIO.write(image, "PNG", new File("./"+fileName+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
    /**
     * 网页截图
     * @param url 网页链接
     */
    public static void Screenshot(String url){
        Screenshot(url , "Screenshot");
    }
}
