import java.io.IOException;

public class Main {
    private Vector initial = new Vector(0,0,0);

    public static void main(String[] args) throws IOException {
        /*
        BufferedImage mandel = new BufferedImage(640, 480,1);
        for (int i = 0; i < 480; i++) {
            for (int j = 0; j < 640; j++) {
                Vector col = trace(i, j).mul(255);
                int red = (int) col.getX();
                int green = (int) col.getY();
                int blue = (int) col.getZ();
                mandel.setRGB(j, i, 0xff000000 | (((0xff & red) << 16)
                        | ((0xff & green) << 8) | (0xff & blue)));
            }
        }

        File output = new File("saved.png");
        ImageIO.write(mandel,"png",output);
        */
        Vector col = trace(256,256).mul(255);
        System.out.println(col);

    }

    private static Vector mapping(Vector p) {
        double power = 8.0;
        double radius = 1.0;
        double tta, phi;
        double dr = 1.0;
        double t0 = 1.0;

        Vector z = p;


        for (int i = 0; i < 7; i++) {
            radius = Math.sqrt(z.getX()*z.getX()+z.getY()*z.getY()+z.getZ()*z.getZ());

            if (radius > 2D) {
                break;
            } else {
                tta = Math.acos(z.getY()/radius)*power;
                phi = Math.acos(z.getZ()/z.getX())*power;
                dr  = Math.pow(radius, power - 1D)*dr*power+1D;
                radius = Math.pow(radius, power);
                z = new Vector(Math.sin(tta)*Math.cos(phi), Math.cos(tta), Math.sin(tta)*Math.sin(phi));

                t0 = t0 < radius ? t0 : radius;
            }
        }

        return new Vector(0.5*Math.log(radius)*radius/dr, t0, 0D);
    }

    private static Vector trace(int x, int y) {
        Vector v1 = new Vector((x*2/640-1)*640/480, y*2/480-1,0);
        Vector v2 = new Vector(0, 2, 2.2);
        Vector v3 = new Vector(0, 1, 0);
        Vector v4 = v2.mul(-1).normlised();
        Vector v5 = (v4.dotProtect(v3)).normlised(); //cs
        Vector v6 = (v5.mul(v1.getX()).add( (((v5.dotProtect(v4)).normlised()).mul(v1.getY())).add(v4.mul(2.8)) ).normlised());

        Vector p;
        double t = 0;
        while (t < 20) {
            p = v2.add(v6.mul(t));
            Vector akuma = mapping(p);
            if(akuma.getX()<0.0001) {
                return new Vector(akuma.getY(),akuma.getY(),akuma.getY());
            }

            t+=akuma.getX();
        }

        return new Vector(1,1,1);

    }



}
