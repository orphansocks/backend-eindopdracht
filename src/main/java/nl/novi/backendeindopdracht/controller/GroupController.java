package nl.novi.backendeindopdracht.controller;

import nl.novi.backendeindopdracht.dtos.group.GroupDto;
import nl.novi.backendeindopdracht.dtos.group.GroupInputDto;
import nl.novi.backendeindopdracht.services.GroupService;
import nl.novi.backendeindopdracht.services.RelativeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupservice;
    private final RelativeService teacherservice;

    public GroupController(GroupService groupservice, RelativeService teacherservice) {
        this.groupservice = groupservice;
        this.teacherservice = teacherservice;
    }

    @PostMapping("")
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupInputDto groupInputDto) {
        GroupDto groupDto = groupservice.createGroup(groupInputDto);

        URI uri = URI.create (
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + groupDto.getId()).toUriString());

        return ResponseEntity.created(uri).body(groupDto);

    }

    @GetMapping("/groups")
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        List<GroupDto> groupDtos = groupservice.getAllGroups();

        return ResponseEntity.ok().body(groupDtos);
    }



}
