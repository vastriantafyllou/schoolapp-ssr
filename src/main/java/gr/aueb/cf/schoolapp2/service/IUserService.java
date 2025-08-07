package gr.aueb.cf.schoolapp2.service;

import gr.aueb.cf.schoolapp2.core.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp2.dto.UserInsertDTO;

public interface IUserService {
    void saveUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException;
}
