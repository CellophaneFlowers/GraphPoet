/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import graph.ConcreteEdgesGraph;
import graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    

    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    //input用来被加工成诗词 corpus提供原料
    public GraphPoet(File corpus,String input) throws IOException {
        try (InputStream in = new FileInputStream(corpus)) {
            byte[] bytes = in.readAllBytes();
            String text = new String(bytes);
            //这个参数不是text吧？
            ConcreteEdgesGraph a=poem(text);
            a.layout();
            //储存graph中的每一个节点
            Set<String> keywords=a.vertices();
            String[] words = input.split(" ");
            String res="";
            String word2="";
            for (int i = 0; i < words.length - 1; i++) {
                String word1 = words[i];
                word2 = words[i + 1];
                String insertWord="";
                if(a.checkVertices(word1)&&a.checkVertices(word2))
                {
                    //如何insert不影响到下标？
                    insertWord=insert(word1,word2,a);

                }
                res+=word1;
                res+=" ";
                if(!insertWord.isEmpty())
                {
                    res+=insertWord;
                    res+=" ";
                }

            }
            res+=word2;

        } catch (IOException e) {
            throw new IOException("Error reading from file: " + corpus.getPath(), e);
        }


    }
    //找到中间的那个词
    public String insert(String source, String target, ConcreteEdgesGraph graph) {
        Map<String, Integer> sourceTargets = graph.targets(source);
        int maxWeight = -1;
        String res = "";
        for (String mid : sourceTargets.keySet()) {
            Map<String, Integer> targetTargets = graph.targets(mid);
            int midWeight = -1;

            if (targetTargets.containsKey(target)) {

                midWeight += graph.getWeight(source, mid);
                midWeight += graph.getWeight(mid, target);
            }
            if (midWeight > maxWeight) {
                res = mid;
                maxWeight = midWeight;
            }
        }
        return res;
    }

    // TODO checkRep
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public ConcreteEdgesGraph poem(String input) {
        String[] words = input.split(" ");
        ConcreteEdgesGraph a=new ConcreteEdgesGraph();
        // 以两个元素为一组访问数组，组间有重叠
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            if(!a.checkVertices(word1))
            {
                a.add(word1);
            }if(!a.checkVertices(word2))
            {
                a.add(word2);
            }

            int weight=a.getWeight(word1,word2);
            a.set(word1,word2,weight+1);
            a.getMedium().set(word1,word2,weight+1);


        }

        return a;
    }


    }
    
    // TODO toString()


