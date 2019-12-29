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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static com.luispintodesa.bartender.models.Constants.*;

@Controller
@RequestMapping(MY_BAR_TEMPLATE)
public class MyBarController extends AbstractController {

    @Autowired
    private IngredientDao ingredientDao;

    @Autowired
    private UserDao userDao;

    @GetMapping(value = "")
    public String myBarForm(Model model) {
        model.addAttribute(INGREDIENTS, Deserializer.listAllIngredients());
        model.addAttribute(new MyBarForm());
        model.addAttribute(TITLE, MY_BAR);
        return MY_BAR_TEMPLATE;
    }

    @PostMapping(value = "")
    public String myBar(Model model, @ModelAttribute MyBarForm form, HttpServletRequest request){

        ArrayList<IngredientInList> list = (ArrayList<IngredientInList>) Deserializer.listAllIngredients();

        User theUser = getUserFromSession(request.getSession());
        List <Ingredient> ingredientsInMyBar = theUser.getIngredients();

        if (!DuplicateChecker.checkIngredientInList(form.getIngredientName(), list)){
            model.addAttribute(INGREDIENTS, Deserializer.listAllIngredients());
            model.addAttribute(new MyBarForm());
            model.addAttribute(ERROR, "invalid");
            model.addAttribute(TITLE, MY_BAR);
            return MY_BAR_TEMPLATE;
        }

        if (DuplicateChecker.checkIngredient(form.getIngredientName(), ingredientsInMyBar)){
            model.addAttribute(INGREDIENTS, Deserializer.listAllIngredients());
            model.addAttribute(new MyBarForm());
            model.addAttribute(ERROR, "duplicate");
            model.addAttribute(TITLE, MY_BAR);
            return MY_BAR_TEMPLATE;
        }

        Ingredient newIngredient = (Ingredient) Deserializer.searchIngredientByName(form.getIngredientName());
        ingredientDao.save(newIngredient);
        theUser.addItem(newIngredient);
        userDao.save(theUser);

        model.addAttribute("ingredients", Deserializer.listAllIngredients());
        model.addAttribute(new MyBarForm());
        model.addAttribute(ERROR, "false");
        model.addAttribute(TITLE, MY_BAR);
        return MY_BAR_TEMPLATE;
    }

    @PostMapping (value="remove")
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
