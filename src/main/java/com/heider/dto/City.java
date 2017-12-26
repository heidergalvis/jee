package com.heider.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class City {

    @Id
    private int id;
    private String name;
    private String countrycode;
    private String district;
    private int population;


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

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City csity = (City) o;

        if (getId() != csity.getId()) return false;
        if (getPopulation() != csity.getPopulation()) return false;
        if (!getName().equals(csity.getName())) return false;
        if (!getCountrycode().equals(csity.getCountrycode())) return false;
        return getDistrict().equals(csity.getDistrict());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getCountrycode().hashCode();
        result = 31 * result + getDistrict().hashCode();
        result = 31 * result + getPopulation();
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countrycode='" + countrycode + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }
}
