package org.aplas.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class Materi
{
    private Data[] data;

    private String message;

    private String status;

    public Data[] getData ()
    {
        return data;
    }

    public void setData (Data[] data)
    {
        this.data = data;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", message = "+message+", status = "+status+"]";
    }
}
