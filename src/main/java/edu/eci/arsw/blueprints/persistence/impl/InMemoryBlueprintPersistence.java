package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author hcadavid
 */
@Repository
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final ConcurrentHashMap<Tuple<String,String>,Blueprint> blueprints=new ConcurrentHashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts1 =new Point[]{new Point(140, 140),new Point(115, 115)};
        Point[] pts2 =new Point[]{new Point(142, 142),new Point(120, 120)};
        Point[] pts3 =new Point[]{new Point(110, 240),new Point(130, 115)};
        Blueprint bp1 =new Blueprint("author1", "bp1",pts1);
        blueprints.put(new Tuple<>(bp1.getAuthor(),bp1.getName()), bp1);
        Blueprint bp2 =new Blueprint("author1", "bp2",pts2);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        Blueprint bp3 =new Blueprint("author2", "bp3",pts3);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }
    }

    @Override
    public void updateBlueprint(String author,String bprintname, Blueprint bp) throws BlueprintNotFoundException {
        Blueprint blueprint = getBlueprint(author,bprintname);
        blueprint.setPoints(bp.getPoints());
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> authorBlueprints = new HashSet<>();
        for (Tuple<String,String> key: blueprints.keySet()){
            if(key.o1.equals(author)){
                authorBlueprints.add(getBlueprint(author, key.o2));
            }
        }
        return authorBlueprints;
    }

    @Override
    public Set<Blueprint> getAllBlueprints() throws BlueprintPersistenceException {
        return new HashSet<Blueprint>(blueprints.values());
    }
}
