package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.dtos.relative.RelativeInputDto;
import nl.novi.backendeindopdracht.dtos.relative.RelativeDto;
import nl.novi.backendeindopdracht.exceptions.RecordNotFoundException;
import nl.novi.backendeindopdracht.model.Relative;
import nl.novi.backendeindopdracht.repositories.RelativeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelativeService {
    private final RelativeRepository relativeRepository;

    public RelativeService(RelativeRepository relativeRepository) {
        this.relativeRepository = relativeRepository;
}

// DIT IS DE METHODE OM DE RELATIVE AAN TE MAKEN
    // DAARBIJ HEB JE DE RELATIVE DTO NODIG ALS INPUT
    // JE RETOURNEERT DE RELATIVE WEER NA DE SAVE IN DE DATABASE
    // = PUBLIC RETURNTYPE METHODENAAM(PARAMETER)

public RelativeDto createRelative(RelativeInputDto dto) {

            Relative relative = transferToEntity(dto);
            relativeRepository.save(relative);

        return transferToDto(relative);
}

public void deleteRelative(Long id) {

        relativeRepository.deleteById(id);
}

public RelativeDto updateRelative(Long id, RelativeInputDto relativeNewInputDto) {

        if(relativeRepository.findById(id).isPresent()) {

            Relative relative = relativeRepository.findById(id).get();

            Relative newInputRelative = transferToEntity(relativeNewInputDto);
            newInputRelative.setId(relative.getId());

            relativeRepository.save(newInputRelative);

            return transferToDto(newInputRelative);

        } else {
            throw new RecordNotFoundException("no relative found");
        }
}

public RelativeDto getRelativeById(Long id) {

        if(relativeRepository.findById(id).isPresent()) {
            Relative relative = relativeRepository.findById(id).get();
            //RelativeDto relativeDto = transferToDto(relative);
            // LOGICA OPHALEN VOOR GROUP
            return transferToDto(relative);
        } else {
            throw new RecordNotFoundException("no relative found");
        }
}

public List<RelativeDto> getAllRelatives() {
        List<Relative> relativeList = relativeRepository.findAll();
        return transferEntityListToDtoList(relativeList);
}

public List<RelativeDto> getAllRelativesByName(String firstName) {
        List<Relative> relativeList = relativeRepository.findAllByFirstNameEqualsIgnoreCase(firstName);

        return transferEntityListToDtoList(relativeList);
}


// DE 2 MAPPERFUNCTIES DIE VELDEN KOPIEREN VAN EN NAAR DE DATABASE:

public Relative transferToEntity(RelativeInputDto dto) {

            Relative relative = new Relative();

        relative.setFirstName(dto.firstName);
        relative.setLastName(dto.lastName);
        relative.setNickName(dto.nickName);
        relative.setDob(dto.dob);
        relative.setSocialStatus(dto.socialStatus);
        relative.setHasKids(dto.hasKids);
        relative.setAmountOfKids(dto.amountOfKids);
        relative.setNamesOfKids(dto.namesOfKids);
        relative.setMisc(dto.misc);
        relative.setRelation(dto.relation);

        return relative;
}

public RelativeDto transferToDto(Relative relative) {

            RelativeDto dto = new RelativeDto();

        dto.setId(relative.getId());
        dto.setFirstName(relative.getFirstName());
        dto.setLastName(relative.getLastName());
        dto.setNickName(relative.getNickName());
        dto.setDob(relative.getDob());
        dto.setSocialStatus(relative.getSocialStatus());
        dto.setHasKids(relative.getHasKids());
        dto.setAmountOfKids(relative.getAmountOfKids());
        dto.setNamesOfKids(relative.getNamesOfKids());
        dto.setMisc(relative.getMisc());
        dto.setRelation(relative.getRelation());

        return dto;

}

// DE MAPPERFUNCTIE VOOR DE TRANSFER
// VAN EEN LIJST MET ENTITEITEN UIT DE DATABASE NAAR EEN LIJST MET DTOS OM TERUG TE GEVEN

    public List<RelativeDto> transferEntityListToDtoList(List<Relative> relatives) {

        List<RelativeDto> relativeDtoList = new ArrayList<>();

        for(Relative relative : relatives) {

            RelativeDto relativeDto = transferToDto(relative);
            //VOEG LOGICA VOOR ADD GROUP TOE
            relativeDtoList.add(relativeDto);
        }


        return relativeDtoList;
    }






}
