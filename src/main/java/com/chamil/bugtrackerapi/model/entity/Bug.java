package com.chamil.bugtrackerapi.model.entity;

import com.chamil.bugtrackerapi.model.entity.enums.Severity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Entity
public class Bug extends Issue{
    @Enumerated(EnumType.STRING)
    private Severity severity;
}
