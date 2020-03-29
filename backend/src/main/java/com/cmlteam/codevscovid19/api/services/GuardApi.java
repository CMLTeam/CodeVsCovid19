package com.cmlteam.codevscovid19.api.services;

import com.cmlteam.codevscovid19.models.Customer;
import com.cmlteam.codevscovid19.models.Slot;
import com.cmlteam.codevscovid19.repo.CustomerRepository;
import com.cmlteam.codevscovid19.repo.SlotRepository;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GuardApi {

    private final SlotRepository slotRepository;
    private final CustomerRepository customerRepository;

    public GuardApi(SlotRepository slotRepository, CustomerRepository customerRepository) {
        this.slotRepository = slotRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers/{customer_id}/validate/{target_id}")
    public List<Slot> getCustomerSlotsByTargetToValidateIt(
            @PathVariable(name = "customer_id") Integer customerId,
            @PathVariable(name = "target_id") Integer targetId) {

        Customer customer = customerRepository.findById(customerId);
        List<Slot> slotsByTarget = slotRepository.findByTargetId(targetId);

        List<Slot> slotsByTargetAndCustomer = slotsByTarget.stream()
                .filter(slot -> customer.getBookedSlots().contains(slot.getId()))
                .collect(Collectors.toList());

        System.out.println(">>> " + slotsByTargetAndCustomer);
        return slotsByTargetAndCustomer;
    }

    @PostMapping("/took-slot")
    public void recordSlotUsed(@RequestBody TookSlotModel tookSlotModel) {

        Customer customer = customerRepository.findById(tookSlotModel.getCustomerId());
        Slot slot = slotRepository.findById(tookSlotModel.getSlotId());
        // TODO ?? where to record this action
    }

    @Data
    private static class TookSlotModel {
        private Integer customerId;
        private Integer slotId;
    }
}
