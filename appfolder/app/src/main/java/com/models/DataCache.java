package com.models;

import com.util.FakeData;

import java.util.List;
import java.util.Vector;

public class DataCache {
    static private DataCache dataCache;

    public User user;
    public AuthToken authToken;
    public LocationData locationData;
    public List<Friend> friendList;
    public List<Beacon> beaconList;
    public Beacon currUserBeacon;
    public Beacon currentBeacon;        // This is the currently selected beacon.

    private DataCache() {
        FakeData fake = new FakeData();
        this.user = new User("Jason Smith", "@jsmith",
                "https://this-person-does-not-exist.com/img/avatar-8803d6bfbec2aec3dc09df371a4c25f3.jpg");
        this.authToken = fake.getAuthToken();
        this.locationData = fake.getStartLocationData();
        this.friendList = fake.getFakeFriends();
        this.beaconList = fake.getFakeBeacons();
        currUserBeacon = null;
        currentBeacon = null;
        System.out.println(toString());
    }

    static public DataCache getInstance() {
        if (dataCache == null)
            dataCache = new DataCache();
        return dataCache;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public List<Friend> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<Friend> friendList) {
        this.friendList = friendList;
    }

    public List<Beacon> getBeaconList() {
        return beaconList;
    }

    public void setBeaconList(List<Beacon> beaconList) {
        this.beaconList = beaconList;
    }

    public String toString() {
        String output = "Current user: " + this.user.getUserName() + "\nFriend List\n" +
                "Current Lat: " + this.locationData.getLatitude() + " Long: " + this.locationData.getLongitude()
                +"\n";
        for(Friend f : friendList) {
            output += "Friend: " + f.getUserName() + "\n";
        }
        output += "Beacons\n";
        for(Beacon b : beaconList) {
            output += "Beacons: " + b.getTitle() + "Desc: " + b.getDescription()
                    +  " Lat: " + b.getLatitude() + " Long: " +b.getLongitude() + "\n";
        }
        return output;
    }

    public void setCurrUserBeacon(Beacon beacon) {this.currUserBeacon = beacon;}
    public Beacon getCurrUserBeacon() {return this.currUserBeacon;}

    public LocationData getLocationData() {
        return locationData;
    }

    public void setLocationData(LocationData locationData) {
        this.locationData = locationData;
    }

    public Beacon getCurrentBeacon() {
        return currentBeacon;
    }

    public void setCurrentBeacon(Beacon currentBeacon) {
        this.currentBeacon = currentBeacon;
    }
}
