package TestRunner;

import Controller.Controller;
import Model.Model;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestRUnner {
    @Test(priority = 1,description = "User Registation")
    public void reg() throws IOException, ParseException, ConfigurationException {
       Controller control=new Controller();
//       Model.Model model=new Model.Model();
//       Faker fake = new Faker();
//       String fName=fake.name().firstName();
//       String lname=fake.name().lastName();
//       int randmailID = Utils.Utils.generateRandnum(1, 100);
//       String email = "fahmid+" + randmailID + "@gmail.com";
//       String pass="1234";
//       String phnNumber = "017" + Utils.Utils.generateRandnum(10000000, 99999999);
//       String add=fake.address().fullAddress();
//       model.setfName(fName);
//       model.setlName(lname);
//       model.setEmail(email);
//       model.setPassword(pass);
//       model.setPhnNumber(phnNumber);
//       model.setAdd(add);
//       model.setGen("Male");
//       model.setTermsAccepted(true);

        Response response= control.register();


       System.out.println(response.asString());
        JsonPath jsonPath=response.jsonPath();
        String reg_id= jsonPath.get("_id");
        System.out.println(reg_id);

        //Utils.Utils.seENv("reg_id",reg_id);
        Utils utils=new Utils();
        utils.saveRegId(reg_id);




   }


   @Test(priority = 2,description = "Login By Admin")
public void loging() throws ConfigurationException, IOException, ParseException {
    Controller control=new Controller();
    Model model=new Model();
    model.setEmail("admin@test.com");
    model.setPassword("admin123");
    Response response= control.login(model);
    System.out.println(response.asString());
    JsonPath jsonPath=response.jsonPath();
    String token= jsonPath.get("token");
    System.out.println(token);
    //Utils.Utils.seENv("a_token",token);
    Utils utils = new Utils();
    utils.saveAtoken(token);

}
@Test(priority = 3,description = "Get all users")
public void getUset() throws IOException, ParseException {
    Controller control=new Controller();

    Response response= control.getUser();
    System.out.println(response.asString());


}  @Test(priority = 4,description = "Get User By ID")
    public void getUsetByid() throws IOException, ParseException {
        Controller control=new Controller();

        Response response= control.getUserById();
        System.out.println(response.asString());
        JsonPath jsonPath=response.jsonPath();
        String cfname= jsonPath.get("firstName");
        String clname= jsonPath.get("lastName");
        String cemail= jsonPath.get("email");
        String cnumber= jsonPath.get("phoneNumber");
        String cadd= jsonPath.get("address");
        String ccrArt= jsonPath.get("createdAt");
        String cupCrt= jsonPath.get("updatedAt");
        Utils utils=new Utils();
        utils.saveEdit_info(cfname,clname,cemail,cnumber,cadd,ccrArt,cupCrt);







    }
    @Test(priority = 10,description = "Edit User Info")
    public void getEdit() throws IOException, ParseException {
        Controller control = new Controller();

        Response response = control.edit();
        System.out.println(response.asString());
    }
   @Test(priority = 5,description = "Login By user")
    public void logingByUSer() throws IOException, ParseException {
        Controller control = new Controller();

        Response response = control.loginByuser();
        System.out.println(response.asString());
        JsonPath jsonPath=response.jsonPath();
        String token= jsonPath.get("token");
        Utils utils = new Utils();
        utils.saveUtoken(token);

    }
    @Test(priority = 6,description = "Add Item")
    public void addItem() throws IOException, ParseException {
        Controller control = new Controller();
        Response response = control.addItemr();

        System.out.println(response.asString());

        JsonPath jsonPath = response.jsonPath();
        String iem_id = jsonPath.getString("_id");
        String iem_name = jsonPath.getString("itemName");
        String iem_qun = jsonPath.getString("quantity");
        String iem_amnt = jsonPath.getString("amount");
        String iem_purchaseDate = jsonPath.getString("purchaseDate");
        String iem_month = jsonPath.getString("month");
        String iem_remarks = jsonPath.getString("remarks");
        String iem_userId = jsonPath.getString("userId");
        String iem_updatedAt = jsonPath.getString("updatedAt");
        String iem_createdAt = jsonPath.getString("createdAt");

        Utils utils = new Utils();
        utils.saveItemList(
                iem_id, iem_name, iem_qun, iem_amnt,
                iem_purchaseDate, iem_month, iem_remarks,
                iem_userId, iem_updatedAt, iem_createdAt
        );
    }

    @Test(priority = 7,description = "Get Item")
    public void getItem() throws IOException, ParseException {
        Controller control = new Controller();
        Response response = control.getItem();
        System.out.println(response.asString());
    }
  @Test(priority = 8,description = "Edit item")
    public void editItem() throws IOException, ParseException {
        Controller control = new Controller();
        Response response = control.editItem();
        System.out.println(response.asString());
    }
    @Test(priority = 9,description = "Delete Item")
    public void deleteItem() throws IOException, ParseException {
        Controller control = new Controller();
        Response response = control.deleteItem();
        System.out.println(response.asString());
    }


}
