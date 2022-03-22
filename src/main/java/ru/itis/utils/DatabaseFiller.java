package ru.itis.utils;

public interface DatabaseFiller <R>{
    void fillWithRandomData(R repository, int count);
}
