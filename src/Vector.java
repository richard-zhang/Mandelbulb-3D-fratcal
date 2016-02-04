/**
 * Created by R on 2/3/16.
 */
public class Vector {
    private double x = 0;
    private double y = 0;
    private double z = 0;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vector add(Vector added) {
        return new Vector(x+added.getX(),y+added.getY(),z+added.getZ());
    }

    public Vector mul(double multi) {
        return new Vector(x*multi,y*multi,z*multi);
    }

    public Vector normlised() {
        double disInv = 1/Math.sqrt(x*x+y*y+z*z);
        return this.mul(disInv);
    }

    public Vector dotProtect(Vector dot) {
        double xt = this.y*dot.getZ()-this.z*dot.getY();
        double yt = this.z*dot.getX()-this.x*dot.getZ();
        double zt = this.x*dot.getY()-this.y*dot.getX();

        return new Vector(xt,yt,zt);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
