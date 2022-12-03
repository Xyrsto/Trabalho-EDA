/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package merkelarraylists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
/**
 *
 * @author rodri
 */
public class Merkle 
{
    ArrayList<Object> inputs;
    ArrayList<Object> hashCodes;
    ArrayList<Object> treeGeneratingHeapPrincipal;
    int globalIndex;
    
    public Merkle()
    {
        this.inputs = new ArrayList<>();
        this.hashCodes = new ArrayList<>();
        this.treeGeneratingHeapPrincipal = new ArrayList<>();
        globalIndex = 0;
    }
    
    //adiciona nas Arraylists inputs e hashCodes o objeto do parametro e o seu hashCode respetivamente
    public void add(Object o)
    {
        inputs.add(o);
        createHash(o.hashCode());
        globalIndex++;
        buildTree();
    }
    
    //adiciona o hashCode do objeto recebido por parametro á Arraylist hashCodes
    public void createHash(Object o)
    {
        hashCodes.add(o);
    }
    
    //remove nas Arraylists inputs e hashCodes o objeto no index recebido como parametro e o seu hashCode respetivamente
    public void remove(int index)
    {
        inputs.remove(index);
        removeHash(index);
        globalIndex--;
        buildTree();
    }
    
    //remove o hashCode na arraylist hashCodes na posicao recebida como parametro
    public void removeHash(int index)
    {
        hashCodes.remove(index);
    }
    
    //permite alterar os valores no index recebido por um objeto recebido
    public void changeValue(int index, Object newValue)
    {
        inputs.remove(index);
        inputs.add(index,newValue);
        changeHash(index, newValue.hashCode());
        buildTree();
    }
    
    //altera o valor no index recebido pelo hashCode do objeto recebido na funcao anterior
    public void changeHash(int index, Object newHash)
    {
        hashCodes.remove(index);
        hashCodes.add(index,newHash);
    }
    
    //constroi um arvore usando a arrayList treeGeneratingHeapPrincipal 
    public void buildTree()
    {
        ArrayList<Object> treeGeneratingHeapAux = new ArrayList<>();
        treeGeneratingHeapPrincipal.clear();
        for (Object hash : hashCodes) 
        {
            treeGeneratingHeapPrincipal.add(hash);
        }
        
        //chama a funcao buildTree recursiva
        buildTree(treeGeneratingHeapPrincipal);
    }
    
    //build tree recursiva
    public Object buildTree(ArrayList<Object> hashList)
    {
        //condicao de paragem
        if(hashList.size() == 1)
            return hashList;
                      
        ArrayList<Object> newHashList = new ArrayList<>();
        
        int i;
        
        //se o tamanho da lista passada como parametro for impar, adiciona o ultimo elemento da lista no final da lista
        if(hashList.size() % 2 != 0)
        {
            Object addedHash = hashList.get(hashList.size() - 1);
            hashList.add(addedHash);
        }
        
        //cria a concatenacao dos hashCodes e adiciona à lista newHashList
        for(i = 0; i<hashList.size(); i = i+2) 
        {
            Object concatenatedHash = Objects.hash(hashList.get(i),hashList.get(i + 1));
            newHashList.add(concatenatedHash);
        }
        
        //para cada objeto na newHashList, adiciona à treeGeneratingHeapPrincipal
        for (Object object : newHashList) 
        {
           treeGeneratingHeapPrincipal.add(object); 
        }
        
        return buildTree(newHashList);          
    }
    
    //diverte te bro, o metodo show ta fodido ¯\_(ツ)_/¯
    public void show()
    {
        ArrayList<Object> treeHeapAux = treeGeneratingHeapPrincipal;
        
        //isto está aqui para remover o objeto repetido adicionado anteriormente substitui lo por " " para nao dar problemas no while 
        if(globalIndex % 2 != 0)
        {
            treeHeapAux.remove(globalIndex);
            treeHeapAux.add(globalIndex," ");
        }
        
        //inverte a lista para dar print 
        Collections.reverse(treeHeapAux);
        
        /*for (Object object : treeHeapAux) 
        {
            System.out.print(object.toString() + " ");
        }*/
        
        int count = 0;
        
        //dá problemas aqui no while 
        while(!treeHeapAux.isEmpty())
        {
            for(int j = globalIndex - count - 1; j > 0; j--)
            {
                System.out.print("  ");
            }
            
            for(int i = (int)Math.pow(2,count) - 1; i >= 0; i--)
            {
                System.out.print(treeHeapAux.get(i) + "  ");
                treeHeapAux.remove(i);
            }
            System.out.println("");
            count++;
        }
        
        //daqui para baixo nao me parece dar problemas
        for (Object input : inputs) 
        {  
            System.out.print("  | ");
        }
        System.out.print("\n");
        System.out.print("  ");
        for (Object input : inputs) 
        {  
            System.out.print(input.toString() + "   ");
        }
    }
}
