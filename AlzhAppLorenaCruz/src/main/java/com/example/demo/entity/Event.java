package com.example.demo.entity;

import java.sql.Date;
import java.sql.Time;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotBlank(message = "Name can not be empty.")
	@Length(min = 2, max = 20, message = "Name length must be between 2 and 20 characters")
	private String name;

	@NotBlank(message = "Type can not be empty.")
	@Length(min = 2, max = 20, message = "Name length must be between 2 and 20 characters")
	private String type;

	@NotBlank(message = "Description can not be empty.")
	@Length(min = 10, max = 100, message = "Name length must be between 10 and 100 characters")
	private String description;

	@NotBlank(message = "Status can not be empty.")
	@Length(min = 2, max = 20, message = "Name length must be between 2 and 20 characters")
	private String status;

	private boolean deleted;

	@NotNull(message = "Initial date can not be null")
	private Date initialdate;

	@NotNull(message = "Final date can not be null")
	private Date finaldate;

	@NotNull(message = "Inital hour can not be null")
	private Time initialhour;

	@NotNull(message = "Final hour can not be null")
	private Time finalhour;
}
