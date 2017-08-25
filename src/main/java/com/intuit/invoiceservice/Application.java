package com.intuit.invoiceservice;

import org.springframework.web.bind.annotation.*;

@RestController
public class Application {

    /*@RequestMapping(value ="/createInvoice", method = RequestMethod.POST, consumes = "text/plain")
    public ResponseEntity<String> createInvoice(@RequestBody String payload) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } */

    @RequestMapping(value ="/createInvoice", method = RequestMethod.POST, consumes = "application/json")
    public void createInvoice(@RequestBody String payload) {
        System.out.println(payload);
    }

}
