/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package main;

import model.Dirs;

public class Avatar {
	
	// -------------------------------------
    // Atributtes
    // -------------------------------------
	private int xPos;
	private int yPos;
	private int r;
	private int g;
	private int b;
	private Dirs dir;
	private String name;
	
	// -------------------------------------
    // Constructor
    // -------------------------------------
	public Avatar() {
		
	}
	
	public Avatar(int xPos, int yPos, int r, int g, int b, Dirs dir, String name) {
		
		this.xPos = xPos;
		this.yPos = yPos;
		this.r = r;
		this.g = g;
		this.b = b;
		this.dir=dir;
		this.setName(name);
		
	}
	
	// -------------------------------------
    // Getters and setters
    // -------------------------------------
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public int getG() {
		return g;
	}
	public void setG(int g) {
		this.g = g;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}

	public Dirs getDir() {
		return dir;
	}

	public void setDir(Dirs dir) {
		this.dir = dir;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
