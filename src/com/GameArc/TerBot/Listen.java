package com.GameArc.TerBot;


public class Listen implements Runnable{
	byte pID = 0;
	int size = 0;
	public void run() {
		try{
			while(true){
				//Thread.sleep(10000);
				size = Bot.in.readInt();
				pID = Bot.in.readByte();
				if(pID != 70)
				size--;
				System.out.println("Packet ID: " + pID + "\nSize: " + (size));
				ParsePacket();
			}
		}catch(Exception e){
			//System.out.println("Packet ID: " + pID + "\nSize: " + (size-1));
			e.printStackTrace();
		}
	}
	
	public void ParsePacket() throws Exception {
		byte[] tmp;
		switch(pID){
			default:
				System.out.println(Bot.in.readString());
			break;
			case 0x02:	//Disconnect
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				System.out.println("Disconnected from server\nReason: " + new String(tmp).trim());
				System.exit(1);
				break;
			case 0x07:	//WorldInfo
				Storage.Time = Bot.in.readInt();
				Storage.DayTime = Bot.in.readBoolean();
				Storage.MoonPhase = Bot.in.readByte();
				Storage.BloodMoon = Bot.in.readBoolean();
				Storage.MaxTilesX = Bot.in.readInt();
				Storage.MaxTilesY = Bot.in.readInt();
				Storage.SpawnX = Bot.in.readInt();
				Storage.SpawnY = Bot.in.readInt();
				
				Storage.WorldSurface = Bot.in.readInt();
				Storage.RockLayer = Bot.in.readInt();
				Storage.WorldID =	Bot.in.readInt();
				Storage.BossFlag =	Bot.in.readByte();
				tmp = new byte[size-36];
				Bot.in.read(tmp, 0, size-36);
				Storage.ServerName = new String(tmp).trim();
				System.out.println("Server Name: " + Storage.ServerName);
				break;
			case 0x09:	//Status
				Bot.in.readInt();
				tmp = new byte[size-4];
				Bot.in.read(tmp, 0, size-4);
				System.out.println("Status: " + new String(tmp).trim());
				break;
			case 0x0A:	//Tile Send Section
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x0B:	//Tile Frame Section
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x0E:	//Player Active
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x11:	//Modify Tile
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x12:	//Time Set
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x13:	//Door Use
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x14:	//Tile Send Square
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x15:	//Item Update
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x16:	//Item Owner
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x17:	//Npc Update
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x18:	//Npc Item Strike
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x19:	//Chat Text
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x1A:	//Player Damage
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x1B:	//Projectile New
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x1C:	//Npc Strike
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x1D:	//Projectile Destroy
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x20:	//Chest Item
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x27:	//Item Unknown
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x2F:	//Sign Update
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x30:	//Liquid Set
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x31:
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x33:	//Npc Special
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x36:	//Npc Update Buff
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x38:	//Update Npc Name
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x39:	//Update Good/Evil Percentage
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x3A:	//Play Harp
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x3B:	//Hit Switch
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
			case 0x3C:	//Npc Home Info Update
				tmp = new byte[size];
				Bot.in.read(tmp, 0, size);
				break;
		}
		
	}
	
}
