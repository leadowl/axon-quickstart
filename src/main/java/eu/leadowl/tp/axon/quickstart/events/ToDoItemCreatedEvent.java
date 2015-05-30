package eu.leadowl.tp.axon.quickstart.events;

public class ToDoItemCreatedEvent {

	private final String todoId;
	private final String description;

	public ToDoItemCreatedEvent(final String todoId, final String description) {
		this.todoId = todoId;
		this.description = description;
	}

	public String getTodoId() {
		return todoId;
	}

	public String getDescription() {
		return description;
	}
}