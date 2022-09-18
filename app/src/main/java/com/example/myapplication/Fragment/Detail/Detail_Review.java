// 작성자 : 김도윤
// 음식점 리뷰 페이지
// Update : 22.08.20

package com.example.myapplication.Fragment.Detail;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.Adapter.Review_Adapter;
import com.example.myapplication.Data.Detail_Review_ResponseData;
import com.example.myapplication.Data.ReviewArrayData;
import com.example.myapplication.Interface.onReviewItemDelete;
import com.example.myapplication.databinding.FragmentDetailReviewBinding;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_Review extends Fragment implements onReviewItemDelete {
    //private View view;
   // private RecyclerView recyclerView;
    private Review_Adapter adapter;
    private String restaurant_id;
    private FragmentDetailReviewBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailReviewBinding.inflate(getLayoutInflater(), container, false);
        //view = inflater.inflate(R.layout.detail_review, container,false);

        restaurant_id = getArguments().getString("restaurant_id");

        //리뷰 recyclerview adapter 설정
        //recyclerView = view.findViewById(R.id.review_recyclerView);
        adapter = new Review_Adapter(getContext(), null, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true);
        linearLayoutManager.setStackFromEnd(true);
        binding.reviewRecyclerView.setLayoutManager(linearLayoutManager);
        binding.reviewRecyclerView.setAdapter(adapter);

        //리뷰 검색
        Call<ReviewArrayData> call = RetrofitClient.getApiService().Review_list(restaurant_id);
        retrofit(call);

        // 리뷰 입력
        //Button review_input = view.findViewById(R.id.review_input);
        binding.reviewInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(getContext());
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("리뷰 작성")
                        .setView(editText)
                        .setPositiveButton("등록", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String text = editText.getText().toString();
                                Call<ReviewArrayData> call = RetrofitClient.getApiService()
                                        .Review_input(restaurant_id, text);
                                retrofit(call);
                            }
                        })
                        .setNegativeButton("취소", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        return binding.getRoot();
    }

    //리뷰 삭제
    @Override
    public void onReviewItemDelete(int review_id) {
        Call<ReviewArrayData> call = RetrofitClient.getApiService()
                .Review_delete(review_id);
        retrofit(call);
    }

    //리뷰 데이터 조회&삽입
    public void retrofit (Call<ReviewArrayData> call){
        ArrayList<Detail_Review_ResponseData> responseData = new ArrayList<>();
        call.enqueue((new Callback<ReviewArrayData>() {
            @Override
            public void onResponse(Call<ReviewArrayData> call, Response<ReviewArrayData> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                    return;
                }
                ReviewArrayData review_Array_data = response.body();

                Detail_Review_ResponseData[] data = review_Array_data != null
                        ? review_Array_data.getReview_data()
                        :new Detail_Review_ResponseData[0];

                for (int i = 0; i < data.length; i++){
                    int Review_id = data[i].getReview_id();
                    String Username = data[i].getUsername();
                    String Content = data[i].getContent();
                    String Date = data[i].getDate();

                    Date = Date.replace("T", " ");

                    responseData.add(new Detail_Review_ResponseData(
                            Review_id,
                            Username,
                            Content,
                            Date
                    ));
                }

                adapter.setData(responseData);
                adapter.notifyDataSetChanged();

            }
            // 서버 통신 실패 시
            @Override
            public void onFailure(Call<ReviewArrayData> call, Throwable t) {
                Log.e("연결실패", t.getMessage());
                Toast.makeText(getContext(), "연결 실패",Toast.LENGTH_SHORT).show();
            }
        }));
    }

}
