package com.luispintodesa.bartender.controllers;

import com.luispintodesa.bartender.models.Deserializer;
import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.IngredientInList;
import com.luispintodesa.bartender.models.User;
import com.luispintodesa.bartender.models.dao.IngredientDao;
import com.luispintodesa.bartender.models.dao.UserDao;
import com.luispintodesa.bartender.models.forms.MyBarForm;
import com.luispintodesa.bartender.models.manipulation.DuplicateChecker;
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
    public String myBarForm(Model model) {
        model.addAttribute("ingredients", Deserializer.listAllIngredients());
        model.addAttribute(new MyBarForm());
        model.addAttribute("title", "My Bar");
        return "mybar";
    }

    @RequestMapping(value = "", method= RequestMethod.POST)
    public String myBar(Model model, @ModelAttribute MyBarForm form, HttpServletRequest request){

        ArrayList<IngredientInList> list = (ArrayList<IngredientInList>) Deserializer.listAllIngredients();

        User theUser = getUserFromSession(request.getSession());
        List <Ingredient> ingredientsInMyBar = theUser.getIngredients();

        if (!DuplicateChecker.checkIngredientInList(form.getIngredientName(), list)){
            model.addAttribute("ingredients", Deserializer.listAllIngredients());
            model.addAttribute(new MyBarForm());
            model.addAttribute("error", "invalid");
            model.addAttribute("title", "My Bar");
            return "mybar";
        }

        if (DuplicateChecker.checkIngredient(form.getIngredientName(), ingredientsInMyBar)){
            model.addAttribute("ingredients", Deserializer.listAllIngredients());
            model.addAttribute(new MyBarForm());
            model.addAttribute("error", "duplicate");
            model.addAttribute("title", "My Bar");
            return "mybar";
        }

        Ingredient newIngredient = (Ingredient) Deserializer.searchIngredientByName(form.getIngredientName());
        ingredientDao.save(newIngredient);
        theUser.addItem(newIngredient);
        userDao.save(theUser);

        model.addAttribute("ingredients", Deserializer.listAllIngredients());
        model.addAttribute(new MyBarForm());
        model.addAttribute("error", "false");
        model.addAttribute("title", "My Bar");
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
