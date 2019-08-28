package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.*;
import com.luispintodesa.bartender.models.dao.IngredientDao;
import com.luispintodesa.bartender.models.dao.UserDao;
import com.luispintodesa.bartender.models.forms.InventoryForm;
import com.luispintodesa.bartender.models.deserializers.SearchIngredientByNameDeserializer;
import com.luispintodesa.bartender.models.deserializers.ListAllIngredientsDeserializer;
import com.luispintodesa.bartender.models.manipulation.DuplicateCheckForAddIngredient;
import com.luispintodesa.bartender.models.manipulation.ValidationForAddIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Controller
@RequestMapping("mybar")
public class MyBarController extends AbstractController {

    @Autowired
    private IngredientDao ingredientDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "")
    public String inventoryForm(Model model) {
        model.addAttribute("ingredients", ListAllIngredientsDeserializer.convert());
        model.addAttribute(new InventoryForm());
        model.addAttribute("title", "Inventory");
        return "mybar";
    }

    @RequestMapping(value = "", method= RequestMethod.POST)
    public String inventory(Model model, @ModelAttribute InventoryForm form, HttpServletRequest request){

        ArrayList<IngredientInList> list = (ArrayList<IngredientInList>) ListAllIngredientsDeserializer.convert();

        User theUser = getUserFromSession(request.getSession());
        List <Ingredient> ingredientsInInventory = theUser.getIngredients();

        if (!ValidationForAddIngredient.check(form.getIngredientName(), list)){
            model.addAttribute("ingredients", ListAllIngredientsDeserializer.convert());
            model.addAttribute(new InventoryForm());
            model.addAttribute("error", "invalid");
            model.addAttribute("title", "Inventory");
            return "mybar";
        }

        if (DuplicateCheckForAddIngredient.check(form.getIngredientName(), ingredientsInInventory)){
            model.addAttribute("ingredients", ListAllIngredientsDeserializer.convert());
            model.addAttribute(new InventoryForm());
            model.addAttribute("error", "duplicate");
            model.addAttribute("title", "Inventory");
            return "mybar";
        }

        Ingredient newIngredient = (Ingredient) SearchIngredientByNameDeserializer.convert(form.getIngredientName());
        ingredientDao.save(newIngredient);
        theUser.addItem(newIngredient);
        userDao.save(theUser);

        model.addAttribute("ingredients", ListAllIngredientsDeserializer.convert());
        model.addAttribute(new InventoryForm());
        model.addAttribute("error", "false");
        model.addAttribute("title", "Inventory");
        return "mybar";
    }

    @RequestMapping (value="remove", method=RequestMethod.POST)
        public String remove (@ModelAttribute @RequestParam(required = false) int[] ingredientIDs, Model model, HttpServletRequest request){

        if (ingredientIDs==null){
            return "redirect:./";
        }

        User theUser = getUserFromSession(request.getSession());

        for (int ingredientID : ingredientIDs) {

            ListIterator<Ingredient> iter = theUser.getIngredients().listIterator();

            while (iter.hasNext()){
                if (iter.next().getId()==ingredientID){
                    iter.remove();
                }
            }
        }
        userDao.save(theUser);
        return "redirect:./";
    }
}
