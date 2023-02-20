package com.roofflex.restfulwebservices.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.roofflex.restfulwebservices.model.BeanForFiltering;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.roofflex.restfulwebservices.model.BeanForFiltering.beanForFiltering;

@RestController
@RequestMapping("/filtering")
public class FilteringController {

    @GetMapping("/single")
    public ResponseEntity<MappingJacksonValue> getFilteringBean() {
        BeanForFiltering beanForFiltering = beanForFiltering("value1", "value2", "value3");

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(beanForFiltering);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("propertyFilter", filter);
        mappingJacksonValue.setFilters(filterProvider);

        return ResponseEntity.ok()
                .body(mappingJacksonValue);
    }

    @GetMapping("/list")
    public ResponseEntity<List<BeanForFiltering>> getListOfFilteringBeans() {
        List<BeanForFiltering> beanForFilteringList = List.of(
                beanForFiltering("value1", "value2", "value3"),
                beanForFiltering("value4", "value5", "value6"),
                beanForFiltering("value7", "value8", "value9")
        );
        return ResponseEntity.ok()
                .body(beanForFilteringList);
    }


}
