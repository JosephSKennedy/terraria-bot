using System;
using System.Reflection;
using System.Collections.Generic;
using System.Linq;
using System.IO;
using System.Text;
using TerBot.AIs;
using TerBot.Plugin;

namespace TerBot {
    class AILoader {
        public static int CheckPlugins() {
            if (!Directory.Exists("plugins")) {
                Directory.CreateDirectory("plugins");
                return 0;
            } else {
                string[] files = Directory.GetFiles("plugins", "*.dll");
                if (files.Length == 0) return 0;
                int plugins = 0;
                Storage.Plugins = new List<IPlugin>();
                Assembly tmp;
                foreach (string s in files) {
                    if (s.Contains("PluginLib.dll")) continue;
                    tmp = Assembly.LoadFrom(s);
                    foreach (Type t in tmp.GetTypes()) {
                        if (t.ToString().Contains("Plugin")) {
                            Storage.Plugins.Add(Activator.CreateInstance(t) as IPlugin);
                            break;
                        }
                    }
                }
                Console.ReadKey();
                return plugins;
            }
        }
    }
}
