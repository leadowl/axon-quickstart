package eu.leadowl.tp.axon.quickstart.aggregate;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import eu.leadowl.tp.axon.quickstart.aggregate.ToDoItem;
import eu.leadowl.tp.axon.quickstart.command.CreateToDoItemCommand;
import eu.leadowl.tp.axon.quickstart.command.MarkCompletedCommand;
import eu.leadowl.tp.axon.quickstart.event.ToDoItemCompletedEvent;
import eu.leadowl.tp.axon.quickstart.event.ToDoItemCreatedEvent;

public class ToDoItemTest {

  private FixtureConfiguration<ToDoItem> fixture;

  @Before
  public void setUp() throws Exception {
    fixture = Fixtures.newGivenWhenThenFixture(ToDoItem.class);
  }

  @Test
  public void testCreateToDoItem() throws Exception {
    fixture.given().when(new CreateToDoItemCommand("todo1", "need to implement the aggregate"))
        .expectEvents(new ToDoItemCreatedEvent("todo1", "need to implement the aggregate"));
  }

  @Test
  public void testMarkToDoItemAsCompleted() throws Exception {
    fixture.given(new ToDoItemCreatedEvent("todo1", "need to implement the aggregate"))
        .when(new MarkCompletedCommand("todo1")).expectEvents(new ToDoItemCompletedEvent("todo1"));
  }

}
