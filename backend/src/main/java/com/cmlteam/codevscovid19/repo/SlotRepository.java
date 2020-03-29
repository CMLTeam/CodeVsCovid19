package com.cmlteam.codevscovid19.repo;

import com.cmlteam.codevscovid19.models.Doctor;
import com.cmlteam.codevscovid19.models.Slot;
import com.cmlteam.codevscovid19.models.Slot;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SlotRepository {

    private static Map<Integer, Slot> slotTable = new ConcurrentHashMap<>();

    private Collection<Slot> findAll() {
        return slotTable.values();
    }

    Slot findById(Integer id) {
        return slotTable.get(id);
    }

    private void create(Slot slot) {
        Objects.requireNonNull(slot.getId());
        slotTable.put(slot.getId(), slot);
    }

    private void update(Slot slot) {
        create(slot);
    }
}
