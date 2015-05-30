package eu.leadowl.tp.axon.quickstart.handler;

import org.axonframework.eventhandling.annotation.EventHandler;

import eu.leadowl.tp.axon.quickstart.event.ToDoItemCompletedEvent;
import eu.leadowl.tp.axon.quickstart.event.ToDoItemCreatedEvent;

public class ToDoEventHandler {

  @EventHandler
  public void handle(final ToDoItemCreatedEvent event) {
    System.out.println(
        "We've got something to do: " + event.getDescription() + " (" + event.getTodoId() + ")");
  }

  @EventHandler
  public void handle(final ToDoItemCompletedEvent event) {
    System.out.println("We've completed a task: " + event.getTodoId());
  }

}
