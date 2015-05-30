package eu.leadowl.tp.axon.quickstart.aggregates;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import eu.leadowl.tp.axon.quickstart.commands.CreateToDoItemCommand;
import eu.leadowl.tp.axon.quickstart.commands.MarkCompletedCommand;
import eu.leadowl.tp.axon.quickstart.events.ToDoItemCompletedEvent;
import eu.leadowl.tp.axon.quickstart.events.ToDoItemCreatedEvent;

public class ToDoItem extends AbstractAnnotatedAggregateRoot<String> {

  /**
   *
   */
  private static final long serialVersionUID = -428487818685350322L;

  @AggregateIdentifier
  private String id;

  public ToDoItem() {}

  @CommandHandler
  public ToDoItem(final CreateToDoItemCommand command) {
    apply(new ToDoItemCreatedEvent(command.getTodoId(), command.getDescription()));
  }

  @EventHandler
  public void on(final ToDoItemCreatedEvent event) {
    id = event.getTodoId();
  }

  @CommandHandler
  public void markCompleted(final MarkCompletedCommand command) {
    apply(new ToDoItemCompletedEvent(id));
  }

}
