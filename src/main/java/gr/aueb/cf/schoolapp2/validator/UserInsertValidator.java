package gr.aueb.cf.schoolapp2.validator;

import gr.aueb.cf.schoolapp2.core.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp2.core.exceptions.EntityInvalidArgumentException;
import gr.aueb.cf.schoolapp2.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp2.dto.UserInsertDTO;
import gr.aueb.cf.schoolapp2.model.static_data.Region;
import gr.aueb.cf.schoolapp2.repository.RegionRepository;
import gr.aueb.cf.schoolapp2.repository.TeacherRepository;
import gr.aueb.cf.schoolapp2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserInsertValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return UserInsertDTO.class == clazz;
    }

    @Override
    public void validate(@NonNull Object target, Errors errors) {
        UserInsertDTO userInsertDTO = (UserInsertDTO) target;

        if (userRepository.findByUsername(userInsertDTO.getUsername()).isPresent()) {
            log.error("Save failed for user with username={}. " , userInsertDTO.getUsername());
            errors.rejectValue("username", "username.user.exists");
        }
    }
}
