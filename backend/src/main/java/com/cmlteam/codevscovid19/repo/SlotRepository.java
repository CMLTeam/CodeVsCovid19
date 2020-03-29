package com.cmlteam.codevscovid19.repo;

import com.cmlteam.codevscovid19.models.Slot;
import com.cmlteam.codevscovid19.models.Slot;
import org.springframework.stereotype.Component;

import java.util.*;
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

    public List<Slot> findIn(Collection<Integer> ids) {
        Objects.requireNonNull(ids);
        List<Slot> result = new ArrayList<>();
        for (Integer id : ids) {
            result.add(slotTable.get(id));
        }
        return result;
    }
}
