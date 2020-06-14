package com.example.customactionbar;

import java.time.LocalDateTime;

class Person {
    private String itemName;
    private String itemDescription;
    private String itemHospital;
    private LocalDateTime itemTime;

    public Person(String name, String description,String hospital,LocalDateTime time) {
        this.itemName = name;
        this.itemDescription = description;
        this.itemHospital = hospital;
        this.itemTime = time;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }
    public String getItemHospital() {
        return itemHospital;
    }
    public LocalDateTime getItemTime(){
        return itemTime;
    }

}