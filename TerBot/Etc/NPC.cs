namespace TerBot {
    class NPC {
        short ID = 0;
        float X = 0, Y = 0;
        float vX = 0, vY = 0;
        short Target = 0;
        byte Direction = 0;
        byte DirectionY = 0;
        short HP = 0;
        int[] AI = new int[4];
        short Type = 0;
    }
}