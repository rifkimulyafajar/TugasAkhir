package org.aplas.myapplication.Model;

public class LoginResponse {

    private Login[] data;

    private String status;

    public Login[] getData ()
    {
        return data;
    }

    public void setData (Login[] data)
    {
        this.data = data;
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
        return "ClassPojo [data = "+data+", status = "+status+"]";
    }
}
