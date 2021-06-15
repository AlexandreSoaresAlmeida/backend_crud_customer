package com.apptek.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apptek.customer.model.TipoTelefone;

@Repository
public interface TipoTelefoneRepository extends JpaRepository<TipoTelefone, Integer> {

}