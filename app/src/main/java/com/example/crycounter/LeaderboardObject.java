package com.example.crycounter;

public class LeaderboardObject {
    public String firstName;
    public String lastName;
    public int cryCount;

    public LeaderboardObject(String f, String l, int c){
       firstName = f;
       lastName = l;
       cryCount = c;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCryCount() {
        return cryCount;
    }

    public void setCryCount(int cryCount) {
        this.cryCount = cryCount;
    }

    public String toString(){
        return firstName + " " + lastName + " " + cryCount + " Cries";
    }
}
