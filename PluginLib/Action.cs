using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TerBot.Plugin {
    public class Action {
        public static byte[] Package = new byte[255];
        public static void FollowPlayer() { Package[0] = 0x01; }
        public static void AttackNearest() { Package[1] = 0x01; }

    }
}
