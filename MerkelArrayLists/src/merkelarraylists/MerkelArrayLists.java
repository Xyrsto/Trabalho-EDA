/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package merkelarraylists;

/**
 *
 * @author rodri
 */
public class MerkelArrayLists {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Merkle tree = new Merkle();
        
        tree.add("A");
        tree.add("B");
        tree.add("C");
        tree.add("B");
        tree.add("D");
        tree.add("E");
        tree.add("F");
        tree.add("G");
        tree.add("Z");
        
        tree.show();  
       
    }
    
}
