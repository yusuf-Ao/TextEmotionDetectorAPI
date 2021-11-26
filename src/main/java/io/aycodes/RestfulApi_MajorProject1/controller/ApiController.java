package io.aycodes.RestfulApi_MajorProject1.controller;

import io.aycodes.RestfulApi_MajorProject1.service.TrainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/emotion_detect")
public class ApiController {

    @Autowired
    private final TrainModel trainModel;


    public ApiController(TrainModel trainModel) {
        this.trainModel = trainModel;
    }

    @PostMapping(path = "", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> analyzeText(@RequestBody String text) {
        List<String> analysis_result = new ArrayList<>();
        analysis_result = trainModel.beginAnalysis(text);
        String result = analysis_result.get(0);
        String confidence = analysis_result.get(1);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("Input", text);
        map.put("Result", result);
        map.put("Confidence", confidence);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
