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
public class ConcreteEdgesGraph implements Graph<String> {
    
    private final Set<String> vertices = new HashSet<>();
    //这个list有啥用
    private final List<Edge> edges = new ArrayList<>();
    //让这个concreteverticesgraph执行concretevertices所有操作
    private final ConcreteVerticesGraph medium=new ConcreteVerticesGraph();
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    
    // TODO checkRep
    //同时声明concreteedgesgraph和concreteverticesgraph两个对象来分别执行两个add
    public ConcreteVerticesGraph getMedium()
    {
        return medium;
    }
    public void layout()
    {

        medium.getTranslation();

    }    public boolean checkVertices(String name)
    {
        return vertices.contains(name);
    }
    public int checkContains(Edge name)
    {
        for(Edge edge:edges)
        {
            if(edge.source==name.source&&edge.target==name.target)
            {
                return edge.weight;

            }
        }
        return 0;
    }
    public boolean checkEdges(Edge name)
    {
        return edges.contains(name);
    }

    @Override public boolean add(String vertex) {
        if(checkVertices(vertex))
        {
            return false;
        }
        //Vertex d=new Vertex(vertex);
        //到后面再改改
        vertices.add(vertex);
        return true;
    }

    
    @Override public int set(String source, String target, int weight) {

        Edge edge=new Edge(source,target,weight);
        if(checkEdges(edge))
        {

            //这种情况下删除list中原有的边
            if(weight==0)
            {
                edges.remove(edge);
                return weight;
            }
            if(weight<0)
            {
                throw new IllegalArgumentException("illegal input") ;
            }
            int checker=checkContains(edge);
            //更新weight值
            if(checker>0)
            {
                Edge edge2=new Edge(source,target,checker);
                edges.remove(edge2);
                Edge edge3=new Edge(source,target,weight);
                edges.add(edge3);
            }



        }
        else {
            edges.add(edge);
        }


        return weight;
    }
    //删除edges中所有包括vertex节点的边
    public void removeAll(String vertex)
    {
        for(Edge edge:edges)
        {
            if(edge.source==vertex||edge.target==vertex)
            {
                edges.remove(edge);
            }
        }

    }
    public int getWeight(String source,String target)
    {
        for(Edge edge:edges)
        {

            if(edge.source.equals(source)&&edge.target.equals(target))
            {
                return edge.weight;
            }
        }
        return 0;

    }
    @Override public boolean remove(String vertex) {
        if(vertices.contains(vertex))
        {
            vertices.remove(vertex);
            removeAll(vertex);
            return true;
        }
        return false;
    }
    ///在哈希表中加入一条边（如果之前没有的话）
    @Override public Set<String> vertices() {
        return vertices;
    }
    ///返回target与source边的权重
    @Override public Map<String, Integer> sources(String target) {
        Set<String> sourceSet=medium.getSource(target);
        Map<String,Integer>res=new HashMap<>();
        for(String source:sourceSet){
            res.put(source,getWeight(source,target));
        }
        return res;

    }
    ///返回target与source边的权重
    @Override public Map<String, Integer> targets(String source) {
        Set<String> sourceSet=medium.getTarget(source);
        Map<String,Integer>res=new HashMap<>();
        for(String target:sourceSet){
            res.put(target,getWeight(source,target));
        }
        return res;
    }
    
    // TODO toString()
    
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge {
    String source;
    String target;
    int weight;
    Edge(String source,String target,int weight)
    {
        this.source=source;
        this.target=target;
        this.weight=weight;
    }
    // TODO fields
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    
    // TODO checkRep
    
    // TODO methods
    
    // TODO toString()
    
}
