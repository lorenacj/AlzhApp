package com.example.demo.model;

import java.sql.Date;
import java.sql.Time;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SymptomModel {
	private int id;
	private String type;
	private String description;
	private Date date;
	private Time hour;
	private int patientId;
}
