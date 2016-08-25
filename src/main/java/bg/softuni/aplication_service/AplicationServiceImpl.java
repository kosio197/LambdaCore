package bg.softuni.aplication_service;

import java.util.List;

import bg.softuni.contract.AplicationService;
import bg.softuni.contract.Core;
import bg.softuni.contract.CoreRepository;
import bg.softuni.contract.Fragment;
import bg.softuni.factory.CoreFactory;
import bg.softuni.factory.FragmentFactory;
import bg.softuni.repository.CoreRepositoryImpl;

public class AplicationServiceImpl implements AplicationService {

    private CoreRepository repository;
    private Core selectedCore;
    private CoreFactory coreFactory;
    private FragmentFactory fragmentFactory;

    public AplicationServiceImpl() {
        this.repository = new CoreRepositoryImpl();
        this.coreFactory = new CoreFactory();
        this.fragmentFactory = new FragmentFactory();
    }

    @Override
    public String attachFragment(String param) {
        // AttachFragment:@type@name@pressureAffection
        String[] arr = param.split("\\@");
        String type = arr[1];
        String name = arr[2];
        Integer pressureAffection = Integer.parseInt(arr[3]);

        try {
            Fragment fragment = fragmentFactory.createFragment(type, name, pressureAffection);
            if (selectedCore != null) {
                selectedCore.addFragment(fragment);
                return String.format("Successfully attached Fragment %s to Core %s!\n", fragment.getName(),
                        selectedCore.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to attach Fragment " + name + "!\n";
        }

        return "Failed to attach Fragment " + name + "!\n";
    }

    @Override
    public String detachFragment(String param) {
        if (this.selectedCore != null) {
            Fragment fragment = selectedCore.removeFragment();
            if (fragment != null) {
                return String.format("Successfully detached Fragment %s from Core %s!\n", fragment.getName(),
                        selectedCore.getName());
            }
        }
        return "Failed to detach Fragment!\n";
    }

    @Override
    public String createCore(String param) {
        // CreateCore:@type@durability
        String type = param.split("\\@")[1];
        Integer durability = Integer.parseInt(param.split("\\@")[2]);
        if (durability < 0) {
            return "Failed to create Core!\n";
        }
        Core core = null;
        try {
            core = this.coreFactory.createCore(type, durability);
            this.repository.addCore(core);
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to create Core!\n";
        }

        return String.format("Successfully created Core %s!\n", core.getName());
    }

    @Override
    public String removeCore(String param) {
        String coreName = param.split("\\@")[1];
        Core core = repository.removeCore(coreName);
        if (core != null) {
            repository.removeCore(core.getName());
            if (selectedCore.getName().equals(core.getName())) {
                selectedCore = null;
            }
            return String.format("Successfully removed Core %s!\n", coreName);
        }
        return String.format("Failed to remove Core %s!\n", coreName);
    }

    @Override
    public String selectCore(String param) {
        String coreName = param.split("\\@")[1];
        Core core = repository.getCore(coreName);
        if (core != null) {
            selectedCore = core;
            return String.format("Currently selected Core %s!\n", coreName);
        }
        return String.format("Failed to select Core %s!\n", coreName);
    }

    @Override
    public String getStatus(String param) {
        StringBuilder sb = new StringBuilder();

        List<Core> cores = repository.getAllCores();
        int tCore = cores.size();
        long tDurability = 0;
        int tFragments = 0;

        for (Core core : cores) {
            tDurability += core.getDurability();
            tFragments += core.getCountOfAtachedFragment();
            sb.append(core.toString());
        }

        StringBuilder sb1 = new StringBuilder();
        sb1.append("Lambda Core Power Plant Status:\n");
        sb1.append(String.format("Total Durability: %d\n", tDurability));
        sb1.append(String.format("Total Cores: %d\n", tCore));
        sb1.append(String.format("Total Fragments: %d\n", tFragments));
        sb1.append(sb.toString());

        return sb1.toString();
    }


}
