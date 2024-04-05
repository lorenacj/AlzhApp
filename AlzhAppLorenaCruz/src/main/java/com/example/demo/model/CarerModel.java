package com.example.demo.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarerModel {
	private int id;
	private String name;
	private String lastname;
	private String username;
	private String password;
	private int idFamilyUnit;
	private String role;
	private boolean enabled;
	private List<Integer> patientIds; 
	private List<Integer> medicineIds;
}
