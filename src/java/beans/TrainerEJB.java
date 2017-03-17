/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Entity.Battle;
import Entity.Pokemon;
import Entity.Trainer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

/**
 *
 * @author dam
 */
@Stateless
public class TrainerEJB {

    @PersistenceUnit
    EntityManagerFactory emf;

    // Insertamos Trainer
    public boolean insertarTrainer(Trainer t) {
        if (!existeTrainer(t)) {
            EntityManager em = emf.createEntityManager();
            em.persist(t);
            em.close();
            return true;
        }
        return false;
    }

    public boolean existeTrainer(Trainer t) {
        return (emf.createEntityManager().find(Trainer.class, t.getName())) != null;
    }

    public Trainer getTrainerByNombre(String t) {
        return emf.createEntityManager().find(Trainer.class, t);
    }

    public Pokemon getPokemonByNombre(String t) {
        return emf.createEntityManager().find(Pokemon.class, t);
    }

    public List<Pokemon> selectAllPokemons() {
        return emf.createEntityManager().createNamedQuery("Pokemon.findAll").getResultList();
    }

    public List<Trainer> selectAllTrainers() {
        return emf.createEntityManager().createNamedQuery("Trainer.findAll").getResultList();
    }

    public List<Pokemon> selectPokemonLevel() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select p from Pokemon p order by p.level desc, p.life desc");
        return q.getResultList();
    }

    public List<Trainer> selectTrainerPoints() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select t from Trainer t order by t.points desc");
        return q.getResultList();
    }

    public List<Battle> selectPokemonBatalla() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select b from Battle b group by b.winner order by b.winner desc");
        return q.getResultList();
    }

    public boolean insertarPokemon(Pokemon p) {
        if (!existePokemon(p)) {
            EntityManager em = emf.createEntityManager();
            em.persist(p);
            em.close();
            return true;
        }
        return false;
    }
  public boolean buyPotions(String nombre, int potions) {
        EntityManager em = emf.createEntityManager();
        Trainer trainer = em.find(Trainer.class, nombre);
        boolean ok = false;
        if (trainer != null) {
            trainer.setPoints(trainer.getPoints()-(potions*10));
            em.persist(trainer);
            ok = true;
        }
        em.close();
        return ok;
    }

    public List<Pokemon> selectPokemonTrainer(Trainer t) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("Select p from Pokemon p where p.trainer = :entrenador");
        q.setParameter("entrenador", t.getName());
        return q.getResultList();
    }

    public boolean modificarPokemonVida(int life) {
        EntityManager em = emf.createEntityManager();
        Pokemon pokemon = em.find(Pokemon.class, life);
        boolean ok = false;
        if (pokemon != null) {
            pokemon.setLife(life);
            em.persist(pokemon);
            ok = true;
        }
        em.close();
        return ok;
    }

    public boolean borrarPokemon(String name) {
        EntityManager em = emf.createEntityManager();
        Pokemon pokemon = em.find(Pokemon.class, name);
        boolean ok = false;
        if (pokemon != null) {
            em.remove(pokemon);
            ok = true;
        }
        em.close();
        return ok;
    }

    public boolean existePokemon(Pokemon p) {
        return (emf.createEntityManager().find(Pokemon.class, p.getName())) != null;
    }
}
