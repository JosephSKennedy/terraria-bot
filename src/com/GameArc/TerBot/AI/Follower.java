package com.GameArc.TerBot.AI;


public class Follower extends AI {

	public void openAI(){
		WriteChat("I'm baaaack :D");
	}
	
	public void closeAI() {
		WriteChat("I'm gooooone D:");
	}
	
	public void logic() {
		while(isAlive){
			FollowPlayer();
		}
	}

}
