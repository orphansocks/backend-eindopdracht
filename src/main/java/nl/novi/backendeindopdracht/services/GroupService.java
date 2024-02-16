package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.dtos.group.GroupDto;
import nl.novi.backendeindopdracht.dtos.group.GroupInputDto;
import nl.novi.backendeindopdracht.model.Group;
import nl.novi.backendeindopdracht.model.Relative;
import nl.novi.backendeindopdracht.repositories.GroupRepository;
import nl.novi.backendeindopdracht.repositories.RelativeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    public List<GroupDto> getAllGroups() {
        List<Group> groupList = groupRepository.findAll();
        return transferEntityListToDtoList(groupList);
    }


    // DE TRANSFER METHODS DTO < > ENTITY:

    public Group transferToEntity(GroupInputDto dto) {

        Group group = new Group();

        group.setGroupName(dto.groupName);
        group.setGroupPlace(dto.groupPlace);

        for (Long id : dto.relativeIds) {

            Optional<Relative> optionalRelative = relativeRepository.findById(id);

            if (optionalRelative.isPresent()) {
                Relative relative = optionalRelative.get();
                group.getRelatives().add(relative);
            }
        }

        return group;

    }

    public GroupDto transferToDto(Group group) {

        GroupDto dto = new GroupDto();

        dto.setId(group.getId());
        dto.setGroupName(group.getGroupName());
        dto.setGroupPlace(group.getGroupPlace());

//        if(group.getRelatives() != null)
//            dto.setRelatives(RelativeService.transferEntityListToDtoList(group.getRelatives()));

        return dto;
    }

    public List<GroupDto> transferEntityListToDtoList(List<Group> groups) {
        List<GroupDto> groupDtoList = new ArrayList<>();

        for(Group group : groups) {

            GroupDto groupDto = transferToDto(group);

            groupDtoList.add(groupDto);
        }

        return groupDtoList;
    }




}
