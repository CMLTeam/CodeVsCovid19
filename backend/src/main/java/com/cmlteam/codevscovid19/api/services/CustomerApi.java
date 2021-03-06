package com.cmlteam.codevscovid19.api.services;

import com.cmlteam.codevscovid19.api.dto.TargetWithNestedSlots;
import com.cmlteam.codevscovid19.models.Customer;
import com.cmlteam.codevscovid19.models.Slot;
import com.cmlteam.codevscovid19.repo.CustomerRepository;
import com.cmlteam.codevscovid19.repo.SlotRepository;
import com.cmlteam.codevscovid19.repo.TargetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//TODO return doctor id and name with customer model
@RestController
public class CustomerApi {

    private final CustomerRepository customerRepository;
    private final SlotRepository slotRepository;
    private final TargetRepository targetRepository;

    public CustomerApi(CustomerRepository customerRepository, SlotRepository slotRepository, TargetRepository targetRepository) {
        this.customerRepository = customerRepository;
        this.slotRepository = slotRepository;
        this.targetRepository = targetRepository;
    }

    @GetMapping("/me")
    public Customer getCustomerPage() {
        return customerRepository.findById(1001); //hardcode: user_id
    }

    @GetMapping("/slots")
    public List<TargetWithNestedSlots> getCustomerSlots() {
        Customer customer = customerRepository.findById(1001); //hardcode: user_id

        Map<Integer, List<Slot>> targetToSlots = customer.getBookedSlots().stream()
                .map(slotRepository::findById)
                .collect(Collectors.groupingBy(
                        Slot::getTargetId,
                        Collectors.toList()));

        return targetToSlots.entrySet().stream()
                .map(entry -> new TargetWithNestedSlots(
                        targetRepository.findById(entry.getKey()),
                        entry.getValue()))
                .collect(Collectors.toList());
    }

    @PostMapping("/bookings")
    public void bookPlaceInQueue(
            @RequestParam(name = "customerId", required = false) Integer customerIdParam,
            @RequestParam(name = "slotId") Integer slotId
    ) {
        int customerId = customerIdParam == null ? 1001 : customerIdParam;
        customerRepository.addBookedSlot(customerId, slotId);
        Slot slot = slotRepository.findById(slotId);
        slot.setFreeCapacity(slot.getFreeCapacity() - 1);
    }
}
