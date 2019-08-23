package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.*;
import com.luispintodesa.bartender.models.dao.IngredientDao;
import com.luispintodesa.bartender.models.dao.UserDao;
import com.luispintodesa.bartender.models.forms.InventoryForm;
import com.luispintodesa.bartender.models.jsontopojos.IngredientJSONtoPOJO;
import com.luispintodesa.bartender.models.jsontopojos.IngredientsListJSONToPOJOs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Controller
@RequestMapping("inventory")
public class InventoryController extends AbstractController {

    @Autowired
    private IngredientDao ingredientDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "")
    public String inventoryForm(Model model) {
        model.addAttribute("ingredients", IngredientsListJSONToPOJOs.convert());
        model.addAttribute(new InventoryForm());
        return "inventory";
    }

    @RequestMapping(value = "", method= RequestMethod.POST)
    public String inventory(Model model, @ModelAttribute InventoryForm form, HttpServletRequest request){

        ArrayList<ListIngredient> list = (ArrayList<ListIngredient>) IngredientsListJSONToPOJOs.convert();

        if (!InventoryValidation.check(form.getIngredientName(), list)){
            model.addAttribute("ingredients", IngredientsListJSONToPOJOs.convert());
            model.addAttribute(new InventoryForm());
            model.addAttribute("error", "true");
            return "inventory";
        }

        Ingredient newIngredient = (Ingredient) IngredientJSONtoPOJO.convert(form.getIngredientName());
        ingredientDao.save(newIngredient);
        User theUser = getUserFromSession(request.getSession());
        theUser.addItem(newIngredient);
        userDao.save(theUser);

        model.addAttribute("ingredients", IngredientsListJSONToPOJOs.convert());
        model.addAttribute(new InventoryForm());
        model.addAttribute("error", "false");
        return "inventory";
    }

    @RequestMapping (value="remove", method=RequestMethod.POST)
        public String remove (@RequestParam int[] ingredientIDs, HttpServletRequest request){

        User theUser = getUserFromSession(request.getSession());

        ListIterator<Ingredient> iter = theUser.getIngredients().listIterator();

        for (int ingredientID : ingredientIDs) {
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
