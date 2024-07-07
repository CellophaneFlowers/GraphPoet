/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph implements Graph<String> {

    private final Set<String> vertices = new HashSet<String>();
    private final Map<String, Vertex> Translation = new HashMap<String, Vertex>();


    //到底里面放String还是Vertex
   // private final HashMap<String, Integer> vertices = new HashMap<String,Integer>();
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO

    // TODO constructor

    // TODO checkRep
    //后面再找这个函数具体对应source还是target
    public boolean checkVertices(String name)
    {
        return vertices.contains(name);
    }
    @Override public boolean add(String vertex) {
        if(checkVertices(vertex))
        {
            return false;
        }

        Vertex d=new Vertex(vertex);
        //到后面再改改
        Translation.put(vertex,d);
        vertices.add(vertex);
        return true;


    }

    @Override public int set(String source, String target, int weight) {

     //不知道要不要保留
        if(!checkVertices(source))
        {
            add(source);

        }if(!checkVertices(target))
        {
           add(target);
        }
        if(weight<0)
        {
            throw new IllegalArgumentException("illegal input") ;
        }
        if(weight==0)
        {
            Vertex sourceVer= Translation.get(source);
            if(sourceVer.target.contains(target))
            {
                sourceVer.target.remove(target);

            }
            Vertex targetVer= Translation.get(target);
            if(targetVer.source.contains(source))
            {
                targetVer.source.remove(source);
            }
            return weight;
        }
        //打通source和target的连接
        Vertex sourceVer= Translation.get(source);

        if(!sourceVer.target.contains(target))
        {
            sourceVer.target.add(target);
        }
        Vertex targetVer= Translation.get(target);
        if(!targetVer.source.contains(source))
        {
            targetVer.source.add(source);
        }

        return weight;
    }
    public void removeAll(String vertex)
    {
        for(Map.Entry<String, Vertex> msg : Translation.entrySet())
        {
            if(msg.getValue().target.contains(vertex))
            {
                msg.getValue().target.remove(vertex);

            }
            if(msg.getValue().source.contains(vertex))
            {
                msg.getValue().source.remove(vertex);

            }
        }

    }
    
    @Override public boolean remove(String vertex) {
        if(!checkVertices(vertex))
        {
            return false;
        }
        removeAll(vertex);
        vertices.remove(vertex);
        return true;
    }
    
    @Override public Set<String> vertices() {
        return vertices;
    }
    public  Set<String> getSource(String target)
    {

        Vertex targetVer= Translation.get(target);

        return targetVer.source;
    }
    public  Set<String> getTarget(String source)
    {

        Vertex sourceVer= Translation.get(source);
        return sourceVer.target;
    }
    @Override public Map<String, Integer> sources(String target) {
        throw new RuntimeException("not implemented");
    }
    
    @Override public Map<String, Integer> targets(String source) {
        throw new RuntimeException("not implemented");
    }
    
    // TODO toString()
    
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
//不管边的权重了

class Vertex {
    String name;
    Set<String> source;
    Set<String> target;

    
    // TODO fields
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    Vertex(String content)
    {
        name=content;
        source=new HashSet<>();
        target=new HashSet<>();
    }
    // TODO constructor
    
    // TODO checkRep
    
    // TODO methods
    
    // TODO toString()
    
}
