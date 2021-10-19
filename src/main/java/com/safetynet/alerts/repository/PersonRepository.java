package com.safetynet.alerts.repository;


import com.safetynet.alerts.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("select distinct person from Person as person "
            + "inner join person.address as address "
            + "inner join person.address.fireStation as fireStation "
            + "where fireStation.station = :stationNumber")
    List<Person> findPersonsByStationNumber(@Param("stationNumber") Integer stationNumber);


    @Query("select distinct person from Person as person "
            + "inner join person.address as address "
            + "where address.address = :address")
    List<Person> findPersonsByAddress(@Param("address") String address);

    @Query("select distinct person.phone from Person as person "
            + "inner join person.address as address "
            + "inner join person.address.fireStation as fireStation "
            + "where fireStation.station = :stationNumber")
    List<String> findPhoneNumberByFireStation(@Param("stationNumber") Integer fireStationNumber);

    @Query("select distinct person from Person as person "
            + "inner join person.address as address "
            + "inner join person.address.fireStation as fireStation "
            + "where fireStation.station in (:stations)")
    List<Person> findPersonsByStations(@Param("stations") List<Integer> stations);

    List<Person> findAllByFirstNameAndLastName(String firstName, String lastName);


    @Query("select distinct person.email from Person as person "
            + "inner join person.address as address "
            + "where address.city = :city")
    List<String> findAllAddressMailsByCity(@Param("city") String city);
}


