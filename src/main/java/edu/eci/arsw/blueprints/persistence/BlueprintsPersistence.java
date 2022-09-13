package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;

import java.util.Set;

/**
 *
 * @author hcadavid
 */
public interface BlueprintsPersistence {

    /**
     *
     * @param bp the new blueprint
     * @throws BlueprintPersistenceException if a blueprint with the same name already exists,
     *    or any other low-level persistence error occurs.
     */
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException;


    /**
     * Update Blueprint
     * @param author blueprint's author
     * @param bprintname blueprint's author
     * @param bp the new blueprint
     * @throws BlueprintPersistenceException
     */
    public void updateBlueprint(String author,String bprintname, Blueprint bp) throws BlueprintPersistenceException, BlueprintNotFoundException;


    /**
     *
     * @throws BlueprintPersistenceException if a blueprint with the same name already exists,
     *    or any other low-level persistence error occurs.
     */
    public Set<Blueprint> getAllBlueprints() throws BlueprintPersistenceException;

    /**
     *
     * @param author blueprint's author
     * @param bprintname blueprint's author
     * @return the blueprint of the given name and author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String bprintname) throws BlueprintNotFoundException;

    /**
     * Metod that search all the blueprints for one author
     * @param author the name of the author owner of blueprints
     * @return ArrayList of BluePrints
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws  BlueprintNotFoundException;
}
