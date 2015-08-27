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
        float delta = Time.getDelta();
        System.out.printf("%1.7f\n", delta);
        for(int i = 0; i < 1000; i++)
        {
            System.out.print("");
        }
    }
}
