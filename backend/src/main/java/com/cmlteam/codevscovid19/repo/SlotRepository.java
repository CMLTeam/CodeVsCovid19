package com.cmlteam.codevscovid19.repo;

import com.cmlteam.codevscovid19.models.Slot;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SlotRepository {

    public Map<Integer, Slot> slotTable = new ConcurrentHashMap<>();

    public Collection<Slot> findAll() {
        return slotTable.values();
    }

    public Slot findById(Integer id) {
        return slotTable.get(id);
    }

    public void create(Slot slot) {
        Objects.requireNonNull(slot.getId());
        slotTable.put(slot.getId(), slot);
    }

    public void update(Slot slot) {
        create(slot);
    }
}
