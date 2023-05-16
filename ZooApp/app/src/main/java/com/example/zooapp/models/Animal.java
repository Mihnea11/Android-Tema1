package com.example.zooapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Animals")
public class Animal
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private Continent continent;

    public Animal(String name, Continent continent)
    {
        this.name = name;
        this.continent = continent;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Continent getContinent()
    {
        return continent;
    }

    public void setContinent(Continent continent)
    {
        this.continent = continent;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
