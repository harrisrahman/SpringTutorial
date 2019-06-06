package tacos.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.domain.Order;

import javax.validation.Valid;

@RequestMapping(path = "/orders")
@Controller
public class OrderController {

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping(path = "/current")
    public String orderForm(@ModelAttribute Order order) {
        return "orderForm";
    }

    @PostMapping
    public String processOrders(@ModelAttribute @Valid Order order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info("** Order has validaton errors **");
            return "orderForm";
        }
        logger.info("*********** Order Submitted ***********" + order);
        return "redirect:/design";
    }
}

