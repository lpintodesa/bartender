package com.luispintodesa.bartender.models;

import com.luispintodesa.bartender.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class UserManager {
    @Autowired
    protected UserDao userDao;

    public User findUserByUsername(String username){
        return userDao.findByUsername(username);
    }

    public User findUserById (int id){
        return userDao.findById(id);
    }

    public void saveUser (User theUser){
        userDao.save(theUser);
    }

    public void addIngredientAndSave (User theUser, Ingredient newIngredient){
        theUser.addIngredient(newIngredient);
        userDao.save(theUser);
    }

    public void removeIngredientsAndSave(User theUser, Integer [] ingredientIDs){
        theUser.getIngredients().removeIf(ingredient -> Arrays.asList(ingredientIDs).contains(ingredient.getId()));
        userDao.save(theUser);
    }
}
