package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.dtos.group.GroupDto;
import nl.novi.backendeindopdracht.dtos.group.GroupInputDto;
import nl.novi.backendeindopdracht.dtos.relative.RelativeDto;
import nl.novi.backendeindopdracht.exceptions.RecordNotFoundException;
import nl.novi.backendeindopdracht.exceptions.UsernameNotFoundException;
import nl.novi.backendeindopdracht.models.Group;
import nl.novi.backendeindopdracht.models.Relative;
import nl.novi.backendeindopdracht.models.Role;
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


    public GroupDto createGroup(GroupInputDto groupInputDto) {

        Group group = transferToEntity(groupInputDto);
        groupRepository.save(group);

        return transferToDto(group);

    }

//    public GroupDto updateGroup(Long storedGroupId, GroupInputDto groupNewInputDto) {
//
//        if (!groupRepository.findById(storedGroupId).isPresent()) {
//
//            throw new RecordNotFoundException("no Group found");
//        }
//
//        Group storedGroup = groupRepository.findById(storedGroupId).orElse(null);
//
//        storedGroup.setGroupName(groupNewInputDto.groupName);
//        storedGroup.setGroupPlace(groupNewInputDto.groupPlace);
//
//        Set<Relative> relatives = new HashSet<>();
//
//        for (Long id : groupNewInputDto.relativeIds) {
//
//            Optional<Relative> optionalRelative = relativeRepository.findById(storedGroupId);
//
//            if (optionalRelative.isPresent()) {
//                Relative relative = optionalRelative.get();
//                storedGroup.getRelatives().add(relative);
//
//            }
//
//        }
//
//        storedGroup.setRelatives(relatives);
//
//        Group group = transferToEntity(groupNewInputDto);
//
//        group.setId(storedGroup.getId());
//
//        groupRepository.save(group);
//
//        return transferToDto(group);
//
//    }

    public GroupDto updateGroup(Long storedGroupId, GroupInputDto groupNewInputDto) {
        // Check if the group exists
        Group storedGroup = groupRepository.findById(storedGroupId)
                .orElseThrow(() -> new RecordNotFoundException("No Group found"));

        // Update group information
        storedGroup.setGroupName(groupNewInputDto.getGroupName());
        storedGroup.setGroupPlace(groupNewInputDto.getGroupPlace());

        // Update relatives
        Set<Relative> relatives = new HashSet<>();
        for (Long relativeId : groupNewInputDto.getRelativeIds()) {
            Optional<Relative> optionalRelative = relativeRepository.findById(relativeId);
            optionalRelative.ifPresent(relatives::add);
        }
        storedGroup.setRelatives(relatives);

        // Save the updated group
        groupRepository.save(storedGroup);

        // Return the updated group DTO
        return transferToDto(storedGroup);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public GroupDto getGroupById(Long id) {

        if (groupRepository.findById(id).isPresent()) {

            Group group = groupRepository.findById(id).get();

            return transferToDto(group);

        } else {
            throw new RecordNotFoundException("no group found");
        }

    }

    public List<GroupDto> getAllGroups() {

        List<GroupDto> collection = new ArrayList<>();
        List<Group> groupList = groupRepository.findAll();

        for (Group group : groupList) {
            collection.add(transferToDto(group));

        }
        return collection;
    }


    public Group transferToEntity(GroupInputDto groupInputDto) {

        Group group = new Group();

        group.setGroupName(groupInputDto.groupName);
        group.setGroupPlace(groupInputDto.groupPlace);

        Set<Relative> groupRelatives = new HashSet<>();

        for (Long id : groupInputDto.relativeIds) {

            Optional<Relative> optionalRelative = relativeRepository.findById(id);

            if (optionalRelative.isPresent()) {
                Relative relative = optionalRelative.get();
                groupRelatives.add(relative);

            } else {
                throw new RecordNotFoundException("Relative with Id " + id + " not found");
            }


        }
        group.setRelatives(groupRelatives);

        groupRepository.save(group);


        return group;

    }

    public GroupDto transferToDto(Group group) {

        GroupDto dto = new GroupDto();

        dto.setId(group.getId());
        dto.setGroupName(group.getGroupName());
        dto.setGroupPlace(group.getGroupPlace());

        Set<Relative> groupRelatives = new HashSet<>();

        for (Relative relative : group.getRelatives()) {

            RelativeDto relativeDto = assignRelativeToDto(relative);
            groupRelatives.add(relative);
        }

        dto.setRelatives(groupRelatives);
        return dto;
    }

    private RelativeDto assignRelativeToDto(Relative relative) {

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

}
