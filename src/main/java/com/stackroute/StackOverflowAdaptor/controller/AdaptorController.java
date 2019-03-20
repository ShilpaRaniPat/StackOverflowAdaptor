package com.stackroute.StackOverflowAdaptor.controller;

import com.stackroute.StackOverflowAdaptor.service.APIservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*")
public class AdaptorController {

    private APIservice apiService;


    @Autowired
    public AdaptorController(APIservice apiService) {
        this.apiService = apiService;
    }

    @GetMapping("data")
    public ResponseEntity<?> getAllData() {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<>(apiService.getData(), HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
