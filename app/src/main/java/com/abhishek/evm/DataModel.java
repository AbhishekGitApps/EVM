package com.abhishek.evm;

public class DataModel {
    String name;
    String votes;
    String logo;

    public DataModel() {
    }

    public DataModel(String name, String votes, String logo) {

        this.name = name;
        this.votes = votes;
        this.logo = logo;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
