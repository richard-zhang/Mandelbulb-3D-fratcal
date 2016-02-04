/**
 * Created by R on 2/3/16.
 */
public class Vector {
    public double x = 0;
    public double y = 0;
    public double z = 0;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector add(Vector added) {
        return new Vector(this.x+added.x,this.y+added.y,this.z+added.z);
    }

    public Vector mul(double multi) {
        return new Vector(x*multi,y*multi,z*multi);
    }

    public Vector normalised() {
        double disInv = 1/Math.sqrt(x*x+y*y+z*z);
        return this.mul(disInv);
    }

    public double distance() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    public Vector dotProtect(Vector dot) {
        double xt = this.y*dot.z-this.z*dot.y;
        double yt = this.z*dot.x-this.x*dot.z;
        double zt = this.x*dot.y-this.y*dot.x;

        return new Vector(xt,yt,zt);
    }

    public static Vector mapping(Vector p) {
        Vector z = p;
        Vector init = new Vector(0,0,0);
        double power = 8, r = 0, tta, phi, dr = 1.0, t0 = 1.0;
        for (int i = 0; i < 7; i++) {
            r = z.distance();
            if (r > 2) {
                break;
            } else {
                tta = Math.acos(z.y/r)*power;
                phi = Math.atan(z.z/z.x)*power;
                dr = Math.pow(r, power-1)*dr*power+1;
                r = Math.pow(r, power);
                Vector inter = new Vector(Math.sin(tta)*Math.cos(phi), Math.cos(tta), Math.sin(tta)*Math.sin(phi));
                z = (inter.mul(r)).add(p);//possible bug
                t0 = (t0 < r ? t0 : r);
            }
        }

        return new Vector(0.5*Math.log(r)*r/dr, t0, 0.0);
    }

    public static Vector trace(double x, double y) {
        Vector uv = new Vector((x*2.0/640.0-1.0)*640.0/480.0, y*2.0/480.0-1.0, 0.0);
        Vector ro = new Vector(0,2,2.2);
        Vector up = new Vector(0, 1, 0);
        Vector cf = ro.mul(-1.0).normalised();
        Vector cs = (cf.dotProtect(up)).normalised();

        Vector v1 = cs.mul(uv.x);
        Vector v2 = ((cs.dotProtect(cf)).normalised()).mul(uv.y);
        Vector v3 = cf.mul(2.8);
        Vector v4 = v1.add(v2);

        Vector rd = (v4.add(v3)).normalised();

        Vector p;
        double t = 0;

        while(t < 20) {
            p = (rd.mul(t)).add(ro);
            Vector akuma = mapping(p);
            if (akuma.x < 0.0001) {
                return new Vector(akuma.y, akuma.y, akuma.y);
            }
            t += akuma.x;
        }

        return new Vector(1,1,1);


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
