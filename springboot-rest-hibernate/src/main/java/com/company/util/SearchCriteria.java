package com.company.util;

public class SearchCriteria
{
    private String key;
    private Object value;


    public SearchCriteria(String key, Object value)
    {
        super();
        this.key = key;
        this.value = value;
    }


    public String getKey()
    {
        return key;
    }


    public void setKey(String key)
    {
        this.key = key;
    }


    public Object getValue()
    {
        return value;
    }


    public void setValue(Object value)
    {
        this.value = value;
    }


    @Override
    public String toString()
    {
        return "SearchCriteria [key=" + key + ", value=" + value + "]";
    }

}
