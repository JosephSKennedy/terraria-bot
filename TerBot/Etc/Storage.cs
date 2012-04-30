using System;
using System.Reflection;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using TerBot.AIs;
using TerBot.Plugin;

namespace TerBot {
    class Storage {
        public static int Time = 0;
        public static bool DayTime;
        public static byte MoonPhase;
        public static bool BloodMoon;
        public static int MaxTilesX = 0;
        public static int MaxTilesY = 0;
        public static int SpawnX = 0;
        public static int SpawnY = 0;
        public static int WorldSurface = 0;
        public static int RockLayer = 0;
        public static int WorldID = 0;
        public static byte BossFlag;

        public static byte[] CharacterData;
        public static byte[] Health;
        public static byte[] Mana;
        public static byte[] Buff;

        public static List<IPlugin> Plugins;
    }
}
