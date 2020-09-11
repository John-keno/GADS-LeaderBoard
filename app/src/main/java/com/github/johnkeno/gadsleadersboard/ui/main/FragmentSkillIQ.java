package com.github.johnkeno.gadsleadersboard.ui.main;

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
import com.github.johnkeno.gadsleadersboard.network.main.IqResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class FragmentSkillIQ extends Fragment {
    List<IqResponse> mDataHour;
    private RecyclerView mView;

    public FragmentSkillIQ() {
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.iq_fragment,container,false);
        mView = root.findViewById(R.id.skill_iq_view);
        mView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<List<IqResponse>>iqData = APIGetClient.sAPIGetService().getIqResponse();
        iqData.enqueue(new Callback<List<IqResponse>>() {
            @Override
            public void onResponse(Call<List<IqResponse>> call, Response<List<IqResponse>> response) {
                if(response.isSuccessful()){
                    mDataHour = response.body();
                    Log.d(TAG, "onResponse: "+ mDataHour);
                    mView.setAdapter(new SkillRecyclerViewAdapter(getContext(),mDataHour));
                }
                else {
                    Toast.makeText(getActivity(),"an error occurred ",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<IqResponse>> call, Throwable t) {
                Toast.makeText(getContext(),"an error occurred "
                        +t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });



        return root;
    }

}
