package com.example.demo.entity.master;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MRoles")
public class MRole implements Serializable {
	 private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private Integer code;

    @Column(name = "stamp_deleted")
    private Boolean stampDeleted = false;

    public MRole() {
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

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Boolean getStampDeleted() {
		return stampDeleted;
	}

	public void setStampDeleted(Boolean stampDeleted) {
		this.stampDeleted = stampDeleted;
	}
}