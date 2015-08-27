/**
 * Created by Ben on 8/25/15.
 *
 * A test game utilizing the Bengine
 */

import com.bengine.core.Game;
import com.bengine.core.Time;

public class BengineGame extends Game
{
    public static void main(String[] args)
    {
        System.out.println("Test Bengine Game!");

        new BengineGame().run();
    }

    @Override
    protected void update()
    {
        Time.newFrame();
        double delta = Time.getDelta();
        System.out.printf("%2.2f\n", delta * 1000);
    }
}
