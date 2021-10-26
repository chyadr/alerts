package com.safetynet.alerts.service.impl;
import com.safetynet.alerts.model.Data;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.ICommunityEmailService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunityEmailService implements ICommunityEmailService {

    private final Data data;

    public CommunityEmailService(Data data) {
        this.data = data;
    }

    @Override
    public List<String> findAllAddressMailsByCity(String city) {
        return data.getPersons().stream().filter(p->p.getCity().equals(city)).map(Person::getEmail).collect(Collectors.toList());
    }
}