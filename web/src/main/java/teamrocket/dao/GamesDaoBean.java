package teamrocket.dao;


import teamrocket.domain.Game;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "games")
public class GamesDaoBean implements Dao {

    @PersistenceContext(unitName = "grajdolex-hibernate")
    protected EntityManager entityManager;


    @Override
    public void add(Object o) {
        entityManager.persist(o);
    }

    @Override
    public void update(Object o) {
        entityManager.merge(o);
    }

    @Override
    public Game findById(int id) {
        return entityManager.find(Game.class, id);
    }

    @Override
    public void deleteById(int id) {
        Game entity = findById(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public List<Game> findAll() {

        Query query = entityManager.createNamedQuery("Game.findAll");
        return  query.getResultList();
    }
}
