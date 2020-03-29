package com.cmlteam.codevscovid19.api.services;

import com.cmlteam.codevscovid19.models.Customer;
import com.cmlteam.codevscovid19.models.Doctor;
import com.cmlteam.codevscovid19.repo.CustomerRepository;
import com.cmlteam.codevscovid19.repo.DoctorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class DoctorApi {

    private final DoctorRepository doctorRepository;
    private final CustomerRepository customerRepository;

    public DoctorApi(DoctorRepository doctorRepository, CustomerRepository customerRepository) {
        this.doctorRepository = doctorRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/doctors/{doctor_id}")
    public Doctor getDoctorById(@PathVariable(name = "doctor_id") Integer doctorId) {
        return doctorRepository.findById(doctorId);
    }

    @GetMapping("/customers/{doctor_id}/customers")
    public List<Customer> getDoctorCustomers(@PathVariable(name = "doctor_id") Integer doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId);
        return customerRepository.findIn(doctor.getCustomers());
    }
}
