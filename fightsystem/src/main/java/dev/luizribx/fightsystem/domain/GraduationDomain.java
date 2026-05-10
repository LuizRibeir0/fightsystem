package dev.luizribx.fightsystem.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "graduation")
public class GraduationDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modalitie_id")
    private ModalitiesDomain modalities;

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

    public ModalitiesDomain getModalities() {
        return modalities;
    }

    public void setModalities(ModalitiesDomain modalities) {
        this.modalities = modalities;
    }
}
