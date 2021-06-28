package org.aplas.myapplication.Model;

public class Login {

    public Login(String nis, String nama, String username, String password) {
        this.nis = nis;
        this.nama = nama;
        this.username = username;
        this.password = password;
    }

    private String password;

    private String nama;

    private String kelas;

    private String nis;

    private String id_kelas;

    private String jurusan;

    private String id_jurusan;

    private String id_siswa;

    private String username;

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getNama ()
    {
        return nama;
    }

    public void setNama (String nama)
    {
        this.nama = nama;
    }

    public String getKelas ()
    {
        return kelas;
    }

    public void setKelas (String kelas)
    {
        this.kelas = kelas;
    }

    public String getNis ()
    {
        return nis;
    }

    public void setNis (String nis)
    {
        this.nis = nis;
    }

    public String getId_kelas ()
    {
        return id_kelas;
    }

    public void setId_kelas (String id_kelas)
    {
        this.id_kelas = id_kelas;
    }

    public String getJurusan ()
    {
        return jurusan;
    }

    public void setJurusan (String jurusan)
    {
        this.jurusan = jurusan;
    }

    public String getId_jurusan ()
    {
        return id_jurusan;
    }

    public void setId_jurusan (String id_jurusan)
    {
        this.id_jurusan = id_jurusan;
    }

    public String getId_siswa ()
    {
        return id_siswa;
    }

    public void setId_siswa (String id_siswa)
    {
        this.id_siswa = id_siswa;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [password = "+password+", nama = "+nama+", kelas = "+kelas+", nis = "+nis+", id_kelas = "+id_kelas+", jurusan = "+jurusan+", id_jurusan = "+id_jurusan+", id_siswa = "+id_siswa+", username = "+username+"]";
    }

}
