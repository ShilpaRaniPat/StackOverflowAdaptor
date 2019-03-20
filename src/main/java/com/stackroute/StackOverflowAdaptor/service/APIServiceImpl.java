package com.stackroute.StackOverflowAdaptor.service;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stackroute.StackOverflowAdaptor.domain.Items;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class APIServiceImpl implements APIservice, Serializable {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public List<Items> getData() {

        RestAssured.baseURI = "https://api.stackexchange.com";

        // Getting the RequestSpecification of the request
        RequestSpecification httpRequest = RestAssured.given();

        // Making GET request directly by RequestSpecification.get() method
        Response response = httpRequest.get("/2.2/search?pagesize=100&order=desc&sort=votes&intitle=angular&site=stackoverflow");

        //Retrieving Body of response
        String body = response.getBody().asString();
        JSONObject obj = new JSONObject(body);
        Object getItems = obj.get("items");
        log.info("only items" + getItems);

        //using gson to convert jsonstring to object
        Type listType = new TypeToken<ArrayList<Items>>(){}.getType();
        List<Items> list = new Gson().fromJson(getItems.toString(), listType);

        log.info("demo" + list);

        //getting status
        int status = response.getStatusCode();
        log.info("Status code is " + status);

        //Retrieving Status Line
        String statusLine = response.getStatusLine();
        log.info("Status line is "+statusLine);

        //Printing the response
        log.info("Response body is " +body);

        return list;
    }

}
