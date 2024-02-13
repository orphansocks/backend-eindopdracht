package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.dtos.relative.RelativeInputDto;
import nl.novi.backendeindopdracht.dtos.relative.RelativeOutputDto;
import nl.novi.backendeindopdracht.repositories.RelativeRepository;
import org.springframework.stereotype.Service;

@Service
public class RelativeService {
    private final RelativeRepository relativeRepository;

    public RelativeService(RelativeRepository relativeRepository) {
    this.relativeRepository = relativeRepository;
}

public RelativeOutputDto createRelative(RelativeInputDto relativeInputDto) {

}



}
