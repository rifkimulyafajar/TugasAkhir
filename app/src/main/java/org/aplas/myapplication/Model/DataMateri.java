package org.aplas.myapplication.Model;

public class DataMateri {
    private String id_materi;

    private String level;

    private String jurusan;

    private String id_guru;

    private String id_mapel;

    private String id_jurusan;

    private String mapel;

    private String password;

    private String nip;

    private String nama;

    private String kelas;

    private String file2;

    private String file3;

    private String id_kelas;

    private String file1;

    private String username;

    public String getId_materi ()
    {
        return id_materi;
    }

    public void setId_materi (String id_materi)
    {
        this.id_materi = id_materi;
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

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
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

    public String getFile2 ()
    {
        return file2;
    }

    public void setFile2 (String file2)
    {
        this.file2 = file2;
    }

    public String getFile3 ()
    {
        return file3;
    }

    public void setFile3 (String file3)
    {
        this.file3 = file3;
    }

    public String getId_kelas ()
    {
        return id_kelas;
    }

    public void setId_kelas (String id_kelas)
    {
        this.id_kelas = id_kelas;
    }

    public String getFile1 ()
    {
        return file1;
    }

    public void setFile1 (String file1)
    {
        this.file1 = file1;
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
        return "ClassPojo [id_materi = "+id_materi+", level = "+level+", jurusan = "+jurusan+", id_guru = "+id_guru+", id_mapel = "+id_mapel+", id_jurusan = "+id_jurusan+", mapel = "+mapel+", password = "+password+", nip = "+nip+", nama = "+nama+", kelas = "+kelas+", file2 = "+file2+", file3 = "+file3+", id_kelas = "+id_kelas+", file1 = "+file1+", username = "+username+"]";
    }
}
