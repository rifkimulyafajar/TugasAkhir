package org.aplas.myapplication.Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("materi")
    Call<Materi> getMateri();
}
