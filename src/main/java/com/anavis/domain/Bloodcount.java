package com.anavis.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Bloodcount implements Serializable {

    private static final long serialVersionUID = 42533213345L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int typeA;
    private int typeB;
    private int typeAB;
    private int type0;

    public Bloodcount(){
        this.type0 = 0;
        this.typeA = 0;
        this.typeAB = 0;
        this.typeB = 0;
    }

    public int getTypeB() {
        return typeB;
    }

    public void setTypeB(int typeB) {
        this.typeB = typeB;
    }

    public int getTypeAB() {
        return typeAB;
    }

    public void setTypeAB(int typeAB) {
        this.typeAB = typeAB;
    }

    public int getTypeA() {
        return typeA;
    }

    public void setTypeA(int typeA) {
        this.typeA = typeA;
    }

    public int getType0() {
        return type0;
    }

    public void setType0(int type0) {
        this.type0 = type0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
