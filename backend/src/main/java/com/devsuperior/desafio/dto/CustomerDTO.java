package com.devsuperior.desafio.dto;

import java.io.Serializable;
import java.time.Instant;

import com.devsuperior.desafio.entities.Customer;

public class CustomerDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String cpf;
    private Double income;
    private Integer children;
    private Instant birthDate;

    public CustomerDTO() {
    }
    
    public CustomerDTO(Long id, String name, String cpf, Double income, Integer children, Instant birthDate) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.children = children;
        this.birthDate = birthDate;
    }
    
    public CustomerDTO(Customer cust){
        this.id = cust.getId();
        this.name = cust.getName();
        this.cpf = cust.getCpf();
        this.income = cust.getIncome();
        this.children = cust.getChildren();
        this.birthDate = cust.getBirthDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }
    
    
}
