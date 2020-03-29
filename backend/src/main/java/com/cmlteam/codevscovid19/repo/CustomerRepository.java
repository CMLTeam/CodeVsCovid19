package com.cmlteam.codevscovid19.repo;

import com.cmlteam.codevscovid19.models.Customer;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CustomerRepository {

    public final Map<Integer, Customer> customerTable = new ConcurrentHashMap<>();

    public Collection<Customer> findAll(){
        return customerTable.values();
    }

    public void create(Customer customer) {
        Objects.requireNonNull(customer.getId());
        customerTable.put(customer.getId(), customer);
    }
    public void update(Customer customer) {
        create(customer);
    }

    public Customer findById(Integer id) {
        return customerTable.get(id);
    }

    public List<Customer> findIn(Collection<Integer> ids) {
        Objects.requireNonNull(ids);
        List<Customer> result = new ArrayList<>();
        for (Integer id : ids) {
            result.add(customerTable.get(id));
        }
        return result;
    }

    public void putToCloseCommunication(Integer customerId, Integer closeCommunicationWith) {
        customerTable.get(customerId).getCloseCommunicationWith().add(closeCommunicationWith);
    }

    public void addBookedSlot(Integer customerId, Integer slotId){
        customerTable.get(customerId).getBookedSlots().add(slotId);
    }
}
