package bridgesdrivers;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bridges.*;

public class TwitterDriver {

	public static void main(String[] args) throws Exception {
		// TODO Your code here
		GraphVisualizer gv = new GraphVisualizer();
		Bridge.init(0, "796340034401", gv);
		
		int expands_remaining = 10;
		
		Deque<Vertex> frontier = new ArrayDeque<>();
		Map<String, Vertex> visited = new HashMap<>();
		Map<String, Vertex> parent_of = new HashMap<>();
		String name = "Zoey";
		Vertex joey = new Vertex(name, gv);
		joey.setSize(20);
		joey.setColor("orange");
		
		frontier.add(joey);
		visited.put(name,  joey);
		System.out.println(
				SampleDataGenerator.getFriends(joey.getIdentifier(), 10));
		
		
		while ((!frontier.isEmpty()) && expands_remaining > 0) {
			Vertex source = frontier.pop();
			for (String friend_name : SampleDataGenerator.getFriends(source.getIdentifier(),10)) {
				
				Vertex target = visited.get(friend_name);
				if (target == null) {
					target = new Vertex(friend_name, gv);
					parent_of.put(target.getIdentifier(), source);
					
					// The student's do this part for fun! :P
					target.setSize(source.getSize() -4);
					visited.put(friend_name, target);
					frontier.add(target);
				}

				source.createEdge(target);
			}
			frontier.remove(source);
			expands_remaining -= 1;
		}
		
		// Find the route from Joey to Michael by going backward
		// A BFS will give an MST here because the weights are always 1.
		// Luckily, we just did a BFS
		String node = "Audrey";
		visited.get(node).setColor("green");
		
		while (! node.equals("Zoey")) {
			Vertex parent = parent_of.get(node);
			parent.getEdge(visited.get(node)).setColor("red");
			node = parent.getIdentifier();
		}
		
		/*
		Vertex HiWorld = new Vertex("Back", gv);
		Vertex Bob = new Vertex("Bob", gv);
		Vertex Steve = new Vertex("Steve", gv);
		
		HelloWorld.createEdge(HiWorld);
		HelloWorld.createEdge(Bob);
		Steve.createEdge(HelloWorld);
		
		Steve.createEdge(Bob);
		
		Vertex John = new Vertex("John", gv);
		
		Vertex Dave = new Vertex("Dave", gv);
		
		//John.createEdge(Dave);

		Dave.createEdge(John);
		
		Dave.getEdge(John).setColor("red");//works
		Dave.getEdge(John).setWidth(5);//works
		Dave.getEdge(John).setDash(new double[]{5, 10, 5});//works
		Dave.getEdge(Dave).setOpacity(.5);//works
		Bob.setShape("Square");//works
		Bob.setColor("pink");//works
		Bob.setOpacity(1);// works
		Bob.setSize(20);//works
		*/
		Bridge.complete();
	}

}