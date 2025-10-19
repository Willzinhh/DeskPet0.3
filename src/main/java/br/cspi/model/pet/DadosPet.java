package br.cspi.model.pet;

import java.time.LocalDate;

public record DadosPet(Long id, String nomepet, String especie, String raca, String sexo, String descricao, Long tutor_id, Long owner_id) {
    public DadosPet(Pet p){
        this(p.getId(),p.getNomepet(), p.getEspecie(),p.getRaca(),p.getSexo(),p.getDescricao(), p.getTutor().getId(), p.getOwner().getId());
    }
}
