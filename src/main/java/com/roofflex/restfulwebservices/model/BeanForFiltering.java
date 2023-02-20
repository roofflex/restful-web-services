package com.roofflex.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.NonNull;

/**
 * Model to refresh filtering knowledge
 */
@JsonIgnoreProperties("field1")
@JsonFilter("propertyFilter")
public class BeanForFiltering {
    private final String field1;
    @JsonIgnore
    private final String field2;
    private final String field3;

    private BeanForFiltering(@NonNull String field1,
                             @NonNull String field2,
                             @NonNull String field3)
    {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public static BeanForFiltering beanForFiltering(@NonNull String field1,
                                                    @NonNull String field2,
                                                    @NonNull String field3)
    {
        return new BeanForFiltering(field1, field2, field3);
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    @Override
    public String toString() {
        return "BeanForFiltering{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                '}';
    }
}
