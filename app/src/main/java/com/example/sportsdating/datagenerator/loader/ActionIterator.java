package com.example.sportsdating.datagenerator.loader;

public interface ActionIterator<T> {
    public boolean hasNext();
    public T next();
}
