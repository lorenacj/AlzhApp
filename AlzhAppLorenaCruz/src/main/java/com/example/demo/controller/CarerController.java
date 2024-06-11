package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Carer;
import com.example.demo.model.CarerModel;
import com.example.demo.service.CarerService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/carers")
public class CarerController {

    @Autowired
    private CarerService carerService;

    @GetMapping
    public String getAllCarers(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("carers", carerService.listAllCarer());
        model.addAttribute("message", message);
        return "crudcarer";
    }

    @GetMapping("/add")
    public String addCarerForm(Model model) {
        model.addAttribute("carer", new CarerModel());
        return "carerform";
    }

    @PostMapping("/add")
    public String addCarer(@Valid @ModelAttribute("carer") CarerModel carerModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("carer", carerModel);
            return "carerform";
        }
        carerService.register(carerModel);
        redirectAttributes.addFlashAttribute("message", "Carer added successfully!");
        return "redirect:/carers";
    }

    @GetMapping("/edit/{id}")
    public String editCarerForm(@PathVariable("id") int id, Model model) {
        Carer carer = carerService.getCarerById((long) id);
        model.addAttribute("carer", carer);
        return "editcarer";
    }

    @PostMapping("/edit/{id}")
    public String editCarer(@PathVariable("id") int id, @Valid @ModelAttribute("carer") CarerModel carerModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("carer", carerModel);
            return "editcarer";
        }
        carerModel.setId(id);
        carerService.updateCarer(carerModel);
        redirectAttributes.addFlashAttribute("message", "Carer updated successfully!");
        return "redirect:/carers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCarer(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        carerService.removeCarer(id);
        redirectAttributes.addFlashAttribute("message", "Carer deleted successfully!");
        return "redirect:/carers";
    }
    
    @GetMapping("/enable/{id}")
    public String enabledCarer(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        int enable = carerService.enable(id);
        if(enable==1) {
            redirectAttributes.addFlashAttribute("message", "Carer Active successfully!");
        }else {
            redirectAttributes.addFlashAttribute("message", "Carer Desactive successfully!");

        }
        return "redirect:/carers";
    }
}
