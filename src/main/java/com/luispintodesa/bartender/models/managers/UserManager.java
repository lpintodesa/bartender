package com.luispintodesa.bartender.models.managers;

import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.User;
import com.luispintodesa.bartender.models.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserManager {

    private final UserDao userDao;

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
