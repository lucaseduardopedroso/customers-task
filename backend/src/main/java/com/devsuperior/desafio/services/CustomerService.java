package com.devsuperior.desafio.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.devsuperior.desafio.dto.CustomerDTO;
import com.devsuperior.desafio.entities.Customer;
import com.devsuperior.desafio.repositories.CustomerRepository;
import com.devsuperior.desafio.services.exceptions.DatabaseException;
import com.devsuperior.desafio.services.exceptions.ResourceNotFoundException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Transactional(readOnly = true)
    public Page<CustomerDTO> findAllPaged(PageRequest pageRequest) {
        Page<Customer> list = repository.findAll(pageRequest);
        return list.map(x -> new CustomerDTO(x));
    }

    @Transactional(readOnly = true)
    public CustomerDTO findById(Long id) {
        Optional<Customer> obj = repository.findById(id);
        Customer entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new CustomerDTO(entity);
    }

    @Transactional
    public CustomerDTO insert(CustomerDTO dto) {
        Customer entity = new Customer();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new CustomerDTO(entity);
    }

    @Transactional
    public CustomerDTO update(Long id, CustomerDTO dto) {
        try {
            Customer entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new CustomerDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " not found");
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id " + id + " not found");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(CustomerDTO dto, Customer entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
