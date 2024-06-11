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
import com.example.demo.entity.Patient;
import com.example.demo.service.PatientService;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.listAllPatient());
        return "listpatient";
    }

    @GetMapping("/new")
    public String newPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "formpatient";
    }

    @PostMapping
    public String savePatient(@ModelAttribute("patient") Patient patient, Carer carer) {
        patientService.savePatientWithCarer(patient,carer);
        return "redirect:/admin/patients";
    }

    @GetMapping("/edit/{id}")
    public String editPatient(@PathVariable int id, Model model) {
    	Patient patient= patientService.findPatientById(id);
        if (patient == null) {
            return "redirect:/admin/patients";
        }
        model.addAttribute("patient", patient);
        return "formpatient";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable int id) {
        patientService.removePatient(id);
        return "redirect:/admin/patients";
    }
}