package nl.novi.backendeindopdracht.dtos.relative;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class RelativeInputDto {

    @NotNull(message = "Firstname is required")
    public String firstName;
    public String lastName;
    public String nickName;
    @Past
    public LocalDate dob;
    public String socialStatus;
    public Boolean hasKids;
    @PositiveOrZero(message = "Cannot be negative")
    public Integer amountOfKids;
    public String namesOfKids;
    public String misc;
    public String relation;



}
