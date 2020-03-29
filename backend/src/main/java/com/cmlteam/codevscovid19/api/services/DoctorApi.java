package com.cmlteam.codevscovid19.api.services;

import com.cmlteam.codevscovid19.models.Customer;
import com.cmlteam.codevscovid19.models.Doctor;
import com.cmlteam.codevscovid19.repo.CustomerRepository;
import com.cmlteam.codevscovid19.repo.DoctorRepository;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
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

    @GetMapping("/doctors/{doctor_id}/customers")
    public List<Customer> getDoctorCustomers(@PathVariable(name = "doctor_id") Integer doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId);
        return customerRepository.findIn(doctor.getCustomers());
    }

    @GetMapping("/customers/{customer_id}/related")
    public List<Customer> getCustomersClosePeople(@PathVariable(name = "customer_id") Integer customerId) {
        List<Integer> customerFamily = customerRepository.findById(customerId).getCloseCommunicationWith();
        return customerRepository.findIn(customerFamily);
    }

    @PutMapping("/customers/{customer_id}/status")
    public void changeCustomerStatus(@PathVariable(name = "customer_id") Integer customerId,
                                     @RequestBody ChangeStatusModel model) {
        customerRepository.findById(customerId).setStatus(Customer.CustomerStatus.valueOf(model.status));
    }

    @Data
    private static class ChangeStatusModel {
        String status;
    }

}
