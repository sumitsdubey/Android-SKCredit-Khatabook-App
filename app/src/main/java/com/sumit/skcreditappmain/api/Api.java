package com.sumit.skcreditappmain.api;


import com.sumit.skcreditappmain.model.AddCustomerResponse;
import com.sumit.skcreditappmain.model.AddTransactionResponse;
import com.sumit.skcreditappmain.model.CreateUserResponse;
import com.sumit.skcreditappmain.model.CustomerResponse;
import com.sumit.skcreditappmain.model.LogoutUserResponse;
import com.sumit.skcreditappmain.model.UserLoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
//  @POST("createuser.php") //Raw => Json
//    Call<Users> createUser(@FieldMap HashMap<String,String> user);


   @FormUrlEncoded //String => get
   @POST("createuser.php")
    Call<CreateUserResponse> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("password") String password
   );

//    @POST("createuser.php") //body => post
//    Call<Users> createUser(@Body Users users);

    @FormUrlEncoded
    @POST("userlogin.php")
    Call<UserLoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("logoutuser.php")
    Call<LogoutUserResponse> logout(
            @Field("token") String token
    );

    @FormUrlEncoded
    @POST("addcustomer.php")
    Call<AddCustomerResponse> addcustomer(
           @Field("name") String name,
           @Field("email") String email,
           @Field("mobile") String mobile,
           @Field("address") String address,
           @Field("user_id") String user_id
    );

   @FormUrlEncoded
   @POST("getcustomer.php")
   Call<CustomerResponse> getcustomer(
           @Field("user_id") int user_id
   );

    @FormUrlEncoded
    @POST("addtransaction.php")
    Call<AddTransactionResponse> addTransaction(
         @Field("user_id") int user_id,
         @Field("customer_id") int customer_id,
         @Field("title") String title,
         @Field("amount") String amount,
         @Field("type") String type,
         @Field("description") String description
   );

}
