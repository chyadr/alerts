package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FireStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FireStationRepository extends JpaRepository<FireStation, Integer> {

    @Query("select distinct fireStation from FireStation as fireStation "
            + "inner join fireStation.address as address "
            + "where address.id = :addressId")
    List<FireStation> findFireStationsByAddressId(@Param("addressId") Integer addressId);


}
