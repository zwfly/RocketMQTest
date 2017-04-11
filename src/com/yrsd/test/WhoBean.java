package com.yrsd.test;

import java.io.Serializable;

/**
 * Created by admin on 2017/3/21.
 */

public class WhoBean implements Serializable {

    String who;
String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
