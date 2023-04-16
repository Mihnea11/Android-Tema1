package com.example.zooapp.Utility;

public enum Continents
{
    Europe(0), Africa(1), Asia(2), America(3), Australia(4);

    private int value;
    Continents(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}
