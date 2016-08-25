package bg.softuni.contract;

import java.util.List;

public interface CoreRepository {

    void addCore(Core core);

    Core removeCore(String coreName);

    Core getCore(String coreName);

    List<Core> getAllCores();

}
