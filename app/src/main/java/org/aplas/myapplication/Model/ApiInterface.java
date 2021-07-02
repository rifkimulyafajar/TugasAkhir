package org.aplas.myapplication.Model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("Api/Siswa/login")
    Call<LoginResponse> login(@Field("username") String username,
                              @Field("password") String password);


    @FormUrlEncoded
    @PUT("Api/Siswa/siswa/{id_siswa}")
    Call<UpdateResponse> profile(@Path("id_siswa") String id_siswa,
                                 @Field("username") String username, @Field("password") String password);


    @GET("Api/Siswa/materi")
    Call<Materi> getMateri(@Query("id_kelas") String id_kelas,
                           @Query("id_jurusan") String id_jurusan);


    @GET("Api/Siswa/bankSoal")
    Call<BankSoal> getSoal(@Query("id_kelas") String id_kelas,
                           @Query("id_jurusan") String id_jurusan);


    @GET("Api/Siswa/ujian")
    Call<Ujian> getUjian(@Query("id_kelas") String id_kelas,
                           @Query("id_jurusan") String id_jurusan);


    @GET("Api/Siswa/soalujian")
    Call<SoalUjian> getSoalUjian(@Query("id_ujian") String id_ujian,
                                 @Query("jenis") String jenis);

}