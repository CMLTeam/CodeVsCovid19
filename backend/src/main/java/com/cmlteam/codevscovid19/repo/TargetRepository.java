package com.cmlteam.codevscovid19.repo;

import com.cmlteam.codevscovid19.models.Customer;
import com.cmlteam.codevscovid19.models.Target;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TargetRepository {

    private static Map<Integer, Target> targetTable = new ConcurrentHashMap<>();

    private Collection<Target> findAll() {
        return targetTable.values();
    }

    Target findById(Integer id) {
        return targetTable.get(id);
    }

    private void create(Target target) {
        Objects.requireNonNull(target.getId());
        targetTable.put(target.getId(), target);
    }

    private void update(Target target) {
        create(target);
    }
}
