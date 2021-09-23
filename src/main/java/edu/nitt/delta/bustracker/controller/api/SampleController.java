package edu.nitt.delta.bustracker.controller.api;

import edu.nitt.delta.bustracker.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @Autowired private SampleService sampleService;

    @GetMapping
    public ResponseEntity<String> getSampleResponse() {
        String response = sampleService.getSampleResponse();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
