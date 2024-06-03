package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Carer;
import com.example.demo.model.CarerModel;
import com.example.demo.service.CarerService;

@Controller
@RequestMapping("/caarers")
public class CarerController {

    @Autowired
    private CarerService carerService;

    @GetMapping
    public String getAllCarers(Model model) {
        model.addAttribute("carers", carerService.listAllCarer());
        return "crudcarer.html";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("carer", new Carer());
        return "carers/form";
    }

    @PostMapping
    public String createCarer(@ModelAttribute CarerModel carer) {
        carerService.register(carer);
        return "redirect:/carers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Carer carer = carerService.getCarerById(id);
        model.addAttribute("carer", carer);
        return "carers/form";
    }

    @PostMapping("/{id}")
    public String updateCarer(@PathVariable Long id, @ModelAttribute CarerModel carer) {
        carerService.register(carer);
        return "redirect:/carers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCarer(@PathVariable int id) {
        carerService.removeCarer(id);
        return "redirect:/carers";
    }
}
