/**
 * Created by Ben on 8/25/15.
 *
 * A test game utilizing the Bengine
 */

import com.bengine.core.Game;

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

    }
}
