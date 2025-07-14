package gr.aueb.cf.schoolapp2.repository;

import gr.aueb.cf.schoolapp2.model.static_data.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RegionRepository
        extends JpaRepository<Region, Long>, JpaSpecificationExecutor<Region> {

}
