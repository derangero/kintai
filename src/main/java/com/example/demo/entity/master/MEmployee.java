package com.example.demo.entity.master;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MEmployees")
public class MEmployee implements Serializable {
	 private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "stamp_deleted", columnDefinition="boolean default false")
    private Boolean stampDeleted = false;

    @ManyToOne()
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private MRole role;

    public MEmployee() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getStampDeleted() {
		return stampDeleted;
	}

	public void setStampDeleted(Boolean stampDeleted) {
		this.stampDeleted = stampDeleted;
	}

	public MRole getRole() {
		return role;
	}

	public void setRole(MRole role) {
		this.role = role;
	}
}