package org.aplas.myapplication.Model;

public class DataUjian {
    private String waktu_selesai;

    private String level;

    private String jurusan;

    private String id_guru;

    private String id_mapel;

    private String id_jurusan;

    private String mapel;

    private String token;

    private String password;

    private String id_ujian;

    private String nip;

    private String nama;

    private String kelas;

    private String jenis;

    private String id_kelas;

    private String durasi;

    private String waktu_mulai;

    private String username;

    private String nilai;

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public String getWaktu_selesai ()
    {
        return waktu_selesai;
    }

    public void setWaktu_selesai (String waktu_selesai)
    {
        this.waktu_selesai = waktu_selesai;
    }

    public String getLevel ()
    {
        return level;
    }

    public void setLevel (String level)
    {
        this.level = level;
    }

    public String getJurusan ()
    {
        return jurusan;
    }

    public void setJurusan (String jurusan)
    {
        this.jurusan = jurusan;
    }

    public String getId_guru ()
    {
        return id_guru;
    }

    public void setId_guru (String id_guru)
    {
        this.id_guru = id_guru;
    }

    public String getId_mapel ()
    {
        return id_mapel;
    }

    public void setId_mapel (String id_mapel)
    {
        this.id_mapel = id_mapel;
    }

    public String getId_jurusan ()
    {
        return id_jurusan;
    }

    public void setId_jurusan (String id_jurusan)
    {
        this.id_jurusan = id_jurusan;
    }

    public String getMapel ()
    {
        return mapel;
    }

    public void setMapel (String mapel)
    {
        this.mapel = mapel;
    }

    public String getToken ()
    {
        return token;
    }

    public void setToken (String token)
    {
        this.token = token;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getId_ujian ()
    {
        return id_ujian;
    }

    public void setId_ujian (String id_ujian)
    {
        this.id_ujian = id_ujian;
    }

    public String getNip ()
    {
        return nip;
    }

    public void setNip (String nip)
    {
        this.nip = nip;
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

    public String getJenis ()
    {
        return jenis;
    }

    public void setJenis (String jenis)
    {
        this.jenis = jenis;
    }

    public String getId_kelas ()
    {
        return id_kelas;
    }

    public void setId_kelas (String id_kelas)
    {
        this.id_kelas = id_kelas;
    }

    public String getDurasi ()
    {
        return durasi;
    }

    public void setDurasi (String durasi)
    {
        this.durasi = durasi;
    }

    public String getWaktu_mulai ()
    {
        return waktu_mulai;
    }

    public void setWaktu_mulai (String waktu_mulai)
    {
        this.waktu_mulai = waktu_mulai;
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
        return "ClassPojo [waktu_selesai = "+waktu_selesai+", level = "+level+", jurusan = "+jurusan+
                ", id_guru = "+id_guru+", id_mapel = "+id_mapel+", id_jurusan = "+id_jurusan+", mapel = "
                +mapel+", token = "+token+", password = "+password+", id_ujian = "+id_ujian+", nip = "+nip
                +", nama = "+nama+", kelas = "+kelas+", jenis = "+jenis+", id_kelas = "+id_kelas+", durasi = "
                +durasi+", waktu_mulai = "+waktu_mulai+", username = "+username+"]";
    }
}
