package com.github.johnkeno.gadsleadersboard.ui.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.johnkeno.gadsleadersboard.R;
import com.github.johnkeno.gadsleadersboard.network.main.APIGetClient;
import com.github.johnkeno.gadsleadersboard.network.main.HourResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static androidx.core.content.ContextCompat.getSystemService;

public class FragmentLearn extends Fragment {
    List<HourResponse> mData;
    private RecyclerView mView;

    public FragmentLearn() {
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.hour_fragment,container,false);
        mView = root.findViewById(R.id.learn_view);
        mView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<List<HourResponse>> hourData = APIGetClient.sAPIGetService().getHourResponse();
        hourData.enqueue(new Callback<List<HourResponse>>() {
            @Override
            public void onResponse(Call<List<HourResponse>> call, Response<List<HourResponse>> response) {
                if (response.isSuccessful()){
                    mData = response.body();
                    Log.d(TAG, "onResponse: "+ mData);
                    mView.setAdapter(new HourRecyclerViewAdapter(getContext(),mData));
                }
                else {
                    Toast.makeText(getActivity(),"an error occurred. code: "
                            + response.code(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<HourResponse>> call, Throwable t) {
                Toast.makeText(getContext(),"No Internet Connection",Toast.LENGTH_LONG).show();

            }
        });



         return root;
    }
}

