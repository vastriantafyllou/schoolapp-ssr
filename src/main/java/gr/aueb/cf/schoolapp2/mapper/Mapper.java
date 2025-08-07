package gr.aueb.cf.schoolapp2.mapper;

import gr.aueb.cf.schoolapp2.core.enums.Role;
import gr.aueb.cf.schoolapp2.dto.TeacherEditDTO;
import gr.aueb.cf.schoolapp2.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp2.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp2.dto.UserInsertDTO;
import gr.aueb.cf.schoolapp2.model.Teacher;
import gr.aueb.cf.schoolapp2.model.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Teacher mapToTeacherEntity(TeacherInsertDTO dto) {
        return new Teacher(null, null, dto.getVat(), dto.getFirstname(), dto.getLastname(), null);
    }

    public TeacherReadOnlyDTO mapToTeacherReadOnlyDTO(Teacher teacher) {
        return new TeacherReadOnlyDTO(teacher.getId(), teacher.getUuid(), teacher.getCreatedAt(), teacher.getUpdatedAt(),
                teacher.getFirstname(), teacher.getLastname(), teacher.getVat(), teacher.getRegion().getName());
    }

    public TeacherEditDTO mapToTeacherEditDTO(Teacher teacher) {
        return new TeacherEditDTO(teacher.getUuid(), teacher.getFirstname(), teacher.getLastname(), teacher.getVat(), teacher.getRegion().getId());
    }

    public User mapToUserEntity(UserInsertDTO userInsertDTO) {
        return new User(userInsertDTO.getUsername(), userInsertDTO.getPassword(),
                Role.valueOf(userInsertDTO.getRole().toUpperCase()));
    }
}
