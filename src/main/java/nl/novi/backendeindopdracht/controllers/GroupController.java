package nl.novi.backendeindopdracht.controllers;

import nl.novi.backendeindopdracht.dtos.group.GroupDto;
import nl.novi.backendeindopdracht.dtos.group.GroupInputDto;
import nl.novi.backendeindopdracht.dtos.relative.RelativeDto;
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
//        groupService.addRelatives(newGroup, relatives );

        URI uri = URI.create (
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + groupDto.getId()).toUriString());

        return ResponseEntity.created(uri).body(groupDto);

    }

    // deze methode haalt de relatives op die in de group zitten

    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable("id") Long id, @RequestBody GroupInputDto groupNewInputDto) {
        GroupDto groupDto = groupService.updateGroup(id, groupNewInputDto );
        return ResponseEntity.ok().body(groupDto);
    }



    @GetMapping("")
    public ResponseEntity<List<GroupDto>> getAllGroups() {

        List<GroupDto> groupDtos = groupService.getAllGroups();

        return ResponseEntity.ok().body(groupDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable("id") Long id) {

       GroupDto groupDto = groupService.getGroupById(id);

        return ResponseEntity.ok().body(groupDto);
    }




}
