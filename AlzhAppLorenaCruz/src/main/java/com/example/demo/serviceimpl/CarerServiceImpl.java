package com.example.demo.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Carer;
import com.example.demo.entity.FamilyUnit;
import com.example.demo.entity.Patient;
import com.example.demo.model.CarerModel;
import com.example.demo.repository.CarerRepository;
import com.example.demo.service.CarerService;

@Configuration
@Service("carerService")
public class CarerServiceImpl implements CarerService, UserDetailsService {

    @Autowired
    @Qualifier("carerRepository")
    private CarerRepository carerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Carer carer = carerRepository.findByUsername(username);
        if (carer == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return carer;
    }

	@Override
	public List<CarerModel> listAllCarer() {
		ModelMapper modelMapper = new ModelMapper();
		List<Carer> carerList = carerRepository.findAll();
		return carerList.stream().map(carer -> modelMapper.map(carer, CarerModel.class)).collect(Collectors.toList());
	}

	public Carer addAdmin(CarerModel carerModel) {
		Carer carer = transformCarer(carerModel);
		carer.setPassword(passwordEncoder.encode(carer.getPassword()));
		carer.setEnabled(true);
		carer.setRole("ROLE_ADMIN");
		System.out.println("CREADO");
		return carerRepository.save(carer);
	}

	@Override
	public int removeCarer(int id) {
		carerRepository.deleteById(id);
		return id;
	}

	@Override
	public Carer updateCarer(CarerModel carerModel) {
		Carer carer = transformCarer(carerModel);
		carer.setPassword(passwordEncoder.encode(carer.getPassword()));
		return carerRepository.save(carer);
	}

	@Override
	public Carer findByUsername(String passportID) {
		return carerRepository.findByUsername(passportID);
	}

	@Override
	public CarerModel findCarerByPatient(Patient patient) {
		return null;
	}

	@Override
	public CarerModel findCarerByPatientPID(String patientPassportId) {
		return null;
	}

	@Override
	public Carer transformCarer(CarerModel carerModel) {
		if (carerModel == null) {
			return null;
		}
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(carerModel, Carer.class);
	}

	@Override
	public Carer getCarerById(Long id) {
		List<CarerModel> carers = listAllCarer();
		for (CarerModel carer : carers) {
			if (carer.getId() == id) {
				return transformCarer(carer);
			}
		}
		return null;
	}

	@Override
	public CarerModel transformCarer(Carer carer) {
		if (carer == null) {
			return null;
		}
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(carer, CarerModel.class);
	}

	@Override
	public boolean checkPassword(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

	@Override
	public Carer register(CarerModel carerModel) {
		Carer carer = transformCarer(carerModel);
		carer.setPassword(passwordEncoder.encode(carer.getPassword()));
		carer.setRole("ROLE_CARER");
		List<Patient> patients = null;
		carer.setPatientsCare(patients);
		List<FamilyUnit> family = null;
		carer.setEnabled(true);
		carer.setFamilyUnit(family);
		return carerRepository.save(carer);
	}

	@Override
	public int enable(int id) {
		Carer c=carerRepository.findById(id);
		if(c.isEnabled()) {
			c.setEnabled(false);
			carerRepository.save(c);
			return 0;
		}else {
			c.setEnabled(true);
			carerRepository.save(c);
			return 1;
		}
	}
	
}
