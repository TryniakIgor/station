package com.work.station.repository;

import com.work.station.model.CompletedWorks;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompletedWorksRepo extends PagingAndSortingRepository<CompletedWorks, Long> {
}
