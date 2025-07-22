package gr.aueb.cf.schoolapp2.service;

import gr.aueb.cf.schoolapp2.core.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp2.core.exceptions.EntityInvalidArgumentException;
import gr.aueb.cf.schoolapp2.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp2.mapper.Mapper;
import gr.aueb.cf.schoolapp2.model.Teacher;
import gr.aueb.cf.schoolapp2.model.static_data.Region;
import gr.aueb.cf.schoolapp2.repository.RegionRepository;
import gr.aueb.cf.schoolapp2.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService implements ITeacherService {
    private final TeacherRepository teacherRepository;
    private final RegionRepository regionRepository;
    private final Mapper mapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Teacher saveTeacher(TeacherInsertDTO dto)
            throws EntityAlreadyExistsException, EntityInvalidArgumentException {

        try {
            if (teacherRepository.findByVat(dto.getVat()).isPresent()) {
                throw new EntityAlreadyExistsException("Teacher", "Teacher with vat " + dto.getVat() + " already exists");
            }

            Region region = regionRepository.findById(dto.getRegionId())
                    .orElseThrow(() -> new  EntityInvalidArgumentException("Region", "Invalid region id"));

            Teacher teacher = mapper.mapToTeacherEntity(dto);
            region.addTeacher(teacher);
            teacherRepository.save(teacher);
            log.info("Teacher with vat={} saved.", dto.getVat());   // structured logging vat={}
            return teacher;

        } catch (EntityAlreadyExistsException e) {
            log.error("Save failed for Teacher with vat={}. Teacher already exists", dto.getVat(), e);
            throw e;
        } catch (EntityInvalidArgumentException e) {
            log.error("Save failed for Teacher with vat={}. Region id={} invalid", dto.getVat(), dto.getRegionId(), e);
            throw e;
        }
    }
}
