package com.example.lizhi.rko.model;

import java.util.ArrayList;
import java.util.List;

public class DataBean {
    /**
     * id : 1
     * name : 南礼士路99号
     * area_id : 1
     * subdevices : [{"id":1,"name":"","mac":"ECAD243C926B","ud":"01","sensor_id":1}]
     */

    private int id;
    private String name;
    private int area_id;
    public List<Data2Bean> subdevices = new ArrayList<>();
    private boolean b_l;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public List<Data2Bean> getSubdevices() {
        return subdevices;
    }

    public void setSubdevices(List<Data2Bean> subdevices) {
        this.subdevices = subdevices;
    }

    public static class SubdevicesBean {
    }
    public boolean isB_l() {
        return b_l;
    }

    public void setB_l(boolean b_l) {
        this.b_l = b_l;
    }
}

