using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using TerBot.Plugin;

namespace FighterAI {
    public class Plugin : IPlugin {
        public string GetPluginName() {
            return "FighterAI";
        }

        public byte[] getLogic() {
                return TerBot.Plugin.Action.Package;
        }


        public byte[] Logic {
            get {
                throw new NotImplementedException();
            }
            set {
                throw new NotImplementedException();
            }
        }

        public void SetVar(string var) {
            throw new NotImplementedException();
        }
    }
}
