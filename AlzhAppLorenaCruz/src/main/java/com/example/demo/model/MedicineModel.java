package com.example.demo.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MedicineModel {
	private int id;
	private String name;
	private String description;
	private String usage;
	private int howOften;
	private int howManyDays;
	private boolean deleted;
	private List<Integer> carerIds;
	private List<Integer> patientIds;
}
