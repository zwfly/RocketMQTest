package com.yrsd.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/3/23.
 */
public class DeviceBean {

    String name;
    List<WhoBean> whoBeanList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WhoBean> getWhoBeanList() {
        return whoBeanList;
    }

    public void setWhoBeanList(List<WhoBean> whoBeanList) {
        this.whoBeanList = whoBeanList;
    }
}
