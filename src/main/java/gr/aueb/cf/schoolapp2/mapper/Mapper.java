package gr.aueb.cf.schoolapp2.mapper;

import gr.aueb.cf.schoolapp2.dto.TeacherEditDTO;
import gr.aueb.cf.schoolapp2.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp2.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp2.model.Teacher;
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
}
