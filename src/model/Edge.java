package model;

public class Edge {

    private int x1, y1, x2, y2;

    /**
     * @param p1 první hraniční bod úsečky
     * @param p2 druhý hraniční bod úsečky
     */
    public Edge(Point p1, Point p2) {
        this.x1 = p1.getX();
        this.y1 = p1.getY();
        this.x2 = p2.getX();
        this.y2 = p2.getY();
    }

    /**
     * Zjistí, zda je úsečka vodorovná
     *
     * @return true pokud je vodorovná, jinak false
     */
    public boolean isHorizontal() {
        // TODO test na rovnost y1 a y2
        return false;
    }

    /**
     * Zorientuje úsečku odshora dolů
     */
    public void orientate() {
        // TODO prohození hodnot, když y1 je větší než y2
    }

    /**
     * Zjistí, zda existuje průsečík s scan-line s touto úsečkou
     *
     * @param y y-ová souřadnice vodorovné přímky (scan-line)
     * @return true, pokud existuje průsečík
     */
    public boolean hasIntersection(int y) {
        // TODO y, y1, y2 - porovnat, zda je y v rozsahu
        return false;
    }

    /**
     * Vrátí x-ovou souřadnici průsečíku s scan-line a této úsečky
     *
     * @param y y-ová souřadnice vodorovné přímky (scan-line)
     * @return vrátí x-ovou souřadnici průsečíku
     */
    public int getIntersection(int y) {
        // TODO vypočítat průsečík pomocí y, k, q (osa Y)
        return 0;
    }

    /**
     * Zjišťuje, na které straně přímky tvořené touto úsečkou se nachází bod z parametru
     *
     * @param p bod pro určení
     * @return true když je na "správné" straně - záleží na orientaci
     */
    public boolean isInside(Point p) {
        // tečný vektor
        Point t = new Point(x2 - x1, y2 - y1);

        // normálový vektor
//        Point n = new Point(t.getY(), -t.getX());
        Point n = new Point(-t.getY(), t.getX());

        // vektor k bodu
        Point v = new Point(p.getX() - x1, p.getY() - y1);

        return (v.getX() * n.getX() + v.getY() * n.getY() < 0);
    }

    /**
     * Výpočet průsečíku dvou úseček.
     * První úsečka je daná instancí této třídy.
     * Druhá úsečka je daná dvěma body z parametrů funkce.
     *
     * @param p1 první krajní bod druhé úsečky
     * @param p2 druhý krajní bod druhé úsečky
     * @return průsečík
     */
    public Point getIntersection(Point p1, Point p2) {
        // TODO
        return new Point();
    }

}
