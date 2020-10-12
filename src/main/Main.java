/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package main;

import com.google.gson.Gson;

import model.Direction;
import model.Dirs;
import model.Generic;
import model.Name;
import model.RandomColor;
import processing.core.PApplet;

public class Main extends PApplet implements OnMessageListener {

	// -------------------------------------
	// Global assets
	// -------------------------------------
	private TCPConnection tcp;
	private Gson gson;

	// -------------------------------------
	// Global variables
	// -------------------------------------
	private Avatar avatar;
	private boolean connected;

	// -------------------------------------
	// Main method
	// -------------------------------------
	public static void main(String[] args) {
		PApplet.main("main.Main");
	}

	// -------------------------------------
	// Processing methods
	// -------------------------------------
	public void settings() {
		size(810, 510);
	}

	public void setup() {

		connected = false;
		avatar = new Avatar(405, 255, 255, 255, 255, Dirs.DOWN, "");

		gson = new Gson();

		tcp = TCPConnection.getInstance();
		tcp.setObserver(this);

	}

	public void draw() {

		background(0);

		if (connected) {

			fill(avatar.getR(), avatar.getG(), avatar.getB());
			noStroke();
			ellipse(avatar.getxPos(), avatar.getyPos(), 30, 30);

			fill(255);
			text(avatar.getName(), avatar.getxPos()-15, avatar.getyPos() - 25);

		} else {

			fill(255);
			text("Waiting for client", 100, 100);

		}

	}

	// -------------------------------------
	// TCP
	// -------------------------------------
	@Override
	public void onMessage(String msg) {
		// TODO Auto-generated method stub

		Generic generic = gson.fromJson(msg, Generic.class);

		switch (generic.type) {

		case "Direction":

			Direction direction = gson.fromJson(msg, Direction.class);
			avatar.setDir(direction.getDirection());
			avatar.move(width, height);
			break;

		case "Name":

			Name name = gson.fromJson(msg, Name.class);
			avatar.setName(name.getName());

			break;

		case "RandomColor":

			RandomColor randomColor = gson.fromJson(msg, RandomColor.class);
			avatar.setR(randomColor.getR());
			avatar.setG(randomColor.getG());
			avatar.setB(randomColor.getB());

			break;

		}

		connected = true;

	}

}
