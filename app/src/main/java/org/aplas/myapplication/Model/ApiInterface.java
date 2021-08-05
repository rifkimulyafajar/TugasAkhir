package org.aplas.myapplication.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers("API-KEY: " + "smaismai")
    @FormUrlEncoded
    @POST("Api/Siswa/login")
    Call<LoginResponse> login(@Field("username") String username,
                              @Field("password") String password);


    @Headers("API-KEY: " + "smaismai")
    @FormUrlEncoded
    @PUT("Api/Siswa/siswa/{id_siswa}")
    Call<UpdateResponse> profile(@Path("id_siswa") String id_siswa,
                                 @Field("username") String username, @Field("password") String password);


    @Headers("API-KEY: " + "smaismai")
    @GET("Api/Siswa/materi")
    Call<Materi> getMateri(@Query("id_kelas") String id_kelas,
                           @Query("id_jurusan") String id_jurusan);


    @Headers("API-KEY: " + "smaismai")
    @GET("Api/Siswa/bankSoal")
    Call<BankSoal> getSoal(@Query("id_kelas") String id_kelas,
                           @Query("id_jurusan") String id_jurusan);


    @Headers("API-KEY: " + "smaismai")
    @GET("Api/Siswa/ujian")
    Call<Ujian> getUjian(@Query("id_kelas") String id_kelas,
                         @Query("id_jurusan") String id_jurusan);


    @Headers("API-KEY: " + "smaismai")
    @GET("Api/Siswa/soalujian")
    Call<SoalUjian> getSoalUjian(@Query("id_ujian") String id_ujian,
                                 @Query("jenis") String jenis);


    @Headers("API-KEY: " + "smaismai")
    @FormUrlEncoded
    @POST("Api/Siswa/ujian")
    Call<SoalUjian> sendSoalUjian(@Field("id_ujian") String id_ujian,
                                  @Field("id_siswa") String id_siswa,
                                  @Field("jml_benar") String jml_benar,
                                  @Field("nilai") String nilai);


    @Headers("API-KEY: " + "smaismai")
    @GET("Api/Siswa/hasil_ujian")
    Call<HasilUjian> getHasilUjian(@Query("id_siswa") String id_siswa);


//    @Headers("API-KEY: " + "smaismai")
//    @GET("Api/Siswa/cek_siswa")
//    Call<HasilUjianSiswa> getHasilUjianSiswa(@Query("id_siswa") String id_siswa);

}