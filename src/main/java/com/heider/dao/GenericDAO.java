package com.heider.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {

    T findById(ID id, boolean lock);
    List<T> findAll();

}
