package com.example.wensql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Room")
public class Room {
    @Id
    @Column(name = "room_id")
    private int roomId;
    @Column(name = "cus_code")
    private String cusCode;
    @Column(name = "r_date")
    private String rDate;
    @Column(name = "price")
    private int price;
    @Column(name = "people")
    private int people;

    public int getRoomId() {
        return roomId;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
