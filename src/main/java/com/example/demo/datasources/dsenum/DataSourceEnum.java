package com.example.demo.datasources.dsenum;

import lombok.Data;

/**
 * @author chenxue
 * @Description
 * @Date 2019/9/20 14:53
 */
public enum DataSourceEnum {
    DS1("ds1"),DS2("ds2");
    private String value;

    DataSourceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
