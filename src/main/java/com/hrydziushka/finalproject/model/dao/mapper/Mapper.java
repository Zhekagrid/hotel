package com.hrydziushka.finalproject.model.dao.mapper;

import com.hrydziushka.finalproject.model.entity.AbstractEntity;

import java.sql.ResultSet;

public interface Mapper<T extends AbstractEntity>{
    T mapRow(ResultSet resultSet);
}
