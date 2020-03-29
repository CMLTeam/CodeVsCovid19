package com.cmlteam.codevscovid19.repo;

import com.cmlteam.codevscovid19.models.Target;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TargetRepository {

    public Map<Integer, Target> targetTable = new ConcurrentHashMap<>();

    public Collection<Target> findAll() {
        return targetTable.values();
    }

    public Target findById(Integer id) {
        return targetTable.get(id);
    }

    public void create(Target target) {
        Objects.requireNonNull(target.getId());
        targetTable.put(target.getId(), target);
    }

    public void update(Target target) {
        create(target);
    }
}
