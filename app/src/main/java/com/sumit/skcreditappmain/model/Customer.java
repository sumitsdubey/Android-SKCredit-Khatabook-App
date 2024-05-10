package com.sumit.skcreditappmain.model;

import androidx.annotation.NonNull;

public class Customer {
    public int id;
    public String name;
    public String mobile;
    public String email;
    public String address;
    public String date_time;
    public String created_by;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getDate_time() {
        return date_time;
    }

    public String getCreated_by() {
        return created_by;
    }

    @NonNull
    @Override
    public String toString() {
        return name.toString();
    }
}
