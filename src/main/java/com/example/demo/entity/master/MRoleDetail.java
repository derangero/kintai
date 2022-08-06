package com.example.demo.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mrole_details")
public class MRoleDetail {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "stamp_deleted")
    private Boolean stampDeleted = false;

    @ManyToOne()
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private MRole role;
    
    public MRoleDetail() {
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