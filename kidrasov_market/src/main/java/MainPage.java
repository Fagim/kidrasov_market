import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainPage extends IndexPage {
    public static void main(String[] args) {
        try {
            Item xiaomiMain = new Item();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("inputdata.json"));
            JSONObject jsonObject = (JSONObject) obj;
            xiaomiMain.setModels((String) jsonObject.get("search"));
            System.out.println("Search data : " + xiaomiMain.getModels());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    String name;
    public String search(String nameTovar){
        name = nameTovar;
        return nameTovar;
    }

}





