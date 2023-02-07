package com.work.station.service;

import com.work.station.dto.CompletedWorksDTO;
import com.work.station.model.CompletedWorks;

import java.util.List;

public interface CompletedWorksService {

    CompletedWorksDTO save(CompletedWorks completedWorks);

    CompletedWorksDTO getCompletedWork(String name);

    CompletedWorksDTO getCompletedWorkByCustomerName(String name);

    CompletedWorksDTO getCompletedWorkByCustomerPhone(Byte phoneNumber);

    CompletedWorksDTO getCompletedWorkByLicensePlate(Integer num);

    List<CompletedWorksDTO> getAllCompletedWorks();

    List<CompletedWorksDTO> getAllCompletedWorksBeetweenDates();

    CompletedWorksDTO updateCompletedWork(String name, CompletedWorks completedWorks);

    void deleteCompletedWork(String name);
}
