package edu.eci.arsw.blueprints.service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

import java.util.HashSet;
import java.util.Set;

import edu.eci.arsw.blueprints.persistence.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service("bluePrintsService")
public class BlueprintsServices {

    @Autowired
    BlueprintsPersistence bpp = null;

    @Autowired
    Filter filter;

    /**
     * Añade un Blue print
     * @param bp Blueprint
     * @throws BlueprintPersistenceException
     */
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        bpp.saveBlueprint(bp);
    }

    /**
     *
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public void updateBlueprint(String author,String name, Blueprint bp) throws BlueprintNotFoundException, BlueprintPersistenceException {
        bpp.updateBlueprint(author, name, bp);
    }

    /**
     * Retorna todos los bluePrints
     * @return Set que contiene los bluePrints
     * @throws BlueprintPersistenceException
     */
    public Set<Blueprint> getAllBlueprints() throws BlueprintPersistenceException {
        return bpp.getAllBlueprints();
    }

    /**
     *
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
        Blueprint blueprint;
        blueprint = bpp.getBlueprint(author, name);
        blueprint =  filter.filterPoints(blueprint);
        return blueprint;
    }

    /**
     *
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        Set<Blueprint> blueprints = bpp.getBlueprintsByAuthor(author);
        Set<Blueprint> blueprintsWhitFilter = new HashSet<>();
        for(Blueprint bp: blueprints){
            bp = filter.filterPoints(bp);
            blueprintsWhitFilter.add(bp);
        }
        return blueprintsWhitFilter;
    }

}
