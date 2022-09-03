package com.example.myapplication.Map;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class ConvenienceMarkerApi {
    private ArrayList<Document> localList = new ArrayList<>();

    public void retrieveLocals(String local, double x, double y,  ConvenienceUseCase convenienceUseCase){
        Call<Category> call = entityConvenience.getSearchCategory("KakaoAK YOUR_RESTAPI_KEY",local,y+"",x+"",1500);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.isSuccessful()){
                    localList.addAll(response.body().getDocuments());
                    convenienceUseCase.onSuccess(localList);
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
    }


    /* Usecase내부 onSucces */
    @Override
    public void onSuccess(Object object) {
        if(object != null){
            list = (ArrayList<Document>)object;
            convenienceFragment.clickSuccess(list);
        }
    }
}
