package com.finite.repo;

import com.finite.model.FraudCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudCheckRepo extends JpaRepository<FraudCheck, Integer> {
}
