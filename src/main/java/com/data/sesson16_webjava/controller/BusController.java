package com.data.sesson16_webjava.controller;

import com.data.sesson16_webjava.model.Bus;
import com.data.sesson16_webjava.service.BusService;
import com.data.sesson16_webjava.service.BusServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/bus")
public class BusController {
    private final BusService busService = new BusServiceImpl();

    @GetMapping
    public String showBusList(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equals(role)) return "redirect:/login";

        List<Bus> buses = busService.getAll();
        model.addAttribute("buses", buses);
        return "busList.html";
    }

    @GetMapping("/add")
    public String createForm(Model model) {
        model.addAttribute("bus", new Bus());
        return "busAdd";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("bus") @Valid Bus bus,
                       BindingResult result, Model model) {
        if (result.hasErrors()) {
            if (bus.getId() == null) {
                return "busAdd";
            } else {
                model.addAttribute("bus", bus); // đảm bảo dữ liệu hiển thị lại form
                return "busEdit.html";
            }
        }
        busService.save(bus);
        return "redirect:/admin/bus";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("bus", busService.findById(id));
        return "busEdit.html";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        busService.delete(id);
        return "redirect:/admin/bus";
    }
}
