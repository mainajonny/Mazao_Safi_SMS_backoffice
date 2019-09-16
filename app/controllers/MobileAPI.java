package controllers;

import models.Mfarmer_numbers;
import play.mvc.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Page;
import models.*;
import org.codehaus.jackson.node.ArrayNode;
import play.*;
import org.codehaus.jackson.node.ObjectNode;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import play.mvc.Result;

import java.util.List;
import java.util.Map;

/**
 * Created by maina on 9/2/2019.
 */
public class MobileAPI extends Controller {

    public static Result SendingcustomerSMS(){
        ObjectNode result;
        result = Json.newObject();
        String FinalMessage;
        String UsersPhoneNumbers="";
        String allPhoneNumbers="";
        String final_phone_tosend="";
        String resp = "";

        DynamicForm requestCredentials = Form.form().bindFromRequest();
        String sms = requestCredentials.get("message");
        String type = requestCredentials.get("type");

        System.out.println("type: " + type);
        System.out.println("message: " + sms);

        try {
            FinalMessage= URLEncoder.encode(sms, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }

        if(type.equals("All")){
            List<Mcustomer_numbers> mcustomers = Mcustomer_numbers.findAll();
            for (int i = 0; i < mcustomers.size(); i++) {
                String PhoneNumber = mcustomers.get(i).Tel;
                if (UsersPhoneNumbers.equals("")) {
                    UsersPhoneNumbers = PhoneNumber;
                } else {
                    UsersPhoneNumbers = UsersPhoneNumbers + "," + PhoneNumber;
                }
                System.out.println("numbers: " + PhoneNumber);
            }

            System.out.println("stringNumbers: "+UsersPhoneNumbers);

            String [] App_Users_Numbers=UsersPhoneNumbers.split(",");


            for(int g=0 ; g<App_Users_Numbers.length; g++){
                if(!allPhoneNumbers.contains(App_Users_Numbers[g]))
                {allPhoneNumbers=allPhoneNumbers+","+App_Users_Numbers[g];}
            }


            final_phone_tosend= allPhoneNumbers.substring(1,allPhoneNumbers.length());
            System.out.println("final_phone_tosend: "+final_phone_tosend);

        }else {

            List<Mcustomer_numbers> mcustomer_numbers = Mcustomer_numbers.findByType(type);
            for (int i = 0; i < mcustomer_numbers.size(); i++) {
                String PhoneNumber = mcustomer_numbers.get(i).Tel;
                if (UsersPhoneNumbers.equals("")) {
                    UsersPhoneNumbers = PhoneNumber;
                } else {
                    UsersPhoneNumbers = UsersPhoneNumbers + "," + PhoneNumber;
                }
                System.out.println("numbers: " + PhoneNumber);
            }

            System.out.println("stringNumbers: " + UsersPhoneNumbers);

            String[] App_Users_Numbers = UsersPhoneNumbers.split(",");


            for (int g = 0; g < App_Users_Numbers.length; g++) {
                if (!allPhoneNumbers.contains(App_Users_Numbers[g])) {
                    allPhoneNumbers = allPhoneNumbers + "," + App_Users_Numbers[g];
                }
            }


            final_phone_tosend = allPhoneNumbers.substring(1, allPhoneNumbers.length());
            System.out.println("final_phone_tosend: " + final_phone_tosend);
        }

            String message = "&message=" + FinalMessage;
            String numbers = "&to=" + final_phone_tosend;

            try{
                URL obj = new URL("https://api.africastalking.com/restless/send?username=mainajonny&Apikey=894ed3da61456fbf80e2eb690271e87f6e8d1254e0c982a084de059f595166bd" + numbers + message);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                int responseCode = con.getResponseCode();
                System.out.println("GET Response Code :: " + responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) { // success
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    resp = String.valueOf(responseCode);
                    System.out.println(response.toString());
                } else {
                    System.out.println("GET request not worked");
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            result.put("responseCode", resp);

        return ok(result);
    }

    public static Result SendingfarmerSMS(){
        ObjectNode result;
        result = Json.newObject();
        String FinalMessage;
        String UsersPhoneNumbers="";
        String allPhoneNumbers="";
        String final_phone_tosend="";
        String resp = "";

        DynamicForm requestCredentials = Form.form().bindFromRequest();
        String sms = requestCredentials.get("message");

        System.out.println("message: " + sms);

        try {
            FinalMessage= URLEncoder.encode(sms, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }

            List<Mfarmer_numbers> mfarmers = Mfarmer_numbers.findAll();
            for (int i = 0; i < mfarmers.size(); i++) {
                String PhoneNumber = mfarmers.get(i).Tel;
                if (UsersPhoneNumbers.equals("")) {
                    UsersPhoneNumbers = PhoneNumber;
                } else {
                    UsersPhoneNumbers = UsersPhoneNumbers + "," + PhoneNumber;
                }
                System.out.println("numbers: " + PhoneNumber);
            }

            System.out.println("stringNumbers: "+UsersPhoneNumbers);

            String [] App_Users_Numbers=UsersPhoneNumbers.split(",");


            for(int g=0 ; g<App_Users_Numbers.length; g++){
                if(!allPhoneNumbers.contains(App_Users_Numbers[g]))
                {allPhoneNumbers=allPhoneNumbers+","+App_Users_Numbers[g];}
            }


            final_phone_tosend= allPhoneNumbers.substring(1,allPhoneNumbers.length());
            System.out.println("final_phone_tosend: "+final_phone_tosend);

            String message = "&message=" + FinalMessage;
            String numbers = "&to=" + final_phone_tosend;

            try{
                URL obj = new URL("https://api.africastalking.com/restless/send?username=mainajonny&Apikey=894ed3da61456fbf80e2eb690271e87f6e8d1254e0c982a084de059f595166bd" + numbers + message);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                int responseCode = con.getResponseCode();
                System.out.println("GET Response Code :: " + responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) { // success
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    resp = String.valueOf(responseCode);
                    System.out.println(response.toString());
                } else {
                    System.out.println("GET request not worked");
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        result.put("responseCode", resp);

        return ok(result);
    }


}
