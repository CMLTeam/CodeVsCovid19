package com.cmlteam.codevscovid19.repo;

import com.cmlteam.codevscovid19.models.Doctor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DoctorRepository {

    public Map<Integer, Doctor> doctorTable = new ConcurrentHashMap<>();

    public void create(Doctor doctor) {
        Objects.requireNonNull(doctor.getId());
        doctorTable.put(doctor.getId(), doctor);
    }

    public Doctor findById(Integer id) {
        return doctorTable.get(id);
    }

    public void addCustomerToDoctor(Doctor doctor, Integer customerId) {
        doctorTable.get(doctor.getId()).getCustomers().add(customerId);
    }

}
