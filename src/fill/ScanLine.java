package fill;

import model.Edge;
import model.Point;
import renderer.Renderer2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScanLine implements Filler {

    private Renderer2D renderer;

    private List<Point> points;
    private int fillColor, edgeColor;

    @Override
    public void fill() {
        scanLine();
    }

    @Override
    public void setRenderer(Renderer2D renderer) {
        this.renderer = renderer;
    }

    public void init(List<Point> points, int fillColor, int edgeColor) {
        this.points = points;
        this.fillColor = fillColor;
        this.edgeColor = edgeColor;
    }

    private void scanLine() {
        List<Edge> edges = new ArrayList<>();
        // projet všechny body (list points) a vytvořit z nich hrany (stejně nepravidelný n-úhelník)
        // 0. a 1. vrchol budou první hrana; 1. a 2. vrchol budou druhá hrana; ...; poslední a 0. vrchol
        // ignorovat vodorovné úsečky
        // vytvořené nevodorovné hrany se zorientují a přidají do seznamu

        // výsledkem je seznam zorientovaných hran bez vodorovných úseků

        // najít minimum a maximum pro Y
        int minY = points.get(0).getY();
        int maxY = minY;
        // projet všechny body (list points) a najít minimální a maximální Y (optimalizační krok)

        // pro všechna Y od min do max včetně
        for (int y = minY; y <= maxY; y++) {

            List<Integer> intersections = new ArrayList<>();
            // vnořený cyklus
            // projít všechny hrany (list edges)
            // pokud hrana má průsečík na daném Y..
            // .. tak vypočítat X hodnotu průsečíku a uložit ji do seznamu

            // nyní je naplněný seznam průsečíků

            // setřídit průsečíky
            Collections.sort(intersections);
            // nebo volitelně implementovat vlastní algoritmus na seřazení (pouze v rámci PGRF)
            // v praxi používat hotová řešení, tj. Collections.sort

            // vybarvení mezi průsečíky
            // spojení vždy sudého s lichým (myšleno sudý a lichý index)
            // tj. 0. a 1.; 2. a 3.; ...
        }

        // obtáhnutí hranice
        // renderer.drawPolygon(points, polygonColor);
    }
}
