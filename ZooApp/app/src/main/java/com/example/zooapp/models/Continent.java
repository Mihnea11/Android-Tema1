package com.example.zooapp.models;

public enum Continent
{
    EUROPE(0), ASIA(1), AFRICA(2), AMERICA(3), AUSTRALIA(4);

    private final int key;

    private Continent(int key)
    {
        this.key = key;
    }

    public int getKey()
    {
        return key;
    }
}
