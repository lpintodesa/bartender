package com.luispintodesa.bartender.models.dao;

import com.luispintodesa.bartender.models.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface IngredientDao extends CrudRepository<Ingredient, Integer> {}
