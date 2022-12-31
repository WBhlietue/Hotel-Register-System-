package com.example.wensql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "cus_code")
    private String cus_code;
    @Column(name = "cusfname")
    private String cusFName;
    @Column(name = "cuslname")
    private String cusLName;
    @Column(name = "cus_birth")
    private String cusBirth;
    @Column(name = "cus_reg")
    private String cusReg;
    @Column(name = "cus_phone")
    private String cusPhone;
    @Column(name = "pass")
    private String pass;
    @Column(name ="isadmin")
    private boolean admin;

    public String getCusCode() {
        return cus_code;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusReg() {
        return cusReg;
    }

    public void setCusReg(String cusReg) {
        this.cusReg = cusReg;
    }

    public String getCusBirth() {
        return cusBirth;
    }

    public void setCusBirth(String cusBirth) {
        this.cusBirth = cusBirth;
    }

    public String getCusLName() {
        return cusLName;
    }

    public void setCusLName(String cusLName) {
        this.cusLName = cusLName;
    }

    public String getCusFName() {
        return cusFName;
    }

    public void setCusFName(String cusFName) {
        this.cusFName = cusFName;
    }

    public void setCusCode(String cusCode) {
        this.cus_code = cusCode;
    }

    @Override
    public String toString() {
        return cusFName + "\n" + cusLName + "\n" + cusBirth + "\n" + cus_code + "\n" + cusReg + "\n" + cusPhone + "\n" + pass + "\n";
     }
}
