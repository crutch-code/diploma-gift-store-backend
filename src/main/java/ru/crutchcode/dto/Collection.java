package ru.crutchcode.dto;

public class Collection {
    private String id;

    private String restriction;

    private String age;

    private String gender;

    private String category;

    public String getId() {
        return id;
    }

    public Collection setId(String id) {
        this.id = id;
        return this;
    }

    public String getRestriction() {
        return restriction;
    }

    public String getAge() {
        return age;
    }

    public Collection setAge(String age) {
        this.age = age;
        return this;
    }

    public Collection setRestriction(String restriction) {
        this.restriction = restriction;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Collection setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Collection setCategory(String category) {
        this.category = category;
        return this;
    }
}
