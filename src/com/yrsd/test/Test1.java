package com.yrsd.test;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/3/23.
 */
public class Test1 {


    public static void main(String[] args) {


        List<WhoBean> whoBeanList = new ArrayList<>();
        DeviceBean deviceBean = new DeviceBean();
        for (int i = 0; i < 5; i++) {
            WhoBean whoBean = new WhoBean();
            whoBean.setId("" + i);
            whoBean.setWho("jake");
            whoBeanList.add(whoBean);
        }
        deviceBean.setName("rose");
        deviceBean.setWhoBeanList(whoBeanList);
        System.out.println(new Gson().toJson(deviceBean));
    }

}
