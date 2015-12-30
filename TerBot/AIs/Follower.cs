using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

//This AI is simply follows the player around
//FollowPlayer() is defined is AI.cs (Perhaps is should be moved into here? Unlikely)

namespace TerBot.AIs {
    class Follower : AI {

        override
        public void openAI() {
            WriteChat("I'm baaaack!");
        }
        override
        public void closeAI() {
            WriteChat("I'm gooooone D:");
        }
        override
        public void logic() {
            FollowPlayer();
        }

    }
}
