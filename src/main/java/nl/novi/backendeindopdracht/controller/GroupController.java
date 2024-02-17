package nl.novi.backendeindopdracht.controller;

import nl.novi.backendeindopdracht.dtos.group.GroupDto;
import nl.novi.backendeindopdracht.dtos.group.GroupInputDto;
import nl.novi.backendeindopdracht.services.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("")
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupInputDto groupInputDto) {

        GroupDto groupDto = groupService.createGroup(groupInputDto);

        URI uri = URI.create (
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + groupDto.getGroupName()).toUriString());

        return ResponseEntity.created(uri).body(groupDto);

    }

    @GetMapping("")
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        List<GroupDto> groupDtos = groupService.getAllGroups();

        return ResponseEntity.ok().body(groupDtos);
    }

//    @GetMapping("/{groupname}")
//    public ResponseEntity<GroupDto> getGroup(@PathVariable("groupname") String groupName) {
//
//        GroupDto optionalGroup = groupService.getGroup(groupName);
//
//        return ResponseEntity.ok().body(optionalGroup);
//
//    }



}
