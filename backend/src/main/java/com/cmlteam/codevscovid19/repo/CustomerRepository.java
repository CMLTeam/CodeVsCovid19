package com.cmlteam.codevscovid19.repo;

import com.cmlteam.codevscovid19.models.Customer;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CustomerRepository {

    private final Map<Integer, Customer> customerTable = new ConcurrentHashMap<>();

    private Collection<Customer> findAll(){
        return customerTable.values();
    }

    private void create(Customer customer) {
        Objects.requireNonNull(customer.getId());
        customerTable.put(customer.getId(), customer);
    }
    private void update(Customer customer) {
        create(customer);
    }

    private Customer findById(Integer id) {
        return customerTable.get(id);
    }

    private List<Customer> findIn(Collection<Integer> ids) {
        Objects.requireNonNull(ids);
        List<Customer> result = new ArrayList<>();
        for (Integer id : ids) {
            result.add(customerTable.get(id));
        }
        return result;
    }

    private void putToCloseCommunication(Integer customerId, Integer closeCommunicationWith) {
        customerTable.get(customerId).getCloseCommunicationWith().add(closeCommunicationWith);
    }

    private void addBookedSlot(Integer customerId, Integer slotId){
        customerTable.get(customerId).getBookedSlots().add(slotId);
    }
}
