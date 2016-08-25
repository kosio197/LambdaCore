package bg.softuni.service;

import bg.softuni.contract.Repository;
import bg.softuni.contract.Service;
import bg.softuni.framework.annotation.Component;
import bg.softuni.framework.annotation.Inject;

@Component
public class ServiceImpl implements Service {
    @Inject
    private Repository repository;
}
