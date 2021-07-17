package com.dasser.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dasser.api.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {

}
