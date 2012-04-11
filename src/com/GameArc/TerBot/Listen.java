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
				//System.out.println("Packet ID: " + pID);
				ParsePacket();
			}
		}catch(Exception e){
			System.out.println("Packet ID: " + pID + "\nSize: " + (size-1));
			e.printStackTrace();
		}
	}
	
	public void ParsePacket() throws Exception {
		byte[] tmp;
		switch(pID) {
			case 0x01:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x02:	//Disconnect
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				System.out.println("Disconnected from server\nReason: " + new String(tmp).trim());
				System.exit(1);
				break;
			case 0x03:
				Bot.bot.PlayerID = Bot.in.readByte();
				break;
			case 0x04: //Player Info
				byte tmpPID = Bot.in.readByte();
				tmp = new byte[24];
				Bot.in.readFully(tmp, 0, 24);
				byte[] tmpN = new byte[size-25];
				Bot.in.readFully(tmpN, 0, size-25);
				String tmpName = new String(tmpN);
				if(tmpName.equals(Bot.player.Name))Bot.player.PlayerID = tmpPID;
				break;
			case 0x05:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x06:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
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
				Bot.in.readFully(tmp, 0, size-36);
				break;
			case 0x08:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x09:	//Status
				Bot.in.readInt();
				tmp = new byte[size-4];
				Bot.in.readFully(tmp, 0, size-4);
				System.out.println("Status: " + new String(tmp).trim());
				break;
			case 0x0A:	//Tile Send Section
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x0B:	//Tile Frame Section
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x0C:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x0D: //Player update
				byte tmpP = Bot.in.readByte();
				if(tmpP == Bot.player.PlayerID){
					Bot.player.Control = Bot.in.readByte();
					System.out.println(Bot.player.Control);
					Bot.player.SelectedItem = Bot.in.readByte();
					Bot.player.X = Bot.in.readFloat();
					Bot.player.Y = Bot.in.readFloat();
					Bot.player.vX = Bot.in.readFloat();
					Bot.player.vY = Bot.in.readFloat();
				}else {
					tmp = new byte[size-1];
					Bot.in.readFully(tmp, 0, size-1);
				}
				break;
			case 0x0E:	//Player Active
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x10:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x11:	//Modify Tile
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x12:	//Time Set
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x13:	//Door Use
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x14:	//Tile Send Square
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x15:	//Item Update
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x16:	//Item Owner
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x17:	//Npc Update
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x18:	//Npc Item Strike
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x19:	//Chat Text
				tmp = new byte[size-4];
				System.out.print(Bot.in.readByte() + ": ");
				Bot.in.skip(3);
				Bot.in.readFully(tmp, 0, size-4);
				System.out.println(new String(tmp));
				break;
			case 0x1A:	//Player Damage
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x1B:	//Projectile New
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x1C:	//Npc Strike
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x1D:	//Projectile Destroy
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x20:	//Chest Item
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x21:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x22:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x23:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x24:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x25:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x26:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x27:	//Item Unknown
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x28:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x29:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x2A:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x2B:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x2C:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x2D:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x2E:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x2F:	//Sign Update
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x30:	//Liquid Set
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x31: //Player First Spawn
				Bot.Active = true;
				break;
			case 0x32:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x33:	//Npc Special
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x34:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x35:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x36:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x37:	//Npc Update Buff
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x38:	//Update Npc Name
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x39:	//Update Good/Evil Percentage
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x3A:	//Play Harp
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x3B:	//Hit Switch
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			case 0x3C:	//Npc Home Info Update
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
			default:
				tmp = new byte[size];
				Bot.in.readFully(tmp, 0, size);
				break;
		}
		Runtime.getRuntime().gc();
	}
	
}
