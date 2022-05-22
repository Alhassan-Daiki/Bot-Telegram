package com.ifnti.modele.dao ;

import java.util.ArrayList; ;

public abstract class DAO <T> {
    protected static String connection;

    protected abstract void create(final T object);

    protected abstract void update(final T object);

    protected abstract void delete(final T object);

    protected abstract T findById(final String id);

    protected abstract T findByName(final String pName);

    protected abstract ArrayList<T> getAll();

    static {
    }

    protected T mRead(final String pSearchName) {
        // TODO Auto-generated return
        return null;
    }

    protected boolean insertObject(final String pRequest) {
        // TODO Auto-generated return
        return false;
    }

    protected ArrayList<T> selectObject(final String pRequest) {
        // TODO Auto-generated return
        return null;
    }

    protected int getLastId() {
        // TODO Auto-generated return
        return 0;
    }

}
