package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.dtos.group.GroupDto;
import nl.novi.backendeindopdracht.dtos.group.GroupInputDto;
import nl.novi.backendeindopdracht.exceptions.RecordNotFoundException;
import nl.novi.backendeindopdracht.model.Group;
import nl.novi.backendeindopdracht.model.Relative;
import nl.novi.backendeindopdracht.repositories.GroupRepository;
import nl.novi.backendeindopdracht.repositories.RelativeRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final RelativeRepository relativeRepository;

    public GroupService(GroupRepository groupRepository,
                        RelativeRepository relativeRepository) {
        this.groupRepository = groupRepository;
        this.relativeRepository = relativeRepository;
    }

    // CREATE DELETE UPDATE GETALL GETBYID GETBYNAME

    public GroupDto createGroup(GroupInputDto groupInputDto) {

        Group group = transferToEntity(groupInputDto);
        groupRepository.save(group);

        return transferToDto(group);

    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public GroupDto getGroupById(Long id) {

        if(groupRepository.findById(id).isPresent()) {

            Group group = groupRepository.findById(id).get();

            return transferToDto(group);

        } else {
            throw new RecordNotFoundException("no group found");
        }

    }

    public List<GroupDto> getAllGroups() {

        List<GroupDto> collection = new ArrayList<>();
        List<Group> groupList = groupRepository.findAll();

        for(Group group : groupList) {
            collection.add(transferToDto(group));

        }
        return collection;
    }


    // DE TRANSFER METHODS DTO < > ENTITY:

    public Group transferToEntity(GroupInputDto groupInputDto) {

        Group group = new Group();

        group.setGroupName(groupInputDto.groupName);
        group.setGroupPlace(groupInputDto.groupPlace);

        Set<Relative> relatives = new HashSet<>();

        for (Long id : groupInputDto.relativeIds) {

            Optional<Relative> optionalRelative = relativeRepository.findById(id);

            if (optionalRelative.isPresent()) {
                Relative relative = optionalRelative.get();
                group.getRelatives().add(relative);

            }

        }

        group.setRelatives(relatives);
        groupRepository.save(group);


        return group;

    }

    public GroupDto transferToDto(Group group) {

        GroupDto dto = new GroupDto();

        dto.setId(group.getId());
        dto.setGroupName(group.getGroupName());
        dto.setGroupPlace(group.getGroupPlace());
        dto.setRelatives(group.getRelatives());


        return dto;
    }



}
