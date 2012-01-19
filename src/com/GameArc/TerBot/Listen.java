package com.GameArc.TerBot;


public class Listen implements Runnable{
	byte buff[];
	int size;
	public void run() {
		try{
			while(true){
				size = Bot.in.read()+Bot.in.read()+Bot.in.read()+Bot.in.read();
				System.out.print(size + ":");
					buff = new byte[size];
					Bot.in.read(buff);
					System.out.println(buff[0]);
					for(byte b : buff)
						System.out.print(b + ", ");
					System.out.println();
					ParsePacket();
					//for(byte b : buff) System.out.print(b);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void ParsePacket() {
		int i = 0;
		switch(buff[i++]){
			case 0x00:
			break;
			case 0x02:	//Disconnect
				System.out.println("Disconnected from server\nReason: " + new String(buff));
				System.exit(1);
				break;
			case 0x07:	//WorldInfo
				Storage.Time =		new byte[]{buff[i++], buff[i++], buff[i++], buff[i++]};
				Storage.DayTime =	buff[i++];
				Storage.MoonPhase = buff[i++];
				Storage.BloodMoon = buff[i++];
				Storage.MaxTilesX = new byte[]{buff[i++], buff[i++], buff[i++], buff[i++]};
				Storage.MaxTilesY = new byte[]{buff[i++], buff[i++], buff[i++], buff[i++]};
				Storage.SpawnX =	new byte[]{buff[i++], buff[i++], buff[i++], buff[i++]};
				Storage.SpawnY =	new byte[]{buff[i++], buff[i++], buff[i++], buff[i++]};
			 Storage.WorldSurface = new byte[]{buff[i++], buff[i++], buff[i++], buff[i++]};
				Storage.RockLayer = new byte[]{buff[i++], buff[i++], buff[i++], buff[i++]};
				Storage.WorldID =	new byte[]{buff[i++], buff[i++], buff[i++], buff[i++]};
				Storage.BossFlag =	buff[i++];
				byte[] tmp7 = new byte[size-36];
				for(int x = 0 ; x < size-37 ; x++)tmp7[x] = buff[i++];
				System.out.println("Name of server: " + new String(tmp7));
				break;
			case 0x09:	//Status
				i = 5;
				byte[] tmp9 = new byte[size-4];
				for(int x = 0 ; x < size-5 ; x++)tmp9[x] = buff[i++];
				System.out.println("Status: " + new String(tmp9));
				break;
			case 0x0A:	//Tile Send Section
				break;
			case 0x0B:	//Tile Frame Section
				break;
			case 0x0E:	//Player Active
				break;
			case 0x11:	//Modify Tile
				break;
			case 0x12:	//Time Set
				break;
			case 0x13:	//Door Use
				break;
			case 0x14:	//Tile Send Square
				break;
			case 0x15:	//Item Update
				break;
			case 0x16:	//Item Owner
				break;
			case 0x17:	//Npc Update
				break;
			case 0x18:	//Npc Item Strike
				break;
			case 0x19:	//Chat Text
				break;
			case 0x1A:	//Player Damage
				break;
			case 0x1B:	//Projectile New
				break;
			case 0x1C:	//Npc Strike
				break;
			case 0x1D:	//Projectile Destroy
				break;
			case 0x20:	//Chest Item
				break;
			case 0x27:	//Item Unknown
				break;
			case 0x2F:	//Sign Update
				break;
			case 0x30:	//Liquid Set
				break;
			case 0x33:	//Npc Special
				break;
			case 0x36:	//Npc Update Buff
				break;
			case 0x38:	//Update Npc Name
				break;
			case 0x39:	//Update Good/Evil Percentage
				break;
			case 0x3A:	//Play Harp
				break;
			case 0x3B:	//Hit Switch
				break;
			case 0x3C:	//Npc Home Info Update
				break;
		}
		
	}
}
