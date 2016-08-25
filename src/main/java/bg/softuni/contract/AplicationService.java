package bg.softuni.contract;

public interface AplicationService {

    String attachFragment(String param);

    String detachFragment(String param);

    String createCore(String param);

    String removeCore(String param);

    String selectCore(String param);

    String getStatus(String param);
}
