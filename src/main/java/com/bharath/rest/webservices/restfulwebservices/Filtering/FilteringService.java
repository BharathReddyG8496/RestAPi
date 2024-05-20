package com.bharath.rest.webservices.restfulwebservices.Filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

@Service
public class FilteringService {



public MappingJacksonValue JackReturn(Object policeCase){
    MappingJacksonValue mappingJacksonValue= new MappingJacksonValue(policeCase);
    SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("placeOfCrime","crimeDate");
    FilterProvider filters = new SimpleFilterProvider().addFilter("PoliceCaseFilter",filter);
    mappingJacksonValue.setFilters(filters);
    return mappingJacksonValue;

    // Used to dynamically filter the sending of certain properties of the bean, i.e passwords etc which obviously you dont
    //want to send it to the user, so we make use of MappingJacksonValue to wrap the object and create filters using SimpleBeanPropertyFilter
    //and then joining the name of the bean i.e,. in line 17 the same name has to be present in the annotation JaksonFilter above
    // the bean you are filtering. Since we are using various filters for different methods we call it dynamic filtering.
}
}
