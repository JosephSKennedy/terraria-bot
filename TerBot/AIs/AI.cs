using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using TerBot;

namespace TerBot.AIs {
    abstract class AI {

        public abstract void closeAI();
        public abstract void openAI();
        public abstract void logic();
        bool isAlive = false;

        public AI() {
            isAlive = true;
            openAI();
        }

        public void stop() {
            isAlive = false;
            closeAI();
        }
        public void KillNPC(short id) {

        }
        public void UpdatePlayer() {
            try {
                Bot.output.Write(new byte[] { 0x14, 0x00, 0x00, 0x00, 0x0D });
                Bot.output.Write(Bot.player.PlayerID);
                Bot.output.Write(Bot.bot.Control);
                Bot.output.Write(Bot.bot.SelectedItem);
                Bot.output.Write(Bot.bot.X);
                Bot.output.Write(Bot.bot.Y);
                Bot.output.Write(Bot.bot.vX);
                Bot.output.Write(Bot.bot.vY);
            } catch (Exception e) { Console.WriteLine(e.Message); }
        }
        public void GotoPosition(Single x, Single y) {
            Bot.bot.X = x;
            Bot.bot.Y = y;
        }
        public void FollowPlayer() {
            if (Bot.bot.X < 10.0f) {
                Bot.bot.X = Bot.player.X;
                Bot.bot.Y = Bot.player.Y;
            }

            if (Bot.bot.X < Bot.player.X - 12) {
                Bot.bot.X += 1f;
                Bot.bot.vX = 3f;
            } else {
                Bot.bot.vX = 0;
                Bot.bot.Control = Player.States.IdleRight;
            }

            if (Bot.bot.X > Bot.player.X + 12) {
                Bot.bot.X -= 1f;
                Bot.bot.vX = -3f;
            } else {
                Bot.bot.vX = 0;
                Bot.bot.Control = Player.States.IdleLeft;
            }

            if (Bot.bot.vX == -3f) {
                Bot.bot.Control = Player.States.FirstLeft;
                UpdatePlayer();
                Bot.bot.Control = Player.States.WalkLeft;
            } else {
                Bot.bot.Control = Player.States.FirstRight;
                UpdatePlayer();
                Bot.bot.Control = Player.States.WalkRight;
            }

            Bot.bot.Y = Bot.player.Y;
            UpdatePlayer();
        }
        public void DestroyTile(int x, int y) {
            try {
                Bot.output.Write(13);
                Bot.output.Write(0x11);
                Bot.output.Write(0x00);
                Bot.output.Write(x);
                Bot.output.Write(y);
                Bot.output.Write(0x00);
                Bot.output.Write(false);
                Bot.output.Write(0x00);
            } catch (Exception e) { Console.WriteLine(e.Message); }
        }
        public void PlaceTile(int x, int y, byte tile) {
            try {
                Bot.output.Write(13);
                Bot.output.Write(0x11);
                Bot.output.Write(0x01);
                Bot.output.Write(x);
                Bot.output.Write(y);
                Bot.output.Write(tile);
                Bot.output.Write(false);
                Bot.output.Write(0x01);
            } catch (Exception e) { Console.WriteLine(e.Message); }
        }
        public void DestroyWall(int x, int y) {
            try {
                Bot.output.Write(13);
                Bot.output.Write(0x11);
                Bot.output.Write(0x02);
                Bot.output.Write(x);
                Bot.output.Write(y);
                Bot.output.Write(0x00);
                Bot.output.Write(false);
                Bot.output.Write(0x02);
            } catch (Exception e) { Console.WriteLine(e.Message); }
        }
        public void PlaceWall(int x, int y, byte tile) {
            try {
                Bot.output.Write(13);
                Bot.output.Write(0x11);
                Bot.output.Write(0x03);
                Bot.output.Write(x);
                Bot.output.Write(y);
                Bot.output.Write(tile);
                Bot.output.Write(false);
                Bot.output.Write(0x03);
            } catch (Exception e) { Console.WriteLine(e.Message); }
        }
        public void WriteChat(String tmp) {
            try {
                Bot.output.Write(tmp.Length + 5);
                Bot.output.Write(0x19);
                Bot.output.Write(Bot.bot.PlayerID);
                Bot.output.Write(Encoding.ASCII.GetBytes(tmp));
            } catch (Exception e) { Console.WriteLine(e.Message); }
        }
    }
}