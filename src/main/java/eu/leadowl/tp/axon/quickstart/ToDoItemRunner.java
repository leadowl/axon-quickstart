package eu.leadowl.tp.axon.quickstart;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.common.jpa.SimpleEntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerAdapter;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.jpa.JpaEventStore;

import eu.leadowl.tp.axon.quickstart.aggregate.ToDoItem;
import eu.leadowl.tp.axon.quickstart.command.CreateToDoItemCommand;
import eu.leadowl.tp.axon.quickstart.command.MarkCompletedCommand;
import eu.leadowl.tp.axon.quickstart.handler.ToDoEventHandler;

public class ToDoItemRunner {

  public static void main(final String[] args) {
    // let's start with the Command Bus
    final CommandBus commandBus = new SimpleCommandBus();

    // the CommandGateway provides a friendlier API
    final CommandGateway commandGateway = new DefaultCommandGateway(commandBus);

    final EntityManagerFactory entityManagerFactory =
        Persistence.createEntityManagerFactory("eventStore");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    final EventStore eventStore = new JpaEventStore(new SimpleEntityManagerProvider(entityManager));

    // a Simple Event Bus will do
    final EventBus eventBus = new SimpleEventBus();

    // we need to configure the repository
    final EventSourcingRepository<ToDoItem> repository =
        new EventSourcingRepository<ToDoItem>(ToDoItem.class, eventStore);
    repository.setEventBus(eventBus);

    // Axon needs to know that our ToDoItem Aggregate can handle commands
    AggregateAnnotationCommandHandler.subscribe(ToDoItem.class, repository, commandBus);

    // registering event listener
    AnnotationEventListenerAdapter.subscribe(new ToDoEventHandler(), eventBus);

    // and let's send some Commands on the CommandBus.
    final String itemId = UUID.randomUUID().toString();
    commandGateway.send(new CreateToDoItemCommand(itemId, "Need to do this"));
    commandGateway.send(new MarkCompletedCommand(itemId));

    entityManager.close();
    entityManagerFactory.close();
  }
}
