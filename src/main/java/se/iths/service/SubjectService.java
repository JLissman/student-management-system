package se.iths.service;

import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {
    @PersistenceContext
    EntityManager entityManager;

    public Subject create(Subject subject) {
            entityManager.persist(subject);
            return subject;

    }

    public Subject read(Long id) {
        return entityManager.find(Subject.class, id);
    }

    public Subject update(Subject subject) {
        entityManager.merge(subject);
        return entityManager.find(Subject.class, subject.getId());
    }

    public Subject delete(Long id) {
        Subject foundSubject = entityManager.find(Subject.class, id);
        entityManager.remove(foundSubject);
        return foundSubject;
    }
    public List<Subject> getAll(){
        return entityManager.createQuery("SELECT s FROM Subject s", Subject.class).getResultList();

    }


}

