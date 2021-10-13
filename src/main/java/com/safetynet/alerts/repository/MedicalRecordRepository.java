package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer> {

    @Query("select distinct medicalRecord from MedicalRecord as medicalRecord "
            + "inner join fetch medicalRecord.person as person "
            + "where person.firstName = :firstName "
            + "and person.lastName = :lastName")
     MedicalRecord findMedicalRecordByFirstAndLastName(@Param("firstName") String firstName,@Param("lastName") String lastName);

    @Modifying
    @Query("DELETE FROM MedicalRecord medicalRecord WHERE medicalRecord =:medicalRecord")
    @Transactional
    void deleteMedicalRecord(MedicalRecord medicalRecord);
}

