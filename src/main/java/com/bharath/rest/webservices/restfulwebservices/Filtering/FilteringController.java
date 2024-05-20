package com.bharath.rest.webservices.restfulwebservices.Filtering;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    @Autowired
    FilteringService filteringService;


    @GetMapping("/Case")
    public MappingJacksonValue CaseReturn(){
        PoliceCase policeCase = new PoliceCase("100", "Car stolen", "GandhiNagar", LocalDate.now());
       return filteringService.JackReturn(policeCase);
    }


    @GetMapping("/Cases")
    public MappingJacksonValue CasesReturn(){
        List<PoliceCase> list = Arrays.asList(new PoliceCase("100", "Car stolen", "GandhiNagar", LocalDate.now()), new PoliceCase("101", "Purse stolen", "Kanakapura", LocalDate.now()));
        return filteringService.JackReturn(list);
    }
}
