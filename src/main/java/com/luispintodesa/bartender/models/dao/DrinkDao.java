package com.luispintodesa.bartender.models.dao;

import com.luispintodesa.bartender.models.Drink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface DrinkDao extends CrudRepository<Drink, Integer> {
    Drink findById(int id);
}
