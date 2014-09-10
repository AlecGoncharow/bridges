package edu.uncc.cs.bridgesdrivers;

import edu.uncc.cs.bridges.*;

public class QueueDriver {

	public static void main(String[] args) {
		Queue<Follower> queue = new Queue<Follower>();
		Bridge.init(3, "1157177351793", queue, "mmehedin@uncc.edu");
		//AbstractVertex<Follower> entity1= new QueueNode<>(new Follower("entity1"), queue);
		//AbstractVertex<Follower> entity2= new QueueNode<>(new Follower("entity2"), queue);
		queue.enQueue(new Follower("entity1"));
		//queue.enQueue(new Follower("entity2"));
		
		queue.clear();
		
		
		queue.enQueue(new Follower("entity2"));
		queue.enQueue(new Follower("entity3"));
		queue.enQueue(new Follower("entity4"));
		System.out.println(queue.vertices);
		queue.deQueue();
		queue.deQueue();
		//queue.deQueue();
		//queue.deQueue();
		//queue.deQueue();
	
		
		Bridge.complete();

	}

}
