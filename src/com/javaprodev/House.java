package com.javaprodev;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by X on 8/25/2017.
 */
public class House {
    private int roomCount;
    private int kitchenArea;
    private int bathroomCount;
    private String address;

    @DynamicProperty
    private boolean petsAllowed;

    @DynamicProperty
    private boolean smokingAllowed;

    @DynamicProperty
    private String ownerName;

    @DynamicProperty
    private String paintColor;

    public House(int roomCount, int kitchenArea, int bathroomCount, String address, boolean petsAllowed, boolean smokingAllowed, String ownerName, String paintColor) {
        this.roomCount = roomCount;
        this.kitchenArea = kitchenArea;
        this.bathroomCount = bathroomCount;
        this.address = address;
        this.petsAllowed = petsAllowed;
        this.smokingAllowed = smokingAllowed;
        this.ownerName = ownerName;
        this.paintColor = paintColor;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getKitchenArea() {
        return kitchenArea;
    }

    public void setKitchenArea(int kitchenArea) {
        this.kitchenArea = kitchenArea;
    }

    public int getBathroomCount() {
        return bathroomCount;
    }

    public void setBathroomCount(int bathroomCount) {
        this.bathroomCount = bathroomCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public boolean isSmokingAllowed() {
        return smokingAllowed;
    }

    public void setSmokingAllowed(boolean smokingAllowed) {
        this.smokingAllowed = smokingAllowed;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPaintColor() {
        return paintColor;
    }

    public void setPaintColor(String paintColor) {
        this.paintColor = paintColor;
    }


    @Override
    public String toString() {
        return "House{" +
                "roomCount=" + roomCount +
                ", kitchenArea=" + kitchenArea +
                ", bathroomCount=" + bathroomCount +
                ", address='" + address + '\'' +
                ", petsAllowed=" + petsAllowed +
                ", smokingAllowed=" + smokingAllowed +
                ", ownerName='" + ownerName + '\'' +
                ", paintColor='" + paintColor + '\'' +
                '}';
    }
}
