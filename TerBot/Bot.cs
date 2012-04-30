using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net.Sockets;
using System.Net;
using System.Threading;
using System.IO;
using TerBot.AIs;

namespace TerBot {
    class Bot {
        static String ServerIP = "127.0.0.1";

        static TcpClient c;
        public static BinaryReader input;
        public static BinaryWriter output;
        public static Player bot, player;
        public static AI BotAI;
        public static bool Active = false;

        public static void Main(String[] args) {
            Console.Title = "Terraria Bot by LSn145";
            Console.BackgroundColor = ConsoleColor.Black;
            Console.ForegroundColor = ConsoleColor.Green;
            AILoader.CheckPlugins();
            Intro();
            new Thread(setInit).Start();
            BotAI = new Follower();
            while (!Active) Thread.Sleep(250);
            player = new Player();
            if (args.Length == 2) {
                ServerIP = args[0];
                player.Name = args[1];
            } else {
                player.Name = "GamerEngage";
            }

            output.Write(new byte[] { 0x01, 0x00, 0x00, 0x00, 0x0F });
            while (true) {
                BotAI.logic();
                Thread.Sleep(10);
            }
        }

        public static void Intro() {
            bool tmp = true;
            int i = 200;
            Console.SetCursorPosition(0, 2);
            Console.WriteLine("         MMM~   =MMM                ?MM               MMMMMMDI");
            Console.WriteLine("         MMMM   MMMM                ?MM               MMMMMMMMM");
            Console.WriteLine("         MM7M   MIMM   +MMN+    :NM=+MM   =NMI        MM?    MM~,MM    MM");
            Console.WriteLine("         MM M7 DM MM  MM~ ZMM  MMN ~MMM  MM~ MM:      MMMMMMMMZ  NM+  DM+");
            Console.WriteLine("         MM MN MM MM     ~NMM ,MM   ?MM 8M+  ,MM      MMMMMMMMM, ,MM  MM");
            Console.WriteLine("         MM ?MDM~ MM  MMMI:MM ,MM   =MM MMMMMMMM      MM?    +MM  $M=NM~");
            Console.WriteLine("         MM  MMM  MM :MM,:NMM  MM8 +MMM  MM~ OI,      MMMMMMMMM7   MMMO");
            Console.WriteLine("         MM  DMI  MM   DMZ MM:  ,NM+ MM   =NMD,       MMMMMMMO     :MM");
            Console.WriteLine("                                                                , MMI");
            Console.WriteLine("   Type 'tutorial' in the console for the tutorial               ?MD");
            Console.SetCursorPosition(0, 15);
            Console.WriteLine("  MM        NMMMMMM,               MM:      MMM   =MMMMM$           ~MMMMMMMD");
            Console.WriteLine("  MM       +MM   :MM            ,MMMM:     MMMM   NM,          :MM  ~MM    MMM");
            Console.WriteLine("  MM       ,MMN:      MMDMMMM?  MD MM:    MM:MM   MMMMM8            ~MM     MM");
            Console.WriteLine("  MM         MMMMMN   MM8  =MM     MM:   MM :MM  ,MM,,NMM           ~MM     MM");
            Console.WriteLine("  MM              MM, MM+  :MM     MM:  MMMMMMMM:      MM           ~MM    :MM");
            Console.WriteLine("  MM       ZMM=  $MM  MM+  :MM     MM:      :MM  ,MM= MMD      :MM  ~MM   ?MM?");
            Console.WriteLine("  MMMMMMMM  :MMMMMM   MM+  :MM     MM:      :MM    MMMM?            ~MMMMMMM ");
            while (tmp) {
                Thread.Sleep(25);
                if (i-- <= 0) tmp = false;
                if (Console.KeyAvailable) tmp = false;
            }
            Console.Clear();
        }

        public static void setInit() {
            try {
                c = new TcpClient(ServerIP, 7777);
                input = new BinaryReader(c.GetStream());
                output = new BinaryWriter(c.GetStream());
                bot = new Player();
                player = new Player();
                output.Write(new byte[]{
				0x0B, 0x00, 0x00, 0x00, 0x01
			});
                output.Write(Encoding.ASCII.GetBytes("Terraria39"));

                int i = input.ReadInt32();
                byte[] buf = new byte[i];
                input.Read(buf, 0, i);
                new Thread(Listen.run).Start();
                bot.PlayerID = buf[1];
                Storage.CharacterData = new byte[] { 0x1D, 0x00, 0x00, 0x00, 0x04, Bot.bot.PlayerID, 0x00, 0x01, (byte)0xD7, 0x5A, 0x4B, (byte)0xFF, (byte)0x80, 0x37, 0x69, 0x5A, 0x5A, (byte)0xAF, (byte)0xA5, (byte)0x8C, (byte)0xA0, (byte)0xB4, (byte)0xD7, (byte)0xFF, (byte)0xE6, (byte)0xAF, (byte)0xA0, 0x69, 0x3C, 0x00, 0x42, 0x6f, 0x74 };
                Storage.Health = new byte[] { 0x04, 0x00, 0x00, 0x00, 0x10, Bot.bot.PlayerID, 0x64, 0x64 };
                Storage.Mana = new byte[] { 0x04, 0x00, 0x00, 0x00, 0x2A, Bot.bot.PlayerID, 0x32, 0x32 };
                Storage.Buff = new byte[] { 0x0D, 0x00, 0x00, 0x00, 0x32, Bot.bot.PlayerID, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

                output.Write(Storage.CharacterData);
                output.Write(Storage.Health);
                output.Write(Storage.Mana);
                output.Write(Storage.Buff);
                output.Write(new byte[]{
					0x01, 0x00, 0x00, 0x00, 0x06
			});
                output.Write(new byte[]{
					0x09, 0x00, 0x00, 0x00, 0x08, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF
			});
                output.Write(new byte[]{
					0x0A, 0x00, 0x00, 0x00, 0x0C, Bot.bot.PlayerID,(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF
			});
            } catch (Exception e) {
                Console.WriteLine(e.Message);
                Console.ReadKey();
                Environment.Exit(1);
            }
        }
    }
}
