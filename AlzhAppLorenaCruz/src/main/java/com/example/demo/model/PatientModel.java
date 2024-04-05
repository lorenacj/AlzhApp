package com.example.demo.model;

import java.sql.Date;
import java.util.List;

public class PatientModel {
	private int id;
	private String name;
	private String lastname;
	private Date birthdate;
	private int height;
	private int weight;
	private String disorder;
	private String passportId;
	private boolean enabled;
	private boolean deleted;
	private int familyUnitId;
	private List<Integer> carerIds;
	private List<Integer> medicineIds;
	private List<Integer> eventIds;
	private List<Integer> symptomIds;
}
