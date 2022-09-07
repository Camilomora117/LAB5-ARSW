package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;

public interface Filter {

    /**
     * Method that filter and delete points
     * @param bp is teh Blueprint to apply the filter
     * @return Blueprint filtered
     */
    public Blueprint filterPoints(Blueprint bp);
}