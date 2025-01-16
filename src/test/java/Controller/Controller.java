package Controller;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Controller {
    public Controller(){
        RestAssured.baseURI="https://dailyfinanceapi.roadtocareer.net";

    }

    public Response register() throws IOException, ParseException {

        RestAssured.baseURI = "https://dailyfinanceapi.roadtocareer.net";
        Faker fake = new Faker();
        String fName = fake.name().firstName();
        String lName = fake.name().lastName();
        int randmailID = Utils.generateRandnum(1, 100);
        String email = "fahmid+" + randmailID + "@gmail.com";
        String pass = "1234";
        String phnNumber = "017" + Utils.generateRandnum(10000000, 99999999);
        String address = fake.address().fullAddress();
        String body = String.format(
                "{ \"firstName\": \"%s\", \"lastName\": \"%s\", \"email\": \"%s\", \"password\": \"%s\", \"phoneNumber\": \"%s\", \"address\": \"%s\", \"gender\": \"Male\", \"termsAccepted\": true }",
                fName, lName, email, pass, phnNumber, address
        );
        Response response = given()
                .contentType("application/json")
                .body(body)
                .when()
                .post("/api/auth/register");

        System.out.println("Request Body: " + body);
        Utils utils = new Utils();
        utils.saveData(email,pass);

        return response;
    }

    public Response login(Model model){

        Response response=given().contentType("application/json").body(model)
                .when().post("/api/auth/login");
        return response;

    }

    public Response getUser() throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/atoken.json"));
        JSONObject jsonuobj= (JSONObject) jsonArray.get(jsonArray.size()-1);
        String token =jsonuobj.get("aToken").toString();

        Response response=given().contentType("application/json").
                headers("Authorization","Bearer "+token).
                when().
                get("/api/user/users");

        return response;

    }

    public Response getUserById() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/atoken.json"));
        JSONObject jsonuobj = (JSONObject) jsonArray.get(jsonArray.size() - 1);
        String token = jsonuobj.get("aToken").toString();

        JSONParser jsonParser2 = new JSONParser();
        JSONArray jsonArray2 = (JSONArray) jsonParser2.parse(new FileReader("./src/test/resources/regId.json"));
        JSONObject jsonuobj2 = (JSONObject) jsonArray2.get(jsonArray2.size() - 1);
        String rehId = jsonuobj2.get("regID").toString();

        Response response = given()
                .contentType("application/json")
                .headers("Authorization", "Bearer " + token)
                .when()
                .get("/api/user/"+rehId);

        return response;
    }



    public Response edit() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/atoken.json"));
        JSONObject jsonuobj = (JSONObject) jsonArray.get(jsonArray.size() - 1);
        String token = jsonuobj.get("aToken").toString();

        JSONParser jsonParser2 = new JSONParser();
        JSONArray jsonArray2 = (JSONArray) jsonParser2.parse(new FileReader("./src/test/resources/regId.json"));
        JSONObject jsonuobj2 = (JSONObject) jsonArray2.get(jsonArray2.size() - 1);
        String rehId = jsonuobj2.get("regID").toString();

        JSONParser jsonParser3 = new JSONParser();
        JSONArray jsonArray3 = (JSONArray) jsonParser3.parse(new FileReader("./src/test/resources/edit.json"));
        JSONObject jsonuobj3 = (JSONObject) jsonArray3.get(jsonArray3.size() - 1);

        Faker fake = new Faker();
        String fName = fake.name().firstName();
        String lname = jsonuobj3.get("lname").toString();
        String email = jsonuobj3.get("email").toString();
        String pass = "1234";
        String phnNumber = "017" + Utils.generateRandnum(10000000, 99999999);
        String address = jsonuobj3.get("add").toString();
        String createdAt = jsonuobj3.get("crt").toString();
        String updatedAt = jsonuobj3.get("urt").toString();

        Response response = given()
                .contentType("application/json")
                .body("{"
                        + "\"_id\":\"" + rehId + "\","
                        + "\"firstName\":\"" + fName + "\","
                        + "\"lastName\":\"" + lname + "\","
                        + "\"email\":\"" + email + "\","
                        + "\"password\":\"" + pass + "\","
                        + "\"phoneNumber\":\"" + phnNumber + "\","
                        + "\"address\":\"" + address + "\","
                        + "\"gender\":\"Male\","
                        + "\"termsAccepted\":true,"
                        + "\"role\":\"user\","
                        + "\"profileImage\":null,"
                        + "\"resetPasswordToken\":null,"
                        + "\"resetPasswordExpire\":null,"
                        + "\"createdAt\":\"" + createdAt + "\","
                        + "\"updatedAt\":\"" + updatedAt + "\""
                        + "}")
                .headers("Authorization", "Bearer " + token)
                .when()
                .put("/api/user/" + rehId);

        return response;
    }

    public Response loginByuser() throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/user.json"));
        JSONObject jsonuobj = (JSONObject) jsonArray.get(jsonArray.size() - 2);
        String email = jsonuobj.get("email").toString();
        String password = jsonuobj.get("password").toString();
        Response response = given()
                .contentType("application/json")
                .body(String.format("{\"email\":\"%s\", \"password\":\"%s\"}", email, password))
                .when()
                .post("/api/auth/login");

        return response;
    }

    public Response addItemr() throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/utoken.json"));
        JSONObject jsonuobj = (JSONObject) jsonArray.get(jsonArray.size() - 2);
        String token = jsonuobj.get("uToken").toString();

        Faker fake = new Faker();
        String itemNAme = fake.funnyName().name();
        String randQUan = String.valueOf(Utils.generateRandnum(1, 99));
        String ammnt = String.valueOf(Utils.generateRandnum(10, 9999)); // Fixed missing '='

        Response response = given()
                .contentType("application/json")
                .body(String.format(
                        "{\"itemName\":\"%s\",\n" +
                                "\"quantity\":%s,\n" +
                                "\"amount\":\"%s\",\n" +
                                "\"purchaseDate\":\"2025-01-13\",\n" +
                                "\"month\":\"January\",\n" +
                                "\"remarks\":\"hh\"}",
                        itemNAme, randQUan, ammnt
                ))
                .headers("Authorization", "Bearer " + token)
                .when()
                .post("/api/costs");

        return response;
    }

    public Response getItem() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/utoken.json"));
        JSONObject jsonuobj = (JSONObject) jsonArray.get(jsonArray.size() - 1);
        String token = jsonuobj.get("uToken").toString();

        Response response=given().contentType("application/json").
                headers("Authorization","Bearer "+token).
                when().
                get("/api/costs");

        return response;
    }

    public Response editItem() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/itenList.json"));
        JSONObject jsonuobj = (JSONObject) jsonArray.get(jsonArray.size() - 1);

        String id = jsonuobj.get("id").toString();
        Faker fake = new Faker();
        String itemName = fake.funnyName().name();
        String quantity = jsonuobj.get("quanity").toString();
        String amount = jsonuobj.get("amount").toString();
        String purDate = jsonuobj.get("date").toString();
        String month = jsonuobj.get("month").toString();
        String remark = jsonuobj.get("remark").toString();
        String uid = jsonuobj.get("uid").toString();
        String ucard = jsonuobj.get("ucard").toString();
        String cCard = jsonuobj.get("cCard").toString();

        JSONParser jsonParser2 = new JSONParser();
        JSONArray jsonArray2 = (JSONArray) jsonParser2.parse(new FileReader("./src/test/resources/utoken.json"));
        JSONObject jsonuobj2 = (JSONObject) jsonArray2.get(jsonArray2.size() - 1);
        String token = jsonuobj2.get("uToken").toString();

        // Setting variables in the body
        Response response = given()
                .contentType("application/json")
                .headers("Authorization", "Bearer " + token)
                .body(String.format("{\"_id\":\"%s\",\n" +
                                "\"itemName\":\"%s\",\n" +
                                "\"quantity\":%s,\n" +
                                "\"amount\":%s,\n" +
                                "\"purchaseDate\":\"%s\",\n" +
                                "\"month\":\"%s\",\n" +
                                "\"remarks\":\"%s\",\n" +
                                "\"userId\":\"%s\",\n" +
                                "\"createdAt\":\"%s\",\n" +
                                "\"updatedAt\":\"%s\"}",
                        id, itemName, quantity, amount, purDate, month, remark, uid, cCard, ucard))
                .when()
                .put("/api/costs/" + id);

        return response;
    }

    public Response deleteItem() throws IOException, ParseException {
        // JSON parsing for itemList
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/itenList.json"));
        JSONObject jsonuobj = (JSONObject) jsonArray.get(jsonArray.size() - 1);
        String id = jsonuobj.get("id").toString();


        JSONArray jsonArray2 = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/utoken.json"));
        JSONObject jsonuobj2 = (JSONObject) jsonArray2.get(jsonArray2.size() - 1);
        String token = jsonuobj2.get("uToken").toString();


        Response response = given()
                .contentType("application/json")
                .headers("Authorization", "Bearer " + token)
                .when()
                .delete("/api/costs/" + id);

        return response;
    }





}
