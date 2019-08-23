package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.*;
import com.luispintodesa.bartender.models.dao.UserDao;
import com.luispintodesa.bartender.models.forms.InventoryForm;
import com.luispintodesa.bartender.models.forms.WhatCanIMakeForm;
import com.luispintodesa.bartender.models.jsontopojos.DrinkInListJSONtoPOJOs;
import com.luispintodesa.bartender.models.jsontopojos.IngredientJSONtoPOJO;
import com.luispintodesa.bartender.models.jsontopojos.IngredientsListJSONToPOJOs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("whatcanimake")
public class WhatCanIMakeController extends AbstractController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "")
    public String inventoryForm(Model model) {
        model.addAttribute(new WhatCanIMakeForm());
        return "whatcanimake";
    }

    @RequestMapping(value = "", method= RequestMethod.POST)
    public String inventory(Model model, @ModelAttribute WhatCanIMakeForm form, HttpServletRequest request){

       // if (!InventoryValidation.check(form.getIngredientName(), list)){
            //model.addAttribute("ingredients", IngredientsListJSONToPOJOs.convert());
            //model.addAttribute(new InventoryForm());
            //model.addAttribute("error", "true");
            //return "inventory";

        String cocktailName = SpaceToUnderscore.convert(form.getCocktailName());
        ArrayList<DrinkInList> drinks = (ArrayList<DrinkInList>) DrinkInListJSONtoPOJOs.convert(cocktailName);
        ArrayList<ArrayList<DrinkInList>> lists = ArrayListDivider.divide(drinks);
        ArrayList<DrinkInList> one = lists.get(0);
        ArrayList<DrinkInList> two = lists.get(1);
        ArrayList<DrinkInList> three = lists.get(2);

        model.addAttribute("one", one);
        model.addAttribute("two", two);
        model.addAttribute("three", three);
        return "results";
    }
}
