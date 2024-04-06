package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Symptom;

public interface SymptomRepository extends JpaRepository<Symptom, Serializable> {
	public abstract Symptom findById(int id);
}
