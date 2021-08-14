package com.stackroute.repository.V2;

import com.stackroute.domain.V2.BlogV2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This is a repository class which should implement CRUD Repository and
 * mark the specific class as a Data Access Object
 */
@Repository
public interface BlogRepositoryV2 extends CrudRepository<BlogV2 , Integer> {
}
