package com.ecb.ecb.dto.ecb;

import java.util.List;

public class ParentCube {

    private String date;
    private List<Cube> cubes;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Cube> getCubes() {
        return cubes;
    }

    public void setCubes(List<Cube> cubes) {
        this.cubes = cubes;
    }
}
