package com.github.bvigentas.rickandmorty;

import java.io.Serializable;
import java.util.ArrayList;

public class Character implements Serializable {

    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Origin OriginObject;
    private Location LocationObject;
    private String image;
    private ArrayList<String> episode = new ArrayList<>();
    private String url;
    private String created;

    public Character() {
    }

    public Character(int id, String name, String status, String species, String type, String gender, Origin originObject, Location locationObject, String image, ArrayList<String> episode, String url, String created) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        OriginObject = originObject;
        LocationObject = locationObject;
        this.image = image;
        this.episode = episode;
        this.url = url;
        this.created = created;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Origin getOriginObject() {
        return OriginObject;
    }

    public void setOriginObject(Origin originObject) {
        OriginObject = originObject;
    }

    public Location getLocationObject() {
        return LocationObject;
    }

    public void setLocationObject(Location locationObject) {
        LocationObject = locationObject;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<String> getEpisode() {
        return episode;
    }

    public void setEpisode(ArrayList<String> episode) {
        this.episode = episode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
