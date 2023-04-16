package com.example.zooapp.Utility;

import java.io.Serializable;

public class Animal implements Serializable
{
    private String name;
    private Continents continent;

    public Animal(String name, Continents continent)
    {
        this.name = name;
        this.continent = continent;
    }

    public String getName()
    {
        return name;
    }

    public Continents getContinent()
    {
        return continent;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setContinent(Continents continent)
    {
        this.continent = continent;
    }
}
