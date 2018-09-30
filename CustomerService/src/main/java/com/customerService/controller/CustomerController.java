package com.customerService.controller;

import com.customerService.model.Customer;
import com.customerService.payload.CustomerRegRequest;
import com.customerService.payload.CustomerRequest;
import com.customerService.payload.CustomerResponse;
import com.customerService.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/updateCustomer")
    public ResponseEntity<?> registerUser(@Valid @RequestBody CustomerRequest customerRequest) {
        Optional<Customer> customerConsultados = customerRepository.findById(customerRequest.getIdCustomer());
        
    	if (!customerRequest.getEmail().equals(customerConsultados.get().getEmail())) {
    		if(customerRepository.existsByEmail(customerRequest.getEmail())) {
                return new ResponseEntity(new CustomerResponse(false, "Email Address already in use!", null),
                        HttpStatus.BAD_REQUEST);
            }
		}
    	
    	if(passwordEncoder.matches(customerRequest.getOldPassword(), customerConsultados.get().getPassword())) {
        	Customer customer = new Customer(customerRequest.getIdCustomer(), customerConsultados.get().getIdCategory(), customerConsultados.get().getUsername(), customerRequest.getPassword(), customerRequest.getEmail());
        	customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        	Customer customerUpdated = customerRepository.save(customer);
        	return ResponseEntity.ok(new CustomerResponse(true, "User updated successfully", String.valueOf(customerUpdated.getId())));
        }else {
        	return ResponseEntity.ok(new CustomerResponse(false, "", String.valueOf("0")));
        }
    }
    
    @PostMapping("/registerCustomer")
    public ResponseEntity<?> registerUser(@Valid @RequestBody CustomerRegRequest customerRegRequest) {
        if(customerRepository.existsByUsername(customerRegRequest.getUsername())) {
            return new ResponseEntity(new CustomerResponse(false, "Username is already taken!", null),
                    HttpStatus.BAD_REQUEST);
        }

        if(customerRepository.existsByEmail(customerRegRequest.getEmail())) {
            return new ResponseEntity(new CustomerResponse(false, "Email Address already in use!", null),
                    HttpStatus.BAD_REQUEST);
        }

        Customer customer = new Customer(customerRegRequest.getIdCategory(), customerRegRequest.getUsername(),
        		customerRegRequest.getEmail(), customerRegRequest.getPassword());
        
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        Customer result = customerRepository.save(customer);

        return ResponseEntity.ok(new CustomerResponse(true, "User registered successfully", String.valueOf(result.getId())));
    }
}
