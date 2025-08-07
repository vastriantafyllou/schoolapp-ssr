package gr.aueb.cf.schoolapp2.validator;

import gr.aueb.cf.schoolapp2.core.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp2.core.exceptions.EntityInvalidArgumentException;
import gr.aueb.cf.schoolapp2.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp2.model.static_data.Region;
import gr.aueb.cf.schoolapp2.repository.RegionRepository;
import gr.aueb.cf.schoolapp2.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
@Slf4j
public class TeacherInsertValidator implements Validator {

    private final TeacherRepository teacherRepository;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return TeacherInsertDTO.class == clazz;
    }

    @Override
    public void validate(@NonNull Object target, Errors errors) {
        TeacherInsertDTO teacherInsertDTO = (TeacherInsertDTO) target;

        if (teacherInsertDTO.getVat() != null && teacherRepository.findByVat(teacherInsertDTO.getVat()).isPresent()) {
            log.error("Save failed for Teacher with vat={}. Teacher already exists", teacherInsertDTO.getVat());
//            errors.rejectValue("vat", "To ΑΦΜ του καθηγητή υπάρχει ήδη");
            errors.rejectValue("vat", "vat.teacher.exists");
        }
    }
}