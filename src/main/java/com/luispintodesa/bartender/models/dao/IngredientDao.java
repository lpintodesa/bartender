package com.luispintodesa.bartender.models.dao;

import com.luispintodesa.bartender.models.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface IngredientDao extends CrudRepository<Ingredient, Integer> {}
