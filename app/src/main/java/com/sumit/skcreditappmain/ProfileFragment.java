package com.sumit.skcreditappmain;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sumit.skcreditappmain.api.RetrofitClient;
import com.sumit.skcreditappmain.model.LogoutUserResponse;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        //theme setting start
        FrameLayout fl_profile = (FrameLayout) view.findViewById(R.id.fl_profile);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        TextView tv_email = (TextView) view.findViewById(R.id.tv_email);
        TextView tv_mobile = (TextView) view.findViewById(R.id.tv_mobile);
        TextView tv_topname = (TextView) view.findViewById(R.id.tv_topname);
        TextView tv_changePass = (TextView) view.findViewById(R.id.tv_changePass);
        TextView tv_updateprofile = (TextView) view.findViewById(R.id.tv_updateprofile);

        SharedPreferences sp = getActivity().getSharedPreferences("user_data",Context.MODE_PRIVATE);
        String name = sp.getString("uname","");
        String email = sp.getString("uemail","");
        String mobile = sp.getString("umobile","");

        tv_name.setText(name);
        tv_email.setText(email);
        tv_mobile.setText(mobile);
        tv_topname.setText(name);

        SharedPreferences getShared = getActivity().getSharedPreferences("settheme", Context.MODE_PRIVATE);
        String mytheme_value = getShared.getString("str", "nothing");
        if (mytheme_value.equals("blue")){
            fl_profile.setBackground(this.getResources().getDrawable(R.drawable.blue_bg));
        }

        if (mytheme_value.equals("nothing")){
            getActivity().setTheme(R.style.Theme_SkCreditAppMain);
        }


        //theme setting end
        //update profile onclick
        tv_updateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), UpdateProfile.class);
                startActivity(i);
            }
        });
        //change password onclick
        tv_changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),ChangePassword.class);
                startActivity(i);
            }
        });
//        ImageView add_member= (ImageView) view.findViewById(R.id.add_member);
        AppCompatButton signout = (AppCompatButton) view.findViewById(R.id.signout);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences sp = getActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);
                String spToken = sp.getString("utoken","");

                if (!spToken.equals("")){
                    Call<LogoutUserResponse> call = RetrofitClient.getInstance().getApi().logout(spToken);
                    call.enqueue(new Callback<LogoutUserResponse>() {
                        @Override
                        public void onResponse(Call<LogoutUserResponse> call, Response<LogoutUserResponse> response) {
                            if (response.isSuccessful()){
                                LogoutUserResponse logoutUserResponse = response.body();
                                Toast.makeText(getActivity(), ""+logoutUserResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                SharedPreferences sp = getActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.clear();
                                editor.commit();


                                Intent i = new Intent(getActivity(), WelcomeScreen.class);
                                startActivity(i);
                                getActivity().finish();


                            }



                        }

                        @Override
                        public void onFailure(Call<LogoutUserResponse> call, Throwable t) {
                            Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }




            }
        });

//        add_member.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), AddCustomer.class);
//                startActivity(i);
//            }
//        });
        return view;
    }


}