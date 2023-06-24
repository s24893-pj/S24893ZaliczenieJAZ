package com.example.jazs24893nbp.repository;

import com.example.jazs24893nbp.model.CurrencyDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NbpRepository extends JpaRepository<CurrencyDataModel, Long> {
}
