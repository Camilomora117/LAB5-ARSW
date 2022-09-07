package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.Filter;

import java.util.ArrayList;
import java.util.List;

//@Service
public class UnderSampling implements Filter {

    /**
     *
     * @param bp is teh Blueprint to apply the filter
     * @return suprime 1 de cada 2 puntos del plano, de manera intercalada
     */
    @Override
    public Blueprint filterPoints(Blueprint bp) {
        List<Point> points = bp.getPoints();
        List<Point> pointsResult = new ArrayList<>();
        for(int i = 0; i< points.size(); i+=2){
            pointsResult.add(points.get(i));
        }
        bp.setPoints(pointsResult);
        return bp;
    }

}
