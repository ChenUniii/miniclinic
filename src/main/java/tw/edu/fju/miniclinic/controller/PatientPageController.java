package tw.edu.fju.miniclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tw.edu.fju.miniclinic.model.Patient;
import tw.edu.fju.miniclinic.model.PatientRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class PatientPageController {

    @Autowired
    private PatientRepository patientRepo;

    // 網頁：顯示所有病患列表 (對應 templates/patients.html)
    @GetMapping("/patients")
    public String listPatients(Model model) {
        List<Patient> patients = patientRepo.findAll();
        model.addAttribute("patients", patients);
        return "patients";
    }

    // 網頁：顯示單一病患詳細資料 (對應 templates/patient-detail.html)
    @GetMapping("/patients/{chartNo}")
    public String patientDetail(@PathVariable String chartNo, Model model) {
        Optional<Patient> patient = patientRepo.findById(chartNo);
        if (patient.isEmpty()) {
            return "redirect:/patients"; // 找不到就導回列表
        }
        model.addAttribute("patient", patient.get());
        return "patient-detail";
    }
}