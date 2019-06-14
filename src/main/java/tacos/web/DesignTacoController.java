package tacos.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tacos.domain.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import tacos.domain.Ingredient.Type;
import tacos.domain.Order;
import tacos.domain.Taco;
import tacos.repository.IngredientRepository;
import tacos.repository.TacoRepository;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/design")
@SessionAttributes("order")
public class DesignTacoController {

    Logger logger = LoggerFactory.getLogger(DesignTacoController.class);

    private final IngredientRepository repository;
    private final TacoRepository tacoRepository;


    @ModelAttribute
    public Order order(){
        return new Order();
    }

    @ModelAttribute
    public Taco taco(){
        return new Taco();
    }

    @Autowired
    public DesignTacoController(IngredientRepository repository, TacoRepository tacoRepository) {
        this.repository = repository;
        this.tacoRepository = tacoRepository;
    }

    private void initializeModel(Model model){
//        List<Ingredient> ingredientList = Arrays.asList(
//                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//                new Ingredient("GRBF", "Ground Beef", Type.PROTIEN),
//                new Ingredient("CARN", "Carnitas", Type.PROTIEN),
//                new Ingredient("TMTO", "Tomatoes", Type.VEGGIES),
//                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//                new Ingredient("CHED", "Cheddar", Type.CHEESE),
//                new Ingredient("JACK", "Monterry Jack", Type.CHEESE),
//                new Ingredient("SLSA", "Salsa", Type.SAUCE),
//                new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

        List<Ingredient> ingredientList = new ArrayList<>();
        repository.findAll().forEach(ing -> ingredientList.add(ing));

        logger.info("** Model initialization *******");

        for (Type type : Type.values()) {
            model.addAttribute(type.toString().toLowerCase(), ingredientList.stream().
                    filter(ingredient -> ingredient.getType().name().equalsIgnoreCase(type.name())).collect(Collectors.toList()));
        }

    }

    @GetMapping()
    public String showDesignForm(Taco taco, Model model) {
        logger.info("** In Design Form *******");
        initializeModel(model);
        return "design";
    }

    @PostMapping()
    public String processDesign(@Valid @ModelAttribute(value="tacoDesign") Taco design,
                                BindingResult bindingResult, Model model,
                                @ModelAttribute Order order) {
        if (bindingResult.hasErrors()) {
            logger.info("** Has validation errors **" + design);
            initializeModel(model);
            return "design";
        }
        logger.info("** In Processing design *******" + design);
        Taco taco = tacoRepository.save(design);
        order.addDesign(design);
        return "redirect:/orders/current/";
    }
}
