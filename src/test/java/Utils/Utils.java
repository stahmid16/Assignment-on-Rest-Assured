package Utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Utils {

    public static int generateRandnum(  int min, int max){
        double randId= Math.random()*(max-min)+min;
        return (int) randId;

    }


    public static void seENv(String key, String value) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key, value);
        config.save();
    }

    Properties prop;

    public void getENv() throws IOException {
        prop=new Properties();
        FileInputStream fs=new FileInputStream("./src/test/resources/config.properties");
        prop.load(fs);
    }


    public void saveData(String email,String password) throws IOException, ParseException {
        String path= "./src/test/resources/user.json";
        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray=(JSONArray) jsonParser.parse(new FileReader(path));
        JSONObject userjsonObject=new JSONObject();
        userjsonObject.put("email",email);
        userjsonObject.put("password",password);
        jsonArray.add(userjsonObject);
        FileWriter writer=new FileWriter(path);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();

    }


    public void saveAtoken(String token) throws IOException, ParseException {
        String path= "./src/test/resources/atoken.json";
        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray=(JSONArray) jsonParser.parse(new FileReader(path));
        JSONObject userjsonObject=new JSONObject();
        userjsonObject.put("aToken",token);
        jsonArray.add(userjsonObject);
        FileWriter writer=new FileWriter(path);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();

    }
    public void saveRegId(String id) throws IOException, ParseException {
        String path= "./src/test/resources/regId.json";
        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray=(JSONArray) jsonParser.parse(new FileReader(path));
        JSONObject userjsonObject=new JSONObject();
        userjsonObject.put("regID",id);
        jsonArray.add(userjsonObject);
        FileWriter writer=new FileWriter(path);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();

    }

    public void saveEdit_info(String fname, String lname, String email, String number, String add,String crArt,String upCrt) throws IOException, ParseException {
        String path= "./src/test/resources/edit.json";
        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray=(JSONArray) jsonParser.parse(new FileReader(path));
        JSONObject userjsonObject=new JSONObject();
        userjsonObject.put("fName",fname);
        userjsonObject.put("lname",lname);
        userjsonObject.put("email",email);
        userjsonObject.put("num",number);
        userjsonObject.put("add",add);
        userjsonObject.put("crt",crArt);
        userjsonObject.put("urt",upCrt);
        jsonArray.add(userjsonObject);
        FileWriter writer=new FileWriter(path);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();

    }

    public void saveUtoken(String token) throws IOException, ParseException {
        String path= "./src/test/resources/utoken.json";
        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray=(JSONArray) jsonParser.parse(new FileReader(path));
        JSONObject userjsonObject=new JSONObject();
        userjsonObject.put("uToken",token);
        jsonArray.add(userjsonObject);
        FileWriter writer=new FileWriter(path);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();

    }

    public void saveItemList(String id, String name, String qun, String amnt, String purchaseDate, String month, String remarks, String userId, String updatedAt, String createdAt) throws IOException, ParseException {
        String path = "./src/test/resources/itenList.json";
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray;

        jsonArray = (JSONArray) jsonParser.parse(new FileReader(path));

        JSONObject userjsonObject = new JSONObject();
        userjsonObject.put("id", id);
        userjsonObject.put("name", name);
        userjsonObject.put("quanity", qun);
        userjsonObject.put("amount", amnt);
        userjsonObject.put("date", purchaseDate);
        userjsonObject.put("month", month);
        userjsonObject.put("remark", remarks);
        userjsonObject.put("uid", userId);
        userjsonObject.put("ucard", updatedAt);
        userjsonObject.put("cCard", createdAt);

        jsonArray.add(userjsonObject);

        FileWriter writer = new FileWriter(path);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();
    }


}
