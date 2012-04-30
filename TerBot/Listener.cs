using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TerBot {
    class Listen {
        static byte pID = 0;
        static int size = 0;
        public static void run() {
            try {
                while (true) {
                    size = Bot.input.ReadInt32();
                    pID = Bot.input.ReadByte();
                    if (pID != 70) size--;
                    ParsePacket();
                }
            } catch (Exception e) {
                Console.WriteLine("PID: " + pID + " Size: " + size);
                Console.WriteLine(e.Message);
                Console.ReadKey();
                Environment.Exit(1);
            }
        }

        public static void ParsePacket() {
            byte[] tmp;
            switch (pID) {
                case 0x01:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x02:	//Disconnect
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                Console.WriteLine("Disconnected from server\nReason: " + Encoding.ASCII.GetString(tmp));
                Console.ReadKey();
                Environment.Exit(1);
                break;
                case 0x03:
                Bot.bot.PlayerID = Bot.input.ReadByte();
                break;
                case 0x04: //Player Info
                byte tmpPID = Bot.input.ReadByte();
                tmp = new byte[24];
                Bot.input.Read(tmp, 0, 24);
                byte[] tmpN = new byte[size - 25];
                Bot.input.Read(tmpN, 0, size - 25);
                String tmpName = Encoding.ASCII.GetString(tmpN);
                if (tmpName.Equals(Bot.player.Name)) Bot.player.PlayerID = tmpPID;
                break;
                case 0x05:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x06:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x07:	//WorldInfo
                Storage.Time = Bot.input.ReadInt32();
                Storage.DayTime = Bot.input.ReadBoolean();
                Storage.MoonPhase = Bot.input.ReadByte();
                Storage.BloodMoon = Bot.input.ReadBoolean();
                Storage.MaxTilesX = Bot.input.ReadInt32();
                Storage.MaxTilesY = Bot.input.ReadInt32();
                Storage.SpawnX = Bot.input.ReadInt32();
                Storage.SpawnY = Bot.input.ReadInt32();

                Storage.WorldSurface = Bot.input.ReadInt32();
                Storage.RockLayer = Bot.input.ReadInt32();
                Storage.WorldID = Bot.input.ReadInt32();
                Storage.BossFlag = Bot.input.ReadByte();
                tmp = new byte[size - 36];
                Bot.input.Read(tmp, 0, size - 36);
                break;
                case 0x08:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x09:	//Status
                Bot.input.ReadInt32();
                tmp = new byte[size - 4];
                Bot.input.Read(tmp, 0, size - 4);
                Console.WriteLine("Status: " + Encoding.ASCII.GetString(tmp));
                break;
                case 0x0A:	//Tile Send Section
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x0B:	//Tile Frame Section
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x0C:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x0D: //Player update
                byte tmpP = Bot.input.ReadByte();
                if (tmpP == Bot.player.PlayerID) {
                    Bot.player.Control = Bot.input.ReadByte();
                    Console.WriteLine(Bot.player.Control);
                    Bot.player.SelectedItem = Bot.input.ReadByte();
                    Bot.player.X = Bot.input.ReadSingle();
                    Bot.player.Y = Bot.input.ReadSingle();
                    Bot.player.vX = Bot.input.ReadSingle();
                    Bot.player.vY = Bot.input.ReadSingle();
                } else {
                    tmp = new byte[size - 1];
                    Bot.input.Read(tmp, 0, size - 1);
                }
                break;
                case 0x0E:	//Player Active
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x10:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x11:	//Modify Tile
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x12:	//Time Set
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x13:	//Door Use
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x14:	//Tile Send Square
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x15:	//Item Update
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x16:	//Item Owner
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x17:	//Npc Update
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x18:	//Npc Item Strike
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x19:	//Chat Text
                tmp = new byte[size - 4];
                Console.Write(Bot.input.ReadByte() + ": ");
                Bot.input.ReadByte(); Bot.input.ReadByte(); Bot.input.ReadByte();
                Bot.input.Read(tmp, 0, size - 4);
                Console.WriteLine(Encoding.ASCII.GetString(tmp));
                break;
                case 0x1A:	//Player Damage
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x1B:	//Projectile New
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x1C:	//Npc Strike
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x1D:	//Projectile Destroy
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x20:	//Chest Item
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x21:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x22:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x23:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x24:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x25:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x26:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x27:	//Item Unknown
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x28:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x29:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x2A:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x2B:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x2C:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x2D:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x2E:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x2F:	//Sign Update
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x30:	//Liquid Set
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x31: //Player First Spawn
                Bot.Active = true;
                break;
                case 0x32:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x33:	//Npc Special
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x34:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x35:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x36:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x37:	//Npc Update Buff
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x38:	//Update Npc Name
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x39:	//Update Good/Evil Percentage
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x3A:	//Play Harp
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x3B:	//Hit Switch
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                case 0x3C:	//Npc Home Info Update
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
                default:
                tmp = new byte[size];
                Bot.input.Read(tmp, 0, size);
                break;
            }
            System.GC.Collect();
        }

    }
}
