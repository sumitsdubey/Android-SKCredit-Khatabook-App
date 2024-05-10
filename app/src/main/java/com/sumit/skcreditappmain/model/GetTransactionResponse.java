package com.sumit.skcreditappmain.model;


import java.util.ArrayList;

public class GetTransactionResponse{
    public int code;
    public String message;
    public boolean status;
    public ArrayList<Transaction> data;
    public ArrayList<ExtraData> extra_data;
    public boolean error;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }

    public ArrayList<Transaction> getData() {
        return data;
    }

    public ArrayList<ExtraData> getExtra_data() {
        return extra_data;
    }

    public boolean isError() {
        return error;
    }
}

