package com.eliasshallouf.examples.library_management_system.service.db;

import com.eliasshallouf.examples.library_management_system.domain.model.exceptions.RecordNotFoundException;
import com.eliasshallouf.examples.library_management_system.domain.model.helpers.IndexedEntity;
import com.eliasshallouf.examples.library_management_system.service.utils.ListHelper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public abstract class AbstractEntityService<
    Id,
    Entity extends IndexedEntity<Id>,
    Repository extends CrudRepository<Entity, Id>
> {
    @Autowired
    private Repository repository;

    public List<Entity> getAll() {
        return ListHelper.toList(repository.findAll());
    }

    public Entity findById(Id id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(getEntityClass()));
    }

    public void insert(Entity entity) {
        repository.save(entity);
    }

    public void update(Id id, Entity entity) throws RecordNotFoundException {
        if(!repository.existsById(id))
            throw new RecordNotFoundException(getEntityClass());

        entity.setId(id);
        repository.save(entity);
    }

    public void delete(Id id) throws RecordNotFoundException {
        if(!repository.existsById(id))
            throw new RecordNotFoundException(getEntityClass());

        repository.deleteById(id);
    }

    public abstract Class<Entity> getEntityClass();
}
