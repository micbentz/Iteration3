/**
 * Created by ben on 4/11/16.
 */
import models.effects.HemiSphericalEffect;
import models.effects.PrismEffect;
import models.effects.RadialEffect;
import utilities.Point3D;
public class RunGame {
    public static void main(String args[]){
        System.out.println("Hello World");
        Point3D point = new Point3D(0,0,3);
        new RadialEffect(point, 2);
        new PrismEffect(point,2);
        new HemiSphericalEffect(point,1);
    }
}
