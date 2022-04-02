import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class IndexPage{
    @Test
    public void Test1(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://market.yandex.ru/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        JSONParser parser = new JSONParser();
        Item mouse = new Item();
        try {
            Object obj = parser.parse(new FileReader("inputdata.json"));
            JSONObject jsonObject = (JSONObject) obj;
            mouse.setModels((String) jsonObject.get("search"));
            System.out.println("Search data : " + mouse.getModels());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        MainPage mainPage = new MainPage();
        mainPage.search(mouse.getModels());

        WebElement search_input = driver.findElement(By.xpath("//input[@name='text']"));
        WebElement button_search = driver.findElement(By.xpath("//button[@class='_1Dyrh _1NDr9 _3MZAj V9ceN wQgEg _1Himk sQ_gr _2YKh4 _3O-ed G12sD _9Lboa _3ofRm _1XiEJ mini-suggest__button']"));
        search_input.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println(mouse.getModels());
        search_input.sendKeys(mouse.getModels());
        button_search.click();
        WebElement button_sort_price = driver.findElement(By.xpath("//button[@class='_23p69 _3D8AA']"));
        button_sort_price.click();



        ArrayList<String> getCollectionProduct = new ArrayList<>();
        List<WebElement> findAllElevementgetProduct = driver.findElements(By.xpath("//h3[@class='_2UHry _2vVOc']//a"));

        for (WebElement findAllElevementgetProductCurrentLink:findAllElevementgetProduct)
        {
            try {
                getCollectionProduct.add(findAllElevementgetProductCurrentLink.getDomAttribute("title"));
            }catch (NoSuchElementException e){
                e.printStackTrace();
            }catch (StaleElementReferenceException e){
                e.printStackTrace();
            }

        }
        if (getCollectionProduct.contains(mouse.getModels()))
        {
            System.out.println("В списке результатов содержится товар, который искали");
        }else
        {
            System.out.println("В списке результатов не содержится товар, который искали");
        }
        System.out.println("Список продуктов "+getCollectionProduct);


        WebElement buttonFirstResults = driver.findElement(By.xpath("//h3[@class='_2UHry _2vVOc']//a"));
        String getFirstResult="https://market.yandex.ru/"+buttonFirstResults.getDomAttribute("href");
        System.out.println("Ссылка на первый результат " + getFirstResult);

        driver.get(getFirstResult);

        try {
            WebElement pricePrdouct = driver.findElement(By.xpath("//div[@class='_3NaXx _3kWlK']//span//span"));
            WebElement RetailPrdouct = driver.findElement(By.xpath("//div[@class='_1CxTI']//a"));
            System.out.println("Название товара и его цена = "+pricePrdouct.getText() + " "+ RetailPrdouct.getDomAttribute("title"));
        } catch (NoSuchElementException e){
            e.printStackTrace();
        }
        driver.quit();

    }
}
