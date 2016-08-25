package bg.softuni.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bg.softuni.contract.Core;
import bg.softuni.contract.CoreRepository;

public class CoreRepositoryImpl implements CoreRepository {

    private Map<String, Core> innerCollection;

    public CoreRepositoryImpl() {
        this.innerCollection = new HashMap<>();
    }

    @Override
    public void addCore(Core core) {
        this.innerCollection.put(core.getName(), core);
    }

    @Override
    public Core removeCore(String coreName) {
        Core core = innerCollection.remove(coreName);
        return core;
    }

    @Override
    public Core getCore(String coreName) {
        return innerCollection.get(coreName);
    }

    @Override
    public List<Core> getAllCores() {
        List<Core> result = new ArrayList<>();
        for (Map.Entry<String, Core> core : innerCollection.entrySet()) {
            result.add(core.getValue());
        }
        return Collections.unmodifiableList(result);
    }

}
