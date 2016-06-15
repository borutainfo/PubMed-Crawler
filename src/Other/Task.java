/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

public class Task {
    public int id; 
    public int count = 0;  
    public int done = 0;
    public boolean assigned = false;
    
    public Task(int id) {
        this.id = id;
    }
}
