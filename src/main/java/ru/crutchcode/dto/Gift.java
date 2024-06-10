package ru.crutchcode.dto;

import java.util.List;

public class Gift {

    private int id;

    private String picture;

    private String href;

    private String name;

    private String description;

    private Double price;

    private List<GiftRelativity> relativity;

    public int getId() {
        return id;
    }

    public Gift setId(int id) {
        this.id = id;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public Gift setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getName() {
        return name;
    }

    public Gift setName(String name) {
        this.name = name;
        return this;
    }


    public String getHref() {
        return href;
    }

    public Gift setHref(String href) {
        this.href = href;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Gift setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Gift setPrice(Double price) {
        this.price = price;
        return this;
    }

    public List<GiftRelativity> getRelativity() {
        return relativity;
    }

    public Gift setRelativity(List<GiftRelativity> relativity) {
        this.relativity = relativity;
        return this;
    }
}
