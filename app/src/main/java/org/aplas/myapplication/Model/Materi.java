package org.aplas.myapplication.Model;

public class Materi
{
    private DataMateri[] data;

    private String message;

    private String status;

    public DataMateri[] getData ()
    {
        return data;
    }

    public void setData (DataMateri[] data)
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
