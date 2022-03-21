package com.models;

import java.util.Vector;

public class DataCache {
    static private DataCache dataCache;

    public User user;
    public AuthToken authToken;
    public Vector<Friend> friendList;
    public Vector<Beacon> beaconList;

    private DataCache() {

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

    public Vector<Friend> getFriendList() {
        return friendList;
    }

    public void setFriendList(Vector<Friend> friendList) {
        this.friendList = friendList;
    }

    public Vector<Beacon> getBeaconList() {
        return beaconList;
    }

    public void setBeaconList(Vector<Beacon> beaconList) {
        this.beaconList = beaconList;
    }
}
