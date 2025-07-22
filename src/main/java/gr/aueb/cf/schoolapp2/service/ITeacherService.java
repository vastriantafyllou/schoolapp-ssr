package gr.aueb.cf.schoolapp2.service;

import gr.aueb.cf.schoolapp2.core.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp2.core.exceptions.EntityInvalidArgumentException;
import gr.aueb.cf.schoolapp2.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp2.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp2.model.Teacher;
import org.springframework.data.domain.Page;

public interface ITeacherService {

    Teacher saveTeacher(TeacherInsertDTO teacherInsertDTO)
        throws EntityAlreadyExistsException, EntityInvalidArgumentException;

    Page<TeacherReadOnlyDTO> getPaginatedTeachers(int page, int size);
}
