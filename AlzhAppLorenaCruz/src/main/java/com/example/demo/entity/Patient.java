package com.example.demo.entity;

import java.sql.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotBlank(message = "Name can not be empty.")
	@Length(min = 2, max = 20, message = "Name length must be between 2 and 20 characters")
	private String name;

	@NotBlank(message = "Last name can not be empty")
	@Length(min = 5, max = 50, message = "Lastname length must be between 5 and 50 characters")
	private String lastname;

	@NotNull(message = "Birthdate can not be null")
	private Date birthdate;

	@NotNull(message = "Height can not be null")
	private int height;

	@NotNull(message = "Weight can not be null")
	private int weight;

	private String disorder;

	@Column(unique = true)
	@NotBlank(message = "Passport id can not be empty")
	@Pattern(regexp = "\\d{8}[A-HJ-NP-TV-Z]")
	private String passportid;
	// administration
	private boolean enabled;
	private boolean deleted;

	//unidad familiar de tipo unidad familiar
	
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", lastname=" + lastname + ", birthdate=" + birthdate
				+ ", height=" + height + ", weight=" + weight + ", disorder=" + disorder + ", passportid=" + passportid
				+ ", enabled=" + enabled + ", deleted=" + deleted + "]";
	}

}
