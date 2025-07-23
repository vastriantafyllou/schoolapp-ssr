package gr.aueb.cf.schoolapp2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeacherReadOnlyDTO {
    private Long id;
    private String uuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String firstname;
    private String lastname;
    private String vat;
    private String region;


}
