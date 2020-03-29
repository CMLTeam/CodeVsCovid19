package com.cmlteam.codevscovid19.api.services;

import com.cmlteam.codevscovid19.api.dto.TargetWithNestedSlots;
import com.cmlteam.codevscovid19.api.dto.TargetWithSlots;
import com.cmlteam.codevscovid19.models.Slot;
import com.cmlteam.codevscovid19.models.Target;
import com.cmlteam.codevscovid19.repo.SlotRepository;
import com.cmlteam.codevscovid19.repo.TargetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@RestController
public class TargetApi {
    private static final Random R = new Random();

    private final TargetRepository targetRepository;
    private final SlotRepository slotRepository;

    public TargetApi(TargetRepository targetRepository, SlotRepository slotRepository) {
        this.targetRepository = targetRepository;
        this.slotRepository = slotRepository;
    }

    @GetMapping("/targets")
    public List<TargetWithNestedSlots> getTargets(
            @RequestParam(required = false) Integer locationLatitude,
            @RequestParam(required = false) Integer locationLongitude,
            @RequestParam(required = false) Target.TargetType type
    ) {
        List<TargetWithNestedSlots> targetWithNestedSlots = new ArrayList<>();
        Collection<Target> targets = targetRepository.findAll();
        for (Target target : targets) {
            List<Slot> slots = slotRepository.findIn(target.getSlots());
            targetWithNestedSlots.add(new TargetWithNestedSlots(target, slots));
        }
        return targetWithNestedSlots;
    }

    @GetMapping("/targets/{target_id}/slots")
    public TargetWithSlots getSlotsByTarget(
            @PathVariable(required = true, name = "target_id") Integer targetId) {
        List<Slot> slots = slotRepository.findByTargetId(targetId);
        Target target = targetRepository.findById(targetId);
        return new TargetWithSlots(target, slots);
    }

}
